package com.example.summarizer.config;

import org.springframework.ai.openai.OpenAiChatModel;
import org.springframework.ai.openai.OpenAiChatOptions;
import org.springframework.ai.openai.api.OpenAiApi;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AiClientConfig {
    
    @Value("${spring.ai.openai.api-key:}")
    private String apiKey;
    
    @Value("${spring.ai.openai.model:gpt-4o-mini}")
    private String model;
    
    @Value("${spring.ai.openai.base-url:https://api.openai.com}")
    private String baseUrl;
    
    @Bean
    @ConditionalOnProperty(name = "spring.ai.openai.api-key", matchIfMissing = false)
    public OpenAiApi openAiApi() {
        if (apiKey == null || apiKey.trim().isEmpty()) {
            return null;
        }
        return new OpenAiApi(baseUrl, apiKey);
    }
    
    @Bean
    @ConditionalOnProperty(name = "spring.ai.openai.api-key", matchIfMissing = false)
    public OpenAiChatModel openAiChatModel(OpenAiApi openAiApi) {
        if (openAiApi == null) {
            return null;
        }
        return new OpenAiChatModel(openAiApi, 
            OpenAiChatOptions.builder()
                .withModel(model)
                .build());
    }
}
