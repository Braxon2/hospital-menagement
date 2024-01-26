package rs.ac.bg.np.hospitalmanagement.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;

import java.util.Date;
import java.util.List;

/**
 * Entitet u bazi podataka / domenska klasa pacijenta.
 *
 * Koristi se da predstavi direktnu vezu sa tabelom "doctor" iz baze podataka ali i kao domenski objekat.
 * Pamti sve bitne informacije o pacijentu kao sto su id pacijenta, ime, datum rodjenja, jmbg,
 * rezidencija gde zivi i listu izvestaja tj njegova istorija bolesti
 *
 * @author Dusan
 */

@Entity
public class Patient {
    /**
     * Auto generisan identifikator pacijenta koji je unikatan i identifikuje objekat u bazi podataka
     */
    @Id
    @GeneratedValue
    private long pId;
    /**
     * Naziv koji je tipa String
     */
    private String name;
    /**
     * Datum koji je tipa Date
     */
    private Date bornDate;
    /**
     * Jmbg koji je tipa String
     */
    private String jmbg;
    /**
     * Rezidencija koji je tipa String
     */
    private String residence;
    /**
     * Lista izvestaja koji pacijent ima
     */
    @OneToMany(mappedBy = "patient")
    @JsonIgnore
    private List<Report> reports;
    /**
     * Konstruktor koji inicijalizuje objekat klase Doctor sa zadatim vrednostima
     *
     * @param pId id pacijenta
     * @param name ime pacijenta
     * @param bornDate datum rodjenja
     * @param jmbg jmbg pacijenta
     * @param reports skup izvestaja koje pacijent ima
     */
    public Patient(long pId, String name, Date bornDate, String jmbg, String residence, List<Report> reports) {
        this.pId = pId;
        this.name = name;
        this.bornDate = bornDate;
        this.jmbg = jmbg;
        this.residence = residence;
        this.reports = reports;
    }
    /**
     * Vraca jmbg pacijenta
     *
     * @return jmbg kao String
     */
    public String getJmbg() {
        return jmbg;
    }
    /**
     *Postavlja jmbg na zadatu vrednost
     *
     * @param  jmbg kao jmbg pacijenta
     */
    public void setJmbg(String jmbg) {
        this.jmbg = jmbg;
    }
    /**
     * Konstruktor koji inicijalizuje objekat klase Doctor sa zadatim vrednostima
     *
     * @param name ime pacijenta
     * @param bornDate datum rodjenja
     * @param jmbg jmbg pacijenta
     * @param residence rezidencija pacijenta
     */
    public Patient(String name, Date bornDate, String jmbg, String residence) {
        this.name = name;
        this.bornDate = bornDate;
        this.jmbg = jmbg;
        this.residence = residence;
    }
    /**
     * Bez parametarski konstruktor koji inicijalizuje objekat klase Patient
     */
    public Patient() {
    }
    /**
     * Vraca id pacijenta
     *
     * @return pid kao long
     */
    public long getpId() {
        return pId;
    }
    /**
     *Postavlja pid na zadatu vrednost
     *
     * @param  pId kao id pacijenta
     */
    public void setpId(long pId) {
        this.pId = pId;
    }
    /**
     * Vraca ime pacijenta
     *
     * @return name kao String
     */
    public String getName() {
        return name;
    }
    /**
     *Postavlja name na zadatu vrednost
     *
     * @param  name kao ime pacijenta
     */
    public void setName(String name) {
        this.name = name;
    }
    /**
     * Vraca datum rodjenja pacijenta
     *
     * @return bornDate kao Date
     */
    public Date getBornDate() {
        return bornDate;
    }
    /**
     *Postavlja bornDate na zadatu vrednost
     *
     * @param  bornDate kao datum rodjenja pacijenta
     */
    public void setBornDate(Date bornDate) {
        this.bornDate = bornDate;
    }
    /**
     * Vraca rezidenciju pacijenta
     *
     * @return residence kao String
     */
    public String getResidence() {
        return residence;
    }
    /**
     *Postavlja residence na zadatu vrednost
     *
     * @param  residence kao mesto rezidencije pacijenta
     */
    public void setResidence(String residence) {
        this.residence = residence;
    }
    /**
     * Vraca listu izvestaja koji taj pacijent ima
     *
     * @return listu izvestaja
     */
    public List<Report> getReports() {
        return reports;
    }
    /**
     *Postavlja reports na zadatu vrednost
     *
     * @param  reports kao skup izvestaja
     */
    public void setReports(List<Report> reports) {
        this.reports = reports;
    }
}
