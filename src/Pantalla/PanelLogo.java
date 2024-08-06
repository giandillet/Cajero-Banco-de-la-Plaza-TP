package Pantalla;

import javax.swing.JPanel;

import javax.swing.ImageIcon;
import javax.swing.JLabel;

import java.awt.Font;
import java.net.MalformedURLException;
import java.net.URL;

import javax.swing.SwingConstants;

import Banco.ATM;

@SuppressWarnings("serial")
public class PanelLogo extends JPanel {
	
	/**
	 * Create the panel.
	 * @throws MalformedURLException 
	 */
	public PanelLogo(ATM atm) throws MalformedURLException {
		setLayout(null);
		
		JPanel panelLogo = new JPanel();
		panelLogo.setBounds(0, 0, 682, 250);
		add(panelLogo);
		panelLogo.setLayout(null);
		
		JLabel lblLogo = new JLabel("");
		lblLogo.setHorizontalAlignment(SwingConstants.CENTER);
		lblLogo.setBounds(0, 13, 682, 152);
		panelLogo.add(lblLogo);
		
		URL url = new URL("https://www.brandemia.org/wp-content/uploads/2013/04/Banelco_4.jpg");
		lblLogo.setIcon(new ImageIcon(url));
		
		JLabel lblUbicacion = new JLabel("Sucursal "+atm.getUbicacion());
		lblUbicacion.setHorizontalAlignment(SwingConstants.CENTER);
		lblUbicacion.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblUbicacion.setBounds(12, 193, 658, 25);
		panelLogo.add(lblUbicacion);
		
	}
}
