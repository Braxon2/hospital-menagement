package rs.ac.bg.np.hospitalmanagement.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import rs.ac.bg.np.hospitalmanagement.domain.Doctor;
import rs.ac.bg.np.hospitalmanagement.domain.MedicalSpecial;
import rs.ac.bg.np.hospitalmanagement.repository.DoctorRepository;
import rs.ac.bg.np.hospitalmanagement.repository.MedicalSpecialRepository;

import java.util.List;
import java.util.Optional;

@Service
public class DoctorService {

    @Autowired
    private DoctorRepository doctorRepository;

    @Autowired
    private MedicalSpecialRepository medicalSpecialRepository;

    public List<Doctor> getAllDoctors() {
        return doctorRepository.findAll();
    }


    public Doctor addNewDoctor(Doctor doctor) throws Exception {

        Optional<Doctor> optionalDoctor = doctorRepository.findByLicenceNumber(doctor.getLicenceNumber());

        if (optionalDoctor.isPresent()) {
            throw new Exception("Doctor with this licence already exists!!!");
        }

//        Doctor doc = optionalDoctor.get();
        doctorRepository.save(doctor);
        return doctor;

    }

    public Doctor insertSpecialisationForDoctor(long msid, long did) throws Exception {

        Optional<MedicalSpecial> optionalMedicalSpecial = medicalSpecialRepository.findById(msid);

        Optional<Doctor> optionalDoctor = doctorRepository.findById(did);

        if (!optionalMedicalSpecial.isPresent()) {
            throw new Exception("This medical specialisation does not exist!!!");
        }

        if (!optionalDoctor.isPresent()) {
            throw new Exception("This doctor does not exist!!!");
        }

        MedicalSpecial medSpecial = optionalMedicalSpecial.get();
        Doctor doctor = optionalDoctor.get();
        doctor.setMedicalSpecial(medSpecial);
        doctorRepository.save(doctor);

        medSpecial.getMembers().add(doctor);
        return doctor;

    }


}
