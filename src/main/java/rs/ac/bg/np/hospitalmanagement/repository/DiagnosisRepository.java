package rs.ac.bg.np.hospitalmanagement.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import rs.ac.bg.np.hospitalmanagement.domain.Diagnosis;

@Repository
public interface DiagnosisRepository extends JpaRepository<Diagnosis,Long> {
}
