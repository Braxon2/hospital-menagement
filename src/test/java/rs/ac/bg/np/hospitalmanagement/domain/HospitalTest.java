package rs.ac.bg.np.hospitalmanagement.domain;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

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
    void setIdHospitalInvalidIdZero(){
        assertThrows(IllegalArgumentException.class,()->hospital.setHospitalId(0L));
    }

    @Test
    void setIdHospitalInvalidIdNegative(){
        assertThrows(IllegalArgumentException.class,()->hospital.setHospitalId(-5L));
    }

    @Test
    void setName(){
        hospital.setName("Narodni Front");
        assertEquals("Narodni Front",hospital.getName());
    }

    @Test
    void setNameNull(){
        assertThrows(IllegalArgumentException.class,()->hospital.setName(null));
    }

    @Test
    void setNameEmpty(){
        assertThrows(IllegalArgumentException.class,()->hospital.setName(""));
    }

    @Test
    void setAddress(){
        hospital.setAddress("Stari Grad");
        assertEquals("Stari Grad",hospital.getAddress());
    }

    @Test
    void setAddressNull(){
        assertThrows(IllegalArgumentException.class,()->hospital.setAddress(null));
    }

    @Test
    void setAddressEmpty(){
        assertThrows(IllegalArgumentException.class,()->hospital.setAddress(""));
    }

    @Test
    void setCity(){
        hospital.setCity("Beograd");
        assertEquals("Beograd",hospital.getCity());
    }

    @Test
    void setCityNull(){
        assertThrows(IllegalArgumentException.class,()->hospital.setCity(null));
    }

    @Test
    void setCityEmpty(){
        assertThrows(IllegalArgumentException.class,()->hospital.setCity(""));
    }

    @Test
    void setMedicalSpecial(){
        Set<MedicalSpecial> specials = new HashSet<>();
        specials.add(new MedicalSpecial(3L,"ORL",null));
        specials.add(new MedicalSpecial(2L,"Dermatolog",null));
        hospital.setMedicalSpecials(specials);
        assertEquals(specials,hospital.getMedicalSpecials());
    }

    @ParameterizedTest
    @CsvSource({
            "1,Narodni,Beograd,Grad,null",
            "2,xxx,yyy,zzz,null",
    })
    void testParametrizedConstructor(Long id,String name,String addrres,String city,String medical){

        MedicalSpecial ms = new MedicalSpecial();
        MedicalSpecial finalMedspec = medical.equals("null")?null:ms;
        Set<MedicalSpecial> sets = new HashSet<>();
        sets.add(finalMedspec);

        hospital = new Hospital(id,name,addrres,city,sets);

        assertEquals(id,hospital.getHospitalId());
        assertEquals(name,hospital.getName());
        assertEquals(city,hospital.getCity());
        assertEquals(addrres,hospital.getAddress());
        assertEquals(sets,hospital.getMedicalSpecials());

    }

}