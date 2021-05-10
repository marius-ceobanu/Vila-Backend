package com.marius.vila.messaging.repository;

import com.marius.vila.messaging.model.Message;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MessageInventory extends JpaRepository<Message, Long> {
}
