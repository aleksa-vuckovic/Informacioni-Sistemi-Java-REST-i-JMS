/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package grafika;

import java.awt.event.ActionListener;
import java.awt.event.WindowListener;
import java.util.Map;
import javax.swing.JFrame;

/**
 *
 * @author Aleksa
 */
public class Grafika extends JFrame {
    private HomePanel homePanel;
    private ZahtevPanel zahtevPanel;
    ActionListener nazadListener, kreniListener;
    
    public Grafika(String[] opcije, ActionListener daljeListener, ActionListener nazadListener, ActionListener kreniListener, WindowListener windowListener) {
        this.addWindowListener(windowListener);
        this.nazadListener = nazadListener;
        this.kreniListener = kreniListener;
        homePanel = new HomePanel(opcije, daljeListener);
        zahtevPanel = null;
        
        this.add(homePanel);
        this.setVisible(true);
        this.setBounds(400, 300, 500, 500);
        this.pack();
    }
    
    public void prikaziZahtev(String[] nazivi) {
        zahtevPanel = new ZahtevPanel(nazivi, nazadListener, kreniListener);
        this.getContentPane().removeAll();
        this.add(zahtevPanel);
        this.pack();
        this.setSize(this.getWidth() < 300 ? 300 : this.getWidth(), 500);
        this.revalidate();
    }
    
    public void prikaziHome() {
        this.getContentPane().removeAll();
        zahtevPanel = null;
        this.add(homePanel);
        this.pack();
        this.revalidate();
    }
    
    
    
    public String getKime() {
        return homePanel.getKime();
    }
    public String getSifra() {
        return homePanel.getSifra();
    }
    public String getOpcija() {
        return homePanel.getOpcija();
    }
    public Map<String, String> unos() {
        if (zahtevPanel == null) return null;
        return zahtevPanel.unos();
    }
    public void postaviIspis(String ispis) {
        if (zahtevPanel != null) zahtevPanel.postaviIspis(ispis);
    }
}
