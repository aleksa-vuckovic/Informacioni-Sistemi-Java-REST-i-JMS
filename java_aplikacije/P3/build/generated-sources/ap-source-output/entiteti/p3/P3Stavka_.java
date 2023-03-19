package entiteti.p3;

import entiteti.p3.P3Artikl;
import entiteti.p3.P3Narudzbina;
import entiteti.p3.P3StavkaPK;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.7.10.v20211216-rNA", date="2023-01-04T22:36:32")
@StaticMetamodel(P3Stavka.class)
public class P3Stavka_ { 

    public static volatile SingularAttribute<P3Stavka, P3Artikl> p3Artikl;
    public static volatile SingularAttribute<P3Stavka, P3StavkaPK> p3StavkaPK;
    public static volatile SingularAttribute<P3Stavka, P3Narudzbina> p3Narudzbina;
    public static volatile SingularAttribute<P3Stavka, Integer> kolicina;
    public static volatile SingularAttribute<P3Stavka, Float> cena;

}