package BD;

import org.scrum.domain.project.*;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import repositories.*;

import java.util.ArrayList;
import java.util.List;

@Configuration
public class Data_Base {

    @Bean
    public CommandLineRunner initDatabase(
            PasagerRepository pasagerRepository,
            PlataRepository plataRepository,
            ProgramRutaRepository programRutaRepository,
            RutaRepository rutaRepository,
            SoferRepository soferRepository,
            TichetRepository tichetRepository,
            VehiculRepository vehiculRepository) {

        return args -> {
            // Populare tabele
            // Pasageri
            List<Pasager> pasageri = List.of(
                    new Pasager("Ion Popescu", "ion.popescu@example.com", "0723456789"),
                    new Pasager("Maria Ionescu", "maria.ionescu@example.com", "0734567890"),
                    new Pasager("Andrei Gheorghe", "andrei.gheorghe@example.com", "0745678901"),
                    new Pasager("Elena Marin", "elena.marin@example.com", "0756789012"),
                    new Pasager("Cristian Vasile", "cristian.vasile@example.com", "0767890123")
            );
            pasagerRepository.saveAll(pasageri);

            // Plăți
            List<Plata> plati = List.of(
                    new Plata(100.0, "card", true),
                    new Plata(50.0, "numerar", true),
                    new Plata(120.0, "card", false),
                    new Plata(200.0, "numerar", true),
                    new Plata(80.0, "card", true)
            );
            plataRepository.saveAll(plati);

            // Programe de rute
            List<ProgramRuta> programeRute = List.of(
                    new ProgramRuta("08:00", "10:00"),
                    new ProgramRuta("09:30", "11:30"),
                    new ProgramRuta("12:00", "14:00"),
                    new ProgramRuta("15:00", "17:00"),
                    new ProgramRuta("18:30", "20:30")
            );
            programRutaRepository.saveAll(programeRute);

            // Rute
            List<Ruta> rute = List.of(
                    new Ruta(1, "București", "Constanța", programeRute.get(0)),
                    new Ruta(2, "Brașov", "Sibiu", programeRute.get(1)),
                    new Ruta(3, "Iași", "Bacău", programeRute.get(2)),
                    new Ruta(4, "Cluj", "Oradea", programeRute.get(3)),
                    new Ruta(5, "Timișoara", "Arad", programeRute.get(4))
            );
            rutaRepository.saveAll(rute);

            // Șoferi
            List<Sofer> soferi = List.of(
                    new Sofer(1, "Mihai Pop", "B123456"),
                    new Sofer(2, "Ana Georgescu", "C234567"),
                    new Sofer(3, "Radu Voinea", "D345678"),
                    new Sofer(4, "Ioana Dobre", "E456789"),
                    new Sofer(5, "Florin Manea", "F567890")
            );
            soferRepository.saveAll(soferi);

            // Vehicule
            List<Vehicul> vehicule = new ArrayList<>();
            vehicule.add(new Vehicul("B-123-XYZ", 50, soferi.get(0)));
            vehicule.add(new Vehicul("B-456-ABC", 60, soferi.get(1)));
            vehicule.add(new Vehicul("B-789-DEF", 55, soferi.get(2)));
            vehicule.add(new Vehicul("B-101-GHI", 65, soferi.get(3)));
            vehicule.add(new Vehicul("B-202-JKL", 70, soferi.get(4)));
            vehicule.forEach(v -> v.setRuta(rute.get(vehicule.indexOf(v))));
            vehiculRepository.saveAll(vehicule);

            // Tichete
            List<Tichet> tichete = new ArrayList<>();
            for (int i = 0; i < 5; i++) {
                tichete.add(new Tichet(
                        i + 1,
                        pasageri.get(i),
                        rute.get(i),
                        plati.get(i),
                        vehicule.get(i),
                        "Loc " + (i + 1)
                ));
            }
            tichetRepository.saveAll(tichete);
        };
    }
}
