package com.example.summarizer.service;

import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.util.Map;

import org.springframework.ai.chat.model.ChatResponse;
import org.springframework.ai.chat.prompt.Prompt;
import org.springframework.ai.chat.prompt.PromptTemplate;
import org.springframework.ai.openai.OpenAiChatModel;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Service;

@Service
public class SummarizeService {

    private final OpenAiChatModel chatModel;
 
    @Value("classpath:prompts/summarize_prompt.txt")
    private Resource summarizePrompt;

    public SummarizeService(OpenAiChatModel chatModel) {
        this.chatModel = chatModel;
    }

    public String summarize(String text) {
        try {
            String template = loadPrompt();
            PromptTemplate promptTemplate = new PromptTemplate(template);
            Prompt promptObj = promptTemplate.create(Map.of("input", text));

            ChatResponse response = chatModel.call(promptObj);

            return response.getResult().getOutput().getContent();
        } catch (Exception e) {
            throw new RuntimeException("텍스트 요약 실패", e);
        }
    }

    private String loadPrompt() {
        try (InputStream is = summarizePrompt.getInputStream()) {
    
            return new String(is.readAllBytes(), StandardCharsets.UTF_8);

        } catch (IOException e) {
            throw new RuntimeException("프롬프트 파일 읽기 실패", e);
        }
    }
}