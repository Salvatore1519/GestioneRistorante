package net.codejava;

import javax.swing.*;
import java.sql.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.*;
import javax.swing.text.JTextComponent;
import java.util.*;

public class Cucina extends JFrame{

	ArrayList<Double> ordini = new ArrayList<Double>();
	
	private Connection conn;
	private JLabel welcomeLabel,idOrdine;
	private JButton visualizzaButton, statoButton, deleteButton,confermaButton;
	private JTextArea informazioniArea;
	private JTextField idField;
	
	private JButton indietro;
	
	int i = 0;

	
	public Cucina() {
		
		//INIZIALIZZAZIONE DEI COMPONENTI
		welcomeLabel = new JLabel("Cucina");
		Font labelFont = welcomeLabel.getFont();
		int labelFontSize = labelFont.getSize() + 25;
		Font newFont = new Font(labelFont.getName(), labelFont.getStyle(), labelFontSize);
		welcomeLabel.setFont(newFont);
		
		idOrdine = new JLabel("Inserisci ID Ordine:");
		Font labelFont2 = idOrdine.getFont();
		int labelFontSize2 = labelFont2.getSize() + 25;
		Font newFont2 = new Font(labelFont2.getName(), labelFont2.getStyle(), labelFontSize2);
		idOrdine.setFont(newFont2);
		
		idField = new JTextField();
		
		informazioniArea = new JTextArea();
		Font font = informazioniArea.getFont();
		float size = font.getSize() + 20.0f;
		Font newFont3 = font.deriveFont(size);
		informazioniArea.setFont(newFont3);
		
		visualizzaButton = new JButton("Visualizza Ordini");
		Font buttonFont = new Font("Arial", Font.BOLD, 35);
		visualizzaButton.setFont(buttonFont);
		visualizzaButton.setBackground(Color.WHITE);
		visualizzaButton.setForeground(Color.ORANGE);
		
		statoButton = new JButton("Stato Ordine");
		Font buttonFont2 = new Font("Arial", Font.BOLD, 35);
		statoButton.setFont(buttonFont2);
		statoButton.setBackground(Color.WHITE);
		statoButton.setForeground(Color.ORANGE);
		
		deleteButton = new JButton("Elimina Ordine");
		Font buttonFont3 = new Font("Arial", Font.BOLD, 35);
		deleteButton.setFont(buttonFont3);
		deleteButton.setBackground(Color.WHITE);
		deleteButton.setForeground(Color.ORANGE);
		
		confermaButton = new JButton("Conferma");
		Font buttonFont4 = new Font("Arial", Font.BOLD, 35);
		confermaButton.setFont(buttonFont4);
		confermaButton.setBackground(Color.WHITE);
		confermaButton.setForeground(Color.ORANGE);
		
		indietro = new JButton("Torna al menu staff");
		Font buttonFont5 = new Font("Arial", Font.BOLD, 35);
		indietro.setFont(buttonFont5);
		indietro.setBackground(Color.WHITE);
		indietro.setForeground(Color.ORANGE);
		
		
		
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
		
		panel.add(indietro, BorderLayout.NORTH);
		panel.add(informazioniArea, BorderLayout.CENTER);
		panel.add(visualizzaButton, BorderLayout.LINE_START);
		panel.add(deleteButton, BorderLayout.LINE_END);
		panel.add(statoButton, BorderLayout.SOUTH);
		add(panel);
						
		
		
		
		//AGGIUNTA SCROLL		
		JScrollPane   scroll = new JScrollPane(informazioniArea);
        scroll.setVerticalScrollBarPolicy ( ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS );
        panel.add(scroll);
		
        ImageIcon icona1 = new ImageIcon("C:\\Users\\salvatore\\eclipse-workspace\\GestioneRistoranteDueVersion\\logo.jpg");
        setIconImage(icona1.getImage());
		setTitle("Cucina");
		setSize(1180,820);
		setLocationRelativeTo(null);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setVisible(true);
		
		
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
		           
		            String sql = "SELECT * FROM ordini_clienti WHERE cucina=false";

		            ResultSet rs = stmt.executeQuery(sql);
		            
		            while (rs.next()) {
		            	int idOrdine = rs.getInt("id");
		            	int idTavolo2 = rs.getInt("id_tavolo");
		                String nomeProdotto = rs.getString("nome_prodotto");		                
		                int quantita = rs.getInt("quantita");
		                boolean cucina = rs.getBoolean("cucina");
		                double totale = rs.getDouble("totale");
		                ordini.add(totale);
		                informazioniArea.append("ID Ordine" + idOrdine +  "\nNumeroTavolo: " + idTavolo2 + "\nNomeProdotto: " + nomeProdotto + "\nQuantita: " + quantita + "\nOrdine eseguito ?: " + cucina + "\nPrezzo: " + totale + "\n");		                
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
	
			deleteButton.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					new EliminaOrdine();
					dispose();
				}
			}
					);
			
			statoButton.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					new StatoOrdine();
					dispose();
				}
			}
					);
			
			indietro.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					new SceltaStaff();
					dispose();
				}
			}
					);
			
		
		
		 
	}
	
	

	
}