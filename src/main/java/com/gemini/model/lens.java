package com.gemini.model;

import com.gemini.ocs.model.Lens;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.util.concurrent.atomic.AtomicInteger;

@Entity
@DynamicUpdate
public class lens extends Lens {
    private static AtomicInteger count = new AtomicInteger(0);
    @Id
    private Integer id;

    public lens(){
        super("testmake","testmodel","testmanufac",9999);
    }
    public lens(String make,String model,String manufacturer,int year){
        super(make,model,manufacturer,year);
        this.id = count.incrementAndGet();
    }

}
