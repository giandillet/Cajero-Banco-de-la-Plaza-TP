package Controlador;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

import Banco.ATM;

public class Persistencia {

	public static void persistirATM(ATM memoria) {
		try {
			FileOutputStream fos = new FileOutputStream("memoria.file");
			ObjectOutputStream oos = new ObjectOutputStream(fos);
			oos.writeObject(memoria);
			oos.flush();
			oos.close();
		} catch(Exception e) {
			System.out.println("Excepcion durante la serializacion: "+e);
			System.exit(0);
		}
	}
	
	public static ATM instanciarATM() {
		ATM memoria = null;
		try {
			FileInputStream fis = new FileInputStream("memoria.file");
			ObjectInputStream ois = new ObjectInputStream(fis);
			memoria = (ATM) ois.readObject();
			ois.close();
		} catch(Exception e) {
			System.out.println("Excepcion durante la deserealizacion: "+e);
			System.exit(0);
		}
		return memoria;
	}
}
