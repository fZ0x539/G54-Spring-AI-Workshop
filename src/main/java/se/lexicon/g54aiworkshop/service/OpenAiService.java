package se.lexicon.g54aiworkshop.service;

import reactor.core.publisher.Flux;

public interface OpenAiService {
    Flux<String> processChatQueryWithStreamAndContext(final String query, final String level);
    String systemInstructionTemplate(ExpertiseLevel level);
}
