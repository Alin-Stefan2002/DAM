package org.scrum.domain.project;


import jakarta.persistence.*;
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
public class Sofer implements Serializable {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@EqualsAndHashCode.Include
	private Integer soferId;

	@NotNull(message = "Numele soferului este obligatoriu!")
	@Size(min = 2, message = "Numele trebuie să aibă cel puțin 2 caractere!")
	private String nume;

	@NotNull(message = "Permisul de conducere este obligatoriu!")
	private String permisConducere;

	// Constructori personalizați
	public Sofer(Integer soferId, String nume, String permisConducere) {
		this.soferId = soferId;
		this.nume = nume;
		this.permisConducere = permisConducere;
	}

	public Sofer(String nume, String permisConducere) {
		this.nume = nume;
		this.permisConducere = permisConducere;
	}
}