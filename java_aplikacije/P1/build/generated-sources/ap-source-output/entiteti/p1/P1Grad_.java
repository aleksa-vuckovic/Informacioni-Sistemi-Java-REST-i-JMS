package entiteti.p1;

import entiteti.p1.P1Korisnik;
import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.7.10.v20211216-rNA", date="2023-01-05T00:29:54")
@StaticMetamodel(P1Grad.class)
public class P1Grad_ { 

    public static volatile SingularAttribute<P1Grad, String> ime;
    public static volatile ListAttribute<P1Grad, P1Korisnik> p1KorisnikList;
    public static volatile SingularAttribute<P1Grad, Integer> idGra;

}