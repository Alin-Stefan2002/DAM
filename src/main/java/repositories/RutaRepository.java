package repositories;

import org.scrum.domain.project.Ruta;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface RutaRepository extends JpaRepository<Ruta, Integer> {

    // Găsește toate rutele care au un anumit punct de plecare
    List<Ruta> findByPunctPlecare(String punctPlecare);

    // Găsește toate rutele care au un anumit punct de sosire
    List<Ruta> findByPunctSosire(String punctSosire);

    // Găsește toate rutele care au o anumită combinație de punct de plecare și sosire
    List<Ruta> findByPunctPlecareAndPunctSosire(String punctPlecare, String punctSosire);

    // Găsește rutele care conțin vehicule asociate
    List<Ruta> findByVehiculeIsNotEmpty();

    // Găsește rutele care au program asociat cu o anumită oră de plecare
    List<Ruta> findByProgramRuta_OraPlecare(String oraPlecare);

    // Găsește rutele care au program asociat cu o anumită oră de sosire
    List<Ruta> findByProgramRuta_OraSosire(String oraSosire);
}
