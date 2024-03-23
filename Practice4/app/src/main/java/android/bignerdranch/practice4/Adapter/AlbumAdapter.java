package android.bignerdranch.practice4.Adapter;

import android.bignerdranch.practice4.Class.Album;
import android.bignerdranch.practice4.R;
import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class AlbumAdapter extends RecyclerView.Adapter<AlbumAdapter.ViewHolder> {

    // Main list of product items to be displayed by main activity
    private List<Album> albums;

    boolean isSelectMode = false;
    // Array list of selected items to be sent to Results activity.
    private ArrayList<Album> selectedItems = new ArrayList<>();

    public AlbumAdapter(List<Album> albums) {this.albums=albums;}
    @NonNull
    @Override
    public AlbumAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.album_item,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull AlbumAdapter.ViewHolder holder, int position) {
        Album album = albums.get(position);

        holder.album.setText(album.getAlbum());
        holder.artist.setText(album.getArtist());
        holder.year.setText(album.getYear());

    }

    @Override
    public int getItemCount() {
        return albums.size();

    }

    public ArrayList<Album> getSelectedItems(){
        return selectedItems;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public TextView artist,album,year;

        public ViewHolder(View itemView) {
            super(itemView);
            artist = itemView.findViewById(R.id.artistText);
            album = itemView.findViewById(R.id.albumText);
            year = itemView.findViewById(R.id.yearText);
            itemView.setOnLongClickListener(new View.OnLongClickListener(){

                @Override
                public boolean onLongClick(View v) {
                    // on long click, the selected item will change its background to green and be added to the selected items array
                    isSelectMode = true;
                    if(selectedItems.contains(albums.get(getAdapterPosition()))){
                        itemView.setBackgroundColor(Color.TRANSPARENT);
                        selectedItems.remove(albums.get(getAdapterPosition()));
                    }else{
                        itemView.setBackgroundColor(Color.GREEN);
                        selectedItems.add(albums.get(getAdapterPosition()));
                    }
                    if (selectedItems.size()==0)
                        isSelectMode=false;
                    return true;
                }
            });

            //setting an onClickListener to de-select and item and remove selected item from list. If a user clicks again on a selected item, it will de-select it.
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    // if item is in selected mode, it will de select it, otherwise it will select it.
                    if (isSelectMode){
                        if (selectedItems.contains(albums.get(getAdapterPosition()))){
                            itemView.setBackgroundColor(Color.TRANSPARENT);
                            selectedItems.remove(albums.get(getAdapterPosition()));
                        }
                        else{
                            itemView.setBackgroundColor(Color.GREEN);
                            selectedItems.add(albums.get(getAdapterPosition()));
                        }
                        if(selectedItems.size()==0){
                            isSelectMode=false;
                        }
                    }
                    else{

                    }
                }
            });

        }
    }

}
