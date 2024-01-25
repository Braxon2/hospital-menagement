package rs.ac.bg.np.hospitalmanagement.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import rs.ac.bg.np.hospitalmanagement.domain.Diagnosis;
import rs.ac.bg.np.hospitalmanagement.domain.MedicalSpecial;
import rs.ac.bg.np.hospitalmanagement.domain.Medicine;
import rs.ac.bg.np.hospitalmanagement.repository.DiagnosisRepository;
import rs.ac.bg.np.hospitalmanagement.repository.HospitalRepository;
import rs.ac.bg.np.hospitalmanagement.repository.MedicalSpecialRepository;
import rs.ac.bg.np.hospitalmanagement.repository.MedicineRepository;

import java.util.ArrayList;
import java.util.List;

@Service
public class SeederService {

    @Autowired
    private MedicalSpecialRepository medicalSpecialRepository;

    @Autowired
    private HospitalRepository hospitalRepository;

    @Autowired
    private MedicineRepository medicineRepository;

    @Autowired
    private DiagnosisRepository diagnosisRepository;

    public void initMS() {

        List<MedicalSpecial> specials = new ArrayList<>();
        specials.add(new MedicalSpecial("ORL"));
        specials.add(new MedicalSpecial("Stomatologija"));
        specials.add(new MedicalSpecial("Oftamologija"));
        specials.add(new MedicalSpecial("Dermatologija"));

        medicalSpecialRepository.saveAll(specials);

    }




    public void initMedicine(){

        Medicine medicine = new Medicine();
        medicine.setName("Brufen");
        medicine.setWeightPerPill(10);

        medicineRepository.save(medicine);
    }

    public void seedDiagnosis() {
        List<Diagnosis> diagnosis = new ArrayList<>();
        //oftamologija
        diagnosis.add(new Diagnosis("Kataratkta",3350,"H0701"));
        diagnosis.add(new Diagnosis("Blepharitis",3351,"H0702"));
        diagnosis.add(new Diagnosis("Deformatio orbitae",3352,"H0703"));

        //orl
        diagnosis.add(new Diagnosis("Tinitus",4200,"B0100"));
        diagnosis.add(new Diagnosis("Laringitis",4201,"B0101"));
        diagnosisRepository.saveAll(diagnosis);
    }
}
