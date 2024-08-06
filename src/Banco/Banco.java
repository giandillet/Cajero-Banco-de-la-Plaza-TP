package Banco;
import java.io.Serializable;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;

public class Banco implements Serializable{
	
	private static final long serialVersionUID = -7438639286297577314L;
	
	private String nombre;
	private BigInteger minRango;
	private BigInteger maxRango;
	private List<Usuario> ListaUsuarios;
	private List<TarjetaATM> ListaTarjetas;
	
	public Banco(String nombre, BigInteger minRango, BigInteger maxRango) {
		super();
		this.nombre = nombre;
		this.minRango = minRango;
		this.maxRango = maxRango;
		ListaUsuarios = new ArrayList<>();
		ListaTarjetas = new ArrayList<>();
	}
	
	public void agregarUsuario(Usuario usuario) {
		ListaUsuarios.add(usuario);
	}
	
	public void agregarTarjeta(TarjetaATM tarjeta) {
		ListaTarjetas.add(tarjeta);
	}

	public String getNombre() {
		return nombre;
	}

	public BigInteger getMinRango() {
		return minRango;
	}

	public BigInteger getMaxRango() {
		return maxRango;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public void setMinRango(BigInteger minRango) {
		this.minRango = minRango;
	}

	public void setMaxRango(BigInteger maxRango) {
		this.maxRango = maxRango;
	}
	
	public boolean ValidarTarjeta(BigInteger id, int pin) {
		int i=0;
		while(i<ListaTarjetas.size() && id!=ListaTarjetas.get(i).getID()) {
			i++;
		}
		if (i==ListaTarjetas.size()) {
			return false;
		} else {
			if (ListaTarjetas.get(i).getPIN()==pin) {
				return true;
			} else {
				return false;
			}
		}
	}
	
	public void deshabilitar(BigInteger id) {
		int i=0;
		while(i<ListaTarjetas.size() && id!=ListaTarjetas.get(i).getID()) {
			i++;
		}
		ListaTarjetas.remove(i);
	}

	public List<Usuario> getListaUsuarios() {
		return ListaUsuarios;
	}

	public List<TarjetaATM> getListaTarjetas() {
		return ListaTarjetas;
	}

	public void setListaUsuarios(List<Usuario> listaUsuarios) {
		ListaUsuarios = listaUsuarios;
	}

	public void setListaTarjetas(List<TarjetaATM> listaTarjetas) {
		ListaTarjetas = listaTarjetas;
	}
	
	public void mantener(List<Tarifa> ListaTarifas) {
		int i=0; int j;
		while(i<ListaUsuarios.size()){
			Usuario user = ListaUsuarios.get(i);
			j=0;
			while(j<user.getListaCuentas().size()) {
				if(user.getListaCuentas().get(j).tipoCta().equals("CajaAhorro")) {
					user.getListaCuentas().get(j).setSaldo(user.getListaCuentas().get(j).getSaldo().subtract(ListaTarifas.get(0).getValor()));
					user.getListaCuentas().get(j).liquidacionIntereses();
				}else {
					if(user.getListaCuentas().get(j).tipoCta().equals("CuentaCorriente")) {
						user.getListaCuentas().get(j).setSaldo(user.getListaCuentas().get(j).getSaldo().subtract(ListaTarifas.get(1).getValor()));
						user.getListaCuentas().get(j).reinicio();
					}
				}//fin else cuentas
				j++;
			}//fin lista de cuentas
			i++;
		}//fin lista de usuarios
	}//fin funcion
	
}
