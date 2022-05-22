package com.example.powerslave;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    //TODO: Settings + Finish Music
    //TODO: More Pics
    //One

    //Main button on main activity
    Button buttonStart;
    Button buttonLoad;
    Button buttonAbout;
    Button buttonExit;

    public MediaPlayer mediaPlayer;

    //Static arraylist of all game countries
    public static final ArrayList<Country> countries = new ArrayList<>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initCountries();
        startMusic();

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

    private void startMusic() {
        mediaPlayer = MediaPlayer.create(getApplicationContext(),R.raw.bossantigua);
        mediaPlayer.setLooping(true);
        //mediaPlayer.start();
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
        private int key;

        private String name;
        private String capitalName;
        private Uri flagSrc;
        private RegionalForm regionalForm;
        private GovernmentType governmentType;
        private String desc;
        private String adjective;
        private Religion religion;
        private Continent continent;

        private Economy economy;

        public class Economy {

            private long population;
            private long area;
            private float density;

            private long gdp;
            private float gdp_per_person;
            private long debt;
            private Currency currency;
            private ArrayList<City> cities;

            private float developmentIndex;
            private float literacy;
            private float life_expectancy;

            private Economy(int key){
                this.area = getResources().getIntArray(R.array.area)[key];
                this.population = getResources().getIntArray(R.array.population)[key];
                this.density = (float)population/area;

                this.gdp = getResources().getIntArray(R.array.gdp)[key];
                this.gdp_per_person = ((float)gdp/population);

            }

            public long getPopulation() {
                return population;
            }

            public void setPopulation(long population) {
                this.population = population;
            }

            public long getArea() {
                return area;
            }

            public void setArea(long area) {
                this.area = area;
            }

            public float getDensity() {
                return density;
            }

            public String getDensityString() {
                return String.format("%.2f", density);
            }

            public void setDensity(float density) {
                this.density = density;
            }

            public long getGdp() {
                return gdp;
            }

            public void setGdp(long gdp) {
                this.gdp = gdp;
            }

            public float getGdp_per_person() {
                return gdp_per_person;
            }

            public String getGdp_per_person_String() {
                return String.format("%.2f", gdp_per_person);
            }

            public void setGdp_per_person(long gdp_per_person) {
                this.gdp_per_person = gdp_per_person;
            }

            public long getDebt() {
                return debt;
            }

            public void setDebt(long debt) {
                this.debt = debt;
            }

            public Currency getCurrency() {
                return currency;
            }

            public void setCurrency(Currency currency) {
                this.currency = currency;
            }

            public ArrayList<City> getCities() {
                return cities;
            }

            public void setCities(ArrayList<City> cities) {
                this.cities = cities;
            }

            public float getDevelopmentIndex() {
                return developmentIndex;
            }

            public void setDevelopmentIndex(float developmentIndex) {
                this.developmentIndex = developmentIndex;
            }

            public float getLiteracy() {
                return literacy;
            }

            public void setLiteracy(float literacy) {
                this.literacy = literacy;
            }

            public float getLife_expectancy() {
                return life_expectancy;
            }

            public void setLife_expectancy(float life_expectancy) {
                this.life_expectancy = life_expectancy;
            }
        }

        private Military military;

        private Government government;

        private Country(int key) {
            this.key = key;
            this.name = getResources().getStringArray(R.array.countries)[key];
            this.capitalName = getResources().getStringArray(R.array.capitals)[key];
            this.flagSrc = Uri.parse(getResources().getStringArray(R.array.flag_src)[key]);
            this.regionalForm = RegionalForm.valueOf(getResources().getStringArray(R.array.regional)[key]);
            this.governmentType = GovernmentType.valueOf(getResources().getStringArray(R.array.government)[key]);
            this.continent = Continent.valueOf(getResources().getStringArray(R.array.location)[key]);
            this.economy = new Economy(key);
        }

        @Override
        public String toString() {
            return name;
        }

        public Uri getFlagSrc() {
            return flagSrc;
        }

        public String getCapitalName() {
            return capitalName;
        }

        public GovernmentType getGovernmentType() {
            return governmentType;
        }

        public RegionalForm getRegionalForm() {
            return regionalForm;
        }

        public Continent getContinent() {
            return continent;
        }

        public Economy getEconomy() {
            return economy;
        }
    }

}
