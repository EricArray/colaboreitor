package ar.edu.undav.colaboreitor.domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;

@Entity
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
public class Foto {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	protected Long id;
	
	protected Long incidente;
	protected String path;
	
	
	public Foto() {
		super();
	}
	
	public Foto(Long id, Long incidente, String path) {
		super();
		this.id = id;
		this.incidente = incidente;
		this.path = path;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getIncidente() {
		return incidente;
	}

	public void setIncidente(Long incidente) {
		this.incidente = incidente;
	}

	public String getPath() {
		return path;
	}

	public void setPath(String path) {
		this.path = path;
	}
	
	
}
