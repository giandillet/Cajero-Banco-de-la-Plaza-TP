package Banco;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import Cuentas.Cuenta;

public class Usuario implements Serializable{
	
	private static final long serialVersionUID = -4719304602340946591L;
	
	private int ID;
	private String apellido;
	private String nombre;
	private List<Cuenta> ListaCuentas;
	
	public Usuario(int ID,String nombre , String apellido) {
		this.ID = ID;
		this.nombre = nombre;
		this.apellido = apellido;
		ListaCuentas = new ArrayList<>();
	}

	public int getID() {
		return ID;
	}

	public String getApellido() {
		return apellido;
	}

	public String getNombre() {
		return nombre;
	}

	public void setID(int iD) {
		ID = iD;
	}

	public void setApellido(String apellido) {
		this.apellido = apellido;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	
	public void agregarCuenta(Cuenta cuenta) {
		ListaCuentas.add(cuenta);
	}

	public List<Cuenta> getListaCuentas() {
		return ListaCuentas;
	}	
	
	public int indiceCuenta(Cuenta cuenta) {
		int k=0; boolean encontrado=false;
		while(k<getListaCuentas().size() && !encontrado) {
			if (getListaCuentas().get(k)!=cuenta) {
				k++;
			}else {
				encontrado=true;
			}
		}
		return k;
	}
	
	public String toString() {
		return getID() + " - "+getApellido();
	}
	
}
