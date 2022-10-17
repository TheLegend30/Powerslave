package com.example.powerslave.government;

import android.content.Context;
import android.content.Entity;
import android.net.Uri;

import androidx.appcompat.app.AppCompatActivity;

import com.example.powerslave.MainActivity;
import com.example.powerslave.R;
import com.example.powerslave.ministry.Ministry;
import com.example.powerslave.ministry.MinistryOfAgriculture;
import com.example.powerslave.ministry.MinistryOfCulture;
import com.example.powerslave.ministry.MinistryOfDefense;
import com.example.powerslave.ministry.MinistryOfDevelopment;
import com.example.powerslave.ministry.MinistryOfEconomy;
import com.example.powerslave.ministry.MinistryOfEducation;
import com.example.powerslave.ministry.MinistryOfForeignAffairs;
import com.example.powerslave.ministry.MinistryOfHealthcare;
import com.example.powerslave.ministry.MinistryOfIndustry;
import com.example.powerslave.ministry.MinistryOfInternalAffairs;
import com.example.powerslave.ministry.MinistryOfJustice;
import com.example.powerslave.ministry.Parliament;
import com.example.powerslave.person.Minister;
import com.example.powerslave.person.Ruler;
import com.example.powerslave.person.Sex;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

public class Country implements Comparable<Country> {
    private int key;

    private String name;
    private String capitalName;
    private Uri flagSrc;
    private RegionalForm regionalForm;
    private GovernmentType governmentType;
    private String adjective;
    private Continent continent;

    private Context context;

    //Static arraylist of all game countries
    public static ArrayList<Country> countries;

    private Ruler ruler;

    public final Minister vacant;
    private ArrayList<Minister> possibleMinisters;

    private Map<String, Ministry> ministries = new HashMap<>();

    public void oneMove() {
        updateMinistries();
    }

    @Override
    public int compareTo(Country country) {
        return this.getName().compareTo(country.getName());
    }

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
        this.ruler = randomRuler();

        vacant = new Minister("Vacant", "position", Sex.MALE, this, Ideology.Plycism, 0, 0, Uri.parse(context.getResources().getString(R.string.anon)));
        ministries.put("m_economy", new MinistryOfEconomy(key, vacant, context, this));
        ministries.put("m_health", new MinistryOfHealthcare(key, vacant, context, this));
        ministries.put("m_education", new MinistryOfEducation(key, vacant, context, this));
        ministries.put("m_defense", new MinistryOfDefense(key, vacant, context, this));
        ministries.put("m_agriculture", new MinistryOfAgriculture(key, vacant, context, this));
        ministries.put("m_industry", new MinistryOfIndustry(key, vacant, context, this));
        ministries.put("m_development", new MinistryOfDevelopment(key, vacant, context, this));
        ministries.put("m_foreign", new MinistryOfForeignAffairs(key, vacant, context, this));
        ministries.put("m_culture", new MinistryOfCulture(key, vacant, context, this));
        ministries.put("m_internal", new MinistryOfInternalAffairs(key, vacant, context, this));
        ministries.put("m_justice", new MinistryOfJustice(key, vacant, context, this));
        ministries.put("m_parliament", new Parliament(key, vacant, context, this));
        initialFirstMinisterAppointment();
        updateMinisters();
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

    public MinistryOfDefense getMinistryOfDefense() {
        return (MinistryOfDefense) ministries.get("m_defense");
    }

    public MinistryOfAgriculture getMinistryOfAgriculture() {
        return (MinistryOfAgriculture) ministries.get("m_agriculture");
    }

    public MinistryOfIndustry getMinistryOfIndustry() {
        return (MinistryOfIndustry) ministries.get("m_industry");
    }

    public MinistryOfDevelopment getMinistryOfDevelopment() {
        return (MinistryOfDevelopment) ministries.get("m_development");
    }

    public MinistryOfForeignAffairs getMinistryOfForeignAffairs() {
        return (MinistryOfForeignAffairs) ministries.get("m_foreign");
    }

    public MinistryOfCulture getMinistryOfCulture() {
        return (MinistryOfCulture) ministries.get("m_culture");
    }

    public MinistryOfInternalAffairs getMinistryOfInternalAffairs() {
        return (MinistryOfInternalAffairs) ministries.get("m_internal");
    }

    public MinistryOfJustice getMinistryOfJustice() {
        return (MinistryOfJustice) ministries.get("m_justice");
    }

    public Parliament getParliament() {
        return (Parliament) ministries.get("m_parliament");
    }

    public String getAdjective() {
        return adjective;
    }

    public void setAdjective(String adjective) {
        this.adjective = adjective;
    }

    public Ruler getRuler() {
        return ruler;
    }

    public void setRuler(Ruler ruler) {
        this.ruler = ruler;
        updateMinistries();
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
        for (Country c : countries) {
            c.updateMinistries();
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

    public Ruler randomRuler() {
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

        Ruler ruler = new Ruler(name, surname, sex, this, ideology, portrait);
        return ruler;
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
        for (int i = 0; i < 10; i++) {
            possibleMinisters.add(this.randomMinister());
        }
        Collections.sort(possibleMinisters);
    }

    public void updateMinistries() {
        for (Map.Entry<String, Ministry> ministry : ministries.entrySet()) {
            ministry.getValue().updateMinistry();
        }
    }


}
