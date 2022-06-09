package com.example.powerslave;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;
import android.widget.Toast;

import com.example.powerslave.government.Country;
import com.example.powerslave.government.Ideology;
import com.example.powerslave.ministry.Ministry;
import com.example.powerslave.ministry.MinistryOfHealthcare;
import com.example.powerslave.person.Minister;

public class MinistryActivity extends AppCompatActivity implements View.OnClickListener {

    private Country country = MainGameMenuActivity.country;
    private LinearLayout ministerSetLayout;
    private Spinner spinnerChooseMinister;
    private ImageView ministerPortrait;
    private TextView textMinister;
    private TextView textMinistry;
    private Button buttonConfirmMinister;
    private Ministry ministry;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ministry);

        ministerSetLayout = findViewById(R.id.ministerSetLayout);
        spinnerChooseMinister = findViewById(R.id.spinnerChooseMinister);
        ministerPortrait = findViewById(R.id.ministerPortrait);
        textMinister = findViewById(R.id.textMinister);
        textMinistry = findViewById(R.id.textMinistry);
        buttonConfirmMinister = findViewById(R.id.buttonConfirmMinister);
        buttonConfirmMinister.setOnClickListener(this);

        ArrayAdapter<Minister> ministerAdapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, country.getPossibleMinisters());
        spinnerChooseMinister.setAdapter(ministerAdapter);

        if (MainGameMenuActivity.selectedMinistry == 0) {
            ministry = country.getMinistryOfHealthcare();
        } else if (MainGameMenuActivity.selectedMinistry == 1) {
            ministry = country.getMinistryOfEducation();
        } else if (MainGameMenuActivity.selectedMinistry == 2) {
            ministry = country.getMinistryOfEconomy();
        }


        textMinister.setText(ministry.ministerToStringShort());
        textMinistry.setText(ministry.toString());
        ministerPortrait.setImageURI(ministry.getMinister().getPortrait());

        spinnerChooseMinister.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
            }
        });
    }


    @Override
    public void onBackPressed() {
        MinistryActivity.this.finish();
    }

    @Override
    public void onClick(View view) {
        if (view.getId() == buttonConfirmMinister.getId()) {
            DialogInterface.OnClickListener dialogMinister = new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialogInterface, int i) {
                    switch (i) {
                        case Dialog.BUTTON_POSITIVE:
                            ministry.setMinister(MainGameMenuActivity.country.getPossibleMinisters().get(spinnerChooseMinister.getSelectedItemPosition()));
                            MainGameMenuActivity.country.getPossibleMinisters().remove(spinnerChooseMinister.getSelectedItemPosition());
                            Toast.makeText(MinistryActivity.this, "Minister was selected", Toast.LENGTH_SHORT).show();
                            MinistryActivity.this.recreate();
                            break;
                    }
                }
            };

            AlertDialog.Builder builder = new AlertDialog.Builder(MinistryActivity.this);
            builder.setMessage("Are you sure you want to appoint this minister?")
                    .setPositiveButton("Yes", dialogMinister)
                    .setNegativeButton("No", dialogMinister);
            builder.show();
        }
    }
}

