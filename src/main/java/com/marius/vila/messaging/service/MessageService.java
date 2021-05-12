package com.marius.vila.messaging.service;

import com.marius.vila.messaging.dto.MessageDto;
import com.marius.vila.messaging.model.Message;
import com.marius.vila.messaging.repository.MessageRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class MessageService {
    private final MessageRepository messageRepository;

    @Transactional
    public ResponseEntity<String> addMessage(MessageDto messageDto) {
        if(validateMessage(messageDto)) {
            Message message = new Message();
            message.setName(messageDto.getName());
            message.setEmail(messageDto.getEmail());
            message.setMessage(messageDto.getMessage());
            messageRepository.save(message);
            return new ResponseEntity<>("Mesaj inregistrat cu succes!", HttpStatus.OK);
        }
        return new ResponseEntity<>("Va rugam sa completati toate campurile!", HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @Transactional
    public List<Message> getAllMessagesByEmail(String email) {
        return messageRepository.findAllByEmail(email);
    }

    @Transactional
    public List<Message> getAllMessages() {
        return messageRepository.findAllByOrderByIdDesc();
    }

    @Transactional
    public ResponseEntity<String> answerMessage(String messageId, String answer) {
        try {
            Message message = getMessageById(messageId);
            message.setAnswer(answer);
            messageRepository.save(message);
            return new ResponseEntity<>("Message answered!", HttpStatus.OK);
        } catch (Exception e){
            return new ResponseEntity<>("Message not found!", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    private Message getMessageById(String messageId) {
        return messageRepository.getOne(Long.parseLong(messageId));
    }

    private Boolean validateMessage (MessageDto messageDto) {
        return messageDto.getName().length() > 0 &&
                messageDto.getEmail().length() > 0 &&
                messageDto.getMessage().length() > 0;
    }
}
