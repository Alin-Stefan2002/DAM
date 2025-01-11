package repositories;

import org.scrum.domain.project.Plata;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PlataRepository extends JpaRepository<Plata, Integer> {

    // Găsește plăți după suma exactă
    List<Plata> findBySuma(Double suma);

    // Găsește plăți după metoda de plată
    List<Plata> findByMetodaPlata(String metodaPlata);

    // Găsește plăți după statusul plății (efectuată/neefectuată)
    List<Plata> findByStatusPlata(Boolean statusPlata);

    // Găsește plăți după sumă mai mare decât o valoare specificată
    List<Plata> findBySumaGreaterThan(Double suma);

    // Găsește plăți după metoda de plată ignorând majusculele
    List<Plata> findByMetodaPlataIgnoreCase(String metodaPlata);

    // Găsește plăți efectuate cu succes pentru o anumită metodă de plată
    List<Plata> findByMetodaPlataAndStatusPlata(String metodaPlata, Boolean statusPlata);
}

