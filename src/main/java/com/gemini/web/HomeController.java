package com.gemini.web;

import com.gemini.model.AddSciplanForm;
import com.gemini.model.Employee;
import com.gemini.model.LoginForm;
import com.gemini.model.SciencePlan;
import com.gemini.repository.EmployeeRepository;
import com.gemini.repository.SciplanRepository;
import io.jsonwebtoken.SignatureAlgorithm;
import jparsec.ephem.Target;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import io.jsonwebtoken.Jwts;
import org.json.*;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

@CrossOrigin(origins = "http://localhost:3000", maxAge = 3600)
@RestController
public class HomeController {
    @Autowired
    private EmployeeRepository employeeRepository;
    @Autowired
    private SciplanRepository sciplanRepository;

    @GetMapping("/")
    public @ResponseBody String root() {
        return "Welcome welcome";
    }

    @PostMapping(path = "/api/login",consumes = "application/json", produces = "application/json")
    public ResponseEntity<Object> login(@RequestBody LoginForm loginForm){
        String username = loginForm.getUsername();
        String password = loginForm.getPassword();
//        test user
        Employee user = new Employee('0', "naipawat","password","Naipawat","Poolsawat");
        employeeRepository.save(user);
        if(employeeRepository.findByUsername(username) != null)
        {
            Employee employee = employeeRepository.findByUsername(username);
                if (employee.getUsername().equals(username) && employee.getPassword().equals(password))
                {
                    JSONObject jsonString =  new JSONObject();
                    jsonString.put("token",Jwts.builder().setSubject(employee.getUsername()).claim("roles", "user").setIssuedAt(new Date())
                            .signWith(SignatureAlgorithm.HS256, "secretkey").compact());
                    return new ResponseEntity<>(jsonString, HttpStatus.OK);
                }
        }
        return new ResponseEntity<>("User not found",HttpStatus.OK);
    }

    @PostMapping(path="/api/addsciplan",consumes = "application/json", produces = "application/json")
    public ResponseEntity<String> addSciplan(@RequestHeader(name = "X-COM-PERSIST") String headerPersist
                                            ,@RequestBody AddSciplanForm addSciplanForm) throws ParseException {
        JSONObject tokenJSON = new JSONObject(headerPersist);
        String creator = addSciplanForm.getCreator();
        String startDate = addSciplanForm.getStartDate();
        String endDate = addSciplanForm.getEndDate();
        String submiter = addSciplanForm.getSubmiter();
        Double fundingInUSD = addSciplanForm.getFundingInUSD();
        String objectives = addSciplanForm.getObjectives();
        String starSystem = addSciplanForm.getStarSystem();
        String TELESCOPELOC = addSciplanForm.getTELESCOPELOC();
        ArrayList<String> dataProcRequirements = addSciplanForm.getDataProcRequirements();
        ArrayList<String> observingProgram = addSciplanForm.getObservingProgram();
        String status = addSciplanForm.getStatus();
        String token = tokenJSON.get("token").toString();
        //        Token checking
        String[] parts = token.split("\\.");
        String decoded1 = new String(Base64.getUrlDecoder().decode(parts[1]));
        JSONObject test = new JSONObject(decoded1);
        if(!test.get("sub").toString().equals(creator)){
            return new ResponseEntity<>("Please type your username as creator", HttpStatus.OK);
        }
//        Date formatting
        Date StartDate = new SimpleDateFormat("dd/MM/yyyy").parse(startDate);
        Date EndDate = new SimpleDateFormat("dd/MM/yyyy").parse(endDate);
        sciplanRepository.save(new SciencePlan(creator,submiter,fundingInUSD,objectives,starSystem,StartDate,EndDate,TELESCOPELOC,dataProcRequirements,observingProgram,status));
        return new ResponseEntity<>("SciPlan Added",HttpStatus.OK);
    }

    @GetMapping("/api/getsciplan")
    public @ResponseBody
    Iterable<SciencePlan> getSciplan(){
        return sciplanRepository.findAll();
    }

    @PostMapping("/api/validate")
    public ResponseEntity<String> validateSciplan(int PlanNo,boolean humanValidation){
        SciencePlan sciencePlan = sciplanRepository.findByPlanNo(PlanNo);
//        Check starSystem Enum from Target jparsec.ephem
        String[] AvailableNames = Target.getNames();
        List<String> availableNames = Arrays.asList(AvailableNames);
        availableNames.replaceAll(String::toLowerCase);
        String starSystem = sciencePlan.getStarSystem();
        if(!availableNames.contains(starSystem)){
            return new ResponseEntity<>(starSystem+"is an invalid starSystem",HttpStatus.OK);
        }
        //TODO:TELESCOPELOC Enum checking
        //TODO:DataProcRequirements fileType and COLOR_TYPE Enum checking
        //TODO:observingProgram : Try create observingProgram objects by the input received
        //TODO:STATUS Enum checking
        if(humanValidation){
            sciencePlan.setValidated(true);
            sciplanRepository.save(sciencePlan);
            return new ResponseEntity<>("Valid!",HttpStatus.OK);
        }
        return new ResponseEntity<>("Invalid.. Reason Unknown",HttpStatus.OK);
    }

    @DeleteMapping("/api/delete/{id}")
    public ResponseEntity<String> removeSciPlanById(@PathVariable("id") int id) {
        // delete a specific hero
        sciplanRepository.deleteById(id);
        return new ResponseEntity<>("Sciplan: "+id+"is deleted",HttpStatus.OK);
    }
}