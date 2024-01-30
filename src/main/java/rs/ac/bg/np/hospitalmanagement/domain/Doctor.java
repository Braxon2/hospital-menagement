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
        setDocId(docId);
        setName(name);
        setLicenceNumber(licenceNumber);
        setMedicalSpecial(medicalSpecial);
        setReports(reports);
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
     * @throws IllegalArgumentException ako je licneca null,prazan string ili duzina karaktera licence nije jednak 6
     */
    public void setLicenceNumber(String licenceNumber) {
        if((licenceNumber != null && !licenceNumber.isEmpty()) && licenceNumber.length() == 6) {
            this.licenceNumber = licenceNumber;
        }else{
            throw new IllegalArgumentException("Broj licence ne sme biti null niti prazan string i duzina licence mora biti jedna 6!");
        }
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
     * @throws IllegalArgumentException ako je id doktora 0 ili manje od 0
     */
    public void setDocId(long docId) {
        if(docId > 0) {
            this.docId = docId;
        }else{
            throw new IllegalArgumentException("Id doktora ne sme biti nula ili manje od nule!");
        }
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
     * @throws IllegalArgumentException ako je ime doktora null ili prazan string
     */
    public void setName(String name) {
        if(name != null && !name.isEmpty()) {
            this.name = name;
        }else{
            throw new IllegalArgumentException("Ime doktora ne sme biti null niti prazan string!");
        }
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
     * @throws IllegalArgumentException ako je medicalSpecial null
     */
    public void setMedicalSpecial(MedicalSpecial medicalSpecial) {
        if(medicalSpecial != null) {
            this.medicalSpecial = medicalSpecial;
        }else{
            throw new IllegalArgumentException("MedicalSpecial ne sme biti null!!!");
        }
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
