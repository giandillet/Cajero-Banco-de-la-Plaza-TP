package Banco;

import java.io.Serializable;
import java.math.BigInteger;

public class LectorTarjeta implements Serializable{

	private static final long serialVersionUID = 2086427556387554117L;
	
	private TarjetaATM tarjeta;

	public LectorTarjeta(TarjetaATM tarjeta) {
		this.setTarjeta(tarjeta);
	}
	
	public BigInteger leerTarjeta(TarjetaATM Tarjeta) {
		return tarjeta.getID();
	}
	
	public void setTarjeta(TarjetaATM tarjeta) {
		this.tarjeta = tarjeta;
	}
	
	public String expulsarTarjeta() {
		this.setTarjeta(null);
		return "La tarjeta esta siendo expulsada";
	}
	
	public String retenerTarjeta() {
		return "Tarjeta retenida y deshabilitada";
	}

	public TarjetaATM getTarjeta() {
		return tarjeta;
	}

	
}
