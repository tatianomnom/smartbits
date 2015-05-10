package com.leveluptor.smartbits;

import org.springframework.data.repository.CrudRepository;

public interface UserRepository extends CrudRepository<User, Long> {
    User findOneByUsername(String username);
}
