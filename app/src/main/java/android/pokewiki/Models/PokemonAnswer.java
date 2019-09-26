package android.pokewiki.Models;

import java.util.ArrayList;

public class PokemonAnswer {

    public ArrayList<Pokemon> getResults() {
        return results;
    }

    public void setResults(ArrayList<Pokemon> results) {
        this.results = results;
    }

    private ArrayList<Pokemon> results;
}
