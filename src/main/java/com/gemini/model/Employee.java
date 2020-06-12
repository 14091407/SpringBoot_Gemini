package com.gemini.model;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

@SuppressWarnings("ALL")
@Entity
public class Employee {
    private static AtomicInteger count = new AtomicInteger(0);
    private String username;
    private String password;
    private String firstname;
    private String lastname;

    @Id
    private Integer id;


//    @OneToMany(mappedBy = "planNo",cascade = CascadeType.ALL)
//    private List<SciencePlan> sciencePlans;

    public Employee(String username, String password, String firstname
            , String lastname){
        this.id = count.incrementAndGet();
        this.username = username;
        this.password = password;
        this.firstname = firstname;
        this.lastname = lastname;
    }
    public Employee(){}

//    public List<SciencePlan> getSciencePlans(){
//        return sciencePlans;
//    }

    public String getPassword() {
        return password;
    }

    public String getUsername() {
        return username;
    }

//    public boolean addSciencePlans(int planNo, String creator
//            , String submitter, double fundingInUSD
//            , String objectives, String starSystem
//            , Date startDate, Date endDate
//            , String telescopeLocation, ArrayList<String> dataProcRequirements
//            , ArrayList<String> observingProgram, String status){
//        sciencePlans.add(new SciencePlan(planNo,creator,submitter,fundingInUSD
//                ,objectives,starSystem,startDate,endDate,telescopeLocation
//                ,dataProcRequirements,observingProgram,status));
//        return true;
//    }
}
