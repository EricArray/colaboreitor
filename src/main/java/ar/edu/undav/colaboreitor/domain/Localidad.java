package ar.edu.undav.colaboreitor.domain;

import java.math.BigDecimal;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Localidad {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	protected Long id;
	
	protected String nombre;

	protected BigDecimal lng;
	protected BigDecimal lat;

	
	public Localidad() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Localidad(String nombre, BigDecimal lng, BigDecimal lat) {
		super();
		this.nombre = nombre;
		this.lng = lng;
		this.lat = lat;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
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
	
}
