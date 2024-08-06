package Banco;
import java.io.Serializable;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Transaccion implements Serializable{

	private static final long serialVersionUID = -2643070322868203261L;
	
	protected Date fecha;
	protected BigDecimal valor;
	public TipoTransaccion tipo;
	
	public Transaccion(Date fecha, BigDecimal valor, TipoTransaccion tipo) {
		super();
		this.fecha = fecha;
		this.valor = valor;
		this.tipo = tipo;
	}

	public String getFecha() {
		SimpleDateFormat formato = new SimpleDateFormat("dd/MM/aaaa - HH:mm:ss");
		String FechaFormateada = formato.format(this.fecha);
		return FechaFormateada;
	}

	public BigDecimal getValor() {
		return valor;
	}

	public TipoTransaccion getTipo() {
		return tipo;
	}

	public void setFecha(Date fecha) {
		this.fecha = fecha;
	}

	public void setValor(BigDecimal valor) {
		this.valor = valor;
	}

	public void setTipo(TipoTransaccion tipo) {
		this.tipo = tipo;
	}

	public String toString() {
		StringBuilder str = new StringBuilder();
		str.append("Resumen de Transaccion");
		str.append("\n");
		str.append(tipo.toString()).append("\t").append(getFecha()).append("\n");
		str.append("$"+getValor()).append("\n");
		return str.toString();
	}

}
