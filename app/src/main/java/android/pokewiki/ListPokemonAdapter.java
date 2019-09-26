package android.pokewiki;

import android.pokewiki.Models.Pokemon;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class ListPokemonAdapter extends RecyclerView.Adapter<ListPokemonAdapter.ViewHolder> {

    private ArrayList<Pokemon> dataset;

    public ListPokemonAdapter()
    {
        dataset = new ArrayList<>();
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_pokemon, parent, false);
        return new ViewHolder(view);
    }

    // Replace the contents of a view (invoked by the layout manager)
    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        Pokemon pokemon = dataset.get(position);
        holder.textView.setText(pokemon.getName());
    }

    // Return the size of your dataset (invoked by the layout manager)
    @Override
    public int getItemCount() {
        return 0;
    }

    public void addPokemonsList(ArrayList<Pokemon> pokemons) {
        dataset.addAll(pokemons);
        notifyDataSetChanged();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private ImageView imageView;
        private TextView textView;

        public ViewHolder(View itemView)
        {
            super(itemView);

            imageView = itemView.findViewById(R.id.imageViewPokemon);
            textView = itemView.findViewById(R.id.textViewPokemon);
        }
    }
}