package rs.ac.bg.np.hospitalmanagement.domain;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class RecipeTest {

    private Recipe recipe;

    @BeforeEach
    void setUp(){
        recipe = new Recipe();
    }

    @AfterEach
    void tearDown(){
        recipe = null;
    }

    @Test
    void setDoses() {
        recipe.setDoses("2 puta dnevno");
        assertEquals("2 puta dnevno",recipe.getDoses());
    }

    @Test
    void setDosesNull() {
        assertThrows(IllegalArgumentException.class,()->recipe.setDoses(null));
    }

    @Test
    void setDosesEmpty() {
        assertThrows(IllegalArgumentException.class,()->recipe.setDoses(""));
    }

    @Test
    void setRecid() {
        recipe.setRecid(1L);
        assertEquals(1L,recipe.getRecid());
    }

    @Test
    void setRecidZero() {
        assertThrows(IllegalArgumentException.class,()->recipe.setRecid(0L));
    }

    @Test
    void setRecidNegative() {
        assertThrows(IllegalArgumentException.class,()->recipe.setRecid(-5L));
    }

    @Test
    void setMedicine() {
        Medicine med = new Medicine(1L,"Brufen",10);
        recipe.setMedicine(med);
        assertEquals(med,recipe.getMedicine());
    }

    @Test
    void setMedicineNull() {
        assertThrows(IllegalArgumentException.class,()->recipe.setMedicine(null));
    }

    @Test
    void setReport() {
        Report report = new Report(1L,"nema",null,null,null,"okej je sve");
        recipe.setReport(report);
        assertEquals(report,recipe.getReport());
    }

    @ParameterizedTest
    @CsvSource({
            "1,dva puta dnevno,1:Pancef:10,null",
            "2,dva puta dnevno,2:Brufen:20,null",
            "3,dva puta dnevno,3:Panklav:30,null",
    })
    void testConstructor(long id, String doses, String medicineText,String reportText){

        Report report = reportText.equals("null")?null:new Report();
        String[] dataMedicine = medicineText.split(":");
        Medicine medicine = new Medicine();
        medicine.setMedId(Long.parseLong(dataMedicine[0]));
        medicine.setName(dataMedicine[1]);
        medicine.setWeightPerPill(Integer.parseInt(dataMedicine[2]));
        recipe = new Recipe(id,doses,medicine,report);

        assertEquals(id,recipe.getRecid());
        assertEquals(doses,recipe.getDoses());
        assertEquals(medicine,recipe.getMedicine());
        assertEquals(report,recipe.getReport());

    }

    @Test
    void testToString(){
        Medicine medicine = new Medicine(2l,"Panklav",10);
        recipe = new Recipe(1l,"dva puta dnevno",medicine,null);

        assertTrue(recipe.toString().contains("1"));
        assertTrue(recipe.toString().contains("dva puta dnevno"));
        assertTrue(recipe.toString().contains(medicine.toString()));
    }


    @ParameterizedTest
    @CsvSource({
            "1,dva puta dnevno,1:Pancef:10,null,1,dva puta dnevno,1:Pancef:10,null,1,1,true",
            "1,dva puta dnevno,1:Pancef:10,null,1,dva puta dnevno,1:Panklav:10,null,1,1,false",
            "2,dva puta dnevno,2:Brufen:20,null,1,dva puta dnevno,1:Pancef:10,null,1,2,false",
            "3,dva puta dnevno,3:Panklav:30,null,3,tri puta dnevno,3:Panklav:30,null,3,3,false",
            "3,dva puta dnevno,3:Panklav:30,null,2,dva puta dnevno,3:Panklav:30,null,1,1,false"
    })
    void testConstructor(long id, String doses, String medicineText,String reportText,
                         long id2, String doses2, String medicineText2,String reportText2,long repid1,long repid2,boolean areEqual){

        Report report = new Report();
        report.setRepid(repid1);
        String[] dataMedicine = medicineText.split(":");
        Medicine medicine = new Medicine();
        medicine.setMedId(Long.parseLong(dataMedicine[0]));
        medicine.setName(dataMedicine[1]);
        medicine.setWeightPerPill(Integer.parseInt(dataMedicine[2]));
        recipe = new Recipe(id,doses,medicine,report);

        Report report2 = new Report();
        report2.setRepid(repid2);
        String[] dataMedicine2 = medicineText2.split(":");
        Medicine medicine2 = new Medicine();
        medicine2.setMedId(Long.parseLong(dataMedicine2[0]));
        medicine2.setName(dataMedicine2[1]);
        medicine2.setWeightPerPill(Integer.parseInt(dataMedicine2[2]));
        Recipe r2 = new Recipe(id2,doses2,medicine2,report2);

        assertEquals(r2.equals(recipe),areEqual);

    }

}