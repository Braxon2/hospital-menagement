package rs.ac.bg.np.hospitalmanagement.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;

import java.util.Date;
import java.util.List;

@Entity
public class Patient {

    @Id
    @GeneratedValue
    private long pId;
    private String name;
    private Date bornDate;
    private String residence;

    @OneToMany(mappedBy = "patient")
    @JsonIgnore
    private List<Report> reports;

    public Patient(long pId, String name, Date bornDate, String residence, List<Report> reports) {
        this.pId = pId;
        this.name = name;
        this.bornDate = bornDate;
        this.residence = residence;
        this.reports = reports;
    }

    public Patient() {
    }

    public long getpId() {
        return pId;
    }

    public void setpId(long pId) {
        this.pId = pId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Date getBornDate() {
        return bornDate;
    }

    public void setBornDate(Date bornDate) {
        this.bornDate = bornDate;
    }

    public String getResidence() {
        return residence;
    }

    public void setResidence(String residence) {
        this.residence = residence;
    }

    public List<Report> getReports() {
        return reports;
    }

    public void setReports(List<Report> reports) {
        this.reports = reports;
    }
}
