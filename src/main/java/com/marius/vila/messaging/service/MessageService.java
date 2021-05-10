package com.marius.vila.messaging.service;

import com.marius.vila.messaging.dto.MessageDto;
import com.marius.vila.messaging.model.Message;
import com.marius.vila.messaging.repository.MessageInventory;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class MessageService {
    private final MessageInventory messageInventory;

    public ResponseEntity<String> addMessage(MessageDto messageDto) {
        if(validateMessage(messageDto)) {
            Message message = new Message();
            message.setName(messageDto.getName());
            message.setEmail(messageDto.getEmail());
            message.setMessage(messageDto.getMessage());
            messageInventory.save(message);
            return new ResponseEntity<>("Mesaj inregistrat cu succes!", HttpStatus.OK);
        }
        return new ResponseEntity<>("Va rugam sa completati toate campurile!", HttpStatus.INTERNAL_SERVER_ERROR);
    }

    private Boolean validateMessage (MessageDto messageDto) {
        return messageDto.getName().length() > 0 &&
                messageDto.getEmail().length() > 0 &&
                messageDto.getMessage().length() > 0;
    }
}
