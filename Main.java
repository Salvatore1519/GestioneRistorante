package net.codejava;

import java.sql.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.*;

public class Main extends JFrame{
	
	private static final String DB_URL = "jdbc:mysql://localhost/ristorante";
	private static final String DB_USER = "root";
	private static final String DB_PASSWORD = "lacoste1999!";	
	
	private JLabel cliente,staff ;
	private JButton clienteButton, staffButton;
	
	public Main() {
		
		cliente = new JLabel("Sei un cliente?");
		cliente.setHorizontalAlignment(SwingConstants.CENTER);
		cliente.setVerticalAlignment(SwingConstants.CENTER);
		Font labelFont = cliente.getFont();
		int labelFontSize = labelFont.getSize() + 35;
		Font newFont = new Font(labelFont.getName(), labelFont.getStyle(), labelFontSize);
		cliente.setFont(newFont);
		
		staff = new JLabel("Sei dello staff?");
		staff.setHorizontalAlignment(SwingConstants.CENTER);
		staff.setVerticalAlignment(SwingConstants.CENTER);
		Font labelFont2 = staff.getFont();
		int labelFontSize2 = labelFont2.getSize() + 35;
		Font newFont2 = new Font(labelFont2.getName(), labelFont2.getStyle(), labelFontSize2);
		staff.setFont(newFont2);
		
		clienteButton = new JButton("Cliente");
		Font buttonFont = new Font("Arial", Font.BOLD, 35);
		clienteButton.setFont(buttonFont);
		
		staffButton = new JButton("Staff Ristorante");
		Font buttonFont2 = new Font("Arial", Font.BOLD, 35);
		staffButton.setFont(buttonFont);
		
		clienteButton.setBackground(Color.WHITE);
		staffButton.setBackground(Color.WHITE);
		clienteButton.setForeground(Color.PINK);
		staffButton.setForeground(Color.PINK);
		
		JPanel panel = new JPanel() {
			@Override
			protected void paintComponent(Graphics g) {
				super.paintComponent(g);
				ImageIcon imageIcon = new ImageIcon("C:\\Users\\salvatore\\eclipse-workspace\\GestioneRistorante\\sfondoarancione.jpg");
				Image image = imageIcon.getImage();
				g.drawImage(image, 0, 0, getWidth(), getHeight(), this);
			}
		};
		panel.setLayout(new GridLayout(4,1));

		ImageIcon icona = new ImageIcon("C:\\Users\\salvatore\\eclipse-workspace\\GestioneRistoranteDueVersion\\logo.jpg");
        setIconImage(icona.getImage());
		setTitle("Menu Principale");
		setSize(1180,820);
		setLocationRelativeTo(null);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		panel.add(cliente);
		panel.add(clienteButton);
		panel.add(staff);
		panel.add(staffButton);
		
		
		clienteButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new Tavoli();
				dispose();
			}
		});
		
		staffButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new Accesso();
				dispose();
			}
		});
		
		add(panel);
		setVisible(true);
	}
	
	public static void main(String[] args) {
		new Main();
	}
}
