package com.gemini.model;

import com.gemini.ocs.model.DataProcRequirement;

import javax.persistence.*;
import java.util.concurrent.atomic.AtomicInteger;

@Entity
public class dataProc extends DataProcRequirement {
    private static AtomicInteger count = new AtomicInteger(0);
    @Id
    private Integer id;
    public dataProc(){
        super();
    }
}
