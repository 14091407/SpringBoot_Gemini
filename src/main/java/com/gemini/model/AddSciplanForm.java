package com.gemini.model;

import com.gemini.ocs.model.BaseObservingProgram;
import com.gemini.ocs.model.DataProcRequirement;
import org.springframework.web.bind.annotation.RequestParam;

import javax.xml.bind.annotation.XmlRootElement;
import java.util.ArrayList;
import java.util.Date;

@XmlRootElement
public class AddSciplanForm {

    Boolean validated;
    double fundingInUSD;
    String creator;
    String objectives;
    String starSystem;
    String startDate;
    String endDate;
    String TELESCOPELOC;
    dataProc dataProcRequirements;
    ObservingProgram observingProgram;
    String status;
    
    public AddSciplanForm(){}
    
    public AddSciplanForm(String creator, double fundingInUSD
            , String objectives, String starSystem
            , String startDate, String endDate
            , String telescopeLocation, dataProc dataProcRequirements
            , ObservingProgram observingProgram, String status){
        this.creator = creator;
        this.fundingInUSD = fundingInUSD;
        this.objectives = objectives;
        this.starSystem = starSystem;
        this.startDate = startDate;
        this.endDate = endDate;
        this.TELESCOPELOC = telescopeLocation;
        this.dataProcRequirements = dataProcRequirements;
        this.observingProgram = observingProgram;
        this.status = status;
        this.validated = Boolean.FALSE;
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

    public dataProc getDataProcRequirements() {
        return dataProcRequirements;
    }

    public ObservingProgram getObservingProgram() {
        return observingProgram;
    }

    public String getStatus() {
        return status;
    }
}
