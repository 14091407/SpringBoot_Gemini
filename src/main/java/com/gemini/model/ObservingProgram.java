package com.gemini.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import com.gemini.ocs.model.Filter;
import com.gemini.ocs.model.Lens;
import com.gemini.ocs.model.SpecialEquipment;
import jparsec.observer.LocationElement;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@JsonTypeInfo(use = JsonTypeInfo.Id.NAME, include = JsonTypeInfo.As.WRAPPER_OBJECT)
@Entity
public class ObservingProgram {
    @JsonProperty("locationElement")
    private LocationElement loc;
    private boolean isLightDetectorOn;
    @ElementCollection
    @CollectionTable
    @JsonProperty("specialEquipment")
    private List<String> specialEquipments;
    @OneToMany(cascade = CascadeType.ALL)
    @JsonProperty("filter")
    private List<filter> filters;
    @ElementCollection
    @CollectionTable
    @JsonProperty("exposures")
    private List<Double> exposures;
    @OneToOne(cascade = CascadeType.ALL)
    @JsonProperty("lens")
    private lens lens;

    @Id
    private int id;

    public ObservingProgram(){}

    public ObservingProgram(LocationElement locationElement
            , ArrayList<String> specialEquipments
            , ArrayList<filter> filter
            , ArrayList<Double> exposures
            , lens lens, boolean isLightDetectorOn
    ){
        this.loc = new LocationElement();
        this.filters = filter;
        this.exposures = exposures;
        this.lens = lens;
        this.isLightDetectorOn = isLightDetectorOn;
        this.specialEquipments = specialEquipments;
    }

    public LocationElement getLoc() {
        return loc;
    }

    public lens getLens() {
        return lens;
    }

    public ArrayList<filter> getFilters() {
        return new ArrayList<>(filters);
    }

    public ArrayList<Double> getExposures() {
        return new ArrayList<>(exposures);
    }

    public boolean isLightDetectorOn() {
        return isLightDetectorOn;
    }

    public ArrayList<String> getSpecialEquipments() {
        return new ArrayList<>(specialEquipments);
    }
}
