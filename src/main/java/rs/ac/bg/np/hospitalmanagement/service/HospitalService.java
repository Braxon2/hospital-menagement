package rs.ac.bg.np.hospitalmanagement.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import rs.ac.bg.np.hospitalmanagement.domain.Hospital;
import rs.ac.bg.np.hospitalmanagement.repository.HospitalRepository;

import java.util.Optional;

/**
 * Sadrzi poslovnu logiku sa radom sa doktorom
 *
 * Klasa sluzi da manipulise, upravlja sa modelom i podacima vezanim sa bolnicom
 * Omogucava pravljenje nove bolnice i vracanje bolnice sa posebnim id
 *
 * @author Dusan
 */
@Service
public class HospitalService {

    /**
     * Broker baze podataka koji je posrednik ka tabeli Hospital
     */
    @Autowired
    private HospitalRepository hospitalRepository;

    /**
     * Perzistiranje bolnice u sistem kroz trajno cuvanje
     *
     * @param hospital Bolnica koja se treba perzestirati
     * @return perzistirana bolnica
     * @throws Exception ako ta bolnica posotji u bazi sa tim podacima
     */
    public Hospital createHospital(Hospital hospital) throws Exception {

        Optional<Hospital> optionalHospital = hospitalRepository.findByCityAndAddress(hospital.getCity(),hospital.getAddress());

        if(optionalHospital.isPresent()){
            throw new Exception("This hospital already exists!!!");
        }

        return hospitalRepository.save(hospital);

    }

    /**
     *
     * Ova metoda nam vraca bolnicu sa odredjenim Id-jem
     *
     * @param hid id bolnice koju trazimo
     * @return bolnicu sa prosledjenim id-jem
     * @throws Exception ako ta bolnica ne posotji u bazi
     */
    public Hospital getOneHospital(long hid) throws Exception {

        Optional<Hospital> optHospital = hospitalRepository.findById(hid);

        if(!optHospital.isPresent()){
            throw new Exception("There is no hospital with that ID!");
        }

        Hospital hospital = optHospital.get();

        return hospital;
    }
}
