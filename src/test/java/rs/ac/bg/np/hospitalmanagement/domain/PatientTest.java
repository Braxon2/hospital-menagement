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
    void setIdZero(){
        assertThrows(IllegalArgumentException.class,()->patient.setpId(0L));
    }

    @Test
    void setIdNegative(){
        assertThrows(IllegalArgumentException.class,()->patient.setpId(-5L));
    }

    @Test
    void setName(){
        patient.setName("Slavko Petronijevic");
        assertEquals("Slavko Petronijevic",patient.getName());
    }

    @Test
    void setNameNull(){
        assertThrows(IllegalArgumentException.class,()->patient.setName(null));
    }

    @Test
    void setNameEmpty(){
        assertThrows(IllegalArgumentException.class,()->patient.setName(""));
    }

    @Test
    void setbornDate(){
        patient.setBornDate(new Date());
        assertEquals(new Date(),patient.getBornDate());
    }

    @Test
    void setbornDateNull(){
        assertThrows(IllegalArgumentException.class,()-> patient.setBornDate(null));
    }

    @Test
    void setResidence(){
        patient.setResidence("Beograd");
        assertEquals("Beograd",patient.getResidence());
    }

    @Test
    void setResidenceNull(){
        assertThrows(IllegalArgumentException.class,()->patient.setResidence(null));
    }

    @Test
    void setResidenceEmpty(){
        assertThrows(IllegalArgumentException.class,()->patient.setResidence(""));
    }

    @Test
    void setReports(){
        List<Report> reports = new ArrayList<>();
        reports.add(new Report(1L,"sa",null,null,null,"asas"));
        reports.add(new Report(2L,"snodaks",null,null,null,"oksds"));
        patient.setReports(reports);
        assertEquals(reports,patient.getReports());
    }

    @Test
    void setJmbg() {
        patient.setJmbg("1234567891011");
        assertEquals("1234567891011",patient.getJmbg());
    }

    @Test
    void setJmbgNull() {
        assertThrows(IllegalArgumentException.class,()->patient.setJmbg(null));
    }

    @Test
    void setJmbgEmpty() {
        assertThrows(IllegalArgumentException.class,()->patient.setJmbg(""));
    }

    @Test
    void setJmbgLengthInvalid() {
        assertThrows(IllegalArgumentException.class,()->patient.setJmbg("123"));
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