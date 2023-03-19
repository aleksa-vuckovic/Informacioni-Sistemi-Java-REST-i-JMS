/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package entiteti.p3;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.validation.constraints.NotNull;

/**
 *
 * @author Aleksa
 */
@Embeddable
public class P3StavkaPK implements Serializable {

    @Basic(optional = false)
    @Column(name = "IdNar")
    private int idNar;
    @Basic(optional = false)
    @NotNull
    @Column(name = "IdArt")
    private int idArt;

    public P3StavkaPK() {
    }

    public P3StavkaPK(int idNar, int idArt) {
        this.idNar = idNar;
        this.idArt = idArt;
    }

    public int getIdNar() {
        return idNar;
    }

    public void setIdNar(int idNar) {
        this.idNar = idNar;
    }

    public int getIdArt() {
        return idArt;
    }

    public void setIdArt(int idArt) {
        this.idArt = idArt;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (int) idNar;
        hash += (int) idArt;
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof P3StavkaPK)) {
            return false;
        }
        P3StavkaPK other = (P3StavkaPK) object;
        if (this.idNar != other.idNar) {
            return false;
        }
        if (this.idArt != other.idArt) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entiteti.p3.P3StavkaPK[ idNar=" + idNar + ", idArt=" + idArt + " ]";
    }
    
}
