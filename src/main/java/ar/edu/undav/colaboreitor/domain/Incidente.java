package ar.edu.undav.colaboreitor.domain;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.GenerationType;

@Entity
public class Incidente {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	protected Long id;
	
	@ManyToOne
	@JoinColumn(name="user")
	protected User user;
	
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


	public Incidente(User user, BigDecimal lng, BigDecimal lat, Timestamp creacion) {
		super();
		this.user = user;
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


	public User getUser() {
		return user;
	}


	public void setUser(User user) {
		this.user = user;
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