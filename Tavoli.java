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
import java.util.Scanner;
import java.io.*;
import javax.swing.*;





public class Tavoli extends JFrame {

	private static final String DB_URL = "jdbc:mysql://localhost/ristorante";
	private static final String DB_USER = "root";
	private static final String DB_PASSWORD = "lacoste1999!";
	  int i=0;
	private JPanel panel,panelOrdine;
	
	private JTextField idField  ;
	private JTextArea informazioniArea;
	private JLabel nomeProdottoLabel;
	private JButton categoriaPizzaButton,categoriaPrimiButton,categoriaantiPastiButton,categoriaSecondiButton,visualizzaButton,indietroButton;
	private JButton indietro;
	private JButton bibite;
	
	public Tavoli() {
		
  // Costrutore Tavoli
		super("MenuBarDemo");
		
		
		
		

		idField = new JTextField();
		informazioniArea = new JTextArea();
		visualizzaButton = new JButton("Visualizza Ordini");
		
		JLabel gifLabel = new JLabel();
		ImageIcon gifIcon = new ImageIcon("C:\\Users\\Salvatore\\Desktop\\ImmagginiRisotorante\\cameriere-0057.gif");
		
		Font buttonFont = new Font("Arial", Font.BOLD, 35);
		visualizzaButton.setFont(buttonFont);
		
		indietro = new JButton("Torna al menu Principale");
		Font buttonFont6 = new Font("Arial", Font.BOLD, 35);
		indietro.setFont(buttonFont6);
		indietro.setBackground(Color.PINK);
		
		bibite = new JButton("Categoria Bibite");
		Font buttonFont7 = new Font("Arial", Font.BOLD, 35);
		bibite.setFont(buttonFont7);
		bibite.setBackground(Color.WHITE);
		bibite.setForeground(Color.PINK);
		
	
		

		//AGGIUNTA DEI COMPONENTI		
		 panelOrdine = new JPanel(new BorderLayout());
		
		panelOrdine.add(informazioniArea, BorderLayout.CENTER);
		panelOrdine.add(visualizzaButton, BorderLayout.LINE_START);
		

		//AGGIUNTA SCROLL		
		JScrollPane    scroll = new JScrollPane(informazioniArea);
		scroll.setVerticalScrollBarPolicy ( ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS );
		panelOrdine.add(scroll);
		
	
		setTitle("Cucina");
		setSize(1180,820);
		setLocationRelativeTo(null);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setVisible(true);


		
		


		
		
		
		
		
		
		
		
		
		
		
		
		   // MenuBar
		categoriaPizzaButton = new JButton("Categoria Pizza");
		Font buttonFont2 = new Font("Arial", Font.BOLD, 35);
		categoriaPizzaButton.setFont(buttonFont2);
		categoriaPizzaButton.setBackground(Color.WHITE);
		categoriaPizzaButton.setForeground(Color.PINK);
		
		categoriaPrimiButton = new JButton("Categoria Primi");
		Font buttonFont3 = new Font("Arial", Font.BOLD, 35);
		categoriaPrimiButton.setFont(buttonFont3);
		categoriaPrimiButton.setBackground(Color.WHITE);
		categoriaPrimiButton.setForeground(Color.PINK);
		
		categoriaSecondiButton = new JButton("Categoria Secondi");
		Font buttonFont4 = new Font("Arial", Font.BOLD, 35);
		categoriaSecondiButton.setFont(buttonFont4);
		categoriaSecondiButton.setBackground(Color.WHITE);
		categoriaSecondiButton.setForeground(Color.PINK);
		
		categoriaantiPastiButton = new JButton("AntiPasti");
		Font buttonFont5 = new Font("Arial", Font.BOLD, 35);
		categoriaantiPastiButton.setFont(buttonFont5);
		categoriaantiPastiButton.setBackground(Color.WHITE);
		categoriaantiPastiButton.setForeground(Color.PINK);
		
		
		indietroButton= new JButton("Torna Ai Menu");
		Font buttonFont8= new Font("Arial", Font.BOLD, 35);
		indietroButton.setFont(buttonFont8);
		indietroButton.setBackground(Color.WHITE);
		indietroButton.setForeground(Color.PINK);
		
		
		ImageIcon icona = new ImageIcon("C:\\Users\\salvatore\\eclipse-workspace\\GestioneRistoranteDueVersion\\logo.jpg");
        setIconImage(icona.getImage());
		setTitle("Gestione Prodotti");
		setSize(1180,820);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		setLocationRelativeTo(null);



	
		
		
		// Creazione Logo 
		panel = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
            	panel.setLayout(new  GridLayout(8,2));
        		gifLabel.setIcon(gifIcon);
        		panel.add(gifLabel);
                ImageIcon imageIcon = new ImageIcon("C:\\Users\\salvatore\\eclipse-workspace\\GestioneRistoranteDueVersion\\sfondoarancione.jpg");
                Image image = imageIcon.getImage();
                g.drawImage(image, 0, 0, getWidth(), getHeight(), this);
            }
        };
		
		
		
		panelOrdine = new JPanel();
		
		
		
		
		panel.add(categoriaPizzaButton);
		
		panel.add(categoriaPrimiButton);
		
		panel.add(categoriaSecondiButton);
		
		panel.add(categoriaantiPastiButton);
		
		panel.add(bibite);
		
		panel.add(indietro);

		panel.setLayout(new  GridLayout(10,2));
         gifLabel.setIcon(icona);
         panel.add(gifLabel);

		
		
		
		
		super.setJMenuBar(createMenuBar());
		super.getContentPane().add(panel, BorderLayout.CENTER);




		// Bottoni Selezione Categorie
	
		categoriaPrimiButton.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent acc) {	
			try {
				new Primi();
				dispose();
			} catch (IOException e) {
				e.printStackTrace();
			}
			
			}
		});
		
		
		
		

		categoriaPizzaButton.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent acc) {	
				try {
					new Pizze();
					dispose();
				} catch (IOException e) {
					e.printStackTrace();
				}
		
			
			}
		});
		
		categoriaSecondiButton.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent acc) {	
			try {
				new Secondi();
				dispose();
			} catch (IOException e) {
				e.printStackTrace();
			}
		
		
			}
		});
		
		categoriaantiPastiButton.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent acc) {	
			try {
				new Antipasti();
				dispose();
			} catch (IOException e) {
				e.printStackTrace();
			}
			
		
			}
		});
		
		
		
		
		indietroButton.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent acc) {	
				System.out.println("Ciao");
			add(panel).setVisible(true);
			add(panelOrdine).setVisible(false);
			}
		});
		
		indietro.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new Main();
				dispose();
			}
		}
				);
		
		bibite.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					new Bibite();
					dispose();
				} catch (IOException e1) {
					e1.printStackTrace();
				}
			}
		}
				);
		
		
		add(panel);
		
		setVisible(true);
		
		
	}
	












 // Metodi Del Menubar
