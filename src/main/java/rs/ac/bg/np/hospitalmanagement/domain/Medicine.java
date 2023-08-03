package rs.ac.bg.np.hospitalmanagement.domain;

import jakarta.persistence.*;

@Entity
public class Medicine {

    @Id
    @GeneratedValue
    private long medId;
    private String name;
    private int weightPerPill;

    public Medicine(long medId, String name, int weightPerPill) {
        this.medId = medId;
        this.name = name;
        this.weightPerPill = weightPerPill;
    }

    public Medicine() {
    }


    public long getMedId() {
        return medId;
    }

    public void setMedId(long medId) {
        this.medId = medId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getWeightPerPill() {
        return weightPerPill;
    }

    public void setWeightPerPill(int weightPerPill) {
        this.weightPerPill = weightPerPill;
    }
}
