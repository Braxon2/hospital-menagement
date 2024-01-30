package rs.ac.bg.np.hospitalmanagement.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import rs.ac.bg.np.hospitalmanagement.domain.Doctor;
import rs.ac.bg.np.hospitalmanagement.domain.Hospital;
import rs.ac.bg.np.hospitalmanagement.domain.MedicalSpecial;
import rs.ac.bg.np.hospitalmanagement.repository.HospitalRepository;
import rs.ac.bg.np.hospitalmanagement.repository.MedicalSpecialRepository;

import java.util.*;

/**
 * Sadrzi poslovnu logiku sa radom sa medicinskom specijalizacijom tj kadrom
 *
 * Klasa sluzi da manipulise, upravlja sa modelom i podacima vezanim sa medicinskom specijalizacijom tj kadrom
 * Omogucava povezivanje kadra sa bolnicom, vracanje svih kadrova kao i doktora iz odredjenog kadra i nalazenje doktora iz odredjenog kadra
 *
 * @author Dusan
 */
@Service
public class MedicalServiceService {

    /**
     * Broker baze podataka koji je posrednik ka tabeli MedicalSpecial
     */
    @Autowired
    private MedicalSpecialRepository medicalSpecialRepository;

    /**
     * Broker baze podataka koji je posrednik ka tabeli Hospital
     */
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

    /**
     *Ova metoda sluzi da poveze medicisku specijalizaciju za bolnicu
     *
     * @param msid id mediciske specijalizacije koju hocemo da povezemo za bolnicu
     * @param hid id bolnice
     * @return Medicinsku specijalizaciju koji je povezan sa bolnicom
     * @throws Exception ako ID bolnice ili kadra nije validan tj da ne postoje u bazi
     */
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


    /**
     *Ova metoda vraca sve medicinske specijaliste
     *
     * @return listu medicinskih specijalista iz baze
     */
    public List<MedicalSpecial> getAll() {
        return medicalSpecialRepository.findAll();
    }

    /**
     *Ova metoda nam vraca sve doktore iz odredjenog kadra
     *
     * @param msid id kadra tj mediciske spicjalnosti
     * @return vraca nam skup doktora koji pripadaju tom kadru
     * @throws Exception ukoliko taj kadar ne postoji u bazi
     */
    public Set<Doctor> getAllDoctors(long msid) throws Exception {
        Optional<MedicalSpecial> optMedSpec = medicalSpecialRepository.findById(msid);

        if(!optMedSpec.isPresent()){
            throw new Exception("Id of this medical special does not exist!!!");
        }

        MedicalSpecial ms = optMedSpec.get();
        return ms.getMembers();


    }

    /**
     * Ova metoda nam vraca lekara iz odredjenog kadra
     *
     * @param msid id kadra u kojem se nalazi lekar
     * @param did id lekara kojeg trazimo
     * @return  lekara koji pripada tom kadru  ili null ukoliko ne posotji lekar u tom kadru
     * @throws Exception ako kadar sa prosledjenim id-jem ne posotji u bazi
     */
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
