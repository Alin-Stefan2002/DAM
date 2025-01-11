package org.scrum.domain.project;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@NoArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Entity
public class Pasager implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @EqualsAndHashCode.Include
    private Integer pasagerId;

    @NotNull(message = "Numele este obligatoriu!")
    @Size(min = 2, message = "Numele trebuie să aibă cel puțin 2 caractere!")
    private String nume;

    @NotNull(message = "Email-ul este obligatoriu!")
    @Email(message = "Adresa de email nu este validă!")
    private String email;

    @NotNull(message = "Numărul de telefon este obligatoriu!")
    @Pattern(regexp = "\\d{10}", message = "Numărul de telefon trebuie să conțină exact 10 cifre!")
    private String telefon;

    // Constructori personalizați
    public Pasager(String nume, String email, String telefon) {
        this.nume = nume;
        this.email = email;
        this.telefon = telefon;
    }

    public Pasager(Integer pasagerId, String nume, String email, String telefon) {
        this.pasagerId = pasagerId;
        this.nume = nume;
        this.email = email;
        this.telefon = telefon;
    }
}
