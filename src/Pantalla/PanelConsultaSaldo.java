package Pantalla;

import javax.swing.JPanel;
import javax.swing.JTabbedPane;

import Banco.ATM;
import Cuentas.Cuenta;

import java.awt.Font;
import java.util.Iterator;

import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JButton;
import javax.swing.JTextPane;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

@SuppressWarnings("serial")
public class PanelConsultaSaldo extends JPanel {

	private JComboBox<Cuenta> cbTipoCuenta = new JComboBox<Cuenta>();
	private ATM atm;
	
	public PanelConsultaSaldo(JTabbedPane tabbedPane, ATM atm) {
		setLayout(null);
		this.atm=atm;
		
		JPanel pantallaSaldo = new JPanel();
		pantallaSaldo.setBounds(0, 0, 682, 250);
		add(pantallaSaldo);
		pantallaSaldo.setLayout(null);
		
		
		cbTipoCuenta.setBounds(30, 68, 217, 22);
		
		pantallaSaldo.add(cbTipoCuenta);
		
		JLabel lblCuenta = new JLabel("Seleccione cuenta");
		lblCuenta.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblCuenta.setBounds(30, 39, 150, 22);
		pantallaSaldo.add(lblCuenta);
		
		JButton btnVolver = new JButton("Volver");
		btnVolver.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				tabbedPane.setSelectedIndex(1);
			}
		});
		btnVolver.setBounds(30, 140, 97, 25);
		pantallaSaldo.add(btnVolver);
		
		JTextPane consultaSaldo = new JTextPane();
		consultaSaldo.setFont(new Font("Tahoma", Font.PLAIN, 15));
		consultaSaldo.setBounds(300, 39, 370, 180);
		pantallaSaldo.add(consultaSaldo);
		
		JButton btnContinuar = new JButton("Continuar");
		btnContinuar.setBounds(90, 178, 97, 25);
		pantallaSaldo.add(btnContinuar);
		btnContinuar.setVisible(false);
		btnContinuar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				btnContinuar.setVisible(false);
				consultaSaldo.setText("");
				tabbedPane.setSelectedIndex(8);
			}
		});
		
		JButton btnSeleccionar = new JButton("Confirmar");
		btnSeleccionar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				consultaSaldo.setText("");
				Cuenta cuentaSeleccionada = (Cuenta) cbTipoCuenta.getSelectedItem();
				consultaSaldo.setText(cuentaSeleccionada.consultaSaldo());
				btnContinuar.setVisible(true);
			}
		});
		btnSeleccionar.setBounds(150, 140, 97, 25);
		pantallaSaldo.add(btnSeleccionar);
		
	}
	
	public void actualizar() {
		cbTipoCuenta.removeAllItems();
		for(Iterator<Cuenta> iterator = atm.getLector().getTarjeta().getUsuario().getListaCuentas().iterator(); iterator.hasNext();) {
			cbTipoCuenta.addItem(iterator.next());
		}
	}
}
