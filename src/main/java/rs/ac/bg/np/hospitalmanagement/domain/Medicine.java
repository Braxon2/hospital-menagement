package rs.ac.bg.np.hospitalmanagement.domain;

import jakarta.persistence.*;

/**
 * Entitet u bazi podataka / domenska klasa leka.
 *
 * Koristi se da predstavi direktnu vezu sa tabelom "medicine" iz baze podataka ali i kao domenski objekat.
 * Pamti sve bitne informacije o leku kao sto su naziv, tezina po piluli i id leka.
 *
 * @author Dusan
 */
@Entity
public class Medicine {
    /**
     * Auto generisan identifikator leka koji je unikatan i identifikuje objekat u bazi podataka
     */
    @Id
    @GeneratedValue
    private long medId;
    /**
     * Naziv leka koji je tipa String
     */
    private String name;
    /**
     * Tezina po piluli koji je tipa integer
     */
    private int weightPerPill;
    /**
     * Konstruktor koji inicijalizuje objekat klase Medicine sa zadatim vrednostima
     *
     * @param medId id leka
     * @param name naziv kadra
     * @param weightPerPill tezina po piluli
     */
    public Medicine(long medId, String name, int weightPerPill) {
        this.medId = medId;
        this.name = name;
        this.weightPerPill = weightPerPill;
    }
    /**
     * Bez parametarski konstruktor koji inicijalizuje objekat klase Medicine
     */
    public Medicine() {
    }

    /**
     * Vraca id leka
     *
     * @return medId kao long
     */
    public long getMedId() {
        return medId;
    }
    /**
     *Postavlja medId na zadatu vrednost
     *
     * @param  medId kao id za lek
     */
    public void setMedId(long medId) {
        this.medId = medId;
    }
    /**
     * Vraca naziv leka
     *
     * @return name kao String
     */
    public String getName() {
        return name;
    }
    /**
     *Postavlja name na zadatu vrednost
     *
     * @param  name kao naziv za lek
     */
    public void setName(String name) {
        this.name = name;
    }
    /**
     * Vraca tezinu  po piluli
     *
     * @return weightPerPill kao integer
     */
    public int getWeightPerPill() {
        return weightPerPill;
    }
    /**
     *Postavlja weightPerPill na zadatu vrednost
     *
     * @param  weightPerPill kao tezina leka po piluli
     */
    public void setWeightPerPill(int weightPerPill) {
        this.weightPerPill = weightPerPill;
    }
}
