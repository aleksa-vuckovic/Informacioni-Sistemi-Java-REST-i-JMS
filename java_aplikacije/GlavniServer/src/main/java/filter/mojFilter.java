/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package filter;

import java.io.IOException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.Resource;
import javax.jms.ConnectionFactory;
import javax.jms.JMSConsumer;
import javax.jms.JMSContext;
import javax.jms.JMSException;
import javax.jms.JMSProducer;
import javax.jms.Queue;
import javax.jms.TextMessage;
import javax.ws.rs.container.ContainerRequestContext;
import javax.ws.rs.container.ContainerRequestFilter;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.Provider;
import static pristupnetacke.Common.*;

/**
 *
 * @author Aleksa
 */
@Provider
public class mojFilter implements ContainerRequestFilter {
    @Resource(lookup = "jms/__defaultConnectionFactory")
    private ConnectionFactory cf;
    @Resource(lookup = "p1RedZahteva")
    private Queue zahtevi;
    @Resource(lookup = "p1RedOdgovora")
    private Queue odgovori;
    
    private JMSContext context;
    private JMSProducer producer;
    private JMSConsumer consumer;
    
    private void init() {
        if (context == null) {
            context = cf.createContext();
            producer = context.createProducer();
            consumer = context.createConsumer(odgovori);
        }
    }

    @Override
    public void filter(ContainerRequestContext crc) throws IOException {
        init();
        //return;
        List<String> list = crc.getHeaders().get("Authorization");
        if (list == null || list.size() != 1) {
            crc.abortWith(Response.status(Response.Status.UNAUTHORIZED).entity("Morate uneti kredencijale!").build());
            return;
        }
        String data = list.get(0).replace("Basic ", "");
        data = decode(data);
        String kime = data.split(":")[0];
        String sifra = data.split(":")[1];
        String zahtev = "KORISNIK" + delim + kime + delim + sifra;
        TextMessage tm = context.createTextMessage(zahtev);
        producer.send(zahtevi, tm);
        TextMessage res = (TextMessage)consumer.receive();
        String[] odgovor;
        try {
            odgovor = res.getText().split(delim);
        } catch (JMSException ex) {
            crc.abortWith(Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity("Serverska greska pri autentifikaciji!").build());
            return;
        }
        if (odgovor[0].equals("OK")) return;
        if (odgovor[0].equals("NEUSPEH")) {
            crc.abortWith(Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity("Serverska greska pri autentifikaciji!").build());
            return;
        }
        crc.abortWith(Response.status(Response.Status.UNAUTHORIZED).entity("Neispravni kredencijali!").build());
    }
    
}
