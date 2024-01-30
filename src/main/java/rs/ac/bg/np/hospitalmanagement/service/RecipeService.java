package rs.ac.bg.np.hospitalmanagement.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import rs.ac.bg.np.hospitalmanagement.domain.Medicine;
import rs.ac.bg.np.hospitalmanagement.domain.Recipe;
import rs.ac.bg.np.hospitalmanagement.domain.Report;
import rs.ac.bg.np.hospitalmanagement.repository.MedicineRepository;
import rs.ac.bg.np.hospitalmanagement.repository.RecipeRepository;
import rs.ac.bg.np.hospitalmanagement.repository.ReportRepository;

import java.util.List;
import java.util.Optional;

/**
 * Sadrzi poslovnu logiku sa radom sa receptom
 *
 * Klasa sluzi da manipulise, upravlja sa modelom i podacima vezanim sa receptom
 * Omogucava pravljenje recpta i vracanje svih recpata iz baze
 *
 *
 * @author Dusan
 */
@Service
public class RecipeService {

    /**
     * Broker baze podataka koji je posrednik ka tabeli Recept
     */
    @Autowired
    private RecipeRepository recipeRepository;

    /**
     * Broker baze podataka koji je posrednik ka tabeli Report
     */
    @Autowired
    private ReportRepository reportRepository;

    /**
     * Broker baze podataka koji je posrednik ka tabeli Medicine
     */
    @Autowired
    private MedicineRepository medicineRepository;


    /**
     * Perzistiranje recepta u sistem kroz trajno cuvanje
     *
     * @param repid id izjave za koji je recept vezan
     * @param mid id leka koji ce se prepisati u receptu
     * @param recipe recept koji se popunjava
     * @return perzistirani recept
     * @throws Exception ako u bazi ne postoji izjava ili lek sa prosledjenim id-jem
     */
    public Recipe createRecipe(long repid, long mid, Recipe recipe) throws Exception {

        Optional<Report> optionalReport = reportRepository.findById(repid);

        if(!optionalReport.isPresent()){
            throw new Exception("This report does not exist!!!");
        }

        Optional<Medicine> optionalMedicine = medicineRepository.findById(mid);

        if(!optionalMedicine.isPresent()){
            throw new Exception("This medicine does not exist!!!");
        }

        Report report = optionalReport.get();
        Medicine medicine = optionalMedicine.get();

        recipe.setMedicine(medicine);
        recipe.setReport(report);

        return recipeRepository.save(recipe);

    }

    /**
     * Ova metoda vraca listu recpata
     *
     * @return lista recepata iz baze
     */
    public List<Recipe> getAll() {
        return recipeRepository.findAll();
    }
}
