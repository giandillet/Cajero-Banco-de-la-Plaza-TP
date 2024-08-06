package Pantalla;

import javax.swing.JPanel;
import javax.swing.JTabbedPane;

import Banco.ATM;
import Controlador.Persistencia;

import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

@SuppressWarnings("serial")
public class PanelContinuar extends JPanel {

	/**
	 * Create the panel.
	 */
	public PanelContinuar(JTabbedPane tabbedPane, ATM atm) {
		setLayout(null);
		
		JLabel lblOperacionFinalizada = new JLabel("Operacion Finalizada");
		lblOperacionFinalizada.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblOperacionFinalizada.setBounds(256, 60, 146, 16);
		add(lblOperacionFinalizada);
		
		JLabel lbldeseaRealizarOtra = new JLabel("\u00BFDesea Realizar Otra Operacion?");
		lbldeseaRealizarOtra.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lbldeseaRealizarOtra.setBounds(219, 89, 224, 16);
		add(lbldeseaRealizarOtra);
		
		JButton btnSi = new JButton("Si");
		btnSi.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				tabbedPane.setSelectedIndex(1);
			}
		});
		btnSi.setBounds(219, 150, 75, 25);
		add(btnSi);
		
		JButton btnNo = new JButton("No");
		btnNo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JOptionPane.showMessageDialog(null, atm.getLector().expulsarTarjeta());
				Persistencia.persistirATM(atm);
				tabbedPane.setSelectedIndex(0);
			}
		});
		btnNo.setBounds(368, 150, 75, 25);
		add(btnNo);

	}

}
