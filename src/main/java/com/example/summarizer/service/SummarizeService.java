package com.example.summarizer.service;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Map;

import org.springframework.ai.chat.model.ChatResponse;
import org.springframework.ai.chat.prompt.Prompt;
import org.springframework.ai.chat.prompt.PromptTemplate;
import org.springframework.ai.openai.OpenAiChatModel;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Service;

@Service
public class SummarizeService {

    private final OpenAiChatModel chatModel;
 
    private static final String PROMPT_PATH = "src/main/resources/prompts/summarize_prompt.txt";

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
        try {
            // 실시간으로 파일을 읽어서 변경사항이 즉시 반영되도록 함
            Path path = Paths.get(PROMPT_PATH);
            return Files.readString(path, StandardCharsets.UTF_8);
        } catch (IOException e) {
            throw new RuntimeException("프롬프트 파일 읽기 실패", e);
        }
    }
}