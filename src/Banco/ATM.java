package Banco;
import java.io.Serializable;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;

import Cuentas.Cuenta;

public class ATM implements Serializable{
	
	private static final long serialVersionUID = 2315603211111049631L;
	
	private int ID;
	private String ubicacion;
	private boolean modoMantenimiento;
	private List<Banco> ListaBancos;
	private List<Tarifa> ListaTarifas;
	private ReconocedorBilletes reconocedor;
	private List<Billetero> ListaBilleteros;
	private LectorTarjeta lector;
	private boolean mantenimientoRealizado;
	
	public ATM(int ID, String ubicacion, boolean modoMantenimiento, ReconocedorBilletes reconocedor, LectorTarjeta lector) {
		this.ID = ID;
		this.ubicacion = ubicacion;
		this.modoMantenimiento = modoMantenimiento;
		this.reconocedor = reconocedor;
		this.lector = lector;
		ListaBancos = new ArrayList<>();
		ListaTarifas = new ArrayList<>();
		ListaBilleteros = new ArrayList<>();
	}
	
	public boolean validar(BigInteger idTarjetaATM, int pin) {
		int i=0; boolean salida=false;
		boolean verifica = false;
		while(i<ListaBancos.size() && !salida) {
			if((idTarjetaATM.compareTo(ListaBancos.get(i).getMinRango())>=0) && (idTarjetaATM.compareTo(ListaBancos.get(i).getMaxRango())<=0)) {
				verifica = (ListaBancos.get(i).ValidarTarjeta(idTarjetaATM, pin));
				salida=true;
			} else {
				i++;
			}
		}
		return verifica;
	}
	
	public void deshabilitar(BigInteger idTarjetaATM) {
		int i=0; boolean fin=false;
		while(i<ListaBancos.size() && !fin) {
			if((idTarjetaATM.compareTo(ListaBancos.get(i).getMinRango())>=0) && (idTarjetaATM.compareTo(ListaBancos.get(i).getMaxRango())<=0)) {
				ListaBancos.get(i).deshabilitar(idTarjetaATM);
				fin=true;
			}
		}
	}
	
	public void agregarBanco(Banco banco) {
		ListaBancos.add(banco);
	}
	
	public void agregarTarifa(Tarifa tarifa) {
		ListaTarifas.add(tarifa);
	}
	
	public void agregarBilletero(Billetero billetero) {
		ListaBilleteros.add(billetero);
	}

	public int getID() {
		return ID;
	}

	public String getUbicacion() {
		return ubicacion;
	}
	
	public List<Banco> getBancos(){
		return ListaBancos;
	}
	
	public List<Billetero> getBilleteros(){
		return ListaBilleteros;
	}
	
	public List<Tarifa> getTarifas(){
		return ListaTarifas;
	}
	
	public Billetero buscarBilletero(int billete) {
		int i=0;
		boolean encontrado=false;
		while(i<ListaBilleteros.size() && !encontrado) {
			if(!ListaBilleteros.get(i).getValorBillete().equals(BigDecimal.valueOf(billete))) {
				i++;
			}else {
				encontrado=true;
			}
		}
		return ListaBilleteros.get(i);
	}

	public boolean isModoMantenimiento() {
		return modoMantenimiento;
	}

	public void setID(int iD) {
		ID = iD;
	}

	public void setUbicacion(String ubicacion) {
		this.ubicacion = ubicacion;
	}

	public void setModoMantenimiento(boolean modoMantenimiento) {
		this.modoMantenimiento = modoMantenimiento;
	}

	public ReconocedorBilletes getReconocedor() {
		return reconocedor;
	}

	public void setReconocedor(ReconocedorBilletes reconocedor) {
		this.reconocedor = reconocedor;
	}

	public LectorTarjeta getLector() {
		return lector;
	}

	public void setLector(LectorTarjeta lector) {
		this.lector = lector;
	}
	
	public BigDecimal saldo() {
		int i=0;
		BigDecimal suma=BigDecimal.valueOf(0);
		Billetero aux;
		while(i<ListaBilleteros.size()) {
			aux=ListaBilleteros.get(i);
			suma = suma.add(aux.getValorBillete().multiply(BigDecimal.valueOf(aux.getCantidad())));
			i++;
		}
		return suma;
	}
	
