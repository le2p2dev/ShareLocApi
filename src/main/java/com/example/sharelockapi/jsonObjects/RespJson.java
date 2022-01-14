package com.example.sharelockapi.jsonObjects;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class RespJson {

    @XmlElement(name = "message")
    public String message;



    public RespJson(String message) {
        this.message = message;

    }
}
