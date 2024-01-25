package rs.ac.bg.np.hospitalmanagement.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import rs.ac.bg.np.hospitalmanagement.domain.MedicalSpecial;
import rs.ac.bg.np.hospitalmanagement.domain.Medicine;
import rs.ac.bg.np.hospitalmanagement.repository.HospitalRepository;
import rs.ac.bg.np.hospitalmanagement.repository.MedicalSpecialRepository;
import rs.ac.bg.np.hospitalmanagement.repository.MedicineRepository;
import rs.ac.bg.np.hospitalmanagement.service.SeederService;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/seed")
public class SeedController {

    @Autowired
    private SeederService seederService;

    @PostMapping("/medspec")
    public void seedMedSpec(){
         seederService.initMS();
    }

    @PostMapping("/medicine")
    public void seedMedicine(){
        seederService.initMedicine();
    }

    @PostMapping("/diagnosis")
    public void seedDiagnosis(){
        seederService.seedDiagnosis();
    }


}
