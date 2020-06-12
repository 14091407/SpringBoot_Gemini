package com.gemini.model;

import com.gemini.ocs.model.Filter;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class filter extends Filter {
    @Id
    private int id;

    public filter(){
        super("","","",9999,0,0);
    }

    public filter(String make, String manufacturer, String model, int year, double size, double weight) {
        super(make, manufacturer, model, year, size, weight);
    }
}
