package rs.ac.bg.np.hospitalmanagement.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import rs.ac.bg.np.hospitalmanagement.domain.Doctor;
import rs.ac.bg.np.hospitalmanagement.domain.MedicalSpecial;
import rs.ac.bg.np.hospitalmanagement.repository.DoctorRepository;
import rs.ac.bg.np.hospitalmanagement.repository.MedicalSpecialRepository;

import java.util.List;
import java.util.Optional;

/**
 * Sadrzi poslovnu logiku sa radom sa doktorom
 *
 * Klasa sluzi da manipulise, upravlja sa modelom i podacima vezanim sa doktorom
 * Omogucava vracanje svih doktora, pravljenje novog doktora,
 * povezivanje specijalizacije tj kadra sa doktorom
 *
 * @author Dusan
 */
@Service
public class DoctorService {

    /**
     * Broker baze podataka koji je posrednik ka tabeli Docotor
     */
    @Autowired
    private DoctorRepository doctorRepository;

    /**
     * Broker baze podataka koji je posrednik ka tabeli MedicalSpecial
     */
    @Autowired
    private MedicalSpecialRepository medicalSpecialRepository;

    /**
     * Metoda vraca listu svih doktora
     * @return lista svih doktora
     */
    public List<Doctor> getAllDoctors() {
        return doctorRepository.findAll();
    }


    /**
     * Perzistiranje doktora u sistem kroz trajno cuvanje
     *
     * Novi doktor se cuva u bazi podataka
     *
     * @param doctor Doktor koji se treba perzisitirati
     * @return Perzistirani doktor
     * @throws Exception ako doktor sa tim podacima vec postoji u bazi
     */
    public Doctor addNewDoctor(Doctor doctor) throws Exception {

        Optional<Doctor> optionalDoctor = doctorRepository.findByLicenceNumber(doctor.getLicenceNumber());

        if (optionalDoctor.isPresent()) {
            throw new Exception("Doctor with this licence already exists!!!");
        }

//        Doctor doc = optionalDoctor.get();
        doctorRepository.save(doctor);
        return doctor;

    }

    /**
     * Ova metoda vraca doktora sa specijalizacijom kojom smo ga povezali
     *
     * @param msid id kadra tj medicinske specijalizacije sa kojim ce se doktor povezati
     * @param did Id doktora
     * @return doktora sa kadrom
     * @throws Exception ako Doktor ili kadar tj medicalSpecial ne posotoje u bazi
     */
    public Doctor insertSpecialisationForDoctor(long msid, long did) throws Exception {

        Optional<MedicalSpecial> optionalMedicalSpecial = medicalSpecialRepository.findById(msid);

        Optional<Doctor> optionalDoctor = doctorRepository.findById(did);

        if (!optionalMedicalSpecial.isPresent()) {
            throw new Exception("This medical specialisation does not exist!!!");
        }

        if (!optionalDoctor.isPresent()) {
            throw new Exception("This doctor does not exist!!!");
        }

        MedicalSpecial medSpecial = optionalMedicalSpecial.get();
        Doctor doctor = optionalDoctor.get();
        doctor.setMedicalSpecial(medSpecial);
        doctorRepository.save(doctor);

        medSpecial.getMembers().add(doctor);
        return doctor;

    }


}
