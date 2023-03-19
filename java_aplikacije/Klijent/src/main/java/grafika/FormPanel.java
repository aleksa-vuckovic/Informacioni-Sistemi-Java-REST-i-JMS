/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package grafika;

import java.awt.GridLayout;
import java.util.HashMap;
import java.util.Map;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

/**
 *
 * @author Aleksa
 */
public class FormPanel extends JPanel {
    private JLabel[] labele;
    private JTextField[] polja;
    private int n;
    public FormPanel(String[] nazivi) {
        n = nazivi.length;
        labele = new JLabel[n];
        polja = new JTextField[n];
        for (int i = 0; i < n; i++) {
            labele[i] = new JLabel(nazivi[i]);
            polja[i] = new JTextField(15);
        }
        int cols = n / 5 + 1;
        this.setLayout(new GridLayout(0, cols * 2));
        
        for (int i = 0; i < n; i++) {
            this.add(new PanelOmotac(labele[i]));
            this.add(new PanelOmotac(polja[i]));
        }
    }
    
    public Map<String, String> unos() {
        Map<String, String> res = new HashMap<>();
        for (int i = 0; i < n; i++) res.put(labele[i].getText(), polja[i].getText());
        return res;
    }
    
    
}
