# Text Summarizer

Spring AI를 활용한 텍스트 요약 서비스입니다. OpenAI GPT 모델을 사용하여 한국어 텍스트를 자동으로 요약합니다.

## 🚀 주요 기능

- **AI 기반 텍스트 요약**: OpenAI GPT-4o-mini 모델을 사용한 고품질 텍스트 요약
- **REST API**: HTTP POST 요청을 통한 간편한 API 사용
- **프롬프트 템플릿**: 커스터마이징 가능한 요약 프롬프트
- **Spring Boot**: 안정적이고 확장 가능한 Spring Boot 기반 아키텍처

## 🛠 기술 스택

- **Java 17**
- **Spring Boot 3.4.0**
- **Spring AI 1.0.0-M3**
- **OpenAI API**
- **Gradle 9.1.0**

## 📋 사전 요구사항

- Java 17 이상
- OpenAI API 키
- Gradle (또는 Gradle Wrapper)

## 🚀 빠른 시작

### 1. 프로젝트 클론 및 빌드

```bash
git clone <repository-url>
cd spring-ai-summarizer
./gradlew build
```

### 2. OpenAI API 키 설정

`src/main/resources/application.yml` 파일에서 API 키를 설정하거나 환경 변수를 사용하세요:

```yaml
spring:
  ai:
    openai:
      api-key: your-openai-api-key-here
      model: gpt-4o-mini
      base-url: https://api.openai.com
```

또는 환경 변수로 설정:

```bash
export OPENAI_API_KEY=your-openai-api-key-here
```

### 3. 애플리케이션 실행

```bash
./gradlew bootRun
```

또는 JAR 파일로 실행:

```bash
java -jar build/libs/spring-ai-summarizer-0.0.1-SNAPSHOT.jar
```

### 4. API 테스트

```bash
curl -X POST http://localhost:8080/api/summarize \
  -H "Content-Type: text/plain" \
  -d "요약할 텍스트를 여기에 입력하세요"
```

## 📚 API 문서

### POST /api/summarize

텍스트를 요약합니다.

**요청:**
- **Method**: POST
- **Content-Type**: text/plain
- **Body**: 요약할 텍스트

**응답:**
- **Content-Type**: text/plain
- **Body**: 요약된 텍스트

**예시:**

```bash
curl -X POST http://localhost:8080/api/summarize \
  -H "Content-Type: text/plain" \
  -d "Spring AI는 Spring Framework의 확장으로, 인공지능 기능을 Spring 애플리케이션에 쉽게 통합할 수 있게 해주는 프레임워크입니다."
```

**응답:**
```
Spring AI는 Spring Framework의 확장판으로, 인공지능 기능을 쉽게 통합할 수 있도록 도와주는 프레임워크입니다.
```

## 🏗 프로젝트 구조

```
src/
├── main/
│   ├── java/
│   │   └── com/example/summarizer/
│   │       ├── SummarizerApplication.java      # 메인 애플리케이션
│   │       ├── controller/
│   │       │   └── SummarizeController.java    # REST API 컨트롤러
│   │       ├── service/
│   │       │   └── SummarizeService.java       # 텍스트 요약 서비스
│   │       └── config/
│   │           └── AiClientConfig.java          # AI 설정
│   └── resources/
│       ├── application.yml                     # 애플리케이션 설정
│       └── prompts/
│           └── summarize_prompt.txt           # 요약 프롬프트 템플릿
```

## ⚙️ 설정

### application.yml

```yaml
spring:
  ai:
    openai:
      api-key: ${OPENAI_API_KEY}
      model: gpt-4o-mini
      base-url: https://api.openai.com
server:
  port: 8080
```

### 프롬프트 템플릿

`src/main/resources/prompts/summarize_prompt.txt` 파일에서 요약 프롬프트를 커스터마이징할 수 있습니다:

```
다음 텍스트를 한국어로 간단히 요약해주세요:

{input}
```

## 🔧 개발

### 빌드

```bash
./gradlew build
```

### 테스트

```bash
./gradlew test
```

### 실행

```bash
./gradlew bootRun
```

## 📝 라이선스

이 프로젝트는 MIT 라이선스 하에 배포됩니다.

## 🤝 기여

1. Fork the Project
2. Create your Feature Branch (`git checkout -b feature/AmazingFeature`)
3. Commit your Changes (`git commit -m 'Add some AmazingFeature'`)
4. Push to the Branch (`git push origin feature/AmazingFeature`)
5. Open a Pull Request

## 📞 지원

문제가 발생하거나 질문이 있으시면 이슈를 생성해 주세요.

---

**Spring AI Text Summarizer** - AI 기반 텍스트 요약을 위한 Spring Boot 애플리케이션