	public boolean saldoSuficiente(BigDecimal monto) {
		boolean verifica=false;
		if(monto.compareTo(saldo())==0 || monto.compareTo(saldo())==-1) {
			return true;
		}
		return verifica;
	}
	
	public void extraer(BigDecimal monto, TarjetaATM tarjeta, Cuenta cuenta) {
		int i = tarjeta.getUsuario().indiceCuenta(cuenta);
		if(tarjeta.getID().compareTo(BigInteger.valueOf(0))==0 || tarjeta.getID().compareTo(BigInteger.valueOf(0))==1 && tarjeta.getID().compareTo(BigInteger.valueOf(1000))==0 || tarjeta.getID().compareTo(BigInteger.valueOf(1000))==-1) {
			tarjeta.getUsuario().getListaCuentas().get(i).extraer(monto);
		}else {
			tarjeta.getUsuario().getListaCuentas().get(i).extraer(monto.add(tarjeta.getUsuario().getListaCuentas().get(i).getImpDebOtroBanco()));
		}
		
	}
	
	public void depositar(BigDecimal monto, TarjetaATM tarjeta, Cuenta cuenta) {
		int i = tarjeta.getUsuario().indiceCuenta(cuenta);
		if(tarjeta.getID().compareTo(BigInteger.valueOf(0))==0 || tarjeta.getID().compareTo(BigInteger.valueOf(0))==1 && tarjeta.getID().compareTo(BigInteger.valueOf(1000))==0 || tarjeta.getID().compareTo(BigInteger.valueOf(1000))==-1) {
			tarjeta.getUsuario().getListaCuentas().get(i).setSaldo(tarjeta.getUsuario().getListaCuentas().get(i).getSaldo().add(monto));
		}else {
			tarjeta.getUsuario().getListaCuentas().get(i).setSaldo(tarjeta.getUsuario().getListaCuentas().get(i).getSaldo().add(monto.subtract(tarjeta.getUsuario().getListaCuentas().get(i).getImpDebOtroBanco())));
		}
	}
	
	public void transferir(BigDecimal monto, TarjetaATM tarjeta, Cuenta cuentaEmisora, Cuenta cuentaReceptora) {
		int i = tarjeta.getUsuario().indiceCuenta(cuentaEmisora);
		if(tarjeta.getID().compareTo(BigInteger.valueOf(0))==0 || tarjeta.getID().compareTo(BigInteger.valueOf(0))==1 && tarjeta.getID().compareTo(BigInteger.valueOf(1000))==0 || tarjeta.getID().compareTo(BigInteger.valueOf(1000))==-1) {
			tarjeta.getUsuario().getListaCuentas().get(i).extraer(monto);
		}else {
			tarjeta.getUsuario().getListaCuentas().get(i).extraer(monto.add(tarjeta.getUsuario().getListaCuentas().get(i).getImpDebOtroBanco()));
		}
		cuentaReceptora.setSaldo(cuentaReceptora.getSaldo().add(monto));
	}
	
	public void mantenimientos() {
		int i=0;
		while(i<ListaBancos.size()){
			ListaBancos.get(i).mantener(ListaTarifas);
			i++;
		}
	}
	
	public String contadorBilleteros() {
		StringBuilder str = new StringBuilder();
		int i=0;
		while(i<ListaBilleteros.size()) {
			str.append(ListaBilleteros.get(i).control()).append("   ");
			i++;
		}
		return str.toString();
	}
	
	public void recargar(Billetero billetero, int cantidad) {
		int i=0;
		while(i<ListaBilleteros.size() && ListaBilleteros.get(i)!=billetero) { //al estar cargadas las listas en el combobox del panel de administrador nunca me voy a pasar
			i++;
		}
		ListaBilleteros.get(i).recargar(cantidad);
	}
	
	public void descargar(Billetero billetero, int cantidad) {
		int i=0;
		while(i<ListaBilleteros.size() && ListaBilleteros.get(i)!=billetero) {
			i++;
		}
		ListaBilleteros.get(i).descargar(cantidad);
	}

	public boolean isMantenimientoRealizado() {
		return mantenimientoRealizado;
	}

	public void setMantenimientoRealizado(boolean mantenimientoRealizado) {
		this.mantenimientoRealizado = mantenimientoRealizado;
	}
	
}
