package edu.umbc.hiitapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;

import com.google.android.material.bottomnavigation.BottomNavigationView;

public class BurpeesActivity extends AppCompatActivity {

    BottomNavigationView bottomNavigationView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_burpees);

        bottomNavigationView = findViewById(R.id.bottomNavigation);
        bottomNavigationView.setSelectedItemId(R.id.home);

        bottomNavigationView.setOnItemSelectedListener(item -> {

            switch (item.getItemId()) {
                case R.id.home:
                    startActivity(new Intent(getApplicationContext(),
                            MainActivity.class));
                    overridePendingTransition(0,0);
                    return true;
//                case R.id.progress:
//                    startActivity(new Intent(getApplicationContext(),
//                            ProgressActivity.class));
//                    overridePendingTransition(0,0);
//                    return true;
                case R.id.timer:
                    startActivity(new Intent(getApplicationContext(),
                            TimerActivity.class));
                    overridePendingTransition(0,0);
                    return true;
            }
            return false;
        });
    }
}