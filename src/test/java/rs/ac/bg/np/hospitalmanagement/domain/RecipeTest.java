package rs.ac.bg.np.hospitalmanagement.domain;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

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

    @Test
    void testConstructor(){
        Medicine med = new Medicine(1L,"Brufen",10);
        Report report = new Report(1L,"nema",null,null,null,"okej je sve");

        Recipe r1 = new Recipe(1L,"2 puta dnevno",med,report);
        recipe.setReport(report);
        recipe.setRecid(r1.getRecid());
        recipe.setDoses(r1.getDoses());
        recipe.setMedicine(r1.getMedicine());
        boolean tacno;
        if(r1.getDoses().equals(recipe.getDoses()) &&
                r1.getMedicine().getMedId() == recipe.getMedicine().getMedId() &&
                    r1.getReport().getRepid() == recipe.getReport().getRepid() &&
                r1.getRecid() == recipe.getRecid()
        ){
            tacno = true;
        }else tacno = false;

        assertTrue(tacno);

    }





}