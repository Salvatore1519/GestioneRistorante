package net.codejava;


import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.*;
import java.sql.*;
import javax.imageio.ImageIO;
import javax.swing.*;

public class VisualizzaImmagine extends JFrame {


	private JLabel campo1,campo2;
    private JPanel pnlImmagini;

    public VisualizzaImmagine() {
    	
    	campo1 = new JLabel("campo1");
    	campo2 = new JLabel("campo2");
    	
        setTitle("Visualizzatore Immagini");
        setSize(800,600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        //panel immagini
        pnlImmagini = new JPanel();
        pnlImmagini.setLayout(new BoxLayout(pnlImmagini, BoxLayout.Y_AXIS));
        pnlImmagini.add(campo1);

        try {
            Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/ristorante", "root", "Davide123@");
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * FROM menu where id = 1 or  ID = 2");
            while(rs.next()) {
                byte[] bytes = rs.getBytes("foto");
                BufferedImage img = ImageIO.read(new ByteArrayInputStream(bytes));
                ImageIcon icon = new ImageIcon(img.getScaledInstance(300, 180, BufferedImage.SCALE_SMOOTH));
                JLabel lblImmagine = new JLabel(icon);
                pnlImmagini.add(lblImmagine);
            }
            conn.close();
        } catch(Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this, "Errore durante il recupero dell'immagine.");
        }
        JScrollPane scrollPane = new JScrollPane(pnlImmagini);
        add(scrollPane, BorderLayout.CENTER);

        setVisible(true);
    }

    public static void main(String[] args) {
        new VisualizzaImmagine();
    }


}