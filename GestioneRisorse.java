package net.codejava;

import javax.swing.*;
import java.sql.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.*;



public class GestioneRisorse extends JFrame{









	private static final String DB_URL = "jdbc:mysql://localhost/ristorante";
	private static final String DB_USER = "root";
	private static final String DB_PASSWORD = "lacoste1999!";
	private JLabel statusLabel;
	private JTextField prezzoField,categoriaField,portataField;
	;
	private JLabel prezzoLabel, portataLabel,categoriaLabel;

	

	private JButton inserisciButton,indietro,inviaButton,aggiungiFotoBtn;
	JTextArea area ;
	JPanel panel;


	public GestioneRisorse() {

		super("Image Uploader");


		
		area = new JTextArea();
		ImageIcon icona = new ImageIcon("C:\\Users\\salvatore\\eclipse-workspace\\GestioneRistoranteDueVersion\\logo.jpg");
        setIconImage(icona.getImage());
		setTitle("Aggiungi Al Menu");
		setSize(1680,1050);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		setLocationRelativeTo(null);

		JPanel panel = new JPanel() {
			@Override
			protected void paintComponent(Graphics g) {
				super.paintComponent(g);
				ImageIcon imageIcon = new ImageIcon("C:\\Users\\salvatore\\eclipse-workspace\\GestioneRistoranteDueVersion\\sfondoarancione.jpg");
				Image image = imageIcon.getImage();
				g.drawImage(image, 0, 0, getWidth(), getHeight(), this);
			}
		};
		panel.setLayout(new GridLayout(10,2));
	

		portataField= new JTextField();
		Font font = new Font("Arial", Font.PLAIN, 20);
		portataField.setFont(font);
		
		portataLabel = new JLabel("Nome Portata " );
		Font labelFont = portataLabel.getFont();
		int labelFontSize = labelFont.getSize() + 35;
		Font newFont = new Font(labelFont.getName(), labelFont.getStyle(), labelFontSize);
		portataLabel.setFont(newFont);
/*
		categoriaField= new JTextField();
		Font font2 = new Font("Arial", Font.PLAIN, 20);
		categoriaField.setFont(font2);
*/
	/*
		categoriaLabel = new JLabel("Iserisci Categoria " );
		Font labelFont2 =categoriaLabel.getFont();
		int labelFontSize2 = labelFont2.getSize() + 35;
		Font newFont2 = new Font(labelFont2.getName(), labelFont2.getStyle(), labelFontSize2);
		categoriaLabel.setFont(newFont2);
*/
		
		
		
		 String[] options = {"Pizze", "Primi", "Secondi","Antipasti","bibite"};
	      JComboBox<String> selectList = new JComboBox<>(options);
	      panel.add(selectList);
	      selectList.addActionListener(new ActionListener() {
	          public void actionPerformed(ActionEvent e) {
	             // Get the selected item
	             String selectedOption = (String) selectList.getSelectedItem();
	             // Do something with the selected option
	             System.out.println("Selected option: " + selectedOption);
	             
	          
	          }
	       });
		
		
	  JLabel 	DescrizioneLabel = new JLabel("Descrizione ");
	  Font labelFont3 = DescrizioneLabel.getFont();
	  int labelFontSize3 = labelFont3.getSize() + 35;
	  Font newFont3 = new Font(labelFont3.getName(), labelFont3.getStyle(), labelFontSize3);
	  DescrizioneLabel.setFont(newFont3);
	  

	  JTextArea AreaDescrizione=new  JTextArea();
	  Font font4 = AreaDescrizione.getFont();
		float size = font.getSize() + 15.0f;
		Font newFont4 = font.deriveFont(size);
		AreaDescrizione.setFont(newFont4);




		prezzoField = new JTextField();
		Font font3 = new Font("Arial", Font.PLAIN, 50);
		prezzoField.setFont(font3);

		

		prezzoField = new JTextField();


		prezzoLabel= new JLabel("Inserisci Prezzo");
		Font labelFont4 = prezzoLabel.getFont();
		int labelFontSize4 = labelFont4.getSize() + 35;
		Font newFont5 = new Font(labelFont4.getName(), labelFont4.getStyle(), labelFontSize4);
		prezzoLabel.setFont(newFont5);


	



		panel.setBackground(Color.white);

		indietro = new JButton("Torna al menu Staff");
		Font buttonFont = new Font("Arial", Font.BOLD, 35);
		indietro.setFont(buttonFont);

		inserisciButton = new JButton("Conferma Password");
		inserisciButton.setBackground(Color.ORANGE);
		Font buttonFont2 = new Font("Arial", Font.BOLD, 35);
		inserisciButton.setFont(buttonFont2);


		inviaButton = new JButton("AggiungiFoto");
		Font buttonFont3 = new Font("Arial", Font.BOLD, 35);
		inviaButton.setFont(buttonFont3);
		inviaButton.setBackground(Color.pink);
		indietro.setBackground(Color.pink);

		
/*
		panel.add(categoriaLabel);
		panel.add(categoriaField);
*/
		panel.add(portataLabel);
		
		panel.add(portataField);
		
		panel.add(prezzoLabel);
		panel.add(prezzoField);
		panel.add(DescrizioneLabel);
		panel.add(AreaDescrizione);
		panel.add(inviaButton);
		
		
		panel.add(indietro);

		





		inviaButton.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent acc) {
				String categoria= (String) selectList.getSelectedItem();
				String portate =  portataField.getText();
				String descrizione = AreaDescrizione.getText();
				double prezzo= (double) Double.parseDouble(prezzoField.getText());

				Connection conn = null;
				PreparedStatement stmt = null;
				ResultSet rs = null;
				
				try {

					

						String sql_editore = "Insert INTO menu(categoria,portate,prezzo,descrizione) VALUES(?,?,?,?)";
						conn= DriverManager.getConnection(DB_URL,DB_USER,DB_PASSWORD);
						stmt=conn.prepareStatement(sql_editore);

						stmt.setString(1, categoria);
						stmt.setString(2, portate);
						stmt.setDouble(3,prezzo);
						stmt.setString(4,descrizione);
						stmt.execute();

					}catch(Exception  e ) {
						e.printStackTrace();
					}

				
				try {
					stmt.close();
					conn.close();
					
				} catch (SQLException e) {
					
					e.printStackTrace();
				}
			
					

				
				new CaricaImmagini();

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

		public static void main(String[]args) {
			new GestioneRisorse();
		}



}