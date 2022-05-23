package com.example.powerslave;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.stream.Stream;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    //TODO: Add ChooseRulerActivity and finish it
    //TODO: Settings + Finish Music
    //TODO: More Pics
    //TODO: Military indexes
    //TODO: Better descriptions
    //TODO: Rework economic indexes

    //Main button on main activity
    private Button buttonStart;
    private Button buttonLoad;
    private Button buttonAbout;
    private Button buttonExit;

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
        mediaPlayer = MediaPlayer.create(getApplicationContext(), R.raw.bossantigua);
        mediaPlayer.setLooping(true);
        //mediaPlayer.start();
    }

    public void initCountries() {
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
            private float inflation;
            private String currency;
            private ArrayList<City> cities;

            private float developmentIndex;
            private float life_expectancy;
            private float literacy;

            private Economy(int key) {
                this.area = getResources().getIntArray(R.array.area)[key];
                this.population = getResources().getIntArray(R.array.population)[key];
                this.density = (float) population / area;

                this.gdp = getResources().getIntArray(R.array.gdp)[key] * 100L;
                this.gdp_per_person = ((float) gdp / population);
                this.debt = (long) (gdp * (Float.parseFloat(getResources().getStringArray(R.array.debt)[key]) / 100));

                this.inflation = Float.parseFloat(getResources().getStringArray(R.array.inflation)[key]);
                this.currency = getResources().getStringArray(R.array.currency)[key];

                this.life_expectancy = Float.parseFloat(getResources().getStringArray(R.array.live_expectancy)[key]);
                this.literacy = Float.parseFloat(getResources().getStringArray(R.array.literacy)[key]);
                this.developmentIndex = (life_expectancy * literacy * gdp_per_person) / 1000;
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

            public String getAreaString() {
                return area + " km^2";
            }

            public void setArea(long area) {
                this.area = area;
            }

            public float getDensity() {
                return density;
            }

            public String getDensityString() {
                return String.format("%.2f", density) + "/km^2";
            }

            public void setDensity(float density) {
                this.density = density;
            }

            public long getGdp() {
                return gdp;
            }

            public String getGdpString() {
                return gdp + " ƒ";
            }

            public void setGdp(long gdp) {
                this.gdp = gdp;
            }


            public float getInflation() {
                return inflation;
            }

            public String getInflationString() {
                return String.format("%.2f", inflation) + " %";
            }

            public void setInflation(float inflation) {
                this.inflation = inflation;
            }

            public float getGdp_per_person() {
                return gdp_per_person;
            }

            public void setGdp_per_person(float gdp_per_person) {
                this.gdp_per_person = gdp_per_person;
            }

            public String getGdp_per_person_String() {
                return String.format("%.2f", gdp_per_person) + " ƒ";
            }

            public void setGdp_per_person(long gdp_per_person) {
                this.gdp_per_person = gdp_per_person;
            }

            public long getDebt() {
                return debt;
            }

            public String getDebtString() {
                Float debtFloat = (((float) debt / gdp) * 100);
                String debtPercent = String.format("( %.2f", debtFloat).concat(" %)");
                return debt + " ƒ " + debtPercent;
            }

            public void setDebt(long debt) {
                this.debt = debt;
            }

            public String getCurrency() {
                return adjective + " " + currency;
            }

            public void setCurrency(String currency) {
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

            public String getDevelopmentIndexString() {
                float ave_di = 0f;
                String devString = "(Middle)";
                for (Country country : countries) {
                    ave_di += country.economy.developmentIndex;
                }
                ave_di /= countries.size();
                if (developmentIndex <= ave_di) {
                    if (developmentIndex <= ave_di - (ave_di*0.75)) {
                        devString = "(Very Low)";
                    } else if (developmentIndex <= ave_di - (ave_di * 0.5)) {
                        devString = "(Low)";
                    }
                } else {
                    if (developmentIndex >= ave_di + (ave_di * 0.75)) {
                        devString = "(Very High)";
                    } else if (developmentIndex >= ave_di + (ave_di * 0.5)) {
                        devString = "(High)";
                    }
                }
                return String.format("%.2f", developmentIndex) + " " + devString;
            }

            public void setDevelopmentIndex(float developmentIndex) {
                this.developmentIndex = developmentIndex;
            }

            public float getLiteracy() {
                return literacy;
            }

            public String getLiteracyString() {
                return String.format("%.2f", literacy) + " %";
            }

            public void setLiteracy(float literacy) {
                this.literacy = literacy;
            }

            public float getLife_expectancy() {
                return life_expectancy;
            }

            public String getLife_expectancyString() {
                return String.format("%.2f", life_expectancy) + " years";
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
            this.adjective = getResources().getStringArray(R.array.adjectives)[key];
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
