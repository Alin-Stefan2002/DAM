package org.scrum.domain.project;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.EqualsAndHashCode;

import java.io.Serializable;

@Data
@NoArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Entity
public class ProgramRuta implements Serializable {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@EqualsAndHashCode.Include
	private Integer programId;

	@NotNull(message = "Ora de plecare este obligatorie!")
	@Pattern(regexp = "^([01]?\\d|2[0-3]):[0-5]\\d$", message = "Ora de plecare trebuie să fie în format HH:mm!")
	private String oraPlecare;

	@NotNull(message = "Ora de sosire este obligatorie!")
	@Pattern(regexp = "^([01]?\\d|2[0-3]):[0-5]\\d$", message = "Ora de sosire trebuie să fie în format HH:mm!")
	private String oraSosire;

	// Constructor personalizat
	public ProgramRuta(String oraPlecare, String oraSosire) {
		this.oraPlecare = oraPlecare;
		this.oraSosire = oraSosire;
	}
}