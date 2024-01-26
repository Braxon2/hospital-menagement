package rs.ac.bg.np.hospitalmanagement.domain;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.OneToOne;

/**
 * Entitet u bazi podataka / domenska klasa recepta.
 *
 * Koristi se da predstavi direktnu vezu sa tabelom "recipe" iz baze podataka ali i kao domenski objekat.
 * Pamti sve bitne informacije o receptu kao sto su id recepta, upustvo doze, lek i izjavu
 *
 * @author Dusan
 */

@Entity
public class Recipe {
    /**
     * Auto generisan identifikator recepta koji je unikatan i identifikuje objekat u bazi podataka
     */
    @Id
    @GeneratedValue
    private long recid;
    /**
     * Upustvo doziranja koji je tipa String
     */
    private String doses;
    /**
     * Lek koji je tipa Medicine
     */
    @OneToOne
    private Medicine medicine;
    /**
     * Izjava koji je tipa Report
     */
    @OneToOne
    private Report report;
    /**
     * Bez parametarski konstruktor koji inicijalizuje objekat klase Recipe
     */
    public Recipe() {
    }
    /**
     * Konstruktor koji inicijalizuje objekat klase Recipe sa zadatim vrednostima
     *
     * @param recid id recepta
     * @param doses upustvo doziranja
     * @param medicine lek koji se prepisuje
     * @param report izjava iz koje potice
     */
    public Recipe(long recid, String doses, Medicine medicine, Report report) {
        this.recid = recid;
        this.doses = doses;
        this.medicine = medicine;
        this.report = report;
    }
    /**
     * Vraca id recepta
     *
     * @return recid kao long
     */
    public long getRecid() {
        return recid;
    }
    /**
     * Vraca upustvo doziranja
     *
     * @return doses kao String
     */
    public String getDoses() {
        return doses;
    }
    /**
     *Postavlja doses na zadatu vrednost
     *
     * @param  doses kao upsutvo za doziranje
     */
    public void setDoses(String doses) {
        this.doses = doses;
    }
    /**
     *Postavlja recid na zadatu vrednost
     *
     * @param  recid kao id za recept
     */
    public void setRecid(long recid) {
        this.recid = recid;
    }

    /**
     * Vraca lek
     *
     * @return medicine kao Medicine
     */
    public Medicine getMedicine() {
        return medicine;
    }
    /**
     *Postavlja medicine na zadatu vrednost
     *
     * @param  medicine kao lek koji ce se prepisati
     */
    public void setMedicine(Medicine medicine) {
        this.medicine = medicine;
    }
    /**
     * Vraca izvestaj
     *
     * @return report kao Report
     */
    public Report getReport() {
        return report;
    }
    /**
     *Postavlja report na zadatu vrednost
     *
     * @param  report kao izvestaj na osnovu koje ce se napisati recept
     */
    public void setReport(Report report) {
        this.report = report;
    }

    //    private Doctor approvedByDoctor;
//
//    private Patient patient;

}
