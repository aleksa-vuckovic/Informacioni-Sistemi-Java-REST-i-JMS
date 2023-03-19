/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package aplikacija;
import grafika.*;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import zahtevi.*;
import java.util.Map;
import java.util.concurrent.TimeUnit;
import okhttp3.OkHttpClient;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Retrofit;
import helper.*;
import java.io.IOException;
import retrofit2.Callback;
import retrofit2.Response;

/**
 *
 * @author Aleksa
 */
public class Aplikacija {
    private Grafika grafika;
    private String kime, sifra, opcija;
    private final String[] opcije = new String[] {
            "Novi grad", "Novi korisnik", "Dodaj novac korisniku", "Promeni adresu",
            "Svi gradovi", "Svi korisnici", "Nova kategorija", "Novi artikl",
            "Promeni cenu artikla", "Postavi popust za artikl",
            "Dodaj u korpu", "Izbaci iz korpe", "Sve kategorije", "Moji artikli",
            "Plati", "Moje narudzbine", "Sve narudzbine", "Sve transakcije"
        };
    private Zahtevi zahtevi;
    private Call<ResponseBody> zahtev;
    private Stanje stanje;
    private ActionListener daljeListener = x -> { dalje(); };
    private ActionListener nazadListener = x -> { nazad(); };
    private ActionListener kreniListener = x -> { kreni(); };
    private class MojWindowListener extends WindowAdapter {
        public void windowClosing(WindowEvent e) { kraj(); }
    }
    private class MojCallBack implements Callback<ResponseBody> {
        public void onResponse(Call<ResponseBody> call, Response<ResponseBody> rspns) {
            odgovor(rspns.isSuccessful() ? rspns.body() : rspns.errorBody());
        }
        public void onFailure(Call<ResponseBody> call, Throwable thrwbl) {
            odgovor(null);
        }
    
    }
    public Aplikacija() {
        OkHttpClient client = new OkHttpClient.Builder()
                .readTimeout(60, TimeUnit.SECONDS)
                .connectTimeout(10, TimeUnit.SECONDS)
                .build();
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://localhost:8080/GlavniServer/api/")
                .client(client)
                .build();
        zahtevi = retrofit.create(Zahtevi.class);
        zahtev = null;
        stanje = new StanjeHome();
        grafika = new Grafika(opcije, daljeListener, nazadListener, kreniListener, new MojWindowListener());
        
    }
    private abstract class Stanje {
        public abstract void dalje();
        public abstract void nazad();
        public abstract void kreni();
        public abstract void kraj();
        public abstract void odgovor(ResponseBody rspns);
    }
    public synchronized void dalje() {
        stanje.dalje();
    }
    public synchronized void nazad() {
        stanje.nazad();
    }
    public synchronized void kreni() {
        stanje.kreni();
    }
    public synchronized void kraj() {
        stanje.kraj();
    }
    public synchronized void odgovor(ResponseBody rspns) {
        stanje.odgovor(rspns);
    }
    private class StanjeHome extends Stanje {
        public void dalje() {
            kime = grafika.getKime();
            sifra = grafika.getSifra();
            opcija = grafika.getOpcija();
            String[] nazivi = naziviZaOpciju(opcija);
            grafika.prikaziZahtev(nazivi);
            stanje = new StanjeZahtev();
        }
        public void nazad() {
            return;
        }
        public void kreni() {
            return;
        }
        public void kraj() {
            grafika.dispose();
        }
        public void odgovor(ResponseBody res) {
            return;
        }
    }
    private class StanjeZahtev extends Stanje {
        public void dalje() {
            return;
        }
        public void nazad() {
            if (zahtev != null) zahtev.cancel();
            zahtev = null;
            grafika.prikaziHome();
            stanje = new StanjeHome();
        }
        public void kreni() {
            if (zahtev != null) zahtev.cancel();
            Map<String, String> unos = grafika.unos();
            zahtev = napraviZahtev(unos);
            zahtev.enqueue(Aplikacija.this.new MojCallBack());
            grafika.postaviIspis("Ceka se odgovor... BUDITE STRPLJIVI.");
        }
        public void kraj() {
            if (zahtev != null) zahtev.cancel();
            grafika.dispose();
        }
        public void odgovor(ResponseBody res) {
            try {
                grafika.postaviIspis(Helper.format(res.string()));
            } catch (IOException | NullPointerException ex) {
                grafika.postaviIspis("Doslo je do greske..");
            }
        }
    }
    
