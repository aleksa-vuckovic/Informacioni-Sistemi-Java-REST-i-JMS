/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package entiteti.p2;

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
@Table(name = "p2_recenzija")
@NamedQueries({
    @NamedQuery(name = "P2Recenzija.findAll", query = "SELECT p FROM P2Recenzija p"),
    @NamedQuery(name = "P2Recenzija.findByIdRec", query = "SELECT p FROM P2Recenzija p WHERE p.idRec = :idRec"),
    @NamedQuery(name = "P2Recenzija.findByOpis", query = "SELECT p FROM P2Recenzija p WHERE p.opis = :opis"),
    @NamedQuery(name = "P2Recenzija.findByOcena", query = "SELECT p FROM P2Recenzija p WHERE p.ocena = :ocena")})
public class P2Recenzija implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "IdRec")
    private Integer idRec;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 200)
    @Column(name = "Opis")
    private String opis;
    @Basic(optional = false)
    @NotNull
    @Column(name = "Ocena")
    private int ocena;
    @JoinColumn(name = "IdArt", referencedColumnName = "IdArt")
    @ManyToOne(optional = false, fetch = FetchType.EAGER)
    private P2Artikl idArt;

    public P2Recenzija() {
    }

    public P2Recenzija(Integer idRec) {
        this.idRec = idRec;
    }

    public P2Recenzija(Integer idRec, String opis, int ocena) {
        this.idRec = idRec;
        this.opis = opis;
        this.ocena = ocena;
    }

    public Integer getIdRec() {
        return idRec;
    }

    public void setIdRec(Integer idRec) {
        this.idRec = idRec;
    }

    public String getOpis() {
        return opis;
    }

    public void setOpis(String opis) {
        this.opis = opis;
    }

    public int getOcena() {
        return ocena;
    }

    public void setOcena(int ocena) {
        this.ocena = ocena;
    }

    public P2Artikl getIdArt() {
        return idArt;
    }

    public void setIdArt(P2Artikl idArt) {
        this.idArt = idArt;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idRec != null ? idRec.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof P2Recenzija)) {
            return false;
        }
        P2Recenzija other = (P2Recenzija) object;
        if ((this.idRec == null && other.idRec != null) || (this.idRec != null && !this.idRec.equals(other.idRec))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entiteti.p2.P2Recenzija[ idRec=" + idRec + " ]";
    }
    
}
