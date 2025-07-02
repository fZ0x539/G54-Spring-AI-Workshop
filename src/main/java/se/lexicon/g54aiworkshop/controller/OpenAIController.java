package se.lexicon.g54aiworkshop.controller;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.RequiredArgsConstructor;
import org.springframework.ai.chat.model.ChatResponse;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;
import se.lexicon.g54aiworkshop.service.OpenAiService;

@RestController
@RequestMapping("/api/chat")
@RequiredArgsConstructor
public class OpenAIController {

    private final OpenAiService openAiService;

    @GetMapping
    public Flux<ChatResponse> processSimpleChatQuery(
            @NotNull(message = "Question cannot be null.")
            @NotBlank(message = "Question cannot be blank.")
            @Size(max = 500, message = "Question cannot exceed 500 characters")
            @RequestParam String question,
            @RequestParam(required = false, name = "expertiseLevel", defaultValue = "intermediate") String level
    ){
       return openAiService.processChatQueryWithStreamAndContext(question, level);
    }

}
