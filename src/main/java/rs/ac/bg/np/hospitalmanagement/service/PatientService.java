package rs.ac.bg.np.hospitalmanagement.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import rs.ac.bg.np.hospitalmanagement.domain.Patient;
import rs.ac.bg.np.hospitalmanagement.repository.PatientRepository;

import java.util.List;
import java.util.Optional;

/**
 * Sadrzi poslovnu logiku sa radom sa pacijentom
 *
 * Klasa sluzi da manipulise, upravlja sa modelom i podacima vezanim sa pacijentom
 * Omogucava vracanje svih pacijenata kao i pojedinacnog pacijenta i pravljenje novog pacijenta
 *
 *
 * @author Dusan
 */
@Service
public class PatientService {

    /**
     * Broker baze podataka koji je posrednik ka tabeli Patient
     */
    @Autowired
    private PatientRepository patientRepository;

    /**
     *Ova metoda vraca listu pacijenata
     *
     * @return lista pacijenata iz baze
     */
    public List<Patient> getAll() {
        return patientRepository.findAll();
    }

    /**
     *Ova metoda vraca pacijenta sa odredjenim id-jem iz baze
     *
     * @param pid id pacijenta kojeg trazimo
     * @return pacijenta iz baze sa prosledjenim id-jem
     * @throws Exception ako taj pacijent sa tim id-jem ne posotji u bazi
     */
    public Patient getPatient(long pid) throws Exception {

        Optional<Patient> optionalPatient = patientRepository.findById(pid);

        if(!optionalPatient.isPresent()){
            throw new Exception("This patient does not exist!!!");
        }

        return optionalPatient.get();

    }

    /**
     * Perzistiranje pacijenta u sistem kroz trajno cuvanje
     *
     * @param patient pacijent koji treba da se perzistira
     * @return perzistirani pacijent
     * @throws Exception ako taj pacijent sa tim podacima vec posotji
     */
    public Patient createPatient(Patient patient) throws Exception {

        Optional<Patient> optionalPatient = patientRepository.findByJmbg(patient.getJmbg());

        if(optionalPatient.isPresent()){
            throw new Exception("This patient already exists!!!");
        }

        return patientRepository.save(patient);

    }
}
