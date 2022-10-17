package com.example.powerslave;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Dialog;
import android.content.DialogInterface;
import android.content.res.ColorStateList;
import android.os.Bundle;
import android.text.InputType;
import android.text.TextUtils;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.powerslave.government.Country;
import com.example.powerslave.ministry.Ministry;
import com.example.powerslave.ministry.MinistryOfForeignAffairs;
import com.example.powerslave.ministry.NationalIntelligence;
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
        } else if (MainGameMenuActivity.selectedMinistry == 7) {
            ministry = country.getMinistryOfTransportation();
        } else if (MainGameMenuActivity.selectedMinistry == 8) {
            ministry = country.getMinistryOfCulture();
        } else if (MainGameMenuActivity.selectedMinistry == 9) {
            ministry = country.getMinistryOfInternalAffairs();
        } else if (MainGameMenuActivity.selectedMinistry == 10) {
            ministry = country.getMinistryOfJustice();
        } else if (MainGameMenuActivity.selectedMinistry == 11) {
            ministry = country.getParliament();
        } else if (MainGameMenuActivity.selectedMinistry == 12) {
            ministry = country.getNationalIntelligence();

            NationalIntelligence intelligence = (NationalIntelligence) ministry;

            EditText agentsLimitEdit = new EditText(this);
            agentsLimitEdit.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT));
            agentsLimitEdit.setInputType(InputType.TYPE_CLASS_NUMBER);
            agentsLimitEdit.setHint("Enter limit of agents");

            Button buttonConfirmAgentsLimitEdit = new Button(this);
            buttonConfirmAgentsLimitEdit.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT));
            buttonConfirmAgentsLimitEdit.setText("Confirm limit");
            buttonConfirmAgentsLimitEdit.setOnClickListener(view -> {
                if (TextUtils.isEmpty(agentsLimitEdit.getText())) {
                    Toast.makeText(MinistryActivity.this, "You need to enter something", Toast.LENGTH_SHORT).show();
                } else {
                    Integer limit = Integer.parseInt(String.valueOf(agentsLimitEdit.getText()));
                    if (limit > intelligence.getMaximumAgentsLimit() || limit < 0) {
                        Toast.makeText(MinistryActivity.this, ("Have you lost your mind, " + (country.getRuler().getSex() == Sex.MALE ? "sir?" : "ma'am?")), Toast.LENGTH_SHORT).show();
                    } else {
                        intelligence.setAgentsLimit(limit);
                        MinistryActivity.this.recreate();
                    }
                }
            });

            EditText agentsSalaryEdit = new EditText(this);
            agentsSalaryEdit.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT));
            agentsSalaryEdit.setInputType(InputType.TYPE_CLASS_NUMBER);
            agentsSalaryEdit.setHint("Enter salary of agents");

            Button buttonConfirmAgentsSalaryEdit = new Button(this);
            buttonConfirmAgentsSalaryEdit.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT));
            buttonConfirmAgentsSalaryEdit.setText("Confirm salary");
            buttonConfirmAgentsSalaryEdit.setOnClickListener(view -> {
                if (TextUtils.isEmpty(agentsSalaryEdit.getText())) {
                    Toast.makeText(MinistryActivity.this, "You need to enter something", Toast.LENGTH_SHORT).show();
                } else {
                    Float salary = Float.parseFloat(String.valueOf(agentsSalaryEdit.getText()));
                    if (salary > (intelligence.getAgentsSalaryNeed() * 10) || salary < 0) {
                        Toast.makeText(MinistryActivity.this, ("Have you lost your mind, " + (country.getRuler().getSex() == Sex.MALE ? "sir?" : "ma'am?")), Toast.LENGTH_SHORT).show();
                    } else {
                        intelligence.setAgentsSalary(salary);
                        MinistryActivity.this.recreate();
                    }
                }
            });

            Spinner missionsSpinner = new Spinner(this);
            TextView textCurrentCountryMissions = new TextView(this);

            ArrayAdapter missionsAdapter = new ArrayAdapter(this, android.R.layout.simple_spinner_dropdown_item, intelligence.countriesMissions);
            missionsSpinner.setAdapter(missionsAdapter);


            Slider sliderPutThereAgents = new Slider(this);
            final int[] availableAgentsForThisCountry = {intelligence.getAgentsFree() + intelligence.countriesMissions.get(missionsSpinner.getSelectedItemPosition()).getAgentsThere()};
            sliderPutThereAgents.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT));
            sliderPutThereAgents.setValueFrom(0);
            sliderPutThereAgents.setValueTo(availableAgentsForThisCountry[0]);
            sliderPutThereAgents.setStepSize(1);

            RadioGroup missionGroup = new RadioGroup(this);

            RadioButton missionSabotage = new RadioButton(this);
            missionSabotage.setText("Sabotage");

            RadioButton missionSpying = new RadioButton(this);
            missionSpying.setText("Spying");

            RadioButton missionNomission = new RadioButton(this);
            missionNomission.setText("No mission");

            missionGroup.addView(missionSabotage);
            missionGroup.addView(missionSpying);
            missionGroup.addView(missionNomission);

            missionGroup.setOnCheckedChangeListener((radioGroup, i) -> {
                if (i == missionSabotage.getId()) {
                    intelligence.countriesMissions.get(missionsSpinner.getSelectedItemPosition()).setSabotageMission();
                } else if (i == missionSpying.getId()) {
                    intelligence.countriesMissions.get(missionsSpinner.getSelectedItemPosition()).setSpyingMission();
                } else {
                    intelligence.countriesMissions.get(missionsSpinner.getSelectedItemPosition()).setNomission();
                }
                missionGroup.check(i);
                Toast.makeText(MinistryActivity.this, "Mission is changed!", Toast.LENGTH_SHORT).show();
                MinistryActivity.this.recreate();
            });

            Button buttonPutThereDiplomats = new Button(this);
            buttonPutThereDiplomats.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT));
            buttonPutThereDiplomats.setText("Put agents in " + intelligence.countriesMissions.get(missionsSpinner.getSelectedItemPosition()).getCountry().getName());
            buttonPutThereDiplomats.setOnClickListener(view -> {
                intelligence.setAgentsFree((int) (intelligence.getAgentsFree() - (sliderPutThereAgents.getValue() - intelligence.countriesMissions.get(missionsSpinner.getSelectedItemPosition()).getAgentsThere())));
                intelligence.countriesMissions.get(missionsSpinner.getSelectedItemPosition()).setAgentsThere((int) sliderPutThereAgents.getValue());
                Toast.makeText(MinistryActivity.this, "Agents are put in this country!", Toast.LENGTH_SHORT).show();
                MinistryActivity.this.recreate();
            });

            missionsSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                @Override
                public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                    textCurrentCountryMissions.setText(intelligence.getCurrentMissions(intelligence.countriesMissions.get(i)));
                    availableAgentsForThisCountry[0] = intelligence.getAgentsFree() + intelligence.countriesMissions.get(i).getAgentsThere();
                    sliderPutThereAgents.setValueTo(availableAgentsForThisCountry[0]);
                    if (availableAgentsForThisCountry[0] == 0) {
                        buttonLayout.removeView(sliderPutThereAgents);
                        buttonLayout.removeView(buttonPutThereDiplomats);
                    }
                    if (availableAgentsForThisCountry[0] > 0 && sliderPutThereAgents.getParent() == null) {
                        buttonLayout.addView(sliderPutThereAgents);
                        buttonLayout.addView(buttonPutThereDiplomats);
                    }
                }

                @Override
                public void onNothingSelected(AdapterView<?> adapterView) {

                }
            });

            buttonLayout.addView(missionsSpinner);
            buttonLayout.addView(textCurrentCountryMissions);

            buttonLayout.addView(agentsLimitEdit);
            buttonLayout.addView(buttonConfirmAgentsLimitEdit);

            buttonLayout.addView(agentsSalaryEdit);
            buttonLayout.addView(buttonConfirmAgentsSalaryEdit);

            buttonLayout.addView(missionGroup);

            if (availableAgentsForThisCountry[0] > 0) {
                buttonLayout.addView(sliderPutThereAgents);
                buttonLayout.addView(buttonPutThereDiplomats);
            }
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
            TextView textCurrentCountryRelations = new TextView(this);

            ArrayAdapter relationsAdapter = new ArrayAdapter(this, android.R.layout.simple_spinner_dropdown_item, foreignAffairs.countriesRelations);
            relationsSpinner.setAdapter(relationsAdapter);

            Button buttonMakeTradePartner = new Button(this);
            buttonMakeTradePartner.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT));
            buttonMakeTradePartner.setText("Make trade partner");
            buttonMakeTradePartner.setOnClickListener(view -> {
                if (foreignAffairs.countriesRelations.get(spinnerChooseMinister.getSelectedItemPosition()).getRelation() > 55) {
                    foreignAffairs.countriesRelations.get(relationsSpinner.getSelectedItemPosition()).setTradePartner(true);
                    textCurrentCountryRelations.setText(foreignAffairs.getCurrentRelations(foreignAffairs.countriesRelations.get(relationsSpinner.getSelectedItemPosition())));
                    Toast.makeText(MinistryActivity.this, "We now in trade relationships!", Toast.LENGTH_SHORT).show();
                    MinistryActivity.this.recreate();
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
                    Toast.makeText(MinistryActivity.this, "They're our ally from now!", Toast.LENGTH_SHORT).show();
                    MinistryActivity.this.recreate();
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
                Toast.makeText(MinistryActivity.this, "We in war with those bastards now!", Toast.LENGTH_SHORT).show();
                MinistryActivity.this.recreate();
            });

            Slider sliderPutThereDiplomats = new Slider(this);
            final int[] availableDiplomatsForThisCountry = {foreignAffairs.getDiplomatsFree() + foreignAffairs.countriesRelations.get(relationsSpinner.getSelectedItemPosition()).getDiplomatsThere()};
            sliderPutThereDiplomats.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT));
            sliderPutThereDiplomats.setValueFrom(0);
            sliderPutThereDiplomats.setValueTo(availableDiplomatsForThisCountry[0]);
            sliderPutThereDiplomats.setStepSize(1);

            Button buttonPutThereDiplomats = new Button(this);
            buttonPutThereDiplomats.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT));
            buttonPutThereDiplomats.setText("Put diplomats in " + foreignAffairs.countriesRelations.get(relationsSpinner.getSelectedItemPosition()).getCountry().getName());
            buttonPutThereDiplomats.setOnClickListener(view -> {
                foreignAffairs.setDiplomatsFree((int) (foreignAffairs.getDiplomatsFree() - (sliderPutThereDiplomats.getValue() - foreignAffairs.countriesRelations.get(relationsSpinner.getSelectedItemPosition()).getDiplomatsThere())));
                foreignAffairs.countriesRelations.get(relationsSpinner.getSelectedItemPosition()).setDiplomatsThere((int) sliderPutThereDiplomats.getValue());
                Toast.makeText(MinistryActivity.this, "Diplomats are put in this country!", Toast.LENGTH_SHORT).show();
                MinistryActivity.this.recreate();
            });

            relationsSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                @Override
                public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                    textCurrentCountryRelations.setText(foreignAffairs.getCurrentRelations(foreignAffairs.countriesRelations.get(i)));
                    availableDiplomatsForThisCountry[0] = foreignAffairs.getDiplomatsFree() + foreignAffairs.countriesRelations.get(i).getDiplomatsThere();
                    sliderPutThereDiplomats.setValueTo(availableDiplomatsForThisCountry[0]);
                    if (availableDiplomatsForThisCountry[0] == 0) {
                        buttonLayout.removeView(sliderPutThereDiplomats);
                        buttonLayout.removeView(buttonPutThereDiplomats);
                    }
                    if (availableDiplomatsForThisCountry[0] > 0 && sliderPutThereDiplomats.getParent() == null) {
                        buttonLayout.addView(sliderPutThereDiplomats);
                        buttonLayout.addView(buttonPutThereDiplomats);
                    }
                }

                @Override
                public void onNothingSelected(AdapterView<?> adapterView) {

                }
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

            if (availableDiplomatsForThisCountry[0] > 0) {
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

