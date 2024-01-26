package rs.ac.bg.np.hospitalmanagement.domain;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.HashSet;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

class HospitalTest {

    private Hospital hospital;

    @BeforeEach
    void setUp(){
        hospital = new Hospital();
    }

    @AfterEach
    void tearDown(){
        hospital = null;
    }

    @Test
    void setIdHospital(){
        hospital.setHospitalId(1L);
        assertEquals(1L,hospital.getHospitalId());
    }

    @Test
    void setName(){
        hospital.setName("Narodni Front");
        assertEquals("Narodni Front",hospital.getName());
    }

    @Test
    void setAddress(){
        hospital.setAddress("Stari Grad");
        assertEquals("Stari Grad",hospital.getAddress());
    }

    @Test
    void setCity(){
        hospital.setCity("Beograd");
        assertEquals("Beograd",hospital.getCity());
    }

    @Test
    void setMedicalSpecial(){
        Set<MedicalSpecial> specials = new HashSet<>();
        specials.add(new MedicalSpecial(3L,"ORL",null));
        specials.add(new MedicalSpecial(2L,"Dermatolog",null));
        hospital.setMedicalSpecials(specials);
        assertEquals(specials,hospital.getMedicalSpecials());
    }

}