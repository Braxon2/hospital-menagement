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
        setRecid(recid);
        setDoses(doses);
        setMedicine(medicine);
        setReport(report);
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
     * @throws IllegalArgumentException ako je doza null ili prazan string
     */
    public void setDoses(String doses) {
        if(doses != null && !doses.isEmpty()) {
            this.doses = doses;
        }else{
            throw new IllegalArgumentException("Doza ne sme biti null niti prazan string");
        }
    }
    /**
     *Postavlja recid na zadatu vrednost
     *
     * @param  recid kao id za recept
     * @throws IllegalArgumentException ako je id manji ili jednak nuli
     */
    public void setRecid(long recid) {
        if(recid>0) {
            this.recid = recid;
        }else {
            throw new IllegalArgumentException("Id recepta treba da bude veci od 0");
        }
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
     * @throws IllegalArgumentException ako je medicine null
     */
    public void setMedicine(Medicine medicine) {
        if(medicine != null) {
            this.medicine = medicine;
        }else{
            throw new IllegalArgumentException("Medicine ne moze biti null!");
        }
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
