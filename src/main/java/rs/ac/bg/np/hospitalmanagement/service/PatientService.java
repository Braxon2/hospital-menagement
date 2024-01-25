package rs.ac.bg.np.hospitalmanagement.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import rs.ac.bg.np.hospitalmanagement.domain.Patient;
import rs.ac.bg.np.hospitalmanagement.repository.PatientRepository;

import java.util.List;
import java.util.Optional;

@Service
public class PatientService {

    @Autowired
    private PatientRepository patientRepository;

    public List<Patient> getAll() {
        return patientRepository.findAll();
    }

    public Patient getPatient(long pid) throws Exception {

        Optional<Patient> optionalPatient = patientRepository.findById(pid);

        if(!optionalPatient.isPresent()){
            throw new Exception("This patient does not exist!!!");
        }

        return optionalPatient.get();

    }

    public Patient createPatient(Patient patient) throws Exception {

        Optional<Patient> optionalPatient = patientRepository.findByJmbg(patient.getJmbg());

        if(optionalPatient.isPresent()){
            throw new Exception("This patient already exists!!!");
        }

        return patientRepository.save(patient);

    }
}
