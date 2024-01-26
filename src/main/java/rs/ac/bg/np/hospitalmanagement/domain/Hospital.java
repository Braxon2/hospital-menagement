package rs.ac.bg.np.hospitalmanagement.domain;

import jakarta.persistence.*;

import java.util.Set;

/**
 * Entitet u bazi podataka / domenska klasa bolnice.
 *
 * Koristi se da predstavi direktnu vezu sa tabelom "hospital" iz baze podataka ali i kao domenski objekat.
 * Pamti sve bitne informacije o bolnici kao sto su naziv, adresu, grad i hospitalId .
 *
 * @author Dusan
 */

@Entity
public class Hospital {

    /**
     * Auto generisan identifikator bolnice koji je unikatan i identifikuje objekat u bazi podataka
     */
    @Id
    @GeneratedValue
    private long hospitalId;
    /**
     * Naziv bolnice koji je tipa String
     */
    private String name;
    /**
     * Adresa bolnice koji je tipa String
     */
    private String address;
    /**
     * Grad bolnice u kojem se nalazi, koji je tipa String
     */
    private String city;

    /**
     * Skup medicinskih specijalista ili kadrova koje bolnica ima
     */
    @OneToMany(mappedBy = "hospital")
    private Set<MedicalSpecial> medicalSpecials;
    /**
     * Konstruktor koji inicijalizuje objekat klase Hospital sa zadatim vrednostima
     *
     * @param hospitalId Id bolnice
     * @param name naziv bolnice
     * @param address adresa bolnice
     * @param  city grad u kojem se nalazi bolnica
     * @param medicalSpecials skup kadrova bolnice
     */
    public Hospital(long hospitalId, String name, String address, String city, Set<MedicalSpecial> medicalSpecials) {
        this.hospitalId = hospitalId;
        this.name = name;
        this.address = address;
        this.city = city;
        this.medicalSpecials = medicalSpecials;
    }

    /**
     * Bez parametarski konstruktor koji inicijalizuje objekat klase Hospital
     */
    public Hospital() {
    }
    /**
     * Vraca kadrove bolnice
     *
     * @return medicalSpecial kao skup kadrova bolnice
     */
    public Set<MedicalSpecial> getMedicalSpecials() {
        return medicalSpecials;
    }
    /**
     *Postavlja medicialSpecial na zadatu vrednost
     *
     * @param  medicalSpecials kao skup kadrova bolnice
     */
    public void setMedicalSpecials(Set<MedicalSpecial> medicalSpecials) {
        this.medicalSpecials = medicalSpecials;
    }

    /**
     * Vraca ID bolnice
     *
     * @return hospitalId kao long
     */
    public long getHospitalId() {
        return hospitalId;
    }
    /**
     *Postavlja hospitalId na zadatu vrednost
     *
     * @param hospitalId Id bolnice
     */
    public void setHospitalId(long hospitalId) {
        this.hospitalId = hospitalId;
    }
    /**
     * Vraca naziv bolnice
     *
     * @return name kao String
     */
    public String getName() {
        return name;
    }
    /**
     * Postavlja naziv bolnice na zadatu vrednost
     *
     * @param name naziv bolnice
     */
    public void setName(String name) {
        this.name = name;
    }
    /**
     * Vraca adresu bolnice
     *
     * @return address kao String
     */
    public String getAddress() {
        return address;
    }
    /**
     * Postavlja adresu bolnice na zadatu vrednost
     *
     * @param  address adresa bolnice
     */
    public void setAddress(String address) {
        this.address = address;
    }
    /**
     *Vraca grad u kome se nalazi bolnica
     *
     * @return city kao String
     */
    public String getCity() {
        return city;
    }
    /**
     * Postavlja grad na zadatu vrednost
     *
     * @param city grad u kome se nalazi bolnica
     */
    public void setCity(String city) {
        this.city = city;
    }
}
