/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package entiteti.p3;

import java.io.Serializable;
import java.util.Date;
import javax.json.bind.annotation.JsonbTransient;

/**
 *
 * @author Aleksa
 */
public class P3Transakcija implements Serializable {

    private static final long serialVersionUID = 1L;
    private Integer idTra;
    private float iznos;
    private Date vreme;
    @JsonbTransient
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
