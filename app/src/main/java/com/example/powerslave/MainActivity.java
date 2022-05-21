package com.example.powerslave;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    //TODO: Settings
    //TODO: Music
    //TODO: More Pics

    //Main button on main activity
    Button buttonStart;
    Button buttonLoad;
    Button buttonAbout;
    Button buttonExit;

    //Static arraylist of all game countries
    public static final ArrayList<Country> countries = new ArrayList<>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initCountries();

        //Assigning values to buttons
        buttonStart = findViewById(R.id.buttonStart);
        buttonLoad = findViewById(R.id.buttonLoad);
        buttonAbout = findViewById(R.id.buttonAbout);
        buttonExit = findViewById(R.id.buttonExit);

        //Set onClick method to each of buttons
        buttonStart.setOnClickListener(this);
        buttonLoad.setOnClickListener(this);
        buttonAbout.setOnClickListener(this);
        buttonExit.setOnClickListener(this);

    }

    private void initCountries() {
        int length = getResources().getStringArray(R.array.countries).length;
        for (int i = 0; i < length; i++) {
            countries.add(new Country(i));
        }
    }

    @Override
    public void onClick(View view) {
        if (view.getId() == buttonStart.getId()) {
            Intent intent = new Intent(MainActivity.this, StartActivity.class);
            startActivity(intent);
        } else if (view.getId() == buttonLoad.getId()) {
        } else if (view.getId() == buttonAbout.getId()) {
            Intent intent = new Intent(MainActivity.this, AboutActivity.class);
            startActivity(intent);
        } else if (view.getId() == buttonExit.getId()) {
            //Rudimentary function in my opinion. Android player can always freely close the app.
            super.finishAffinity();
        }
    }

    public class Country {
        //TODO: Religion enum, Currency enum
        //TODO: Inner classes Economy, Army,
        //TODO: Person class
        //TODO: Parliament class
        //TODO: Finish ideology descriptions
        private int key;

        private String name;
        private String capitalName;
        private int flagSrc;
        private ArrayList<String> cities;
        private GovernmentType governmentType;

        private String adjective;
        private String culture;
        private Religion religion;
        private Continent continent;
        private Currency currency;

        private int developmentIndex;
        private long population;
        private long area;
        private long gdp;
        private long debt;
        private long armyPower;

        Country(int key) {
            this.key = key;
            this.name = getResources().getStringArray(R.array.countries)[key];
            this.flagSrc = getResources().getIntArray(R.array.flag_src)[key];

        }

        @Override
        public String toString() {
            return name;
        }

        public int getFlagSrc() {
            return flagSrc;
        }
    }

}
