package com.gemini.model;

import com.gemini.ocs.model.Filter;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.util.concurrent.atomic.AtomicInteger;

@Entity
public class filter extends Filter {
    private static AtomicInteger count = new AtomicInteger(0);
    @Id
    private int id;

    public filter(){
        super("","","",9999,0,0);
    }

    public filter(String make, String manufacturer, String model
            , int year, double size, double weight) {
        super(make, manufacturer, model, year, size, weight);
        this.id = count.incrementAndGet();
    }
}
