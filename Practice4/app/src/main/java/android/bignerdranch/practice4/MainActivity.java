package android.bignerdranch.practice4;

import android.bignerdranch.practice4.fragments.AlbumListFragment;
import android.bignerdranch.practice4.fragments.TestFragment;
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



        /** Step 3: Get the NavigationView object from the layout SML file */
        mNavigationView = findViewById(R.id.nav_view);

        View headerView = mNavigationView.getHeaderView(0);
        TextView headerSubtitleTextView=headerView.findViewById(R.id.nav_drawer_header_subtitle);

        Intent i = getIntent();
        headerSubtitleTextView.setText(i.getStringExtra("user_email"));


        /** Step 4: Set the listener for the NavigationView. The Main Activity should
         * implement the interface NavigationView.OnNavigationItemSelectedListener */
        mNavigationView.setNavigationItemSelectedListener(this);

        mActionBarDrawerToggle = new ActionBarDrawerToggle(
                this, // Activity / Context
                mDrawerLayout, // DrawerLayout
                R.string.navigation_drawer_open, // String to open
                R.string.navigation_drawer_close // String to close
        );

        /** Step 6: Include the mActionBarDrawerToggle as the listener to the DrawerLayout.
         *  The synchState() method is used to synchronize the state of the navigation drawer */
        mDrawerLayout.addDrawerListener(mActionBarDrawerToggle);
        mActionBarDrawerToggle.syncState();

        /** Step 7:Set the default fragment to the HomeFragment */
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.fragment_container, new TestFragment()).commit();

    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {// Handle navigation view item clicks here.
        int id = item.getItemId();

        if(id == R.id.nav_log_out){
            signOut();
        } else if (id == R.id.nav_album_list) {
            getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, new AlbumListFragment()).commit();
        }


        /** Close the navigation drawer */
        mDrawerLayout.closeDrawer(GravityCompat.START);
        return true;
    }

    private void signOut(){
        mAuth.signOut();
        Intent i = new Intent(MainActivity.this, HomeActivity.class);
        startActivity(i);
    }
}