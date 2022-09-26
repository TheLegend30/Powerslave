package com.example.powerslave;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.powerslave.government.Country;
import com.example.powerslave.government.Event;
import com.example.powerslave.person.Ruler;

import java.text.SimpleDateFormat;
import java.util.Calendar;

public class MainGameMenuActivity extends AppCompatActivity implements View.OnClickListener {
    private TextView textViewDate;
    private TextView textViewCountryName;
    private ImageView imageViewFlag;
    private Button buttonNextMonth;
    private ImageButton buttonHealthcare;
    private ImageButton buttonEducation;
    private ImageButton buttonEconomy;
    private ImageButton buttonDefense;
    private ImageButton buttonAgriculture;

    public static Country country;
    public static Ruler ruler;
    public static boolean showAlertMessage;

    public static Calendar calendar = Calendar.getInstance();
    public static SimpleDateFormat format1 = new SimpleDateFormat("MMMM yyyy");

    public static int selectedMinistry = 0;

    static {
        startMainGameActivity();
    }

    private static void startMainGameActivity() {
        calendar.set(1965, 0, 25);
        showAlertMessage = true;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_game_menu);

        country = ChooseRulerActivity.country;
        ruler = ChooseRulerActivity.ruler;

        textViewDate = findViewById(R.id.textViewDate);
        textViewCountryName = findViewById(R.id.textViewCountryName);
        imageViewFlag = findViewById(R.id.imageViewFlag);
        buttonNextMonth = findViewById(R.id.buttonNextMonth);
        buttonHealthcare = findViewById(R.id.buttonHealthcare);
        buttonEducation = findViewById(R.id.buttonEducation);
        buttonEconomy = findViewById(R.id.buttonEconomy);
        buttonDefense = findViewById(R.id.buttonDefense);
        buttonAgriculture = findViewById(R.id.buttonAgriculture);

        textViewDate.setText("Current date is: " + format1.format(calendar.getTime()));
        textViewCountryName.setText("Country: " + country.getName());

        imageViewFlag.setImageURI(country.getFlagSrc());

        buttonNextMonth.setOnClickListener(this);
        buttonHealthcare.setOnClickListener(this);
        buttonEducation.setOnClickListener(this);
        buttonEconomy.setOnClickListener(this);
        buttonDefense.setOnClickListener(this);
        buttonAgriculture.setOnClickListener(this);

        ActionBar actionBar = getSupportActionBar();
        actionBar.setIcon(R.drawable.ic_settings_foreground);
        actionBar.setDisplayUseLogoEnabled(true);
        actionBar.setDisplayShowHomeEnabled(true);

        showAlertMessage();


    }

    private void showAlertMessage() {
        DialogInterface.OnClickListener dialogClickListener = (dialogInterface, i) -> {
            switch (i) {
                case Dialog.BUTTON_POSITIVE:
                    showAlertMessage = false;
                    break;
            }

        };
        AlertDialog.Builder builder = new AlertDialog.Builder(MainGameMenuActivity.this);
        if (showAlertMessage) {
            builder.setMessage("Welcome to the Power Slave! Now you're playing as ruler of country of " + country.getName() + ". Good luck in your decisions!")
                    .setPositiveButton("Understood", dialogClickListener)
                    .show();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        getMenuInflater().inflate(R.menu.main, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {

        switch (item.getItemId()) {
            case R.id.exit:
                Intent intent = new Intent(MainGameMenuActivity.this, MainActivity.class);
                startActivity(intent);
                MainGameMenuActivity.startMainGameActivity();
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onBackPressed() {

    }

    @Override
    public void onClick(View view) {
        if (view.getId() == buttonNextMonth.getId()) {
            calendar.add(Calendar.MONTH, 1);
            country.addMinisterExperience();
            if (calendar.getTime().getMonth() == 0) {
                country.updateMinisters();
            }
            showEvent();
        } else {
            Intent intent = new Intent(MainGameMenuActivity.this, MinistryActivity.class);
            if (view.getId() == buttonHealthcare.getId()) {
                selectedMinistry = 0;
            } else if (view.getId() == buttonEducation.getId()) {
                selectedMinistry = 1;
            } else if (view.getId() == buttonEconomy.getId()) {
                selectedMinistry = 2;
            } else if (view.getId() == buttonDefense.getId()) {
                selectedMinistry = 3;
            } else if (view.getId() == buttonAgriculture.getId()) {
                selectedMinistry = 4;
            }

            startActivity(intent);
        }
    }

    private void showEvent() {
        Event event = new Event(this);
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle(event.getEventText());

        builder.setItems(event.getEventChoices(), (dialogInterface, i) -> {
            switch (i) {
                default:
                    MainGameMenuActivity.this.recreate();
            }
        });
        builder.create().show();
    }
}