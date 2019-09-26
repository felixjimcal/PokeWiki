package android.pokewiki.API;

import android.pokewiki.Models.PokemonAnswer;

import retrofit2.Call;
import retrofit2.http.GET;

public interface APIService {

    @GET("pokemon")
    Call<PokemonAnswer> getPokemonList();
}
