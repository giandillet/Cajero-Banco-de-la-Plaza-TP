package Banco;
import java.math.BigDecimal;
import java.util.Date;

import Cuentas.Cuenta;

public class Transferencia extends Transaccion{
	
	private static final long serialVersionUID = 5337383419548380297L;
	
	private Cuenta otraCuenta;

	public Transferencia(Date fecha, BigDecimal valor, Cuenta otraCuenta) {
		super(fecha, valor, new TipoTransaccion("Transferencia",true));
		this.otraCuenta = otraCuenta;
		// TODO Auto-generated constructor stub
	}

	public Cuenta getOtraCuenta() {
		return otraCuenta;
	}

	public void setOtraCuenta(Cuenta otraCuenta) {
		this.otraCuenta = otraCuenta;
	}

	@Override
	public String toString() {
		StringBuilder str = new StringBuilder(super.toString());
		str.append("\n");
		str.append("Cuenta a transferir: ").append(getOtraCuenta());
		str.append("\n");
		return str.toString();
	}

}
