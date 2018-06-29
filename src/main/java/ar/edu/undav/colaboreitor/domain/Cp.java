package ar.edu.undav.colaboreitor.domain;

import java.math.BigDecimal;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
public class Cp {
	@Id
	protected String cp;
	
	@ManyToOne
	@JoinColumn(name="localidad")
	protected Localidad localidad;
	
	protected BigDecimal lng;
	protected BigDecimal lat;
	
	
	public Cp() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Cp(String cp, Localidad localidad, BigDecimal lng, BigDecimal lat) {
		super();
		this.cp = cp;
		this.localidad = localidad;
		this.lng = lng;
		this.lat = lat;
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
	public Localidad getLocalidad() {
		return localidad;
	}
	public void setLocalidad(Localidad localidad) {
		this.localidad = localidad;
	}
}
