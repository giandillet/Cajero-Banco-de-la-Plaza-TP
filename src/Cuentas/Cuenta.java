package Cuentas;

import java.io.Serializable;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;

import Banco.Transaccion;

public abstract class Cuenta implements Serializable{
	
	private static final long serialVersionUID = -6792716175737372466L;
	
	private BigInteger CBU;
	private BigDecimal saldo;
	private BigDecimal impDebOtroBanco;
	private List<Transaccion> movimientos; 
	
	public Cuenta(BigInteger CBU, BigDecimal saldo, BigDecimal impDebOtroBanco) {
		this.CBU = CBU;
		this.saldo = saldo;
		this.impDebOtroBanco = impDebOtroBanco;
		movimientos = new ArrayList<>();
	}
	
	public void agregarTransaccion(Transaccion transaccion) {
		movimientos.add(transaccion);
	}
	
	public BigDecimal getSaldo() {
		return saldo;
	}
	
	public String tipoCta() {
		return this.getClass().getSimpleName();
	}

	public BigInteger getCBU() {
		return CBU;
	}

	public BigDecimal getImpDebOtroBanco() {
		return impDebOtroBanco;
	}

	public void setCBU(BigInteger cBU) {
		CBU = cBU;
	}

	public void setSaldo(BigDecimal saldo) {
		this.saldo = saldo;
	}

	public void setImpDebOtroBanco(BigDecimal impDebOtroBanco) {
		this.impDebOtroBanco = impDebOtroBanco;
	}
	
	public String toString() {
		return CBU.toString();
	}
	
	public abstract boolean verificaSaldo(BigDecimal monto);
	
	public abstract void extraer(BigDecimal monto);
	
	public abstract void liquidacionIntereses();
	
	public abstract void reinicio();

	public String consultaSaldo() {
		StringBuilder str = new StringBuilder();
		str.append("Tipo de Cuenta: ").append(this.tipoCta()).append("\n").append("\n");
		str.append("Cuenta: ").append(this.getCBU()).append("\n").append("\n");
		str.append("Saldo Disponible: $").append(this.getSaldo());
		return str.toString();
	}
	
	public String consultaMovimientos() {
		StringBuilder str = new StringBuilder();
		int i=0;
		while(i<movimientos.size()) {
			str.append(movimientos.get(i).toString());
			i++;
		}
		return str.toString();
	}
}
