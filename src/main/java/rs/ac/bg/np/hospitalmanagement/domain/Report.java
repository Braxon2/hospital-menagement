package rs.ac.bg.np.hospitalmanagement.domain;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;

import java.util.Objects;

/**
 * Entitet u bazi podataka / domenska klasa izvestaja.
 *
 * Koristi se da predstavi direktnu vezu sa tabelom "recipe" iz baze podataka ali i kao domenski objekat.
 * Pamti sve bitne informacije o izvestaju kao sto su id izvestaja, opis problema,pacijenta, dijagnozu,
 * doktora koji pise izvestaj i klinicki nalaz
 *
 * @author Dusan
 */

@Entity
public class Report {
    /**
     * Auto generisan identifikator izvestaja koji je unikatan i identifikuje objekat u bazi podataka
     */
    @Id
    @GeneratedValue
    private long repid;
    /**
     * Opis problema koji je tipa String
     */
    private String description;
    /**
     * Pacijent koji je tipa Patient
     */
    @ManyToOne//t
    private Patient patient;
    /**
     * Dijagnoza koji je tipa Diagnosis
     */
    @ManyToOne//t
    private Diagnosis diagnosis;
    /**
     * Doctor koji je tipa Doctor
     */
    @ManyToOne//t
    private Doctor doctor;
    /**
     * Klinicki nalaz koji je tipa String
     */
    private String clinicalFinding;

    /**
     * Konstruktor koji inicijalizuje objekat klase Report sa zadatim vrednostima
     *
     * @param repid id izvestaja
     * @param description opis problema
     * @param patient pacijent za koga se pise izvestaj
     * @param diagnosis dijagnoza koja ce mu se utvrditi
     * @param doctor doktor koji pise ovu izjavu
     * @param clinicalFinding klicnki nalaz koji vrsi lekar
     */
    public Report(long repid, String description, Patient patient, Diagnosis diagnosis, Doctor doctor, String clinicalFinding) {
        setRepid(repid);
        setDescription(description);
        setPatient(patient);
        setDiagnosis(diagnosis);
        setDoctor(doctor);
        setClinicalFinding(clinicalFinding);
    }
    /**
     * Bez parametarski konstruktor koji inicijalizuje objekat klase Report
     */
    public Report() {
    }
    /**
     * Vraca id izvestaja
     *
     * @return repid kao long
     */
    public long getRepid() {
        return repid;
    }
    /**
     *Postavlja repid na zadatu vrednost
     *
     * @param  repid kao id izvestaja
     * @throws IllegalArgumentException ako je id manje ili jednak nuli
     */
    public void setRepid(long repid) {
        if(repid>0) {
            this.repid = repid;
        }else{
            throw new IllegalArgumentException("Id izjave ne sme biti manje ili jednak nuli");
        }
    }
    /**
     * Vraca opis probelema
     *
     * @return description kao String
     */
    public String getDescription() {
        return description;
    }
    /**
     *Postavlja description na zadatu vrednost
     *
     * @param  description kao opis problema
     * @throws IllegalArgumentException ako je opis null ili prazan string
     */
    public void setDescription(String description) {
        if(description != null && !description.isEmpty()) {
            this.description = description;
        }else{
            throw new IllegalArgumentException("Opis problema ne sme biti null ili prazan string");
        }
    }
    /**
     * Vraca pacijenta
     *
     * @return patient kao Patient
     */
    public Patient getPatient() {
        return patient;
    }
    /**
     *Postavlja patient na zadatu vrednost
     *
     * @param  patient kao pacijent na kome se vrsi pregled
     */
    public void setPatient(Patient patient) {
        this.patient = patient;
    }
    /**
     * Vraca dijagnozu
     *
     * @return diagnosis kao Diagnosis
     */
    public Diagnosis getDiagnosis() {
        return diagnosis;
    }
    /**
     *Postavlja diagnosis na zadatu vrednost
     *
     * @param  diagnosis kao dijagnoza koja se utvrdjuje
     */
    public void setDiagnosis(Diagnosis diagnosis) {
        this.diagnosis = diagnosis;
    }
    /**
     * Vraca doktora koji je napisao izvestaj
     *
     * @return doctor kao Doctor
     */
    public Doctor getDoctor() {
        return doctor;
    }
    /**
     *Postavlja doctor na zadatu vrednost
     *
     * @param  doctor kao doktor koji pise izvestaj
     */
    public void setDoctor(Doctor doctor) {
        this.doctor = doctor;
    }

    /**
     * Vraca klinicki nalaz
     *
     * @return clinicalFinding kao String
     */
    public String getClinicalFinding() {
        return clinicalFinding;
    }
    /**
     *Postavlja clinicalFinding na zadatu vrednost
     *
     * @param  clinicalFinding kao klinicki nalaz
     */
    public void setClinicalFinding(String clinicalFinding) {
        if(clinicalFinding!= null && !clinicalFinding.isEmpty()){
        this.clinicalFinding = clinicalFinding;
        }else{
            throw new IllegalArgumentException("Klinicki nalaz ne sme biti null ili prazan string!");
        }
    }

    /**
     * ToString metoda
     * @return Vrednost atributa izvestaja kao String
     */
    @Override
    public String toString() {
        return "Report{" +
                "repid=" + repid +
                ", description='" + description + '\'' +
                ", patient=" + patient +
                ", diagnosis=" + diagnosis +
                ", doctor=" + doctor +
                ", clinicalFinding='" + clinicalFinding + '\'' +
                '}';
    }

    /**
     * Poredi dva objekta izvestaja
     * @param o objekat sa kojim se poredi
     * @return true ako su objekti isti ili ako su im vrednost iste, obrnto false vraca
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Report report = (Report) o;
        return repid == report.repid && description.equals(report.description) && Objects.equals(patient, report.patient) && Objects.equals(diagnosis, report.diagnosis) && Objects.equals(doctor, report.doctor) && clinicalFinding.equals(report.clinicalFinding);
    }

    /**
     * Vraca hash code vrdnost izvestaja
     * @return int reprezentacija na osnovu svih atributa
     */
    @Override
    public int hashCode() {
        return Objects.hash(repid, description, patient, diagnosis, doctor, clinicalFinding);
    }
}
