package Controlador;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.net.MalformedURLException;
import java.util.Calendar;
import java.util.Date;

import Banco.ATM;
import Banco.Banco;
import Banco.Billetero;
import Banco.LectorTarjeta;
import Banco.ReconocedorBilletes;
import Banco.Tarifa;
import Banco.TarjetaATM;
import Banco.Usuario;
import Cuentas.CajaAhorro;
import Cuentas.CuentaCorriente;
import Cuentas.CuentaSueldo;
import Pantalla.Pantalla;

public class Principal {

	public static void main(String[] args) throws MalformedURLException {
		
		Usuario usuarioAdmin = new Usuario(99999999,"Modo","Administrador");
		
		TarjetaATM tarjetaAdmin = new TarjetaATM(BigInteger.valueOf(0),2364,true,usuarioAdmin);
		
		if(args[0].equals("serializar")) {
			ReconocedorBilletes reconocedor = new ReconocedorBilletes();
		
			reconocedor.agregarBilletesPermitidos(100);
			reconocedor.agregarBilletesPermitidos(200);
			reconocedor.agregarBilletesPermitidos(500);
		
			Billetero billetero100 = new Billetero(BigDecimal.valueOf(100),1000);
			Billetero billetero200 = new Billetero(BigDecimal.valueOf(200),1000);
			Billetero billetero500 = new Billetero(BigDecimal.valueOf(500),1000);
		
			LectorTarjeta tarjetero = new LectorTarjeta(tarjetaAdmin);
		
			ATM atm = new ATM(1, "Avenida Pedro Luro & San Luis", false, reconocedor, tarjetero);
		
			atm.agregarBilletero(billetero100);
			atm.agregarBilletero(billetero200);
			atm.agregarBilletero(billetero500);
		
			Tarifa mantenimientoCA = new Tarifa(BigDecimal.valueOf(150));
			Tarifa mantenimientoCC = new Tarifa(BigDecimal.valueOf(300));
			Tarifa otroBancoCA = new Tarifa(BigDecimal.valueOf(10));
			Tarifa otroBancoCC = new Tarifa(BigDecimal.valueOf(30));
		
			atm.agregarTarifa(mantenimientoCA); //Tarifa 0
			atm.agregarTarifa(mantenimientoCC); //Tarifa 1
			atm.agregarTarifa(otroBancoCA); //Tarifa 2
			atm.agregarTarifa(otroBancoCC); //Tarifa 3
		
			Banco banco1 = new Banco("Banco de la Plaza",BigInteger.valueOf(0),BigInteger.valueOf(1000));
			Banco banco2 = new Banco("Banco Santander Rio",BigInteger.valueOf(1001),BigInteger.valueOf(2000));
			Banco banco3 = new Banco("Banco BBVA",BigInteger.valueOf(2001),BigInteger.valueOf(3000));
			
			int tasaInteresCA=3;
			int tasaInteresCS=4;
		
			CajaAhorro cuenta1 = new CajaAhorro(BigInteger.valueOf(12345),BigDecimal.valueOf(250000),BigDecimal.valueOf(0),BigDecimal.valueOf(tasaInteresCA));
			CajaAhorro cuenta2 = new CajaAhorro(BigInteger.valueOf(16539),BigDecimal.valueOf(320000),BigDecimal.valueOf(0),BigDecimal.valueOf(tasaInteresCA));
			CajaAhorro cuenta3 = new CajaAhorro(BigInteger.valueOf(12230),BigDecimal.valueOf(106350),atm.getTarifas().get(2).getValor(),BigDecimal.valueOf(tasaInteresCA));
			
			int limiteExtraccionCC = 3;
		
			CuentaCorriente cuenta4 = new CuentaCorriente(BigInteger.valueOf(29065),BigDecimal.valueOf(125000),atm.getTarifas().get(3).getValor(),BigDecimal.valueOf(2000),limiteExtraccionCC);
			CuentaCorriente cuenta5 = new CuentaCorriente(BigInteger.valueOf(20763),BigDecimal.valueOf(223000),atm.getTarifas().get(3).getValor(),BigDecimal.valueOf(2000),limiteExtraccionCC);
			CuentaCorriente cuenta6 = new CuentaCorriente(BigInteger.valueOf(29973),BigDecimal.valueOf(339210),atm.getTarifas().get(3).getValor(),BigDecimal.valueOf(3000),limiteExtraccionCC);
		
			cuenta4.reinicio();
			cuenta5.reinicio();
			cuenta6.reinicio();
		
			CuentaSueldo cuenta7 = new CuentaSueldo(BigInteger.valueOf(30546),BigDecimal.valueOf(325000),BigDecimal.valueOf(0),BigDecimal.valueOf(tasaInteresCS),"30-50279317-5");
			CuentaSueldo cuenta8 = new CuentaSueldo(BigInteger.valueOf(33891),BigDecimal.valueOf(402000),BigDecimal.valueOf(0),BigDecimal.valueOf(tasaInteresCS),"30-53764771-6");
			CuentaSueldo cuenta9 = new CuentaSueldo(BigInteger.valueOf(38099),BigDecimal.valueOf(109000),BigDecimal.valueOf(0),BigDecimal.valueOf(tasaInteresCS),"30-63230798-1");
		
			Usuario usuario1 = new Usuario(26325986,"Juan","Dominguez");
			Usuario usuario2 = new Usuario(30764989,"Laura","Estevanez");
			Usuario usuario3 = new Usuario(17687223,"Florencio","Etcheverria");
			Usuario usuario4 = new Usuario(25723999,"Julieta","Marquez");
			Usuario usuario5 = new Usuario(39223456,"Diego","Monterrey");
			Usuario usuario6 = new Usuario(22674020,"Martina","Sanchez");
		
			usuario1.agregarCuenta(cuenta1);
			usuario1.agregarCuenta(cuenta7);
		
			usuario2.agregarCuenta(cuenta2);
			usuario2.agregarCuenta(cuenta9);
		
			usuario3.agregarCuenta(cuenta4);
			usuario3.agregarCuenta(cuenta8);
		
			usuario4.agregarCuenta(cuenta3);
		
			usuario5.agregarCuenta(cuenta5);
		
			usuario6.agregarCuenta(cuenta6);
		
			banco1.agregarTarjeta(tarjetaAdmin);
		
			TarjetaATM tarjeta1 = new TarjetaATM(BigInteger.valueOf(302),4295,true,usuario1);
			TarjetaATM tarjeta2 = new TarjetaATM(BigInteger.valueOf(509),6623,true,usuario2);
			TarjetaATM tarjeta3 = new TarjetaATM(BigInteger.valueOf(1523),6987,true,usuario3);
			TarjetaATM tarjeta4 = new TarjetaATM(BigInteger.valueOf(1973),5283,true,usuario4);
			TarjetaATM tarjeta5 = new TarjetaATM(BigInteger.valueOf(2309),2047,true,usuario5);
			TarjetaATM tarjeta6 = new TarjetaATM(BigInteger.valueOf(2783),9520,true,usuario6);
		
			banco1.agregarTarjeta(tarjeta1);
			banco1.agregarUsuario(usuario1);
			banco1.agregarTarjeta(tarjeta2);
			banco1.agregarUsuario(usuario2);
		
			banco2.agregarTarjeta(tarjeta3);
			banco2.agregarUsuario(usuario3);
			banco2.agregarTarjeta(tarjeta4);
			banco2.agregarUsuario(usuario4);
		
			banco3.agregarTarjeta(tarjeta5);
			banco3.agregarUsuario(usuario5);
			banco3.agregarTarjeta(tarjeta6);
			banco3.agregarUsuario(usuario6);
		
			atm.agregarBanco(banco1);
			atm.agregarBanco(banco2);
			atm.agregarBanco(banco3);
			
			Persistencia.persistirATM(atm);
		}
		if(args[0].equals("deserializar")) {
			ATM atm = Persistencia.instanciarATM();
			atm.getLector().setTarjeta(tarjetaAdmin); //se instancia para evitar cargar datos al inicio con NULL
			Calendar fecha = Calendar.getInstance();
			Boolean mantenimiento = atm.isMantenimientoRealizado();
			fecha.setTime(new Date());
			if(fecha.get(Calendar.DATE)==1 && !mantenimiento) {
				atm.mantenimientos();
				atm.setMantenimientoRealizado(true);
			}else {
				if(fecha.get(Calendar.DATE)==2 && mantenimiento) {
					atm.setMantenimientoRealizado(false);
				}
			}
			Pantalla pantalla = new Pantalla(atm);
			pantalla.setVisible(true);
		}
		
	}

}
