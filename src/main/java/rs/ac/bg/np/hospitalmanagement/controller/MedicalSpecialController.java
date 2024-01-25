package rs.ac.bg.np.hospitalmanagement.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import rs.ac.bg.np.hospitalmanagement.domain.Doctor;
import rs.ac.bg.np.hospitalmanagement.domain.MedicalSpecial;
import rs.ac.bg.np.hospitalmanagement.service.MedicalServiceService;

import java.util.List;
import java.util.Set;

@RestController
@RequestMapping("/api/medspec")
public class MedicalSpecialController {

    @Autowired
    private MedicalServiceService medicalServiceService;


    @PostMapping("/{msid}/add/{hid}")
    public MedicalSpecial addMedSpecToHospital(@PathVariable long msid,@PathVariable long hid) throws Exception {
        return medicalServiceService.connect(msid,hid);
    }

    @GetMapping
    public List<MedicalSpecial> getAllSpecials(){
        return medicalServiceService.getAll();
    }


    @GetMapping("/{msid}/find/{did}")
    public Doctor specialisationForDoctor(@PathVariable long msid, @PathVariable long did) throws Exception {
        return medicalServiceService.findDoctor(msid,did);
    }

    @GetMapping("/{msid}/all")
    public Set<Doctor> getAllDoctors(@PathVariable long msid) throws Exception {
        return medicalServiceService.getAllDoctors(msid);
    }



}
