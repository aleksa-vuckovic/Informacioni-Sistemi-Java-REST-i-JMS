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
public class P2Korisnik implements Serializable {

    private static final long serialVersionUID = 1L;
    private Integer idKor;
    private float ukupnaCena;
    private String korisnickoIme;
    @JsonbTransient
    private List<P2Artikl> p2ArtiklList;
    @JsonbTransient
    private List<P2Korpa> p2KorpaList;

    public P2Korisnik() {
    }

    public P2Korisnik(Integer idKor) {
        this.idKor = idKor;
    }

    public P2Korisnik(Integer idKor, float ukupnaCena, String korisnickoIme) {
        this.idKor = idKor;
        this.ukupnaCena = ukupnaCena;
        this.korisnickoIme = korisnickoIme;
    }

    public Integer getIdKor() {
        return idKor;
    }

    public void setIdKor(Integer idKor) {
        this.idKor = idKor;
    }

    public float getUkupnaCena() {
        return ukupnaCena;
    }

    public void setUkupnaCena(float ukupnaCena) {
        this.ukupnaCena = ukupnaCena;
    }

    public String getKorisnickoIme() {
        return korisnickoIme;
    }

    public void setKorisnickoIme(String korisnickoIme) {
        this.korisnickoIme = korisnickoIme;
    }

    public List<P2Artikl> getP2ArtiklList() {
        return p2ArtiklList;
    }

    public void setP2ArtiklList(List<P2Artikl> p2ArtiklList) {
        this.p2ArtiklList = p2ArtiklList;
    }

    public List<P2Korpa> getP2KorpaList() {
        return p2KorpaList;
    }

    public void setP2KorpaList(List<P2Korpa> p2KorpaList) {
        this.p2KorpaList = p2KorpaList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idKor != null ? idKor.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof P2Korisnik)) {
            return false;
        }
        P2Korisnik other = (P2Korisnik) object;
        if ((this.idKor == null && other.idKor != null) || (this.idKor != null && !this.idKor.equals(other.idKor))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entiteti.p2.P2Korisnik[ idKor=" + idKor + " ]";
    }
    
}
