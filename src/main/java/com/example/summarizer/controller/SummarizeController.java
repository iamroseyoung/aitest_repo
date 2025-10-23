package com.example.summarizer.controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.summarizer.service.SummarizeService;
import com.example.summarizer.service.MockSummarizeService;

@Controller
@RequestMapping("/api")
public class SummarizeController {

    private final SummarizeService summarizeService;

    public SummarizeController(SummarizeService summarizeService) {
        this.summarizeService = summarizeService;
    }

    @GetMapping("/")
    public String index() {
        return "forward:/index.html";
    }

    @PostMapping("/summarize")
    public ResponseEntity<String> summarize(@RequestBody Map<String, String> body) {
        String text = body.get("text"); 
        
        try {
            return ResponseEntity.ok(summarizeService.summarize(text));
        } catch (Exception e) {
            return ResponseEntity.status(500).body("요약 중 오류가 발생했습니다: " + e.getMessage());
        }
    }
}
