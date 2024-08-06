package Pantalla;

import javax.swing.JPanel;
import javax.swing.JTabbedPane;

import Banco.ATM;
import Banco.Billetero;
import Controlador.Persistencia;

import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.util.Iterator;
import java.awt.event.ActionEvent;
import javax.swing.JComboBox;
import javax.swing.JTextField;

@SuppressWarnings("serial")
public class PanelAdmin extends JPanel {
	private JTextField cantidad;
	private JLabel lblBilleteros = new JLabel("");
	private ATM atm;
	
	public PanelAdmin(JTabbedPane tabbedPane, ATM atm) {
		setLayout(null);
		this.atm=atm;
		
		JPanel panelBotones = new JPanel();
		panelBotones.setBounds(0, 0, 682, 250);
		add(panelBotones);
		panelBotones.setLayout(null);
		
		int[] opcion= {0};
		
		JLabel lblModoAdministrador = new JLabel("Modo Administrador - Seleccione la Operacion");
		lblModoAdministrador.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblModoAdministrador.setBounds(180, 13, 311, 16);
		panelBotones.add(lblModoAdministrador);
		
		JComboBox<Billetero> comboBox = new JComboBox<Billetero>();
		comboBox.setBounds(102, 191, 100, 22);
		panelBotones.add(comboBox);
		for(Iterator<Billetero> iterator = atm.getBilleteros().iterator(); iterator.hasNext();) {
			comboBox.addItem(iterator.next());
		}
		comboBox.setVisible(false);
		
		
		lblBilleteros.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblBilleteros.setBounds(12, 206, 658, 59);
		panelBotones.add(lblBilleteros);
		
		
		cantidad = new JTextField();
		cantidad.setBounds(212, 191, 61, 22);
		panelBotones.add(cantidad);
		cantidad.setColumns(10);
		cantidad.setVisible(false);
		
		JButton btnConfirmar = new JButton("Confirmar");
		btnConfirmar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(cantidad.getText()!="") {
					int cant = Integer.parseInt(cantidad.getText());
					Billetero selec = (Billetero) comboBox.getSelectedItem();
					if(opcion[0]==1) {
						atm.recargar(selec, cant);
						lblBilleteros.setText(atm.contadorBilleteros());
						JOptionPane.showMessageDialog(null, "Operacion Realizada");
						cantidad.setText("");
						comboBox.setVisible(false);
						cantidad.setVisible(false);
						btnConfirmar.setVisible(false);
					} else {
						if(cant<=selec.getCantidad()) {
							atm.descargar(selec, cant);
							lblBilleteros.setText(atm.contadorBilleteros());
							JOptionPane.showMessageDialog(null, "Operacion Realizada");
							cantidad.setText("");
							comboBox.setVisible(false);
							cantidad.setVisible(false);
							btnConfirmar.setVisible(false);
						}else {
							JOptionPane.showMessageDialog(null, "Cantidad Invalida");
							cantidad.setText("");
						}
					}
				}else {
					JOptionPane.showMessageDialog(null, "Cantidad Invalida");
				}
			}
		});
		btnConfirmar.setBounds(294, 191, 97, 25);
		panelBotones.add(btnConfirmar);
		btnConfirmar.setVisible(false);
		
		JButton btnRecarga = new JButton("");
		btnRecarga.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				comboBox.setVisible(true);
				cantidad.setVisible(true);
				btnConfirmar.setVisible(true);
				opcion[0]=1;
			}
		});
		btnRecarga.setBounds(12, 58, 80, 25);
		panelBotones.add(btnRecarga);
		
		JLabel lblRecargarBilletero = new JLabel("Recargar Billetero");
		lblRecargarBilletero.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblRecargarBilletero.setBounds(102, 58, 118, 25);
		panelBotones.add(lblRecargarBilletero);
		
		JButton btnDescarga = new JButton("");
		btnDescarga.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				comboBox.setVisible(true);
				cantidad.setVisible(true);
				btnConfirmar.setVisible(true);
				opcion[0]=2;
			}
		});
		btnDescarga.setBounds(12, 96, 80, 25);
		panelBotones.add(btnDescarga);
		
		JLabel lblDescargarBilletero = new JLabel("Descargar Billetero");
		lblDescargarBilletero.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblDescargarBilletero.setBounds(102, 96, 130, 25);
		panelBotones.add(lblDescargarBilletero);
		
		JLabel lblMontoEnReconocedor = new JLabel("Reconocedor de Billetes "+atm.getReconocedor().cantidad());
		lblMontoEnReconocedor.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblMontoEnReconocedor.setBounds(430, 184, 240, 35);
		panelBotones.add(lblMontoEnReconocedor);
		
		JButton btnVaciarReconocedor = new JButton("");
		btnVaciarReconocedor.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				atm.getReconocedor().vaciar();
				lblMontoEnReconocedor.setText("Billetero "+atm.getReconocedor().cantidad());
				JOptionPane.showMessageDialog(null, "Reconocedor Vaciado");
			}
		});
		btnVaciarReconocedor.setBounds(590, 58, 80, 25);
		panelBotones.add(btnVaciarReconocedor);
		
		JLabel lblVaciarReconocedorDe = new JLabel("Vaciar Reconocedor de Billetes");
		lblVaciarReconocedorDe.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblVaciarReconocedorDe.setBounds(381, 58, 206, 25);
		panelBotones.add(lblVaciarReconocedorDe);
		
		JButton btnFinalizar = new JButton("");
		btnFinalizar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				atm.setModoMantenimiento(false);
				Persistencia.persistirATM(atm);
				tabbedPane.setSelectedIndex(0);
			}
		});
		btnFinalizar.setBounds(590, 128, 80, 25);
		panelBotones.add(btnFinalizar);
		
		JLabel lblFinalizar = new JLabel("Finalizar");
		lblFinalizar.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblFinalizar.setBounds(526, 128, 61, 25);
		panelBotones.add(lblFinalizar);
		
		JButton btnABM = new JButton("");
		btnABM.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				tabbedPane.setSelectedIndex(10);
			}
		});
		btnABM.setBounds(12, 134, 80, 25);
		panelBotones.add(btnABM);
		
		JLabel lblAbm = new JLabel("ABM");
		lblAbm.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblAbm.setBounds(102, 137, 56, 16);
		panelBotones.add(lblAbm);
	}
	
	public void actualizar() {
		lblBilleteros.setText(atm.contadorBilleteros());
	}
}
