package com.gemini.model;

import com.gemini.ocs.model.Filter;
import com.gemini.ocs.model.Lens;
import com.gemini.ocs.model.SpecialEquipment;
import jparsec.observer.LocationElement;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
public class ObservingProgram {
    private LocationElement loc;
    private boolean isLightDetectorOn;
    @ElementCollection
    @CollectionTable
    private List<String> specialEquipments;
    @OneToMany(cascade = CascadeType.ALL)
    private List<filter> filters;
    @ElementCollection
    @CollectionTable
    private List<Double> exposures;
    @OneToOne(cascade = CascadeType.ALL)
    private lens lens;
    @Id
    private int id;

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
