package Pantalla;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.border.EmptyBorder;

import Banco.ATM;
import Banco.Banco;
import Banco.TarjetaATM;
import Controlador.Persistencia;

import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.math.BigInteger;
import java.net.MalformedURLException;
import java.util.Iterator;

import javax.swing.JTabbedPane;

import java.awt.Color;

@SuppressWarnings("serial")
public class Pantalla extends JFrame {

	private JPanel PanelPrincipal;

	/**
	 * Create the frame.
	 * @throws MalformedURLException 
	 */
	public Pantalla(ATM atm) throws MalformedURLException {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 735, 448);
		PanelPrincipal = new JPanel();
		PanelPrincipal.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(PanelPrincipal);
		PanelPrincipal.setLayout(null);
		
		JLabel lblCajeroBanelcoSucursal = new JLabel("Cajero Banelco sucursal Banco De La Plaza");
		lblCajeroBanelcoSucursal.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblCajeroBanelcoSucursal.setBounds(199, 13, 306, 16);
		PanelPrincipal.add(lblCajeroBanelcoSucursal);
		
		JPanel panelTarjeta = new JPanel();
		panelTarjeta.setBorder(new EmptyBorder(0, 0, 0, 0));
		panelTarjeta.setBackground(new Color(240, 240, 240));
		panelTarjeta.setBounds(0, 42, 970, 48);
		PanelPrincipal.add(panelTarjeta);
		panelTarjeta.setVisible(true);
		
		JLabel lblIngreseSuPin = new JLabel("Ingrese su pin");
		lblIngreseSuPin.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblIngreseSuPin.setBounds(12, 5, 100, 23);
		panelTarjeta.add(lblIngreseSuPin);
		
		JComboBox<TarjetaATM> cbTarjetas = new JComboBox<TarjetaATM>();
		cbTarjetas.setBounds(124, 5, 120, 22);
		for(Banco bco : atm.getBancos()) {
			for(Iterator<TarjetaATM> iterator = bco.getListaTarjetas().iterator(); iterator.hasNext();) {
				cbTarjetas.addItem(iterator.next());
			}
		}
		panelTarjeta.setLayout(null);
		panelTarjeta.add(cbTarjetas);
		
		JPasswordField passwordField;
		passwordField = new JPasswordField();
		passwordField.setBounds(270, 5, 150, 22);
		panelTarjeta.add(passwordField);
		
		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		tabbedPane.setBounds(0, 42, 970, 398);
		PanelPrincipal.add(tabbedPane);
		
		PanelMenuPrincipal menuPrincipal = new PanelMenuPrincipal(tabbedPane, atm);
		PanelInteraccionDinero menuRetiro = new PanelInteraccionDinero(tabbedPane, atm,"Retiro");
		PanelInteraccionDinero menuDeposito = new PanelInteraccionDinero(tabbedPane, atm,"Deposito");
		PanelConsultaSaldo menuSaldo = new PanelConsultaSaldo(tabbedPane, atm);
		PanelTransferencia menuTransferencia = new PanelTransferencia(tabbedPane, atm);
		PanelMovimientos menuMovimientos = new PanelMovimientos(tabbedPane, atm);
		PanelAdmin menuAdmin = new PanelAdmin(tabbedPane, atm);
		
		JButton btnConfirmar = new JButton("Confirmar");
		
