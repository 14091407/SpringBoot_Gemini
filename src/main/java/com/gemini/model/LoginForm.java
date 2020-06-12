package com.gemini.model;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class LoginForm {
    private String username;
    private String password;

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }
}
