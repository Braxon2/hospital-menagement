package rs.ac.bg.np.hospitalmanagement.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import rs.ac.bg.np.hospitalmanagement.domain.Doctor;
import rs.ac.bg.np.hospitalmanagement.domain.Report;
import rs.ac.bg.np.hospitalmanagement.service.DoctorService;

import java.util.List;
import java.util.Set;

@RestController
@RequestMapping("/api/doctors")
public class DoctorController {

    @Autowired
    private DoctorService doctorService;

    @GetMapping
    public List<Doctor> getAllDoctors(){
        return doctorService.getAllDoctors();
    }

    @PostMapping
    public Doctor addNewDoctor(@RequestBody Doctor doctor) throws Exception {
        return doctorService.addNewDoctor(doctor);
    }

    @PostMapping("/{msid}/addDoc/{did}")
    public Doctor specialisationForDoctor(@PathVariable long msid, @PathVariable long did) throws Exception {
        return doctorService.insertSpecialisationForDoctor(msid,did);
    }



}
