package net.codejava;

import java.sql.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.*;


public class Cassa extends JFrame{
	
		
	ArrayList<Double> ordini = new ArrayList<Double>();
	ArrayList<String> stringa = new ArrayList<String>();
	ArrayList<Integer> quantitaa = new ArrayList<Integer>();
	
	private static final String DB_URL = "jdbc:mysql://localhost/ristorante";
	private static final String DB_USER = "root";
	private static final String DB_PASSWORD = "lacoste1999!";	
	
	private JLabel numeroTavolo,nomeProdotto,quantita,totaleOrdine;
	private JTextField numeroTavoloField, nomeProdottoField,quantitaField,totaleField;
	private JButton visualizzaOrdine,stampaScontrino,indietro;
	private JLabel inserisciNumeroTavoloLabel,vuoto;
	private JTextField inserisciNumeroTavoloField;
	private JLabel campo;
	
	public Cassa() {
	
		inserisciNumeroTavoloLabel = new JLabel("Inserisci il numero del tavolo: ");
		inserisciNumeroTavoloLabel.setForeground(Color.white);
		Font font = new Font("Arial", Font.BOLD, 20);
		inserisciNumeroTavoloLabel.setFont(font);
		inserisciNumeroTavoloField = new JTextField();
		
		vuoto = new JLabel();
		
		visualizzaOrdine = new JButton("Visualizza Ordine");
		stampaScontrino = new JButton("Stampa Scontrino");
		visualizzaOrdine.setBackground(Color.WHITE);
		visualizzaOrdine.setForeground(Color.PINK);
		Font buttonFont = new Font("Arial", Font.BOLD, 25);
		visualizzaOrdine.setFont(buttonFont);
		stampaScontrino.setBackground(Color.WHITE);
		stampaScontrino.setForeground(Color.PINK);
		Font buttonFont2 = new Font("Arial", Font.BOLD, 25);
		stampaScontrino.setFont(buttonFont2);
		
		indietro = new JButton("Torna al menu staff");
		indietro.setBackground(Color.WHITE);
		indietro.setForeground(Color.PINK);
		Font buttonFont3 = new Font("Arial", Font.BOLD, 25);
		indietro.setFont(buttonFont3);
		
		numeroTavolo = new JLabel("Numero Tavolo: ");
		numeroTavolo.setForeground(Color.white);
		Font font2 = new Font("Arial", Font.BOLD, 20);
		numeroTavolo.setFont(font2);
		
		nomeProdotto = new JLabel("NomeProdotti: ");
		nomeProdotto.setForeground(Color.white);
		Font font3 = new Font("Arial", Font.BOLD, 20);
		nomeProdotto.setFont(font3);
		
		quantita = new JLabel("Quantita: ");
		quantita.setForeground(Color.white);
		Font font4 = new Font("Arial", Font.BOLD, 20);
		quantita.setFont(font4);
		
		totaleOrdine = new JLabel("Totale Ordine: ");
		totaleOrdine.setForeground(Color.white);
		Font font5 = new Font("Arial", Font.BOLD, 20);
		totaleOrdine.setFont(font5);
		
		
		numeroTavoloField = new JTextField();
		nomeProdottoField = new JTextField();
		quantitaField = new JTextField();
		totaleField = new JTextField();
		
		JTextArea area = new JTextArea();
		
		campo = new JLabel();

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(1680, 1050);
        ImageIcon icona = new ImageIcon("C:\\Users\\salvatore\\eclipse-workspace\\GestioneRistoranteDueVersion\\logo.jpg");
        setIconImage(icona.getImage());
        
		JPanel panel = new JPanel() {
			@Override
			protected void paintComponent(Graphics g) {
				super.paintComponent(g);
				ImageIcon imageIcon = new ImageIcon("C:\\Users\\salvatore\\eclipse-workspace\\GestioneRistoranteDueVersion\\sfondoarancione.jpg");
				Image image = imageIcon.getImage();
				g.drawImage(image, 0, 0, getWidth(), getHeight(), this);
			}
		};
		panel.setLayout(new GridLayout(8,2));
		
		for(int i=0;i<1;i++) {
		
		JTextArea visualizza = new JTextArea();
		
		/*
		setTitle("Cassa");
		setSize(1180,820);
		setLocationRelativeTo(null);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		*/
		
		panel.add(inserisciNumeroTavoloLabel);
		panel.add(inserisciNumeroTavoloField);
		panel.add(visualizzaOrdine);
		panel.add(visualizza);
		JScrollPane scroll1 = new JScrollPane(visualizza);
        scroll1.setVerticalScrollBarPolicy ( ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS );
        panel.add(scroll1);
		panel.add(stampaScontrino);
		panel.add(campo);
		panel.add(numeroTavolo);
		panel.add(numeroTavoloField);
		numeroTavoloField.setEditable(false);
		panel.add(nomeProdotto);
		panel.add(area);
		JScrollPane    scroll = new JScrollPane(area);
        scroll.setVerticalScrollBarPolicy ( ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS );
        panel.add(scroll);
		nomeProdottoField.setEditable(false);
		panel.add(quantita);
		panel.add(quantitaField);
		quantitaField.setEditable(false);
		panel.add(totaleOrdine);
		panel.add(totaleField);
		panel.add(vuoto);
		panel.add(indietro);
		totaleField.setEditable(false);
		
		
		
		visualizzaOrdine.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Connection conn = null;
		        try {
		            conn = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);

		            Statement stmt = conn.createStatement();
		            int idTavolo = Integer.parseInt(inserisciNumeroTavoloField.getText());
		            String sql = "select tavolo.numero_tavolo, ordini_clienti.nome_prodotto,ordini_clienti.quantita,ordini_clienti.totale FROM tavolo  JOIN ordini_clienti ON tavolo.numero_tavolo = ordini_clienti.id_tavolo WHERE id_tavolo=" + idTavolo;

		            ResultSet rs = stmt.executeQuery(sql);
		            
		            while(rs.next()) {
		            	int idTavolo2 = rs.getInt("numero_tavolo");
		            	String str2 = Integer.toString(idTavolo2);
		            	numeroTavoloField.setText(str2);
		                String nomeProdotto = rs.getString("nome_prodotto");
		                int quantita = rs.getInt("quantita");
		                //boolean cucina = rs.getBoolean("cucina");
		                //boolean cassa = rs.getBoolean("cassa");
		                double totale = rs.getDouble("totale");
		                ordini.add(totale);
		                System.out.println(ordini);
		                visualizza.append("NumeroTavolo: " + idTavolo2 + ",NomeProdotto: " + nomeProdotto + ",Quantita: " + quantita + ",Totale: " + totale + "\n");
		                boolean ordineTrovato = false;
		                String nomeProdotto2 = rs.getString("nome_prodotto");
			        	 stringa.add(nomeProdotto2);
			        	 System.out.println(stringa);
			        	 area.append(nomeProdotto2 + "\n");
			        	 //nomeProdottoField.setText(nomeProdotto);
			        	 int quantita1 = rs.getInt("quantita");
			        	 quantitaa.add(quantita1);
			        	 System.out.println(quantitaa);
			        	 int somma=0;
			        	 for(int quantitaaa: quantitaa) {
			        		 somma += quantitaaa;
			        	 }
			        	 String str1 = Integer.toString(somma);
			        	 quantitaField.setText(str1);
		            } 
		            
		            rs.close();
		            stmt.close();
		            conn.close();
		            

		        } catch (SQLException e1) {
		            e1.printStackTrace();
		        }
			}
		}
				);
		
		stampaScontrino.addActionListener(new ActionListener() {
		    public void actionPerformed(ActionEvent e) {
		        double somma = 0;
		        for(double ordine: ordini) {
		            somma += ordine;
		        }
		        String str = Double.toString(somma);
		        totaleField.setText(str);
		        

		        
		        Connection conn = null;
		        String sql = "DELETE FROM ordini_clienti WHERE id >=1";

		        try {
		            conn = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);

		            Statement stmt = conn.createStatement();
		            String sql2 = "DELETE FROM ordini_clienti WHERE id >= 1";
		            int rowsAffected = stmt.executeUpdate(sql2);
		            
		            stmt.close();
		            conn.close();

		        } catch(SQLException e2) {
		            e2.printStackTrace();
		        }
		        
		        visualizza.setText("");
		    }
		});
		
		indietro.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new SceltaStaff();
				dispose();
			}
		}
				);

		
		
		add(panel);
		setVisible(true);
	}
	}
	

	
	
	

}