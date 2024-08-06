package Pantalla;

import javax.swing.JPanel;
import javax.swing.JTabbedPane;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import java.util.Date;
import java.util.Iterator;

import javax.swing.JTextField;
import javax.swing.JTextPane;

import Banco.ATM;
import Banco.TipoTransaccion;
import Banco.Transaccion;
import Cuentas.Cuenta;

import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.math.BigDecimal;
import java.awt.event.ActionEvent;
import javax.swing.JSpinner;
import javax.swing.SpinnerNumberModel;

@SuppressWarnings("serial")
public class PanelInteraccionDinero extends JPanel {
	
	private JTextField txtMonto;
	private JComboBox<Cuenta> cbTipoCuenta = new JComboBox<Cuenta>();
	private ATM atm;
	private int b500;
	private int b200;
	private int b100;
	
	public PanelInteraccionDinero(JTabbedPane tabbedPane, ATM atm, String operacion) {
		setLayout(null);
		this.atm=atm;
		JPanel pantallaRetiro = new JPanel();
		pantallaRetiro.setBounds(0, 0, 682, 250);
		add(pantallaRetiro);
		pantallaRetiro.setLayout(null);
		
		
		cbTipoCuenta.setBounds(30, 44, 217, 22);
		
		pantallaRetiro.add(cbTipoCuenta);
		
		JLabel lblCuenta = new JLabel("Seleccione cuenta");
		lblCuenta.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblCuenta.setBounds(30, 15, 150, 22);
		pantallaRetiro.add(lblCuenta);
		
		JLabel label = new JLabel("$");
		label.setFont(new Font("Tahoma", Font.PLAIN, 15));
		label.setBounds(30, 152, 56, 16);
		pantallaRetiro.add(label);
		if(operacion.equals("Deposito")) {
			label.setVisible(false);
		}else {
			label.setVisible(true);
		}
		
		JSpinner spinner = new JSpinner();
		spinner.setModel(new SpinnerNumberModel(new Integer(1), new Integer(1), null, new Integer(1)));
		spinner.setBounds(207, 150, 40, 22);
		pantallaRetiro.add(spinner);
		
		JLabel lblIngreseDenominacionY = new JLabel("Ingrese Denominacion y Cantidad de billetes");
		lblIngreseDenominacionY.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblIngreseDenominacionY.setBounds(10, 118, 288, 22);
		pantallaRetiro.add(lblIngreseDenominacionY);
		
		JLabel lblTotal = new JLabel("Total: $");
		lblTotal.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblTotal.setBounds(30, 91, 56, 16);
		pantallaRetiro.add(lblTotal);
		
		JLabel lblSuma = new JLabel("0");
		lblSuma.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblSuma.setBounds(90, 91, 56, 16);
		pantallaRetiro.add(lblSuma);
		
		txtMonto = new JTextField();
		txtMonto.setFont(new Font("Tahoma", Font.PLAIN, 15));
		txtMonto.setBounds(40, 150, 155, 22);
		pantallaRetiro.add(txtMonto);
		txtMonto.setColumns(10);
		
		JTextPane ticket = new JTextPane();
		ticket.setFont(new Font("Tahoma", Font.PLAIN, 15));
		ticket.setBounds(300, 39, 370, 180);
		pantallaRetiro.add(ticket);
		
		JButton btnContinuar = new JButton("Continuar");
		btnContinuar.setBounds(150, 220, 97, 25);
		pantallaRetiro.add(btnContinuar);
		btnContinuar.setVisible(false);
		btnContinuar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				btnContinuar.setVisible(false);
				ticket.setText("");
				tabbedPane.setSelectedIndex(8);
			}
		});
		
		int[] suma= {0};
		
		JButton btnConfirmar = new JButton("Confirmar");
		btnConfirmar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if(operacion.equals("Retiro")) {
					BigDecimal monto = BigDecimal.valueOf(suma[0]);
					if(atm.saldoSuficiente(monto)) {//si el cajero tiene esa suma procede
						if(!((Cuenta) cbTipoCuenta.getSelectedItem()).verificaSaldo(monto)) { //verifica si la cuenta posee la suma
							JOptionPane.showMessageDialog(null, "Saldo Insuficiente");
							b100=0; b200=0; b500=0;
							suma[0]=0;
							lblSuma.setText(Integer.toString(suma[0]));
						}else {
							atm.extraer(monto, atm.getLector().getTarjeta(), (Cuenta) cbTipoCuenta.getSelectedItem());
							atm.descargar(atm.buscarBilletero(100), b100);
							atm.descargar(atm.buscarBilletero(200), b200);
							atm.descargar(atm.buscarBilletero(500), b500);
							Transaccion transaccion = new Transaccion(new Date(), monto, new TipoTransaccion(operacion,true));
							int i=0;
							while(atm.getLector().getTarjeta().getUsuario().getListaCuentas().get(i)!=(Cuenta) cbTipoCuenta.getSelectedItem()) {
								i++;
							}
							atm.getLector().getTarjeta().getUsuario().getListaCuentas().get(i).agregarTransaccion(transaccion);
							JOptionPane.showMessageDialog(null,"Operacion Exitosa");
							ticket.setText(transaccion.toString());
							btnContinuar.setVisible(true);
							b100=0; b200=0; b500=0;
							suma[0]=0;
							lblSuma.setText(Integer.toString(suma[0]));
						}
					}else {
						JOptionPane.showMessageDialog(null, "El cajero no dispone esa suma");
						suma[0]=0;
						lblSuma.setText(Integer.toString(suma[0]));
					}			
				}else { //Funcion Depositar
					BigDecimal monto = BigDecimal.valueOf(suma[0]);
					atm.depositar(monto, atm.getLector().getTarjeta(), (Cuenta) cbTipoCuenta.getSelectedItem());
					Transaccion transaccion = new Transaccion(new Date(), monto, new TipoTransaccion(operacion,false));
					int i=0;
					while(atm.getLector().getTarjeta().getUsuario().getListaCuentas().get(i)!=(Cuenta) cbTipoCuenta.getSelectedItem()) {
						i++;
					}
					atm.getLector().getTarjeta().getUsuario().getListaCuentas().get(i).agregarTransaccion(transaccion);
					lblSuma.setText("");
					suma[0]=0;
					JOptionPane.showMessageDialog(null,"Operacion Exitosa");
					ticket.setText(transaccion.toString());
					btnContinuar.setVisible(true);
				}	
			}
		});
		btnConfirmar.setBounds(150, 195, 97, 25);
		pantallaRetiro.add(btnConfirmar);
		
		JButton btnVolver = new JButton("Volver");
		btnVolver.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				tabbedPane.setSelectedIndex(1);
			}
		});
		btnVolver.setBounds(30, 195, 97, 25);
		pantallaRetiro.add(btnVolver);
		
		JButton btnAgregar = new JButton("Agregar");
		btnAgregar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				int billete = Integer.parseInt(txtMonto.getText());
				int i=0; boolean encontrado=false;
				while(i<atm.getReconocedor().getBilletesPermitidos().size() && !encontrado) {
					if(atm.getReconocedor().getBilletesPermitidos().get(i)==billete) {
						encontrado=true;
						if(operacion.equals("Retiro")) {
							if ((atm.buscarBilletero(billete)).getCantidad()>=(int)spinner.getValue()) {
								switch(billete) {
									case 100: b100=(int)spinner.getValue(); break;
									case 200: b200=(int)spinner.getValue(); break;
									case 500: b500=(int)spinner.getValue(); break;
								}
								suma[0]+= billete * (int)spinner.getValue();
								lblSuma.setText(Integer.toString(suma[0]));
							}else {
								JOptionPane.showMessageDialog(null, "El cajero no dispone de esa suma");
							}
						}else {
							suma[0]+= billete * (int)spinner.getValue();
							BigDecimal billetes = BigDecimal.valueOf(billete);
							atm.getReconocedor().agregarValidos(billetes);
							lblSuma.setText(Integer.toString(suma[0]));
							}
						}else {
							i++;
						}
				}
				if(!encontrado) {
					JOptionPane.showMessageDialog(null, "Denominacion no valida");
				}
			}
		});
		btnAgregar.setBounds(30, 220, 97, 25);
		pantallaRetiro.add(btnAgregar);
	}
	
	public void actualizar() {
		cbTipoCuenta.removeAllItems(); //Quita las cuentas de los usuarios que hayan usado el cajero anteriormente
		for(Iterator<Cuenta> iterator = atm.getLector().getTarjeta().getUsuario().getListaCuentas().iterator(); iterator.hasNext();) {
			cbTipoCuenta.addItem(iterator.next());
		}
		b500=0;
		b200=0;
		b100=0;
	}
}
