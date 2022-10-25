package com.example.powerslave;

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

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.example.powerslave.government.Country;
import com.example.powerslave.government.Event;
import com.example.powerslave.person.Ruler;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Random;

// Change updates
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
    private ImageButton buttonDevelopment;
    private ImageButton buttonIndustry;
    private ImageButton buttonTransportation;
    private ImageButton buttonCulture;
    private ImageButton buttonInternalAffairs;
    private ImageButton buttonJustice;
    private ImageButton buttonParliament;
    private ImageButton buttonNationalIntelligence;
    private ImageButton buttonForeignAffairs;

    public static Country country;
    public static Ruler ruler;
    public static boolean showAlertMessage;

    public static Calendar calendar = Calendar.getInstance();
    public static int week;
    public static int total_weeks;
    public static SimpleDateFormat format1 = new SimpleDateFormat("MMMM yyyy");

    public static int selectedMinistry = 0;

    public static void startMainGameActivity() {
        week = 1;
        total_weeks = 0;
        calendar.set(1965, 0, 25);
        showAlertMessage = true;
        country = ChooseRulerActivity.country;
        ruler = ChooseRulerActivity.ruler;
        country.setRuler(ruler);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_game_menu);

        textViewDate = findViewById(R.id.textViewDate);
        textViewCountryName = findViewById(R.id.textViewCountryName);
        imageViewFlag = findViewById(R.id.imageViewFlag);
        buttonNextMonth = findViewById(R.id.buttonNextMonth);
        buttonHealthcare = findViewById(R.id.buttonHealthcare);
        buttonEducation = findViewById(R.id.buttonEducation);
        buttonEconomy = findViewById(R.id.buttonEconomy);
        buttonDefense = findViewById(R.id.buttonDefense);
        buttonAgriculture = findViewById(R.id.buttonAgriculture);
        buttonDevelopment = findViewById(R.id.buttonDevelopment);
        buttonIndustry = findViewById(R.id.buttonIndustry);
        buttonTransportation = findViewById(R.id.buttonTransportation);
        buttonCulture = findViewById(R.id.buttonCulture);
        buttonInternalAffairs = findViewById(R.id.buttonInternalAffairs);
        buttonJustice = findViewById(R.id.buttonJustice);
        buttonParliament = findViewById(R.id.buttonParliament);
        buttonNationalIntelligence = findViewById(R.id.buttonNationalIntelligence);
        buttonForeignAffairs = findViewById(R.id.buttonForeignAffairs);

        textViewDate.setText("Current date is: " + "Week " + week + " " + format1.format(calendar.getTime()));
        textViewCountryName.setText("Country: " + country.getName() + "\n");
        textViewCountryName.setText(textViewCountryName.getText() + "Capital: " + country.getCapitalName() + "\n");
        textViewCountryName.setText(textViewCountryName.getText() + "Continent: " + country.getContinent() + "\n");
        textViewCountryName.setText(textViewCountryName.getText() + "Ruler: " + country.getRuler().getName() + " " + country.getRuler().getSurname() + "\n");
        textViewCountryName.setText(textViewCountryName.getText() + "Rating of ruler: " + country.getRuler().getRatingString() + " %" + "\n");
        textViewCountryName.setText(textViewCountryName.getText() + "Time left: " + country.getRuler().getTerm_in_weeks() + " weeks" + "\n");

        imageViewFlag.setImageURI(country.getFlagSrc());

        buttonNextMonth.setOnClickListener(this);
        buttonHealthcare.setOnClickListener(this);
        buttonEducation.setOnClickListener(this);
        buttonEconomy.setOnClickListener(this);
        buttonDefense.setOnClickListener(this);
        buttonAgriculture.setOnClickListener(this);
        buttonDevelopment.setOnClickListener(this);
        buttonIndustry.setOnClickListener(this);
        buttonTransportation.setOnClickListener(this);
        buttonCulture.setOnClickListener(this);
        buttonInternalAffairs.setOnClickListener(this);
        buttonJustice.setOnClickListener(this);
        buttonParliament.setOnClickListener(this);
        buttonNationalIntelligence.setOnClickListener(this);
        buttonForeignAffairs.setOnClickListener(this);

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
                    .setCancelable(false)
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
            total_weeks++;
            country.getRuler().setTerm_in_weeks(country.getRuler().getTerm_in_weeks() - 1);
            if (++week == 5) {
                calendar.add(Calendar.MONTH, 1);
                country.addMinisterExperience();
                country.getRuler().changeRating();
                week = 1;
                if (country.getRuler().getTerm_in_weeks() == 0) {
                    gameOver("Your term is finally over!");
                    return;
                }
                if (country.getRuler().getRating() == 0f) {
                    gameOver("You were killed by angry mob!");
                    return;
                }
            }
            if (total_weeks % 52 == 0) {
                country.updateMinisters();
            }
            if (new Random().nextInt(15) == 1) {
                showEvent();
                return;
            }
            country.oneMove();
            MainGameMenuActivity.this.recreate();
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
            } else if (view.getId() == buttonDevelopment.getId()) {
                selectedMinistry = 5;
            } else if (view.getId() == buttonIndustry.getId()) {
                selectedMinistry = 6;
            } else if (view.getId() == buttonTransportation.getId()) {
                selectedMinistry = 7;
            } else if (view.getId() == buttonCulture.getId()) {
                selectedMinistry = 8;
            } else if (view.getId() == buttonInternalAffairs.getId()) {
                selectedMinistry = 9;
            } else if (view.getId() == buttonJustice.getId()) {
                selectedMinistry = 10;
            } else if (view.getId() == buttonParliament.getId()) {
                selectedMinistry = 11;
            } else if (view.getId() == buttonNationalIntelligence.getId()) {
                selectedMinistry = 12;
            } else if (view.getId() == buttonForeignAffairs.getId()) {
                selectedMinistry = 13;
            }

            startActivity(intent);
        }
    }

    private void gameOver(String title) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        String result;
        if (ruler.getRating() < 10f) {
            result = "Terrible";
        } else if (ruler.getRating() >= 10f && ruler.getRating() < 25f) {
            result = "Bad";
        } else if (ruler.getRating() >= 25f && ruler.getRating() < 50f) {
            result = "Average";
        } else if (ruler.getRating() >= 50 && ruler.getRating() < 75f) {
            result = "Good";
        } else {
            result = "Incredible";
        }
        result += " work";
        builder.setTitle(title);
        builder.setMessage("Total time ruled: " + total_weeks + " weeks" + "\n" + "Your rating in the end: " + country.getRuler().getRatingString() + " %" + " \n(" + result + ")\n");
        builder.setCancelable(false);
        builder.setPositiveButton("My time has come...", (dialogInterface, i) -> {
            Intent intent = new Intent(MainGameMenuActivity.this, MainActivity.class);
            startActivity(intent);
            MainGameMenuActivity.startMainGameActivity();
        });
        AlertDialog alert = builder.create();
        alert.show();
    }

    private void showEvent() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        Event event = new Event(this, country);
        builder.setTitle(event.getEventTitle());
        builder.setMessage(event.getEventText());
        builder.setCancelable(false);
        builder.setPositiveButton("Ok", (dialogInterface, i) -> {
            country.oneMove();
            MainGameMenuActivity.this.recreate();
        });

        AlertDialog dialog = builder.create();
        dialog.show();

    }
}