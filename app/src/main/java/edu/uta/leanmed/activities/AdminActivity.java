package edu.uta.leanmed.activities;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.widget.TextView;

public class AdminActivity extends AppCompatActivity {

    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId()) {
                case R.id.navigation_search:
                    //fragmentTransaction.replace(R.id.content,new SearchFragment()).commit();
                    return true;
                case R.id.navigation_request:
                    //fragmentTransaction.replace(R.id.content,new UserFragment()).commit();
                    return true;
                case R.id.navigation_history:
                    //fragmentTransaction.replace(R.id.content,new HistoryFragment()).commit();
                    return true;
                case R.id.navigation_info:
                    //fragmentTransaction.replace(R.id.content,new InfoFragment()).commit();
                    return true;
            }
            return false;
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin);
        BottomNavigationView navigation = (BottomNavigationView) findViewById(R.id.navigation);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);
    }

}
