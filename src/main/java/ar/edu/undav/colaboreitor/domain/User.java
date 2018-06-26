package ar.edu.undav.colaboreitor.domain;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.List;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.OneToMany;
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
	protected BigDecimal lng;
	protected BigDecimal lat;
	protected int dni;
	protected Timestamp creacion;

	@OneToMany(targetEntity=Incidente.class, mappedBy="user", cascade=CascadeType.ALL)
	protected List<Incidente> incidentes;

	@OneToMany(targetEntity=Reaccion.class, mappedBy="user", cascade=CascadeType.ALL)
	protected List<Reaccion> reacciones;

	
	public User() {
		super();
	}

	public User(String username, String password, String nombre_real, String cp,
			BigDecimal lng, BigDecimal lat,
			int dni, Timestamp creacion) {
		super();
		this.username = username;
		this.password = password;
		this.nombre_real = nombre_real;
		this.cp = cp;
		this.lng = lng;
		this.lat = lat;
		this.dni = dni;
		this.creacion = creacion;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getNombre_real() {
		return nombre_real;
	}

	public void setNombre_real(String nombre_real) {
		this.nombre_real = nombre_real;
	}

	public String getCp() {
		return cp;
	}

	public void setCp(String cp) {
		this.cp = cp;
	}

	public BigDecimal getLng() {
		return lng;
	}

	public void setLng(BigDecimal lng) {
		this.lng = lng;
	}

	public BigDecimal getLat() {
		return lat;
	}

	public void setLat(BigDecimal lat) {
		this.lat = lat;
	}

	public int getDni() {
		return dni;
	}

	public void setDni(int dni) {
		this.dni = dni;
	}

	public Timestamp getCreacion() {
		return creacion;
	}

	public void setCreacion(Timestamp creacion) {
		this.creacion = creacion;
	}

	public List<Incidente> getIncidentes() {
		return incidentes;
	}

	public void setIncidentes(List<Incidente> incidentes) {
		this.incidentes = incidentes;
	}

	public List<Reaccion> getReacciones() {
		return reacciones;
	}

	public void setReacciones(List<Reaccion> reacciones) {
		this.reacciones = reacciones;
	}
	
	
}