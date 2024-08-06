package Pantalla;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Iterator;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JTextPane;

import Banco.ATM;
import Cuentas.Cuenta;

@SuppressWarnings("serial")
public class PanelMovimientos extends JPanel {
	
	private JComboBox<Cuenta> cbTipoCuenta = new JComboBox<Cuenta>();
	private ATM atm;
	
	public PanelMovimientos(JTabbedPane tabbedPane, ATM atm) {
		setLayout(null);
		this.atm=atm;
		
		JPanel pantallaMovimientos = new JPanel();
		pantallaMovimientos.setBounds(0, 0, 682, 250);
		add(pantallaMovimientos);
		pantallaMovimientos.setLayout(null);
		
		cbTipoCuenta.setBounds(30, 68, 217, 22);
		
		pantallaMovimientos.add(cbTipoCuenta);
		
		JLabel lblCuenta = new JLabel("Seleccione cuenta");
		lblCuenta.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblCuenta.setBounds(30, 39, 150, 22);
		pantallaMovimientos.add(lblCuenta);
		
		JButton btnVolver = new JButton("Volver");
		btnVolver.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				tabbedPane.setSelectedIndex(1);
			}
		});
		btnVolver.setBounds(30, 140, 97, 25);
		pantallaMovimientos.add(btnVolver);
		
		JTextPane consultaMovimiento = new JTextPane();
		consultaMovimiento.setFont(new Font("Tahoma", Font.PLAIN, 15));
		consultaMovimiento.setBounds(300, 39, 370, 180);
		pantallaMovimientos.add(consultaMovimiento);
		
		JScrollPane sp = new JScrollPane(consultaMovimiento);
		sp.setBounds(300, 39, 370, 180);
		pantallaMovimientos.add(sp);
		
		JButton btnContinuar = new JButton("Continuar");
		btnContinuar.setBounds(90, 178, 97, 25);
		pantallaMovimientos.add(btnContinuar);
		btnContinuar.setVisible(false);
		btnContinuar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				consultaMovimiento.setText("");
				tabbedPane.setSelectedIndex(8);
			}
		});
		
		JButton btnSeleccionar = new JButton("Confirmar");
		btnSeleccionar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				Cuenta cuentaSeleccionada = (Cuenta) cbTipoCuenta.getSelectedItem();
				consultaMovimiento.setText(cuentaSeleccionada.consultaMovimientos());
				btnContinuar.setVisible(true);
			}
		});
		btnSeleccionar.setBounds(150, 140, 97, 25);
		pantallaMovimientos.add(btnSeleccionar);
	}
	
	public void actualizar() {
		cbTipoCuenta.removeAllItems();
		for(Iterator<Cuenta> iterator = atm.getLector().getTarjeta().getUsuario().getListaCuentas().iterator(); iterator.hasNext();) {
			cbTipoCuenta.addItem(iterator.next());
		}
	}
}
