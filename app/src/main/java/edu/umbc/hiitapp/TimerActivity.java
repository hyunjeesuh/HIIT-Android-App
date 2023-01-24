package edu.umbc.hiitapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.MenuItem;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.text.DecimalFormat;
import java.text.Format;
import java.text.NumberFormat;
import java.util.Locale;

public class TimerActivity extends AppCompatActivity {

    private EditText etTimer;
    private TextView tvCountDown;
    private Button btnStart;
    private Button btnReset;
    private Button btnSet;

    private CountDownTimer countDownTimer;

    private boolean timerRunning;
    private long startTimeMillis;
    private long timeLeftMillis;
    private long endTime;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_timer);

        BottomNavigationView bottomNavigationView = findViewById(R.id.bottomNavigation);
        bottomNavigationView.setSelectedItemId(R.id.timer);
        bottomNavigationView.setOnItemSelectedListener(this::onNavigationItemSelected);

        etTimer = findViewById(R.id.etTimer);
        tvCountDown = findViewById(R.id.tvCountdown);
        btnStart = findViewById(R.id.btnStart);
        btnReset = findViewById(R.id.btnReset);
        btnSet = findViewById(R.id.btnSet);

        btnSet.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String input = etTimer.getText().toString();
                if (input.length() == 0) {
                    Toast.makeText(TimerActivity.this, "Field can't be empty", Toast.LENGTH_SHORT).show();
                    return;
                }

                long millisInput = Long.parseLong(input) * 60000;
                if (millisInput == 0) {
                    Toast.makeText(TimerActivity.this, "Please enter a positive number", Toast.LENGTH_SHORT).show();
                    return;
                }

                setTime(millisInput);
                etTimer.setText("");
            }
        });

        btnStart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (timerRunning) {
                    pauseTimer();
                } else {
                    startTimer();
                }
            }
        });

        btnReset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                resetTimer();
            }
        });
    }

    private void setTime(long milliseconds) {
        startTimeMillis = milliseconds;
        resetTimer();
        closeKeyboard();
    }

    private void startTimer() {
        endTime = System.currentTimeMillis() + timeLeftMillis;

        countDownTimer = new CountDownTimer(timeLeftMillis, 1000) {
            @Override
            public void onTick(long millisUntilFinished) {
                timeLeftMillis = millisUntilFinished;
                updateCountDownText();
            }

            @Override
            public void onFinish() {
                timerRunning = false;
                updateWatchInterface();
            }
        }.start();

        timerRunning = true;
        updateWatchInterface();
    }

    private void pauseTimer() {
        countDownTimer.cancel();
        timerRunning = false;
        updateWatchInterface();
    }

    private void resetTimer() {
        timeLeftMillis = startTimeMillis;
        updateCountDownText();
        updateWatchInterface();
    }

    private void updateCountDownText() {
        int minutes = (int) ((timeLeftMillis / 1000) % 3600) / 60;
        int seconds = (int) (timeLeftMillis / 1000) % 60;

        String timeLeftFormatted;

        timeLeftFormatted = String.format(Locale.getDefault(),
                    "%02d:%02d", minutes, seconds);
        tvCountDown.setText(timeLeftFormatted);
    }

    private void updateWatchInterface() {
        if (timerRunning) {
            btnReset.setVisibility(View.INVISIBLE);
            btnStart.setText("Pause");
        } else {
            etTimer.setVisibility(View.VISIBLE);
            btnStart.setText("Start");

            if (timeLeftMillis < 1000) {
                btnStart.setVisibility(View.INVISIBLE);
            } else {
                btnStart.setVisibility(View.VISIBLE);
            }

            if (timeLeftMillis < startTimeMillis) {
                btnReset.setVisibility(View.VISIBLE);
            } else {
                btnReset.setVisibility(View.INVISIBLE);
            }
        }
    }

    private void closeKeyboard() {
        View view = this.getCurrentFocus();
        if (view != null) {
            InputMethodManager imm = (InputMethodManager) getSystemService(INPUT_METHOD_SERVICE);
            imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
        }
    }

    @Override
    protected void onStop() {
        super.onStop();

        SharedPreferences prefs = getSharedPreferences("prefs", MODE_PRIVATE);
        SharedPreferences.Editor editor = prefs.edit();

        editor.putLong("startTimeInMillis", startTimeMillis);
        editor.putLong("millisLeft", timeLeftMillis);
        editor.putBoolean("timerRunning", timerRunning);
        editor.putLong("endTime", endTime);

        editor.apply();

        if (countDownTimer != null) {
            countDownTimer.cancel();
        }
    }

    @Override
    protected void onStart() {
        super.onStart();

        SharedPreferences prefs = getSharedPreferences("prefs", MODE_PRIVATE);

        startTimeMillis = prefs.getLong("startTimeInMillis", 600000);
        timeLeftMillis = prefs.getLong("millisLeft", startTimeMillis);
        timerRunning = prefs.getBoolean("timerRunning", false);

        updateCountDownText();
        updateWatchInterface();

        if (timerRunning) {
            endTime = prefs.getLong("endTime", 0);
            timeLeftMillis = endTime - System.currentTimeMillis();

            if (timeLeftMillis < 0) {
                timeLeftMillis = 0;
                timerRunning = false;
                updateCountDownText();
                updateWatchInterface();
            } else {
                startTimer();
            }
        }
    }

    private boolean onNavigationItemSelected(MenuItem item) {

        switch (item.getItemId()) {
            case R.id.home:
                startActivity(new Intent(getApplicationContext(),
                        MainActivity.class));
                return true;
//            case R.id.progress:
//                startActivity(new Intent(getApplicationContext(),
//                        ProgressActivity.class));
//                overridePendingTransition(0, 0);
//                return true;
            case R.id.timer:
                return true;
        }
        return false;
    }
}