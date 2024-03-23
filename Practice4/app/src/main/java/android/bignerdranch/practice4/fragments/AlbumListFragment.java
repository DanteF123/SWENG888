package android.bignerdranch.practice4.fragments;

import android.bignerdranch.practice4.Adapter.AlbumAdapter;
import android.bignerdranch.practice4.Class.Album;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.bignerdranch.practice4.R;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;
import com.google.firebase.storage.StorageReference;

import java.util.ArrayList;
import java.util.List;


public class AlbumListFragment extends Fragment {

    private FirebaseAuth mFirebaseAuth;
    private FirebaseAuth.AuthStateListener mAuthStateListener;
    private FirebaseUser user;


    private FirebaseFirestore db = FirebaseFirestore.getInstance();
    private CollectionReference mCollectionReference = db.collection("Album");


    private StorageReference mStorageReference;

    private List<Album> mAlbumList;

    private RecyclerView mRecyclerView;
    private AlbumAdapter mAlbumAdapter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_album_list, container, false);


        //firebase auth
        mFirebaseAuth = FirebaseAuth.getInstance();
        user = mFirebaseAuth.getCurrentUser();

        //widgets
        mRecyclerView = view.findViewById(R.id.recyclerView);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));


        //posts arraylist
        mAlbumList = new ArrayList<>();
        return view;
    }

    @Override
    public void onStart() {
        super.onStart();
        mCollectionReference.get().addOnSuccessListener(new OnSuccessListener<QuerySnapshot>() {
            @Override
            public void onSuccess(QuerySnapshot queryDocumentSnapshots) {

                for(QueryDocumentSnapshot albums:queryDocumentSnapshots){
                    Album album = albums.toObject(Album.class);
                    mAlbumList.add(album);
                }

                //reycler view
                mAlbumAdapter= new AlbumAdapter(mAlbumList);
                mRecyclerView.setAdapter(mAlbumAdapter);

                mAlbumAdapter.notifyDataSetChanged();

            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Toast.makeText(getContext(),"something went wrong", Toast.LENGTH_LONG).show();
            }
        });
    }

    //    private AlbumAdapter mAlbumAdapter;
//    private RecyclerView mRecyclerView;
//
//    List<Album> mAlbumList;
//
//    @Override
//    public View onCreateView(LayoutInflater inflater, ViewGroup container,
//                             Bundle savedInstanceState) {
//
//        View view = inflater.inflate(R.layout.fragment_album_list, container, false);
//        mRecyclerView = view.findViewById(R.id.recyclerView);
//        mRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
//
//        mAlbumList = new ArrayList<>();
//        mAlbumList.add(new Album("Taylor Swift", "1970","2010"));
//
//        mAlbumAdapter = new AlbumAdapter(mAlbumList);
//        mRecyclerView.setAdapter(mAlbumAdapter);
//
//
//        return view;
//    }
}