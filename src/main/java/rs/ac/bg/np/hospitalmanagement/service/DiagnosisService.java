package rs.ac.bg.np.hospitalmanagement.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import rs.ac.bg.np.hospitalmanagement.domain.Diagnosis;
import rs.ac.bg.np.hospitalmanagement.repository.DiagnosisRepository;

import java.util.List;
import java.util.Optional;

@Service
public class DiagnosisService {

    @Autowired
    private DiagnosisRepository diagnosisRepository;

    public List<Diagnosis> getAll() {
        return diagnosisRepository.findAll();
    }

    public Diagnosis getDiagnosis(long diagid) throws Exception {
        Optional<Diagnosis> optionalDiagnosis = diagnosisRepository.findById(diagid);


        if(!optionalDiagnosis.isPresent()){
            throw new Exception("This diagnosis does not exist!!!");
        }

        return optionalDiagnosis.get();


    }
}
