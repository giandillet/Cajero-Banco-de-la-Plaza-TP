package Cuentas;

import java.math.BigDecimal;
import java.math.BigInteger;

public class CuentaSueldo extends CajaAhorro{

	private static final long serialVersionUID = 4577917142106194675L;
	
	private String cuitEmpleador;
	
	public CuentaSueldo(BigInteger CBU, BigDecimal saldo, BigDecimal impDebOtroBanco, BigDecimal tasaInteres, String cuit) {
		super(CBU, saldo, impDebOtroBanco,tasaInteres);
		setCuitEmpleador(cuit);
		// TODO Auto-generated constructor stub
	}

	public String getCuitEmpleador() {
		return cuitEmpleador;
	}

	public void setCuitEmpleador(String cuitEmpleador) {
		this.cuitEmpleador = cuitEmpleador;
	}

}
