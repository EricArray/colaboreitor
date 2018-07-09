package ar.edu.undav.colaboreitor.domain;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

@Entity
public class Incidente {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	protected Long id;

	@ManyToOne
	@JoinColumn(name="cuenta")
	protected Cuenta cuenta;

	@ManyToOne
	@JoinColumn(name="cp")
	protected Cp cp;
	
	protected BigDecimal lng;
	protected BigDecimal lat;
	protected Timestamp creacion;

	@OneToMany(targetEntity=Foto.class, mappedBy="incidente", cascade=CascadeType.ALL)
	protected List<Foto> fotos;

	@OneToMany(targetEntity=Reaccion.class, mappedBy="incidente", cascade=CascadeType.ALL)
	protected List<Reaccion> reacciones;


	public Incidente() {
		super();
		// TODO Auto-generated constructor stub
	}


	public Incidente(Cuenta cuenta, Cp cp, BigDecimal lng, BigDecimal lat, Timestamp creacion) {
		super();
		this.cuenta = cuenta;
		this.cp = cp;
		this.lng = lng;
		this.lat = lat;
		this.creacion = creacion;
	}


	public Long getId() {
		return id;
	}


	public void setId(Long id) {
		this.id = id;
	}


	public Cuenta getCuenta() {
		return cuenta;
	}


	public void setCuenta(Cuenta cuenta) {
		this.cuenta = cuenta;
	}


	public Cp getCp() {
		return cp;
	}


	public void setCp(Cp cp) {
		this.cp = cp;
	}


	public List<Reaccion> getReacciones() {
		return reacciones;
	}


	public void setReacciones(List<Reaccion> reacciones) {
		this.reacciones = reacciones;
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


	public Timestamp getCreacion() {
		return creacion;
	}


	public void setCreacion(Timestamp creacion) {
		this.creacion = creacion;
	}


	public List<Foto> getFotos() {
		return fotos;
	}


	public void setFotos(List<Foto> fotos) {
		this.fotos = fotos;
	}
	
	
	
}