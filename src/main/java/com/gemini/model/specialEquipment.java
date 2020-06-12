package com.gemini.model;

import com.fasterxml.jackson.annotation.JsonProperty;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.util.concurrent.atomic.AtomicInteger;

@Entity
public class specialEquipment {
    private static AtomicInteger count = new AtomicInteger(0);
    @JsonProperty("equipmentName")
    private String equipmentName;
    @JsonProperty("ownerName")
    private String ownerName;
    @JsonProperty("installedDate")
    private String installedDate;
    @Id
    private int id;

    public specialEquipment(){}

    public specialEquipment(String equipmentName,String ownerName,String installedDate){
        this.equipmentName=equipmentName;
        this.ownerName=ownerName;
        this.installedDate=installedDate;
        this.id=count.incrementAndGet();
    }

}
