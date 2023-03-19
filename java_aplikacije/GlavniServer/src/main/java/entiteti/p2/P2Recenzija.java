/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package entiteti.p2;

import java.io.Serializable;

/**
 *
 * @author Aleksa
 */
public class P2Recenzija implements Serializable {

    private static final long serialVersionUID = 1L;
    private Integer idRec;
    private String opis;
    private int ocena;
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
