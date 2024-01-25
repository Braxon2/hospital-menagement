package rs.ac.bg.np.hospitalmanagement.domain;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.OneToOne;

@Entity
public class Recipe {

    @Id
    @GeneratedValue
    private long recid;

    private String dose;

    @OneToOne
    private Medicine medicine;

    @OneToOne
    private Report report;

    public Recipe() {
    }

    public Recipe(long recid, String dose, Medicine medicine, Report report) {
        this.recid = recid;
        this.dose = dose;
        this.medicine = medicine;
        this.report = report;
    }

    public long getRecid() {
        return recid;
    }

    public void setRecid(long recid) {
        this.recid = recid;
    }

    public String getdose() {
        return dose;
    }

    public void setdose(String dose) {
        this.dose = dose;
    }

    public Medicine getMedicine() {
        return medicine;
    }

    public void setMedicine(Medicine medicine) {
        this.medicine = medicine;
    }

    public Report getReport() {
        return report;
    }

    public void setReport(Report report) {
        this.report = report;
    }

    //    private Doctor approvedByDoctor;
//
//    private Patient patient;

}
