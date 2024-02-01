package rs.ac.bg.np.hospitalmanagement.service;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import rs.ac.bg.np.hospitalmanagement.domain.*;
import rs.ac.bg.np.hospitalmanagement.repository.MedicineRepository;
import rs.ac.bg.np.hospitalmanagement.repository.RecipeRepository;
import rs.ac.bg.np.hospitalmanagement.repository.ReportRepository;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
class RecipeServiceTest {

    @Mock
    private RecipeRepository recipeRepository;

    @Mock
    private ReportRepository reportRepository;

    @Mock
    private MedicineRepository medicineRepository;

    @InjectMocks
    private RecipeService recipeService;

    @InjectMocks
    private ReportService reportService;

    @InjectMocks
    private MedicineService medicineService;

    @Test
    void createRecipe() throws Exception {

        Medicine medicine1 = new Medicine(102L,"Brufen",10);

        Patient p1 = new Patient(1L,"Slavko Petronijevic",new Date(),"1234567891011","Belgrade",null);
        Patient p2 = new Patient(2L,"Uros Zdravkovic",new Date(),"4912352678931","Belgrade",null);

        Diagnosis d1 = new Diagnosis("Katarakta",3350,"H0701");
        d1.setDiaId(1L);
        Diagnosis d2 = new Diagnosis("Laringitis",4201,"B0101");
        d2.setDiaId(5L);

        MedicalSpecial medicalSpecial2 = new MedicalSpecial(153L,"Stomatologija",null);

        Doctor dr1 = new Doctor(1L,"Oliver Dragojevic","123456",medicalSpecial2,null);

//        Report(long repid, String description, Patient patient, Diagnosis diagnosis, Doctor doctor, String clinicalFinding)
        Report report1 = new Report(1L,"Slabiji vid i zamucenje vida",p1,d1,dr1,"Testovi preko aparata za gledanje sociva i utvrdio je dijagnozu kataratke");



        Recipe recipe = new Recipe(6L,"2 puta dnevno, 7 dana",medicine1,report1);

//        Mockito.when(recipeRepository.findById(recipe.getRecid())).thenReturn(Optional.of(recipe));

        Mockito.when(recipeRepository.save(recipe)).thenReturn(recipe);

        Mockito.when(reportRepository.findById(report1.getRepid())).thenReturn(Optional.of(report1));

        Mockito.when(medicineRepository.findById(medicine1.getMedId())).thenReturn(Optional.of(medicine1));

        Recipe result = recipeService.createRecipe(report1.getRepid(),medicine1.getMedId(),recipe);

//        verify(recipeRepository,times(1)).findById(recipe.getRecid());
        verify(recipeRepository,times(1)).save(recipe);
        verify(reportRepository,times(1)).findById(report1.getRepid());
        verify(medicineRepository,times(1)).findById(medicine1.getMedId());
        assertEquals(result,recipe);


    }

    @Test
    void createRecipeExceptionReport(){
        Medicine medicine1 = new Medicine(102L,"Brufen",10);

        Patient p1 = new Patient(1L,"Slavko Petronijevic",new Date(),"1234567891011","Belgrade",null);
        Patient p2 = new Patient(2L,"Uros Zdravkovic",new Date(),"4912352678931","Belgrade",null);

        Diagnosis d1 = new Diagnosis("Katarakta",3350,"H0701");
        d1.setDiaId(1L);
        Diagnosis d2 = new Diagnosis("Laringitis",4201,"B0101");
        d2.setDiaId(5L);

        MedicalSpecial medicalSpecial2 = new MedicalSpecial(153L,"Stomatologija",null);

        Doctor dr1 = new Doctor(1L,"Oliver Dragojevic","123456",medicalSpecial2,null);

//        Report(long repid, String description, Patient patient, Diagnosis diagnosis, Doctor doctor, String clinicalFinding)
        Report report1 = new Report(1L,"Slabiji vid i zamucenje vida",p1,d1,dr1,"Testovi preko aparata za gledanje sociva i utvrdio je dijagnozu kataratke");

        Recipe recipe = new Recipe(6L,"2 puta dnevno, 7 dana",medicine1,report1);

        Mockito.when(reportRepository.findById(report1.getRepid())).thenReturn(Optional.empty());

        Assertions.assertThrows(Exception.class,()->{
            recipeService.createRecipe(report1.getRepid(),medicine1.getMedId(),recipe);
        });
    }

