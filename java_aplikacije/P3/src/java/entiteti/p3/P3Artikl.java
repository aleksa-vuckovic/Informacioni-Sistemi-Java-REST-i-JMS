/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package entiteti.p3;

import java.io.Serializable;
import java.util.List;
import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
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
@Table(name = "p3_artikl")
@NamedQueries({
    @NamedQuery(name = "P3Artikl.findAll", query = "SELECT p FROM P3Artikl p"),
    @NamedQuery(name = "P3Artikl.findByIdArt", query = "SELECT p FROM P3Artikl p WHERE p.idArt = :idArt"),
    @NamedQuery(name = "P3Artikl.findByNaziv", query = "SELECT p FROM P3Artikl p WHERE p.naziv = :naziv")})
public class P3Artikl implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "IdArt")
    private Integer idArt;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 45)
    @Column(name = "Naziv")
    private String naziv;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "p3Artikl")
    private List<P3Stavka> p3StavkaList;

    public P3Artikl() {
    }

    public P3Artikl(Integer idArt) {
        this.idArt = idArt;
    }

    public P3Artikl(Integer idArt, String naziv) {
        this.idArt = idArt;
        this.naziv = naziv;
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

    public List<P3Stavka> getP3StavkaList() {
        return p3StavkaList;
    }

    public void setP3StavkaList(List<P3Stavka> p3StavkaList) {
        this.p3StavkaList = p3StavkaList;
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
        if (!(object instanceof P3Artikl)) {
            return false;
        }
        P3Artikl other = (P3Artikl) object;
        if ((this.idArt == null && other.idArt != null) || (this.idArt != null && !this.idArt.equals(other.idArt))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entiteti.p3.P3Artikl[ idArt=" + idArt + " ]";
    }
    
}
