package net.codejava;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;
import java.util.ArrayList;
import java.util.Scanner;
import java.io.*;
import javax.swing.*;


public class VisualizzaOrdini extends JFrame{
	
ArrayList<Double> ordini = new ArrayList<Double>();
	
	private Connection conn;
	private JLabel welcomeLabel,idOrdine;
	private JButton visualizzaButton, statoButton, deleteButton,confermaButton,esciButton;
	private JTextArea informazioniArea;
	private JTextField idField;
	
	int i = 0;
	
	public VisualizzaOrdini() {
	
		//INIZIALIZZAZIONE DEI COMPONENTI
				welcomeLabel = new JLabel("Ordini");
				welcomeLabel.setHorizontalAlignment(SwingConstants.CENTER);
				welcomeLabel.setVerticalAlignment(SwingConstants.CENTER);
				welcomeLabel.setForeground(Color.WHITE);
				Font labelFont = welcomeLabel.getFont();
				int labelFontSize = labelFont.getSize() + 25;
				Font newFont = new Font(labelFont.getName(), labelFont.getStyle(), labelFontSize);
				welcomeLabel.setFont(newFont);
				informazioniArea = new JTextArea();
				Font font = informazioniArea.getFont();
				float size = font.getSize() + 20.0f;
				Font newFont3 = font.deriveFont(size);
				informazioniArea.setFont(newFont3);
				visualizzaButton = new JButton("Visualizza Ordini");
				visualizzaButton.setBackground(Color.WHITE);
				Font buttonFont = new Font("Arial", Font.BOLD, 35);
				visualizzaButton.setFont(buttonFont);
				visualizzaButton.setForeground(Color.PINK);
				esciButton = new JButton("Torna Ai Menu");
				Font buttonFont2 = new Font("Arial", Font.BOLD, 35);
				esciButton.setFont(buttonFont2);
				esciButton.setBackground(Color.white);
				esciButton.setForeground(Color.pink);
				
				
				
				//AGGIUNTA DEI COMPONENTI		
				JPanel panel = new JPanel() {
					@Override
					protected void paintComponent(Graphics g) {
						super.paintComponent(g);
						ImageIcon imageIcon = new ImageIcon("C:\\Users\\salvatore\\eclipse-workspace\\GestioneRistoranteDueVersion\\sfondoarancione.jpg");
						Image image = imageIcon.getImage();
						g.drawImage(image, 0, 0, getWidth(), getHeight(), this);
					}
				};
				panel.setLayout(new BorderLayout());
				ImageIcon icona = new ImageIcon("C:\\Users\\salvatore\\eclipse-workspace\\GestioneRistoranteDueVersion\\sfondo3.jpg");
		        setIconImage(icona.getImage());
				
				panel.add(welcomeLabel, BorderLayout.NORTH);
				panel.add(informazioniArea, BorderLayout.CENTER);
				panel.add(visualizzaButton, BorderLayout.LINE_START);
				panel.add(esciButton, BorderLayout.LINE_END);
				add(panel);
								
				
				
				
				//AGGIUNTA SCROLL		
				JScrollPane   scroll = new JScrollPane(informazioniArea);
		        scroll.setVerticalScrollBarPolicy ( ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS );
		        panel.add(scroll);
				
		        ImageIcon icona1 = new ImageIcon("C:\\Users\\peppe\\eclipse-workspace\\GestioneRistorante\\logo.jpg");
		        setIconImage(icona1.getImage());
				setTitle("Visualizza Ordine");
				setSize(1180,820);
				setLocationRelativeTo(null);
				setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
				setVisible(true);
	
	// Bottone Visuallizza Ordine Utente 
	visualizzaButton.addActionListener(new ActionListener() {
		public void actionPerformed(ActionEvent e) {
			Connection conn = null;
			i++;
			
			if(i%2!=0) {
				informazioniArea.setVisible(true);
			
			
			
			
	        try {
	        	conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/ristorante",
						"root", "lacoste1999!");

	            Statement stmt = conn.createStatement();
	           
	            String sql = "SELECT * FROM ordini_clienti ";

	            ResultSet rs = stmt.executeQuery(sql);
	            
	            while (rs.next()) {
	            	int idOrdine = rs.getInt("id");
	            	int idTavolo2 = rs.getInt("id_tavolo");
	                String nomeProdotto = rs.getString("nome_prodotto");		                
	                int quantita = rs.getInt("quantita");
	                boolean cucina = rs.getBoolean("cucina");
	               String cuc = "Pronto";
	                if(cucina == false) {
	                cuc = "in preparazione";
	                }
	                double totale = rs.getDouble("totale");
	                ordini.add(totale);
	                informazioniArea.append("ID Ordine" + idOrdine +  "\nNumeroTavolo: " + idTavolo2 + "\nNomeProdotto: " + nomeProdotto + "\nQuantita: " + quantita + "\nStato Ordine: " + cuc + "\nPrezzo: " + totale + "\n" );		                
	            } 
	            		           
	            	

	            rs.close();
	            stmt.close();
	            conn.close();
	            
	            
	            
	        } catch (SQLException e1) {
	            e1.printStackTrace();
	        }
			} else if(i%2==0) {
				informazioniArea.setText("");
				informazioniArea.setVisible(false);
			}
	        
		}
	}
			);
			
			esciButton.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					new Tavoli();
					dispose();
				}
			}
					);
			
			add(panel);
			setVisible(true);
	}

}
