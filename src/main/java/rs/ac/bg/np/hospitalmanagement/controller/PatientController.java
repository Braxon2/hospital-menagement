package rs.ac.bg.np.hospitalmanagement.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import rs.ac.bg.np.hospitalmanagement.domain.Patient;
import rs.ac.bg.np.hospitalmanagement.repository.PatientRepository;
import rs.ac.bg.np.hospitalmanagement.service.PatientService;

import java.util.List;

@RestController
@RequestMapping("/api/patients")
public class PatientController {

    @Autowired
    private PatientService patientService;

    @GetMapping
    public List<Patient> getAll(){
        return patientService.getAll();
    }

    @GetMapping("/{pid}")
    public Patient getPatient(@PathVariable long pid) throws Exception {
        return patientService.getPatient(pid);
    }


    @PostMapping
    public Patient createPatient(@RequestBody Patient patient) throws Exception {
        return patientService.createPatient(patient);
    }


}
