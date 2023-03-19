/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package grafika;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.JComboBox;

/**
 *
 * @author Aleksa
 */
public class HomePanel extends JPanel {
    private JTextField kime, sifra;
    private JComboBox<String> menu;
    
    public HomePanel(String[] opcije, ActionListener daljeListener) {
        kime = new JTextField(30);
        sifra = new JTextField(30);
        menu = new JComboBox<String>(opcije);
        
        JPanel cent = new JPanel(new GridLayout(0, 1));
        cent.add(new PanelOmotac(new JLabel("Korisnicko ime:")));
        cent.add(new PanelOmotac(kime));
        cent.add(new PanelOmotac(new JLabel("Sifra:")));
        cent.add(new PanelOmotac(sifra));
        cent.add(new PanelOmotac(menu));
        
        this.setLayout(new BorderLayout());
        this.add(cent, BorderLayout.CENTER);
        
        JButton dalje = new JButton("dalje");
        dalje.addActionListener(daljeListener);
        this.add(new PanelOmotac(dalje), BorderLayout.SOUTH);
    }
    
    public String getKime() {
        return kime.getText();
    }
    public String getSifra() {
        return sifra.getText();
    }
    public String getOpcija() {
        return (String)menu.getSelectedItem();
    }
}
