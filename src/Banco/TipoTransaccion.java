package Banco;

import java.io.Serializable;

public class TipoTransaccion implements Serializable{

	private static final long serialVersionUID = -3775964731554899360L;
	
	private String descripcion;
	private boolean esDebito;
	
	public TipoTransaccion(String descripcion, boolean esDebito) {
		super();
		this.descripcion = descripcion;
		this.esDebito = esDebito;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public boolean isEsDebito() {
		return esDebito;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public void setEsDebito(boolean esDebito) {
		this.esDebito = esDebito;
	}

	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		if(esDebito) {
			sb.append("Descripcion: "+descripcion+" Tipo: Debito");
		}else {
			sb.append("Descripcion: "+descripcion+" Tipo: Credito");
		}
		return sb.toString();
	}
}
