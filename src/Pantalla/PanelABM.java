package Pantalla;

import javax.swing.JPanel;
import javax.swing.JTabbedPane;

import Banco.Banco;
import Banco.Usuario;
import Cuentas.CajaAhorro;
import Cuentas.CuentaCorriente;
import Cuentas.CuentaSueldo;

import javax.swing.JRadioButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import java.awt.Color;
import java.awt.event.ActionListener;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.awt.event.ActionEvent;
import javax.swing.JSpinner;

@SuppressWarnings("serial")
public class PanelABM extends JPanel {
	private JTextField txtDNI;
	private JTextField txtNombre;
	private JTextField txtApellido;
	private JTextField txtCBU;
	private JTextField txtSaldo;
	private JTextField txtDescubierto;
	private JTextField txtCUIT;

	public PanelABM(JTabbedPane tabbedPane, Banco banco) {
		setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBounds(0, 0, 682, 250);
		add(panel);
		panel.setLayout(null);
		
		JLabel lblDni = new JLabel("DNI");
		lblDni.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblDni.setBounds(12, 55, 56, 16);
		panel.add(lblDni);
		
		txtDNI = new JTextField();
		txtDNI.setBounds(80, 55, 116, 22);
		panel.add(txtDNI);
		txtDNI.setColumns(10);
		
		JLabel lblNombre = new JLabel("Nombre");
		lblNombre.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblNombre.setBounds(12, 100, 56, 16);
		panel.add(lblNombre);
		
		txtNombre = new JTextField();
		txtNombre.setBounds(80, 98, 116, 22);
		panel.add(txtNombre);
		txtNombre.setColumns(10);
		
		JLabel lblApellido = new JLabel("Apellido");
		lblApellido.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblApellido.setBounds(12, 145, 56, 16);
		panel.add(lblApellido);
		
		txtApellido = new JTextField();
		txtApellido.setBounds(80, 145, 116, 22);
		panel.add(txtApellido);
		txtApellido.setColumns(10);
		
		JLabel lblCbu = new JLabel("CBU");
		lblCbu.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblCbu.setBounds(280, 55, 56, 16);
		panel.add(lblCbu);
		
		txtCBU = new JTextField();
		txtCBU.setBounds(360, 53, 116, 22);
		panel.add(txtCBU);
		txtCBU.setColumns(10);
		
		JLabel lblSaldo = new JLabel("Saldo($)");
		lblSaldo.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblSaldo.setBounds(280, 100, 56, 16);
		panel.add(lblSaldo);
		
		txtSaldo = new JTextField();
		txtSaldo.setBounds(360, 98, 116, 22);
		panel.add(txtSaldo);
		txtSaldo.setColumns(10);
		
		JLabel lblDescubierto = new JLabel("Descubierto");
		lblDescubierto.setForeground(Color.GRAY);
		lblDescubierto.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblDescubierto.setBounds(280, 145, 88, 16);
		panel.add(lblDescubierto);
		
		txtDescubierto = new JTextField();
		txtDescubierto.setEnabled(false);
		txtDescubierto.setBounds(360, 143, 116, 22);
		panel.add(txtDescubierto);
		txtDescubierto.setColumns(10);
		
		JLabel lblCuit = new JLabel("CUIT");
		lblCuit.setForeground(Color.GRAY);
		lblCuit.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblCuit.setBounds(280, 190, 56, 16);
		panel.add(lblCuit);
		
		txtCUIT = new JTextField();
		txtCUIT.setEnabled(false);
		txtCUIT.setBounds(360, 188, 116, 22);
		panel.add(txtCUIT);
		txtCUIT.setColumns(10);
		
		JLabel lblInteres = new JLabel("Interes(%)");
		lblInteres.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblInteres.setBounds(280, 223, 74, 20);
		panel.add(lblInteres);
		
		JSpinner spinner = new JSpinner();
		spinner.setBounds(360, 223, 47, 22);
		panel.add(spinner);
		
		JRadioButton rdbtnCajaDeAhorro = new JRadioButton("Caja de Ahorro");
		rdbtnCajaDeAhorro.setBounds(100, 10, 115, 25);
		panel.add(rdbtnCajaDeAhorro);
		rdbtnCajaDeAhorro.setSelected(true);
		
		JRadioButton rdbtnCuentaCorriente = new JRadioButton("Cuenta Corriente");
		rdbtnCuentaCorriente.setBounds(280, 10, 127, 25);
		panel.add(rdbtnCuentaCorriente);
		rdbtnCuentaCorriente.addChangeListener(new ChangeListener() {
			public void stateChanged(ChangeEvent e) {
				if (((JRadioButton)e.getSource()).isSelected()) {
					lblDescubierto.setForeground(Color.BLACK);
					txtDescubierto.setEnabled(true);
					lblInteres.setForeground(Color.GRAY);
					spinner.setEnabled(false);
				}else {
					lblDescubierto.setForeground(Color.GRAY);
					txtDescubierto.setEnabled(false);
					lblInteres.setForeground(Color.BLACK);
					spinner.setEnabled(true);
				}
			}
		});
		
		JRadioButton rdbtnCuentaSueldo = new JRadioButton("Cuenta Sueldo");
		rdbtnCuentaSueldo.setBounds(455, 10, 127, 25);
		panel.add(rdbtnCuentaSueldo);
		rdbtnCuentaSueldo.addChangeListener(new ChangeListener(){
			public void stateChanged(ChangeEvent e) {
				if(((JRadioButton)e.getSource()).isSelected()) {
					lblCuit.setForeground(Color.BLACK);
					txtCUIT.setEnabled(true);
				}else {
					lblCuit.setForeground(Color.GRAY);
					txtCUIT.setEnabled(false);
				}
			}
		});
		
		ButtonGroup grupodebotones;
		grupodebotones=new ButtonGroup();
		
		grupodebotones.add(rdbtnCajaDeAhorro);
		grupodebotones.add(rdbtnCuentaCorriente);
		grupodebotones.add(rdbtnCuentaSueldo);
		
		JButton btnGenerarCuenta = new JButton("Generar Cuenta");
		btnGenerarCuenta.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(!txtDNI.getText().equals("") && !txtNombre.getText().equals("") && !txtApellido.getText().equals("") && !txtCBU.getText().equals("") && !txtSaldo.getText().equals("")) {
					if(txtDescubierto.isEnabled()) {
						if(!txtDescubierto.getText().equals("")) {
							int dni = Integer.parseInt(txtDNI.getText());
							Usuario usuarioNuevo = new Usuario(dni,txtNombre.getText(),txtApellido.getText());
							int cbu = Integer.parseInt(txtCBU.getText());
							int saldo = Integer.parseInt(txtSaldo.getText());
							int descubierto = Integer.parseInt(txtDescubierto.getText());
							CuentaCorriente cuentaNueva = new CuentaCorriente(BigInteger.valueOf(cbu),BigDecimal.valueOf(saldo),BigDecimal.valueOf(0),BigDecimal.valueOf(descubierto),3);
							usuarioNuevo.agregarCuenta(cuentaNueva);
							banco.agregarUsuario(usuarioNuevo);
							JOptionPane.showMessageDialog(null, "Operacion Realizada con exito");
							txtDNI.setText(""); txtNombre.setText(""); txtApellido.setText(""); txtCBU.setText(""); txtSaldo.setText(""); txtDescubierto.setText("");
						}else {
							JOptionPane.showMessageDialog(null, "ERROR: Complete todos los campos");
						}
					}else {
						if(txtCUIT.isEnabled()) {
							if(!txtCUIT.getText().equals("")) {
								int dni = Integer.parseInt(txtDNI.getText());
								Usuario usuarioNuevo = new Usuario(dni,txtNombre.getText(),txtApellido.getText());
								int cbu = Integer.parseInt(txtCBU.getText());
								int saldo = Integer.parseInt(txtSaldo.getText());
								CuentaSueldo cuentaNueva = new CuentaSueldo(BigInteger.valueOf(cbu),BigDecimal.valueOf(saldo),BigDecimal.valueOf(0),BigDecimal.valueOf((int)spinner.getValue()),txtCUIT.getText());
								usuarioNuevo.agregarCuenta(cuentaNueva);
								banco.agregarUsuario(usuarioNuevo);
								JOptionPane.showMessageDialog(null, "Operacion Realizada con exito");
								txtDNI.setText(""); txtNombre.setText(""); txtApellido.setText(""); txtCBU.setText(""); txtSaldo.setText(""); txtCUIT.setText("");
							}else {
								JOptionPane.showMessageDialog(null, "ERROR: Complete todos los campos");
							}
						}else {
							int dni = Integer.parseInt(txtDNI.getText());
							Usuario usuarioNuevo = new Usuario(dni,txtNombre.getText(),txtApellido.getText());
							int cbu = Integer.parseInt(txtCBU.getText());
							int saldo = Integer.parseInt(txtSaldo.getText());
							CajaAhorro cuentaNueva = new CajaAhorro(BigInteger.valueOf(cbu),BigDecimal.valueOf(saldo),BigDecimal.valueOf(0),BigDecimal.valueOf((int)spinner.getValue()));
							usuarioNuevo.agregarCuenta(cuentaNueva);
							banco.agregarUsuario(usuarioNuevo);
							JOptionPane.showMessageDialog(null, "Operacion Realizada con exito");
							txtDNI.setText(""); txtNombre.setText(""); txtApellido.setText(""); txtCBU.setText(""); txtSaldo.setText("");
						}
					}
				}else {
					JOptionPane.showMessageDialog(null, "ERROR: Complete todos los campos");
				}
			}
		});
		btnGenerarCuenta.setBounds(523, 55, 130, 25);
		panel.add(btnGenerarCuenta);
		
		JButton btnLimparCampos = new JButton("Limpiar Campos");
		btnLimparCampos.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				txtDNI.setText("");
				txtNombre.setText("");
				txtApellido.setText("");
				txtCBU.setText("");
				txtSaldo.setText("");
				txtDescubierto.setText("");
				txtCUIT.setText("");
			}
		});
		btnLimparCampos.setBounds(523, 100, 130, 25);
		panel.add(btnLimparCampos);
		
		JButton btnVolver = new JButton("Volver");
		btnVolver.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				tabbedPane.setSelectedIndex(9);
			}
		});
		btnVolver.setBounds(523, 145, 130, 25);
		panel.add(btnVolver);
		
	}
}
