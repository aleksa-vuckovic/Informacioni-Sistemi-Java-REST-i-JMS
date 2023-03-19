/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package grafika;

import java.awt.BorderLayout;
import java.awt.event.ActionListener;
import java.util.Map;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

/**
 *
 * @author Aleksa
 */
public class ZahtevPanel extends JPanel {
    private FormPanel formPanel;
    private JTextArea ispis;
    
    public ZahtevPanel(String[] nazivi, ActionListener nazadListener, ActionListener kreniListener) {
        formPanel = new FormPanel(nazivi);
        ispis = new JTextArea();
        JScrollPane ispisScrollPane = new JScrollPane(ispis);
        ispis.setEditable(false);
        JButton kreni = new JButton("kreni");
        kreni.addActionListener(kreniListener);
        JButton nazad = new JButton("nazad");
        nazad.addActionListener(nazadListener);
        
        this.setLayout(new BorderLayout());
        this.add(formPanel, BorderLayout.NORTH);
        this.add(ispisScrollPane, BorderLayout.CENTER);
        
        JPanel bottom = new JPanel();
        bottom.add(nazad);
        bottom.add(kreni);
        this.add(bottom, BorderLayout.SOUTH);
    }
    
    public void postaviIspis(String tekst) {
        ispis.setText(tekst);
    }
    
    public Map<String, String> unos() {
        return formPanel.unos();
    }
    
}
