package tech.sjostrom.entsoeaiservice;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping("/api/analysis")
public class PriceAnalysisController {

    private final PriceAnalysisService analysisService;

    public PriceAnalysisController(PriceAnalysisService analysisService) {
        this.analysisService = analysisService;
    }

    @GetMapping
    public ResponseEntity<Map<String, String>> getAnalysis() {
        String analysis = analysisService.analyzePrices();
        return ResponseEntity.ok(Map.of("analysis", analysis));
    }
}
