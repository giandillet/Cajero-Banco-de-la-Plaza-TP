package Cuentas;

import java.math.BigDecimal;
import java.math.BigInteger;

public class CuentaCorriente extends Cuenta{
	
	private static final long serialVersionUID = 617152171427168427L;
	
	private BigDecimal descubierto;
	private int limiteExtracciones;
	
	public CuentaCorriente(BigInteger CBU, BigDecimal saldo, BigDecimal impDebOtroBanco, BigDecimal descubierto, int limiteExtracciones) {
		super(CBU, saldo, impDebOtroBanco);
		setDescubierto(descubierto);
		setLimiteExtracciones(limiteExtracciones);
		// TODO Auto-generated constructor stub
	}

	public BigDecimal getDescubierto() {
		return descubierto;
	}

	public void setDescubierto(BigDecimal descubierto) {
		this.descubierto = descubierto;
	}

	public int getLimiteExtracciones() {
		return limiteExtracciones;
	}

	public void setLimiteExtracciones(int limiteExtracciones) {
		this.limiteExtracciones = limiteExtracciones;
	}
	
	public void descontar() {
		setLimiteExtracciones(getLimiteExtracciones()-1);
	}
	
	public void reinicio() {
		setLimiteExtracciones(3);
	}
	
	public String consultaSaldo() {
		StringBuilder str = new StringBuilder();
		str.append(super.consultaSaldo());
		str.append("\n");
		str.append("Descubierto Disponible: $").append(descubierto);
		return str.toString();
	}

	@Override
	public boolean verificaSaldo(BigDecimal monto) {
		boolean verificar=false;
		if (limiteExtracciones==0) {
			monto.add(BigDecimal.valueOf(15));
		}
		if(getSaldo().compareTo(monto)==1 || getSaldo().compareTo(monto)==0) {
			verificar=true;
		}else{
			if(getSaldo().add(descubierto).compareTo(monto)==1 || getSaldo().add(descubierto).compareTo(monto)==0) {
				verificar=true;
			}
		}
		return verificar;
	}

	@Override
	public void extraer(BigDecimal monto) {
		if (limiteExtracciones==0) {
			monto.add(BigDecimal.valueOf(15));
		}else{
			descontar();
		}
		if(getSaldo().compareTo(monto)==1 || getSaldo().compareTo(monto)==0) {
			setSaldo(getSaldo().subtract(monto));
		}else {
			BigDecimal deuda = monto.subtract(getSaldo());
			setSaldo(getSaldo().subtract(monto.subtract(deuda)));
			setDescubierto(getDescubierto().subtract(deuda));
		}
		
	}

	@Override
	public void liquidacionIntereses() {
		// TODO Auto-generated method stub
	}
}
