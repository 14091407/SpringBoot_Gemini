package com.gemini.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.util.concurrent.atomic.AtomicInteger;

@Entity
@DynamicUpdate
public class locationElement {
    private static AtomicInteger count = new AtomicInteger(0);
    @JsonProperty("longitude")
    private Double longitude;
    @JsonProperty("latitude")
    private Double latitude;
    @JsonProperty("radius")
    private Double radius;
    @Id
    private int id;

    public locationElement(){}

    public locationElement(Double longitude, Double latitude, Double radius){
        this.longitude=longitude;
        this.latitude=latitude;
        this.radius=radius;
        this.id=count.incrementAndGet();
    }

    public void setLongitude(Double longitude) {
        this.longitude = longitude;
    }

    public void setLatitude(Double latitude) {
        this.latitude = latitude;
    }

    public Double getLongitude() {
        return longitude;
    }

    public Double getLatitude() {
        return latitude;
    }

    public Double getRadius() {
        return radius;
    }

    public void setRadius(Double radius) {
        this.radius = radius;
    }

}
