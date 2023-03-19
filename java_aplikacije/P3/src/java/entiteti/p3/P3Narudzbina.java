/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package entiteti.p3;

import java.io.Serializable;
import java.util.Date;
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
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 *
 * @author Aleksa
 */
@Entity
@Table(name = "p3_narudzbina")
@NamedQueries({
    @NamedQuery(name = "P3Narudzbina.findAll", query = "SELECT p FROM P3Narudzbina p"),
    @NamedQuery(name = "P3Narudzbina.findByIdNar", query = "SELECT p FROM P3Narudzbina p WHERE p.idNar = :idNar"),
    @NamedQuery(name = "P3Narudzbina.findByCena", query = "SELECT p FROM P3Narudzbina p WHERE p.cena = :cena"),
    @NamedQuery(name = "P3Narudzbina.findByVreme", query = "SELECT p FROM P3Narudzbina p WHERE p.vreme = :vreme"),
    @NamedQuery(name = "P3Narudzbina.findByAdresa", query = "SELECT p FROM P3Narudzbina p WHERE p.adresa = :adresa"),
    @NamedQuery(name = "P3Narudzbina.findByGrad", query = "SELECT p FROM P3Narudzbina p WHERE p.grad = :grad")})
@Access(AccessType.FIELD)
public class P3Narudzbina implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "IdNar")
    private Integer idNar;
    @Basic(optional = false)
    @NotNull
    @Column(name = "Cena")
    private float cena;
    @Basic(optional = false)
    @NotNull
    @Column(name = "Vreme")
    @Temporal(TemporalType.TIMESTAMP)
    private Date vreme;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 100)
    @Column(name = "Adresa")
    private String adresa;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 45)
    @Column(name = "Grad")
    private String grad;
    @OneToMany(mappedBy = "p3Narudzbina", fetch = FetchType.EAGER)
    private List<P3Stavka> p3StavkaList;
    @JoinColumn(name = "IdKor", referencedColumnName = "IdKor")
    @ManyToOne(optional = false, fetch = FetchType.EAGER)
    private P3Korisnik idKor;
    @OneToMany(mappedBy = "idNar")
    private List<P3Transakcija> p3TransakcijaList;

    public P3Narudzbina() {
    }

    public P3Narudzbina(Integer idNar) {
        this.idNar = idNar;
    }

    public P3Narudzbina(Integer idNar, float cena, Date vreme, String adresa, String grad) {
        this.idNar = idNar;
        this.cena = cena;
        this.vreme = vreme;
        this.adresa = adresa;
        this.grad = grad;
    }

    public Integer getIdNar() {
        return idNar;
    }

    public void setIdNar(Integer idNar) {
        this.idNar = idNar;
    }

    public float getCena() {
        return cena;
    }

    public void setCena(float cena) {
        this.cena = cena;
    }

    public Date getVreme() {
        return vreme;
    }

    public void setVreme(Date vreme) {
        this.vreme = vreme;
    }

    public String getAdresa() {
        return adresa;
    }

    public void setAdresa(String adresa) {
        this.adresa = adresa;
    }

    public String getGrad() {
        return grad;
    }

    public void setGrad(String grad) {
        this.grad = grad;
    }

    public List<P3Stavka> getP3StavkaList() {
        return p3StavkaList;
    }

    public void setP3StavkaList(List<P3Stavka> p3StavkaList) {
        this.p3StavkaList = p3StavkaList;
    }

    public P3Korisnik getIdKor() {
        return idKor;
    }

    public void setIdKor(P3Korisnik idKor) {
        this.idKor = idKor;
    }

    public List<P3Transakcija> getP3TransakcijaList() {
        return p3TransakcijaList;
    }

    public void setP3TransakcijaList(List<P3Transakcija> p3TransakcijaList) {
        this.p3TransakcijaList = p3TransakcijaList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idNar != null ? idNar.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof P3Narudzbina)) {
            return false;
        }
        P3Narudzbina other = (P3Narudzbina) object;
        if ((this.idNar == null && other.idNar != null) || (this.idNar != null && !this.idNar.equals(other.idNar))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entiteti.p3.P3Narudzbina[ idNar=" + idNar + " ]";
    }
    
}
