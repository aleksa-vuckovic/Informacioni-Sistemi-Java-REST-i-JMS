/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package main;

import entiteti.p1.P1Korisnik;
import entiteti.p1.P1Grad;
import java.io.Serializable;
import java.util.List;
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
import javax.persistence.NoResultException;
import javax.persistence.Persistence;
import javax.persistence.PersistenceException;
/**
 *
 * @author Aleksa
 */
public class Main {

    @Resource(lookup = "jms/__defaultConnectionFactory")
    private static ConnectionFactory cf;
    @Resource(lookup = "p1RedZahteva")
    private static Queue zahtevi;
    @Resource(lookup = "p1RedOdgovora")
    private static Queue odgovori;
    
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
        emf = Persistence.createEntityManagerFactory("P1PU");
        em = emf.createEntityManager();
        
        Message ret;
        while(true) {
            try {
            TextMessage tm = (TextMessage)consumer.receive();
            String[] zahtev = tm.getText().split(delim, -1);
            switch(zahtev[0]) {
                case "NOVI GRAD":
                    ret = dodajGrad(zahtev[1]);
                    break;
                case "NOVI KORISNIK":
                    ret = dodajKorisnika(zahtev[1], zahtev[2], zahtev[3], zahtev[4], zahtev[5], zahtev[6], Float.parseFloat(zahtev[7]));
                    break;
                case "DODAJ NOVAC":
                    ret = dodajNovac(zahtev[1], Float.parseFloat(zahtev[2]));
                    break;
                case "PROMENA ADRESE":
                    ret = promeniAdresu(zahtev[1], zahtev[2], zahtev[3]);
                    break;
                case "GRADOVI":
                    ret = sviGradovi();
                    break;
                case "KORISNICI":
                    ret = sviKorisnici();
                    break;
                case "KORISNIK":
                    ret = proveraKorisnik(zahtev[1], zahtev[2]);
                    break;
                case "ADRESA":
                    ret = adresa(zahtev[1]);
                    break;
                default:
                    ret = context.createTextMessage("NEUSPEH" + delim + "Nepoznat kod operacije - P1");
                    break;
            }
            } catch(NumberFormatException e) { ret = context.createTextMessage("NEUSPEH" + delim + "Los format argumenta!"); }
            catch(IndexOutOfBoundsException e) { ret = context.createTextMessage("NEUSPEH" + delim + "Nedovoljan broj argumenata!"); }
            catch(Exception e) { ret = context.createTextMessage("NEUSPEH" + delim + "Nepoznata greska u podsistemu P1!"); e.printStackTrace(); }
            producer.send(odgovori, ret);
            em.close();
            emf.close();
            emf = Persistence.createEntityManagerFactory("P1PU");
            em = emf.createEntityManager();
        }
        /*em.close();
        emf.close();*/
    }
    
    private static Message dodajGrad(String ime) {
        Message ret;
        try {
            P1Grad grad = new P1Grad();
            grad.setIme(ime);
            em.getTransaction().begin();
            em.persist(grad);
            em.getTransaction().commit();
            ret = context.createTextMessage("USPEH");
        } catch (PersistenceException e) { ret = context.createTextMessage("NEUSPEH" + delim + "Grad sa tim imenom vec postoji!"); }
        finally { if (em.getTransaction().isActive()) em.getTransaction().rollback(); }
        return ret;
    }
    
    private static Message dodajKorisnika(String kime, String sifra, String ime, String prezime, String adresa, String imeGrada, float novac) {
        Message ret;
        try {
            P1Grad grad = grad(imeGrada);
            P1Korisnik korisnik = new P1Korisnik();
            korisnik.setKorisnickoIme(kime);
            korisnik.setSifra(sifra);
            korisnik.setIme(ime);
            korisnik.setPrezime(prezime);
            korisnik.setAdresa(adresa);
            korisnik.setIdGra(grad);
            korisnik.setNovac(novac);
            em.getTransaction().begin();
            em.persist(korisnik);
            em.getTransaction().commit();
            ret = context.createTextMessage("USPEH" + delim + korisnik.getIdKor());
        } catch (NoResultException e) { ret = context.createTextMessage("NEUSPEH" + delim + "Grad sa tim imenom ne postoji!"); }
        catch (PersistenceException e) { ret = context.createTextMessage("NEUSPEH" + delim + "Korisnicko ime vec postoji!"); }
        finally { if (em.getTransaction().isActive()) em.getTransaction().rollback(); }
        return ret;
    }
    private static Message dodajNovac(String kime, float novac) {
        Message ret;
        try {
            P1Korisnik k = korisnik(kime);
            em.getTransaction().begin();
            k.setNovac(k.getNovac() + novac);
            em.getTransaction().commit();
            ret = context.createTextMessage("USPEH");
        } catch (NoResultException e) { ret = context.createTextMessage("NEUSPEH" + delim + "Korisnik ne postoji!"); }
        finally { if (em.getTransaction().isActive()) em.getTransaction().rollback(); }
        return ret;
    }
    private static Message promeniAdresu(String kime, String adresa, String imeGrada) {
        Message ret;
        try {
            P1Korisnik k = korisnik(kime);
            P1Grad g = null;
            if (!imeGrada.equals("")) g = grad(imeGrada);
            em.getTransaction().begin();
            if (!adresa.equals("")) k.setAdresa(adresa);
            if (g != null) k.setIdGra(g);
            em.getTransaction().commit();
            ret = context.createTextMessage("USPEH");
        } catch (NoResultException e) { ret = context.createTextMessage("NEUSPEH" + delim + "Korisnik ili grad nije pronadjen!"); }
        finally { if (em.getTransaction().isActive()) em.getTransaction().rollback(); }
        return ret;
    }
    private static Message sviGradovi() {
        List<P1Grad> gradovi = em.createNamedQuery("P1Grad.findAll", P1Grad.class).getResultList();
        em.clear();
        for (P1Grad t : gradovi) { t.setP1KorisnikList(null); }
        return context.createObjectMessage((Serializable)gradovi);
    }
    private static Message sviKorisnici() {
        List<P1Korisnik> korisnici = em.createNamedQuery("P1Korisnik.findAll", P1Korisnik.class).getResultList();
        return context.createObjectMessage((Serializable)korisnici);
    }
    private static Message proveraKorisnik(String kime, String sifra) {
        Message ret;
        try {
            P1Korisnik k = korisnik(kime);
            if (k.getSifra().equals(sifra)) ret = context.createTextMessage("OK");
            else ret = context.createTextMessage("UNATHORIZED" + delim + "Ne postoji korisnik!");
        } catch(NoResultException e) { ret = context.createTextMessage("UNAUTHORIZED" + delim + "Netacna sifra!"); }
        return ret;
    }
    private static Message adresa(String kime) {
        Message ret;
        try {
            P1Korisnik k = korisnik(kime);
            ret = context.createTextMessage("USPEH" + delim + k.getAdresa() + delim + k.getIdGra().getIme());
        } catch(NoResultException e) { ret = context.createTextMessage("NEUSPEH" + delim + "Ne postoji korisnik."); }
        return ret;
    }
    
    private static P1Korisnik korisnik(String kime) {
        return em.createNamedQuery("P1Korisnik.findByKorisnickoIme", P1Korisnik.class).setParameter("korisnickoIme", kime).getSingleResult();
    }
    private static P1Grad grad(String ime) {
        return em.createNamedQuery("P1Grad.findByIme", P1Grad.class).setParameter("ime", ime).getSingleResult();
    }
}
