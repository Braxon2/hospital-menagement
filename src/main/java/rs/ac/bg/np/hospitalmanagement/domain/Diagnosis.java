package rs.ac.bg.np.hospitalmanagement.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;

import java.util.List;

/**
 * Entitet u bazi podataka / domenska klasa dijagnoze.
 *
 * Koristi se da predstavi direktnu vezu sa tabelom "diagnosis" iz baze podataka ali i kao domenski objekat.
 * Pamti sve bitne informacije o dijagnozi kao sto su naziv, kod dijagnoze, sifru i id dijagnoze kao i listu izjava/reportova ukojoj se nalazi .
 *
 * @author Dusan
 */

@Entity
public class Diagnosis {
    /**
     * Auto generisan identifikator dijagnoze koji je unikatan i identifikuje objekat u bazi podataka
     */
    @Id
    @GeneratedValue
    private long diaId;
    /**
     * Naziv dijagnoze koji je tipa String
     */
    private String name;
    /**
     * Kod dijagnoze koji je tipa long
     */
    private long codeOfDiganosis;
    /**
     * Sifra dijagnoze koji je tipa String
     */
    private String label;

    /**
     * Lista izvestaja u kojim se nalazi dijagnoza
     */
    @OneToMany(mappedBy = "diagnosis")
    @JsonIgnore
    private List<Report> reports;
    /**
     * Bez parametarski konstruktor koji inicijalizuje objekat klase Diagnosis
     */
    public Diagnosis() {
    }
    /**
     * Konstruktor koji inicijalizuje objekat klase Diagnosis sa zadatim vrednostima
     *
     * @param codeOfDiganosis kod dijagnoze
     * @param name naziv kadra
     * @param label sifra dijagnoze
     */
    public Diagnosis(String name, long codeOfDiganosis,String label) {
        this.name = name;
        this.codeOfDiganosis = codeOfDiganosis;
        this.label = label;
    }
    /**
     * Vraca kod dijagnoze
     *
     * @return codeOfDiagnosis kao long
     */
    public long getCodeOfDiganosis() {
        return codeOfDiganosis;
    }
    /**
     *Postavlja codeOfDiagnosis na zadatu vrednost
     *
     * @param  codeOfDiganosis kao kod za dijagnozu
     */
    public void setCodeOfDiganosis(long codeOfDiganosis) {
        this.codeOfDiganosis = codeOfDiganosis;
    }
    /**
     * Vraca sifru dijagnoze
     *
     * @return label kao String
     */
    public String getLabel() {
        return label;
    }
    /**
     *Postavlja label na zadatu vrednost
     *
     * @param  label kao sifru za dijagnozu
     */
    public void setLabel(String label) {
        this.label = label;
    }
    /**
     * Konstruktor koji inicijalizuje objekat klase Diagnosis sa zadatim vrednostima
     *
     * @param diaId id dijagnoze
     * @param name naziv kadra
     * @param reports lista izvestaja
     */
    public Diagnosis(long diaId, String name, List<Report> reports) {
        this.diaId = diaId;
        this.name = name;
        this.reports = reports;
    }
    /**
     * Vraca ID id dijagnoze
     *
     * @return diaId kao long
     */
    public long getDiaId() {
        return diaId;
    }
    /**
     *Postavlja diaId na zadatu vrednost
     *
     * @param  diaId kao id kadra
     */
    public void setDiaId(long diaId) {
        this.diaId = diaId;
    }

    /**
     * Vraca naziv dijagnoze
     *
     * @return name kao String
     */
    public String getName() {
        return name;
    }
    /**
     *Postavlja name na zadatu vrednost
     *
     * @param  name kao naziv dijagnoze
     */
    public void setName(String name) {
        this.name = name;
    }
    /**
     * Vraca ID medicinskog specijaliste tj kadra
     *
     * @return reports kao lista izvestaja
     */
    public List<Report> getReports() {
        return reports;
    }
    /**
     *Postavlja reports na zadatu vrednost
     *
     * @param  reports kao lista izvestaja
     */
    public void setReports(List<Report> reports) {
        this.reports = reports;
    }
}
