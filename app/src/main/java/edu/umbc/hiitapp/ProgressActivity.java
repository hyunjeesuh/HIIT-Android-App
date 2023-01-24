//package edu.umbc.hiitapp;
//
//import androidx.appcompat.app.AppCompatActivity;
//
//import android.content.Intent;
//import android.os.Bundle;
//import android.view.MenuItem;
//import android.widget.ArrayAdapter;
//import android.widget.CheckBox;
//import android.widget.CompoundButton;
//import android.widget.ListView;
//import android.widget.ProgressBar;
//
//import com.google.android.material.bottomnavigation.BottomNavigationView;
//
//public class ProgressActivity extends AppCompatActivity {
//
//    private ProgressBar progressBar;
//    private CheckBox cbExercises;
//    private int progress = 0;
//    private int numberOfCheckBoxes = 0;
//    private ListView lvExerciseCheckbox;
//
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_progress);
//
//        BottomNavigationView bottomNavigationView = findViewById(R.id.bottomNavigation);
//        bottomNavigationView.setSelectedItemId(R.id.progress);
//        bottomNavigationView.setOnItemSelectedListener(this::onNavigationItemSelected);
//
//        progressBar = findViewById(R.id.progressBar);
//
//        lvExerciseCheckbox = findViewById(R.id.lvCheckbox);
//
//        String[] exercises = getResources().getStringArray(R.array.exercises);
//        ArrayAdapter<Object> arrayAdapter = new ArrayAdapter<>(this,
//                R.layout.list_item_checkbox,
//                R.id.cbListItem,
//                exercises);
//        lvExerciseCheckbox.setAdapter(arrayAdapter);
//        cbExercises = new CheckBox(this);
//
//        cbExercises.setOnCheckedChangeListener((buttonView, isChecked) -> {
//            cbExercises.setChecked(cbExercises.isChecked());
//            progressBar.setProgress(0);
//            progressBar.setMax(100);
//            progress = progressBar.getProgress();
//            numberOfCheckBoxes = exercises.length;
//
//
//            for (int i = 0; i < numberOfCheckBoxes; i++) {
//                if (cbExercises.isChecked()) {
//                    if (progress < 100) {
//                        if (cbExercises.isSelected()) {
//                            progressBar.setProgress(progress+(numberOfCheckBoxes / 100));
//                        } else {
//                            progressBar.setProgress(progress);
//                        }
//                    } else {
//                        progressBar.setProgress(100);
//                    }
//                }
//            }
//        });
//    }
//
//    private boolean onNavigationItemSelected(MenuItem item) {
//
//        switch (item.getItemId()) {
//            case R.id.home:
//                startActivity(new Intent(getApplicationContext(),
//                        MainActivity.class));
//                return true;
//            case R.id.progress:
//                startActivity(new Intent(getApplicationContext(),
//                        ProgressActivity.class));
//                overridePendingTransition(0, 0);
//                return true;
//            case R.id.timer:
//                return true;
//        }
//        return false;
//    }
//}
