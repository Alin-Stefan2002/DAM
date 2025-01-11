package org.scrum.domain.project;

import jakarta.persistence.*;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.EqualsAndHashCode;

import java.io.Serializable;

@Data
@NoArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Entity
public class Plata implements Serializable {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@EqualsAndHashCode.Include
	private Integer plataId;

	@NotNull(message = "Suma este obligatorie!")
	@Min(value = 0, message = "Suma trebuie să fie pozitivă!")
	private Double suma;

	@NotNull(message = "Metoda de plată este obligatorie!")
	@Size(min = 3, message = "Metoda de plată trebuie să aibă cel puțin 3 caractere!")
	private String metodaPlata; // Ex: "card", "numerar"

	@NotNull(message = "Statusul plății este obligatoriu!")
	private Boolean statusPlata; // True dacă plata a fost efectuată cu succes

	// Constructor personalizat
	public Plata(Double suma, String metodaPlata, Boolean statusPlata) {
		this.suma = suma;
		this.metodaPlata = metodaPlata;
		this.statusPlata = statusPlata;
	}

	public void setStatusPlata(boolean statusPlata) {
		this.statusPlata = statusPlata;
	}
}