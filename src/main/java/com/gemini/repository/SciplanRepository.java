package com.gemini.repository;

import com.gemini.model.Employee;
import com.gemini.model.SciencePlan;
import org.springframework.data.repository.CrudRepository;

import java.util.ArrayList;

public interface SciplanRepository extends
        CrudRepository<SciencePlan, Integer> {
    SciencePlan findByPlanNo(int planNo);
    ArrayList<SciencePlan> findByValidated(Boolean validated);
}
