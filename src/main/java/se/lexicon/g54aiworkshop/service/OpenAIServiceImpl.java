package se.lexicon.g54aiworkshop.service;

import lombok.RequiredArgsConstructor;
import org.springframework.ai.chat.messages.SystemMessage;
import org.springframework.ai.chat.messages.UserMessage;
import org.springframework.ai.chat.model.ChatResponse;
import org.springframework.ai.chat.prompt.ChatOptions;
import org.springframework.ai.chat.prompt.Prompt;
import org.springframework.ai.openai.OpenAiChatModel;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;

@Service
@RequiredArgsConstructor
public class OpenAIServiceImpl implements OpenAiService {

    private final OpenAiChatModel openAiChatModel;


    @Override
    public Flux<ChatResponse> processChatQueryWithStreamAndContext(String query, String level) {
        if (query == null || query.isEmpty())
            throw new IllegalArgumentException("Query cannot be null or empty.");

        ExpertiseLevel expertiseLevel;
        switch (level.toLowerCase()) {
            case "beginner":
                expertiseLevel = ExpertiseLevel.BEGINNER;
            case "intermediate":
                expertiseLevel = ExpertiseLevel.INTERMEDIATE;
            case "advanced":
                expertiseLevel = ExpertiseLevel.ADVANCED;
            default:
                expertiseLevel = ExpertiseLevel.INTERMEDIATE;
        }

        SystemMessage systemMessage = SystemMessage.builder().text(systemInstructionTemplate(expertiseLevel)).build();
        UserMessage userMessage = UserMessage.builder().text(query).build();
        Prompt prompt = Prompt.builder()
                .messages(systemMessage, userMessage)
                .chatOptions(ChatOptions.builder()
                        .model("gpt-4.1-mini")
                        .temperature(0.3)
                        .maxTokens(500)
                        .build())
                .build();

        try {
//            return openAiChatModel.call(prompt).getResult().getOutput().getText();
            return openAiChatModel.stream(prompt);
        } catch (RuntimeException e) {
            throw new RuntimeException("Error processing chat query:" + e.getMessage());
        }
    }

    @Override
    public String systemInstructionTemplate(ExpertiseLevel level) {
        return String.format("""
                You are an expert Java programming tutor assistant with 10+ years of experience teaching Java at all skill levels. Your role is to:
                
                1. Provide clear, accurate explanations of Java concepts
                2. Offer practical coding examples and best practices
                3. Adapt explanations to the user's stated expertise level
                4. Focus exclusively on Java and related ecosystem topics (JVM, build tools, frameworks)
                5. Politely decline to answer non-Java questions by suggesting you're specialized in Java
                
                Respond in accodrance with the specified Expertise level: %s. %s
                """, level.name(), level.getInstruction());
    }
}
