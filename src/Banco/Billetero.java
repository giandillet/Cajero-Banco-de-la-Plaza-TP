package Banco;

import java.io.Serializable;
import java.math.BigDecimal;

public class Billetero implements Serializable{

	private static final long serialVersionUID = -9050609929766063990L;
	
	private BigDecimal valorBillete;
	private int cantidad;
	
	public Billetero(BigDecimal valorBillete, int cantidad) {
		this.valorBillete = valorBillete;
		this.cantidad = cantidad;
	}
	
	public BigDecimal getValorBillete() {
		return valorBillete;
	}
	
	public int getCantidad() {
		return cantidad;
	}
	
	public void setValorBillete(BigDecimal valorBillete) {
		this.valorBillete = valorBillete;
	}
	
	public void setCantidad(int cantidad) {
		this.cantidad = cantidad;
	}
	
	public void recargar(int cant) {
		this.cantidad+=cant;
	}
	
	public void descargar(int cant) {
		this.cantidad-=cant;
	}

	@Override
	public String toString() {
		return "Billetero de "+valorBillete;
	}
	
	public String control() {
		return "Billetero " + valorBillete + ": Cantidad: "+cantidad;
	}
	
}
