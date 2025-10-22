<<<<<<< HEAD
# ai-project
=======


# 기존 프로세스 삭제
$ pkill -f "spring-ai-summarizer"

# build 
$ cd /Users/miyoung/workspace-opa-mentoring-2025/spring-ai-summarizer && gradle clean build

# 구동
$ ./gradlew bootRun

# 테스트 
$ curl -X POST http://localhost:8080/api/summarize -H "Content-Type: text/plain" -d "Spring AI는 Spring Framework의 확장으로, 인공지능 기능을 Spring 애플리케이션에 쉽게 통합할 수 있게 해주는 프레임워크입니다."


>>>>>>> 11d86b5 (init)
