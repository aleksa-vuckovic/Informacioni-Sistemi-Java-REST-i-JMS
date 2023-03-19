package entiteti.p3;

import entiteti.p3.P3Korisnik;
import entiteti.p3.P3Stavka;
import entiteti.p3.P3Transakcija;
import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.7.10.v20211216-rNA", date="2023-01-05T01:33:58")
@StaticMetamodel(P3Narudzbina.class)
public class P3Narudzbina_ { 

    public static volatile ListAttribute<P3Narudzbina, P3Stavka> p3StavkaList;
    public static volatile SingularAttribute<P3Narudzbina, Integer> idNar;
    public static volatile SingularAttribute<P3Narudzbina, Date> vreme;
    public static volatile SingularAttribute<P3Narudzbina, P3Korisnik> idKor;
    public static volatile SingularAttribute<P3Narudzbina, String> adresa;
    public static volatile SingularAttribute<P3Narudzbina, Float> cena;
    public static volatile ListAttribute<P3Narudzbina, P3Transakcija> p3TransakcijaList;
    public static volatile SingularAttribute<P3Narudzbina, String> grad;

}