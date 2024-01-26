package rs.ac.bg.np.hospitalmanagement.domain;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

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
    void setName(){
        medicine.setName("Pancef");
        assertEquals("Pancef",medicine.getName());
    }

    @Test
    void setWeightPerPill(){
        medicine.setWeightPerPill(10);
        assertEquals(10,medicine.getWeightPerPill());
    }

}