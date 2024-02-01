package rs.ac.bg.np.hospitalmanagement.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;

import java.util.Objects;
import java.util.Set;

/**
 * Entitet u bazi podataka / domenska klasa kadra tj medicinske specijalizacije.
 *
 * Koristi se da predstavi direktnu vezu sa tabelom "medicalspecial" iz baze podataka ali i kao domenski objekat.
 * Pamti sve bitne informacije o kadru kao sto su naziv, bolnicu u kojoj se nalazi, skup clanova kadra i id kadra .
 *
 * @author Dusan
 */

@Entity
public class MedicalSpecial {
    /**
     * Auto generisan identifikator kadra koji je unikatan i identifikuje objekat u bazi podataka
     */
    @Id
    @GeneratedValue
    private long medSpecId;
    /**
     * Naziv kadra koji je tipa String
     */
    private String name;

    /**
     * Bolnicu u kojoj se nalazi taj kadar
     */
    @ManyToOne//gotovo
    private Hospital hospital;
    /**
     * Skup clanova ili lekara koji kadar ima
     */
    @JsonIgnore
    @OneToMany(mappedBy = "medicalSpecial")
    private Set<Doctor> members;
    /**
     * Konstruktor koji inicijalizuje objekat klase MedicalSpecial sa zadatim vrednostima
     *
     * @param medSpecId Id kadra
     * @param name naziv kadra
     * @param members clanovi tj lekari tog kadra
     */
    public MedicalSpecial(long medSpecId, String name, Set<Doctor> members) {
        setMedSpecId(medSpecId);
        setName(name);
        setMembers(members);
    }
    /**
     * Konstruktor koji inicijalizuje objekat klase MedicalSpecial sa zadatim vrednostima
     *
     * @param name naziv kadra
     */
    public MedicalSpecial(String name){
        setName(name);
    }
    /**
     * Bez parametarski konstruktor koji inicijalizuje objekat klase MedicalSpecial
     */
    public MedicalSpecial() {
    }
    /**
     * Vraca ID medicinskog specijaliste tj kadra
     *
     * @return medSpecId kao long
     */
    public long getMedSpecId() {
        return medSpecId;
    }
    /**
     *Postavlja medSpecId na zadatu vrednost
     *
     * @param medSpecId Id kadra
     * @throws IllegalArgumentException ako je id manje ili jednako nula
     */
    public void setMedSpecId(long medSpecId) {
        if(medSpecId > 0) {
            this.medSpecId = medSpecId;
        }else{
            throw new IllegalArgumentException("MedicalSpecial Id ne sme biti manje ili jednako nule!");
        }
    }

    /**
     * Vraca naziv kadra
     *
     * @return name kao String
     */
    public String getName() {
        return name;
    }
    /**
     *Postavlja name na zadatu vrednost
     *
     * @param name naziv kadra
     * @throws IllegalArgumentException ime kadra ne sme biti null ili prazan String
     */
    public void setName(String name) {
        if(name != null && !name.isEmpty()) {
            this.name = name;
        }else{
            throw new IllegalArgumentException("Ime kadra ne sme biti null niti prazan string!");
        }
    }
    /**
     * Vraca lekare koji pripadaju tom kadru
     *
     * @return members kao skup klase Doctor-a
     */
    public Set<Doctor> getMembers() {
        return members;
    }
    /**
     *Postavlja members na zadatu vrednost
     *
     * @param members clanovi tj lekari iz tog kadra
     */
    public void setMembers(Set<Doctor> members) {
        this.members = members;
    }
    /**
     * Vraca bolnicu u kojoj se nalazi kadar
     *
     * @return hospital kao bolnica u kojoj se nalazi
     */
    public Hospital getHospital() {
        return hospital;
    }
    /**
     *Postavlja hospotal na zadatu vrednost
     *
     * @param hospital bolnica u kojoj ce se nalaziti
     */
    public void setHospital(Hospital hospital) {
        this.hospital = hospital;
    }

    /**
     * ToString metoda
     * @return Vrednost atributa kadra kao String
     */
    @Override
    public String toString() {
        return "MedicalSpecial{" +
                "medSpecId=" + medSpecId +
                ", name='" + name + '\'' +
                '}';
    }

    /**
     * Poredi dva objekta kadra
     * @param o objekat sa kojim se poredi
     * @return true ako su objekti isti ili ako su im vrednost iste, obrnto false vraca
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        MedicalSpecial that = (MedicalSpecial) o;
        return medSpecId == that.medSpecId && name.equals(that.name);
    }

    /**
     * Vraca hash code vrdnost kadra
     * @return int reprezentacija na osnovu svih atributa
     */
    @Override
    public int hashCode() {
        return Objects.hash(medSpecId, name);
    }
}
