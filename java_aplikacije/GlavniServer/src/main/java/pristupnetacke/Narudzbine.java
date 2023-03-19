/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package pristupnetacke;

import javax.annotation.Resource;
import javax.jms.ConnectionFactory;
import javax.jms.JMSConsumer;
import javax.jms.JMSContext;
import javax.jms.JMSProducer;
import javax.jms.Queue;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.core.Response;
import entiteti.p2.*;
import entiteti.p3.*;
import java.util.List;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.ObjectMessage;
import javax.jms.TextMessage;
import javax.ws.rs.GET;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.GenericEntity;
import javax.ws.rs.core.HttpHeaders;
import static pristupnetacke.Common.*;

/**
 *
 * @author Aleksa
 */
@Path("narudzbine")
public class Narudzbine {
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
    @Resource(lookup = "p3RedZahteva")
    private Queue p3Zahtevi;
    @Resource(lookup = "p3RedOdgovora")
    private Queue p3Odgovori;
    
    private JMSContext context;
    private JMSProducer producer;
    private JMSConsumer p1Consumer;
    private JMSConsumer p2Consumer;
    private JMSConsumer p3Consumer;
    
    private void init() {
        if (context == null) {
            context = cf.createContext();
            producer = context.createProducer();
            p1Consumer = context.createConsumer(p1Odgovori);
            p2Consumer = context.createConsumer(p2Odgovori);
            p3Consumer = context.createConsumer(p3Odgovori);
        }
    }
    
    @POST
    @Path("{kime}")
    public Response plati(@PathParam("kime") String kime, @QueryParam("adresa") String adresa, @QueryParam("grad") String grad) throws JMSException {
        init();
        String zahtev; TextMessage tm; Message res; String[] odgovor;
        //Odredjivanje adrese i grada dostave
        if (adresa == null || grad == null) {
            zahtev = "ADRESA" + delim + kime;
            tm = context.createTextMessage(zahtev);
            producer.send(p1Zahtevi, tm);
            tm = (TextMessage)p1Consumer.receive();
            odgovor = tm.getText().split(delim);
            if (odgovor[0].equals("NEUSPEH")) return ishod(tm);
            if (adresa == null) adresa = odgovor[1];
            if (grad == null) grad = odgovor[2];
        }
        //Dohvatanje stavki iz korpe (i brisanje)
        zahtev = "KORPA" + delim + kime;
        tm = context.createTextMessage(zahtev);
        producer.send(p2Zahtevi, tm);
        res = p2Consumer.receive();
        if (res instanceof TextMessage) return ishod((TextMessage)res);
        List<P2Korpa> stavke = (List<P2Korpa>)((ObjectMessage)res).getObject();
        if (stavke.isEmpty()) return Response.status(Response.Status.BAD_REQUEST).entity("Nema stavki u korpi...").build();
        //Dodavanje narudzbine
        Integer idKor = stavke.get(0).getP2KorpaPK().getIdKor();
        zahtev = "NOVA NARUDZBINA" + delim + adresa + delim + grad + delim + idKor + delim + kime;
        tm = context.createTextMessage(zahtev);
        producer.send(p3Zahtevi, tm);
        tm = (TextMessage)p3Consumer.receive();
        odgovor = tm.getText().split(delim);
        if (odgovor[0].equals("NEUSPEH")) return ishod(tm); //Ovo ne bi trebalo da se desi
        Integer idNar = Integer.parseInt(odgovor[1]);
        //Dodavanje stavki
        float ukupno = 0;
        for (int i = 0; i < stavke.size(); i++) {
            P2Korpa stavka = stavke.get(i);
            float cena = stavka.getP2Artikl().getCenaSaPopustom();
            int kolicina = stavka.getKolicina();
            ukupno += cena*kolicina;
            zahtev = "NOVA STAVKA" + delim + idNar + delim + stavka.getP2Artikl().getIdArt() + delim + stavka.getP2Artikl().getNaziv() + delim + kolicina + delim + cena;
            tm = context.createTextMessage(zahtev);
            producer.send(p3Zahtevi, tm);
            tm = (TextMessage)p3Consumer.receive();
            odgovor = tm.getText().split(delim);
            if (odgovor[0].equals("NEUSPEH")) return ishod(tm); //ne bi trebalo da se desi
            //Dodavanje novca korisniku koji prodaje artikl
            String prodavac = stavka.getP2Artikl().getIdKor().getKorisnickoIme();
            zahtev = "DODAJ NOVAC" + delim + prodavac + delim + (cena*kolicina);
            tm = context.createTextMessage(zahtev);
            producer.send(p1Zahtevi, tm);
            tm = (TextMessage)p1Consumer.receive();
            odgovor = tm.getText().split(delim);
            if (odgovor[0].equals("NEUSPEH")) return ishod(tm); //ne bi trebalo da se desi
        }
        //dodavanje transakcije
        zahtev = "NOVA TRANSAKCIJA" + delim + ukupno + delim +idNar;
        tm = context.createTextMessage(zahtev);
        producer.send(p3Zahtevi, tm);
        tm = (TextMessage)p3Consumer.receive();
        odgovor = tm.getText().split(delim);
        if (odgovor[0].equals("NEUSPEH")) return ishod(tm); //ne bi trebalo da se desi
        //umanjenje novca korisniku koji je kupio artikl
        zahtev = "DODAJ NOVAC" + delim + kime + delim + (-ukupno);
        tm = context.createTextMessage(zahtev);
        producer.send(p1Zahtevi, tm);
        tm = (TextMessage)p1Consumer.receive();
        return ishod(tm);
    }
    
    @GET
    @Path("moje")
    public Response mojeNarudzbine(@Context HttpHeaders headers) throws JMSException {
        init();
        String kime = getKime(headers);
        String zahtev = "NARUDZBINE" + delim + kime;
        TextMessage tm = context.createTextMessage(zahtev);
        producer.send(p3Zahtevi, tm);
        Message res = p3Consumer.receive();
        if (res instanceof TextMessage) return ishod((TextMessage)res);
        List<P3Narudzbina> narudzbine = (List<P3Narudzbina>) ((ObjectMessage)res).getObject();
        return Response.status(Response.Status.OK).entity(new GenericEntity<List<P3Narudzbina>>(narudzbine) {}).build();
    }
    
    @GET
    public Response mojeNarudzbine() throws JMSException {
        init();
        String zahtev = "SVE NARUDZBINE";
        TextMessage tm = context.createTextMessage(zahtev);
        producer.send(p3Zahtevi, tm);
        Message res = p3Consumer.receive();
        if (res instanceof TextMessage) return ishod((TextMessage)res);
        List<P3Narudzbina> narudzbine = (List<P3Narudzbina>) ((ObjectMessage)res).getObject();
        return Response.status(Response.Status.OK).entity(new GenericEntity<List<P3Narudzbina>>(narudzbine) {}).build();
    }
    
    @GET
    @Path("transakcije")
    public Response sveTransakcije() throws JMSException {
        init();
        String zahtev = "TRANSAKCIJE";
        TextMessage tm = context.createTextMessage(zahtev);
        producer.send(p3Zahtevi, tm);
        Message res = p3Consumer.receive();
        if (res instanceof TextMessage) return ishod((TextMessage)res);
        List<P3Transakcija> transakcije = (List<P3Transakcija>) ((ObjectMessage)res).getObject();
        return Response.status(Response.Status.OK).entity(new GenericEntity<List<P3Transakcija>>(transakcije) {}).build();
    }
}
