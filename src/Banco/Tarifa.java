package Banco;

import java.io.Serializable;
import java.math.BigDecimal;

public class Tarifa implements Serializable{

	private static final long serialVersionUID = 6940166363077205525L;
	
	private BigDecimal valor;

	public Tarifa(BigDecimal valor) {
		this.valor = valor;
	}

	public BigDecimal getValor() { //posible cambio a mostrar
		return valor;
	}

	public void setValor(BigDecimal valor) {
		this.valor = valor;
	}
	
	
}
