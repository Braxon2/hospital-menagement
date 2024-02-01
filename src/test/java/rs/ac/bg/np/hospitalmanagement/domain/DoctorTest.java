package rs.ac.bg.np.hospitalmanagement.domain;


import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

import static org.junit.jupiter.api.Assertions.*;

class DoctorTest {

    private Doctor doctor;

    @BeforeEach
    void setUp(){
        doctor = new Doctor();
    }

    @AfterEach
    void tearDown(){
        doctor = null;
    }

    @Test
    void setDocId(){
        doctor.setDocId(1L);
        assertEquals(1L,doctor.getDocId());
    }

    @Test
    void setDocIdZero(){
        assertThrows(IllegalArgumentException.class,()->doctor.setDocId(0L));
    }

    @Test
    void setDocIdNegative(){
        assertThrows(IllegalArgumentException.class,()->doctor.setDocId(-5L));
    }


    @Test
    void setName(){
        doctor.setName("Zoran Radmilovic");
        assertEquals("Zoran Radmilovic",doctor.getName());
    }

    @Test
    void setNameNull(){
        assertThrows(IllegalArgumentException.class,()->doctor.setName(null));
    }

    @Test
    void setNameEmpty(){
        assertThrows(IllegalArgumentException.class,()->doctor.setName(""));
    }

    @Test
    void setLicenceNumber(){
        doctor.setLicenceNumber("123456");
        assertEquals("123456",doctor.getLicenceNumber());
    }

    @Test
    void setLicenceNumberEmpty(){
        assertThrows(IllegalArgumentException.class,()->doctor.setLicenceNumber(""));
    }

    @Test
    void setLicenceNumberNull(){
        assertThrows(IllegalArgumentException.class,()->doctor.setLicenceNumber(null));
    }


    @Test
    void checkLength(){
        doctor.setLicenceNumber("123456");
        assertEquals(6,doctor.getLicenceNumber().length());
    }

    @Test
    void checkLengthInvalid(){
        assertThrows(IllegalArgumentException.class,()->doctor.setLicenceNumber("12345678"));
    }

    @Test
    void setMedicalSpecial(){
        doctor.setMedicalSpecial(new MedicalSpecial(1L,"ORL",null));
        assertEquals(1L,doctor.getMedicalSpecial().getMedSpecId());
    }

    @Test
    void setMedicalSpecialFail(){
        assertThrows(IllegalArgumentException.class,()->doctor.setMedicalSpecial(null));
    }

    @Test
    void setReports(){
        Set<Report> reports = new HashSet<>();
        reports.add(new Report(1L,"sas",null,null,null,"asasas"));
        doctor.setReports(reports);
        assertEquals(reports,doctor.getReports());
    }

    @ParameterizedTest
    @CsvSource({
            "1,Slavko,123456,1;ORL;null,null",
            "2,Darko,654321,2;Stomtatologija;null,null",
            "3,Goran,123987,3;xyz;null,null",
    })
    void testParametrizedContructor(long id,String name,String licenceNumber,String medicalSpecial,String reports) throws ParseException {


//        Doctor(long docId, String name, String licenceNumber, MedicalSpecial medicalSpecial, Set<Report> reports)

        Set<Report> fake = new HashSet<>();
        MedicalSpecial ms = new MedicalSpecial();
        Set<Report> reports1 = reports.equals("null")?null:fake;

        Doctor dr1 = new Doctor();
        Set<Doctor> setDocros = new HashSet<>();
        setDocros.add(dr1);

        String[] dataMedicalSpecial = medicalSpecial.split(";");
        ms.setMedSpecId(Long.parseLong(dataMedicalSpecial[0]));
        ms.setName(dataMedicalSpecial[1]);
        ms.setMembers(dataMedicalSpecial[2].equals("null")?null:setDocros);

//        MedicalSpecial medSPec = medicalSpecial.equals("null")?null:ms;
        Doctor doctor = new Doctor(id,name,licenceNumber,ms,reports1);


        assertEquals(id,doctor.getDocId());
        assertEquals(name,doctor.getName());
        assertEquals(licenceNumber,doctor.getLicenceNumber());
        assertEquals(ms,doctor.getMedicalSpecial());
        assertEquals(reports1,doctor.getReports());



    }


}