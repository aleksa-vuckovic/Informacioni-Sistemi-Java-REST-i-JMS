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
@Table(name = "p2_kategorija")
@NamedQueries({
    @NamedQuery(name = "P2Kategorija.findAll", query = "SELECT p FROM P2Kategorija p"),
    @NamedQuery(name = "P2Kategorija.findByIdKat", query = "SELECT p FROM P2Kategorija p WHERE p.idKat = :idKat"),
    @NamedQuery(name = "P2Kategorija.findByNaziv", query = "SELECT p FROM P2Kategorija p WHERE p.naziv = :naziv")})
public class P2Kategorija implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "IdKat")
    private Integer idKat;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 45)
    @Column(name = "Naziv")
    private String naziv;
    @OneToMany(mappedBy = "idKat")
    private List<P2Artikl> p2ArtiklList;
    @OneToMany(mappedBy = "idNatKat")
    private List<P2Kategorija> p2KategorijaList;
    @JoinColumn(name = "IdNatKat", referencedColumnName = "IdKat")
    @ManyToOne(fetch = FetchType.EAGER)
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
