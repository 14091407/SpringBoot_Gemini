package com.gemini.model;

import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;

public class AddSciplanForm {
    String creator;
    String submiter;
    double fundingInUSD;
    String objectives;
    String starSystem;
    String startDate;
    String endDate;
    String TELESCOPELOC;
    ArrayList<String> dataProcRequirements;
    ArrayList<String> observingProgram;
    String status;

    public String getCreator() {
        return creator;
    }

    public String getSubmiter() {
        return submiter;
    }

    public double getFundingInUSD() {
        return fundingInUSD;
    }

    public String getObjectives() {
        return objectives;
    }

    public String getStarSystem() {
        return starSystem;
    }

    public String getStartDate() {
        return startDate;
    }

    public String getEndDate() {
        return endDate;
    }

    public String getTELESCOPELOC() {
        return TELESCOPELOC;
    }

    public ArrayList<String> getDataProcRequirements() {
        return dataProcRequirements;
    }

    public ArrayList<String> getObservingProgram() {
        return observingProgram;
    }

    public String getStatus() {
        return status;
    }
}
