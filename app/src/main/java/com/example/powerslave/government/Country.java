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
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

public class Country implements Comparable<Country>{
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
    public static ArrayList<Country> countries;

    public final Minister vacant;
    private ArrayList<Minister> possibleMinisters;

    private Map<String, Ministry> ministries = new HashMap<>();

    private Military military;

    @Override
    public int compareTo(Country country) {
        return this.getName().compareTo(country.getName());
    }

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
        vacant =  new Minister("Vacant", "position", Sex.MALE, this, Ideology.Plycism, 0, 0, Uri.parse(context.getResources().getString(R.string.anon)));
        ministries.put("m_health", new MinistryOfHealthcare(key, vacant, context));
        ministries.put("m_education", new MinistryOfEducation(key, vacant, context));
        ministries.put("m_economy", new MinistryOfEconomy(key, vacant, context, this));
        initialFirstMinisterAppointment();
        updateMinisters();

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

    public ArrayList<Minister> getPossibleMinisters() {
        return possibleMinisters;
    }

    public void setPossibleMinisters(ArrayList<Minister> possibleMinisters) {
        this.possibleMinisters = possibleMinisters;
    }

    public static void initCountries(Context context) {
        int length = context.getResources().getStringArray(R.array.countries).length;
        countries = new ArrayList<>();
        for (int i = 0; i < length; i++) {
            countries.add(new Country(i, context));
        }
        Collections.sort(Country.countries);
    }

