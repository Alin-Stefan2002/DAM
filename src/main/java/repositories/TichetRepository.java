package repositories;

import org.scrum.domain.project.Tichet;
import org.scrum.domain.project.Pasager;
import org.scrum.domain.project.Ruta;
import org.scrum.domain.project.Vehicul;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TichetRepository extends JpaRepository<Tichet, Integer> {

    // Găsește tichetele pentru un anumit pasager
    List<Tichet> findByPasager(Pasager pasager);

    // Găsește tichetele pentru o anumită rută
    List<Tichet> findByRuta(Ruta ruta);

    // Găsește tichetele pentru un anumit vehicul
    List<Tichet> findByVehicul(Vehicul vehicul);

    // Găsește tichetele rezervate (esteRezervat = true)
    List<Tichet> findByEsteRezervatTrue();

    // Găsește tichetele care nu sunt rezervate (esteRezervat = false)
    List<Tichet> findByEsteRezervatFalse();

    // Găsește tichetele pentru o anumită rută și un anumit vehicul
    List<Tichet> findByRutaAndVehicul(Ruta ruta, Vehicul vehicul);

    // Găsește tichetele pentru un anumit pasager și statusul rezervării
    List<Tichet> findByPasagerAndEsteRezervat(Pasager pasager, Boolean esteRezervat);

    // Găsește tichetele pentru o anumită rută și loc
    List<Tichet> findByRutaAndLoc(Ruta ruta, String loc);
}
