package rs.ac.bg.np.hospitalmanagement.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import rs.ac.bg.np.hospitalmanagement.domain.Diagnosis;
import rs.ac.bg.np.hospitalmanagement.service.DiagnosisService;

import java.util.List;

@RestController
@RequestMapping("/api/diagnosis")
public class DiagnosisController {

    @Autowired
    private DiagnosisService diagnosisService;

    @GetMapping
    public List<Diagnosis> getAllDiagnosis(){
        return diagnosisService.getAll();
    }

    @GetMapping("/{diagid}")
    public Diagnosis getAllDiagnosis(@PathVariable long diagid) throws Exception {
        return diagnosisService.getDiagnosis(diagid);
    }



}
