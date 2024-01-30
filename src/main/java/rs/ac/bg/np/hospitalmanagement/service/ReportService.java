package rs.ac.bg.np.hospitalmanagement.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import rs.ac.bg.np.hospitalmanagement.domain.Diagnosis;
import rs.ac.bg.np.hospitalmanagement.domain.Doctor;
import rs.ac.bg.np.hospitalmanagement.domain.Patient;
import rs.ac.bg.np.hospitalmanagement.domain.Report;
import rs.ac.bg.np.hospitalmanagement.repository.DiagnosisRepository;
import rs.ac.bg.np.hospitalmanagement.repository.DoctorRepository;
import rs.ac.bg.np.hospitalmanagement.repository.PatientRepository;
import rs.ac.bg.np.hospitalmanagement.repository.ReportRepository;

import java.util.List;
import java.util.Optional;
import java.util.Set;

/**
 * Sadrzi poslovnu logiku sa radom sa izjavom
 *
 * Klasa sluzi da manipulise, upravlja sa modelom i podacima vezanim sa izjavom
 * Omogucava pravljenje izjava, vracanje svih izjava iz baze kao i pojedinacnu izjavu,
 * vracanje svih izjave koje je napisao jedan lekar
 * i vracanje svih izjave koje jedan pacijent ima
 *
 * @author Dusan
 */
@Service
public class ReportService {

    /**
     * Broker baze podataka koji je posrednik ka tabeli Report
     */
    @Autowired
    private ReportRepository reportRepository;

    /**
     * Broker baze podataka koji je posrednik ka tabeli Doctor
     */
    @Autowired
    private DoctorRepository doctorRepository;

    /**
     * Broker baze podataka koji je posrednik ka tabeli Patient
     */
    @Autowired
    private PatientRepository patientRepository;

    /**
     * Broker baze podataka koji je posrednik ka tabeli Diagnosis
     */
    @Autowired
    private DiagnosisRepository diagnosisRepository;

    /**
     * Ova metoda vraca listu svih izjava
     *
     * @return listu svih izjave iz baze
     */
    public List<Report> getAll() {
        return reportRepository.findAll();
    }

    /**
     * Ova metoda vraca jednu izjavu sa prosledjenim id-jem
     *
     * @param repid id izjave koju cemo vratiti
     * @return izjavu iz baze sa prosledjenim id-jem
     * @throws Exception ukoliko u bazi ne posotoji ta izjava sa prosledjenim id-jem
     */
    public Report getReport(long repid) throws Exception {
        Optional<Report> optionalReport = reportRepository.findById(repid);

        if(!optionalReport.isPresent()){
            throw new Exception("This report does not exist!!!");
        }

        return optionalReport.get();

    }

    /**
     * Ova metoda sluzi za pravljenje izjave od strane lekara
     *
     * @param docid id lekara koji pise izjavu
     * @param report izjava koja se popunjava
     * @param pid id pacijenta za koga je vezana ova izjava
     * @param did id dijagnoze koje ce se upotrebiti za ovu izjavu
     * @return perzistirana izjava
     * @throws Exception ukoliko u bazi ne posotji pacijent,doktor  ili dijagnoza sa prosledjenim id-jem
     */
    public Report doctorNewReport(long docid, Report report, long pid, long did) throws Exception {

        Optional<Doctor> optionalDoctor = doctorRepository.findById(docid);

        if(!optionalDoctor.isPresent()){
            throw new Exception("This doctor does not exist!!!");
        }

        Optional<Patient> optionalPatient = patientRepository.findById(pid);

        if(!optionalPatient.isPresent()){
            throw new Exception("This patient does not exist!!!");
        }

        Optional<Diagnosis> optionalDiagnosis = diagnosisRepository.findById(did);

        if(!optionalDiagnosis.isPresent()){
            throw new Exception("This diagnosis does not exist!!!");
        }

        //vadjenje vrednosti iz optionala
        Diagnosis diagnosis = optionalDiagnosis.get();
        Patient patient = optionalPatient.get();
        Doctor doctor = optionalDoctor.get();

        //setovanje vrednosti za report
        report.setDiagnosis(diagnosis);
        report.setPatient(patient);
        report.setDoctor(doctor);

        //setovanje report kod dokotora
        Set<Report> reportSet = doctor.getReports();
        reportSet.add(report);
        doctor.setReports(reportSet);

        //setovanje reporta kod pacijenta
        List<Report> patientReports = patient.getReports();
        patientReports.add(report);
        patient.setReports(patientReports);

        patientRepository.save(patient);
        doctorRepository.save(doctor);
        return reportRepository.save(report);

    }

    /**
     * Ova metoda vraca sve izjave koje je napisao jedan lekar
     *
     * @param docid id lekara koji je pisao izjave
     * @return skup izjava koje je pisao taj lekar
     * @throws Exception ukoliko ne posotji lekar u bazi sa prosledjenim id-jem
     */
    public Set<Report> getAllReportsFromDoctor(long docid) throws Exception {

        Optional<Doctor> optionalDoctor = doctorRepository.findById(docid);

        if(!optionalDoctor.isPresent()){
            throw new Exception("This doctor does not exist!!!");
        }

        Set<Report> reports = reportRepository.findByDoctor(optionalDoctor.get());

        return reports;

    }

    /**
     * Ova metoda vraca skup izjava koja pripada tom pacijentu
     *
     * @param pid id pacijenta koji ima te izjave
     * @return skup izjava koje pripadaju tom pacijentu
     * @throws Exception akopacijent ne postoji u bazi sa prosledjenim id-jem
     */
    public Set<Report> getAllReportsFromPatient(long pid) throws Exception {

        Optional<Patient> optionalPatient = patientRepository.findById(pid);

        if(!optionalPatient.isPresent()){
            throw new Exception("This patient does not exist!!!");
        }

        Set<Report> reports = reportRepository.findByPatient(optionalPatient.get());

        return reports;

    }
}
