/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package main;

import javax.annotation.Resource;
import javax.jms.ConnectionFactory;
import javax.jms.JMSConsumer;
import javax.jms.JMSContext;
import javax.jms.JMSException;
import javax.jms.JMSProducer;
import javax.jms.Message;
import javax.jms.Queue;
import javax.jms.TextMessage;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import entiteti.p3.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Locale;
import javax.persistence.PersistenceException;
import javax.validation.ConstraintViolationException;

/**
 *
 * @author Aleksa
 */
public class Main {

    @Resource(lookup = "jms/__defaultConnectionFactory")
    public static ConnectionFactory cf;
    @Resource(lookup = "p3RedZahteva")
    public static Queue zahtevi;
    @Resource(lookup = "p3RedOdgovora")
    public static Queue odgovori;
    
    private static EntityManagerFactory emf;
    private static EntityManager em;
    private static JMSContext context;
    private static JMSProducer producer;
    private static JMSConsumer consumer;
    
    private static final String delim = "#";
    
    public static void main(String[] args) throws JMSException {
        context = cf.createContext();
        producer = context.createProducer();
        consumer = context.createConsumer(zahtevi);
        emf = Persistence.createEntityManagerFactory("P3PU");
        em = emf.createEntityManager();
        Message ret;
        while(true) {
            try {
            TextMessage tm = (TextMessage)consumer.receive();
            String[] zahtev = tm.getText().split(delim, -1);
            switch(zahtev[0]) {
                case "NOVA NARUDZBINA":
                    ret = dodajNarudzbinu(zahtev[1], zahtev[2], Integer.parseInt(zahtev[3]), zahtev[4]);
                    break;
                case "NOVA STAVKA":
                    ret = dodajStavku(Integer.parseInt(zahtev[1]), Integer.parseInt(zahtev[2]), zahtev[3], Integer.parseInt(zahtev[4]), Float.parseFloat(zahtev[5]));
                    break;
                case "NOVA TRANSAKCIJA":
                    ret = dodajTransakciju(Float.parseFloat(zahtev[1]), Integer.parseInt(zahtev[2]));
                    break;
                case "NARUDZBINE":
                    ret = sveNarudzbine(zahtev[1]);
                    break;
                case "SVE NARUDZBINE":
                    ret = sveNarudzbine();
                    break;
                case "TRANSAKCIJE":
                    ret = sveTransakcije();
                    break;
                default:
                    ret = context.createTextMessage("NEUSPEH" + delim + "Nepoznat kod operacije - P3");
                    break;
            }
            } catch(NumberFormatException e) { ret = context.createTextMessage("NEUSPEH" + delim + "Los format argumenta!"); }
            catch(IndexOutOfBoundsException e) { ret = context.createTextMessage("NEUSPEH" + delim + "Nedovoljan broj argumenata!"); }
            catch(Exception e) { ret = context.createTextMessage("NEUSPEH" + delim + "Nepoznata greska u podsistemu P3!"); }
            producer.send(odgovori, ret);
            em.close();
            emf.close();
            emf = Persistence.createEntityManagerFactory("P3PU");
            em = emf.createEntityManager();
        }
        /*em.close();
        emf.close();*/
    }
    
    private static Message dodajNarudzbinu(String adresa, String grad, Integer idKor, String kime) {
        Message ret;
        try {
            em.getTransaction().begin();
            P3Narudzbina narudzbina = new P3Narudzbina();
            narudzbina.setAdresa(adresa);
            narudzbina.setCena(0);
            narudzbina.setGrad(grad);
            narudzbina.setVreme(Calendar.getInstance(Locale.GERMANY).getTime());
            P3Korisnik korisnik = em.find(P3Korisnik.class, idKor);
            if (korisnik == null) {
                korisnik = new P3Korisnik(idKor, kime);
                em.persist(korisnik);
            }
            narudzbina.setIdKor(korisnik);
            em.persist(narudzbina);
            em.getTransaction().commit();
            ret = context.createTextMessage("USPEH" + delim + narudzbina.getIdNar());
        } catch(PersistenceException e) { ret = context.createTextMessage("NEUSPEH" + delim + "Greska pri perzistiranju..."); }
        finally { if(em.getTransaction().isActive()) em.getTransaction().rollback(); }
        return ret;
    }
    
