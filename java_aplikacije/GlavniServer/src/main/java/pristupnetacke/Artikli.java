/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package pristupnetacke;

import entiteti.p2.*;
import java.util.List;
import java.util.StringTokenizer;
import javax.annotation.Resource;
import javax.jms.ConnectionFactory;
import javax.jms.JMSConsumer;
import javax.jms.JMSContext;
import javax.jms.JMSException;
import javax.jms.JMSProducer;
import javax.jms.Message;
import javax.jms.Queue;
import javax.jms.TextMessage;
import javax.jms.ObjectMessage;
import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.GenericEntity;
import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.Response;
import static pristupnetacke.Common.*;

/**
 *
 * @author Aleksa
 */
@Path("artikli")
public class Artikli {
    @Resource(lookup = "jms/__defaultConnectionFactory")
    private ConnectionFactory cf;
    @Resource(lookup = "p2RedZahteva")
    private Queue p2Zahtevi;
    @Resource(lookup = "p2RedOdgovora")
    private Queue p2Odgovori;
    
    private JMSContext context;
    private JMSProducer producer;
    private JMSConsumer p2Consumer;
    
    private void init() {
        if (context == null) {
            context = cf.createContext();
            producer = context.createProducer();
            p2Consumer = context.createConsumer(p2Odgovori);
        }
    }
    
    @POST
    public Response dodajArtikl(@FormParam("Naziv") String naziv, @FormParam("Opis") String opis,
    @FormParam("Cena") float cena, @FormParam("Popust") int popust, @FormParam("Kategorija") String nazivKat,
            @FormParam("Korisnicko ime") String kime) throws JMSException {
        init();
        if (popust > 100) popust = 100; if (popust < 0) popust = 0;
        String zahtev = "NOVI ARTIKL" + delim + naziv + delim + opis + delim + cena + delim + popust + delim + nazivKat + delim + kime;
        TextMessage tm = context.createTextMessage(zahtev);
        producer.send(p2Zahtevi, tm);
        TextMessage res = (TextMessage) p2Consumer.receive();
        String[] odgovor = res.getText().split(delim);
        if (odgovor[0].equals("USPEH")) return Response.status(Response.Status.OK).entity("Dodat artikl sa id = " + odgovor[1]).build();
        else return ishod(res);
    }
    
    @PUT
    @Path("{id}/cena/{cena}")
    public Response promeniCenu(@PathParam("id") String id, @PathParam("cena") String cena) throws JMSException {
        init();
        String zahtev = "PROMENA CENE" + delim + id + delim + cena;
        TextMessage tm = context.createTextMessage(zahtev);
        producer.send(p2Zahtevi, tm);
        TextMessage res = (TextMessage)p2Consumer.receive();
        return ishod(res);
    }
    
    @PUT
    @Path("{id}/popust/{popust}")
    public Response promeniPopust(@PathParam("id") String id, @PathParam("popust") String popust) throws JMSException  {
        init();
        String zahtev = "PROMENA POPUSTA" + delim + id + delim + popust;
        TextMessage tm = context.createTextMessage(zahtev);
        producer.send(p2Zahtevi, tm);
        TextMessage res = (TextMessage)p2Consumer.receive();
        return ishod(res);
    }
    
    @PUT
    @Path("{id}/uzmi/{kime}/{kolicina}")
    public Response dodajUKorpu(@PathParam("id") String id, @PathParam("kime") String kime, @PathParam("kolicina") String kolicina) throws JMSException {
        init();
        String zahtev = "DODAJ U KORPU" + delim + kime + delim + id + delim + kolicina;
        TextMessage tm = context.createTextMessage(zahtev);
        producer.send(p2Zahtevi, tm);
        TextMessage res = (TextMessage)p2Consumer.receive();
        return ishod(res);
    }
    
    @PUT
    @Path("{id}/vrati/{kime}/{kolicina}")
    public Response izbaciIzKorpe(@PathParam("id") String id, @PathParam("kime") String kime, @PathParam("kolicina") String kolicina) throws JMSException {
        init();
        String zahtev = "IZBACI IZ KORPE" + delim + kime + delim + id + delim + kolicina;
        TextMessage tm = context.createTextMessage(zahtev);
        producer.send(p2Zahtevi, tm);
        TextMessage res = (TextMessage)p2Consumer.receive();
        return ishod(res);
    }
    
    @GET
    public Response sviArtikli(@Context HttpHeaders headers) throws JMSException {
        init();
        String kime = getKime(headers);
        String zahtev = "ARTIKLI" + delim + kime;
        TextMessage tm = context.createTextMessage(zahtev);
        producer.send(p2Zahtevi, tm);
        Message res = p2Consumer.receive();
        if (res instanceof TextMessage) return ishod((TextMessage)res);
        List<P2Artikl> artikli = (List<P2Artikl>) ((ObjectMessage)res).getObject();
        return Response.status(Response.Status.OK).entity(new GenericEntity<List<P2Artikl>> (artikli) {}).build();
    }
}
