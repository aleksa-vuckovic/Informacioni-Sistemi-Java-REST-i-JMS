/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package entiteti.p3;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;
import javax.validation.constraints.NotNull;

/**
 *
 * @author Aleksa
 */
@Entity
@Table(name = "p3_transakcija")
@NamedQueries({
    @NamedQuery(name = "P3Transakcija.findAll", query = "SELECT p FROM P3Transakcija p"),
    @NamedQuery(name = "P3Transakcija.findByIdTra", query = "SELECT p FROM P3Transakcija p WHERE p.idTra = :idTra"),
    @NamedQuery(name = "P3Transakcija.findByIznos", query = "SELECT p FROM P3Transakcija p WHERE p.iznos = :iznos"),
    @NamedQuery(name = "P3Transakcija.findByVreme", query = "SELECT p FROM P3Transakcija p WHERE p.vreme = :vreme")})
@Access(AccessType.FIELD)
public class P3Transakcija implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "IdTra")
    private Integer idTra;
    @Basic(optional = false)
    @NotNull
    @Column(name = "Iznos")
    private float iznos;
    @Basic(optional = false)
    @NotNull
    @Column(name = "Vreme")
    @Temporal(TemporalType.TIMESTAMP)
    private Date vreme;
    @JoinColumn(name = "IdNar", referencedColumnName = "IdNar")
    @ManyToOne(optional = false)
    private P3Narudzbina idNar;

    public P3Transakcija() {
    }

    public P3Transakcija(Integer idTra) {
        this.idTra = idTra;
    }

    public P3Transakcija(Integer idTra, float iznos, Date vreme) {
        this.idTra = idTra;
        this.iznos = iznos;
        this.vreme = vreme;
    }

    public Integer getIdTra() {
        return idTra;
    }

    public void setIdTra(Integer idTra) {
        this.idTra = idTra;
    }

    public float getIznos() {
        return iznos;
    }

    public void setIznos(float iznos) {
        this.iznos = iznos;
    }

    public Date getVreme() {
        return vreme;
    }

    public void setVreme(Date vreme) {
        this.vreme = vreme;
    }

    public P3Narudzbina getIdNar() {
        return idNar;
    }

    public void setIdNar(P3Narudzbina idNar) {
        this.idNar = idNar;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idTra != null ? idTra.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof P3Transakcija)) {
            return false;
        }
        P3Transakcija other = (P3Transakcija) object;
        if ((this.idTra == null && other.idTra != null) || (this.idTra != null && !this.idTra.equals(other.idTra))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entiteti.p3.P3Transakcija[ idTra=" + idTra + " ]";
    }
    
}
