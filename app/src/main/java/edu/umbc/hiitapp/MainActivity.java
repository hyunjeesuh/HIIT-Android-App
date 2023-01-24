package edu.umbc.hiitapp;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.bottomnavigation.BottomNavigationView;


public class MainActivity extends AppCompatActivity {

    BottomNavigationView bottomNavigationView;
    ListView lvExercises;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        lvExercises = findViewById(R.id.lvExercises);
        bottomNavigationView = findViewById(R.id.bottomNavigation);
        bottomNavigationView.setSelectedItemId(R.id.home);

        bottomNavigationView.setOnItemSelectedListener(this::onNavigationItemSelected);
    }

    protected void onResume() {
        String[] exercises = getResources().getStringArray(R.array.exercises);
        ArrayAdapter<Object> arrayAdapter = new ArrayAdapter<>(this,
                R.layout.list_item_home,
                R.id.tvListItem,
                exercises);

        lvExercises.setAdapter(arrayAdapter);
        lvExercises.setOnItemClickListener(onExerciseClicked);
        super.onResume();
    }

    private final AdapterView.OnItemClickListener onExerciseClicked =
            (parent, view, position, id) -> {

                switch (position) {
                    case 0:
                        startActivity(new Intent(MainActivity.this,
                                BurpeesActivity.class));
                        break;
                    case 1:
                        startActivity(new Intent(MainActivity.this,
                                HighKneesActivity.class));
                        break;
                    case 2:
                        startActivity(new Intent(MainActivity.this,
                                MtnClimbersActivity.class));
                        break;
                    case 3:
                        startActivity(new Intent(MainActivity.this,
                                PushUpsActivity.class));
                        break;
                    case 4:
                        startActivity(new Intent(MainActivity.this,
                                BicycleCrunchActivity.class));
                        break;
                    case 5:
                        startActivity(new Intent(MainActivity.this,
                                PlankActivity.class));
                        break;
                    case 6:
                        startActivity(new Intent(MainActivity.this,
                                SquatsActivity.class));
                        break;
                    case 7:
                        startActivity(new Intent(MainActivity.this,
                                DonkeyKickActivity.class));
                        break;
                    case 8:
                        startActivity(new Intent(MainActivity.this,
                                BackLegLiftsActivity.class));
                        break;
                    case 9:
                        startActivity(new Intent(MainActivity.this,
                                FireHydrantActivity.class));
                        break;
                }
            };

    private boolean onNavigationItemSelected(MenuItem item) {

        switch (item.getItemId()) {
            case R.id.home:
                return true;
//            case R.id.progress:
//                startActivity(new Intent(getApplicationContext(),
//                        ProgressActivity.class));
//                overridePendingTransition(0, 0);
//                return true;
            case R.id.timer:
                startActivity(new Intent(getApplicationContext(),
                        TimerActivity.class));
                overridePendingTransition(0, 0);
                return true;
        }
        return false;
    }
}