/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package entiteti.p3;

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
@Table(name = "p3_stavka")
@NamedQueries({
    @NamedQuery(name = "P3Stavka.findAll", query = "SELECT p FROM P3Stavka p"),
    @NamedQuery(name = "P3Stavka.findByIdNar", query = "SELECT p FROM P3Stavka p WHERE p.p3StavkaPK.idNar = :idNar"),
    @NamedQuery(name = "P3Stavka.findByIdArt", query = "SELECT p FROM P3Stavka p WHERE p.p3StavkaPK.idArt = :idArt"),
    @NamedQuery(name = "P3Stavka.findByKolicina", query = "SELECT p FROM P3Stavka p WHERE p.kolicina = :kolicina"),
    @NamedQuery(name = "P3Stavka.findByCena", query = "SELECT p FROM P3Stavka p WHERE p.cena = :cena")})
public class P3Stavka implements Serializable {

    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected P3StavkaPK p3StavkaPK;
    @Basic(optional = false)
    @NotNull
    @Column(name = "Kolicina")
    private int kolicina;
    @Basic(optional = false)
    @NotNull
    @Column(name = "Cena")
    private float cena;
    @JoinColumn(name = "IdArt", referencedColumnName = "IdArt", insertable = false, updatable = false)
    @ManyToOne(optional = false, fetch = FetchType.EAGER)
    private P3Artikl p3Artikl;
    @JoinColumn(name = "IdNar", referencedColumnName = "IdNar", insertable = false, updatable = false)
    @ManyToOne(optional = false, fetch = FetchType.EAGER)
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
