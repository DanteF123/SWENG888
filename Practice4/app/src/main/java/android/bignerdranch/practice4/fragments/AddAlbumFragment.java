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
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;
/** Fragment corresponding to "Add Album". Takes user input and creates a record in Firestore. */
public class AddAlbumFragment extends Fragment {
    private FirebaseFirestore db = FirebaseFirestore.getInstance();
    /** Corresponding collection */
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
        /** When the user clicks the button, take their inputs and create record in firestore, if the album does not already exist. */
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Map<String,Object> albumFirebase = new HashMap<>();
                /** Getting user inputs */

                String user_artist=artist.getText().toString();
                String user_album=album.getText().toString();
                String user_year = year.getText().toString();

                albumFirebase.put("Artist", user_artist);
                albumFirebase.put("Album", user_album);
                albumFirebase.put("Year", user_year);

                DocumentReference documentReference = mCollectionReference.document(user_album);

                documentReference.get().addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
                    @Override
                    public void onSuccess(DocumentSnapshot documentSnapshot) {
                        /** If album is already in list, notify the user. Otherwise add the album. */
                        if(documentSnapshot.exists()){
                            Toast.makeText(getContext(),"Album already exists in list",Toast.LENGTH_SHORT).show();
                        }
                        else{
                            documentReference.set(albumFirebase).addOnSuccessListener(new OnSuccessListener<Void>() {
                                @Override
                                public void onSuccess(Void unused) {
                                    Toast.makeText(getContext(),user_album+" was added successfully",Toast.LENGTH_SHORT).show();
                                }
                            }).addOnFailureListener(new OnFailureListener() {
                                @Override
                                public void onFailure(@NonNull Exception e) {
                                    Toast.makeText(getContext(),"Something went wrong.",Toast.LENGTH_SHORT).show();
                                }
                            });

                        }
                    }
                });

            }
        });

        // Inflate the layout for this fragment
        return view;
    }
}