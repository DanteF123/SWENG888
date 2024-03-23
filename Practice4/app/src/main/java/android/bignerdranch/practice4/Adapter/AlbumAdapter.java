package android.bignerdranch.practice4.Adapter;

import android.bignerdranch.practice4.Class.Album;
import android.bignerdranch.practice4.R;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class AlbumAdapter extends RecyclerView.Adapter<AlbumAdapter.ViewHolder> {

    // Main list of product items to be displayed by main activity
    private List<Album> albums;
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

    public class ViewHolder extends RecyclerView.ViewHolder {
        public TextView artist,album,year;

        public ViewHolder(View itemView) {
            super(itemView);
            artist = itemView.findViewById(R.id.artistText);
            album = itemView.findViewById(R.id.albumText);
            year = itemView.findViewById(R.id.yearText);


        }
    }

}
