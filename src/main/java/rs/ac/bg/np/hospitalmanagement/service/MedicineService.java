package rs.ac.bg.np.hospitalmanagement.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import rs.ac.bg.np.hospitalmanagement.domain.Medicine;
import rs.ac.bg.np.hospitalmanagement.repository.MedicineRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class MedicineService {

    @Autowired
    private MedicineRepository medicineRepository;


    public void init(){

        Medicine medicine = new Medicine();
        medicine.setName("Brufen");
        medicine.setWeightPerPill(10);

        medicineRepository.save(medicine);
    }

    public List<Medicine> getAll() {
        return medicineRepository.findAll();
    }

    public Medicine addNewMedication(Medicine medicine) throws Exception {

        Optional<Medicine> optMedication = medicineRepository.findByNameAndWeightPerPill(medicine.getName(),medicine.getWeightPerPill());

        if(optMedication.isPresent()){
            throw new Exception("This medicine already exist in database!!!");
        }
//        medicineRepository.save(medicine);
        return medicineRepository.save(medicine);

    }

    public void deleteMedicine(long mid) {
         medicineRepository.deleteById(mid);
    }
}
