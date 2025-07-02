package se.lexicon.g54aiworkshop.service;

import org.springframework.ai.chat.model.ChatResponse;
import reactor.core.publisher.Flux;

public interface OpenAiService {
    Flux<ChatResponse> processChatQueryWithStreamAndContext(final String query, final String level);
    String systemInstructionTemplate(ExpertiseLevel level);
}
