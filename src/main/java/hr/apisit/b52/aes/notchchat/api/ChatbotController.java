package hr.apisit.b52.aes.notchchat.api;

import hr.apisit.b52.aes.notchchat.api.model.UserMessage;
import hr.apisit.b52.aes.notchchat.service.ChatbotService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/employee")
public class ChatbotController {

    private final ChatbotService chatbotService;

    ChatbotController(ChatbotService chatbotService) {
        this.chatbotService = chatbotService;
    }

    @PostMapping("/chat/{chatId}")
    public String chat(@PathVariable String chatId, @RequestBody UserMessage userMessage) {
        return chatbotService.chat(chatId, userMessage.text());
    }
}
