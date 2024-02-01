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
            "Laringitis,123,H101, true"
    })
    void testParametrizedContructor(String name,long code,String label){
        Diagnosis d1 = new Diagnosis(name,code,label);
        diagnosis.setLabel(label);
        diagnosis.setName(name);
        diagnosis.setCodeOfDiganosis(code);
        boolean tacno;
        if(d1.getLabel().equals(diagnosis.getLabel()) &&
                d1.getName().equals(diagnosis.getName()) &&
                d1.getCodeOfDiganosis() == diagnosis.getCodeOfDiganosis()
        )   {
            tacno = true;
        }else tacno = false;

        assertTrue(tacno);
    }



}