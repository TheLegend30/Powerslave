package com.example.powerslave.ministry;

import android.content.Context;

import com.example.powerslave.R;
import com.example.powerslave.person.Minister;

public class MinistryOfEducation extends Ministry{

    private float literacy;

    public MinistryOfEducation(int countryKey, Minister minister, Context context) {
        super(countryKey, minister, context);
        name = "Ministry of Education and Science";
        literacy = Float.parseFloat(context.getResources().getStringArray(R.array.literacy)[countryKey]);
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

    @Override
    public String toString() {
        return super.toString() + "\n" +
                "Literacy: " + getLiteracyString();
    }
}
