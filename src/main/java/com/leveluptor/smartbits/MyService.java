package com.leveluptor.smartbits;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class MyService {

    private final MessageRepository messageRepository;

    private final UserRepository userRepository;

    @Autowired
    public MyService(MessageRepository messageRepository, UserRepository userRepository) {
        this.messageRepository = messageRepository;
        this.userRepository = userRepository;
    }

    public List<Message> findMessages(User user) {
        return messageRepository.findAllByAuthorOrderByTimestampDesc(user);
    }

    public Message findMessageById(Long id) {
        return messageRepository.findOne(id);
    }

    public void addMessage(Message message) {
        messageRepository.save(message);
    }

    public void addUser(String username, String password) {
        userRepository.save(new User(username, new BCryptPasswordEncoder().encode(password)));
    }

    public Optional<User> findByUsername(String username) {
        return Optional.ofNullable(userRepository.findOneByUsername(username));
    }
}
