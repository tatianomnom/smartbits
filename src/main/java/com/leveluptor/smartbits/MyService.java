package com.leveluptor.smartbits;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class MyService {

    private final MessageRepository messageRepository;

    @Autowired
    public MyService(MessageRepository messageRepository) {
        this.messageRepository = messageRepository;
    }

    @Transactional
    public List<Message> findMessages() {
        return messageRepository.findAllByOrderByTimestampDesc();
    }

    public Message findMessageById(Long id) {
        return messageRepository.findOne(id);
    }

    public void addMessage(Message message) {
        messageRepository.save(message);
    }
}
