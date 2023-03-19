/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package zahtevi;

import java.util.Map;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.FieldMap;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;
import retrofit2.http.Query;

/**
 *
 * @author Aleksa
 */
public interface Zahtevi {
    @POST("gradovi/{ime}")
    Call<ResponseBody> noviGrad(@Header("Authorization") String auth, @Path("ime") String ime);
    @FormUrlEncoded
    @POST("korisnici")
    Call<ResponseBody> noviKorisnik(@Header("Authorization") String auth, @FieldMap Map<String, String> forma);
    @PUT("korisnici/{kime}/novac/{iznos}")
    Call<ResponseBody> dodajNovac(@Header("Authorization") String auth, @Path("kime") String kime, @Path("iznos") String iznos);
    @PUT("korisnici/{kime}/adresa")
    Call<ResponseBody> promeniAdresu(@Header("Authorization") String auth, @Path("kime") String kime, @Query("adresa") String adresa, @Query("grad") String grad);
    @GET("gradovi")
    Call<ResponseBody> sviGradovi(@Header("Authorization") String auth);
    @GET("korisnici")
    Call<ResponseBody> sviKorisnici(@Header("Authorization") String auth);
    
    @POST("kategorije/{naziv}")
    Call<ResponseBody> novaKategorija(@Header("Authorization") String auth, @Path("naziv") String naziv, @Query("nad") String nad);
    @FormUrlEncoded
    @POST("artikli")
    Call<ResponseBody> noviArtikl(@Header("Authorization") String auth, @FieldMap Map<String, String> forma);
    @PUT("artikli/{id}/cena/{cena}")
    Call<ResponseBody> promeniCenu(@Header("Authorization") String auth, @Path("id") String id, @Path("cena") String cena);
    @PUT("artikli/{id}/popust/{popust}")
    Call<ResponseBody> promeniPopust(@Header("Authorization") String auth, @Path("id") String id, @Path("popust") String popust);
    @PUT("artikli/{id}/uzmi/{kime}/{kolicina}")
    Call<ResponseBody> dodajUKorpu(@Header("Authorization") String auth, @Path("id") String id, @Path("kime") String kime, @Path("kolicina") String kolicina);
    @PUT("artikli/{id}/vrati/{kime}/{kolicina}")
    Call<ResponseBody> izbacIzKorpe(@Header("Authorization") String auth, @Path("id") String id, @Path("kime") String kime, @Path("kolicina") String kolicina);
    @GET("kategorije")
    Call<ResponseBody> sveKategorije(@Header("Authorization") String auth);
    @GET("artikli")
    Call<ResponseBody> mojiArtikli(@Header("Authorization") String auth);
    
    @POST("narudzbine/{kime}")
    Call<ResponseBody> plati(@Header("Authorization") String auth, @Path("kime") String kime, @Query("adresa") String adresa, @Query("grad") String grad);
    @GET("narudzbine/moje")
    Call<ResponseBody> mojeNarudzbine(@Header("Authorization") String auth);
    @GET("narudzbine")
    Call<ResponseBody> sveNarudzbine(@Header("Authorization") String auth);
    @GET("narudzbine/transakcije")
    Call<ResponseBody> sveTransakcije(@Header("Authorization") String auth);
}
