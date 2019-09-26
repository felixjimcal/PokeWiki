package android.pokewiki;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.pokewiki.API.APIService;
import android.pokewiki.Models.Pokemon;
import android.pokewiki.Models.PokemonAnswer;
import android.util.Log;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {
    private static final String TAG = "Consulta";
    private static Retrofit retrofit = null;

    private ListPokemonAdapter listaPokemonAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        RecyclerView recyclerView = findViewById(R.id.recycler_view);
        listaPokemonAdapter = new ListPokemonAdapter();
        recyclerView.setAdapter(listaPokemonAdapter);

        int COLUMNS = 3;
        GridLayoutManager layoutManager = new GridLayoutManager(this, COLUMNS);
        recyclerView.setLayoutManager(layoutManager);


        Retrofit.Builder builder = new Retrofit.Builder();
        builder.baseUrl(getString(R.string.API_url_base));
        builder.addConverterFactory(GsonConverterFactory.create());
        retrofit = builder.build();

        GetData();
    }

    private void GetData() {
        APIService service = retrofit.create(APIService.class);
        Call<PokemonAnswer> pokemonAnswerCall = service.getPokemonList();

        pokemonAnswerCall.enqueue(new Callback<PokemonAnswer>() {
            @Override
            public void onResponse(Call<PokemonAnswer> call, Response<PokemonAnswer> response) {
                if(response.isSuccessful()){
                    PokemonAnswer pokemonAnswer = response.body();

                    ArrayList<Pokemon> pokemons = pokemonAnswer.getResults();
                    listaPokemonAdapter.addPokemonsList(pokemons);

                    /*
                    for(Pokemon item : pokemons)
                    {
                        Log.e(TAG, "Pokemon: " + item.getName());
                    }
                    */
                }
                else
                {
                    Log.e(TAG, "onResponse: " + response.errorBody());
                }
            }

            @Override
            public void onFailure(Call<PokemonAnswer> call, Throwable t) {
                Log.e(TAG, "onResponse: " + t.getMessage());
            }
        });
    }
}
