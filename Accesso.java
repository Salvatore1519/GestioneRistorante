package net.codejava;

import javax.swing.*;
import java.sql.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.*;


	public class Accesso extends JFrame implements ActionListener {
		private JLabel userLabel, passLabel;
		private JTextField userField;
		private JPasswordField passField;
		private JButton loginButton;
		
		
		public Accesso() {
			
			super("Controllo di accesso"); // TITOLO DELLA FINESTRA
			setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE); // TERMINA IL PROGRAMMA QUANDO LA FINESTRA VIENE CHIUSA
			setSize(1180, 820); // DIMENSIONI DELLA FINESTRA
			setLocationRelativeTo(null);
			setLayout(new GridLayout(3, 2)); // LAYOUT DELLA FINESTRA
			ImageIcon icona = new ImageIcon("C:\\Users\\salvatore\\eclipse-workspace\\GestioneRistorante\\logo.jpg");
	        setIconImage(icona.getImage());
			// CREA GLI ELEMENTI DELLA FINESTRA
			userLabel = new JLabel("Nome utente:");
			Font labelFont = userLabel.getFont();
			int labelFontSize = labelFont.getSize() + 35;
			Font newFont = new Font(labelFont.getName(), labelFont.getStyle(), labelFontSize);
			userLabel.setFont(newFont);
			userLabel.setForeground(Color.PINK);
			
			passLabel = new JLabel("Password:");
			Font labelFont2 = passLabel.getFont();
			int labelFontSize2 = labelFont2.getSize() + 35;
			Font newFont2 = new Font(labelFont2.getName(), labelFont2.getStyle(), labelFontSize2);
			passLabel.setFont(newFont2);
			passLabel.setForeground(Color.PINK);
			
			userField = new JTextField();
			Font font = new Font("Arial", Font.PLAIN, 50);
			userField.setFont(font);
			passField = new JPasswordField();
			Font font2 = new Font("Arial", Font.PLAIN, 50);
			passField.setFont(font2);
			loginButton = new JButton("Login");
			Font buttonFont = new Font("Arial", Font.BOLD, 35);
			loginButton.setFont(buttonFont);
			loginButton.setBackground(Color.PINK);
			loginButton.addActionListener(this);
			// AGGIUNGE GLI ELEMENTI ALLA FINESTRA
			add(userLabel);
			add(userField);
			add(passLabel);
			add(passField);
			add(new JLabel(""));
			add(loginButton);
			setVisible(true); //MOSTRA LA FINESTRA
		}
		
		
		public void actionPerformed(ActionEvent e) {
			String user = userField.getText(); // OTTIENE IL NOME UTENTE INSERITO DALL'UTENTE
			String pass = new String(passField.getPassword()); // OTTIENE LA PASSWORD INSERITA DALL'UTENTEE
			
			
			try {
				// CARICA IL FILE JDBC PER IL DATABASE MySQL
				Class.forName("com.mysql.jdbc.Driver");
				// CONNETTE AL DATABASE DI MySQL
				Connection conn =
						DriverManager.getConnection("jdbc:mysql://localhost:3306/ristorante",
								"root", "lacoste1999!");
				
				
				// CREA UN OGGETTO STATEMENT per ESEGUIRE LE QUERY SUL DATABASE
				Statement stmt = conn.createStatement();
				
				
				// ESEGUE LA QUERY PER SELEZIONARE L'UTENTE CON IL NOME UTENTE E LA PASSWORD INSERITI
				ResultSet rs = stmt.executeQuery("SELECT * FROM accesso WHERE username='"
						+ user + "' AND password='" + pass + "'");
				
				
				// VERIFICA SE L'UTENTE ESISTE NEL DATABASE
				if (rs.next()) {
					JOptionPane.showMessageDialog(this, "Accesso consentito", "Login",
							JOptionPane.INFORMATION_MESSAGE);
				} else {
					JOptionPane.showMessageDialog(this, "Nome utente o password errati", "Login",
							JOptionPane.ERROR_MESSAGE);
				}
				// CHIUDE LA CONNESSIONE AL DATABASE
				conn.close();
				
				new SceltaStaff();
				dispose();
			} catch (Exception ex) {
				JOptionPane.showMessageDialog(this, "Errore di connessione al database", "Login",
						JOptionPane.ERROR_MESSAGE);
				ex.printStackTrace();
			}
			
				
			
			
		}
		
	
		
	}