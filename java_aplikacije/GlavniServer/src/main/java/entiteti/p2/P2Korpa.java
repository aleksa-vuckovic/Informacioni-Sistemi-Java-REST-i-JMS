/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package entiteti.p2;

import java.io.Serializable;

/**
 *
 * @author Aleksa
 */
public class P2Korpa implements Serializable {

    private static final long serialVersionUID = 1L;
    protected P2KorpaPK p2KorpaPK;
    private int kolicina;
    private P2Artikl p2Artikl;
    private P2Korisnik p2Korisnik;

    public P2Korpa() {
    }

    public P2Korpa(P2KorpaPK p2KorpaPK) {
        this.p2KorpaPK = p2KorpaPK;
    }

    public P2Korpa(P2KorpaPK p2KorpaPK, int kolicina) {
        this.p2KorpaPK = p2KorpaPK;
        this.kolicina = kolicina;
    }

    public P2Korpa(int idKor, int idArt) {
        this.p2KorpaPK = new P2KorpaPK(idKor, idArt);
    }

    public P2KorpaPK getP2KorpaPK() {
        return p2KorpaPK;
    }

    public void setP2KorpaPK(P2KorpaPK p2KorpaPK) {
        this.p2KorpaPK = p2KorpaPK;
    }

    public int getKolicina() {
        return kolicina;
    }

    public void setKolicina(int kolicina) {
        this.kolicina = kolicina;
    }

    public P2Artikl getP2Artikl() {
        return p2Artikl;
    }

    public void setP2Artikl(P2Artikl p2Artikl) {
        this.p2Artikl = p2Artikl;
    }

    public P2Korisnik getP2Korisnik() {
        return p2Korisnik;
    }

    public void setP2Korisnik(P2Korisnik p2Korisnik) {
        this.p2Korisnik = p2Korisnik;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (p2KorpaPK != null ? p2KorpaPK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof P2Korpa)) {
            return false;
        }
        P2Korpa other = (P2Korpa) object;
        if ((this.p2KorpaPK == null && other.p2KorpaPK != null) || (this.p2KorpaPK != null && !this.p2KorpaPK.equals(other.p2KorpaPK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entiteti.p2.P2Korpa[ p2KorpaPK=" + p2KorpaPK + " ]";
    }
    
}