    private Call<ResponseBody> napraviZahtev(Map<String, String> unos) {
        String auth = Helper.kodiraj(kime, sifra);
        switch (opcija) {
            case "Novi grad":
                return zahtevi.noviGrad(auth, unos.get("Ime"));
            case "Novi korisnik":
                return zahtevi.noviKorisnik(auth, unos);
            case "Dodaj novac korisniku":
                return zahtevi.dodajNovac(auth, unos.get("Korisnicko ime"), unos.get("Iznos"));
            case "Promeni adresu":
                return zahtevi.promeniAdresu(auth, unos.get("Korisnicko ime"), nullIfEmpty(unos.get("Adresa")), nullIfEmpty(unos.get("Grad")));
            case "Svi gradovi":
                return zahtevi.sviGradovi(auth);
            case "Svi korisnici":
                return zahtevi.sviKorisnici(auth);
            case "Sve kategorije":
                return zahtevi.sveKategorije(auth);
            case "Moji artikli":
                return zahtevi.mojiArtikli(auth);
            case "Moje narudzbine":
                return zahtevi.mojeNarudzbine(auth);
            case "Sve narudzbine":
                return zahtevi.sveNarudzbine(auth);
            case "Sve transakcije":
                return zahtevi.sveTransakcije(auth);
            case "Nova kategorija":
                return zahtevi.novaKategorija(auth, unos.get("Naziv"), nullIfEmpty(unos.get("Natkategorija")));
            case "Novi artikl":
                return zahtevi.noviArtikl(auth, unos);
            case "Promeni cenu artikla":
                return zahtevi.promeniCenu(auth, unos.get("ID artikla"), unos.get("Cena"));
            case "Postavi popust za artikl":
                return zahtevi.promeniPopust(auth, unos.get("ID artikla"), unos.get("Popust"));
            case "Dodaj u korpu":
                return zahtevi.dodajUKorpu(auth, unos.get("ID artikla"), unos.get("Korisnicko ime"), unos.get("Kolicina"));
            case "Izbaci iz korpe":
                return zahtevi.izbacIzKorpe(auth, unos.get("ID artikla"), unos.get("Korisnicko ime"), unos.get("Kolicina"));
            case "Plati":
                return zahtevi.plati(auth, unos.get("Korisnicko ime"), nullIfEmpty(unos.get("Adresa")), nullIfEmpty(unos.get("Grad")));
            default:
                throw new AssertionError();
        }
    }
    
    private String[] naziviZaOpciju(String opcija) {
        switch (opcija) {
            case "Novi grad":
                return new String[]{"Ime"};
            case "Novi korisnik":
                return new String[]{"Korisnicko ime", "Sifra", "Ime", "Prezime", "Adresa", "Grad", "Novac"};
            case "Dodaj novac korisniku":
                return new String[]{"Korisnicko ime", "Iznos"};
            case "Promeni adresu":
                return new String[]{"Korisnicko ime", "Adresa", "Grad"};
            case "Svi gradovi": case "Svi korisnici": case "Sve kategorije": case "Moji artikli": case "Moje narudzbine": case "Sve narudzbine": case "Sve transakcije":
                return new String[]{};
            case "Nova kategorija":
                return new String[]{"Naziv", "Natkategorija"};
            case "Novi artikl":
                return new String[]{"Naziv", "Opis", "Cena", "Popust", "Kategorija", "Korisnicko ime"};
            case "Promeni cenu artikla":
                return new String[]{"ID artikla", "Cena"};
            case "Postavi popust za artikl":
                return new String[]{"ID artikla", "Popust"};
            case "Dodaj u korpu": case "Izbaci iz korpe":
                return new String[]{"ID artikla", "Korisnicko ime", "Kolicina"};
            case "Plati":
                return new String[]{"Korisnicko ime", "Adresa", "Grad"};
            default:
                throw new AssertionError();
        }
    }
    private static String nullIfEmpty(String x) {
        return x.equals("") ? null : x;
    }
    public static void main(String[] args) {
        new Aplikacija();
    }
    
}
