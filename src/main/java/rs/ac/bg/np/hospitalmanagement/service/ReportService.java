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

@Service
public class ReportService {

    @Autowired
    private ReportRepository reportRepository;

    @Autowired
    private DoctorRepository doctorRepository;

    @Autowired
    private PatientRepository patientRepository;

    @Autowired
    private DiagnosisRepository diagnosisRepository;

    public List<Report> getAll() {
        return reportRepository.findAll();
    }

    public Report getReport(long repid) throws Exception {
        Optional<Report> optionalReport = reportRepository.findById(repid);

        if(!optionalReport.isPresent()){
            throw new Exception("This report does not exist!!!");
        }

        return optionalReport.get();

    }

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

    public Set<Report> getAllReportsFromDoctor(long docid) throws Exception {

        Optional<Doctor> optionalDoctor = doctorRepository.findById(docid);

        if(!optionalDoctor.isPresent()){
            throw new Exception("This doctor does not exist!!!");
        }

        Set<Report> reports = reportRepository.findByDoctor(optionalDoctor.get());

        return reports;

    }

    public Set<Report> getAllReportsFromPatient(long pid) throws Exception {

        Optional<Patient> optionalPatient = patientRepository.findById(pid);

        if(!optionalPatient.isPresent()){
            throw new Exception("This patient does not exist!!!");
        }

        Set<Report> reports = reportRepository.findByPatient(optionalPatient.get());

        return reports;

    }
}
