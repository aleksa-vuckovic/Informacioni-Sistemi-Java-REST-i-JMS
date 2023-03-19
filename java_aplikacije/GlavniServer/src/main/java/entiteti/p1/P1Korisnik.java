/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package entiteti.p1;

import java.io.Serializable;

/**
 *
 * @author Aleksa
 */
public class P1Korisnik implements Serializable {

    private static final long serialVersionUID = 1L;
    private Integer idKor;
    private String korisnickoIme;
    private String sifra;
    private String ime;
    private String prezime;
    private String adresa;
    private float novac;
    private P1Grad idGra;

    public P1Korisnik() {
    }

    public P1Korisnik(Integer idKor) {
        this.idKor = idKor;
    }

    public P1Korisnik(Integer idKor, String korisnickoIme, String sifra, String ime, String prezime, String adresa, float novac) {
        this.idKor = idKor;
        this.korisnickoIme = korisnickoIme;
        this.sifra = sifra;
        this.ime = ime;
        this.prezime = prezime;
        this.adresa = adresa;
        this.novac = novac;
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

    public String getSifra() {
        return sifra;
    }

    public void setSifra(String sifra) {
        this.sifra = sifra;
    }

    public String getIme() {
        return ime;
    }

    public void setIme(String ime) {
        this.ime = ime;
    }

    public String getPrezime() {
        return prezime;
    }

    public void setPrezime(String prezime) {
        this.prezime = prezime;
    }

    public String getAdresa() {
        return adresa;
    }

    public void setAdresa(String adresa) {
        this.adresa = adresa;
    }

    public float getNovac() {
        return novac;
    }

    public void setNovac(float novac) {
        this.novac = novac;
    }

    public P1Grad getIdGra() {
        return idGra;
    }

    public void setIdGra(P1Grad idGra) {
        this.idGra = idGra;
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
        if (!(object instanceof P1Korisnik)) {
            return false;
        }
        P1Korisnik other = (P1Korisnik) object;
        if ((this.idKor == null && other.idKor != null) || (this.idKor != null && !this.idKor.equals(other.idKor))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entiteti.p1.P1Korisnik[ idKor=" + idKor + " ]";
    }
    
}
