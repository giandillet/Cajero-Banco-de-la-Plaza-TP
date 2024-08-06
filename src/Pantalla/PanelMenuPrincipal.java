package Pantalla;

import java.awt.Font;
import java.math.BigInteger;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;

import Banco.ATM;
import Controlador.Persistencia;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.SwingConstants;

@SuppressWarnings("serial")
public class PanelMenuPrincipal extends JPanel {

	private JLabel lblNewLabel = new JLabel();
	private JLabel lblConsultaDeMovimientos;
	private JButton btnMovimientos;
	private ATM atm;
	
	public PanelMenuPrincipal(JTabbedPane tabbedPane, ATM atm) {
		setLayout(null);
		this.atm=atm;
		JPanel OperacionesGrales = new JPanel();
		OperacionesGrales.setBounds(0, 0, 682, 250);
		add(OperacionesGrales);
		OperacionesGrales.setLayout(null);
		
		lblConsultaDeMovimientos = new JLabel("Consulta de Movimientos");
		lblConsultaDeMovimientos.setBounds(418, 175, 164, 25);
		OperacionesGrales.add(lblConsultaDeMovimientos);
		lblConsultaDeMovimientos.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblConsultaDeMovimientos.setVisible(false);
		
		btnMovimientos = new JButton("");
		btnMovimientos.setBounds(590, 175, 80, 25);
		OperacionesGrales.add(btnMovimientos);
		btnMovimientos.setVisible(false);
		btnMovimientos.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				tabbedPane.setSelectedIndex(7);
			}
		});
		
		JLabel lblConsultaDeSaldo = new JLabel("Consulta de Saldo");
		lblConsultaDeSaldo.setBounds(460, 115, 122, 25);
		OperacionesGrales.add(lblConsultaDeSaldo);
		lblConsultaDeSaldo.setFont(new Font("Tahoma", Font.PLAIN, 15));
		
		JButton btnSaldo = new JButton("");
		btnSaldo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				tabbedPane.setSelectedIndex(5);
			}
		});
		btnSaldo.setBounds(590, 115, 80, 25);
		OperacionesGrales.add(btnSaldo);
		
		JLabel lblCambioDeClave = new JLabel("Cambio de Clave");
		lblCambioDeClave.setBounds(466, 55, 116, 25);
		OperacionesGrales.add(lblCambioDeClave);
		lblCambioDeClave.setFont(new Font("Tahoma", Font.PLAIN, 15));
		
		JButton btnClave = new JButton("");
		btnClave.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				tabbedPane.setSelectedIndex(4);
			}
		});
		btnClave.setBounds(590, 55, 80, 25);
		OperacionesGrales.add(btnClave);
		
		JLabel lblTransferenciaDeDinero = new JLabel("Transferencia de Dinero");
		lblTransferenciaDeDinero.setBounds(102, 175, 170, 25);
		OperacionesGrales.add(lblTransferenciaDeDinero);
		lblTransferenciaDeDinero.setFont(new Font("Tahoma", Font.PLAIN, 15));
		
		JButton btnTransferencia = new JButton("");
		btnTransferencia.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				tabbedPane.setSelectedIndex(6);
			}
		});
		btnTransferencia.setBounds(12, 175, 80, 25);
		OperacionesGrales.add(btnTransferencia);
		
		JLabel lblDepositarDinero = new JLabel("Depositar Dinero");
		lblDepositarDinero.setBounds(102, 115, 116, 25);
		OperacionesGrales.add(lblDepositarDinero);
		lblDepositarDinero.setFont(new Font("Tahoma", Font.PLAIN, 15));
		
		JButton btnDepositar = new JButton("");
		btnDepositar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				tabbedPane.setSelectedIndex(3);
			}
		});
		btnDepositar.setBounds(12, 115, 80, 25);
		OperacionesGrales.add(btnDepositar);
		
		JLabel lblExtraer = new JLabel("Retirar Dinero");
		lblExtraer.setBounds(102, 55, 102, 25);
		OperacionesGrales.add(lblExtraer);
		lblExtraer.setFont(new Font("Tahoma", Font.PLAIN, 15));
		
		JButton btnExtraer = new JButton("");
		btnExtraer.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				tabbedPane.setSelectedIndex(2);
			}
		});
		btnExtraer.setBounds(12, 55, 80, 25);
		OperacionesGrales.add(btnExtraer);
		
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblNewLabel.setBounds(12, 0, 658, 32);
		OperacionesGrales.add(lblNewLabel);
		
		JButton btnSalir = new JButton("");
		btnSalir.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				JOptionPane.showMessageDialog(null, atm.getLector().expulsarTarjeta());
				Persistencia.persistirATM(atm);
				tabbedPane.setSelectedIndex(0);
			}
		});
		btnSalir.setBounds(244, 212, 80, 25);
		OperacionesGrales.add(btnSalir);
		
		JLabel lblSalir = new JLabel("Salida");
		lblSalir.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblSalir.setBounds(336, 212, 56, 25);
		OperacionesGrales.add(lblSalir);
		
		
		
	}
	public void actualizar() {
		lblNewLabel.setText("Bienvenido "+atm.getLector().getTarjeta().getUsuario().getNombre()+" "+atm.getLector().getTarjeta().getUsuario().getApellido()+" - Seleccione una Operacion");
		if((BigInteger.valueOf(1).compareTo(atm.getLector().getTarjeta().getID())==-1 || BigInteger.valueOf(1).compareTo(atm.getLector().getTarjeta().getID())==0)&& //Si el ID de la tarjeta es >=1 y <=1000
				(BigInteger.valueOf(1000).compareTo(atm.getLector().getTarjeta().getID())==1 || BigInteger.valueOf(1000).compareTo(atm.getLector().getTarjeta().getID())==0)) { //IDs asignados al Banco de la Plaza
			lblConsultaDeMovimientos.setVisible(true);
			btnMovimientos.setVisible(true);
		}else {
			lblConsultaDeMovimientos.setVisible(false);
			btnMovimientos.setVisible(false);
		}
	}
}
