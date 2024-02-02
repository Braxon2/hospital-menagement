package rs.ac.bg.np.hospitalmanagement.domain;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import javax.print.Doc;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

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
    void setRepidZero(){
        assertThrows(IllegalArgumentException.class,()->report.setRepid(0L));
    }

    @Test
    void setRepidNegative(){
        assertThrows(IllegalArgumentException.class,()->report.setRepid(-5L));
    }

    @Test
    void setDescription() {
        report.setDescription("Nesto pise");
        assertEquals("Nesto pise",report.getDescription());
    }

    @Test
    void setDescriptionNull() {
        assertThrows(IllegalArgumentException.class,()->report.setDescription(null));
    }

    @Test
    void setDescriptionEmpty() {
        assertThrows(IllegalArgumentException.class,()->report.setDescription(""));
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
        Diagnosis diagnosis = new Diagnosis("Laringitis",1203L,"H0101");
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

    @Test
    void setClinicalFindingNull() {
        assertThrows(IllegalArgumentException.class,()->report.setClinicalFinding(null));
    }

    @Test
    void setClinicalFindingEmpty() {
        assertThrows(IllegalArgumentException.class,()->report.setClinicalFinding(""));
    }

//    Report(long repid, String description, Patient patient, Diagnosis diagnosis, Doctor doctor, String clinicalFinding)
//Patient(long pId, String name, Date bornDate, String jmbg, String residence, List<Report> reports)
//Doctor(long docId, String name, String licenceNumber, MedicalSpecial medicalSpecial, Set<Report> reports)
    @ParameterizedTest
    @CsvSource({
            "1,sasa, 1:Slavko:2000-01-01:1234567891011:Belgrade:null, Laringitis:1230:H0101, 1:Bore:123456:1;ORL;null:null,nalaz",
            "2,sasa, 1:Goran:2000-01-01:1234567891011:Belgrade:null, Tinitus:1230:H0101, 1:Zale:123456:1;Dermatologija;null:null,nalaz"

    })
    void testParametrizedContructor(long id,String description, String patientData,String diagnosisData,String doctorData,String clinicalFinding) throws ParseException {

        Patient patient = convertDataToPatient(patientData);
        Diagnosis diagnosis = convertDataToDiagnosis(diagnosisData);
        Doctor doctor = converDataToDoctor(doctorData);

        Report report1 = new Report(id,description,patient,diagnosis,doctor,clinicalFinding);

        assertEquals(id,report1.getRepid());
        assertEquals(description,report1.getDescription());
        assertEquals(clinicalFinding,report1.getClinicalFinding());
        assertEquals(patient,report1.getPatient());
        assertEquals(diagnosis,report1.getDiagnosis());
        assertEquals(doctor,report1.getDoctor());


    }

    private Doctor converDataToDoctor(String doctorData) {

        Doctor doctor = new Doctor();

        String[] dataDoctor = doctorData.split(":");
        doctor.setDocId(Long.parseLong(dataDoctor[0]));
        doctor.setName(dataDoctor[1]);
        doctor.setLicenceNumber(dataDoctor[2]);

        String[] dataMedicalSpecial = dataDoctor[3].split(";");

        Set<Report> fake = new HashSet<>();
        MedicalSpecial ms = new MedicalSpecial();

        Doctor dr1 = new Doctor();
        Set<Doctor> setDocros = new HashSet<>();
        setDocros.add(dr1);

        ms.setMedSpecId(Long.parseLong(dataMedicalSpecial[0]));
        ms.setName(dataMedicalSpecial[1]);
        ms.setMembers(dataMedicalSpecial[2].equals("null")?null:setDocros);

        Set<Report> reports1 = dataDoctor[4].equals("null")?null:fake;

        doctor.setMedicalSpecial(ms);
        doctor.setReports(reports1);

        return doctor;

    }

    private Diagnosis convertDataToDiagnosis(String diagnosisData) {

        Diagnosis diagnosis = new Diagnosis();

        String[] dataDiagnosis = diagnosisData.split(":");
        diagnosis.setName(dataDiagnosis[0]);
        diagnosis.setCodeOfDiganosis(Long.parseLong(dataDiagnosis[1]));
        diagnosis.setLabel(dataDiagnosis[2]);

        return diagnosis;

    }

    private Patient convertDataToPatient(String patientData) throws ParseException {

        String[] dataPatient = patientData.split(":");
        Patient p = new Patient();

        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        List<Report> fake = new ArrayList<>();

        long id = Long.parseLong(dataPatient[0]);
        String name = dataPatient[1];
        Date date = dateFormat.parse(dataPatient[2]);
        String jmbg = dataPatient[3];
        String residence = dataPatient[4];
        List<Report> reports = dataPatient[5].equals("null")?null:fake;

        p.setpId(id);
        p.setJmbg(jmbg);
        p.setBornDate(date);
        p.setName(name);
        p.setResidence(residence);


        return p;
    }

    @ParameterizedTest
    @CsvSource({
            "1,sasa, 1:Slavko:2000-01-01:1234567891011:Belgrade:null, Laringitis:1230:H0101, 1:Bore:123456:1;ORL;null:null,nalaz,1,sasa, 1:Slavko:2000-01-01:1234567891011:Belgrade:null, Laringitis:1230:H0101, 1:Bore:123456:1;ORL;null:null,nalaz,true",
            "2,sasa, 1:Goran:2000-01-01:1234567891011:Belgrade:null, Tinitus:1230:H0101, 1:Zale:123456:1;Dermatologija;null:null,nalaz,2,sasa, 1:Goran:2000-01-01:1234567891011:Belgrade:null, Laringitis:1230:H0101, 1:Zale:123456:1;Dermatologija;null:null,nalaz,false",
            "2,sasa, 1:Goran:2000-07-01:1234567891011:Belgrade:null, Tinitus:1230:H0101, 1:Zale:123456:1;Dermatologija;null:null,nalaz,2,sasa, 1:Goran:2000-01-01:1234567891011:Belgrade:null, Tinitus:1230:H0101, 1:Zale:123456:1;Dermatologija;null:null,nalaz,false",
            "2,Momcilo, 1:Goran:2000-01-01:1234567891011:Belgrade:null, Tinitus:1230:H0101, 1:Zale:123456:1;Dermatologija;null:null,nalaz,2,sasa, 1:Goran:2000-01-01:1234567891011:Belgrade:null, Tinitus:1230:H0101, 1:Zale:123456:1;Dermatologija;null:null,nalaz,false",
            "2,sasa, 1:Goran:2000-01-01:1234567891011:Belgrade:null, Tinitus:1230:H0101, 1:Zale:123456:1;Dermatologija;null:null,nalaz,3,sasa, 1:Goran:2000-01-01:1234567891011:Belgrade:null, Tinitus:1230:H0101, 1:Zale:123456:1;Dermatologija;null:null,nalaz,false"


    })
    void testEquals(long id,String description, String patientData,String diagnosisData,String doctorData,String clinicalFinding,
                    long id2,String description2, String patientData2,String diagnosisData2,String doctorData2,String clinicalFinding2,boolean areEquals) throws ParseException {

        Patient patient = convertDataToPatient(patientData);
        Diagnosis diagnosis = convertDataToDiagnosis(diagnosisData);
        Doctor doctor = converDataToDoctor(doctorData);

        Report report1 = new Report(id,description,patient,diagnosis,doctor,clinicalFinding);

        Patient patient2 = convertDataToPatient(patientData2);
        Diagnosis diagnosis2 = convertDataToDiagnosis(diagnosisData2);
        Doctor doctor2 = converDataToDoctor(doctorData2);

        Report report2 = new Report(id2,description2,patient2,diagnosis2,doctor2,clinicalFinding2);

        assertEquals(report1.equals(report2),areEquals);


    }

    @Test
    void toStringReprot(){
        Date date = new Date();
        Patient patient = new Patient("Slavko Petronijevic",date,"1234567891011","Beograd");
        MedicalSpecial medicalSpecial = new MedicalSpecial(3L,"ORL",null);
        Doctor doctor = new Doctor(1L,"Vladan Aksentijevic","123456", medicalSpecial,null);
        Diagnosis diagnosis = new Diagnosis("Laringitis",1203L,"H0101");
        Report report1 = new Report(1L,"opis",patient,diagnosis,doctor,"nalaz");

        assertTrue(report1.toString().contains("1"));
        assertTrue(report1.toString().contains("opis"));
        assertTrue(report1.toString().contains(patient.toString()));
        assertTrue(report1.toString().contains(diagnosis.toString()));
        assertTrue(report1.toString().contains(doctor.toString()));
        assertTrue(report1.toString().contains("nalaz"));
    }

}