    @Test
    void createRecipeExceptionMedicine(){
        Medicine medicine1 = new Medicine(102L,"Brufen",10);

        Patient p1 = new Patient(1L,"Slavko Petronijevic",new Date(),"1234567891011","Belgrade",null);
        Patient p2 = new Patient(2L,"Uros Zdravkovic",new Date(),"4912352678931","Belgrade",null);

        Diagnosis d1 = new Diagnosis("Katarakta",3350,"H0701");
        d1.setDiaId(1L);
        Diagnosis d2 = new Diagnosis("Laringitis",4201,"B0101");
        d2.setDiaId(5L);

        MedicalSpecial medicalSpecial2 = new MedicalSpecial(153L,"Stomatologija",null);

        Doctor dr1 = new Doctor(1L,"Oliver Dragojevic","123456",medicalSpecial2,null);

//        Report(long repid, String description, Patient patient, Diagnosis diagnosis, Doctor doctor, String clinicalFinding)
        Report report1 = new Report(1L,"Slabiji vid i zamucenje vida",p1,d1,dr1,"Testovi preko aparata za gledanje sociva i utvrdio je dijagnozu kataratke");

        Recipe recipe = new Recipe(6L,"2 puta dnevno, 7 dana",medicine1,report1);

        Mockito.when(reportRepository.findById(report1.getRepid())).thenReturn(Optional.of(report1));

        Mockito.when(medicineRepository.findById(medicine1.getMedId())).thenReturn(Optional.empty());

        Assertions.assertThrows(Exception.class,()->{
            recipeService.createRecipe(report1.getRepid(),medicine1.getMedId(),recipe);
        });
    }


    @Test
    void getAll() {

        Medicine medicine1 = new Medicine(102L,"Brufen",10);
        Medicine medicine2 = new Medicine(103L,"Pancef",20);

        Patient p1 = new Patient(1L,"Slavko Petronijevic",new Date(),"1234567891011","Belgrade",null);
        Patient p2 = new Patient(2L,"Uros Zdravkovic",new Date(),"4912352678931","Belgrade",null);

        Diagnosis d1 = new Diagnosis("Katarakta",3350,"H0701");
        d1.setDiaId(1L);
        Diagnosis d2 = new Diagnosis("Laringitis",4201,"B0101");
        d2.setDiaId(5L);

        MedicalSpecial medicalSpecial1 = new MedicalSpecial(154L,"Oftamologija",null);
        MedicalSpecial medicalSpecial2 = new MedicalSpecial(153L,"Stomatologija",null);

        Doctor dr1 = new Doctor(1L,"Oliver Dragojevic","123456",medicalSpecial2,null);
        Doctor dr2 = new Doctor(1L,"Bata Zivojinovic","361789",medicalSpecial1,null);

//        Report(long repid, String description, Patient patient, Diagnosis diagnosis, Doctor doctor, String clinicalFinding)
        Report report1 = new Report(1L,"Slabiji vid i zamucenje vida",p1,d1,dr1,"Testovi preko aparata za gledanje sociva i utvrdio je dijagnozu kataratke");
        Report report2 = new Report(2L,"Promuklost", p2,d2,dr2,"Otecene i crvene glasnice utvrdjeno putem indirektno laringoskopije");


        List<Recipe> recipes = new ArrayList<>();
        recipes.add(new Recipe(4L,"2 puta dnevno, 7 dana",medicine1,report1));
        recipes.add(new Recipe(53L,"Jednom dnevno, 7 - 10 dana",medicine2,report2));

        Mockito.when(recipeRepository.findAll()).thenReturn(recipes);

        List<Recipe> result = recipeService.getAll();

        verify(recipeRepository,times(1)).findAll();

        assertEquals(result,recipes);

    }
}