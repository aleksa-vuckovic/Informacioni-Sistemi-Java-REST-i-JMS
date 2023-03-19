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
public class P2KorpaPK implements Serializable {

    private int idKor;
    private int idArt;

    public P2KorpaPK() {
    }

    public P2KorpaPK(int idKor, int idArt) {
        this.idKor = idKor;
        this.idArt = idArt;
    }

    public int getIdKor() {
        return idKor;
    }

    public void setIdKor(int idKor) {
        this.idKor = idKor;
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
        hash += (int) idKor;
        hash += (int) idArt;
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof P2KorpaPK)) {
            return false;
        }
        P2KorpaPK other = (P2KorpaPK) object;
        if (this.idKor != other.idKor) {
            return false;
        }
        if (this.idArt != other.idArt) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entiteti.p2.P2KorpaPK[ idKor=" + idKor + ", idArt=" + idArt + " ]";
    }
    
}
