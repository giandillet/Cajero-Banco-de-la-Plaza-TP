package Pantalla;

import javax.swing.JPanel;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.JPasswordField;
import javax.swing.JTabbedPane;
import javax.swing.JTextPane;

import Banco.ATM;
import Controlador.Persistencia;

import java.awt.event.ActionListener;
import java.util.Date;
import java.awt.event.ActionEvent;

@SuppressWarnings("serial")
public class PanelCambioClave extends JPanel {
	private JPasswordField clave;

	/**
	 * Create the panel.
	 */
	public PanelCambioClave(JTabbedPane tabbedPane, ATM atm) {
		setLayout(null);
		
		JPanel pantallaClave = new JPanel();
		pantallaClave.setBounds(0, 0, 682, 250);
		add(pantallaClave);
		pantallaClave.setLayout(null);
		
		JTextPane ticket = new JTextPane();
		ticket.setFont(new Font("Tahoma", Font.PLAIN, 15));
		ticket.setBounds(300, 39, 370, 180);
		pantallaClave.add(ticket);
		
		JLabel lblIngreseSuClave = new JLabel("Ingrese su clave actual");
		lblIngreseSuClave.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblIngreseSuClave.setBounds(30, 80, 160, 20);
		pantallaClave.add(lblIngreseSuClave);
		
		clave = new JPasswordField();
		clave.setBounds(30, 110, 207, 22);
		pantallaClave.add(clave);
		
		JLabel lblIngreseSuNueva = new JLabel("Ingrese su nueva clave");
		lblIngreseSuNueva.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblIngreseSuNueva.setBounds(30, 80, 160, 20);
		pantallaClave.add(lblIngreseSuNueva);
		lblIngreseSuNueva.setVisible(false);
		
		int[] intento = {0};
		
		JButton btnVolver = new JButton("Volver");
		btnVolver.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				tabbedPane.setSelectedIndex(1);
			}
		});
		btnVolver.setBounds(30, 180, 97, 25);
		pantallaClave.add(btnVolver);
		
		JButton btnContinuar = new JButton("Continuar");
		btnContinuar.setBounds(90, 212, 97, 25);
		pantallaClave.add(btnContinuar);
		btnContinuar.setVisible(false);
		btnContinuar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				btnContinuar.setVisible(false);
				ticket.setText("");
				tabbedPane.setSelectedIndex(8);
			}
		});
		
		JButton btnConfirmar = new JButton("Confirmar");
		btnConfirmar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if(lblIngreseSuClave.isVisible()) { //Si el campo clave es NULL se toma como un error
					String contrasena = new String(clave.getPassword());
					int clave2 = Integer.parseInt(contrasena);
					boolean aprobado = false;
					if(intento[0]<3 && !aprobado) {
						if(atm.validar(atm.getLector().getTarjeta().getID(), clave2)) {
							aprobado=true;
							lblIngreseSuClave.setVisible(false);
							lblIngreseSuNueva.setVisible(true);
							clave.setText("");
						}else {
							intento[0]++;
							JOptionPane.showMessageDialog(null, "Clave Incorrecta");
						}
					}
					if(intento[0]==3) {
						JOptionPane.showMessageDialog(null, atm.getLector().retenerTarjeta());
						btnConfirmar.setEnabled(false);
						btnVolver.setEnabled(false);
						atm.getLector().getTarjeta().setHabilitada(false);
						Persistencia.persistirATM(atm);
					}
				}else{ //ya se realizo el if y lblIngreseSuClave ya no es visible, pero si lo es lblIngreseSuNueva
					if(clave.getPassword().toString().compareTo("")==0) {
						JOptionPane.showMessageDialog(null, "Ingrese su nueva clave");
					}else{
						String contrasena = new String(clave.getPassword());
						int clave = Integer.parseInt(contrasena);
						atm.getLector().getTarjeta().setPIN(clave);
						JOptionPane.showMessageDialog(null, "Clave cambiada con exito");
						Date fecha = new Date();
						ticket.setText(fecha + "\n\nTarjeta: "+atm.getLector().getTarjeta().getID()+"\nClave PIN cambiada con exito");
						btnContinuar.setVisible(true);
					}
				}
			}
		});
		btnConfirmar.setBounds(150, 180, 97, 25);
		pantallaClave.add(btnConfirmar);
		
	}
}
