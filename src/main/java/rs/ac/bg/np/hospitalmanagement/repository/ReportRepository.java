package rs.ac.bg.np.hospitalmanagement.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import rs.ac.bg.np.hospitalmanagement.domain.Doctor;
import rs.ac.bg.np.hospitalmanagement.domain.Patient;
import rs.ac.bg.np.hospitalmanagement.domain.Report;

import java.util.Set;

@Repository
public interface ReportRepository extends JpaRepository<Report, Long> {
    Set<Report> findByDoctor(Doctor doctor);

    Set<Report> findByPatient(Patient patient);
}
