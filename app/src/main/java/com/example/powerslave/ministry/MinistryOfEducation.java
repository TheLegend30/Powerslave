package com.example.powerslave.ministry;

import android.content.Context;

import com.example.powerslave.R;
import com.example.powerslave.government.Country;
import com.example.powerslave.person.Minister;

public class MinistryOfEducation extends Ministry{

    private int teachers;
    private int teachers_salary;
    private int teachers_salary_need;

    private int children_students;

    private int schools_colleges;

    private float literacy;
    private float liberty;

    public MinistryOfEducation(int countryKey, Minister minister, Context context, Country country) {
        super(countryKey, minister, context, country);
        name = "Ministry of Education and Science";

        literacy = Float.parseFloat(context.getResources().getStringArray(R.array.literacy)[countryKey]);
//        liberty = Float.parseFloat(context.getResources().getStringArray(R.array.liberty)[countryKey]);
    }

    public float getLiteracy() {
        return literacy;
    }

    public String getLiteracyString() {
        return literacy + " %";
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
        return super.toString() + "\n" +
                "Literacy: " + getLiteracyString();
    }
}
