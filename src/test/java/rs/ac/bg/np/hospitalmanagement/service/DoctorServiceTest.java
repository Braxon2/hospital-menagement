package rs.ac.bg.np.hospitalmanagement.service;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import rs.ac.bg.np.hospitalmanagement.domain.Doctor;
import rs.ac.bg.np.hospitalmanagement.domain.MedicalSpecial;
import rs.ac.bg.np.hospitalmanagement.repository.DoctorRepository;
import rs.ac.bg.np.hospitalmanagement.repository.MedicalSpecialRepository;

import javax.print.Doc;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
class DoctorServiceTest {

    @Mock
    private DoctorRepository doctorRepository;

    @Mock
    private MedicalSpecialRepository medicalSpecialRepository;

    @InjectMocks
    private DoctorService doctorService;

    @InjectMocks
    private MedicalServiceService medicalServiceService;

    @Test
    void getAllDoctors() {

        MedicalSpecial medicalSpecial1 = new MedicalSpecial(154L,"Oftamologija",null);
        MedicalSpecial medicalSpecial2 = new MedicalSpecial(153L,"Stomatologija",null);
        MedicalSpecial medicalSpecial3 = new MedicalSpecial(152L,"ORL",null);

        List<Doctor> doctors = new ArrayList<>();
        doctors.add(new Doctor(1L,"Oliver Dragojevic","123456",medicalSpecial2,null));
        doctors.add(new Doctor(2L,"Bata Zivojinovic","361789",medicalSpecial1,null));
        doctors.add(new Doctor(52L,"Vladan Aksentijevic","291420",medicalSpecial1,null));
        doctors.add(new Doctor(102L,"Magdalena Stojkovic","831494",medicalSpecial3,null));

        Mockito.when(doctorRepository.findAll()).thenReturn(doctors);

        List<Doctor> result = doctorService.getAllDoctors();

        verify(doctorRepository,times(1)).findAll();
        assertEquals(result,doctors);

    }

    @Test
    void addNewDoctor() throws Exception {

        MedicalSpecial medicalSpecial2 = new MedicalSpecial(153L,"Stomatologija",null);
        Doctor dr = new Doctor(7L,"Nada Macura","987354",medicalSpecial2,null);

        Mockito.when(doctorRepository.save(dr)).thenReturn(dr);

        Doctor result = doctorService.addNewDoctor(dr);

        verify(doctorRepository,times(1)).save(dr);
        assertEquals(result,dr);

    }

    @Test
    void addNewDoctorException(){
        MedicalSpecial medicalSpecial2 = new MedicalSpecial(153L,"Stomatologija",null);
        Doctor dr = new Doctor(1L,"Oliver Dragojevic","123456",medicalSpecial2,null);

        Mockito.when(doctorRepository.findByLicenceNumber(dr.getLicenceNumber())).thenReturn(Optional.of(dr));

        Assertions.assertThrows(Exception.class,()->{
            doctorService.addNewDoctor(dr);
        });

        verify(doctorRepository,times(1)).findByLicenceNumber(dr.getLicenceNumber());

    }

    @Test
    void insertSpecialisationForDoctor() throws Exception {

        MedicalSpecial medicalSpecial2 = new MedicalSpecial(153L,"Stomatologija",new HashSet<>());
        Doctor dr = new Doctor(1L,"Oliver Dragojevic","123456",medicalSpecial2,null);


        Mockito.when(medicalSpecialRepository.findById(medicalSpecial2.getMedSpecId())).thenReturn(Optional.of(medicalSpecial2));

        Mockito.when(doctorRepository.findById(dr.getDocId())).thenReturn(Optional.of(dr));


        List<Doctor> results = doctorService.getAllDoctors();
        Doctor result = doctorService.insertSpecialisationForDoctor(medicalSpecial2.getMedSpecId(),dr.getDocId());


        verify(doctorRepository,times(1)).findById(dr.getDocId());

        assertEquals(result,dr);

    }

    @Test
    void insertSpecialisationForDoctorExceptionMedSpec() throws Exception {

        MedicalSpecial medicalSpecial2 = new MedicalSpecial(153L,"Stomatologija",new HashSet<>());
        Doctor dr = new Doctor(1L,"Oliver Dragojevic","123456",medicalSpecial2,null);


        Mockito.when(medicalSpecialRepository.findById(medicalSpecial2.getMedSpecId())).thenReturn(Optional.empty());

        Assertions.assertThrows(Exception.class,()->{
           doctorService.insertSpecialisationForDoctor(medicalSpecial2.getMedSpecId(),dr.getDocId());
        });

//        Mockito.when(doctorRepository.findById(dr.getDocId())).thenReturn(Optional.of(dr));




    }

    @Test
    void insertSpecialisationForDoctorExceptionDoctor() throws Exception {

        MedicalSpecial medicalSpecial2 = new MedicalSpecial(153L,"Stomatologija",new HashSet<>());
        Doctor dr = new Doctor(1L,"Oliver Dragojevic","123456",medicalSpecial2,null);


        Mockito.when(medicalSpecialRepository.findById(medicalSpecial2.getMedSpecId())).thenReturn(Optional.of(medicalSpecial2));



        Mockito.when(doctorRepository.findById(dr.getDocId())).thenReturn(Optional.empty());

        Assertions.assertThrows(Exception.class,()->{
            doctorService.insertSpecialisationForDoctor(medicalSpecial2.getMedSpecId(),dr.getDocId());
        });


    }

}