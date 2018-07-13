package ar.edu.undav.colaboreitor.domain;

import java.io.Serializable;

public class ReaccionPk implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	protected long incidente;
	protected long cuenta;
	
	
	public ReaccionPk() {
		super();
	}
	public ReaccionPk(long incidente, long cuenta) {
		super();
		this.incidente = incidente;
		this.cuenta = cuenta;
	}
	
	@Override
	public boolean equals(Object o) {
		if (o instanceof ReaccionPk) {
			ReaccionPk u = (ReaccionPk)(o);
			return incidente == u.incidente &&
				   cuenta == u.cuenta;
		} else {
			return false;
		}
	}
	
	public long getIncidente() {
		return incidente;
	}
	public void setIncidente(long incidente) {
		this.incidente = incidente;
	}
	public long getCuenta() {
		return cuenta;
	}
	public void setCuenta(long cuenta) {
		this.cuenta = cuenta;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	
	
}
