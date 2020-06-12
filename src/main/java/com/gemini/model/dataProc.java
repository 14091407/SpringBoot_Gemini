package com.gemini.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import com.gemini.ocs.model.DataProcRequirement;

import javax.persistence.*;
import java.util.concurrent.atomic.AtomicInteger;

@JsonTypeInfo(use = JsonTypeInfo.Id.NAME, include = JsonTypeInfo.As.WRAPPER_OBJECT)
@Entity
public class dataProc {
    private static AtomicInteger count = new AtomicInteger(0);
    @JsonProperty("saturation")
    private double saturation;
    @JsonProperty("brightness")
    private double brightness;
    @JsonProperty("contrast")
    private double contrast;
    @JsonProperty("fileQuality")
    private double fileQuality;
    @Id
    private Integer id;
    @JsonProperty("fileType")
    private String fileType;
    @JsonProperty("colorType")
    private String COLOR_TYPE;

    public dataProc(){}

    public dataProc(String fileType, double fileQuality
            , String COLOR_TYPE, double contrast
            , double brightness, double saturation){
        this.fileType=fileType;
        this.fileQuality=fileQuality;
        this.COLOR_TYPE=COLOR_TYPE;
        this.contrast=contrast;
        this.brightness=brightness;
        this.saturation=saturation;
        this.id = count.incrementAndGet();
    }
}
