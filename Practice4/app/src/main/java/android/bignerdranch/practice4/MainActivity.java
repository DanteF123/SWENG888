package android.bignerdranch.practice4;

import android.bignerdranch.practice4.fragments.AddAlbumFragment;
import android.bignerdranch.practice4.fragments.AlbumListFragment;
import android.bignerdranch.practice4.fragments.HomeFragment;
import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.navigation.NavigationView;
import com.google.firebase.auth.FirebaseAuth;

/** Activity that handles navigation view and logic. */
public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    private DrawerLayout mDrawerLayout;
    private ActionBarDrawerToggle mActionBarDrawerToggle;
    private NavigationView mNavigationView;
    private FirebaseAuth mAuth;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mDrawerLayout = findViewById(R.id.nav_drawer_layout);
        mAuth = FirebaseAuth.getInstance();



        /** Getting the NavigationView object */
        mNavigationView = findViewById(R.id.nav_view);

        View headerView = mNavigationView.getHeaderView(0);
        TextView headerSubtitleTextView=headerView.findViewById(R.id.nav_drawer_header_subtitle);
        /** Setting header banner to users email. */
        Intent i = getIntent();
        headerSubtitleTextView.setText(i.getStringExtra("user_email"));


        /** Set the listener for the NavigationView */
        mNavigationView.setNavigationItemSelectedListener(this);

        mActionBarDrawerToggle = new ActionBarDrawerToggle(
                this, // Activity / Context
                mDrawerLayout, // DrawerLayout
                R.string.navigation_drawer_open, // String to open
                R.string.navigation_drawer_close // String to close
        );

        mDrawerLayout.addDrawerListener(mActionBarDrawerToggle);
        mActionBarDrawerToggle.syncState();

        /** Setting the default fragment to the HomeFragment */
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.fragment_container, new HomeFragment()).commit();

    }

    /** Attaching the corresponding fragment with the navigation menu item. Inflate the fragment upon navigation item select. */
    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {// Handle navigation view item clicks here.
        int id = item.getItemId();

        if(id == R.id.nav_log_out){
            signOut();
        } else if (id == R.id.nav_album_list) {
            getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, new AlbumListFragment()).commit();
        } else if (id == R.id.nav_add_album) {
            getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, new AddAlbumFragment()).commit();
        }else if (id == R.id.nav_home) {
            getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, new HomeFragment()).commit();
        }



        /** Close the navigation drawer */
        mDrawerLayout.closeDrawer(GravityCompat.START);
        return true;
    }

    /** Method to sign the user out*/
    private void signOut(){
        mAuth.signOut();
        Intent i = new Intent(MainActivity.this, HomeActivity.class);
        startActivity(i);
    }
}