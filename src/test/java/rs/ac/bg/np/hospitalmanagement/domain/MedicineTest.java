package rs.ac.bg.np.hospitalmanagement.domain;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.junit.jupiter.api.Assertions.*;

class MedicineTest {

    private Medicine medicine;

    @BeforeEach
    void setUp(){
        medicine = new Medicine();
    }

    @AfterEach
    void tearDown(){
        medicine = null;
    }


    @Test
    void setIdMedicine(){
        medicine.setMedId(1L);
        assertEquals(1L,medicine.getMedId());
    }

    @Test
    void setIdMedicineInvalidNegative(){
        assertThrows(IllegalArgumentException.class,()->medicine.setMedId(-3L));
    }

    @Test
    void setIdMedicineInvalidZero(){
        assertThrows(IllegalArgumentException.class,()->medicine.setMedId(0L));
    }

    @Test
    void setName(){
        medicine.setName("Pancef");
        assertEquals("Pancef",medicine.getName());
    }

    @Test
    void setNameNull(){
        assertThrows(IllegalArgumentException.class,()->medicine.setName(null));
    }

    @Test
    void setNameEmpty(){
        assertThrows(IllegalArgumentException.class,()->medicine.setName(""));
    }

    @Test
    void setWeightPerPill(){
        medicine.setWeightPerPill(10);
        assertEquals(10,medicine.getWeightPerPill());
    }

    @Test
    void setWeightPerPillZeroOrLess(){
        assertThrows(IllegalArgumentException.class,()->medicine.setWeightPerPill(0));
    }

    @ParameterizedTest
    @CsvSource({
            "1,Pancef,10",
            "2,Panklav,20",
            "3,Brufen,30"
    })
    void testConstructor(long id, String name, int weightPerPill){

        medicine = new Medicine(id,name,weightPerPill);

        assertEquals(id,medicine.getMedId());
        assertEquals(name,medicine.getName());
        assertEquals(weightPerPill,medicine.getWeightPerPill());

    }
}