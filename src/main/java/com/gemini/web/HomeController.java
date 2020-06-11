package com.gemini.web;

import com.gemini.model.Employee;
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
    @CrossOrigin
    @GetMapping("/")
    public @ResponseBody String root() {
        return "Welcome welcome";
    }


//    @CrossOrigin(origins = "http://localhost:3000", maxAge = 3600)
    @PostMapping("/api/login")
    public ResponseEntity<Object> login(@RequestParam String username,
                                 @RequestParam String password){
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

    @CrossOrigin
    @PostMapping("/api/addsciplan")
    public ResponseEntity<String> addSciplan(@RequestParam int planNo,
                                             @RequestParam String creator,
                                             @RequestParam String submiter,
                                             @RequestParam double fundingInUSD,
                                             @RequestParam String objectives,
                                             @RequestParam String starSystem,
                                             @RequestParam String startDate,
                                             @RequestParam String endDate,
                                             @RequestParam String TELESCOPELOC,
                                             //TELESCOPELOC = {String TELESCOPELOC :HAWAII or CHILE}
                                             @RequestParam ArrayList<String> dataProcRequirements,
                                             //dataProcRequirement =
                                             //{string 'fileType':'RAW'or'PNG'or'JPEG'or'TIFF'
                                             //,double 'fileQuality'
                                             //,string 'COLOR_TYPE':'BW'or'COLOR'
                                             //,double 'contrast'
                                             //,double 'brightness'
                                             //,double 'saturation'}
                                             @RequestParam ArrayList<String> observingProgram,
                                             //observingProgram =
                                             //{int 'id'
                                             //LocationElement : {double longitude,double latitude,double radius}
                                             //Lens : {String make,String model, String manufacturer, int year}
                                             //ArrayList<Filter> for each Filter {String make,String model,String manufacturer,int year,double size,double weight}}
                                             //ArrayList<Double>
                                             //boolean isLightDetectorOn
                                             //ArrayList<SpecialEquipment> for each SpecialEquipment {String equipmentName,String ownerName,Date installedDate}
                                             @RequestParam String status,
                                             //status = {String 'status': COMPLETE or RUNNING or SUMMITTED}

                                            @RequestParam String token) throws ParseException {
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

    @CrossOrigin
    @GetMapping("/api/getsciplan")
    public @ResponseBody
    Iterable<SciencePlan> getSciplan(){
        return sciplanRepository.findAll();
    }

    @CrossOrigin
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

    @CrossOrigin
    @DeleteMapping("/api/delete/{id}")
    public ResponseEntity<String> removeSciPlanById(@PathVariable("id") int id) {
        // delete a specific hero
        sciplanRepository.deleteById(id);
        return new ResponseEntity<>("Sciplan: "+id+"is deleted",HttpStatus.OK);
    }
}