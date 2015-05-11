package com.leveluptor.smartbits;

import org.hibernate.validator.constraints.Length;

public class RegistrationForm {
    private String username;

    @Length(min = 3)
    private String password;

    public RegistrationForm() {
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }
}
