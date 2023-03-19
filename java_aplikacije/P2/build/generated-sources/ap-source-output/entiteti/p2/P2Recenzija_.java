package entiteti.p2;

import entiteti.p2.P2Artikl;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.7.10.v20211216-rNA", date="2023-01-04T02:08:15")
@StaticMetamodel(P2Recenzija.class)
public class P2Recenzija_ { 

    public static volatile SingularAttribute<P2Recenzija, Integer> idRec;
    public static volatile SingularAttribute<P2Recenzija, Integer> ocena;
    public static volatile SingularAttribute<P2Recenzija, P2Artikl> idArt;
    public static volatile SingularAttribute<P2Recenzija, String> opis;

}