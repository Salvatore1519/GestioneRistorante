package net.codejava;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.datatransfer.*;
import java.awt.dnd.*;
import java.awt.event.*;
import java.io.*;
import java.sql.*;
import javax.swing.*;
import java.sql.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.*;
public class CaricaImmagini extends JFrame {

	private static final String DB_URL = "jdbc:mysql://localhost/ristorante";
	private static final String DB_USER = "root";
	private static final String DB_PASSWORD = "lacoste1999!";

	private JPanel dropPanel;
	private JLabel statusLabel;

	private JButton aggiungiBtn;

	public CaricaImmagini() {
		super("Caricamento Immagine Portata");
		setLayout(new BorderLayout());
		ImageIcon icona = new ImageIcon("C:\\Users\\salvatore\\eclipse-workspace\\GestioneRistoranteDueVersion\\logo.jpg");
        setIconImage(icona.getImage());

		// Create the drop panel and add it to the main frame
		dropPanel = new JPanel();
		dropPanel.setBorder(BorderFactory.createTitledBorder("Carica Immagine Portata"));
		dropPanel.setPreferredSize(new Dimension(300, 300));






		dropPanel.setDropTarget(new DropTarget() {
			public synchronized void drop(DropTargetDropEvent evt) {
				try {
					evt.acceptDrop(DnDConstants.ACTION_COPY);
					Transferable transferable = evt.getTransferable();
					if (transferable.isDataFlavorSupported(DataFlavor.javaFileListFlavor)) {
						// Get the dropped files and check if they are images
						java.util.List<File> files = (java.util.List<File>) transferable.getTransferData(DataFlavor.javaFileListFlavor);
						for (File file : files) {
							if (isImage(file)) {
								// Set the image in the drop panel
								dropPanel.removeAll();
								dropPanel.add(new JLabel(new ImageIcon(file.getAbsolutePath())));
								dropPanel.revalidate();
								dropPanel.repaint();
								statusLabel.setText("Image dropped: " + file.getName());
								// Save the image in the database
								saveImage(file);

							} else {
								statusLabel.setText("Invalid file type: " + file.getName());
							}
						}
					} else {
						statusLabel.setText("Invalid data flavor");
					}
				} catch (Exception ex) {
					ex.printStackTrace();
					statusLabel.setText("Error: " + ex.getMessage());
				}
			}




		});



		add(dropPanel, BorderLayout.CENTER);

		// Create the status label and add it to the main frame
		statusLabel = new JLabel("Carica Immagine Portata Qui:");
		add(statusLabel, BorderLayout.SOUTH);

		// Configure the main frame
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(1180, 800);
		setLocationRelativeTo(null);
		setVisible(true);
	}

	private boolean isImage(File file) {
		try {
			Image image = ImageIO.read(file);
			return (image != null);
		} catch (IOException ex) {
			return false;
		}
	}

	private void saveImage(File file) {
		String conferma =JOptionPane.showInputDialog("Confermi ? SI o  No");
		if(conferma.equalsIgnoreCase("si")) {
			try (Connection conn = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
					PreparedStatement stmt = conn.prepareStatement("update menu set foto= ?   where id=?")) {
				// Read the image data and store it in a byte array
				InputStream is = new FileInputStream(file);
				ByteArrayOutputStream baos = new ByteArrayOutputStream();
				byte[] buffer = new byte[4096];
				int bytesRead;
				while ((bytesRead = is.read(buffer)) != -1) {
					baos.write(buffer, 0, bytesRead);
				}
				int id=rsPrendiId();
				byte[] imageData = baos.toByteArray();
				// Set the parameters of the SQL statement and execute it
				System.out.println(file.getName());
				System.out.println(imageData);
				stmt.setInt(2,id);
				stmt.setBytes(1, imageData);
				stmt.executeUpdate();
				
				dispose();
 
			}catch(Exception e) {
				e.printStackTrace();
			}
		}else {
			JOptionPane.showMessageDialog(null, "Errore Nel Caricamento Del File");
		}

	}

	public int rsPrendiId() {
		int id=0;
	    try {
	        Connection conn = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
	        Statement stmt = conn.createStatement();
	        ResultSet rs = stmt.executeQuery("SELECT id FROM menu");
	        int count = 0;
	        int[] idArray = new int[100]; // 
	        while (rs.next()) {
	            int id_foto = rs.getInt("id");
	            idArray[count] = id_foto;
	            count++;
	        }
	        int maxId = idArray[0];
	        for (int i = 1; i < count; i++) {
	            if (idArray[i] > maxId) {
	                maxId = idArray[i];
	                id=maxId;
	            }
	        }
	        return id;
	    } catch (SQLException e) {
	        e.printStackTrace();
	    }
	  
		return id;
	    
	}







}
