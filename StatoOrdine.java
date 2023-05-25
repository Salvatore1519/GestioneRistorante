package net.codejava;

import javax.swing.*;
import java.sql.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.*;
import javax.swing.text.JTextComponent;
import java.util.*;
import java.awt.image.BufferedImage;



public class StatoOrdine extends JFrame{

	ArrayList<Double> ordini = new ArrayList<Double>();
	
	private Connection conn;
	private JLabel idOrdineLabel;
	private JTextField idField;
	private JCheckBox prontoCheckBox;
	private JButton confermaButton;
	private JButton indietro;
	
	public StatoOrdine() {
		
		idOrdineLabel = new JLabel("Inserisci ID Ordine:");
		Font labelFont = idOrdineLabel.getFont();
		int labelFontSize = labelFont.getSize() + 25;
		Font newFont = new Font(labelFont.getName(), labelFont.getStyle(), labelFontSize);
		idOrdineLabel.setFont(newFont);
		idOrdineLabel.setForeground(Color.ORANGE);
		idOrdineLabel.setBackground(Color.WHITE);
		
		idField = new JTextField();
		Font font = new Font("Arial", Font.PLAIN, 50);
		idField.setFont(font);
		
		confermaButton = new JButton("Conferma");
		Font buttonFont = new Font("Arial", Font.BOLD, 35);
		confermaButton.setFont(buttonFont);
		confermaButton.setBackground(Color.WHITE);
		confermaButton.setForeground(Color.ORANGE);
		
		indietro = new JButton("Torna in cucina");
		Font buttonFont6 = new Font("Arial", Font.BOLD, 35);
		indietro.setFont(buttonFont6);
		indietro.setBackground(Color.WHITE);
		indietro.setForeground(Color.ORANGE);
		
		JCheckBox prontoCheckBox = new JCheckBox("Ordine Pronto");
		prontoCheckBox.setPreferredSize(new Dimension(70, 70));
		prontoCheckBox.setForeground(Color.ORANGE);

		prontoCheckBox.setIcon(null);

		prontoCheckBox.setFont(new Font("Arial", Font.BOLD, 45));

		
		JPanel panel = new JPanel(new GridLayout(5,2));
		
		panel.add(idOrdineLabel);
		panel.add(idField);
		panel.add(prontoCheckBox);
		panel.add(confermaButton);	
		panel.add(indietro);
		add(panel);
		
		ImageIcon icona = new ImageIcon("C:\\Users\\salvatore\\eclipse-workspace\\GestioneRistoranteDueVersion\\logo.jpg");
        setIconImage(icona.getImage());
		setTitle("Gestione Ordini");
		setSize(1180,820);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setLocationRelativeTo(null);
		setVisible(true);
		
		 confermaButton.addActionListener(new ActionListener() {
			 public void actionPerformed(ActionEvent e) {
				 Connection conn = null;
				 PreparedStatement stmt = null;
				 String sql =  "UPDATE ordini_clienti SET cucina=true  WHERE id=?";
				 
				 try {
					 conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/ristorante",
								"root", "lacoste1999!");
					 
					 String id_modifica = idField.getText().toString();
					 stmt = conn.prepareStatement(sql);
						stmt.setString(1, id_modifica);
						stmt.executeUpdate();
						
						JOptionPane.showMessageDialog(null, "Ordine aggiornato correttamente");
						idField.setText(" ");
					 
				 }catch(SQLException ex) {
					 ex.printStackTrace();	
					 JOptionPane.showMessageDialog(null, "Ordine non aggiornato correttamente");
				 }
			 }
		 }
				 );
		 
		 indietro.addActionListener(new ActionListener() {
			 public void actionPerformed(ActionEvent e) {
				 new Cucina();
				 dispose();
			 }
		 }
				 );
	}
	
	
}
