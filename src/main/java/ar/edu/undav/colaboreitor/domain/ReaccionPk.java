package ar.edu.undav.colaboreitor.domain;

import java.io.Serializable;

public class ReaccionPk implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	protected Long incidente;
	protected Long user;
	
	
	public ReaccionPk() {
		super();
	}
	public ReaccionPk(Long incidente, Long user) {
		super();
		this.incidente = incidente;
		this.user = user;
	}
	
	public Long getIncidente() {
		return incidente;
	}
	public void setIncidente(Long incidente) {
		this.incidente = incidente;
	}
	public Long getUser() {
		return user;
	}
	public void setUser(Long user) {
		this.user = user;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	
	
}
