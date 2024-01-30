package rs.ac.bg.np.hospitalmanagement.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import rs.ac.bg.np.hospitalmanagement.domain.Diagnosis;
import rs.ac.bg.np.hospitalmanagement.repository.DiagnosisRepository;

import java.util.List;
import java.util.Optional;

/**
 * Sadrzi poslovnu logiku sa radom sa dijagnozom
 *
 * Klasa sluzi da manipulise, upravlja sa modelom i podacima vezanim sa dijagnozom
 * Omogucava vracanje svih dijagnoza, pojedincanu dijagnozu
 *
 * @author Dusan
 */
@Service
public class DiagnosisService {

    /**
     * Broker baze podataka koji je posrednik ka tabeli Diagnosis
     */
    @Autowired
    private DiagnosisRepository diagnosisRepository;

    /**
     * Metoda vraca listu svih dijagnoza
     * @return lista svih dijagnoza
     */
    public List<Diagnosis> getAll() {
        return diagnosisRepository.findAll();
    }

    /**
     * Metoda koja vraca dijagnozu sa prosledjenim id
     * @param diagid
     * @return Dijagnozu ciji atribut id je jednak prosledjenom podatku
     * @throws Exception ukoliko ne postoji dijagnoza sa prosledjenim Id-jem
     */
    public Diagnosis getDiagnosis(long diagid) throws Exception {
        Optional<Diagnosis> optionalDiagnosis = diagnosisRepository.findById(diagid);


        if(!optionalDiagnosis.isPresent()){
            throw new Exception("This diagnosis does not exist!!!");
        }

        return optionalDiagnosis.get();


    }
}
