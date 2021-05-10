package com.marius.vila.messaging.controller;

import com.marius.vila.messaging.dto.MessageDto;
import com.marius.vila.messaging.service.MessageService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/vila/v1/messages")
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
@CrossOrigin(origins = "http://localhost:3000", allowedHeaders = "*")
public class MessageController {
    private final MessageService messageService;

    @PostMapping("/add")
    public ResponseEntity<String> addMessage(@RequestBody MessageDto messageDto) {
        return messageService.addMessage(messageDto);
    }
}
