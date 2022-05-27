package com.example.powerslave;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.powerslave.government.City;
import com.example.powerslave.government.Continent;
import com.example.powerslave.government.Government;
import com.example.powerslave.government.GovernmentType;
import com.example.powerslave.government.RegionalForm;
import com.example.powerslave.government.Religion;

import java.util.ArrayList;

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

                this.gdp = getResources().getIntArray(R.array.gdp)[key] * 10L;
                this.gdp_per_person = ((float) gdp / population);
                this.debt = (long) (gdp * (Float.parseFloat(getResources().getStringArray(R.array.debt)[key]) / 100));

                this.inflation = Float.parseFloat(getResources().getStringArray(R.array.inflation)[key]);
                this.currency = getResources().getStringArray(R.array.currency)[key];

                this.life_expectancy = Float.parseFloat(getResources().getStringArray(R.array.live_expectancy)[key]);
                this.literacy = Float.parseFloat(getResources().getStringArray(R.array.literacy)[key]);
                this.developmentIndex = (life_expectancy * literacy * gdp_per_person) / 1000;
            }

            public long getGdp() {
                return gdp;
            }

            public void setGdp(long gdp) {
                this.gdp = gdp;
            }

            public String getCurrency() {
                return adjective + " " + currency;
            }

            public void setCurrency(String currency) {
                this.currency = currency;
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
                    if (developmentIndex <= ave_di - (ave_di * 0.75)) {
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

            @Override
            public String toString() {
                String string;
                string = "Area: " + area + " km^2" + "\n";
                string += "Population: " + population + "\n";
                string += "Density: " + String.format("%.2f", density) + "/km^2" + "\n";
                string += "GDP: " + gdp + " ƒ" + "\n";
                string += "GDP per Capita: " + String.format("%.2f", gdp_per_person) + " ƒ" + "\n";
                string += "National Debt: " + debt + " ƒ " + String.format("(%.2f", (float) debt / gdp * 100).concat(" %)") + "\n";
                string += "Inflation: " + String.format("%.2f", inflation) + " %" + "\n";
                string += "Currency: " + adjective + " " + currency + "\n";
                string += "Life Expectancy: " + String.format("%.2f", life_expectancy) + " years" + "\n";
                string += "Literacy: " + String.format("%.2f", literacy) + " %"+ "\n";
                string += "Development Index: " + getDevelopmentIndexString() + "\n";
                return string;
            }
        }

        private Military military;

        public class Military {
            private int arm_power;
            private int nav_power;
            private int air_power;
            private boolean conscription;
            private boolean landlocked;
            private int outlay;

            public Military(int key) {
                this.arm_power = getResources().getIntArray(R.array.army)[key];
                this.nav_power = getResources().getIntArray(R.array.navy)[key];
                this.air_power = getResources().getIntArray(R.array.air_force)[key];
                this.conscription = Boolean.parseBoolean(getResources().getStringArray(R.array.conscription)[key]);
                this.landlocked = Boolean.parseBoolean(getResources().getStringArray(R.array.landlocked)[key]);
                this.outlay = (arm_power * 100) + (nav_power * 500) + (air_power * 800);
            }

            public int getArm_power() {
                return arm_power;
            }

            public void setArm_power(int arm_power) {
                this.arm_power = arm_power;
            }

            public int getNav_power() {
                return nav_power;
            }

            public void setNav_power(int nav_power) {
                this.nav_power = nav_power;
            }

            public int getAir_power() {
                return air_power;
            }

            public void setAir_power(int air_power) {
                this.air_power = air_power;
            }

            public boolean isConscription() {
                return conscription;
            }

            public void setConscription(boolean conscription) {
                this.conscription = conscription;
            }

            public boolean isLandlocked() {
                return landlocked;
            }

            public void setLandlocked(boolean landlocked) {
                this.landlocked = landlocked;
            }

            public int getOutlay() {
                return outlay;
            }

            public void setOutlay(int outlay) {
                this.outlay = outlay;
            }

            @Override
            public String toString() {
                String string;
                string = "Army Power: " + arm_power + "\n";
                string += "Navy Power: " + nav_power + "\n";
                string += "Air Force Power: " + air_power + "\n";
                string += "Conscription: " + (isConscription() ? "Yes" : "No") + "\n";
                string += "Landlocked: " + (isLandlocked() ? "Yes" : "No") + "\n";
                string += "Outlay: " + outlay + " ƒ ";
                string += "(" + String.format("%.2f", (float) outlay / getEconomy().getGdp() * 100) + "%)" + "\n";
                return string;
            }
        }

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
            this.military = new Military(key);
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

        public Military getMilitary() {
            return military;
        }

        public void setMilitary(Military military) {
            this.military = military;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }
    }

}
