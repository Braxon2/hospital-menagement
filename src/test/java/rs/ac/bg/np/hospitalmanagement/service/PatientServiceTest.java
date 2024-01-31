package rs.ac.bg.np.hospitalmanagement.service;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import rs.ac.bg.np.hospitalmanagement.domain.Patient;
import rs.ac.bg.np.hospitalmanagement.repository.PatientRepository;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
class PatientServiceTest {

    @Mock
    private PatientRepository patientRepository;

    @InjectMocks
    private PatientService patientService;

    @Test
    void getAll() {

        List<Patient> patients = new ArrayList<>();
        patients.add(new Patient(1L,"Slavko Petronijevic",new Date(),"1234567891011","Belgrade",null));
        patients.add(new Patient(2L,"Uros Zdravkovic",new Date(),"4912352678931","Belgrade",null));
        patients.add(new Patient(3L,"Dragan Bender",new Date(),"8739027143200","Nis",null));

        Mockito.when(patientRepository.findAll()).thenReturn(patients);

        List<Patient> result = patientService.getAll();

        verify(patientRepository,times(1)).findAll();

        assertEquals(result,patients);

    }

    @Test
    void getPatient() throws Exception {

        Patient patient = new Patient(1L,"Slavko Petronijevic",new Date(),"1234567891011","Belgrade",null);

        Mockito.when(patientRepository.findById(patient.getpId())).thenReturn(Optional.of(patient));

        Patient result = patientService.getPatient(patient.getpId());

        verify(patientRepository,times(1)).findById(patient.getpId());
        assertEquals(result,patient);

    }

    @Test
    void getPatientException(){

        Patient patient = new Patient(9L,"Slavko Petronijevic",new Date(),"1234567891011","Belgrade",null);

        Mockito.when(patientRepository.findById(patient.getpId())).thenReturn(Optional.empty());

        Assertions.assertThrows(Exception.class,()->{
            patientService.getPatient(patient.getpId());
        });

        verify(patientRepository,times(1)).findById(patient.getpId());

    }

    @Test
    void createPatient() throws Exception {

        Patient patient = new Patient(4L,"Bogdan Bogdanovic",new Date(),"4321567891011","Belgrade",null);

        Mockito.when(patientRepository.save(patient)).thenReturn(patient);

        Patient result = patientService.createPatient(patient);

        verify(patientRepository,times(1)).save(patient);
        assertEquals(result,patient);

    }

    @Test
    void createPatientException(){

        Patient patient = new Patient(1L,"Slavko Petronijevic",new Date(),"1234567891011","Belgrade",null);
        Mockito.when(patientRepository.findByJmbg(patient.getJmbg())).thenReturn(Optional.of(patient));

        Assertions.assertThrows(Exception.class,()->{
            patientService.createPatient(patient);
        });

        verify(patientRepository,times(1)).findByJmbg(patient.getJmbg());

    }
}