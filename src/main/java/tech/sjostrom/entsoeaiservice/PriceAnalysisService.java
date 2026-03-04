package tech.sjostrom.entsoeaiservice;

import org.springframework.ai.chat.client.ChatClient;
import org.springframework.stereotype.Service;

@Service
public class PriceAnalysisService {

    private final ChatClient chatClient;

    public PriceAnalysisService(ChatClient.Builder builder) {
        this.chatClient = builder.build();
    }

    public String analyze() {
        String prompt = """
                Du är en energianalytiker. 
                Elpriset i Sverige (SE3) har varierat mycket senaste dagarna.
                Ge en kort analys på svenska om när det generellt brukar vara billigast 
                att använda el under dygnet, och ge ett enkelt råd till en hushållskund.
                Svara med max 3 meningar.
                """;

        return chatClient.prompt()
                .user(prompt)
                .call()
                .content();
    }
}
