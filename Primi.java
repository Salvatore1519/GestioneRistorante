package net.codejava;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.sql.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.awt.event.*;
import java.util.*;

public class Primi extends JFrame  {
	private static final String DB_URL = "jdbc:mysql://localhost/ristorante";
	private static final String DB_USER = "root";
	private static final String DB_PASSWORD = "lacoste1999!";	
	
	
	private JPanel panel;
	private JButton buttonImmagini;
	private JButton indietro;
	
	
	public Primi() throws IOException {
		
		
		
		super("Pizze");
		
		
		
		
		JFrame frame = new JFrame("C:\\Users\\salvatore\\eclipse-workspace\\GestioneRistoranteDueVersion\\logo.jpg");
		   
		indietro = new JButton("Torna alle categorie menu");
		Font buttonFont5 = new Font("Arial", Font.BOLD, 35);
		indietro.setFont(buttonFont5);
		indietro.setBackground(Color.WHITE);
		indietro.setForeground(Color.pink);
		
		ImageIcon icona = new ImageIcon("C:\\Users\\salvatore\\eclipse-workspace\\GestioneRistoranteDueVersion\\logo.jpg");
        setIconImage(icona.getImage());
        setSize(1180, 820);
        JPanel panel = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                ImageIcon imageIcon = new ImageIcon("C:\\Users\\salvatore\\eclipse-workspace\\GestioneRistoranteDueVersion\\sfondoarancione.jpg");
                Image image = imageIcon.getImage();
                g.drawImage(image, 0, 0, getWidth(), getHeight(), this);
            }
        };
        panel.setLayout(new GridLayout(1,2));

		    
        for (int i = 0; i < 1; i++) {
	    
	    Connection conn = null;
	    Statement stmt = null;
	    ResultSet rs=null;
	 
	    
	    try {
	        String portata = "primi";
	        conn = DriverManager.getConnection(DB_URL,DB_USER,DB_PASSWORD);
	        stmt=conn.createStatement();
	        rs=stmt.executeQuery("select *from menu where categoria='" + portata +"'");
	        
	        while (rs.next()) {
	        	
	            byte[] bytes = rs.getBytes("foto");
	            String nomePizza = rs.getString("portate");
	            int idImmagine = rs.getInt("id");
	            int prezzo_prodotto=rs.getInt("prezzo");
	            String descizioneProdotto=rs.getString("descrizione");
	            BufferedImage img = ImageIO.read(new ByteArrayInputStream(bytes));

	            ImageIcon icon = new ImageIcon(img.getScaledInstance(400, 400, BufferedImage.SCALE_SMOOTH));

	            JButton buttonImmagine = new JButton();
	            buttonImmagine.setIcon(icon);
	            buttonImmagine.setOpaque(false);
	            buttonImmagine.setContentAreaFilled(false);
	            buttonImmagine.setBorderPainted(false);
	            buttonImmagine.setFocusPainted(false);
	            buttonImmagine.setForeground(new Color(0, 0, 0, 0)); // Imposta il colore del testo come trasparente
	            buttonImmagine.setVisible(true);

	            buttonImmagine.addActionListener(e->{
	                //CODICE PER AGGIUNGERE LA PORTATA ED INVIARLA ALLA CUCINA
	            	
	            	   
	            	   
	                String quantita = JOptionPane.showInputDialog(null,"Prezzo Prodotto: " +prezzo_prodotto+ "€" + "\n" +descizioneProdotto + "\n"+ "Inserisci Quantita" );
	                int totalePorzioni = (int) Integer.parseInt(quantita);
	                String nome_prodotto = nomePizza;
	                Connection conn1 = null;
	                PreparedStatement stmt1 = null;
	                //ResultSet rs1 = null;
	                //String sqlPrezzo = ("SELECT prezzo FROM menu");
	                String sql = ("INSERT INTO ordini_clienti(id_tavolo,nome_prodotto,quantita,cassa,cucina,totale) values(1,?,?,0,0,?)");
	                try {
	                    conn1 = DriverManager.getConnection("jdbc:mysql://localhost:3306/ristorante", "root", "lacoste1999!");
	                    stmt1 = conn1.prepareStatement(sql);
	                    //rs1 = stmt1.executeQuery(sql);
	                    //int prezzo = 0;
	                    int totaleCosto = totalePorzioni * prezzo_prodotto;
	                    System.out.println(totaleCosto);
	                    System.out.println("Totale Porzioni" + totalePorzioni);
	                    stmt1 = conn1.prepareStatement(sql);
	                    stmt1.setString(1,nome_prodotto);
	                    stmt1.setInt(2,totalePorzioni);
	                    stmt1.setInt(3, totaleCosto);
	                    stmt1.execute();
	                    stmt1.close();
	                    //rs1.close();
	                    conn1.close();
	                 
	                    
	                    JOptionPane.showMessageDialog(null, "Ordine Inviato!");

	                }catch(SQLException ex) {
	                    ex.printStackTrace();
	                    JOptionPane.showMessageDialog(null, "Ordine non Inviato");
	                 
	                }
	            });
	            
	          
	           
	   	     
	            panel.add(buttonImmagine); // aggiungi il bottone al pannello
	            
	           
	        }
	        

	     
	        
	        rs.close();
	        stmt.close();
	        conn.close();
	    	}catch(SQLException e) {
	        e.printStackTrace();
	        }

        }
        indietro.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		new Tavoli();
        		dispose();
        	}
        }
        		);
        panel.add(indietro);
	    
	     
	    JScrollPane scrollPane = new JScrollPane(panel);
		 //scrollPane.setVerticalScrollBarPolicy ( ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS );
		 getContentPane().add(scrollPane);

	     setVisible(true);
	  
	}
	
	
}