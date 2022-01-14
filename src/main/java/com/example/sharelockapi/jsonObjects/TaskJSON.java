package com.example.sharelockapi.jsonObjects;

import javax.xml.bind.annotation.XmlElement;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class TaskJSON {


    @XmlElement(name = "title")
    public String title;

    @XmlElement(name = "description")
    public String description;

    @XmlElement(name = "point")
    public int point;


    @XmlElement(name = "idCategory")
    public int idCategory;



    @XmlElement(name = "idHouseShare")
    public int idHouseShare;
}
