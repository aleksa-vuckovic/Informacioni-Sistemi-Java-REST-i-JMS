/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package pristupnetacke;

import entiteti.p2.*;
import java.util.List;
import javax.annotation.Resource;
import javax.jms.ConnectionFactory;
import javax.jms.JMSConsumer;
import javax.jms.JMSContext;
import javax.jms.JMSException;
import javax.jms.JMSProducer;
import javax.jms.Message;
import javax.jms.ObjectMessage;
import javax.jms.Queue;
import javax.jms.TextMessage;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
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
@Path("kategorije")
public class Kategorije {
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
    @Path("{naziv}")
    public Response dodajKategoriju(@PathParam("naziv") String naziv, @QueryParam("nad") String nad) throws JMSException {
        init();
        String zahtev = "NOVA KATEGORIJA" + delim + naziv + delim + (nad == null? "" : nad);
        TextMessage tm = context.createTextMessage(zahtev);
        producer.send(p2Zahtevi, tm);
        TextMessage res = (TextMessage)p2Consumer.receive();
        return ishod(res);
    }
    
    @GET
    public Response sveKategorije() throws JMSException {
        init();
        String zahtev = "KATEGORIJE";
        TextMessage tm = context.createTextMessage(zahtev);
        producer.send(p2Zahtevi, tm);
        Message res = p2Consumer.receive();
        if (res instanceof TextMessage) return ishod((TextMessage)res);
        ObjectMessage om = (ObjectMessage)res;
        List<P2Kategorija> kategorije = (List<P2Kategorija>) om.getObject();
        return Response.status(Response.Status.OK).entity(new GenericEntity<List<P2Kategorija>>(kategorije) {}).build();
    }
    
    
}