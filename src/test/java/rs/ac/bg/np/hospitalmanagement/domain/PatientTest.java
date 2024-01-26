package rs.ac.bg.np.hospitalmanagement.domain;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import java.util.*;

import static org.junit.jupiter.api.Assertions.*;

class PatientTest {

    private Patient patient;

    @BeforeEach
    void setUp(){
        patient = new Patient();
    }

    @AfterEach
    void tearDown(){
        patient = null;
    }

    //id,name,bornDate,jmbg,residence,reports

    @Test
    void setpId(){
        patient.setpId(1L);
        assertEquals(1L,patient.getpId());
    }

    @Test
    void setName(){
        patient.setName("Slavko Petronijevic");
        assertEquals("Slavko Petronijevic",patient.getName());
    }

    @Test
    void setbornDate(){
        patient.setBornDate(new Date());
        assertEquals(new Date(),patient.getBornDate());
    }

    @Test
    void setResidence(){
        patient.setResidence("Beograd");
        assertEquals("Beograd",patient.getResidence());
    }

    @Test
    void setReports(){
        List<Report> reports = new ArrayList<>();
        reports.add(new Report(1L,null,null,null,null,null));
        reports.add(new Report(2L,null,null,null,null,null));
        patient.setReports(reports);
        assertEquals(reports,patient.getReports());
    }

    @Test
    void setJmbg() {
        patient.setJmbg("12344567891011");
        assertEquals("12344567891011",patient.getJmbg());
    }

    @ParameterizedTest
    @CsvSource({
            "1, Slavko Petronijevic, 12345678910112,Beograd"
    })
    void testParametrizedContructor(long id,String name,String jmbg,String residence){

        Patient p1 = new Patient();
        p1.setJmbg(jmbg);
        p1.setpId(id);
        p1.setResidence(residence);
        p1.setName(name);

        patient.setName(name);
        patient.setJmbg(jmbg);
        patient.setpId(id);
        patient.setResidence(residence);

        boolean tacno;
        if(p1.getJmbg().equals(patient.getJmbg()) && p1.getpId() == patient.getpId()){
            tacno = true;
        }else tacno = false;

        assertTrue(tacno);

    }

}