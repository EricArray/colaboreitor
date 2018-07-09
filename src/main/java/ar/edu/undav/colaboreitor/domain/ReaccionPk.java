package ar.edu.undav.colaboreitor.domain;

import java.io.Serializable;

public class ReaccionPk implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	protected Long incidente;
	protected Long cuenta;
	
	
	public ReaccionPk() {
		super();
	}
	public ReaccionPk(Long incidente, Long cuenta) {
		super();
		this.incidente = incidente;
		this.cuenta = cuenta;
	}
	
	public Long getIncidente() {
		return incidente;
	}
	public void setIncidente(Long incidente) {
		this.incidente = incidente;
	}
	public Long getCuenta() {
		return cuenta;
	}
	public void setCuenta(Long cuenta) {
		this.cuenta = cuenta;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	
	
}
