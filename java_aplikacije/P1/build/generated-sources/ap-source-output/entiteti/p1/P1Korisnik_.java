package entiteti.p1;

import entiteti.p1.P1Grad;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.7.10.v20211216-rNA", date="2023-01-03T21:30:35")
@StaticMetamodel(P1Korisnik.class)
public class P1Korisnik_ { 

    public static volatile SingularAttribute<P1Korisnik, String> ime;
    public static volatile SingularAttribute<P1Korisnik, String> prezime;
    public static volatile SingularAttribute<P1Korisnik, Integer> idKor;
    public static volatile SingularAttribute<P1Korisnik, Float> novac;
    public static volatile SingularAttribute<P1Korisnik, String> adresa;
    public static volatile SingularAttribute<P1Korisnik, String> sifra;
    public static volatile SingularAttribute<P1Korisnik, String> korisnickoIme;
    public static volatile SingularAttribute<P1Korisnik, P1Grad> idGra;

}