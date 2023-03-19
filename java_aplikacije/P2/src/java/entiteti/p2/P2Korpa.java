/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package entiteti.p2;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

/**
 *
 * @author Aleksa
 */
@Entity
@Table(name = "p2_korpa")
@NamedQueries({
    @NamedQuery(name = "P2Korpa.findAll", query = "SELECT p FROM P2Korpa p"),
    @NamedQuery(name = "P2Korpa.findByIdKor", query = "SELECT p FROM P2Korpa p WHERE p.p2KorpaPK.idKor = :idKor"),
    @NamedQuery(name = "P2Korpa.findByIdArt", query = "SELECT p FROM P2Korpa p WHERE p.p2KorpaPK.idArt = :idArt"),
    @NamedQuery(name = "P2Korpa.findByKolicina", query = "SELECT p FROM P2Korpa p WHERE p.kolicina = :kolicina")})
public class P2Korpa implements Serializable {

    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected P2KorpaPK p2KorpaPK;
    @Basic(optional = false)
    @NotNull
    @Column(name = "Kolicina")
    private int kolicina;
    @JoinColumn(name = "IdArt", referencedColumnName = "IdArt", insertable = false, updatable = false)
    @ManyToOne(optional = false, fetch = FetchType.EAGER)
    private P2Artikl p2Artikl;
    @JoinColumn(name = "IdKor", referencedColumnName = "IdKor", insertable = false, updatable = false)
    @ManyToOne(optional = false, fetch = FetchType.EAGER)
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