    private static Message dodajStavku(Integer idNar, Integer idArt, String nazivArt, int kolicina, float cena) {
        Message ret;
        try {
            em.getTransaction().begin();
            P3Narudzbina narudzbina = em.find(P3Narudzbina.class, idNar);
            narudzbina.setCena(narudzbina.getCena() + kolicina * cena);
            P3Artikl artikl = em.find(P3Artikl.class, idArt);
            if (artikl == null) {
                artikl = new P3Artikl(idArt, nazivArt);
                em.persist(artikl);
            }
            P3Stavka stavka = new P3Stavka(new P3StavkaPK(idNar, idArt), kolicina, cena);
            em.persist(stavka);
            em.getTransaction().commit();
            ret = context.createTextMessage("USPEH");
        } catch(NullPointerException e) { ret = context.createTextMessage("NEUSPEH" + delim + "Pogresan idNar."); }
        catch(PersistenceException e) { ret = context.createTextMessage("NEUSPEH" + delim + "Stavka vec postoji."); }
        finally { if (em.getTransaction().isActive()) em.getTransaction().rollback(); }
        return ret;
    }
    
    private static Message dodajTransakciju(float iznos, Integer idNar) {
        Message res;
        try {
            em.getTransaction().begin();
            P3Narudzbina narudzbina = em.find(P3Narudzbina.class, idNar);
            P3Transakcija transakcija = new P3Transakcija();
            transakcija.setIznos(iznos);
            transakcija.setIdNar(narudzbina);
            transakcija.setVreme(Calendar.getInstance(Locale.GERMANY).getTime());
            em.persist(transakcija);
            em.getTransaction().commit();
            res = context.createTextMessage("USPEH");
        } catch(ConstraintViolationException | PersistenceException e) { res = context.createTextMessage("NEUSPEH" + delim + "Narudzbina ne postoji."); }
        finally { if (em.getTransaction().isActive()) em.getTransaction().rollback(); }
        return res;
    }
    
    private static Message sveNarudzbine(String kime) {
        List<P3Narudzbina> res = new ArrayList<P3Narudzbina>();
        List<P3Korisnik> tmp = em.createNamedQuery("P3Korisnik.findByKorisnickoIme", P3Korisnik.class).setParameter("korisnickoIme", kime).getResultList();
        if (tmp.size() == 1) {
            P3Korisnik k = tmp.get(0);
            res = k.getP3NarudzbinaList();
        }
        pripremiZaSlanje(res);
        Message ret = context.createObjectMessage((Serializable)res);
        return ret;
    }
    
    private static Message sveNarudzbine() {
        List<P3Narudzbina> res = em.createNamedQuery("P3Narudzbina.findAll", P3Narudzbina.class).getResultList();
        pripremiZaSlanje(res);
        return context.createObjectMessage((Serializable)res);
    }
    
    private static void pripremiZaSlanje(List<P3Narudzbina> res) {
        res.size(); //da se ucita
        for (P3Narudzbina t: res) {
            List<P3Stavka> stavke = t.getP3StavkaList();
            stavke.size(); //da se ucita
        }
        em.clear();
        for (P3Narudzbina t: res) {
            t.setP3TransakcijaList(null);
            List<P3Stavka> stavke = t.getP3StavkaList();
            for (P3Stavka s: stavke) {
                s.getP3Artikl().setP3StavkaList(null);
            }
        }
    }
    
    private static Message sveTransakcije() {
        List<P3Transakcija> res = em.createNamedQuery("P3Transakcija.findAll", P3Transakcija.class).getResultList();
        em.clear();
        for (P3Transakcija t : res) t.setIdNar(null);
        return context.createObjectMessage((Serializable)res);
    }
    
}
