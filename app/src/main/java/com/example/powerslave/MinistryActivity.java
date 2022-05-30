package com.example.powerslave;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

import com.example.powerslave.government.Country;
import com.example.powerslave.ministry.Ministry;

public class MinistryActivity extends AppCompatActivity {

    private Country country = MainGameMenuActivity.country;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ministry);

        TextView textView = new TextView(this);

        if (MainGameMenuActivity.selectedMinistry == 0) {
            textView.setText(country.getMinistryOfHealthcare().toString());
        } else if (MainGameMenuActivity.selectedMinistry == 1) {
            textView.setText(country.getMinistryOfEducation().toString());
        } else if (MainGameMenuActivity.selectedMinistry == 2) {
            textView.setText(country.getMinistryOfEconomy().toString());
        }

        LinearLayout ll = findViewById(R.id.textLayoutMinistry);
        LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);
        ll.addView(textView, lp);
    }


    @Override
    public void onBackPressed() {
        MinistryActivity.this.finish();
    }
}

