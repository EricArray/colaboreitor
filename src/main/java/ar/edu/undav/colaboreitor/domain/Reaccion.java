package ar.edu.undav.colaboreitor.domain;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
@IdClass(ReaccionPk.class)
public class Reaccion implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = -2383937019035174010L;
 
	@Id
	@ManyToOne
	@JoinColumn(name="incidente")
	protected Incidente incidente;
	
	@Id
	@ManyToOne
	@JoinColumn(name="user")
	protected User user;
	
	protected int reaccion;

	
	public Reaccion() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Reaccion(Incidente incidente, User user, int reaccion) {
		super();
		this.incidente = incidente;
		this.user = user;
		this.reaccion = reaccion;
	}

	public Incidente getIncidente() {
		return incidente;
	}

	public void setIncidente(Incidente incidente) {
		this.incidente = incidente;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public int getReaccion() {
		return reaccion;
	}

	public void setReaccion(int reaccion) {
		this.reaccion = reaccion;
	}
	
}
