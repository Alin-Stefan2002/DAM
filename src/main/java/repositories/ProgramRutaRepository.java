package repositories;

import org.scrum.domain.project.ProgramRuta;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ProgramRutaRepository extends JpaRepository<ProgramRuta, Integer> {

    // Găsește toate programele după ora de plecare
    List<ProgramRuta> findByOraPlecare(String oraPlecare);

    // Găsește toate programele după ora de sosire
    List<ProgramRuta> findByOraSosire(String oraSosire);

    // Găsește programele care au o anumită combinație de ore de plecare și sosire
    List<ProgramRuta> findByOraPlecareAndOraSosire(String oraPlecare, String oraSosire);

    // Găsește toate programele unde ora de plecare este înainte de o anumită oră
    List<ProgramRuta> findByOraPlecareLessThan(String oraPlecare);

    // Găsește toate programele unde ora de sosire este după o anumită oră
    List<ProgramRuta> findByOraSosireGreaterThan(String oraSosire);
}
