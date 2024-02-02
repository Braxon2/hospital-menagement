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
    void setMedSpecIdZero(){
        assertThrows(IllegalArgumentException.class,()->medicalSpecial.setMedSpecId(0L));
    }

    @Test
    void setMedSpecIdNegative(){
        assertThrows(IllegalArgumentException.class,()->medicalSpecial.setMedSpecId(-5L));
    }

    @Test
    void setName(){
        medicalSpecial.setName("ORL");
        assertEquals("ORL",medicalSpecial.getName());
    }

    @Test
    void setNameNull(){
        assertThrows(IllegalArgumentException.class,()->medicalSpecial.setName(null));
    }

    @Test
    void setNameEmpty(){
        assertThrows(IllegalArgumentException.class,()->medicalSpecial.setName(""));
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
            "1,ORL,null",
            "2,xxx,null",
            "3,zzz,null"
    })
    void paramitreezedCOntructorName(long id, String name,String membersData){
        Set<Doctor> doctors = new HashSet<>();
        Doctor doctor = new Doctor();

        Set<Doctor> result = membersData.equals("null")?null:doctors;

        medicalSpecial = new MedicalSpecial(id,name,result);

        assertEquals(id, medicalSpecial.getMedSpecId());
        assertEquals(name,medicalSpecial.getName());
        assertEquals(result,medicalSpecial.getMembers());

    }

    @ParameterizedTest
    @CsvSource({
            "ORL",
            "xxx",
            "zzz"
    })
    void paramitreezedCOntructorName(String name){


        medicalSpecial = new MedicalSpecial(name);

        assertEquals(name,medicalSpecial.getName());


    }

    @Test
    void testToString(){
        medicalSpecial = new MedicalSpecial(1L,"ORL",null);
        assertTrue(medicalSpecial.toString().contains("1"));
        assertTrue(medicalSpecial.toString().contains("ORL"));
    }

    @ParameterizedTest
    @CsvSource({
            "1,ORL,null,1,ORL,null,true",
            "2,xxx,null,1,ORL,null,false",
            "3,zzz,null,2,zzz,null,false",
            "3,zzz,null,3,qqq,null,false"

    })
    void testEquals(long id1, String name1,String membersData1,long id2, String name2,String membersData2,boolean areEqual){
        Set<Doctor> doctors1 = new HashSet<>();
        Doctor doctor1 = new Doctor();

        Set<Doctor> result1 = membersData1.equals("null")?null:doctors1;

        medicalSpecial = new MedicalSpecial(id1,name1,result1);


        Set<Doctor> doctors2 = new HashSet<>();
        Doctor doctor2 = new Doctor();

        Set<Doctor> result2 = membersData2.equals("null")?null:doctors2;

        MedicalSpecial medicalSpecial2 = new MedicalSpecial(id2,name2,result2);

        assertEquals(medicalSpecial.equals(medicalSpecial2),areEqual);


    }

}