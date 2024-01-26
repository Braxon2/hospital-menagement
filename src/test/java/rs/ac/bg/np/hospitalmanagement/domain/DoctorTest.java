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
    void setName(){
        doctor.setName("Zoran Radmilovic");
        assertEquals("Zoran Radmilovic",doctor.getName());
    }

    @Test
    void setLicenceNumber(){
        doctor.setLicenceNumber("123456");
        assertEquals("123456",doctor.getLicenceNumber());
    }

    @Test
    void checkLength(){
        doctor.setLicenceNumber("123456");
        assertEquals(6,doctor.getLicenceNumber().length());
    }

    @Test
    void setMedicalSpecial(){
        doctor.setMedicalSpecial(new MedicalSpecial(1L,"ORL",null));
        assertEquals(1L,doctor.getMedicalSpecial().getMedSpecId());
    }

    @Test
    void setReports(){
        Set<Report> reports = new HashSet<>();
        reports.add(new Report(1L,null,null,null,null,null));
        doctor.setReports(reports);
        assertEquals(reports,doctor.getReports());
    }



}