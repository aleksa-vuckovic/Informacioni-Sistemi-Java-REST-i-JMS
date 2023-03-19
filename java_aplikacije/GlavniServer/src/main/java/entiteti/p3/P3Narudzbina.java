/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package entiteti.p3;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
import javax.json.bind.annotation.JsonbTransient;

/**
 *
 * @author Aleksa
 */
public class P3Narudzbina implements Serializable {

    private static final long serialVersionUID = 1L;
    private Integer idNar;
    private float cena;
    private Date vreme;
    private String adresa;
    private String grad;
    private List<P3Stavka> p3StavkaList;
    private P3Korisnik idKor;
    @JsonbTransient
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
