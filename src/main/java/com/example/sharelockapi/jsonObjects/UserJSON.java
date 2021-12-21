package com.example.sharelockapi.jsonObjects;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class UserJSON {

    @XmlElement(name = "login")
    public String login;

    @XmlElement(name = "password")
    public String password;
}
