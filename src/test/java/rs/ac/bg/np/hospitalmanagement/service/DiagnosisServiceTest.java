package rs.ac.bg.np.hospitalmanagement.service;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import rs.ac.bg.np.hospitalmanagement.domain.Diagnosis;
import rs.ac.bg.np.hospitalmanagement.repository.DiagnosisRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class DiagnosisServiceTest {

    @Mock
    private DiagnosisRepository diagnosisRepository;

    @InjectMocks
    private DiagnosisService diagnosisService;

    @Test
    void getAll() {

        List<Diagnosis> dijagnoze = new ArrayList<>();
        dijagnoze.add(new Diagnosis("Katarakta",3350,"H0701"));
        dijagnoze.add(new Diagnosis("Blepharitis",3351,"H0702"));
        dijagnoze.add(new Diagnosis("Deformatio orbitae",3352,"H0703"));
        dijagnoze.add(new Diagnosis("Tinitus",4200,"B0100"));
        dijagnoze.add(new Diagnosis("Laringitis",4201,"B0101"));
//        dijagnoze.add(new Diagnosis("Bol u grlu",3353,"H0703"));
        dijagnoze.get(0).setDiaId(1L);
        dijagnoze.get(1).setDiaId(2L);
        dijagnoze.get(2).setDiaId(3L);
        dijagnoze.get(3).setDiaId(4L);
        dijagnoze.get(4).setDiaId(5L);

        Mockito.when(diagnosisRepository.findAll()).thenReturn(dijagnoze);
//        when(diagnosisRepository.findAll()).thenReturn(dijagnoze);

        List<Diagnosis> result = diagnosisService.getAll();

        assertThat(result).isEqualTo(dijagnoze);
        verify(diagnosisRepository, times(1)).findAll();


    }

    @Test
    void getDiagnosis() throws Exception {

        Diagnosis diagnosis = new Diagnosis("Katarakta",3350,"H0701");
        diagnosis.setDiaId(1L);
        Long id = diagnosis.getDiaId();
        Mockito.when(diagnosisRepository.findById(id)).thenReturn(Optional.of(diagnosis));

        Diagnosis result = diagnosisService.getDiagnosis(id);

        verify(diagnosisRepository, times(1)).findById(id);
        assertEquals(result,diagnosis);

    }

    @Test
    void getDiagnosisWithException(){

        Diagnosis diagnosis = new Diagnosis("Katarakta",3350,"H0701");
        diagnosis.setDiaId(9L);
        Long id = diagnosis.getDiaId();
        Mockito.when(diagnosisRepository.findById(id)).thenReturn(Optional.empty());

        Assertions.assertThrows(Exception.class, ()->{
            diagnosisService.getDiagnosis(id);
        });

        verify(diagnosisRepository, times(1)).findById(id);

    }
}