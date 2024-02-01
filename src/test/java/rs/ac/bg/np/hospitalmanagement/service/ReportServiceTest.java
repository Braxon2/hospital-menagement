package rs.ac.bg.np.hospitalmanagement.service;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import rs.ac.bg.np.hospitalmanagement.domain.*;
import rs.ac.bg.np.hospitalmanagement.repository.DiagnosisRepository;
import rs.ac.bg.np.hospitalmanagement.repository.DoctorRepository;
import rs.ac.bg.np.hospitalmanagement.repository.PatientRepository;
import rs.ac.bg.np.hospitalmanagement.repository.ReportRepository;

import java.util.*;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
class ReportServiceTest {

    @Mock
    private ReportRepository reportRepository;

    @Mock
    private DoctorRepository doctorRepository;

    @Mock
    private PatientRepository patientRepository;

    @Mock
    private DiagnosisRepository diagnosisRepository;

    @InjectMocks
    private ReportService reportService;

    @InjectMocks
    private PatientService patientService;

    @Test
    void getAll() {

        List<Report> reports = new ArrayList<>();
//        reports.add(new Report());

        Patient p1 = new Patient(1L,"Slavko Petronijevic",new Date(),"1234567891011","Belgrade",null);
        Patient p2 = new Patient(2L,"Uros Zdravkovic",new Date(),"4912352678931","Belgrade",null);

        Diagnosis d1 = new Diagnosis("Katarakta",3350,"H0701");
        d1.setDiaId(1L);
        Diagnosis d2 = new Diagnosis("Laringitis",4201,"B0101");
        d2.setDiaId(5L);

        MedicalSpecial medicalSpecial1 = new MedicalSpecial(154L,"Oftamologija",null);
        MedicalSpecial medicalSpecial2 = new MedicalSpecial(153L,"Stomatologija",null);

        Doctor dr1 = new Doctor(1L,"Oliver Dragojevic","123456",medicalSpecial2,null);
        Doctor dr2 = new Doctor(2L,"Bata Zivojinovic","361789",medicalSpecial1,null);

//        Report(long repid, String description, Patient patient, Diagnosis diagnosis, Doctor doctor, String clinicalFinding)
        reports.add(new Report(1L,"Slabiji vid i zamucenje vida",p1,d1,dr1,"Testovi preko aparata za gledanje sociva i utvrdio je dijagnozu kataratke"));
        reports.add(new Report(2L,"Promuklost", p2,d2,dr2,"Otecene i crvene glasnice utvrdjeno putem indirektno laringoskopije"));

        Mockito.when(reportRepository.findAll()).thenReturn(reports);

        List<Report> result = reportService.getAll();
        verify(reportRepository,times(1)).findAll();

        assertEquals(result,reports);

    }

    @Test
    void getReport() throws Exception {
        Diagnosis d1 = new Diagnosis("Katarakta",3350,"H0701");
        MedicalSpecial medicalSpecial2 = new MedicalSpecial(153L,"Stomatologija",null);
        Patient p1 = new Patient(1L,"Slavko Petronijevic",new Date(),"1234567891011","Belgrade",null);
        Doctor dr1 = new Doctor(1L,"Oliver Dragojevic","123456",medicalSpecial2,null);
        d1.setDiaId(1L);
        Report report = new Report(1L,"Slabiji vid i zamucenje vida",p1,d1,dr1,"Testovi preko aparata za gledanje sociva i utvrdio je dijagnozu kataratke");

        Mockito.when(reportRepository.findById(report.getRepid())).thenReturn(Optional.of(report));

        Report result = reportService.getReport(report.getRepid());

        verify(reportRepository,times(1)).findById(report.getRepid());
        assertEquals(result,report);

    }

    @Test
    void getReportException(){
        Diagnosis d1 = new Diagnosis("Katarakta",3350,"H0701");
        MedicalSpecial medicalSpecial2 = new MedicalSpecial(153L,"Stomatologija",null);
        Patient p1 = new Patient(1L,"Slavko Petronijevic",new Date(),"1234567891011","Belgrade",null);
        Doctor dr1 = new Doctor(1L,"Oliver Dragojevic","123456",medicalSpecial2,null);
        d1.setDiaId(1L);
        Report report = new Report(8L,"Slabiji vid i zamucenje vida",p1,d1,dr1,"Testovi preko aparata za gledanje sociva i utvrdio je dijagnozu kataratke");

        Mockito.when(reportRepository.findById(report.getRepid())).thenReturn(Optional.empty());

        Assertions.assertThrows(Exception.class,()->{
            reportService.getReport(report.getRepid());
        });
        verify(reportRepository,times(1)).findById(report.getRepid());

    }

