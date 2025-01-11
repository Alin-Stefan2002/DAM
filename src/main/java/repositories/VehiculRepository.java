package repositories;

import org.scrum.domain.project.Sofer;
import org.scrum.domain.project.Vehicul;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface VehiculRepository extends JpaRepository<Vehicul, Integer> {

    // Găsește un vehicul după numărul de înmatriculare
    Optional<Vehicul> findByNumarInmatriculare(String numarInmatriculare);

    // Găsește toate vehiculele asociate unui șofer
    List<Vehicul> findBySofer(Sofer sofer);

    // Găsește vehiculele cu o capacitate mai mare decât un anumit număr
    List<Vehicul> findByCapacitateGreaterThan(int capacitate);

    // Găsește vehiculele cu o capacitate mai mică decât un anumit număr
    List<Vehicul> findByCapacitateLessThan(int capacitate);

    // Găsește vehiculele care nu sunt asociate unui șofer
    List<Vehicul> findBySoferIsNull();

    // Verifică dacă există un vehicul cu un anumit număr de înmatriculare
    boolean existsByNumarInmatriculare(String numarInmatriculare);

    // Găsește vehiculul după ID (metodă standard, dar poate fi utilă pentru un scenariu simplu)
    Optional<Vehicul> findById(Integer id);

    // Găsește vehiculele care au o capacitate între două valori
    List<Vehicul> findByCapacitateBetween(int capacitateMin, int capacitateMax);
}
