/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package entiteti.p1;

import java.io.Serializable;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 *
 * @author Aleksa
 */
@Entity
@Table(name = "p1_grad")
@NamedQueries({
    @NamedQuery(name = "P1Grad.findAll", query = "SELECT p FROM P1Grad p"),
    @NamedQuery(name = "P1Grad.findByIdGra", query = "SELECT p FROM P1Grad p WHERE p.idGra = :idGra"),
    @NamedQuery(name = "P1Grad.findByIme", query = "SELECT p FROM P1Grad p WHERE p.ime = :ime")})
public class P1Grad implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "IdGra")
    private Integer idGra;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 45)
    @Column(name = "Ime")
    private String ime;
    @OneToMany(mappedBy = "idGra")
    private List<P1Korisnik> p1KorisnikList;

    public P1Grad() {
    }

    public P1Grad(Integer idGra) {
        this.idGra = idGra;
    }

    public P1Grad(Integer idGra, String ime) {
        this.idGra = idGra;
        this.ime = ime;
    }

    public Integer getIdGra() {
        return idGra;
    }

    public void setIdGra(Integer idGra) {
        this.idGra = idGra;
    }

    public String getIme() {
        return ime;
    }

    public void setIme(String ime) {
        this.ime = ime;
    }

    public List<P1Korisnik> getP1KorisnikList() {
        return p1KorisnikList;
    }

    public void setP1KorisnikList(List<P1Korisnik> p1KorisnikList) {
        this.p1KorisnikList = p1KorisnikList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idGra != null ? idGra.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof P1Grad)) {
            return false;
        }
        P1Grad other = (P1Grad) object;
        if ((this.idGra == null && other.idGra != null) || (this.idGra != null && !this.idGra.equals(other.idGra))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entiteti.p1.P1Grad[ idGra=" + idGra + " ]";
    }
    
}
