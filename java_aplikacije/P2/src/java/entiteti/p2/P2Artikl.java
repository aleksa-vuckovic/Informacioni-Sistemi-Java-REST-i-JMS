/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package entiteti.p2;

import java.io.Serializable;
import java.util.List;
import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
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
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 *
 * @author Aleksa
 */
@Entity
@Table(name = "p2_artikl")
@NamedQueries({
    @NamedQuery(name = "P2Artikl.findAll", query = "SELECT p FROM P2Artikl p"),
    @NamedQuery(name = "P2Artikl.findByIdArt", query = "SELECT p FROM P2Artikl p WHERE p.idArt = :idArt"),
    @NamedQuery(name = "P2Artikl.findByNaziv", query = "SELECT p FROM P2Artikl p WHERE p.naziv = :naziv"),
    @NamedQuery(name = "P2Artikl.findByOpis", query = "SELECT p FROM P2Artikl p WHERE p.opis = :opis"),
    @NamedQuery(name = "P2Artikl.findByCena", query = "SELECT p FROM P2Artikl p WHERE p.cena = :cena"),
    @NamedQuery(name = "P2Artikl.findByPopust", query = "SELECT p FROM P2Artikl p WHERE p.popust = :popust")})
@Access(AccessType.FIELD) //Zelim da odredjena polja budu tranzijentna pri serijalizaciji, ali ne i pri JPA perizistiranju
public class P2Artikl implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "IdArt")
    private Integer idArt;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 45)
    @Column(name = "Naziv")
    private String naziv;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 200)
    @Column(name = "Opis")
    private String opis;
    @Basic(optional = false)
    @NotNull
    @Column(name = "Cena")
    private float cena;
    @Basic(optional = false)
    @NotNull
    @Column(name = "Popust")
    private int popust;
    @JoinColumn(name = "IdKat", referencedColumnName = "IdKat")
    @ManyToOne(optional = false, fetch = FetchType.EAGER)
    private P2Kategorija idKat;
    @JoinColumn(name = "IdKor", referencedColumnName = "IdKor")
    @ManyToOne(optional = false)
    private P2Korisnik idKor;
    @OneToMany(mappedBy = "p2Artikl")
    private List<P2Korpa> p2KorpaList;
    @OneToMany(mappedBy = "idArt")
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
