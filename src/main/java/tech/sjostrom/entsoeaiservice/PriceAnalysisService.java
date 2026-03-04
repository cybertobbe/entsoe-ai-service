package tech.sjostrom.entsoeaiservice;

import org.springframework.ai.chat.client.ChatClient;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class PriceAnalysisService {

    private final ChatClient chatClient;
    private final RestTemplate restTemplate;

    @Value("${entsoe.price-analyzer.url}")
    private String priceAnalyzerUrl;

    public PriceAnalysisService(ChatClient.Builder chatClientBuilder, RestTemplate restTemplate) {
        this.chatClient = chatClientBuilder.build();
        this.restTemplate = restTemplate;
    }

    public String analyzePrices() {
        List<PriceAnalysis> prices = fetchTodayPrices();
        DailySummary summary = fetchTodaySummary();

        if (prices == null || prices.isEmpty()) {
            return "Ingen prisdata tillgänglig för idag.";
        }

        String priceData = prices.stream()
                .map(p -> String.format("%s -> %.2f SEK/kWh (inkl moms: %.2f SEK/kWh)",
                        p.getTimestamp().toLocalTime(),
                        p.getPriceSekKwh(),
                        p.getPriceSekKwhInclVat()))
                .collect(Collectors.joining("\n"));

        String summaryData = summary != null ? String.format("""
        Statistik för dagen:
        - Snitt: %.2f SEK/kWh (inkl moms: %.2f SEK/kWh)
        - Lägsta: %.2f SEK/kWh
        - Högsta: %.2f SEK/kWh
        - Billigaste perioder: %s
        - Dyraste perioder: %s
        """,
                summary.getAverageSekKwh(),
                summary.getAverageSekKwhInclVat(),
                summary.getMinSekKwh(),
                summary.getMaxSekKwh(),
                summary.getCheapestHours().stream()
                        .map(p -> String.format("%s (%.2f SEK/kWh)",
                                p.getTimestamp().toLocalTime(),
                                p.getPriceSekKwh()))
                        .collect(Collectors.joining(", ")),
                summary.getExpensiveHours().stream()
                        .map(p -> String.format("%s (%.2f SEK/kWh)",
                                p.getTimestamp().toLocalTime(),
                                p.getPriceSekKwh()))
                        .collect(Collectors.joining(", "))
        ) : "";

        String prompt = """
                Du är en expert på den svenska elmarknaden. Nedan följer dagens elpriser för SE3 i SEK/kWh samt förberäknad statistik.
                
                %s
                
                %s
                
                Basera din analys på statistiken ovan och ge:
                1. De billigaste timmarna att använda el idag (använd de exakta värden från statistiken)
                2. De dyraste timmarna att undvika (använd de exakta värden från statistiken)
                3. Ett kort råd till ett hushåll om när de bör köra tvättmaskin, diskmaskin eller liknande
                
                Svara på svenska och håll svaret kortfattat.
                """.formatted(priceData, summaryData);

        return chatClient.prompt()
                .user(prompt)
                .call()
                .content();
    }

    private List<PriceAnalysis> fetchTodayPrices() {
        return restTemplate.exchange(
                priceAnalyzerUrl + "/api/analysis/today",
                HttpMethod.GET,
                null,
                new ParameterizedTypeReference<List<PriceAnalysis>>() {}
        ).getBody();
    }

    private DailySummary fetchTodaySummary() {
        return restTemplate.getForObject(
                priceAnalyzerUrl + "/api/analysis/summary/today",
                DailySummary.class
        );
    }
}