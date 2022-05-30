package com.example.powerslave.government;

import android.content.Context;
import android.net.Uri;

import androidx.appcompat.app.AppCompatActivity;

import com.example.powerslave.MainActivity;
import com.example.powerslave.R;
import com.example.powerslave.ministry.Ministry;
import com.example.powerslave.ministry.MinistryOfEconomy;
import com.example.powerslave.ministry.MinistryOfEducation;
import com.example.powerslave.ministry.MinistryOfHealthcare;
import com.example.powerslave.person.Minister;
import com.example.powerslave.person.Sex;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

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

    private Context context;

    //Static arraylist of all game countries
    public static final ArrayList<Country> countries = new ArrayList<>();

    private final Minister vacant = new Minister("Vacant", "position", Sex.MALE, continent, Ideology.Plycism, 0, 0);

    private Map<String, Ministry> ministries = new HashMap<>();

    private Military military;

    public class Military {
        private int arm_power;
        private int nav_power;
        private int air_power;
        private boolean conscription;
        private boolean landlocked;
        private int outlay;

        public Military(int key) {
            this.arm_power = context.getResources().getIntArray(R.array.army)[key];
            this.nav_power = context.getResources().getIntArray(R.array.navy)[key];
            this.air_power = context.getResources().getIntArray(R.array.air_force)[key];
            this.conscription = Boolean.parseBoolean(context.getResources().getStringArray(R.array.conscription)[key]);
            this.landlocked = Boolean.parseBoolean(context.getResources().getStringArray(R.array.landlocked)[key]);
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
            string += "(" + String.format("%.2f", (float) outlay / getMinistryOfEconomy().getGdp() * 100) + "%)" + "\n";
            return string;
        }
    }

    private Government government;

    public Country(int key, Context context) {
        this.key = key;
        this.context = context;
        this.name = context.getResources().getStringArray(R.array.countries)[key];
        this.capitalName = context.getResources().getStringArray(R.array.capitals)[key];
        this.flagSrc = Uri.parse(context.getResources().getStringArray(R.array.flag_src)[key]);
        this.regionalForm = RegionalForm.valueOf(context.getResources().getStringArray(R.array.regional)[key]);
        this.governmentType = GovernmentType.valueOf(context.getResources().getStringArray(R.array.government)[key]);
        this.continent = Continent.valueOf(context.getResources().getStringArray(R.array.location)[key]);
        this.adjective = context.getResources().getStringArray(R.array.adjectives)[key];

        ministries.put("m_health", new MinistryOfHealthcare(key, vacant, context));
        ministries.put("m_education", new MinistryOfEducation(key, vacant, context));
        ministries.put("m_economy", new MinistryOfEconomy(key, vacant, context, this));

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

    public Map<String, Ministry> getMinistries() {
        return ministries;
    }

    public void setMinistries(Map<String, Ministry> ministries) {
        this.ministries = ministries;
    }

    public MinistryOfHealthcare getMinistryOfHealthcare() {
        return (MinistryOfHealthcare) ministries.get("m_health");
    }

    public MinistryOfEducation getMinistryOfEducation() {
        return (MinistryOfEducation) ministries.get("m_education");
    }

    public MinistryOfEconomy getMinistryOfEconomy() {
        return (MinistryOfEconomy) ministries.get("m_economy");
    }

    public String getAdjective() {
        return adjective;
    }

    public void setAdjective(String adjective) {
        this.adjective = adjective;
    }
}