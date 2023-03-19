package entiteti.p2;

import entiteti.p2.P2Artikl;
import entiteti.p2.P2Korpa;
import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.7.10.v20211216-rNA", date="2023-01-05T00:29:57")
@StaticMetamodel(P2Korisnik.class)
public class P2Korisnik_ { 

    public static volatile ListAttribute<P2Korisnik, P2Artikl> p2ArtiklList;
    public static volatile SingularAttribute<P2Korisnik, Float> ukupnaCena;
    public static volatile SingularAttribute<P2Korisnik, Integer> idKor;
    public static volatile SingularAttribute<P2Korisnik, String> korisnickoIme;
    public static volatile ListAttribute<P2Korisnik, P2Korpa> p2KorpaList;

}