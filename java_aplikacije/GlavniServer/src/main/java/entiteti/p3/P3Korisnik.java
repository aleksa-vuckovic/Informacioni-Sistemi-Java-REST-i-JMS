/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package entiteti.p3;

import java.io.Serializable;
import java.util.List;
import javax.json.bind.annotation.JsonbTransient;
/**
 *
 * @author Aleksa
 */
public class P3Korisnik implements Serializable {

    private static final long serialVersionUID = 1L;
    private Integer idKor;
    private String korisnickoIme;
    @JsonbTransient
    private List<P3Narudzbina> p3NarudzbinaList;

    public P3Korisnik() {
    }

    public P3Korisnik(Integer idKor) {
        this.idKor = idKor;
    }

    public P3Korisnik(Integer idKor, String korisnickoIme) {
        this.idKor = idKor;
        this.korisnickoIme = korisnickoIme;
    }

    public Integer getIdKor() {
        return idKor;
    }

    public void setIdKor(Integer idKor) {
        this.idKor = idKor;
    }

    public String getKorisnickoIme() {
        return korisnickoIme;
    }

    public void setKorisnickoIme(String korisnickoIme) {
        this.korisnickoIme = korisnickoIme;
    }

    public List<P3Narudzbina> getP3NarudzbinaList() {
        return p3NarudzbinaList;
    }

    public void setP3NarudzbinaList(List<P3Narudzbina> p3NarudzbinaList) {
        this.p3NarudzbinaList = p3NarudzbinaList;
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
        if (!(object instanceof P3Korisnik)) {
            return false;
        }
        P3Korisnik other = (P3Korisnik) object;
        if ((this.idKor == null && other.idKor != null) || (this.idKor != null && !this.idKor.equals(other.idKor))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entiteti.p3.P3Korisnik[ idKor=" + idKor + " ]";
    }
    
}
