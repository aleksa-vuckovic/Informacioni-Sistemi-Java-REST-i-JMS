package entiteti.p2;

import entiteti.p2.P2Kategorija;
import entiteti.p2.P2Korisnik;
import entiteti.p2.P2Korpa;
import entiteti.p2.P2Recenzija;
import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.7.10.v20211216-rNA", date="2023-01-05T00:29:57")
@StaticMetamodel(P2Artikl.class)
public class P2Artikl_ { 

    public static volatile ListAttribute<P2Artikl, P2Recenzija> p2RecenzijaList;
    public static volatile SingularAttribute<P2Artikl, P2Kategorija> idKat;
    public static volatile SingularAttribute<P2Artikl, P2Korisnik> idKor;
    public static volatile SingularAttribute<P2Artikl, String> naziv;
    public static volatile SingularAttribute<P2Artikl, Integer> popust;
    public static volatile SingularAttribute<P2Artikl, Float> cena;
    public static volatile SingularAttribute<P2Artikl, Integer> idArt;
    public static volatile ListAttribute<P2Artikl, P2Korpa> p2KorpaList;
    public static volatile SingularAttribute<P2Artikl, String> opis;

}