package Pantalla;

import java.awt.Font;
import java.util.Date;
import java.util.Iterator;


import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;
import javax.swing.JTextField;
import javax.swing.JTextPane;

import Banco.ATM;
import Banco.Banco;
import Banco.Transferencia;
import Cuentas.Cuenta;
import java.awt.event.ActionListener;
import java.math.BigDecimal;
import java.awt.event.ActionEvent;

@SuppressWarnings("serial")
public class PanelTransferencia extends JPanel {

	private JComboBox<Cuenta> cbTipoCuenta = new JComboBox<Cuenta>();
	private JComboBox<Cuenta> cbCBU = new JComboBox<Cuenta>();
	private ATM atm;
	
	public PanelTransferencia(JTabbedPane tabbedPane, ATM atm) {
		setLayout(null);
		this.atm=atm;
		
		JPanel pantallaTransferencia = new JPanel();
		pantallaTransferencia.setBounds(0, 0, 682, 250);
		add(pantallaTransferencia);
		
		JLabel lblCuenta = new JLabel("Seleccione cuenta");
		lblCuenta.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblCuenta.setBounds(30, 30, 150, 22);
		pantallaTransferencia.add(lblCuenta);
		
		
		cbTipoCuenta.setBounds(30, 53, 217, 22);
		
		pantallaTransferencia.add(cbTipoCuenta);
		
		
		cbCBU.setBounds(30, 99, 217, 22);
		
		pantallaTransferencia.setLayout(null);
		pantallaTransferencia.add(cbCBU);
		
		JLabel lblMonto = new JLabel("Ingrese Monto");
		lblMonto.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblMonto.setBounds(30, 130, 217, 22);
		pantallaTransferencia.add(lblMonto);
		
		JLabel label = new JLabel("$");
		label.setFont(new Font("Tahoma", Font.PLAIN, 15));
		label.setBounds(30, 162, 56, 16);
		pantallaTransferencia.add(label);
		
		JTextField txtMonto = new JTextField();
		txtMonto.setFont(new Font("Tahoma", Font.PLAIN, 15));
		txtMonto.setBounds(40, 160, 207, 22);
		pantallaTransferencia.add(txtMonto);
		txtMonto.setColumns(10);
		
		JTextPane ticket = new JTextPane();
		ticket.setFont(new Font("Tahoma", Font.PLAIN, 15));
		ticket.setBounds(300, 39, 370, 180);
		pantallaTransferencia.add(ticket);
		
		JButton btnContinuar = new JButton("Continuar");
		btnContinuar.setBounds(90, 220, 97, 25);
		pantallaTransferencia.add(btnContinuar);
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
				if(txtMonto.getText()!="") {
					int dinero = Integer.parseInt(txtMonto.getText());
					BigDecimal monto = BigDecimal.valueOf(dinero);
					Cuenta propia = (Cuenta) cbTipoCuenta.getSelectedItem();
					if(propia.verificaSaldo(monto)) {
						atm.transferir(monto, atm.getLector().getTarjeta(), propia, (Cuenta) cbCBU.getSelectedItem());
						Transferencia transferencia = new Transferencia(new Date() ,monto, (Cuenta) cbCBU.getSelectedItem());
						((Cuenta) cbCBU.getSelectedItem()).agregarTransaccion(transferencia);
						int i=0;
						while(atm.getLector().getTarjeta().getUsuario().getListaCuentas().get(i)!=(Cuenta) cbTipoCuenta.getSelectedItem()) {
							i++;
						}
						atm.getLector().getTarjeta().getUsuario().getListaCuentas().get(i).agregarTransaccion(transferencia);
						ticket.setText(transferencia.toString());
						btnContinuar.setVisible(true);
					}else {
						JOptionPane.showMessageDialog(null, "Saldo Insuficiente");
					}
				}else {
					JOptionPane.showMessageDialog(null, "Complete todos los campos");
				}
			}
		});
		btnConfirmar.setBounds(150, 195, 97, 25);
		pantallaTransferencia.add(btnConfirmar);
		
		JButton btnVolver = new JButton("Volver");
		btnVolver.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				tabbedPane.setSelectedIndex(1);
			}
		});
		btnVolver.setBounds(30, 195, 97, 25);
		pantallaTransferencia.add(btnVolver);
		
		JLabel labelCBU = new JLabel("Seleccione CBU a Transferir");
		labelCBU.setFont(new Font("Tahoma", Font.PLAIN, 15));
		labelCBU.setBounds(30, 76, 217, 22);
		pantallaTransferencia.add(labelCBU);
	}
	
	public void actualizar() {
		cbTipoCuenta.removeAllItems();
		cbCBU.removeAllItems();
		for(Iterator<Cuenta> iterator = atm.getLector().getTarjeta().getUsuario().getListaCuentas().iterator(); iterator.hasNext();) {
			cbTipoCuenta.addItem(iterator.next());
		}
		int i;
		for(Banco bco : atm.getBancos()) {
			for(i=0; i<bco.getListaUsuarios().size(); i++) {
				for(Iterator<Cuenta> iterator = bco.getListaUsuarios().get(i).getListaCuentas().iterator(); iterator.hasNext();) {
					cbCBU.addItem(iterator.next());
				}
			}
		}
	}

}
