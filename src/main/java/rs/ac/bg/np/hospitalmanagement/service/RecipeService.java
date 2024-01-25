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

@Service
public class RecipeService {

    @Autowired
    private RecipeRepository recipeRepository;

    @Autowired
    private ReportRepository reportRepository;

    @Autowired
    private MedicineRepository medicineRepository;


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

    public List<Recipe> getAll() {
        return recipeRepository.findAll();
    }
}
