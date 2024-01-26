package rs.ac.bg.np.hospitalmanagement.domain;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Date;

import static org.junit.jupiter.api.Assertions.*;

class ReportTest {

    private Report report;

    @BeforeEach
    void setUp(){
        report = new Report();
    }

    @AfterEach
    void tearDown(){
        report = null;
    }

    @Test
    void setRepid() {
        report.setRepid(1L);
        assertEquals(1L,report.getRepid());
    }

    @Test
    void setDescription() {
        report.setDescription("Nesto pise");
        assertEquals("Nesto pise",report.getDescription());
    }

    @Test
    void setPatient() {
        Date date = new Date();
        Patient patient = new Patient("Slavko Petronijevic",date,"1234567891011","Beograd");
        report.setPatient(patient);
        assertEquals(patient,report.getPatient());

    }

    @Test
    void setDiagnosis() {
        Diagnosis diagnosis = new Diagnosis("Laringitis",123L,"H101");
        report.setDiagnosis(diagnosis);
        assertEquals(diagnosis,report.getDiagnosis());
    }

    @Test
    void setDoctor() {
        MedicalSpecial medicalSpecial = new MedicalSpecial(3L,"ORL",null);
        Doctor doctor = new Doctor(1L,"Vladan Aksentijevic","123456", medicalSpecial,null);
        report.setDoctor(doctor);
        assertEquals(doctor,report.getDoctor());
    }

    @Test
    void setClinicalFinding() {
        report.setClinicalFinding("Uradjen detaljan pregled");
        assertEquals("Uradjen detaljan pregled",report.getClinicalFinding());
    }
}