package rs.ac.bg.np.hospitalmanagement.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import rs.ac.bg.np.hospitalmanagement.domain.Hospital;
import rs.ac.bg.np.hospitalmanagement.service.HospitalService;

@RestController
@RequestMapping("/api/hospital")
public class HospitalController {

    @Autowired
    private HospitalService hospitalService;

    @PostMapping
    public Hospital createHospital(@RequestBody Hospital hospital) throws Exception {
        return hospitalService.createHospital(hospital);
    }

    @GetMapping("/{hid}")
    public Hospital getOneHospital(@PathVariable long hid) throws Exception {
        return hospitalService.getOneHospital(hid);
    }

}
