/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package pristupnetacke;

import javax.annotation.Resource;
import javax.jms.ConnectionFactory;
import javax.jms.JMSConsumer;
import javax.jms.JMSContext;
import javax.jms.JMSException;
import javax.jms.JMSProducer;
import javax.jms.ObjectMessage;
import javax.jms.Queue;
import javax.jms.TextMessage;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.core.Response;
import static pristupnetacke.Common.*;
import entiteti.p1.*;
import java.util.List;
import javax.ws.rs.core.GenericEntity;
/**
 *
 * @author Aleksa
 */
@Path("gradovi")
public class Gradovi {
    @Resource(lookup = "jms/__defaultConnectionFactory")
    private ConnectionFactory cf;
    @Resource(lookup = "p1RedZahteva")
    private Queue p1Zahtevi;
    @Resource(lookup = "p1RedOdgovora")
    private Queue p1Odgovori;
    
    private JMSContext context;
    private JMSProducer producer;
    private JMSConsumer p1Consumer;
    
    private void init() {
        if (context == null) {
            context = cf.createContext();
            producer = context.createProducer();
            p1Consumer = context.createConsumer(p1Odgovori);
        }
    }
    
    @POST
    @Path("{ime}")
    public Response dodajGrad(@PathParam("ime") String ime) throws JMSException {
        init();
        String zahtev = "NOVI GRAD" + delim + ime;
        TextMessage tm = context.createTextMessage(zahtev);
        producer.send(p1Zahtevi, tm);
        TextMessage res = (TextMessage)p1Consumer.receive();
        return ishod(res);
        //return Response.status(Response.Status.OK).entity(cf == null ? "NULL" : "NIJE NULL").build();
    }
    
    @GET
    public Response sviGradovi() throws JMSException {
        init();
        String zahtev = "GRADOVI";
        TextMessage tm = context.createTextMessage(zahtev);
        producer.send(p1Zahtevi, tm);
        ObjectMessage res = (ObjectMessage)p1Consumer.receive();
        List<P1Grad> gradovi = (List<P1Grad>)res.getObject();
        return Response.status(Response.Status.OK).entity(new GenericEntity<List<P1Grad>>(gradovi) {}).build();
    }
    
    
}
