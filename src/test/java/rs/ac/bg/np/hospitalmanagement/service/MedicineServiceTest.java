package rs.ac.bg.np.hospitalmanagement.service;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import rs.ac.bg.np.hospitalmanagement.domain.Medicine;
import rs.ac.bg.np.hospitalmanagement.repository.MedicineRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
class MedicineServiceTest {

    @Mock
    private MedicineRepository medicineRepository;

    @InjectMocks
    private MedicineService medicineService;

    @Test
    void getAll() {

        List<Medicine> medicines = new ArrayList<>();
        medicines.add(new Medicine(102L,"Brufen",10));
        medicines.add(new Medicine(103L,"Pancef",20));

        Mockito.when(medicineRepository.findAll()).thenReturn(medicines);

        List<Medicine> result = medicineService.getAll();

        verify(medicineRepository,times(1)).findAll();

        assertEquals(result,medicines);


    }

    @Test
    void addNewMedication() throws Exception {

        Medicine medicine = new Medicine(104L,"Panklav",30);

        Mockito.when(medicineRepository.save(medicine)).thenReturn(medicine);

        Medicine result = medicineService.addNewMedication(medicine);

        verify(medicineRepository,times(1)).save(medicine);

        assertEquals(result,medicine);

    }


    @Test
    void deleteMedicine() {

        Long id = 1L;
        Medicine medicine = new Medicine();
        medicine.setMedId(id);

        Mockito.when(medicineRepository.findById(id)).thenReturn(Optional.of(medicine));

         medicineService.deleteMedicine(id);


        verify(medicineRepository, times(1)).deleteById(id);
        assertTrue(medicineRepository.findById(id).isPresent());


    }

}