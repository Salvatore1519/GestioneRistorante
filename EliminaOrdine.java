package net.codejava;

import javax.swing.*;
import java.sql.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.*;
import javax.swing.text.JTextComponent;
import java.util.*;


public class EliminaOrdine extends JFrame{

ArrayList<Double> ordini = new ArrayList<Double>();
	
	private Connection conn;
	private JLabel idOrdineLabel;
	private JTextField idField;
	private JButton confermaButton;
	private JButton indietro;
	
	public EliminaOrdine() {
		
		idOrdineLabel = new JLabel("Inserisci ID Ordine:");
		idOrdineLabel.setHorizontalAlignment(SwingConstants.CENTER);
		idOrdineLabel.setVerticalAlignment(SwingConstants.CENTER);
		Font labelFont = idOrdineLabel.getFont();
		int labelFontSize = labelFont.getSize() + 25;
		Font newFont = new Font(labelFont.getName(), labelFont.getStyle(), labelFontSize);
		idOrdineLabel.setFont(newFont);
		idOrdineLabel.setForeground(Color.WHITE);
		
		idField = new JTextField();
		Font font = new Font("Arial", Font.PLAIN, 50);
		idField.setFont(font);
		
		confermaButton = new JButton("Conferma");
		Font buttonFont = new Font("Arial", Font.BOLD, 35);
		confermaButton.setFont(buttonFont);
		confermaButton.setBackground(Color.WHITE);
		confermaButton.setForeground(Color.PINK);
		
		indietro = new JButton("Torna In cucina");
		Font buttonFont3 = new Font("Arial", Font.BOLD, 35);
		indietro.setFont(buttonFont3);
		indietro.setBackground(Color.WHITE);
		indietro.setForeground(Color.PINK);
		
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
		panel.setLayout(new BorderLayout());
		
		panel.add(idOrdineLabel, BorderLayout.NORTH);
		panel.add(idField, BorderLayout.CENTER);
		panel.add(confermaButton, BorderLayout.SOUTH);
		panel.add(indietro, BorderLayout.WEST);
		add(panel);
		
		
		setTitle("Elimina Ordine");
		setSize(1180,820);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setLocationRelativeTo(null);
		setVisible(true);
		
		confermaButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Connection conn = null;
				PreparedStatement stmt = null;
				String sql = "DELETE FROM ordini_clienti WHERE id = ?";
				
				try {
					String id_modifica = idField.getText().toString();
					
					conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/ristorante",
							"root", "lacoste1999!");
					
					stmt = conn.prepareStatement(sql);
					stmt.setString(1, id_modifica);
					stmt.executeUpdate();
					
					idField.setText(" ");
					
					JOptionPane.showMessageDialog(null, "Ordine cancellato correttamente");
					
					
				} catch(SQLException ex) {
					ex.printStackTrace();
					JOptionPane.showMessageDialog(null, "Ordine non cancellato correttamente");
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
	public static void main(String[] args) {
		new EliminaOrdine();
	}

}