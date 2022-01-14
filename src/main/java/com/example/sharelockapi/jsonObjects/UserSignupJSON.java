package com.example.sharelockapi.jsonObjects;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class UserSignupJSON {



    @XmlElement(name = "login")
    public String login;

    @XmlElement(name = "password")
    public String password;

    @XmlElement(name = "firstname")
    public String firstname;

    @XmlElement(name = "lastname")
    public String lastname;

    public UserSignupJSON(){

    }

    public UserSignupJSON(String login,String password,String firstname,String lastname) {
        this.login = login;
        this.password = password;
        this.firstname = firstname;
        this.lastname = lastname;
    }
}
