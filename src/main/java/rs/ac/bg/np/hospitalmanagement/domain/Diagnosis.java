package rs.ac.bg.np.hospitalmanagement.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;

import java.util.List;
import java.util.Objects;

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
     * @param name naziv kadra
     * @param codeOfDiganosis kod dijagnoze
     * @param label sifra dijagnoze
     */
    public Diagnosis(String name, long codeOfDiganosis,String label) {
        setName(name);
        setCodeOfDiganosis(codeOfDiganosis);
        setLabel(label);
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
     * @throws IllegalArgumentException ako je kod za dijagnozu manji od 10000 ili veci od 999
     */
    public void setCodeOfDiganosis(long codeOfDiganosis) {
        if(codeOfDiganosis > 999 && codeOfDiganosis < 10000) {
            this.codeOfDiganosis = codeOfDiganosis;
        }else {
            throw new IllegalArgumentException("CodeOfDiagnosis must be greater than 99!");
        }
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
     * @throws IllegalArgumentException ako je sifra dijagnoze prazan string ili ako duzina nije jednaka 5 karaktera i
     * ako ne krece velikim pocetnim slvoom
     */
    public void setLabel(String label) {
        if(label != null && !label.isEmpty() && (label.charAt(0) >= 'A' && label.charAt(0)<='Z') && label.length()==5) {
            this.label = label;
        }else{
            throw new IllegalArgumentException("Sifra dijagnoze ne sme biti null ili prazan string, mora da krene sa velikim slovom i da mu je duzina 5!");
        }
    }
    /**
     * Konstruktor koji inicijalizuje objekat klase Diagnosis sa zadatim vrednostima
     *
     * @param diaId id dijagnoze
     * @param name naziv kadra
     * @param reports lista izvestaja
     */
    public Diagnosis(long diaId, String name, List<Report> reports) {
        setDiaId(diaId);
        setName(name);
        setReports(reports);
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
     * @throws IllegalArgumentException ako je Id dijagnoze manji ili jednak nuli
     */
    public void setDiaId(long diaId) {
        if(diaId>0) {
            this.diaId = diaId;
        }else{
            throw new IllegalArgumentException("Id dijagnoze mora biti veci od 0!!!");
        }
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
     *
     */
    public void setName(String name) {
        if(name != null && !name.isEmpty()) {
            this.name = name;
        }else{
            throw new IllegalArgumentException("Naziv dijagnoze ne sme biti null ili prazan string!!!");
        }
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

    /**
     * ToString metoda
     * @return Vrednost atributa dijagnoze kao String
     */
    @Override
    public String toString() {
        return "Diagnosis{" +
                "id=" + diaId +
                ", name='" + name + '\'' +
                ", codeOfDiagnosis='" + codeOfDiganosis + '\'' +
                ", label= "+label+
                '}';
    }

    /**
     * Poredi dva objekta dijagnoze
     * @param o objekat sa kojim se poredi
     * @return true ako su objekti isti ili ako su im vrednost iste, obrnto false vraca
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Diagnosis diagnosis = (Diagnosis) o;
        return diaId == diagnosis.diaId && codeOfDiganosis == diagnosis.codeOfDiganosis && name.equals(diagnosis.name) && label.equals(diagnosis.label);
    }

    /**
     * Vraca hash code vrdnost dijagnoze
     * @return int reprezentacija na osnovu svih atributa
     */
    @Override
    public int hashCode() {
        return Objects.hash(diaId, name, codeOfDiganosis, label);
    }

    /**
     *Konstruktor koji inicijalizuje objekat klase Diagnosis sa zadatim vrednostima
     *
     * @param diaId id dijagnoze
     * @param name naziv kadra
     * @param codeOfDiganosis kod dijagnoze
     * @param label sifra dijagnoze
     * @param reports lista izvestaja
     */
    public Diagnosis(long diaId, String name, long codeOfDiganosis, String label, List<Report> reports) {
        this.diaId = diaId;
        this.name = name;
        this.codeOfDiganosis = codeOfDiganosis;
        this.label = label;
        this.reports = reports;
    }
}
