package rs.ac.bg.np.hospitalmanagement.domain;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;


@Entity
public class Report {

    @Id
    @GeneratedValue
    private long repid;

    private String description;
    @ManyToOne//t
    private Patient patient;

    @ManyToOne//t
    private Diagnosis diagnosis;
    @ManyToOne//t
    private Doctor doctor;


    private String clinicalFinding;

    public Report(long repid, String description, Patient patient, Diagnosis diagnosis, Doctor doctor, String clinicalFinding) {
        this.repid = repid;
        this.description = description;
        this.patient = patient;
        this.diagnosis = diagnosis;
        this.doctor = doctor;
        this.clinicalFinding = clinicalFinding;
    }

    public Report() {
    }

    public long getRepid() {
        return repid;
    }

    public void setRepid(long repid) {
        this.repid = repid;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Patient getPatient() {
        return patient;
    }

    public void setPatient(Patient patient) {
        this.patient = patient;
    }

    public Diagnosis getDiagnosis() {
        return diagnosis;
    }

    public void setDiagnosis(Diagnosis diagnosis) {
        this.diagnosis = diagnosis;
    }

    public Doctor getDoctor() {
        return doctor;
    }

    public void setDoctor(Doctor doctor) {
        this.doctor = doctor;
    }

    public String getClinicalFinding() {
        return clinicalFinding;
    }

    public void setClinicalFinding(String clinicalFinding) {
        this.clinicalFinding = clinicalFinding;
    }
}
