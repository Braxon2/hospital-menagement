package rs.ac.bg.np.hospitalmanagement.domain;

import jakarta.persistence.*;

import java.util.Set;

@Entity
public class MedicalSpecial {

    @Id
    @GeneratedValue
    private long medSpecId;
    private String name;

    @ManyToOne//gotovo
    private Hospital hospital;

    @OneToMany(mappedBy = "medicalSpecial")
    private Set<Doctor> members;

    public MedicalSpecial(long medSpecId, String name, Set<Doctor> members) {
        this.medSpecId = medSpecId;
        this.name = name;
        this.members = members;
    }

    public MedicalSpecial() {
    }

    public long getMedSpecId() {
        return medSpecId;
    }

    public void setMedSpecId(long medSpecId) {
        this.medSpecId = medSpecId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Set<Doctor> getMembers() {
        return members;
    }

    public void setMembers(Set<Doctor> members) {
        this.members = members;
    }
}
