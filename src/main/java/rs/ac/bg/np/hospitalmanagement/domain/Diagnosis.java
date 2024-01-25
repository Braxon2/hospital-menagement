package rs.ac.bg.np.hospitalmanagement.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;

import java.util.List;

@Entity
public class Diagnosis {

    @Id
    @GeneratedValue
    private long diaId;

    private String name;

    private long codeOfDiganosis;

    private String label;

    @OneToMany(mappedBy = "diagnosis")
    @JsonIgnore
    private List<Report> reports;

    public Diagnosis() {
    }

    public Diagnosis(String name, long codeOfDiganosis,String label) {
        this.name = name;
        this.codeOfDiganosis = codeOfDiganosis;
        this.label = label;
    }

    public long getCodeOfDiganosis() {
        return codeOfDiganosis;
    }

    public void setCodeOfDiganosis(long codeOfDiganosis) {
        this.codeOfDiganosis = codeOfDiganosis;
    }

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public Diagnosis(long diaId, String name, List<Report> reports) {
        this.diaId = diaId;
        this.name = name;
        this.reports = reports;
    }

    public long getDiaId() {
        return diaId;
    }

    public void setDiaId(long diaId) {
        this.diaId = diaId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Report> getReports() {
        return reports;
    }

    public void setReports(List<Report> reports) {
        this.reports = reports;
    }
}
