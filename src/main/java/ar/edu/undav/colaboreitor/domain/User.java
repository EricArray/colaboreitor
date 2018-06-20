package ar.edu.undav.colaboreitor.domain;

import java.sql.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.GenerationType;

@Entity
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
public class User {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	protected Long id;
	
	protected String username;
	protected String password;
	protected String nombre_real;
	protected String cp;
	protected double lat;
	protected double lng;
	protected int dni;
	protected Date creacion;
	
	public User() {
		super();
	}

	public User(Long id, String username, String password, String nombre_real, String cp, double lat, double lng,
			int dni, Date creacion) {
		super();
		this.id = id;
		this.username = username;
		this.password = password;
		this.nombre_real = nombre_real;
		this.cp = cp;
		this.lat = lat;
		this.lng = lng;
		this.dni = dni;
		this.creacion = creacion;
	}
}