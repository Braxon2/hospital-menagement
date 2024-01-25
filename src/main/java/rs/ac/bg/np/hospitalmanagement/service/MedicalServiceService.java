package rs.ac.bg.np.hospitalmanagement.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import rs.ac.bg.np.hospitalmanagement.domain.Doctor;
import rs.ac.bg.np.hospitalmanagement.domain.Hospital;
import rs.ac.bg.np.hospitalmanagement.domain.MedicalSpecial;
import rs.ac.bg.np.hospitalmanagement.repository.HospitalRepository;
import rs.ac.bg.np.hospitalmanagement.repository.MedicalSpecialRepository;

import java.util.*;

@Service
public class MedicalServiceService {


    @Autowired
    private MedicalSpecialRepository medicalSpecialRepository;

    @Autowired
    private HospitalRepository hospitalRepository;



    public void init() {

        List<MedicalSpecial> specials = new ArrayList<>();
        specials.add(new MedicalSpecial("ORL"));
        specials.add(new MedicalSpecial("Stomatologija"));
        specials.add(new MedicalSpecial("Oftamologija"));
        specials.add(new MedicalSpecial("Dermatologija"));

        medicalSpecialRepository.saveAll(specials);

    }

    public MedicalSpecial connect(long msid, long hid) throws Exception {

        Optional<Hospital> optHos = hospitalRepository.findById(hid);

        Optional<MedicalSpecial> optionalMedicalSpecial = medicalSpecialRepository.findById(msid);

        if(!optHos.isPresent()){
            throw new Exception("ID of the hospital is INVALID!!!");
        }

        if(!optionalMedicalSpecial.isPresent()){
            throw new Exception("ID of the Medical Special is INVALID!!!");
        }

        Hospital h = optHos.get();
        MedicalSpecial ms = optionalMedicalSpecial.get();
        Set<MedicalSpecial> spec = new HashSet<>();
        if(h.getMedicalSpecials() == null){
            spec.add(ms);
            h.setMedicalSpecials(spec);
        }else{
            h.getMedicalSpecials().add(ms);
        }

        h.getMedicalSpecials().add(ms);
        hospitalRepository.save(h);
        return ms;
    }


    public List<MedicalSpecial> getAll() {
        return medicalSpecialRepository.findAll();
    }

    public Set<Doctor> getAllDoctors(long msid) throws Exception {
        Optional<MedicalSpecial> optMedSpec = medicalSpecialRepository.findById(msid);

        if(!optMedSpec.isPresent()){
            throw new Exception("Id of this medical special does not exist!!!");
        }

        MedicalSpecial ms = optMedSpec.get();
        return ms.getMembers();


    }





    public Doctor findDoctor(long msid, long did) throws Exception {

        Optional<MedicalSpecial> optionalMedicalSpecial = medicalSpecialRepository.findById(msid);

//        Optional<Doctor> optionalDoctor = doctorRepository.findById(did);

        if (!optionalMedicalSpecial.isPresent()) {
            throw new Exception("This medical specialisation does not exist!!!");
        }

        MedicalSpecial ms = optionalMedicalSpecial.get();
        Set<Doctor> doctors = ms.getMembers();

        for (Doctor d:doctors) {
            if(d.getDocId() == did) return d;
        }


//        if (!optionalDoctor.isPresent()) {
//            throw new Exception("This doctor does not exist!!!");
//        }

        return null;

    }
}
