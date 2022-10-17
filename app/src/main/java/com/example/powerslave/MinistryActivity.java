package com.example.powerslave;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.text.InputType;
import android.text.TextUtils;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.powerslave.government.Country;
import com.example.powerslave.ministry.Ministry;
import com.example.powerslave.ministry.MinistryOfForeignAffairs;
import com.example.powerslave.person.Minister;
import com.example.powerslave.person.Sex;
import com.google.android.material.slider.Slider;

public class MinistryActivity extends AppCompatActivity implements View.OnClickListener {

    private Country country = MainGameMenuActivity.country;
    private LinearLayout ministerSetLayout;
    private LinearLayout buttonLayout;
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
        buttonLayout = findViewById(R.id.buttonLayout);
        spinnerChooseMinister = findViewById(R.id.spinnerChooseMinister);
        ministerPortrait = findViewById(R.id.ministerPortrait);
        textMinister = findViewById(R.id.textMinister);
        textMinistry = findViewById(R.id.textMinistry);
        buttonConfirmMinister = findViewById(R.id.buttonConfirmMinister);
        buttonConfirmMinister.setOnClickListener(this);

        ArrayAdapter<Minister> ministerAdapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item, country.getPossibleMinisters());
        spinnerChooseMinister.setAdapter(ministerAdapter);

        if (MainGameMenuActivity.selectedMinistry == 0) {
            ministry = country.getMinistryOfHealthcare();
        } else if (MainGameMenuActivity.selectedMinistry == 1) {
            ministry = country.getMinistryOfEducation();
        } else if (MainGameMenuActivity.selectedMinistry == 2) {
            ministry = country.getMinistryOfEconomy();
        } else if (MainGameMenuActivity.selectedMinistry == 3) {
            ministry = country.getMinistryOfDefense();
        } else if (MainGameMenuActivity.selectedMinistry == 4) {
            ministry = country.getMinistryOfAgriculture();
        } else if (MainGameMenuActivity.selectedMinistry == 5) {
            ministry = country.getMinistryOfDevelopment();
        } else if (MainGameMenuActivity.selectedMinistry == 6) {
            ministry = country.getMinistryOfIndustry();
        } else if (MainGameMenuActivity.selectedMinistry == 8) {
            ministry = country.getMinistryOfCulture();
        } else if (MainGameMenuActivity.selectedMinistry == 9) {
            ministry = country.getMinistryOfInternalAffairs();
        } else if (MainGameMenuActivity.selectedMinistry == 10) {
            ministry = country.getMinistryOfJustice();
        } else if (MainGameMenuActivity.selectedMinistry == 11) {
            ministry = country.getParliament();
        } else if (MainGameMenuActivity.selectedMinistry == 13) {
            ministry = country.getMinistryOfForeignAffairs();

            MinistryOfForeignAffairs foreignAffairs = (MinistryOfForeignAffairs) ministry;

            EditText diplomatsLimitEdit = new EditText(this);
            diplomatsLimitEdit.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT));
            diplomatsLimitEdit.setInputType(InputType.TYPE_CLASS_NUMBER);
            diplomatsLimitEdit.setHint("Enter limit of diplomats");

            Button buttonConfirmDiplomatsLimitEdit = new Button(this);
            buttonConfirmDiplomatsLimitEdit.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT));
            buttonConfirmDiplomatsLimitEdit.setText("Confirm limit");
            buttonConfirmDiplomatsLimitEdit.setOnClickListener(view -> {
                if (TextUtils.isEmpty(diplomatsLimitEdit.getText())) {
                    Toast.makeText(MinistryActivity.this, "You need to enter something", Toast.LENGTH_SHORT).show();
                } else {
                    Integer limit = Integer.parseInt(String.valueOf(diplomatsLimitEdit.getText()));
                    if (limit > foreignAffairs.getMaximumDiplomatsLimit() || limit < 0) {
                        Toast.makeText(MinistryActivity.this, ("Have you lost your mind, " + (country.getRuler().getSex() == Sex.MALE ? "sir?" : "ma'am?")), Toast.LENGTH_SHORT).show();
                    } else {
                        foreignAffairs.setDiplomatsLimit(limit);
                        MinistryActivity.this.recreate();
                    }
                }
            });

            EditText diplomatsSalaryEdit = new EditText(this);
            diplomatsSalaryEdit.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT));
            diplomatsSalaryEdit.setInputType(InputType.TYPE_CLASS_NUMBER);
            diplomatsSalaryEdit.setHint("Enter salary of diplomats");

            Button buttonConfirmDiplomatsSalaryEdit = new Button(this);
            buttonConfirmDiplomatsSalaryEdit.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT));
            buttonConfirmDiplomatsSalaryEdit.setText("Confirm salary");
            buttonConfirmDiplomatsSalaryEdit.setOnClickListener(view -> {
                if (TextUtils.isEmpty(diplomatsSalaryEdit.getText())) {
                    Toast.makeText(MinistryActivity.this, "You need to enter something", Toast.LENGTH_SHORT).show();
                } else {
                    Float salary = Float.parseFloat(String.valueOf(diplomatsSalaryEdit.getText()));
                    if (salary > (foreignAffairs.getDiplomatsSalaryNeed() * 10) || salary < 0) {
                        Toast.makeText(MinistryActivity.this, ("Have you lost your mind, " + (country.getRuler().getSex() == Sex.MALE ? "sir?" : "ma'am?")), Toast.LENGTH_SHORT).show();
                    } else {
                        foreignAffairs.setDiplomatsSalary(salary);
                        MinistryActivity.this.recreate();
                    }
                }
            });

            Spinner relationsSpinner = new Spinner(this);
            Slider diplomatsSlider = new Slider(this);
            diplomatsSlider.setValueFrom(0);
            diplomatsSlider.setValueFrom(foreignAffairs.getDiplomats());
            TextView textCurrentCountryRelations = new TextView(this);

            ArrayAdapter relationsAdapter = new ArrayAdapter(this, android.R.layout.simple_spinner_dropdown_item, foreignAffairs.countriesRelations);
            relationsSpinner.setAdapter(relationsAdapter);

            relationsSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                @Override
                public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                    textCurrentCountryRelations.setText(foreignAffairs.getCurrentRelations(foreignAffairs.countriesRelations.get(i)));
                }

                @Override
                public void onNothingSelected(AdapterView<?> adapterView) {

                }
            });

            Button buttonMakeTradePartner = new Button(this);
            buttonMakeTradePartner.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT));
            buttonMakeTradePartner.setText("Make trade partner");
            buttonMakeTradePartner.setOnClickListener(view -> {
                if (foreignAffairs.countriesRelations.get(spinnerChooseMinister.getSelectedItemPosition()).getRelation() > 55) {
                    foreignAffairs.countriesRelations.get(relationsSpinner.getSelectedItemPosition()).setTradePartner(true);
                    textCurrentCountryRelations.setText(foreignAffairs.getCurrentRelations(foreignAffairs.countriesRelations.get(relationsSpinner.getSelectedItemPosition())));
                } else {
                    Toast.makeText(MinistryActivity.this, "Too low relationship (At least - 55 points)", Toast.LENGTH_SHORT).show();
                }
            });

            Button buttonMakeAlly = new Button(this);
            buttonMakeAlly.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT));
            buttonMakeAlly.setText("Make ally");
            buttonMakeAlly.setOnClickListener(view -> {
                if (foreignAffairs.countriesRelations.get(spinnerChooseMinister.getSelectedItemPosition()).getRelation() > 85) {
                    foreignAffairs.countriesRelations.get(relationsSpinner.getSelectedItemPosition()).setAlly(true);
                    textCurrentCountryRelations.setText(foreignAffairs.getCurrentRelations(foreignAffairs.countriesRelations.get(relationsSpinner.getSelectedItemPosition())));
                } else {
                    Toast.makeText(MinistryActivity.this, "Too low relationship (At least - 85 points)", Toast.LENGTH_SHORT).show();
                }
            });

            Button buttonDeclareWar = new Button(this);
            buttonDeclareWar.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT));
            buttonDeclareWar.setText("Declare war");
            buttonDeclareWar.setOnClickListener(view -> {
                foreignAffairs.countriesRelations.get(relationsSpinner.getSelectedItemPosition()).setInWar(true);
                foreignAffairs.countriesRelations.get(relationsSpinner.getSelectedItemPosition()).setAlly(false);
                foreignAffairs.countriesRelations.get(relationsSpinner.getSelectedItemPosition()).setTradePartner(false);

                foreignAffairs.countriesRelations.get(relationsSpinner.getSelectedItemPosition()).setRelation(-100);
                textCurrentCountryRelations.setText(foreignAffairs.getCurrentRelations(foreignAffairs.countriesRelations.get(relationsSpinner.getSelectedItemPosition())));
            });

            Slider sliderPutThereDiplomats = new Slider(this);
            sliderPutThereDiplomats.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT));
            sliderPutThereDiplomats.setValueFrom(0);
            sliderPutThereDiplomats.setValueTo(foreignAffairs.getDiplomatsFree());
            sliderPutThereDiplomats.setStepSize(1);

            Button buttonPutThereDiplomats = new Button(this);
            buttonPutThereDiplomats.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT));
            buttonPutThereDiplomats.setText("Put diplomats in " + foreignAffairs.countriesRelations.get(relationsSpinner.getSelectedItemPosition()).getCountry().getName());
            buttonPutThereDiplomats.setOnClickListener(view -> {
                foreignAffairs.countriesRelations.get(relationsSpinner.getSelectedItemPosition()).setDiplomatsThere((int) sliderPutThereDiplomats.getValue() + foreignAffairs.countriesRelations.get(relationsSpinner.getSelectedItemPosition()).getDiplomatsThere());
                foreignAffairs.setDiplomatsFree((int) (foreignAffairs.getDiplomatsFree() - sliderPutThereDiplomats.getValue()));
                MinistryActivity.this.recreate();
            });

            buttonLayout.addView(relationsSpinner);
            buttonLayout.addView(textCurrentCountryRelations);

            buttonLayout.addView(diplomatsLimitEdit);
            buttonLayout.addView(buttonConfirmDiplomatsLimitEdit);

            buttonLayout.addView(diplomatsSalaryEdit);
            buttonLayout.addView(buttonConfirmDiplomatsSalaryEdit);

            buttonLayout.addView(buttonMakeTradePartner);
            buttonLayout.addView(buttonMakeAlly);
            buttonLayout.addView(buttonDeclareWar);

            if (foreignAffairs.getDiplomatsFree() > 0) {
                buttonLayout.addView(sliderPutThereDiplomats);
                buttonLayout.addView(buttonPutThereDiplomats);
            }
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
            DialogInterface.OnClickListener dialogMinister = (dialogInterface, i) -> {
                switch (i) {
                    case Dialog.BUTTON_POSITIVE:
                        if (ministry.getMinister().getLoyalty() >= 0f) {
                            ministry.getMinister().setLoyalty(ministry.getMinister().getLoyalty() - 15f);
                            if (ministry.getMinister().getLoyalty() <= 0f) {
                                ministry.getMinister().setLoyalty(0f);
                            }
                        }
                        Minister previousMinister = ministry.getMinister();
                        Minister newMinister = MainGameMenuActivity.country.getPossibleMinisters().get(spinnerChooseMinister.getSelectedItemPosition());
                        ministry.setMinister(newMinister);
                        MainGameMenuActivity.country.getPossibleMinisters().add(previousMinister);
                        MainGameMenuActivity.country.getPossibleMinisters().remove(spinnerChooseMinister.getSelectedItemPosition());
                        Toast.makeText(MinistryActivity.this, "Minister was selected", Toast.LENGTH_SHORT).show();
                        MinistryActivity.this.recreate();
                        break;
                }
            };

            AlertDialog.Builder builder = new AlertDialog.Builder(MinistryActivity.this);
            Minister tempMinister = MainGameMenuActivity.country.getPossibleMinisters().get(spinnerChooseMinister.getSelectedItemPosition());
            builder.setMessage("Are you sure you want to appoint this minister?\nName & Surname: " + tempMinister.getName() + " " + tempMinister.getSurname() + "\nLoyalty: " + tempMinister.getLoyalty() + "\nCompetence: " + tempMinister.getCompetency() + "\nIdeology: " + tempMinister.getIdeology())
                    .setPositiveButton("Yes", dialogMinister)
                    .setNegativeButton("No", dialogMinister);
            builder.show();
        }
    }
}

