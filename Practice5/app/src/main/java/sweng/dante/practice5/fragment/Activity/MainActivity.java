package sweng.dante.practice5.fragment.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.GravityCompat;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import com.google.android.material.navigation.NavigationView;

import sweng.dante.practice5.R;
import sweng.dante.practice5.fragment.Fragment.HomeFragment;
import sweng.dante.practice5.fragment.Fragment.MapsFragment;

public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener{
    private ActionBarDrawerToggle mActionBarDrawerToggle;
    private NavigationView mNavigationView;
    private DrawerLayout mDrawerLayout;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        /** Instantiate elements
         * */
        setContentView(R.layout.activity_main);

        setContentView(R.layout.activity_main);
        mDrawerLayout = findViewById(R.id.nav_drawer_layout);

        mDrawerLayout = findViewById(R.id.nav_drawer_layout);



        /** Getting the NavigationView object */
        mNavigationView = findViewById(R.id.nav_view);

        View headerView = mNavigationView.getHeaderView(0);
        TextView headerSubtitleTextView=headerView.findViewById(R.id.nav_drawer_header_subtitle);



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

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
        int id = menuItem.getItemId();

        if(id == R.id.nav_map){
            getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, new MapsFragment()).commit();

        }else if (id == R.id.nav_home) {
            getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, new HomeFragment()).commit();
        }



        /** Close the navigation drawer */
        mDrawerLayout.closeDrawer(GravityCompat.START);
        return true;
    }
}