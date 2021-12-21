package com.example.sharelockapi.jsonObjects;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class JWTJson {

    @XmlElement(name = "message")
    public String message;

    @XmlElement(name = "login")
    public String login;

    public JWTJson(String message,String login) {
        this.message = message;
        this.login = login;
    }
}
