package com.example.sharelockapi.jsonObjects;

import com.example.sharelockapi.model.HouseshareEntity;
import com.example.sharelockapi.paths.HouseShare;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.List;

@XmlRootElement
public class HouseShareJson {

    @XmlElement(name = "houses")
    public List<HouseshareEntity> houses;
}
