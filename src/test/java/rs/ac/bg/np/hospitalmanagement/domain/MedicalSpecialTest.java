package rs.ac.bg.np.hospitalmanagement.domain;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

class MedicalSpecialTest {

    private MedicalSpecial medicalSpecial;

    @BeforeEach
    void setUp(){
        medicalSpecial = new MedicalSpecial();
    }

    @AfterEach
    void tearDown(){
        medicalSpecial = null;
    }

    @Test
    void setMedSpecId(){
        medicalSpecial.setMedSpecId(1L);
        assertEquals(1L,medicalSpecial.getMedSpecId());
    }

    @Test
    void setName(){
        medicalSpecial.setName("ORL");
        assertEquals("ORL",medicalSpecial.getName());
    }

    @Test
    void setHospital(){
        Hospital hospital = new Hospital(1L,"Narodni front","Stari Grad","Beograd",null);
        medicalSpecial.setHospital(hospital);
        assertEquals(hospital,medicalSpecial.getHospital());
    }

    @Test
    void setMembers(){
        Set<Doctor> members = new HashSet<>();
        MedicalSpecial medSPec = new MedicalSpecial(3L,"ORL",members);
        members.add(new Doctor(1L,"Oliver Dragojevic","123456",medSPec,null));
        members.add(new Doctor(2L,"Ivana Spanovic","654321",medSPec,null));
        medicalSpecial.setMembers(members);
        medSPec.setMembers(members);
        assertEquals(members,medicalSpecial.getMembers());
    }

    @ParameterizedTest
    @CsvSource({
            "1,ORL"
    })
    void paramitreezedCOntructorName(long id, String name){
        Set<Doctor> doctors = null;
        MedicalSpecial ms = new MedicalSpecial(id,name,doctors);
        medicalSpecial.setName(name);
        medicalSpecial.setMembers(doctors);
        medicalSpecial.setMedSpecId(id);

        assertEquals(ms.getMedSpecId(), medicalSpecial.getMedSpecId());

    }

}