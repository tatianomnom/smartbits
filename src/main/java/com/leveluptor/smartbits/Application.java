package com.leveluptor.smartbits;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

@SpringBootApplication
public class Application {
    public static void main(String[] args) throws Exception {
        ConfigurableApplicationContext context = SpringApplication.run(Application.class, args);

        MessageRepository messageRepository = context.getBean(MessageRepository.class);
        messageRepository.save(new Message("short", "long"));
        messageRepository.save(new Message("shoooort", "loooong"));
    }
}