    @Test
    void doctorNewReport() throws Exception {
        Diagnosis d1 = new Diagnosis("Katarakta",3350,"H0701");
        MedicalSpecial medicalSpecial2 = new MedicalSpecial(153L,"Stomatologija",null);
        Patient p1 = new Patient(1L,"Slavko Petronijevic",new Date(),"1234567891011","Belgrade",new ArrayList<>());
        Doctor dr1 = new Doctor(1L,"Oliver Dragojevic","123456",medicalSpecial2,new HashSet<>());
        d1.setDiaId(1L);
        Report report = new Report(8L,"Slabiji vid i zamucenje vida",p1,d1,null,"Testovi preko aparata za gledanje sociva i utvrdio je dijagnozu kataratke");

        Mockito.when(reportRepository.save(report)).thenReturn(report);

        Mockito.when(doctorRepository.findById(dr1.getDocId())).thenReturn(Optional.of(dr1));

        Mockito.when(patientRepository.findById(p1.getpId())).thenReturn(Optional.of(p1));

        Mockito.when(diagnosisRepository.findById(d1.getDiaId())).thenReturn(Optional.of(d1));

        Report result = reportService.doctorNewReport(dr1.getDocId(),report,p1.getpId(),d1.getDiaId());
        report.setDoctor(dr1);
        verify(reportRepository,times(1)).save(report);
        assertEquals(result,report);


    }

    @Test
    void doctorNewReportExceptionDoctor() {
        Diagnosis d1 = new Diagnosis("Katarakta",3350,"H0701");
        MedicalSpecial medicalSpecial2 = new MedicalSpecial(153L,"Stomatologija",null);
        Patient p1 = new Patient(1L,"Slavko Petronijevic",new Date(),"1234567891011","Belgrade",new ArrayList<>());
        Doctor dr1 = new Doctor(1L,"Oliver Dragojevic","123456",medicalSpecial2,new HashSet<>());
        d1.setDiaId(1L);
        Report report = new Report(8L,"Slabiji vid i zamucenje vida",p1,d1,null,"Testovi preko aparata za gledanje sociva i utvrdio je dijagnozu kataratke");

        Mockito.when(doctorRepository.findById(dr1.getDocId())).thenReturn(Optional.of(dr1));

        Mockito.when(doctorRepository.findById(dr1.getDocId())).thenReturn(Optional.empty());

        Assertions.assertThrows(Exception.class,()->{
           reportService.doctorNewReport(dr1.getDocId(),report,p1.getpId(),d1.getDiaId());
        });


    }

    @Test
    void doctorNewReportExceptionPatient() {
        Diagnosis d1 = new Diagnosis("Katarakta",3350,"H0701");
        MedicalSpecial medicalSpecial2 = new MedicalSpecial(153L,"Stomatologija",null);
        Patient p1 = new Patient(1L,"Slavko Petronijevic",new Date(),"1234567891011","Belgrade",new ArrayList<>());
        Doctor dr1 = new Doctor(1L,"Oliver Dragojevic","123456",medicalSpecial2,new HashSet<>());
        d1.setDiaId(1L);
        Report report = new Report(8L,"Slabiji vid i zamucenje vida",p1,d1,null,"Testovi preko aparata za gledanje sociva i utvrdio je dijagnozu kataratke");

        Mockito.when(doctorRepository.findById(dr1.getDocId())).thenReturn(Optional.of(dr1));

        Mockito.when(patientRepository.findById(p1.getpId())).thenReturn(Optional.empty());

        Assertions.assertThrows(Exception.class,()->{
            reportService.doctorNewReport(dr1.getDocId(),report,p1.getpId(),d1.getDiaId());
        });


    }

    @Test
    void doctorNewReportExceptionDiagnosis() {
        Diagnosis d1 = new Diagnosis("Katarakta",3350,"H0701");
        MedicalSpecial medicalSpecial2 = new MedicalSpecial(153L,"Stomatologija",null);
        Patient p1 = new Patient(1L,"Slavko Petronijevic",new Date(),"1234567891011","Belgrade",new ArrayList<>());
        Doctor dr1 = new Doctor(1L,"Oliver Dragojevic","123456",medicalSpecial2,new HashSet<>());
        d1.setDiaId(1L);
        Report report = new Report(8L,"Slabiji vid i zamucenje vida",p1,d1,null,"Testovi preko aparata za gledanje sociva i utvrdio je dijagnozu kataratke");

        Mockito.when(doctorRepository.findById(dr1.getDocId())).thenReturn(Optional.of(dr1));
        Mockito.when(patientRepository.findById(p1.getpId())).thenReturn(Optional.of(p1));

        Mockito.when(diagnosisRepository.findById(d1.getDiaId())).thenReturn(Optional.empty());

        Assertions.assertThrows(Exception.class,()->{
            reportService.doctorNewReport(dr1.getDocId(),report,p1.getpId(),d1.getDiaId());
        });


    }

