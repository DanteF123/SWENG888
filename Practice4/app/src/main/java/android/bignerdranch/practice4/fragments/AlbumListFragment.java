package android.bignerdranch.practice4.fragments;

import android.bignerdranch.practice4.Adapter.AlbumAdapter;
import android.bignerdranch.practice4.Class.Album;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.bignerdranch.practice4.R;
import java.util.ArrayList;
import java.util.List;


public class AlbumListFragment extends Fragment {

    private AlbumAdapter mAlbumAdapter;
    private RecyclerView mRecyclerView;

    List<Album> mAlbumList;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_album_list, container, false);
        mRecyclerView = view.findViewById(R.id.recyclerView);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));

        mAlbumList = new ArrayList<>();
        mAlbumList.add(new Album("Taylor Swift", "1970","2010"));

        mAlbumAdapter = new AlbumAdapter(mAlbumList);
        mRecyclerView.setAdapter(mAlbumAdapter);


        return view;
    }
}