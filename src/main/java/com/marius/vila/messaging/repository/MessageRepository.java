package com.marius.vila.messaging.repository;

import com.marius.vila.messaging.model.Message;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface MessageRepository extends JpaRepository<Message, Long> {
    List<Message> findAllByEmail(String email);
    List<Message> findAllByOrderByIdDesc();
}
