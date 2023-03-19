/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package entiteti.p1;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 *
 * @author Aleksa
 */
@Entity
@Table(name = "p1_korisnik")
@NamedQueries({
    @NamedQuery(name = "P1Korisnik.findAll", query = "SELECT p FROM P1Korisnik p"),
    @NamedQuery(name = "P1Korisnik.findByIdKor", query = "SELECT p FROM P1Korisnik p WHERE p.idKor = :idKor"),
    @NamedQuery(name = "P1Korisnik.findByKorisnickoIme", query = "SELECT p FROM P1Korisnik p WHERE p.korisnickoIme = :korisnickoIme"),
    @NamedQuery(name = "P1Korisnik.findBySifra", query = "SELECT p FROM P1Korisnik p WHERE p.sifra = :sifra"),
    @NamedQuery(name = "P1Korisnik.findByIme", query = "SELECT p FROM P1Korisnik p WHERE p.ime = :ime"),
    @NamedQuery(name = "P1Korisnik.findByPrezime", query = "SELECT p FROM P1Korisnik p WHERE p.prezime = :prezime"),
    @NamedQuery(name = "P1Korisnik.findByAdresa", query = "SELECT p FROM P1Korisnik p WHERE p.adresa = :adresa"),
    @NamedQuery(name = "P1Korisnik.findByNovac", query = "SELECT p FROM P1Korisnik p WHERE p.novac = :novac")})
public class P1Korisnik implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "IdKor")
    private Integer idKor;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 45)
    @Column(name = "KorisnickoIme")
    private String korisnickoIme;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 45)
    @Column(name = "Sifra")
    private String sifra;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 45)
    @Column(name = "Ime")
    private String ime;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 45)
    @Column(name = "Prezime")
    private String prezime;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 100)
    @Column(name = "Adresa")
    private String adresa;
    @Basic(optional = false)
    @NotNull
    @Column(name = "Novac")
    private float novac;
    @JoinColumn(name = "IdGra", referencedColumnName = "IdGra")
    @ManyToOne(optional = false, fetch = FetchType.EAGER)
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
