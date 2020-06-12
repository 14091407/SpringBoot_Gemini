package com.gemini.model;

import com.fasterxml.jackson.annotation.JsonProperty;
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
    dataProc dataProcRequirement;
    ObservingProgram observingProgram;
    String status;

    public void setValidated(Boolean validated) {
        this.validated = validated;
    }

    public void setFundingInUSD(double fundingInUSD) {
        this.fundingInUSD = fundingInUSD;
    }

    public void setCreator(String creator) {
        this.creator = creator;
    }

    public void setObjectives(String objectives) {
        this.objectives = objectives;
    }

    public void setStarSystem(String starSystem) {
        this.starSystem = starSystem;
    }

    public void setStartDate(String startDate) {
        this.startDate = startDate;
    }

    public void setEndDate(String endDate) {
        this.endDate = endDate;
    }

    public void setTELESCOPELOC(String TELESCOPELOC) {
        this.TELESCOPELOC = TELESCOPELOC;
    }

    public void setDataProcRequirements(dataProc dataProcRequirement) {
        this.dataProcRequirement = dataProcRequirement;
    }

    public void setObservingProgram(ObservingProgram observingProgram) {
        this.observingProgram = observingProgram;
    }

    public void setStatus(String status) {
        this.status = status;
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
        return dataProcRequirement;
    }

    public ObservingProgram getObservingProgram() {
        return observingProgram;
    }

    public String getStatus() {
        return status;
    }
}
