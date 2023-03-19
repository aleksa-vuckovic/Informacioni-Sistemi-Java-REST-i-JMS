/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package entiteti.p3;

import java.io.Serializable;
import java.util.List;
import javax.json.bind.annotation.JsonbTransient;

/**
 *
 * @author Aleksa
 */
public class P3Artikl implements Serializable {

    private static final long serialVersionUID = 1L;
    private Integer idArt;
    private String naziv;
    @JsonbTransient
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
