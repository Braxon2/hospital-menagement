package rs.ac.bg.np.hospitalmanagement.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import rs.ac.bg.np.hospitalmanagement.domain.Medicine;

import java.util.Optional;

@Repository
public interface MedicineRepository extends JpaRepository<Medicine, Long> {
    Optional<Medicine> findByName(String name);

    Optional<Medicine> findByNameAndWeightPerPill(String name, int weightPerPill);
}
