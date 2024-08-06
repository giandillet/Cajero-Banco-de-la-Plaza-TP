package Banco;
import java.io.Serializable;
import java.math.BigInteger;

public class TarjetaATM implements Serializable{

	private static final long serialVersionUID = 6553222628088364500L;
	
	private BigInteger id;
	private int PIN;
	private boolean habilitada;
	private Usuario usuario;
	private int intentos;
	
	public TarjetaATM(BigInteger id, int PIN, boolean habilitada, Usuario usuario) {
		this.id = id;
		this.PIN = PIN;
		this.habilitada = habilitada;
		this.usuario = usuario;
		setIntentos(0);
	}

	public BigInteger getID() {
		return id;
	}

	public int getPIN() {
		return PIN;
	}

	public boolean isHabilitada() {
		return habilitada;
	}

	public void setID(BigInteger id) {
		this.id = id;
	}

	public void setPIN(int pIN) {
		PIN = pIN;
	}

	public void setHabilitada(boolean habilitada) {
		this.habilitada = habilitada;
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	@Override
	public String toString() {
		return id.toString();
	}

	public int getIntentos() {
		return intentos;
	}

	public void setIntentos(int intentos) {
		this.intentos = intentos;
	}
}
