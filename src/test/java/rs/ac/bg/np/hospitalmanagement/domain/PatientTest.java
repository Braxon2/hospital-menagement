package rs.ac.bg.np.hospitalmanagement.domain;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import java.text.ParseException;
import java.text.SimpleDateFormat;
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
    void setbornDate() throws ParseException {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        Date bornDate = dateFormat.parse("2000-5-23");
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
            "1,Slavko,2000-01-01,1234567891011,Belgrade,null",
            "2,Darko,1970-05-08,1236547891011,Belgrade,null",
            "2,Goran,1965-09-09,3216547891011,Belgrade,null",
    })
    void testParametrizedContructor(long id,String name,String date,String jmbg,String residence,String reports) throws ParseException {




        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        List<Report> fake = new ArrayList<>();

        Date bornDate = dateFormat.parse(date);
        List<Report> reports1 = reports.equals("null")?null:fake;

        Patient p = new Patient(id,name,bornDate,jmbg,residence,reports1);

        assertEquals(id,p.getpId());
        assertEquals(name,p.getName());
        assertEquals(jmbg,p.getJmbg());
        assertEquals(residence,p.getResidence());
        assertEquals(bornDate,p.getBornDate());
        assertEquals(reports1,p.getReports());


    }

}