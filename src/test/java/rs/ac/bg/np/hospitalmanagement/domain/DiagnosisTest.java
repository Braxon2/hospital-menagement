package rs.ac.bg.np.hospitalmanagement.domain;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

class DiagnosisTest {

    private Diagnosis diagnosis;

    @BeforeEach
    void setUp(){
        diagnosis = new Diagnosis();
    }

    @AfterEach
    void tearDown(){
        diagnosis = null;
    }

    @Test
    void setIdDia(){
        diagnosis.setDiaId(1L);
        assertEquals(1L,diagnosis.getDiaId());
    }

    @Test
    void setIdDiaException(){
        assertThrows(IllegalArgumentException.class,()->diagnosis.setDiaId(0L));
    }

    @Test
    void setIdDiaNegative(){
        assertThrows(IllegalArgumentException.class,()->diagnosis.setDiaId(-4L));
    }

    @Test
    void setName(){
        diagnosis.setName("Laringitis");
        assertEquals("Laringitis",diagnosis.getName());
    }

    @Test
    void setNameNull(){
        assertThrows(IllegalArgumentException.class,()->diagnosis.setName(null));
    }

    @Test
    void setNameEmpty(){
        assertThrows(IllegalArgumentException.class,()->diagnosis.setName(""));
    }

    @Test
    void setCodeOfDiagnosis(){
        diagnosis.setCodeOfDiganosis(1230L);
        assertEquals(1230L,diagnosis.getCodeOfDiganosis());
    }

    @Test
    void setCodeOfDiagnosisOutOfBound(){
        assertThrows(IllegalArgumentException.class,()-> diagnosis.setCodeOfDiganosis(120L));
    }
    @Test
    void setLabel(){
        diagnosis.setLabel("H0101");
        assertEquals("H0101",diagnosis.getLabel());
    }

    @Test
    void setLabelNull(){
        assertThrows(IllegalArgumentException.class,()-> diagnosis.setLabel(null));
    }

    @Test
    void setLabelEmpty(){
        assertThrows(IllegalArgumentException.class,()-> diagnosis.setLabel(""));
    }

    @Test
    void setLabelNotUpperFirstLetter(){
        assertThrows(IllegalArgumentException.class,()-> diagnosis.setLabel("h0234"));
    }

    @Test
    void setLabelLength(){
        assertThrows(IllegalArgumentException.class,()-> diagnosis.setLabel("H0234555"));
    }

    @Test
    void setReports(){
        List<Report> reports = new ArrayList<>();
        reports.add(new Report(1L,"asas",null,null,null,"sasasas"));
        diagnosis.setReports(reports);
        assertEquals(reports,diagnosis.getReports());
    }

    @ParameterizedTest
    @CsvSource({
            "Laringitis,1230,H0101",
            "Katarakta,1231,H0102",
            "Laringitis,1232,H0103"
    })
    void testParametrizedContructor(String name,long code,String label){
        diagnosis = new Diagnosis(name,code,label);
        assertEquals(name, diagnosis.getName());
        assertEquals(code, diagnosis.getCodeOfDiganosis());
        assertEquals(label, diagnosis.getLabel());
    }


    @ParameterizedTest
    @CsvSource({
            "1,Laringitis,null",
            "2,Katarakta,null",
            "3,Laringitis,null"
    })
    void testParametrizedContructorSecond(long id,String name,String reports){
//        Report(long repid, String description, Patient patient, Diagnosis diagnosis, Doctor doctor, String clinicalFinding) {
        List<Report> reportList = new ArrayList<>();
        List<Report> result = reports.equals("null")?null:reportList;
//        reportList = reports.equals("null")?null:reportList;
        diagnosis = new Diagnosis(id,name,result);
        assertEquals(name, diagnosis.getName());
        assertEquals(id, diagnosis.getDiaId());
        assertEquals(result, diagnosis.getReports());
    }

    @Test
    void testToString(){
        Diagnosis d1 = new Diagnosis("Laringitis",1230,"H0101");
        assertTrue(d1.toString().contains("Laringitis"));
        assertTrue(d1.toString().contains("1230"));
        assertTrue(d1.toString().contains("H0101"));
    }

    @ParameterizedTest
    @CsvSource({
            "1,Laringitis,1230,H0101,1,Laringitis,1230,H0101",
            "1,Katarakta,1230,H0101,1,Katarakta,1230,H0101",
            "3,Bol u glavi,1200,H0102,3,Bol u glavi,1200,H0102"
    })
    void testParamatrizedConstructor3(long id1,String name1,long code1,String label1,
                    long id2,String name2,long code2,String label2){
        diagnosis = new Diagnosis(id1,name1,code1,label1,null);
        Diagnosis d1 = new Diagnosis(id2,name2,code2,label2,null);
        assertEquals(diagnosis,d1);
    }

    @ParameterizedTest
    @CsvSource({
            "1,Laringitis,1230,H0101,1,Laringitis,1230,H0101, true",
            "1,Laringitis,1230,H0101,2,Laringitis,1230,H0101, false",
            "2,Katarakta,1231,H0102,3,Bol u glavi,1231,H0102,false"
    })
    void testEquals(long id1,String name1,long code1,String label1,
                    long id2,String name2,long code2,String label2,boolean areEqual){
        diagnosis = new Diagnosis(id1,name1,code1,label1,null);
        Diagnosis d1 = new Diagnosis(id2,name2,code2,label2,null);
        assertEquals(diagnosis.equals(d1),areEqual);
    }


}