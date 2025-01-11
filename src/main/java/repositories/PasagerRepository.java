package repositories;

import org.scrum.domain.project.Pasager;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PasagerRepository extends JpaRepository<Pasager, Integer> {

    // Caută pasageri după nume
    List<Pasager> findByNume(String nume);

    // Caută pasageri după email
    Pasager findByEmail(String email);

    // Caută pasageri după număr de telefon
    Pasager findByTelefon(String telefon);

    // Găsește pasageri al căror nume conține un anumit substring (ignorând majusculele)
    List<Pasager> findByNumeContainingIgnoreCase(String partialName);
}
