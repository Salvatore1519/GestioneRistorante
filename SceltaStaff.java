package net.codejava;

import java.sql.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.*;

public class SceltaStaff extends JFrame{
	
	private static final String DB_URL = "jdbc:mysql://localhost/ristorante";
	private static final String DB_USER = "root";
	private static final String DB_PASSWORD = "lacoste1999!";	
	
	private JLabel cucina,cassa,aggiungi;
	private JButton cucinaButton,cassaButton,portataButton,indietro;
	
	public SceltaStaff() {
		cucina = new JLabel("Cucina");
		cucina.setHorizontalAlignment(SwingConstants.CENTER);
		cucina.setVerticalAlignment(SwingConstants.CENTER);
		Font labelFont = cucina.getFont();
		int labelFontSize = labelFont.getSize() + 25;
		Font newFont = new Font(labelFont.getName(), labelFont.getStyle(), labelFontSize);
		cucina.setFont(newFont);
		cassa = new JLabel("Cassa");
		cassa.setHorizontalAlignment(SwingConstants.CENTER);
		cassa.setVerticalAlignment(SwingConstants.CENTER);
		Font labelFont2 = cassa.getFont();
		int labelFontSize2 = labelFont2.getSize() + 25;
		Font newFont2 = new Font(labelFont2.getName(), labelFont2.getStyle(), labelFontSize2);
		cassa.setFont(newFont2);
		
		aggiungi = new JLabel("Aggiungi Portata al menu");
		aggiungi.setHorizontalAlignment(SwingConstants.CENTER);
		cucina.setVerticalAlignment(SwingConstants.CENTER);
		Font labelFont6 = aggiungi.getFont();
		int labelFontSize6 = labelFont6.getSize() + 25;
		Font newFont6 = new Font(labelFont6.getName(), labelFont6.getStyle(), labelFontSize6);
		aggiungi.setFont(newFont6);
		
		cucinaButton = new JButton("Cucina");
		Font buttonFont = new Font("Arial", Font.BOLD, 35);
		cucinaButton.setFont(buttonFont);
		cassaButton = new JButton("Cassa");
		Font buttonFont2 = new Font("Arial", Font.BOLD, 35);
		cassaButton.setFont(buttonFont2);
		
		cassaButton.setBackground(Color.PINK);
		cucinaButton.setBackground(Color.PINK);
		
		portataButton = new JButton("Aggiungi Portata");
		Font buttonFont5 = new Font("Arial", Font.BOLD, 35);
		portataButton.setFont(buttonFont5);
		portataButton.setBackground(Color.PINK);
		
		indietro = new JButton("Torna al menu Principale");
		Font buttonFont6 = new Font("Arial", Font.BOLD, 35);
		indietro.setFont(buttonFont6);
		indietro.setBackground(Color.PINK);
		
		JPanel panel = new JPanel(new GridLayout(7,1));
		
		ImageIcon icona = new ImageIcon("C:\\Users\\salvatore\\eclipse-workspace\\GestioneRistoranteDueVersion\\logo.jpg");
        setIconImage(icona.getImage());
		setTitle("Menu Staff");
		setSize(1180,820);
		setLocationRelativeTo(null);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		panel.add(cucina);
		panel.add(cucinaButton);
		panel.add(cassa);
		panel.add(cassaButton);
		panel.add(aggiungi);
		panel.add(portataButton);
		panel.add(indietro);
		
		cucinaButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new Cucina();;
				dispose();
			}
		}
				);
		
		cassaButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new Cassa();
				dispose();
			}
		}
				);
		
		portataButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new GestioneRisorse();
				dispose();
			}
		}
				);
		
		indietro.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new Main();
				dispose();
			}
		}
				);
		
		
		
		add(panel);
		setVisible(true);
		
	}
	
	

}
