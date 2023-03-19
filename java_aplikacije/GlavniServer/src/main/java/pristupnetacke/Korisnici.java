/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package pristupnetacke;

import entiteti.p1.*;
import java.util.List;
import javax.annotation.Resource;
import javax.jms.ConnectionFactory;
import javax.jms.JMSConsumer;
import javax.jms.JMSContext;
import javax.jms.JMSException;
import javax.jms.JMSProducer;
import javax.jms.ObjectMessage;
import javax.jms.Queue;
import javax.jms.TextMessage;
import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.GenericEntity;
import javax.ws.rs.core.Response;
import static pristupnetacke.Common.*;

/**
 *
 * @author Aleksa
 */
@Path("korisnici")
public class Korisnici {
    @Resource(lookup = "jms/__defaultConnectionFactory")
    private ConnectionFactory cf;
    @Resource(lookup = "p1RedZahteva")
    private Queue p1Zahtevi;
    @Resource(lookup = "p1RedOdgovora")
    private Queue p1Odgovori;
    @Resource(lookup = "p2RedZahteva")
    private Queue p2Zahtevi;
    @Resource(lookup = "p2RedOdgovora")
    private Queue p2Odgovori;
    
    private JMSContext context;
    private JMSProducer producer;
    private JMSConsumer p1Consumer;
    private JMSConsumer p2Consumer;
    
    private void init() {
        if (context == null) {
            context = cf.createContext();
            producer = context.createProducer();
            p1Consumer = context.createConsumer(p1Odgovori);
            p2Consumer = context.createConsumer(p2Odgovori);
        }
    }
    
    @POST
    public Response dodajKorisnika(@FormParam("Korisnicko ime") String kime, @FormParam("Sifra") String sifra, @FormParam("Ime") String ime,
            @FormParam("Prezime") String prezime, @FormParam("Adresa") String adresa, @FormParam("Grad") String imeGrada, @FormParam("Novac") String novac) throws JMSException {
        init();
        String zahtev = "NOVI KORISNIK" + delim + kime + delim + sifra + delim + ime + delim + prezime + delim + adresa + delim + imeGrada + delim + novac;
        TextMessage tm = context.createTextMessage(zahtev);
        producer.send(p1Zahtevi, tm);
        TextMessage res = (TextMessage)p1Consumer.receive();
        String[] odgovor = res.getText().split(delim);
        if (odgovor[0].equals("NEUSPEH")) return ishod(res);
        
        zahtev = "NOVI KORISNIK" + delim + odgovor[1] + delim + kime;
        tm = context.createTextMessage(zahtev);
        producer.send(p2Zahtevi, tm);
        res = (TextMessage)p2Consumer.receive();//ne bi trebalo da ovde dodje do bilo kakve greske
        return ishod(res);
    }
    
    @PUT
    @Path("{kime}/novac/{iznos}")
    public Response dodajNovac(@PathParam("kime") String kime, @PathParam("iznos") String iznos) throws JMSException {
        init();
        String zahtev = "DODAJ NOVAC" + delim + kime + delim + iznos;
        TextMessage tm = context.createTextMessage(zahtev);
        producer.send(p1Zahtevi, tm);
        TextMessage res = (TextMessage)p1Consumer.receive();
        return ishod(res);
    }
    
    @PUT
    @Path("{kime}/adresa")
    public Response promeniAdresu(@PathParam("kime") String kime, @QueryParam("adresa") String adresa, @QueryParam("grad") String grad) throws JMSException {
        init();
        if (adresa == null) adresa = "";
        if (grad == null) grad = "";
        String zahtev = "PROMENA ADRESE" + delim + kime + delim + adresa + delim + grad;
        TextMessage tm = context.createTextMessage(zahtev);
        producer.send(p1Zahtevi, tm);
        TextMessage res = (TextMessage)p1Consumer.receive();
        return ishod(res);
    }
    
    @GET
    public Response sviKorisnici() throws JMSException {
        init();
        String zahtev = "KORISNICI";
        TextMessage tm = context.createTextMessage(zahtev);
        producer.send(p1Zahtevi, tm);
        ObjectMessage res = (ObjectMessage)p1Consumer.receive();
        List<P1Korisnik> korisnici = (List<P1Korisnik>)res.getObject();
        return Response.status(Response.Status.OK).entity(new GenericEntity<List<P1Korisnik>>(korisnici) {}).build();
    }
}
