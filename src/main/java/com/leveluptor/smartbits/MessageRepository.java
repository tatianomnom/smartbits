package com.leveluptor.smartbits;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MessageRepository extends CrudRepository<Message, Long> {

    List<Message> findAll();

    List<Message> findAllByOrderByTimestampDesc();
}
