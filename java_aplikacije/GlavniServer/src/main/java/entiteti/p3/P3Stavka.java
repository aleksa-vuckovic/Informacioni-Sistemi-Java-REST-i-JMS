/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package entiteti.p3;

import java.io.Serializable;
import javax.json.bind.annotation.JsonbTransient;

/**
 *
 * @author Aleksa
 */
public class P3Stavka implements Serializable {

    private static final long serialVersionUID = 1L;
    @JsonbTransient
    protected P3StavkaPK p3StavkaPK;
    private int kolicina;
    private float cena;
    private P3Artikl p3Artikl;
    @JsonbTransient
    private P3Narudzbina p3Narudzbina;

    public P3Stavka() {
    }

    public P3Stavka(P3StavkaPK p3StavkaPK) {
        this.p3StavkaPK = p3StavkaPK;
    }

    public P3Stavka(P3StavkaPK p3StavkaPK, int kolicina, float cena) {
        this.p3StavkaPK = p3StavkaPK;
        this.kolicina = kolicina;
        this.cena = cena;
    }

    public P3Stavka(int idNar, int idArt) {
        this.p3StavkaPK = new P3StavkaPK(idNar, idArt);
    }

    public P3StavkaPK getP3StavkaPK() {
        return p3StavkaPK;
    }

    public void setP3StavkaPK(P3StavkaPK p3StavkaPK) {
        this.p3StavkaPK = p3StavkaPK;
    }

    public int getKolicina() {
        return kolicina;
    }

    public void setKolicina(int kolicina) {
        this.kolicina = kolicina;
    }

    public float getCena() {
        return cena;
    }

    public void setCena(float cena) {
        this.cena = cena;
    }

    public P3Artikl getP3Artikl() {
        return p3Artikl;
    }

    public void setP3Artikl(P3Artikl p3Artikl) {
        this.p3Artikl = p3Artikl;
    }

    public P3Narudzbina getP3Narudzbina() {
        return p3Narudzbina;
    }

    public void setP3Narudzbina(P3Narudzbina p3Narudzbina) {
        this.p3Narudzbina = p3Narudzbina;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (p3StavkaPK != null ? p3StavkaPK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof P3Stavka)) {
            return false;
        }
        P3Stavka other = (P3Stavka) object;
        if ((this.p3StavkaPK == null && other.p3StavkaPK != null) || (this.p3StavkaPK != null && !this.p3StavkaPK.equals(other.p3StavkaPK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entiteti.p3.P3Stavka[ p3StavkaPK=" + p3StavkaPK + " ]";
    }
    
}
