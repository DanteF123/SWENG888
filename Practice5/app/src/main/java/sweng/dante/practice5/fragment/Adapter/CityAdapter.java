package sweng.dante.practice5.fragment.Adapter;

import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import sweng.dante.practice5.R;
import sweng.dante.practice5.fragment.Class.City;

public class CityAdapter extends RecyclerView.Adapter<CityAdapter.ViewHolder> {

    private List<City> cities;
    private int selectedPosition = RecyclerView.NO_POSITION; // Initially no item is selected

    public City selectedCity;

    //constructor
    public CityAdapter(List<City> cities) {
        this.cities = cities;
    }

    /** On create, inflate from the city_list_item xml*/
    @NonNull
    @Override
    public CityAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.city_list_item, parent, false);
        return new ViewHolder(view);
    }

    /** Bind data elements, and if the item is selected highlight it cyan*/
    @Override
    public void onBindViewHolder(@NonNull CityAdapter.ViewHolder holder, int position) {
        City city = cities.get(position);
        holder.population.setText(city.getPopulation());
        holder.name.setText(city.getName());

        // Highlight the selected item
        if (selectedPosition == position) {
            holder.itemView.setBackgroundColor(Color.CYAN);
        } else {
            holder.itemView.setBackgroundColor(Color.TRANSPARENT);
        }
    }

    @Override
    public int getItemCount() {
        return cities.size();
    }

    /** View holder that tracks the users previous and current selected position.*/
    public class ViewHolder extends RecyclerView.ViewHolder {
        public TextView name, population;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            name = itemView.findViewById(R.id.cityText);
            population = itemView.findViewById(R.id.popValue);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    // Update selected position
                    int previousSelectedPosition = selectedPosition;
                    selectedPosition = getAdapterPosition();
                    selectedCity=cities.get(getAdapterPosition());

                    // Notify item changes for proper highlighting
                    if (previousSelectedPosition != RecyclerView.NO_POSITION) {
                        notifyItemChanged(previousSelectedPosition);
                    }
                    notifyItemChanged(selectedPosition);
                }
            });
        }
    }
}
