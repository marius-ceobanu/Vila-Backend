package com.marius.vila.messaging.controller;

import com.marius.vila.messaging.dto.AnswerDto;
import com.marius.vila.messaging.dto.MessageDto;
import com.marius.vila.messaging.model.Message;
import com.marius.vila.messaging.service.MessageService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.transaction.Transactional;
import java.util.List;

@RestController
@RequestMapping("/vila/v1/messages")
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
@CrossOrigin(origins = "http://localhost:3000", allowedHeaders = "*")
public class MessageController {
    private final MessageService messageService;

    @Transactional
    @PostMapping("/add")
    public ResponseEntity<String> addMessage(@RequestBody MessageDto messageDto) {
        return messageService.addMessage(messageDto);
    }

    @Transactional
    @PostMapping("/answer")
    public ResponseEntity<String> answerMessage(@RequestBody AnswerDto answerDto) {
        return messageService.answerMessage(answerDto.getMessageId(), answerDto.getAnswer());
    }

    @Transactional
    @GetMapping("/user/{userEmail}")
    public List<Message> getMessagesByUser(@PathVariable("userEmail") String userEmail) {
        return messageService.getAllMessagesByEmail(userEmail);
    }

    @Transactional
    @GetMapping("/all")
    public List<Message> getAllForAdmin() {
        return messageService.getAllMessages();
    }
}
