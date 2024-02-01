package rs.ac.bg.np.hospitalmanagement.service;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import rs.ac.bg.np.hospitalmanagement.domain.Hospital;
import rs.ac.bg.np.hospitalmanagement.repository.HospitalRepository;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
class HospitalServiceTest {

    @Mock
    private HospitalRepository hospitalRepository;

    @InjectMocks
    private HospitalService hospitalService;

    @Test
    void createHospital() throws Exception {

        Hospital hospital = new Hospital(2L,"Urgentni Centar","Pasterova 2","Belgrade",null);

        Mockito.when(hospitalRepository.save(hospital)).thenReturn(hospital);

        Hospital result = hospitalService.createHospital(hospital);

        verify(hospitalRepository,times(1)).save(hospital);

        assertEquals(result,hospital);
    }

    @Test
    void getOneHospital() throws Exception {

        Hospital hospital = new Hospital(1L,"Narodni Front","Stari Grad","Belgrade",null);

        Mockito.when(hospitalRepository.findById(hospital.getHospitalId())).thenReturn(Optional.of(hospital));


        Hospital result = hospitalService.getOneHospital(hospital.getHospitalId());

        verify(hospitalRepository, times(1)).findById(hospital.getHospitalId());
        assertEquals(result,hospital);
    }

    @Test
    void getOneHospitalException(){
        Hospital hospital = new Hospital(8L,"Narodni Front","Stari Grad","Belgrade",null);

        Mockito.when(hospitalRepository.findById(hospital.getHospitalId())).thenReturn(Optional.empty());


        Assertions.assertThrows(Exception.class,()->{
            hospitalService.getOneHospital(hospital.getHospitalId());
        });
        verify(hospitalRepository, times(1)).findById(hospital.getHospitalId());

    }

    @Test
    void createOneHospitalException(){
        Hospital hospital = new Hospital(1L,"Narodni Front","Stari Grad","Belgrade",null);
        Mockito.when(hospitalRepository.findByCityAndAddress(hospital.getCity(),hospital.getAddress())).thenReturn(Optional.of(hospital));

//        Hospital result = hospitalService.createHospital(hospital);

        Assertions.assertThrows(Exception.class,()->{
            hospitalService.createHospital(hospital);
        });

        verify(hospitalRepository,times(1)).findByCityAndAddress(hospital.getCity(),hospital.getAddress());
    }
}