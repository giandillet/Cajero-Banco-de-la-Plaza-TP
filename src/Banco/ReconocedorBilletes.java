package Banco;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class ReconocedorBilletes implements Serializable{

	private static final long serialVersionUID = -5401529790151313429L;
	
	private List<Integer> billetesPermitidos;
	private List<BigDecimal> validos;

	public ReconocedorBilletes() {
		billetesPermitidos = new ArrayList<>();
		validos = new ArrayList<>();
	}
	
	public void agregarValidos(BigDecimal sumaValida) {
		validos.add(sumaValida);
	}
	
	public void agregarBilletesPermitidos(int permitidos) {
		billetesPermitidos.add(permitidos);
	}

	public List<BigDecimal> getValidos() {
		return validos;
	}

	public void setValidos(List<BigDecimal> validos) {
		this.validos = validos;
	}

	public List<Integer> getBilletesPermitidos() {
		return billetesPermitidos;
	}

	public void setBilletesPermitidos(List<Integer> billetesPermitidos) {
		this.billetesPermitidos = billetesPermitidos;
	}
	
	public String cantidad() {
		String respuesta;
		if(validos==null) {
			respuesta="Vacio";
		}else{
			respuesta="Cargado";
		}
		return respuesta;
	}
	
	public void vaciar() {
		validos=null;
	}
}