    public Minister randomMinister() {
        Random random = new Random();

        String name = null, surname = null;
        Sex sex = Sex.values()[random.nextInt(Sex.values().length)];
        Uri portrait = null;

        if (sex == Sex.MALE) {
            switch (this.getContinent()) {
                case EY:
                    name = context.getResources().getStringArray(R.array.eyut_names_m)[random.nextInt(context.getResources().getStringArray(R.array.eyut_names_m).length)];
                    surname = context.getResources().getStringArray(R.array.eyut_surnames)[random.nextInt(context.getResources().getStringArray(R.array.eyut_surnames).length)];
                    portrait = Uri.parse(context.getResources().getStringArray(R.array.ym)[random.nextInt(context.getResources().getStringArray(R.array.ym).length)]);
                    break;
                case NY:
                    name = context.getResources().getStringArray(R.array.nyut_names_m)[random.nextInt(context.getResources().getStringArray(R.array.nyut_names_m).length)];
                    surname = context.getResources().getStringArray(R.array.nyut_surnames)[random.nextInt(context.getResources().getStringArray(R.array.nyut_surnames).length)];
                    portrait = Uri.parse(context.getResources().getStringArray(R.array.ym)[random.nextInt(context.getResources().getStringArray(R.array.ym).length)]);
                    break;
                case SY:
                    name = context.getResources().getStringArray(R.array.syut_names_m)[random.nextInt(context.getResources().getStringArray(R.array.syut_names_m).length)];
                    surname = context.getResources().getStringArray(R.array.syut_surnames)[random.nextInt(context.getResources().getStringArray(R.array.syut_surnames).length)];
                    portrait = Uri.parse(context.getResources().getStringArray(R.array.ym)[random.nextInt(context.getResources().getStringArray(R.array.ym).length)]);
                    break;
                case WY:
                    name = context.getResources().getStringArray(R.array.wyut_names_m)[random.nextInt(context.getResources().getStringArray(R.array.wyut_names_m).length)];
                    surname = context.getResources().getStringArray(R.array.wyut_surnames)[random.nextInt(context.getResources().getStringArray(R.array.wyut_surnames).length)];
                    portrait = Uri.parse(context.getResources().getStringArray(R.array.ym)[random.nextInt(context.getResources().getStringArray(R.array.ym).length)]);
                    break;
                case IB:
                    name = context.getResources().getStringArray(R.array.ibru_names_m)[random.nextInt(context.getResources().getStringArray(R.array.ibru_names_m).length)];
                    surname = context.getResources().getStringArray(R.array.ibru_surnames)[random.nextInt(context.getResources().getStringArray(R.array.ibru_surnames).length)];
                    portrait = Uri.parse(context.getResources().getStringArray(R.array.bm)[random.nextInt(context.getResources().getStringArray(R.array.bm).length)]);
                    break;
                case OB:
                    name = context.getResources().getStringArray(R.array.obru_names_m)[random.nextInt(context.getResources().getStringArray(R.array.obru_names_m).length)];
                    surname = context.getResources().getStringArray(R.array.obru_surnames)[random.nextInt(context.getResources().getStringArray(R.array.obru_surnames).length)];
                    portrait = Uri.parse(context.getResources().getStringArray(R.array.bm)[random.nextInt(context.getResources().getStringArray(R.array.bm).length)]);
                    break;
                case CA:
                    name = context.getResources().getStringArray(R.array.cadu_names_m)[random.nextInt(context.getResources().getStringArray(R.array.cadu_names_m).length)];
                    surname = context.getResources().getStringArray(R.array.cadu_surnames)[random.nextInt(context.getResources().getStringArray(R.array.cadu_surnames).length)];
                    portrait = Uri.parse(context.getResources().getStringArray(R.array.am)[random.nextInt(context.getResources().getStringArray(R.array.am).length)]);
                    break;
                case FA:
                    name = context.getResources().getStringArray(R.array.fadu_names_m)[random.nextInt(context.getResources().getStringArray(R.array.fadu_names_m).length)];
                    surname = context.getResources().getStringArray(R.array.fadu_surnames)[random.nextInt(context.getResources().getStringArray(R.array.fadu_surnames).length)];
                    portrait = Uri.parse(context.getResources().getStringArray(R.array.am)[random.nextInt(context.getResources().getStringArray(R.array.am).length)]);
                    break;
                case ME:
                    name = context.getResources().getStringArray(R.array.mehe_names_m)[random.nextInt(context.getResources().getStringArray(R.array.mehe_names_m).length)];
                    surname = context.getResources().getStringArray(R.array.mehe_surnames)[random.nextInt(context.getResources().getStringArray(R.array.mehe_surnames).length)];
                    portrait = Uri.parse(context.getResources().getStringArray(R.array.mm)[random.nextInt(context.getResources().getStringArray(R.array.mm).length)]);
                    break;
                case GE:
                    name = context.getResources().getStringArray(R.array.gewi_names_m)[random.nextInt(context.getResources().getStringArray(R.array.gewi_names_m).length)];
                    surname = context.getResources().getStringArray(R.array.gewi_surnames)[random.nextInt(context.getResources().getStringArray(R.array.gewi_surnames).length)];
                    portrait = Uri.parse(context.getResources().getStringArray(R.array.mf)[random.nextInt(context.getResources().getStringArray(R.array.mf).length)]);
                    break;
            }
        } else {
            switch (this.getContinent()) {
                case EY:
                    name = context.getResources().getStringArray(R.array.eyut_names_f)[random.nextInt(context.getResources().getStringArray(R.array.eyut_names_f).length)];
                    surname = context.getResources().getStringArray(R.array.eyut_surnames)[random.nextInt(context.getResources().getStringArray(R.array.eyut_surnames).length)];
                    portrait = Uri.parse(context.getResources().getStringArray(R.array.yf)[random.nextInt(context.getResources().getStringArray(R.array.yf).length)]);
                    break;
                case NY:
                    name = context.getResources().getStringArray(R.array.nyut_names_f)[random.nextInt(context.getResources().getStringArray(R.array.nyut_names_f).length)];
                    surname = context.getResources().getStringArray(R.array.nyut_surnames)[random.nextInt(context.getResources().getStringArray(R.array.nyut_surnames).length)];
                    portrait = Uri.parse(context.getResources().getStringArray(R.array.yf)[random.nextInt(context.getResources().getStringArray(R.array.yf).length)]);
                    break;
                case SY:
                    name = context.getResources().getStringArray(R.array.syut_names_f)[random.nextInt(context.getResources().getStringArray(R.array.syut_names_f).length)];
                    surname = context.getResources().getStringArray(R.array.syut_surnames)[random.nextInt(context.getResources().getStringArray(R.array.syut_surnames).length)];
                    portrait = Uri.parse(context.getResources().getStringArray(R.array.yf)[random.nextInt(context.getResources().getStringArray(R.array.yf).length)]);
                    break;
                case WY:
                    name = context.getResources().getStringArray(R.array.wyut_names_f)[random.nextInt(context.getResources().getStringArray(R.array.wyut_names_f).length)];
                    surname = context.getResources().getStringArray(R.array.wyut_surnames)[random.nextInt(context.getResources().getStringArray(R.array.wyut_surnames).length)];
                    portrait = Uri.parse(context.getResources().getStringArray(R.array.yf)[random.nextInt(context.getResources().getStringArray(R.array.yf).length)]);
                    break;
                case IB:
                    name = context.getResources().getStringArray(R.array.ibru_names_f)[random.nextInt(context.getResources().getStringArray(R.array.ibru_names_f).length)];
                    surname = context.getResources().getStringArray(R.array.ibru_surnames)[random.nextInt(context.getResources().getStringArray(R.array.ibru_surnames).length)];
                    portrait = Uri.parse(context.getResources().getStringArray(R.array.bf)[random.nextInt(context.getResources().getStringArray(R.array.bf).length)]);
                    break;
                case OB:
                    name = context.getResources().getStringArray(R.array.obru_names_f)[random.nextInt(context.getResources().getStringArray(R.array.obru_names_f).length)];
                    surname = context.getResources().getStringArray(R.array.obru_surnames)[random.nextInt(context.getResources().getStringArray(R.array.obru_surnames).length)];
                    portrait = Uri.parse(context.getResources().getStringArray(R.array.bf)[random.nextInt(context.getResources().getStringArray(R.array.bf).length)]);
                    break;
                case CA:
                    name = context.getResources().getStringArray(R.array.cadu_names_f)[random.nextInt(context.getResources().getStringArray(R.array.cadu_names_f).length)];
                    surname = context.getResources().getStringArray(R.array.cadu_surnames)[random.nextInt(context.getResources().getStringArray(R.array.cadu_surnames).length)];
                    portrait = Uri.parse(context.getResources().getStringArray(R.array.af)[random.nextInt(context.getResources().getStringArray(R.array.af).length)]);
                    break;
                case FA:
                    name = context.getResources().getStringArray(R.array.fadu_names_f)[random.nextInt(context.getResources().getStringArray(R.array.fadu_names_f).length)];
                    surname = context.getResources().getStringArray(R.array.fadu_surnames)[random.nextInt(context.getResources().getStringArray(R.array.fadu_surnames).length)];
                    portrait = Uri.parse(context.getResources().getStringArray(R.array.af)[random.nextInt(context.getResources().getStringArray(R.array.af).length)]);
                    break;
                case ME:
                    name = context.getResources().getStringArray(R.array.mehe_names_f)[random.nextInt(context.getResources().getStringArray(R.array.mehe_names_f).length)];
                    surname = context.getResources().getStringArray(R.array.mehe_surnames)[random.nextInt(context.getResources().getStringArray(R.array.mehe_surnames).length)];
                    portrait = Uri.parse(context.getResources().getStringArray(R.array.mf)[random.nextInt(context.getResources().getStringArray(R.array.mf).length)]);
                    break;
                case GE:
                    name = context.getResources().getStringArray(R.array.gewi_names_f)[random.nextInt(context.getResources().getStringArray(R.array.gewi_names_f).length)];
                    surname = context.getResources().getStringArray(R.array.gewi_surnames)[random.nextInt(context.getResources().getStringArray(R.array.gewi_surnames).length)];
                    portrait = Uri.parse(context.getResources().getStringArray(R.array.gf)[random.nextInt(context.getResources().getStringArray(R.array.gf).length)]);
                    break;
            }
        }

        Ideology ideology = Ideology.values()[random.nextInt(Ideology.values().length)];
        float loyalty = (float) random.nextInt(100);
        float competency = (float) random.nextInt(100);

        Minister minister = new Minister(name, surname, sex, this, ideology, loyalty, competency, portrait);
        return minister;
    }

    public void initialFirstMinisterAppointment() {
        for (Ministry ministry : getMinistries().values()) {
            ministry.setMinister(randomMinister());
        }
    }

    public void addMinisterExperience() {
        for (Ministry ministry : getMinistries().values()) {
            if (!ministry.getMinister().equals(vacant)) {
                float comp = ministry.getMinister().getCompetency() + 0.35f;
                comp = comp * 100f;
                comp = Math.round(comp);
                comp = comp / 100f;
                if (comp > 100f) comp = 100f;
                ministry.getMinister().setCompetency(comp);
            }
        }
    }

    public void updateMinisters() {
        possibleMinisters = new ArrayList<>();
        for (int i = 0; i < 20; i++) {
            possibleMinisters.add(this.randomMinister());
        }
        Collections.sort(possibleMinisters);
    }


}
