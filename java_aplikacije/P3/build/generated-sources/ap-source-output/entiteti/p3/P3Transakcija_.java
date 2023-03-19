package entiteti.p3;

import entiteti.p3.P3Narudzbina;
import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.7.10.v20211216-rNA", date="2023-01-05T01:33:58")
@StaticMetamodel(P3Transakcija.class)
public class P3Transakcija_ { 

    public static volatile SingularAttribute<P3Transakcija, Float> iznos;
    public static volatile SingularAttribute<P3Transakcija, P3Narudzbina> idNar;
    public static volatile SingularAttribute<P3Transakcija, Date> vreme;
    public static volatile SingularAttribute<P3Transakcija, Integer> idTra;

}