package Cuentas;

import java.math.BigDecimal;
import java.math.BigInteger;

public class CajaAhorro extends Cuenta{
	
	private static final long serialVersionUID = 4505222342357808345L;
	
	private BigDecimal tasaInteres;
	private BigInteger limiteExtraccionDia;
	
	public CajaAhorro(BigInteger CBU, BigDecimal saldo, BigDecimal impDebOtroBanco, BigDecimal tasaInteres) {
		super(CBU, saldo, impDebOtroBanco);
		this.tasaInteres=tasaInteres;
	}

	public BigDecimal getTasaInteres() {
		return tasaInteres;
	}

	public BigInteger getLimiteExtraccionDia() {
		return limiteExtraccionDia;
	}

	public void setTasaInteres(BigDecimal tasaInteres) {
		this.tasaInteres = tasaInteres;
	}

	public void setLimiteExtraccionDia() {
		this.limiteExtraccionDia = BigInteger.valueOf(5000);
	}

	@Override
	public boolean verificaSaldo(BigDecimal monto) {
		boolean verificar=false;
		if(getSaldo().compareTo(monto)==1 || getSaldo().compareTo(monto)==0) {
			verificar=true;
		}
		return verificar;
	}

	@Override
	public void extraer(BigDecimal monto) {
		setSaldo(getSaldo().subtract(monto));
	}
	
	public void liquidacionIntereses() {
		BigDecimal interes = (tasaInteres.multiply(getSaldo())).divide(BigDecimal.valueOf(100));
		setSaldo(getSaldo().add(interes));
	}

	@Override
	public void reinicio() {
		// TODO Auto-generated method stub
		
	}

}
