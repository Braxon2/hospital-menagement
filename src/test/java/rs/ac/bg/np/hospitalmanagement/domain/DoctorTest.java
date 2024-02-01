package rs.ac.bg.np.hospitalmanagement.domain;


import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import java.util.HashSet;
import java.util.Set;

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
    void setReports(){
        Set<Report> reports = new HashSet<>();
        reports.add(new Report(1L,"sas",null,null,null,"asasas"));
        doctor.setReports(reports);
        assertEquals(reports,doctor.getReports());
    }



}