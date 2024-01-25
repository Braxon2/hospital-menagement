package rs.ac.bg.np.hospitalmanagement.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import rs.ac.bg.np.hospitalmanagement.domain.Medicine;
import rs.ac.bg.np.hospitalmanagement.service.MedicineService;

import java.util.List;

@RestController
@RequestMapping("/api/medicine")
public class MedicineController {

    @Autowired
    private MedicineService medicineService;

    @GetMapping
    public List<Medicine> getAll(){
        return medicineService.getAll();
    }

    @PostMapping
    public Medicine addNewMedication(@RequestBody Medicine medicine) throws Exception {
        return medicineService.addNewMedication(medicine);
    }


    @DeleteMapping("/{mid}")
    public void deleteMedicine(@PathVariable long mid){
         medicineService.deleteMedicine(mid);
    }

}