protected JMenuBar createMenuBar() {
	
	//Costruzione della menuBar
	JMenuBar menuBar = new JMenuBar();
	JMenu menuFile = new JMenu("Assistenza");
	Font menuFont = menuFile.getFont();
	int menuFontSize = menuFont.getSize() + 35;
	Font newFont = new Font(menuFont.getName(), menuFont.getStyle(), menuFontSize);
	menuFile.setFont(newFont);
	JMenuItem esci = new JMenuItem("Esci");
	Font menuFont4 = esci.getFont();
	int menuFontSize4 = menuFont4.getSize() + 25;
	Font newFont4 = new Font(menuFont4.getName(), menuFont4.getStyle(), menuFontSize4);
	esci.setFont(newFont4);
	JMenu radioButtonOrdine = new JMenu("Visualizza Ordine");
	Font menuFont2 = radioButtonOrdine.getFont();
	int menuFontSize2 = menuFont2.getSize() + 25;
	Font newFont2 = new Font(menuFont2.getName(), menuFont2.getStyle(), menuFontSize2);
	radioButtonOrdine.setFont(newFont2);
	JMenu radioButtonMenu = new JMenu("Richiedi Assistenza");
	Font menuFont3 = radioButtonMenu.getFont();
	int menuFontSize3 = menuFont3.getSize() + 25; 
	Font newFont3= new Font(menuFont3.getName(), menuFont3.getStyle(), menuFontSize3);
	radioButtonMenu.setFont(newFont3);

	JRadioButtonMenuItem richiediAssistenzaRadio= new JRadioButtonMenuItem("Richiedi Assistenza");
	Font menuFont5 = richiediAssistenzaRadio.getFont();
	int menuFontSize5 = menuFont5.getSize() + 25;
	Font newFont5 = new Font(menuFont5.getName(), menuFont5.getStyle(), menuFontSize5);
	richiediAssistenzaRadio.setFont(newFont5);
	JRadioButtonMenuItem  visualizzaOrdine = new JRadioButtonMenuItem("Ordini In corso");
	Font menuFont6 = visualizzaOrdine.getFont();
	int menuFontSize6 = menuFont6.getSize() + 25;
	Font newFont6 = new Font(menuFont6.getName(), menuFont6.getStyle(), menuFontSize6);
	visualizzaOrdine.setFont(newFont6);
	ButtonGroup group = new ButtonGroup();



	radioButtonMenu.add(richiediAssistenzaRadio);
	radioButtonOrdine.add(visualizzaOrdine);
	menuFile.add(radioButtonOrdine);
	menuFile.add(radioButtonMenu);//popup pull-right
	menuFile.addSeparator();
	menuFile.add(esci);
	menuBar.add(menuFile);
	
	//Listeners

	richiediAssistenzaRadio.addActionListener(new AiutoActionListener());
	visualizzaOrdine.addActionListener(new VisualizzaActionListener());
	esci.addActionListener(new ExitActionListener());
	return menuBar;
}


// Metodi Menu Bar per visualizzare ordni e richiesta di assistenza
private class VisualizzaActionListener implements ActionListener{
	public void actionPerformed(ActionEvent e) {
		new VisualizzaOrdini();
		dispose();
		
	}
}


// Metodi Menu Bar
private class AiutoActionListener implements ActionListener{
	public void actionPerformed(ActionEvent e) {
		
		

		System.out.println("Ciao");
		JOptionPane.showMessageDialog(null,"La Tua Richiesta Di assistenza E Stata Inviata Con Successo Presto Saremo Da te");
	}
}
private class ExitActionListener implements ActionListener {
	public void actionPerformed(ActionEvent e) {
		JOptionPane.showMessageDialog(null," Logout Effettuato ");
		 new Main();
	}
}

private class AggiornaActionListener implements ActionListener{
	public void actionPerformed(ActionEvent e) {
		dispose();
		//new Prodotto();
	}
}


	

}