package rs.ac.bg.np.hospitalmanagement.service;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import rs.ac.bg.np.hospitalmanagement.domain.Doctor;
import rs.ac.bg.np.hospitalmanagement.domain.Hospital;
import rs.ac.bg.np.hospitalmanagement.domain.MedicalSpecial;
import rs.ac.bg.np.hospitalmanagement.repository.HospitalRepository;
import rs.ac.bg.np.hospitalmanagement.repository.MedicalSpecialRepository;

import java.util.*;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)

class MedicalServiceServiceTest {

    @Mock
    private MedicalSpecialRepository medicalSpecialRepository;

    @Mock
    private HospitalRepository hospitalRepository;

    @InjectMocks
    private MedicalServiceService medicalServiceService;

    @InjectMocks
    private HospitalService hospitalService;

    @Test
    void connect() throws Exception {

        Hospital hospital = new Hospital(1L,"Narodni Front","Stari Grad","Belgrade",null);
        MedicalSpecial medicalSpecial2 = new MedicalSpecial(153L,"Stomatologija",null);
        medicalSpecial2.setHospital(hospital);
        Set<MedicalSpecial> specials = new HashSet<>();
        specials.add(medicalSpecial2);
        hospital.setMedicalSpecials(specials);

        Mockito.when(hospitalRepository.findById(hospital.getHospitalId())).thenReturn(Optional.of(hospital));

        Mockito.when(medicalSpecialRepository.findById(medicalSpecial2.getMedSpecId())).thenReturn(Optional.of(medicalSpecial2));


        MedicalSpecial result = medicalServiceService.connect(medicalSpecial2.getMedSpecId(),hospital.getHospitalId());

        verify(hospitalRepository,times(1)).findById(hospital.getHospitalId());
        verify(medicalSpecialRepository,times(1)).findById(medicalSpecial2.getMedSpecId());
        assertEquals(result,medicalSpecial2);




    }

    @Test
    void connectExceptionForNotHospital(){

        Hospital hospital = new Hospital(1L,"Narodni Front","Stari Grad","Belgrade",null);
        MedicalSpecial medicalSpecial2 = new MedicalSpecial(153L,"Stomatologija",null);
        medicalSpecial2.setHospital(hospital);
        Set<MedicalSpecial> specials = new HashSet<>();
        specials.add(medicalSpecial2);
        hospital.setMedicalSpecials(specials);

        Mockito.when(hospitalRepository.findById(hospital.getHospitalId())).thenReturn(Optional.empty());

        Assertions.assertThrows(Exception.class,()->{
            hospitalService.getOneHospital(hospital.getHospitalId());
        });


    }

    @Test
    void connectExceptionForNotMedicalSpecial(){

        Hospital hospital = new Hospital(1L,"Narodni Front","Stari Grad","Belgrade",null);
        MedicalSpecial medicalSpecial2 = new MedicalSpecial(153L,"Stomatologija",null);
        medicalSpecial2.setHospital(hospital);
        Set<MedicalSpecial> specials = new HashSet<>();
        specials.add(medicalSpecial2);
        hospital.setMedicalSpecials(specials);

        Mockito.when(medicalSpecialRepository.findById(medicalSpecial2.getMedSpecId())).thenReturn(Optional.empty());
        Assertions.assertThrows(Exception.class,()->{
            medicalServiceService.connect(medicalSpecial2.getMedSpecId(),hospital.getHospitalId());
        });

    }

    @Test
    void getAll() {

        List<MedicalSpecial> specials = new ArrayList<>();

        MedicalSpecial medicalSpecial1 = new MedicalSpecial(154L,"Oftamologija",null);
        MedicalSpecial medicalSpecial2 = new MedicalSpecial(153L,"Stomatologija",null);
        specials.add(medicalSpecial1);
        specials.add(medicalSpecial2);

        Mockito.when(medicalSpecialRepository.findAll()).thenReturn(specials);
        List<MedicalSpecial> result = medicalServiceService.getAll();

        verify(medicalSpecialRepository,times(1)).findAll();
        assertEquals(result,specials);

    }

    @Test
    void getAllDoctors() throws Exception {
        MedicalSpecial medicalSpecial2 = new MedicalSpecial(153L,"Stomatologija",null);
        Set<Doctor> doctorSet = new HashSet<>();
        Doctor dr1 = new Doctor(1L,"Oliver Dragojevic","123456",medicalSpecial2,null);
        Doctor dr2 = new Doctor(1L,"Bata Zivojinovic","361789",medicalSpecial2,null);
        doctorSet.add(dr2);
        doctorSet.add(dr1);
        medicalSpecial2.setMembers(doctorSet);
        Mockito.when(medicalSpecialRepository.findById(medicalSpecial2.getMedSpecId())).thenReturn(Optional.of(medicalSpecial2));

        Set<Doctor> result  = medicalServiceService.getAllDoctors(medicalSpecial2.getMedSpecId());

        verify(medicalSpecialRepository,times(1)).findById(medicalSpecial2.getMedSpecId());
        assertEquals(result,doctorSet);


    }

    @Test
    void getAllDoctorsException(){

        MedicalSpecial medicalSpecial2 = new MedicalSpecial(153L,"Stomatologija",null);
        Set<Doctor> doctorSet = new HashSet<>();
        Doctor dr1 = new Doctor(1L,"Oliver Dragojevic","123456",medicalSpecial2,null);
        Doctor dr2 = new Doctor(1L,"Bata Zivojinovic","361789",medicalSpecial2,null);
        doctorSet.add(dr2);
        doctorSet.add(dr1);
        medicalSpecial2.setMembers(doctorSet);
        Mockito.when(medicalSpecialRepository.findById(medicalSpecial2.getMedSpecId())).thenReturn(Optional.empty());

        Assertions.assertThrows(Exception.class,()->{
            medicalServiceService.getAllDoctors(medicalSpecial2.getMedSpecId());
        });

    }
    @Test
    void findDoctor() throws Exception {
        MedicalSpecial medicalSpecial2 = new MedicalSpecial(153L,"Stomatologija",null);
        Doctor dr1 = new Doctor(1L,"Oliver Dragojevic","123456",medicalSpecial2,null);
        Set<Doctor> doctorSet = new HashSet<>();
        doctorSet.add(dr1);
        medicalSpecial2.setMembers(doctorSet);

        Mockito.when(medicalSpecialRepository.findById(medicalSpecial2.getMedSpecId())).thenReturn(Optional.of(medicalSpecial2));

        Doctor docResult = medicalServiceService.findDoctor(medicalSpecial2.getMedSpecId(),dr1.getDocId());


        assertEquals(docResult,dr1);

    }

    @Test
    void findDoctorException(){

        MedicalSpecial medicalSpecial2 = new MedicalSpecial(153L,"Stomatologija",null);
        Doctor dr1 = new Doctor(1L,"Oliver Dragojevic","123456",medicalSpecial2,null);
        Set<Doctor> doctorSet = new HashSet<>();
        doctorSet.add(dr1);
        medicalSpecial2.setMembers(doctorSet);

        Mockito.when(medicalSpecialRepository.findById(medicalSpecial2.getMedSpecId())).thenReturn(Optional.empty());

        Assertions.assertThrows(Exception.class,()->{
            medicalServiceService.findDoctor(medicalSpecial2.getMedSpecId(),dr1.getDocId());
        });

    }
}