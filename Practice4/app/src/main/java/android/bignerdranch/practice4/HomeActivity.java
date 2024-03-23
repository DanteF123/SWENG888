package android.bignerdranch.practice4;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class HomeActivity extends AppCompatActivity {

    EditText email,password;
    Button logIn, signUp;

    private FirebaseAuth firebaseAuth;
    private FirebaseUser currentUser;
    private FirebaseAuth.AuthStateListener authStateListener;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        email = findViewById(R.id.emailEditText);
        password = findViewById(R.id.passwordEditText);
        logIn = findViewById(R.id.LogInButton);
        signUp = findViewById(R.id.SignUpButton);


        //Firebase Auth
        firebaseAuth = FirebaseAuth.getInstance();



        authStateListener = new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                currentUser = firebaseAuth.getCurrentUser();

                //check if user is logged in or not
                if(currentUser!=null){
                    //user logged in

                }else{
                    //user is signed out

                }
            }
        };

        signUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!TextUtils.isEmpty(email.getText().toString())&& !TextUtils.isEmpty(password.getText().toString())){

                    String emailCreate = email.getText().toString().trim();
                    String passCreate = password.getText().toString().trim();

                    CreateUserEmailAccount(emailCreate,passCreate);
                }else{
                    Toast.makeText(HomeActivity.this, "No fields can be empty", Toast.LENGTH_SHORT).show();
                }
            }
        });

        logIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!TextUtils.isEmpty(email.getText().toString())&& !TextUtils.isEmpty(password.getText().toString())){

                    String emailCreate = email.getText().toString().trim();
                    String passCreate = password.getText().toString().trim();

                    loginEmailPassUser(emailCreate,passCreate);
                }else{
                    Toast.makeText(HomeActivity.this, "No fields can be empty", Toast.LENGTH_SHORT).show();
                }
            }
        });


    }



    private void CreateUserEmailAccount(
            String email,
            String pass
    ){
        if(!TextUtils.isEmpty(email) && !TextUtils.isEmpty(pass)){
            firebaseAuth.createUserWithEmailAndPassword(email,pass).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                @Override
                public void onComplete(@NonNull Task<AuthResult> task) {
                    if(task.isSuccessful()){
                        //user is created successfully
                        Toast.makeText(HomeActivity.this, "user is created", Toast.LENGTH_SHORT).show();
                    }
                }
            });

            // Check if email is used by another account
            firebaseAuth.createUserWithEmailAndPassword(email,pass).addOnFailureListener(new OnFailureListener() {
                @Override
                public void onFailure(@NonNull Exception e) {
                    Toast.makeText(HomeActivity.this, e.getMessage(),Toast.LENGTH_SHORT).show();

                }
            });
        }
    }

    //method to log in
    private void loginEmailPassUser(
            String email, String pwd
    ) {
        // Checking for empty texts
        if (!TextUtils.isEmpty(email)
                && !TextUtils.isEmpty(pwd)
        ) {
            firebaseAuth.signInWithEmailAndPassword(
                    email,
                    pwd
            ).addOnSuccessListener(new OnSuccessListener<AuthResult>() {
                @Override
                public void onSuccess(AuthResult authResult) {
                    FirebaseUser user = firebaseAuth.getCurrentUser();
                    Toast.makeText(HomeActivity.this,user.getEmail().toString()+" signed in successfully", Toast.LENGTH_SHORT).show();
                    Intent i = new Intent(HomeActivity.this, MainActivity.class);
                    i.putExtra("user_email", user.getEmail().toString());

                    startActivity(i);

                }


            });

        }


    }
}