package rs.ac.bg.np.hospitalmanagement;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import rs.ac.bg.np.hospitalmanagement.domain.MedicalSpecial;
import rs.ac.bg.np.hospitalmanagement.repository.MedicineRepository;
import rs.ac.bg.np.hospitalmanagement.service.MedicalServiceService;
import rs.ac.bg.np.hospitalmanagement.service.MedicineService;

@SpringBootApplication
public class HospitalManagementApplication {

	@Autowired
	private MedicineService medicineService;

	@Autowired
	private MedicalServiceService medicalServiceService;

	public static void main(String[] args) {
		SpringApplication.run(HospitalManagementApplication.class, args);
	}

	/*@EventListener(ApplicationReadyEvent.class)
	public void setUp(){
		medicineService.init();
		medicalServiceService.init();
	}*/

}
