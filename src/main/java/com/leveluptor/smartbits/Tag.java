package com.leveluptor.smartbits;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class Tag {

    @Id
    @GeneratedValue
    private Long id;

    private String name;


    public Tag(String name) {
        this.name = name;
    }

    Tag() {
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    @Override
    public String toString() {
        return name; //todo for now
    }
}
