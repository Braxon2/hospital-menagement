package rs.ac.bg.np.hospitalmanagement.domain;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;

@Entity
public class Hospital {

    @Id
    @GeneratedValue
    private long hospitalId;
    private String name;
    private String address;
    private String city;

    public Hospital(long hospitalId, String name, String address, String city) {
        this.hospitalId = hospitalId;
        this.name = name;
        this.address = address;
        this.city = city;
    }

    public Hospital() {
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
