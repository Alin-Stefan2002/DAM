package org.scrum.domain.project;


import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.EqualsAndHashCode;

import java.io.Serializable;

@Data
@NoArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Entity
public class Tichet implements Serializable {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@EqualsAndHashCode.Include
	private Integer tichetId;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "pasager_id", nullable = false)
	@NotNull(message = "Pasagerul este obligatoriu!")
	private Pasager pasager;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "ruta_id", nullable = false)
	@NotNull(message = "Ruta este obligatorie!")
	private Ruta ruta;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "vehicul_id", nullable = false)
	@NotNull(message = "Vehicolul este obligatoriu!")
	private Vehicul vehicul;

	@OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	@JoinColumn(name = "plata_id", nullable = false)
	@NotNull(message = "Plata este obligatorie!")
	private Plata plata;

	@NotNull(message = "Locul este obligatoriu!")
	private String loc; // Ex: numărul locului

	@NotNull
	private Boolean esteRezervat = false;

	public Tichet(int i, Pasager pasager, Ruta ruta, Plata plata, Vehicul vehicul, String loc) {
		this.pasager = pasager;
		this.ruta = ruta;
		this.plata = plata;
		this.loc = loc;
		this.esteRezervat = false;
		this.vehicul=vehicul;
	}
}