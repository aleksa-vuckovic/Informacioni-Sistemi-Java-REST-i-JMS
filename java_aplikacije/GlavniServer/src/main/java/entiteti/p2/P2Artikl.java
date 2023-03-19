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

public class P2Artikl implements Serializable {

    private static final long serialVersionUID = 1L;
    private Integer idArt;
    private String naziv;
    private String opis;
    private float cena;
    private int popust;
    private P2Kategorija idKat;
    @JsonbTransient
    private P2Korisnik idKor;
    @JsonbTransient
    private List<P2Korpa> p2KorpaList;
    @JsonbTransient
    private List<P2Recenzija> p2RecenzijaList;

    public P2Artikl() {
    }

    public P2Artikl(Integer idArt) {
        this.idArt = idArt;
    }

    public P2Artikl(Integer idArt, String naziv, String opis, float cena, int popust) {
        this.idArt = idArt;
        this.naziv = naziv;
        this.opis = opis;
        this.cena = cena;
        this.popust = popust;
    }

    public Integer getIdArt() {
        return idArt;
    }

    public void setIdArt(Integer idArt) {
        this.idArt = idArt;
    }

    public String getNaziv() {
        return naziv;
    }

    public void setNaziv(String naziv) {
        this.naziv = naziv;
    }

    public String getOpis() {
        return opis;
    }

    public void setOpis(String opis) {
        this.opis = opis;
    }

    public float getCena() {
        return cena;
    }

    public void setCena(float cena) {
        this.cena = cena;
    }

    public int getPopust() {
        return popust;
    }

    public void setPopust(int popust) {
        this.popust = popust;
    }

    public P2Kategorija getIdKat() {
        return idKat;
    }

    public void setIdKat(P2Kategorija idKat) {
        this.idKat = idKat;
    }

    public P2Korisnik getIdKor() {
        return idKor;
    }

    public void setIdKor(P2Korisnik idKor) {
        this.idKor = idKor;
    }

    public List<P2Korpa> getP2KorpaList() {
        return p2KorpaList;
    }

    public void setP2KorpaList(List<P2Korpa> p2KorpaList) {
        this.p2KorpaList = p2KorpaList;
    }

    public List<P2Recenzija> getP2RecenzijaList() {
        return p2RecenzijaList;
    }

    public void setP2RecenzijaList(List<P2Recenzija> p2RecenzijaList) {
        this.p2RecenzijaList = p2RecenzijaList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idArt != null ? idArt.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof P2Artikl)) {
            return false;
        }
        P2Artikl other = (P2Artikl) object;
        if ((this.idArt == null && other.idArt != null) || (this.idArt != null && !this.idArt.equals(other.idArt))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entiteti.p2.P2Artikl[ idArt=" + idArt + " ]";
    }
    
    public float getCenaSaPopustom() {
        return cena * (float)(100 - popust)/100;
    }
    
}
