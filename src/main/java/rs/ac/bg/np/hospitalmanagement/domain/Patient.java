package rs.ac.bg.np.hospitalmanagement.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;

import java.util.Date;
import java.util.List;
import java.util.Objects;

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
        setpId(pId);
        setName(name);
        setBornDate(bornDate);
        setJmbg(jmbg);
        setResidence(residence);
        setReports(reports);
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
     * @throws IllegalArgumentException ako je Jmbg  null ili prazan string  ili duzina nije 13
     */
    public void setJmbg(String jmbg) {
        if((jmbg != null && !jmbg.isEmpty()) && jmbg.length() == 13) {
            this.jmbg = jmbg;
        }else{
            throw new IllegalArgumentException("Jmbg ne sme biti null niti prazan string  i duzina mora biti 13");
        }
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
        setName(name);
        setBornDate(bornDate);
        setJmbg(jmbg);
        setResidence(residence);
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
     * @throws IllegalArgumentException ako je id manje ili jednak nuli
     */
    public void setpId(long pId) {
        if(pId>0){
        this.pId = pId;
        }else {
            throw new IllegalArgumentException("Id pacijenta ne sme biti manje ili jednak nuli!");
        }
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
     * @throws IllegalArgumentException ako je ime pacijenta null ili prazan string
     */
    public void setName(String name) {
        if(name != null && !name.isEmpty()) {
            this.name = name;
        }else{
            throw new IllegalArgumentException("Ime doktora ne sme biti null niti prazan string!");
        }
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
     * @throws IllegalArgumentException ako je bornDate null
     */
    public void setBornDate(Date bornDate) {
        if(bornDate != null) {
            this.bornDate = bornDate;
        }else{
            throw new IllegalArgumentException("Datum rodjenja ne sme biti null");
        }
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
     * @throws IllegalArgumentException ako je rezidencija null ili prazan String
     */
    public void setResidence(String residence) {
        if(residence != null && !residence.isEmpty()) {
            this.residence = residence;
        }else{
            throw new IllegalArgumentException("Rezidencija ne sme biti null niti prazan String");
        }
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

    /**
     * ToString metoda
     * @return Vrednost atributa pacijenta kao String
     */
    @Override
    public String toString() {
        return "Patient{" +
                "pId=" + pId +
                ", name='" + name + '\'' +
                ", bornDate=" + bornDate +
                ", jmbg='" + jmbg + '\'' +
                ", residence='" + residence + '\'' +
                '}';
    }

    /**
     * Poredi dva objekta pacijenta
     * @param o objekat sa kojim se poredi
     * @return true ako su objekti isti ili ako su im vrednost iste, obrnto false vraca
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Patient patient = (Patient) o;
        return pId == patient.pId && name.equals(patient.name) && bornDate.equals(patient.bornDate) && jmbg.equals(patient.jmbg) && residence.equals(patient.residence);
    }

    /**
     * Vraca hash code vrdnost pacijenta
     * @return int reprezentacija na osnovu svih atributa
     */
    @Override
    public int hashCode() {
        return Objects.hash(pId, name, bornDate, jmbg, residence);
    }
}
