package entiteti.p2;

import entiteti.p2.P2Artikl;
import entiteti.p2.P2Kategorija;
import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.7.10.v20211216-rNA", date="2023-01-05T00:29:57")
@StaticMetamodel(P2Kategorija.class)
public class P2Kategorija_ { 

    public static volatile ListAttribute<P2Kategorija, P2Artikl> p2ArtiklList;
    public static volatile ListAttribute<P2Kategorija, P2Kategorija> p2KategorijaList;
    public static volatile SingularAttribute<P2Kategorija, Integer> idKat;
    public static volatile SingularAttribute<P2Kategorija, String> naziv;
    public static volatile SingularAttribute<P2Kategorija, P2Kategorija> idNatKat;

}