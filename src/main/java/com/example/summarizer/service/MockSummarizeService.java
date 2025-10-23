package com.example.summarizer.service;

import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.stereotype.Service;

@Service
@ConditionalOnProperty(name = "spring.ai.openai.api-key", havingValue = "", matchIfMissing = true)
public class MockSummarizeService {

    public String summarize(String text) {
        // 간단한 Mock 요약 로직
        if (text == null || text.trim().isEmpty()) {
            return "입력된 텍스트가 없습니다.";
        }
        
        // 텍스트 길이에 따라 다른 Mock 응답
        int textLength = text.length();
        
        if (textLength < 100) {
            return "짧은 텍스트입니다. 원본: " + text;
        } else if (textLength < 500) {
            return "중간 길이의 텍스트입니다. 주요 내용: " + text.substring(0, Math.min(100, text.length())) + "...";
        } else {
            return "긴 텍스트입니다. 요약: " + text.substring(0, Math.min(200, text.length())) + "...\n\n" +
                   "이것은 Mock 서비스로 생성된 테스트 요약입니다. 실제 OpenAI API 키를 설정하면 진짜 AI 요약을 받을 수 있습니다.";
        }
    }
}
