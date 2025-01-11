package repositories;

import org.scrum.domain.project.Sofer;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface SoferRepository extends JpaRepository<Sofer, Integer> {

    // Găsește șoferii după nume
    List<Sofer> findByNume(String nume);

    // Găsește șoferii care au un anumit tip de permis de conducere
    List<Sofer> findByPermisConducere(String permisConducere);

    // Găsește șoferii al căror nume conține un anumit substring (căutare parțială)
    List<Sofer> findByNumeContaining(String partialName);

    // Găsește șoferii al căror permis începe cu un anumit prefix
    List<Sofer> findByPermisConducereStartingWith(String prefix);

    // Găsește șoferii al căror permis se termină cu un anumit sufix
    List<Sofer> findByPermisConducereEndingWith(String suffix);

}
