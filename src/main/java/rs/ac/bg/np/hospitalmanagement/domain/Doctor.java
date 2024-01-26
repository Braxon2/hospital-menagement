package rs.ac.bg.np.hospitalmanagement.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;

import java.util.Set;

/**
 * Entitet u bazi podataka / domenska klasa doktora.
 *
 * Koristi se da predstavi direktnu vezu sa tabelom "doctor" iz baze podataka ali i kao domenski objekat.
 * Pamti sve bitne informacije o doktoru kao sto su id doktora,ime doktora,broj licence,
 * kadar kome pripada i skup izvestaja koje je on napisao
 *
 * @author Dusan
 */

@Entity
public class Doctor {
    /**
     * Auto generisan identifikator doktora koji je unikatan i identifikuje objekat u bazi podataka
     */
    @Id
    @GeneratedValue
    private long docId;
    /**
     * Naziv koji je tipa String
     */
    private String name;
    /**
     * Broj licnence koji je tipa String i duzine 6
     */
    private String licenceNumber;
    /**
     * Spijalnost tj kadar kome pripada koji je tipa MedicalSpecial
     */
    @ManyToOne//gotovo
    private MedicalSpecial medicalSpecial;
    /**
     * Skup izvestaja koji je napisao doktor
     */
    @OneToMany(mappedBy = "doctor")//gotovo
    @JsonIgnore
    private Set<Report> reports;
    /**
     * Konstruktor koji inicijalizuje objekat klase Doctor sa zadatim vrednostima
     *
     * @param docId id doktora
     * @param name ime doktora
     * @param licenceNumber broj licnence doktora
     * @param medicalSpecial kadar kome pripada
     * @param reports skup izvestaja koje je on prepisao
     */
    public Doctor(long docId, String name, String licenceNumber, MedicalSpecial medicalSpecial, Set<Report> reports) {
        this.docId = docId;
        this.name = name;
        this.licenceNumber = licenceNumber;
        this.medicalSpecial = medicalSpecial;
        this.reports = reports;
    }
    /**
     * Vraca broj licence doktora
     *
     * @return licenceNumber kao String
     */
    public String getLicenceNumber() {
        return licenceNumber;
    }
    /**
     *Postavlja licenceNumber na zadatu vrednost
     *
     * @param  licenceNumber kao broj licence
     */
    public void setLicenceNumber(String licenceNumber) {
        this.licenceNumber = licenceNumber;
    }
    /**
     * Bez parametarski konstruktor koji inicijalizuje objekat klase Doctor
     */
    public Doctor() {
    }
    /**
     * Vraca id doktora
     *
     * @return docid kao long
     */
    public long getDocId() {
        return docId;
    }
    /**
     *Postavlja docid na zadatu vrednost
     *
     * @param  docId kao id doktora
     */
    public void setDocId(long docId) {
        this.docId = docId;
    }
    /**
     * Vraca ime doktora
     *
     * @return name kao String
     */
    public String getName() {
        return name;
    }
    /**
     *Postavlja name na zadatu vrednost
     *
     * @param  name kao ime doktora
     */
    public void setName(String name) {
        this.name = name;
    }
    /**
     * Vraca kadar doktora
     *
     * @return medicalSpecial kao MedicalSpecial
     */
    public MedicalSpecial getMedicalSpecial() {
        return medicalSpecial;
    }
    /**
     *Postavlja medicalSpecial na zadatu vrednost
     *
     * @param  medicalSpecial kao kadar doktora
     */
    public void setMedicalSpecial(MedicalSpecial medicalSpecial) {
        this.medicalSpecial = medicalSpecial;
    }
    /**
     * Vraca skup izvestaja koji je doktor napisao
     *
     * @return reports kao skup izvestaja
     */
    public Set<Report> getReports() {
        return reports;
    }
    /**
     *Postavlja reports na zadatu vrednost
     *
     * @param  reports kao ime doktora
     */
    public void setReports(Set<Report> reports) {
        this.reports = reports;
    }
}
