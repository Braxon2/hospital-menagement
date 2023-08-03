package rs.ac.bg.np.hospitalmanagement.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;

import java.util.Set;

@Entity
public class Doctor {

    @Id
    @GeneratedValue
    private long docId;
    private String name;
    @ManyToOne//gotovo
    private MedicalSpecial medicalSpecial;

    @OneToMany(mappedBy = "doctor")//gotovo
    @JsonIgnore
    private Set<Report> reports;

    public Doctor(long docId, String name, MedicalSpecial medicalSpecial, Set<Report> reports) {
        this.docId = docId;
        this.name = name;
        this.medicalSpecial = medicalSpecial;
        this.reports = reports;
    }

    public Doctor() {
    }

    public long getDocId() {
        return docId;
    }

    public void setDocId(long docId) {
        this.docId = docId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public MedicalSpecial getMedicalSpecial() {
        return medicalSpecial;
    }

    public void setMedicalSpecial(MedicalSpecial medicalSpecial) {
        this.medicalSpecial = medicalSpecial;
    }

    public Set<Report> getReports() {
        return reports;
    }

    public void setReports(Set<Report> reports) {
        this.reports = reports;
    }
}
