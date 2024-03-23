package android.bignerdranch.practice4.fragments;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.bignerdranch.practice4.R;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;

public class AddAlbumFragment extends Fragment {

    private FirebaseFirestore db = FirebaseFirestore.getInstance();
    private CollectionReference mCollectionReference = db.collection("Album");

    private EditText album,artist,year;
    private Button btn;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_add_album, container, false);

        artist=view.findViewById(R.id.artistEditText);
        album=view.findViewById(R.id.albumEditText);
        year=view.findViewById(R.id.yearEditText);
        btn= view.findViewById(R.id.button);

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Map<String,Object> albumFirebase = new HashMap<>();

                String user_artist=artist.getText().toString();
                String user_album=album.getText().toString();
                String user_year = year.getText().toString();

                albumFirebase.put("Artist", user_artist);
                albumFirebase.put("Album", user_album);
                albumFirebase.put("Year", user_year);

                mCollectionReference.add(albumFirebase).addOnSuccessListener(new OnSuccessListener<DocumentReference>() {
                    @Override
                    public void onSuccess(DocumentReference documentReference) {
                        Toast.makeText(getContext(),user_album+" was added successfully",Toast.LENGTH_SHORT).show();
                    }
                }).addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Toast.makeText(getContext(),"Something went wrong.",Toast.LENGTH_SHORT).show();
                    }
                });

            }
        });

        // Inflate the layout for this fragment
        return view;
    }
}