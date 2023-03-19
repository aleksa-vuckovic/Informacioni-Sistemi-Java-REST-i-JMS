/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package main;

import java.io.Serializable;
import javax.annotation.Resource;
import javax.jms.ConnectionFactory;
import javax.jms.JMSConsumer;
import javax.jms.JMSContext;
import javax.jms.JMSException;
import javax.jms.JMSProducer;
import javax.jms.Queue;
import javax.jms.TextMessage;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import entiteti.p2.*;
import java.util.List;
import javax.jms.Message;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceException;

/**
 *
 * @author Aleksa
 */
public class Main {

    @Resource(lookup = "jms/__defaultConnectionFactory")
    public static ConnectionFactory cf;
    @Resource(lookup = "p2RedZahteva")
    public static Queue zahtevi;
    @Resource(lookup = "p2RedOdgovora")
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
        emf = Persistence.createEntityManagerFactory("P2PU");
        em = emf.createEntityManager();
        Message ret;
        while(true) {
            try {
            TextMessage tm = (TextMessage)consumer.receive();
            String[] zahtev = tm.getText().split(delim, -1);
            switch(zahtev[0]) {
                case "NOVA KATEGORIJA":
                    ret = dodajKategoriju(zahtev[1], zahtev[2]);
                    break;
                case "NOVI ARTIKL":
                    ret = dodajArtikl(zahtev[1], zahtev[2], Float.parseFloat(zahtev[3]), Integer.parseInt(zahtev[4]), zahtev[5], zahtev[6]);
                    break;
                case "PROMENA CENE":
                    ret = promeniCenu(Integer.parseInt(zahtev[1]), Float.parseFloat(zahtev[2]));
                    break;
                case "PROMENA POPUSTA":
                    ret = promeniPopust(Integer.parseInt(zahtev[1]), Integer.parseInt(zahtev[2]));
                    break;
                case "DODAJ U KORPU":
                    ret = dodajUKorpu(zahtev[1], Integer.parseInt(zahtev[2]), Integer.parseInt(zahtev[3]));
                    break;
                case "IZBACI IZ KORPE":
                    ret = izbaciIzKorpe(zahtev[1], Integer.parseInt(zahtev[2]), Integer.parseInt(zahtev[3]));
                    break;
                case "KATEGORIJE":
                    ret = sveKategorije();
                    break;
                case "ARTIKLI":
                    ret = sviArtikli(zahtev[1]);
                    break;
                case "KORPA":
                    ret = korpa(zahtev[1]);
                    break;
                case "NOVI KORISNIK":
                    ret = dodajKorisnika(Integer.parseInt(zahtev[1]), zahtev[2]);
                    break;
                default:
                    ret = context.createTextMessage("NEUSPEH" + delim + "Nepoznat kod operacije - P2");
                    break;
            }
            } catch(NumberFormatException e) { ret = context.createTextMessage("NEUSPEH" + delim + "Los format argumenta!"); }
            catch(IndexOutOfBoundsException e) { ret = context.createTextMessage("NEUSPEH" + delim + "Nedovoljan broj argumenata!"); }
            catch(Exception e) { ret = context.createTextMessage("NEUSPEH" + delim + "Nepoznata greska u podsistemu P2!"); e.printStackTrace();}
            producer.send(odgovori, ret);
            em.close();
            emf.close();
            emf = Persistence.createEntityManagerFactory("P2PU");
            em = emf.createEntityManager();
        }
        /*em.close();
        emf.close();*/
    }
    
    private static Message dodajKategoriju(String naziv, String nazivNad) {
        Message ret;
        try {
            P2Kategorija nad = null;
            if (!nazivNad.equals("")) nad = kategorija(nazivNad);
            P2Kategorija nova = new P2Kategorija();
            nova.setIdNatKat(nad);
            nova.setNaziv(naziv);
            em.getTransaction().begin();
            em.persist(nova);
            em.getTransaction().commit();
            ret = context.createTextMessage("USPEH");
        } catch(NoResultException e) { ret = context.createTextMessage("NEUSPEH" + delim + "Nije pronadjena natkategorija sa tim nazivom."); }
        catch(PersistenceException e) { ret = context.createTextMessage("NEUSPEH" + delim + "Vec postoji kategorija sa tim nazivom."); }
        finally { if (em.getTransaction().isActive()) em.getTransaction().rollback(); }
        return ret;
    }
    
    private static Message dodajArtikl(String naziv, String opis, float cena, int popust, String katNaziv, String kime) {
        Message ret;
        try {
            P2Korisnik kor = korisnik(kime);
            P2Kategorija kat = kategorija(katNaziv);
            P2Artikl novi = new P2Artikl();
            novi.setCena(cena);
            novi.setIdKat(kat);
            novi.setIdKor(kor);
            novi.setNaziv(naziv);
            novi.setOpis(opis);
            novi.setPopust(popust);
            em.getTransaction().begin();
            em.persist(novi);
            em.getTransaction().commit();
            ret = context.createTextMessage("USPEH" + delim + novi.getIdArt().toString());
        } catch(NoResultException e) { ret = context.createTextMessage("NEUSPEH" + delim + "Nije pronadjena kategorija sa tim nazivom."); }
        finally { if (em.getTransaction().isActive()) em.getTransaction().rollback(); }
        return ret;
    }
    
    private static Message promeniCenu(Integer id, float cena) {
        Message ret;
        try {
            P2Artikl a = em.find(P2Artikl.class, id);
            em.getTransaction().begin();
            float pret = a.getCenaSaPopustom();
            a.setCena(cena);
            azurirajUkupneCene(a, pret);
            em.getTransaction().commit();
            ret = context.createTextMessage("USPEH");
        } catch(NullPointerException e) { ret = context.createTextMessage("NEUSPEH" + delim + "Artikl " + id + " ne postoji.");  e.printStackTrace(); }
        finally { if (em.getTransaction().isActive()) em.getTransaction().rollback(); }
        return ret;
    }
    
    private static Message promeniPopust(Integer id, int popust) {
        Message ret;
        try {
            P2Artikl a = em.find(P2Artikl.class, id);
            em.getTransaction().begin();
            float pret = a.getCenaSaPopustom();
            a.setPopust(popust);
            azurirajUkupneCene(a, pret);
            em.getTransaction().commit();
            ret = context.createTextMessage("USPEH");
        } catch(NullPointerException e) { ret =  context.createTextMessage("NEUSPEH" + delim + "Ne postoji artikl" + id); e.printStackTrace(); }
        finally { if (em.getTransaction().isActive()) em.getTransaction().rollback(); }
        return ret;
    }
    
    private static Message dodajUKorpu(String kime, Integer idArtikla, int kolicina) {
        Message ret;
        try {
            if (kolicina <= 0) throw new IllegalArgumentException();
            P2Korisnik kor = korisnik(kime);
            P2Artikl art = em.find(P2Artikl.class, idArtikla);
            em.getTransaction().begin();
            P2KorpaPK pk = new P2KorpaPK(kor.getIdKor(), idArtikla);
            P2Korpa k = em.find(P2Korpa.class, pk);
            if (k != null) {
                k.setKolicina(k.getKolicina() + kolicina);
            }
            else {
                k = new P2Korpa();
                k.setP2KorpaPK(pk);
                k.setKolicina(kolicina);
                em.persist(k);
            }
            kor.setUkupnaCena(kor.getUkupnaCena() + kolicina * art.getCenaSaPopustom());
            em.getTransaction().commit();
            ret = context.createTextMessage("USPEH");
        } catch(NoResultException e) { ret = context.createTextMessage("NEUSPEH" + delim + "Korisnik ne postoji"); }
        catch(PersistenceException e) { ret = context.createTextMessage("NEUSPEH" + delim + "Ne postoji artikl."); }
        catch(IllegalArgumentException e) { ret = context.createTextMessage("NEUSPEH" + delim + "Nelegalni argument!"); }
        catch(NullPointerException e) { ret = context.createTextMessage("NEUSPEH" + delim + "Artikle ne postoji!"); }
        finally { if (em.getTransaction().isActive()) em.getTransaction().rollback(); }
        return ret;
    }
    private static Message izbaciIzKorpe(String kime, Integer idArtikla, int kolicina) {
        Message ret;
        try {
            if (kolicina <= 0) throw new IllegalArgumentException();
            P2Korisnik kor = korisnik(kime);
            P2Artikl art = em.find(P2Artikl.class, idArtikla);
            P2Korpa korpa = em.find(P2Korpa.class, new P2KorpaPK(kor.getIdKor(), idArtikla));
            em.getTransaction().begin();
            if (korpa != null) {
                kolicina = kolicina > korpa.getKolicina() ? korpa.getKolicina() : kolicina;
                korpa.setKolicina(korpa.getKolicina() - kolicina);
                if (korpa.getKolicina() == 0) em.remove(korpa);
                kor.setUkupnaCena(kor.getUkupnaCena() - kolicina * art.getCenaSaPopustom());
            }
            em.getTransaction().commit();
            ret = context.createTextMessage("USPEH");
        } catch(NoResultException e) { ret = context.createTextMessage("NEUSPEH" + delim + "Nema korisnika."); }
        catch(IllegalArgumentException e) { ret = context.createTextMessage("NEUSPEH" + delim + "Nelegalni argument!"); }
        catch(NullPointerException e) { ret = context.createTextMessage("NEUSPEH" + delim + "Artikl ne postoji!"); }
        finally { if (em.getTransaction().isActive()) em.getTransaction().rollback(); }
        return ret;
    }
    private static Message sveKategorije() {
        List<P2Kategorija> kategorije = em.createNamedQuery("P2Kategorija.findAll", P2Kategorija.class).getResultList();
        em.clear();
        for (P2Kategorija t: kategorije) { t.setP2ArtiklList(null); t.setP2KategorijaList(null); }
        return context.createObjectMessage((Serializable)kategorije);
    }
    private static Message sviArtikli(String kime) {
        Message ret;
        try {
            P2Korisnik kor = korisnik(kime);
            List<P2Artikl> artikli = kor.getP2ArtiklList();
            artikli.size();
            em.clear();
            for (P2Artikl t: artikli) { t.setIdKor(null); t.setP2KorpaList(null); t.setP2RecenzijaList(null); }
            ret = context.createObjectMessage((Serializable)artikli);
        } catch(NoResultException e) { ret = context.createTextMessage("NEUSPEH" + delim + "Korisnik ne postoji"); }
        return ret;
    }
    //Uklanja sve iz korpe korisnika kime i vraca sadrzaj korpe
    private static Message korpa(String kime) {
        Message ret;
        try {
            em.getTransaction().begin();
            P2Korisnik k = korisnik(kime);
            k.setUkupnaCena(0);
            List<P2Korpa> korpa = k.getP2KorpaList();
            for (P2Korpa t: korpa) em.remove(t);
            em.getTransaction().commit();
            em.clear();
            for (P2Korpa t: korpa) {
                P2Artikl ta = t.getP2Artikl(); ta.setP2KorpaList(null); ta.setP2RecenzijaList(null);
                P2Korisnik tk = ta.getIdKor(); tk.setP2ArtiklList(null); tk.setP2KorpaList(null);
                tk = t.getP2Korisnik(); tk.setP2ArtiklList(null); tk.setP2KorpaList(null);
            }
            ret = context.createObjectMessage((Serializable)korpa);
        } catch(NoResultException e) { ret = context.createTextMessage("NEUSPEH" + delim + "Nije pronadjen korisnik!"); }
        finally { if (em.getTransaction().isActive()) em.getTransaction().rollback(); }
        return ret;
    }
    private static Message dodajKorisnika(Integer id, String kime) {
        Message ret;
        try {
            em.getTransaction().begin();
            P2Korisnik novi = new P2Korisnik(id, 0, kime);
            em.persist(novi);
            em.getTransaction().commit();
            ret = context.createTextMessage("USPEH");
        } catch(PersistenceException e) { ret = context.createTextMessage("NEUSPEH" + delim + "Vec postoji korisnik sa tim id ili korisnickim imenom."); }
        finally { if (em.getTransaction().isActive()) em.getTransaction().rollback(); }
        return ret;
    }
    
    private static void azurirajUkupneCene(P2Artikl a, float staraCenaSaPopustom) {
        a.getP2KorpaList().size(); //da bih bio siguran da se ucitala?
        for (P2Korpa korpa : a.getP2KorpaList()) {
            P2Korisnik korisnik = korpa.getP2Korisnik();
            float cena = korisnik.getUkupnaCena();
            cena -= staraCenaSaPopustom * korpa.getKolicina();
            cena += a.getCenaSaPopustom() * korpa.getKolicina();
            korisnik.setUkupnaCena(cena);
        }
    }
    
    private static P2Korisnik korisnik(String kime) {
        return em.createNamedQuery("P2Korisnik.findByKorisnickoIme", P2Korisnik.class).setParameter("korisnickoIme", kime).getSingleResult();
    }
    private static P2Kategorija kategorija(String naziv) {
        return em.createNamedQuery("P2Kategorija.findByNaziv", P2Kategorija.class).setParameter("naziv", naziv).getSingleResult();
    }
}
