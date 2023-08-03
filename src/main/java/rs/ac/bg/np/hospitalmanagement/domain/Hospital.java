package rs.ac.bg.np.hospitalmanagement.domain;

import jakarta.persistence.*;

import java.util.Set;

@Entity
public class Hospital {

    @Id
    @GeneratedValue
    private long hospitalId;
    private String name;
    private String address;
    private String city;

    @OneToMany(mappedBy = "hospital")
    private Set<MedicalSpecial> medicalSpecials;

    public Hospital(long hospitalId, String name, String address, String city, Set<MedicalSpecial> medicalSpecials) {
        this.hospitalId = hospitalId;
        this.name = name;
        this.address = address;
        this.city = city;
        this.medicalSpecials = medicalSpecials;
    }

    public Hospital() {
    }

    public Set<MedicalSpecial> getMedicalSpecials() {
        return medicalSpecials;
    }

    public void setMedicalSpecials(Set<MedicalSpecial> medicalSpecials) {
        this.medicalSpecials = medicalSpecials;
    }


    public long getHospitalId() {
        return hospitalId;
    }

    public void setHospitalId(long hospitalId) {
        this.hospitalId = hospitalId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }
}
