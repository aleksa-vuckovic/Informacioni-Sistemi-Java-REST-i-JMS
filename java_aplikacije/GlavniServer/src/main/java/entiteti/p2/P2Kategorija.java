/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package entiteti.p2;

import java.io.Serializable;
import java.util.List;
import javax.json.bind.annotation.JsonbTransient;

/**
 *
 * @author Aleksa
 */

public class P2Kategorija implements Serializable {

    private static final long serialVersionUID = 1L;
    private Integer idKat;
    private String naziv;
    @JsonbTransient
    private List<P2Artikl> p2ArtiklList;
    @JsonbTransient
    private List<P2Kategorija> p2KategorijaList;
    private P2Kategorija idNatKat;

    public P2Kategorija() {
    }

    public P2Kategorija(Integer idKat) {
        this.idKat = idKat;
    }

    public P2Kategorija(Integer idKat, String naziv) {
        this.idKat = idKat;
        this.naziv = naziv;
    }

    public Integer getIdKat() {
        return idKat;
    }

    public void setIdKat(Integer idKat) {
        this.idKat = idKat;
    }

    public String getNaziv() {
        return naziv;
    }

    public void setNaziv(String naziv) {
        this.naziv = naziv;
    }

    public List<P2Artikl> getP2ArtiklList() {
        return p2ArtiklList;
    }

    public void setP2ArtiklList(List<P2Artikl> p2ArtiklList) {
        this.p2ArtiklList = p2ArtiklList;
    }

    public List<P2Kategorija> getP2KategorijaList() {
        return p2KategorijaList;
    }

    public void setP2KategorijaList(List<P2Kategorija> p2KategorijaList) {
        this.p2KategorijaList = p2KategorijaList;
    }

    public P2Kategorija getIdNatKat() {
        return idNatKat;
    }

    public void setIdNatKat(P2Kategorija idNatKat) {
        this.idNatKat = idNatKat;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idKat != null ? idKat.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof P2Kategorija)) {
            return false;
        }
        P2Kategorija other = (P2Kategorija) object;
        if ((this.idKat == null && other.idKat != null) || (this.idKat != null && !this.idKat.equals(other.idKat))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entiteti.p2.P2Kategorija[ idKat=" + idKat + " ]";
    }
    
}
