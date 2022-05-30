package com.example.powerslave.ministry;

import android.content.Context;
import android.content.res.Resources;

import androidx.appcompat.app.AppCompatActivity;

import com.example.powerslave.MainActivity;
import com.example.powerslave.person.Minister;

public abstract class Ministry {
    protected int countryKey;
    protected String name;
    protected Minister minister;
    protected Context context;
    protected Ministry(int countryKey, Minister minister, Context context) {
        this.countryKey = countryKey;
        this.minister = minister;
        this.context = context;
    }

    @Override
    public String toString() {
        return name + "\n\n" +
                "Minister: " + minister.getName() + " " + minister.getSurname();
    }
}
