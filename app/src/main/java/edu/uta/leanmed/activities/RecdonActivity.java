package edu.uta.leanmed.activities;

import android.app.FragmentTransaction;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.widget.TextView;

public class RecdonActivity extends AppCompatActivity {
    private FragmentManager fragmentManager;
    private android.support.v4.app.FragmentTransaction fragmentTransaction;

    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {

            switch (item.getItemId()) {
                case R.id.navigation_search:
                    //fragmentTransaction.replace(R.id.content,new SearchFragment()).commit();
                    setTitle(R.string.title_search);
                    return true;
                case R.id.navigation_cart:
                    setTitle(R.string.title_cart);
                    //fragmentTransaction.replace(R.id.content,new CartFragment()).commit();
                    return true;
                case R.id.navigation_request:
                    setTitle(R.string.title_requests);
                    //fragmentTransaction.replace(R.id.content,new RequestFragment()).commit();
                    return true;
                case R.id.navigation_history:
                    setTitle(R.string.title_history);
                    //fragmentTransaction.replace(R.id.content,new HistoryFragment()).commit();
                    return true;
                case R.id.navigation_info:
                    setTitle(R.string.title_info);
                    //fragmentTransaction.replace(R.id.content,new InfoFragment()).commit();
                    return true;
            }
            return false;
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recdon);
        fragmentManager=getSupportFragmentManager();
        fragmentTransaction=fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.content,new SearchFragment()).commit();
        setTitle(R.string.title_search);
        BottomNavigationView navigation = (BottomNavigationView) findViewById(R.id.navigation);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);
    }

}
