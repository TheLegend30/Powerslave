package com.example.powerslave.ministry;

import android.content.Context;

import com.example.powerslave.R;
import com.example.powerslave.government.Country;
import com.example.powerslave.person.Minister;

import java.util.Random;

public class MinistryOfEducation extends Ministry {

    private int teachers;
    private int teachers_limit;

    private float teachers_salary;
    private float teachers_salary_need;

    private int teachers_need;

    private int children;
    private int students;

    private int schools;
    private int schools_limit;
    private int schools_need;

    private int colleges;
    private int colleges_limit;
    private int colleges_need;

    private float literacy;
    private float degree;
    private float liberty = 0;

    private MinistryOfEconomy economy;

    public MinistryOfEducation(int countryKey, Minister minister, Context context, Country country) {
        super(countryKey, minister, context, country);
        name = "Ministry of Education and Science";

        this.economy = country.getMinistryOfEconomy();

        this.children = (int) (this.economy.getPopulation() - this.economy.getLabor_force() - country.getMinistryOfHealthcare().getPensioners());

        statsRandomizer();
    }

    public float getLiteracy() {
        return literacy;
    }

    public String getLiteracyString() {
        return (literacy * 100f) + " %";
    }

    public void setLiteracy(float literacy) {
        this.literacy = literacy;
    }

    public float getLiberty() {
        return liberty;
    }

    public void setLiberty(float liberty) {
        this.liberty = liberty;
    }

    @Override
    public String toString() {
        String string;
        string = "Literacy: " + getLiteracyString() + "\n";
        string += "Degree: " + (degree * 100f) + " %" + "\n";
        string += "Liberty: " + liberty + " %" + "\n";
        string += "Teachers: " + this.teachers + "\n";
        string += "Teachers limit: " + this.teachers_limit + "\n";
        string += "Teachers salary: " + this.teachers_salary + " ƒ" + "\n";
        string += "Teachers need salary: " + this.teachers_salary_need + " ƒ" + "\n";
        string += "Children: " + this.children + "\n";
        string += "Students: " + this.students + "\n";
        string += "Schools: " + this.schools + "\n";
        string += "Colleges: " + this.colleges + "\n";
        string += "Schools need: " + this.schools_need + "\n";
        string += "Colleges need: " + this.colleges_need + "\n";
        string += "Teachers need: " + this.teachers_need + "\n";
        return super.toString() + "\n" + string;
    }

    @Override
    public void updateMinistry() {
        super.updateMinistry();
        this.efficiency *= ((float) (schools + colleges) / (float) (schools_need + colleges_need));
        this.efficiency *= ((float) teachers / (float) teachers_need);
        this.efficiency *= (teachers_salary / teachers_salary_need);
    }

    @Override
    public void statsRandomizer() {
        super.statsRandomizer();

        Random random = new Random();

        float modifier_teachers = 0f;
        float modifier_teachers_salary = 0f;

        float modifier_students = 0f;

        float modifier_schools = 0f;
        float modifier_colleges = 0f;

        float modifier_literacy = 0f;
        float modifier_degree = 0f;

        switch (this.country.getContinent()) {
            case EY:
                modifier_teachers = 0.013f;
                modifier_teachers_salary = 0.5f;

                modifier_students = 0.03f;

                modifier_schools = 0.8f;
                modifier_colleges = 0.65f;

                modifier_literacy = 0.8f;
                modifier_degree = 0.08f;
                break;
            case NY:
                modifier_teachers = 0.017f;
                modifier_teachers_salary = 0.58f;

                modifier_students = 0.038f;

                modifier_schools = 0.9f;
                modifier_colleges = 0.75f;

                modifier_literacy = 0.9f;
                modifier_degree = 0.11f;
                break;
            case SY:
                modifier_teachers = 0.01f;
                modifier_teachers_salary = 0.45f;

                modifier_students = 0.025f;

                modifier_schools = 0.75f;
                modifier_colleges = 0.6f;

                modifier_literacy = 0.65f;
                modifier_degree = 0.07f;
                break;
            case WY:
                modifier_teachers = 0.02f;
                modifier_teachers_salary = 0.64f;

                modifier_students = 0.04f;

                modifier_schools = 0.92f;
                modifier_colleges = 0.8f;

                modifier_literacy = 0.95f;
                modifier_degree = 0.14f;
                break;
            case IB:
                modifier_teachers = 0.008f;
                modifier_teachers_salary = 0.4f;

                modifier_students = 0.022f;

                modifier_schools = 0.6f;
                modifier_colleges = 0.45f;

                modifier_literacy = 0.5f;
                modifier_degree = 0.025f;
                break;
            case OB:
                modifier_teachers = 0.009f;
                modifier_teachers_salary = 0.38f;

                modifier_students = 0.02f;

                modifier_schools = 0.55f;
                modifier_colleges = 0.4f;

                modifier_literacy = 0.6f;
                modifier_degree = 0.02f;
                break;
            case CA:
                modifier_teachers = 0.0095f;
                modifier_teachers_salary = 0.35f;

                modifier_students = 0.018f;

                modifier_schools = 0.5f;
                modifier_colleges = 0.38f;

                modifier_literacy = 0.4f;
                modifier_degree = 0.01f;
                break;
            case FA:
                modifier_teachers = 0.008f;
                modifier_teachers_salary = 0.3f;

                modifier_students = 0.017f;

                modifier_schools = 0.45f;
                modifier_colleges = 0.25f;

                modifier_literacy = 0.42f;
                modifier_degree = 0.015f;
                break;
            case ME:
                modifier_teachers = 0.0075f;
                modifier_teachers_salary = 0.24f;

                modifier_students = 0.015f;

                modifier_schools = 0.35f;
                modifier_colleges = 0.15f;

                modifier_literacy = 0.25f;
                modifier_degree = 0.01f;
                break;
            case GE:
                modifier_teachers = 0.018f;
                modifier_teachers_salary = 0.53f;

                modifier_students = 0.033f;

                modifier_schools = 0.85f;
                modifier_colleges = 0.75f;

                modifier_literacy = 0.85f;
                modifier_degree = 0.09f;
                break;
        }

        this.teachers_limit = (int) (this.economy.getLabor_force() * (modifier_teachers + (random.nextFloat() * (0.003 - (-0.003)) + (-0.003))));
        this.teachers = this.teachers_limit;

        this.teachers_salary = (float) (this.economy.getGdp_per_person() * (modifier_teachers_salary + (random.nextFloat() * (0.01 - (-0.01)) + (-0.01))));

        this.students = (int) (this.economy.getLabor_force() * (modifier_students + (random.nextFloat() * (0.005 - (-0.005)) + (-0.005))));

        this.teachers_salary_need = (float) (this.economy.getGdp_per_person() / 1.5);
        this.schools_need = (int) Math.ceil(children / 500);
        this.colleges_need = (int) Math.ceil(students / 1250);
        this.teachers_need = (children + students) / 35;

        this.schools_limit = (int) (this.schools_need * (modifier_schools + (random.nextFloat() * (0.01 - (-0.01)) + (-0.01))));
        this.schools = this.schools_limit;

        this.colleges_limit = (int) (this.colleges_need * (modifier_colleges + (random.nextFloat() * (0.01 - (-0.01)) + (-0.01))));
        this.colleges = this.colleges_limit;

        this.literacy = (float) (modifier_literacy + (random.nextFloat() * (0.01 - (-0.01)) + (-0.01)));
        this.degree = (float) (modifier_degree + (random.nextFloat() * (0.01 - (-0.01)) + (-0.01)));
    }
}
