package rs.ac.bg.np.hospitalmanagement.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import rs.ac.bg.np.hospitalmanagement.domain.Medicine;
import rs.ac.bg.np.hospitalmanagement.repository.MedicineRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * Sadrzi poslovnu logiku sa radom sa lekom
 *
 * Klasa sluzi da manipulise, upravlja sa modelom i podacima vezanim sa lekom
 * Omogucava vracanje svih lekova, pravljenje novog leka i brisanje leka iz baze
 *
 *
 * @author Dusan
 */
@Service
public class MedicineService {

    /**
     * Broker baze podataka koji je posrednik ka tabeli Medicine
     */
    @Autowired
    private MedicineRepository medicineRepository;


    public void init(){

        Medicine medicine = new Medicine();
        medicine.setName("Brufen");
        medicine.setWeightPerPill(10);

        medicineRepository.save(medicine);
    }

    /**
     * Ova metoda vraca listu lekova iz baze
     *
     * @return listu lekova koja posotji u bazi
     */
    public List<Medicine> getAll() {
        return medicineRepository.findAll();
    }

    /**
     * Perzistiranje leka u sistem kroz trajno cuvanje
     *
     * @param medicine lek koji treba da se merzistira
     * @return perzistirani lek
     * @throws Exception ako taj lek vec postoji u bazi
     */
    public Medicine addNewMedication(Medicine medicine) throws Exception {

        Optional<Medicine> optMedication = medicineRepository.findByNameAndWeightPerPill(medicine.getName(),medicine.getWeightPerPill());

        if(optMedication.isPresent()){
            throw new Exception("This medicine already exist in database!!!");
        }
//        medicineRepository.save(medicine);
        return medicineRepository.save(medicine);

    }

    /**
     * Ova metoda brise lek iz baze sa prosledjenim idjem
     *
     * @param mid id leka koji se brise
     */
    public void deleteMedicine(long mid) {
         medicineRepository.deleteById(mid);
    }
}
