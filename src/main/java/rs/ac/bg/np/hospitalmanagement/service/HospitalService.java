package rs.ac.bg.np.hospitalmanagement.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import rs.ac.bg.np.hospitalmanagement.domain.Hospital;
import rs.ac.bg.np.hospitalmanagement.repository.HospitalRepository;

import java.util.Optional;

@Service
public class HospitalService {

    @Autowired
    private HospitalRepository hospitalRepository;
    public Hospital createHospital(Hospital hospital) throws Exception {

        Optional<Hospital> optionalHospital = hospitalRepository.findByCityAndAddress(hospital.getCity(),hospital.getAddress());

        if(optionalHospital.isPresent()){
            throw new Exception("This hospital already exists!!!");
        }

        return hospitalRepository.save(hospital);

    }

    public Hospital getOneHospital(long hid) throws Exception {

        Optional<Hospital> optHospital = hospitalRepository.findById(hid);

        if(!optHospital.isPresent()){
            throw new Exception("There is no hospital with that ID!");
        }

        Hospital hospital = optHospital.get();

        return hospital;
    }
}