		JButton btnLiberarTarjeta = new JButton("Liberar");
		btnLiberarTarjeta.setVisible(false);
		btnLiberarTarjeta.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				String password = new String(passwordField.getPassword());
				int clave = Integer.parseInt(password);
				if(clave==2364) {
					JOptionPane.showMessageDialog(null, atm.getLector().expulsarTarjeta());
					lblIngreseSuPin.setText("Ingrese su pin");
					passwordField.setText("");
					cbTarjetas.setEnabled(true);
					btnConfirmar.setEnabled(true);
					btnLiberarTarjeta.setVisible(false);
					Persistencia.persistirATM(atm);
				} else {
					JOptionPane.showMessageDialog(null, "Clave Incorrecta");
				}
			}
		});
		btnLiberarTarjeta.setBounds(546, 5, 100, 25);
		panelTarjeta.add(btnLiberarTarjeta);
		
		btnConfirmar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				atm.getLector().setTarjeta((TarjetaATM) cbTarjetas.getSelectedItem());
				BigInteger tarjeta = atm.getLector().leerTarjeta(atm.getLector().getTarjeta());
				String password = new String(passwordField.getPassword());
				int clave = Integer.parseInt(password);
				boolean aprobado = false;
				if(tarjeta.compareTo(BigInteger.valueOf(0))==0) {
					if(atm.validar(tarjeta,clave)) {
						atm.setModoMantenimiento(true);
						menuAdmin.actualizar();
						passwordField.setText("");
						tabbedPane.setSelectedIndex(9); //Va al menu de Administrador
					}else {
						JOptionPane.showMessageDialog(null, "Clave Incorrecta");
					}
				}else {
					if(atm.getLector().getTarjeta().getIntentos()<2 && !aprobado) {
						if(atm.validar(tarjeta, clave)) {
							aprobado=true;
							cbTarjetas.setSelectedIndex(0);
							passwordField.setText("");
							Persistencia.persistirATM(atm);
							menuPrincipal.actualizar();
							menuRetiro.actualizar();
							menuDeposito.actualizar();
							menuSaldo.actualizar();
							menuTransferencia.actualizar();
							menuMovimientos.actualizar();
							tabbedPane.setSelectedIndex(1); //Va al menu principal
						}else {
							atm.getLector().getTarjeta().setIntentos(atm.getLector().getTarjeta().getIntentos()+1);
							if(atm.getLector().getTarjeta().getIntentos()==1 || atm.getLector().getTarjeta().getIntentos()==2) {
								JOptionPane.showMessageDialog(null, "Clave Incorrecta");
								passwordField.setText("");
							}
						}
					}else {
						JOptionPane.showMessageDialog(null, atm.getLector().retenerTarjeta());
						passwordField.setText("");
						lblIngreseSuPin.setText("Bloqueado");
						cbTarjetas.setEnabled(false);
						btnConfirmar.setEnabled(false);
						btnLiberarTarjeta.setVisible(true);
						atm.getLector().getTarjeta().setHabilitada(false);
						atm.deshabilitar(tarjeta);
						cbTarjetas.removeItem(cbTarjetas.getSelectedItem());
						cbTarjetas.setSelectedIndex(0);
					}
				}
			}
		});
		btnConfirmar.setBounds(431, 5, 100, 25);
		panelTarjeta.add(btnConfirmar);
		
		PanelLogo menuLogo = new PanelLogo(atm);
		PanelCambioClave menuClave = new PanelCambioClave(tabbedPane, atm);
		PanelContinuar menuContinuar = new PanelContinuar(tabbedPane, atm);
		PanelABM menuABM = new PanelABM(tabbedPane, atm.getBancos().get(0));
		
		tabbedPane.addTab("Pantalla Logo", menuLogo); //0
		tabbedPane.addTab("Pantalla Principal", menuPrincipal); //1
		tabbedPane.addTab("Pantalla Retiro", menuRetiro); //2
		tabbedPane.addTab("Pantalla Deposito", menuDeposito); //3
		tabbedPane.addTab("Pantalla Clave", menuClave); //4
		tabbedPane.addTab("Pantalla Saldo", menuSaldo); //5
		tabbedPane.addTab("Pantalla Transferencia", menuTransferencia); //6
		tabbedPane.addTab("Pantalla Movimientos", menuMovimientos); //7
		tabbedPane.addTab("Pantalla Continuar", menuContinuar); //8
		tabbedPane.addTab("Pantalla Administrador", menuAdmin); //9
		tabbedPane.addTab("Pantalla ABM", menuABM); //10
		
		tabbedPane.setSelectedIndex(0);
	}
}