    @Test
    void getAllReportsFromDoctor() throws Exception {

        Diagnosis d1 = new Diagnosis("Katarakta",3350,"H0701");
        MedicalSpecial medicalSpecial2 = new MedicalSpecial(153L,"Stomatologija",null);
        Patient p1 = new Patient(1L,"Slavko Petronijevic",new Date(),"1234567891011","Belgrade",new ArrayList<>());
        Doctor dr1 = new Doctor(1L,"Oliver Dragojevic","123456",medicalSpecial2,new HashSet<>());
        d1.setDiaId(1L);
        Report report1 = new Report(8L,"Slabiji vid i zamucenje vida",p1,d1,dr1,"Testovi preko aparata za gledanje sociva i utvrdio je dijagnozu kataratke");
        Report report2 = new Report(9L,"Bol u vratu",p1,d1,dr1,"Testovi preko aparata vrat");
        Set<Report> reports = new HashSet<>();
        reports.add(report1);
        reports.add(report2);

        Mockito.when(doctorRepository.findById(dr1.getDocId())).thenReturn(Optional.of(dr1));

        Mockito.when(reportRepository.findByDoctor(dr1)).thenReturn(reports);

        Set<Report> result = reportService.getAllReportsFromDoctor(dr1.getDocId());

        assertEquals(result,reports);
        verify(reportRepository,times(1)).findByDoctor(dr1);
        verify(doctorRepository,times(1)).findById(dr1.getDocId());


    }

    @Test
    void getAllReportsFromDoctorException() {

        Diagnosis d1 = new Diagnosis("Katarakta",3350,"H0701");
        MedicalSpecial medicalSpecial2 = new MedicalSpecial(153L,"Stomatologija",null);
        Patient p1 = new Patient(1L,"Slavko Petronijevic",new Date(),"1234567891011","Belgrade",new ArrayList<>());
        Doctor dr1 = new Doctor(1L,"Oliver Dragojevic","123456",medicalSpecial2,new HashSet<>());
        d1.setDiaId(1L);
        Report report1 = new Report(8L,"Slabiji vid i zamucenje vida",p1,d1,dr1,"Testovi preko aparata za gledanje sociva i utvrdio je dijagnozu kataratke");
        Report report2 = new Report(9L,"Bol u vratu",p1,d1,dr1,"Testovi preko aparata vrat");
        Set<Report> reports = new HashSet<>();
        reports.add(report1);
        reports.add(report2);

        Mockito.when(doctorRepository.findById(dr1.getDocId())).thenReturn(Optional.empty());

//        Mockito.when(reportRepository.findByDoctor(dr1)).thenReturn(reports);

        Assertions.assertThrows(Exception.class,()->{
           reportService.getAllReportsFromDoctor(dr1.getDocId());
        });


    }

    @Test
    void getAllReportsFromPatient() throws Exception {

        Diagnosis d1 = new Diagnosis("Katarakta",3350,"H0701");
        MedicalSpecial medicalSpecial2 = new MedicalSpecial(153L,"Stomatologija",null);
        Patient p1 = new Patient(1L,"Slavko Petronijevic",new Date(),"1234567891011","Belgrade",new ArrayList<>());
        Doctor dr1 = new Doctor(1L,"Oliver Dragojevic","123456",medicalSpecial2,new HashSet<>());
        d1.setDiaId(1L);
        Report report1 = new Report(8L,"Slabiji vid i zamucenje vida",p1,d1,null,"Testovi preko aparata za gledanje sociva i utvrdio je dijagnozu kataratke");
        Report report2 = new Report(9L,"Bol u vratu",p1,d1,null,"Testovi preko aparata vrat");
        Set<Report> reports = new HashSet<>();
        reports.add(report1);
        reports.add(report2);



        Mockito.when(reportRepository.findByPatient(p1)).thenReturn(reports);

        Mockito.when(patientRepository.findById(p1.getpId())).thenReturn(Optional.of(p1));

        Set<Report> result = reportService.getAllReportsFromPatient(p1.getpId());

        assertEquals(result,reports);
        verify(reportRepository,times(1)).findByPatient(p1);


    }

    @Test
    void getAllReportsFromPatientException(){

        Diagnosis d1 = new Diagnosis("Katarakta",3350,"H0701");
        MedicalSpecial medicalSpecial2 = new MedicalSpecial(153L,"Stomatologija",null);
        Patient p1 = new Patient(1L,"Slavko Petronijevic",new Date(),"1234567891011","Belgrade",new ArrayList<>());
        Doctor dr1 = new Doctor(1L,"Oliver Dragojevic","123456",medicalSpecial2,new HashSet<>());
        d1.setDiaId(1L);
        Report report1 = new Report(8L,"Slabiji vid i zamucenje vida",p1,d1,null,"Testovi preko aparata za gledanje sociva i utvrdio je dijagnozu kataratke");
        Report report2 = new Report(9L,"Bol u vratu",p1,d1,null,"Testovi preko aparata vrat");
        Set<Report> reports = new HashSet<>();
        reports.add(report1);
        reports.add(report2);

        Mockito.when(patientRepository.findById(p1.getpId())).thenReturn(Optional.empty());

        Assertions.assertThrows(Exception.class,()->{
            reportService.getAllReportsFromPatient(p1.getpId());
        });

    }

}