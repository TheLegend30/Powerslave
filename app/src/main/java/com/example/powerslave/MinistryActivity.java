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
import android.widget.CheckBox;
import android.widget.CompoundButton;
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
import com.example.powerslave.ministry.MinistryOfAgriculture;
import com.example.powerslave.ministry.MinistryOfCulture;
import com.example.powerslave.ministry.MinistryOfDefense;
import com.example.powerslave.ministry.MinistryOfDevelopment;
import com.example.powerslave.ministry.MinistryOfEducation;
import com.example.powerslave.ministry.MinistryOfForeignAffairs;
import com.example.powerslave.ministry.MinistryOfHealthcare;
import com.example.powerslave.ministry.MinistryOfIndustry;
import com.example.powerslave.ministry.MinistryOfInternalAffairs;
import com.example.powerslave.ministry.MinistryOfJustice;
import com.example.powerslave.ministry.MinistryOfTransportation;
import com.example.powerslave.ministry.NationalIntelligence;
import com.example.powerslave.ministry.Parliament;
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

            ministryFunding();

            MinistryOfHealthcare healthcare = (MinistryOfHealthcare) ministry;

            EditText doctorsLimitEdit = new EditText(this);
            doctorsLimitEdit.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT));
            doctorsLimitEdit.setInputType(InputType.TYPE_CLASS_NUMBER);
            doctorsLimitEdit.setHint("Enter limit of doctors");

            Button buttonConfirmDoctorsLimitEdit = new Button(this);
            buttonConfirmDoctorsLimitEdit.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT));
            buttonConfirmDoctorsLimitEdit.setText("Confirm limit");
            buttonConfirmDoctorsLimitEdit.setOnClickListener(view -> {
                if (TextUtils.isEmpty(doctorsLimitEdit.getText())) {
                    Toast.makeText(MinistryActivity.this, "You need to enter something", Toast.LENGTH_SHORT).show();
                } else {
                    Integer limit = Integer.parseInt(String.valueOf(doctorsLimitEdit.getText()));
                    if (limit > healthcare.getMaximumDoctorsLimit() || limit < 0) {
                        Toast.makeText(MinistryActivity.this, ("Have you lost your mind, " + (country.getRuler().getSex() == Sex.MALE ? "sir?" : "ma'am?")), Toast.LENGTH_SHORT).show();
                    } else {
                        healthcare.setDoctorsLimit(limit);
                        MinistryActivity.this.recreate();
                    }
                }
            });

            EditText doctorsSalaryEdit = new EditText(this);
            doctorsSalaryEdit.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT));
            doctorsSalaryEdit.setInputType(InputType.TYPE_CLASS_NUMBER);
            doctorsSalaryEdit.setHint("Enter salary of doctors");

            Button buttonConfirmDoctorsSalaryEdit = new Button(this);
            buttonConfirmDoctorsSalaryEdit.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT));
            buttonConfirmDoctorsSalaryEdit.setText("Confirm salary");
            buttonConfirmDoctorsSalaryEdit.setOnClickListener(view -> {
                if (TextUtils.isEmpty(doctorsSalaryEdit.getText())) {
                    Toast.makeText(MinistryActivity.this, "You need to enter something", Toast.LENGTH_SHORT).show();
                } else {
                    Float salary = Float.parseFloat(String.valueOf(doctorsSalaryEdit.getText()));
                    if (salary > (healthcare.getDoctorsSalaryNeed() * 10) || salary < 0) {
                        Toast.makeText(MinistryActivity.this, ("Have you lost your mind, " + (country.getRuler().getSex() == Sex.MALE ? "sir?" : "ma'am?")), Toast.LENGTH_SHORT).show();
                    } else {
                        healthcare.setDoctorsSalary(salary);
                        MinistryActivity.this.recreate();
                    }
                }
            });

            EditText pensionSalaryEdit = new EditText(this);
            pensionSalaryEdit.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT));
            pensionSalaryEdit.setInputType(InputType.TYPE_CLASS_NUMBER);
            pensionSalaryEdit.setHint("Enter pension");

            Button buttonConfirmPensionEdit = new Button(this);
            buttonConfirmPensionEdit.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT));
            buttonConfirmPensionEdit.setText("Confirm pension");
            buttonConfirmPensionEdit.setOnClickListener(view -> {
                if (TextUtils.isEmpty(pensionSalaryEdit.getText())) {
                    Toast.makeText(MinistryActivity.this, "You need to enter something", Toast.LENGTH_SHORT).show();
                } else {
                    Float pension = Float.parseFloat(String.valueOf(pensionSalaryEdit.getText()));
                    if (pension > (healthcare.getPensionNeed() * 10) || pension < 0) {
                        Toast.makeText(MinistryActivity.this, ("Have you lost your mind, " + (country.getRuler().getSex() == Sex.MALE ? "sir?" : "ma'am?")), Toast.LENGTH_SHORT).show();
                    } else {
                        healthcare.setPension(pension);
                        MinistryActivity.this.recreate();
                    }
                }
            });

            EditText retirementAgeEdit = new EditText(this);
            retirementAgeEdit.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT));
            retirementAgeEdit.setInputType(InputType.TYPE_CLASS_NUMBER);
            retirementAgeEdit.setHint("Enter retirement age");

            Button buttonConfirmRetirementAgeEdit = new Button(this);
            buttonConfirmRetirementAgeEdit.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT));
            buttonConfirmRetirementAgeEdit.setText("Confirm retirement age");
            buttonConfirmRetirementAgeEdit.setOnClickListener(view -> {
                if (TextUtils.isEmpty(retirementAgeEdit.getText())) {
                    Toast.makeText(MinistryActivity.this, "You need to enter something", Toast.LENGTH_SHORT).show();
                } else {
                    Integer retirementAge = Integer.parseInt(String.valueOf(retirementAgeEdit.getText()));
                    if (retirementAge > (healthcare.getLifeExpectancy() + 20) || retirementAge < (healthcare.getLifeExpectancy() - 20)) {
                        Toast.makeText(MinistryActivity.this, ("Have you lost your mind, " + (country.getRuler().getSex() == Sex.MALE ? "sir?" : "ma'am?")), Toast.LENGTH_SHORT).show();
                    } else {
                        healthcare.setRetirementAge(retirementAge);
                        MinistryActivity.this.recreate();
                    }
                }
            });

            EditText wantToBuildHospitalsEdit = new EditText(this);
            wantToBuildHospitalsEdit.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT));
            wantToBuildHospitalsEdit.setInputType(InputType.TYPE_CLASS_NUMBER);
            wantToBuildHospitalsEdit.setHint("How much hospitals want to build?");

            Button buttonBuildHospital = new Button(this);
            buttonBuildHospital.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT));
            buttonBuildHospital.setText("Build hospitals");
            buttonBuildHospital.setOnClickListener(view -> {
                if (TextUtils.isEmpty(wantToBuildHospitalsEdit.getText())) {
                    Toast.makeText(MinistryActivity.this, "You need to enter something", Toast.LENGTH_SHORT).show();
                } else {
                    int count = Integer.parseInt(String.valueOf(wantToBuildHospitalsEdit.getText()));
                    if (country.getMinistryOfTransportation().getFreeBuilders() >= MinistryOfTransportation.getHospitalBuildersNeed() * count) {
                        Toast.makeText(MinistryActivity.this, "We'll build " + count + " hospitals", Toast.LENGTH_SHORT).show();
                        country.getMinistryOfTransportation().buildHospital(count);
                    } else {
                        Toast.makeText(MinistryActivity.this, "That is too much building", Toast.LENGTH_SHORT).show();
                    }
                }

            });


            buttonLayout.addView(doctorsLimitEdit);
            buttonLayout.addView(buttonConfirmDoctorsLimitEdit);
            buttonLayout.addView(doctorsSalaryEdit);
            buttonLayout.addView(buttonConfirmDoctorsSalaryEdit);
            buttonLayout.addView(pensionSalaryEdit);
            buttonLayout.addView(buttonConfirmPensionEdit);
            buttonLayout.addView(retirementAgeEdit);
            buttonLayout.addView(buttonConfirmRetirementAgeEdit);
            buttonLayout.addView(wantToBuildHospitalsEdit);
            buttonLayout.addView(buttonBuildHospital);

        } else if (MainGameMenuActivity.selectedMinistry == 1) {
            ministry = country.getMinistryOfEducation();

            ministryFunding();

            MinistryOfEducation education = (MinistryOfEducation) ministry;

            EditText teachersLimitEdit = new EditText(this);
            teachersLimitEdit.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT));
            teachersLimitEdit.setInputType(InputType.TYPE_CLASS_NUMBER);
            teachersLimitEdit.setHint("Enter limit of teachers");

            Button buttonConfirmTeachersLimitEdit = new Button(this);
            buttonConfirmTeachersLimitEdit.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT));
            buttonConfirmTeachersLimitEdit.setText("Confirm limit");
            buttonConfirmTeachersLimitEdit.setOnClickListener(view -> {
                if (TextUtils.isEmpty(teachersLimitEdit.getText())) {
                    Toast.makeText(MinistryActivity.this, "You need to enter something", Toast.LENGTH_SHORT).show();
                } else {
                    Integer limit = Integer.parseInt(String.valueOf(teachersLimitEdit.getText()));
                    if (limit > education.getMaximumTeachersLimit() || limit < 0) {
                        Toast.makeText(MinistryActivity.this, ("Have you lost your mind, " + (country.getRuler().getSex() == Sex.MALE ? "sir?" : "ma'am?")), Toast.LENGTH_SHORT).show();
                    } else {
                        education.setTeachersLimit(limit);
                        MinistryActivity.this.recreate();
                    }
                }
            });

            EditText teachersSalaryEdit = new EditText(this);
            teachersSalaryEdit.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT));
            teachersSalaryEdit.setInputType(InputType.TYPE_CLASS_NUMBER);
            teachersSalaryEdit.setHint("Enter salary of teachers");

            Button buttonConfirmTeachersSalaryEdit = new Button(this);
            buttonConfirmTeachersSalaryEdit.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT));
            buttonConfirmTeachersSalaryEdit.setText("Confirm salary");
            buttonConfirmTeachersSalaryEdit.setOnClickListener(view -> {
                if (TextUtils.isEmpty(teachersSalaryEdit.getText())) {
                    Toast.makeText(MinistryActivity.this, "You need to enter something", Toast.LENGTH_SHORT).show();
                } else {
                    Float salary = Float.parseFloat(String.valueOf(teachersSalaryEdit.getText()));
                    if (salary > (education.getTeachersSalaryNeed() * 10) || salary < 0) {
                        Toast.makeText(MinistryActivity.this, ("Have you lost your mind, " + (country.getRuler().getSex() == Sex.MALE ? "sir?" : "ma'am?")), Toast.LENGTH_SHORT).show();
                    } else {
                        education.setTeachersSalary(salary);
                        MinistryActivity.this.recreate();
                    }
                }
            });


            EditText wantToBuildSchoolsEdit = new EditText(this);
            wantToBuildSchoolsEdit.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT));
            wantToBuildSchoolsEdit.setInputType(InputType.TYPE_CLASS_NUMBER);
            wantToBuildSchoolsEdit.setHint("How much schools want to build?");

            Button buttonBuildSchool = new Button(this);
            buttonBuildSchool.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT));
            buttonBuildSchool.setText("Build school");
            buttonBuildSchool.setOnClickListener(view -> {
                if (TextUtils.isEmpty(wantToBuildSchoolsEdit.getText())) {
                    Toast.makeText(MinistryActivity.this, "You need to enter something", Toast.LENGTH_SHORT).show();
                } else {
                    int count = Integer.parseInt(String.valueOf(wantToBuildSchoolsEdit.getText()));
                    if (country.getMinistryOfTransportation().getFreeBuilders() >= MinistryOfTransportation.getSchoolBuildersNeed() * count) {
                        Toast.makeText(MinistryActivity.this, "We'll build " + count + " schools", Toast.LENGTH_SHORT).show();
                        country.getMinistryOfTransportation().buildSchool(count);
                    } else {
                        Toast.makeText(MinistryActivity.this, "That is too much building", Toast.LENGTH_SHORT).show();
                    }
                }

            });

            EditText wantToBuildCollegesEdit = new EditText(this);
            wantToBuildCollegesEdit.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT));
            wantToBuildCollegesEdit.setInputType(InputType.TYPE_CLASS_NUMBER);
            wantToBuildCollegesEdit.setHint("How much colleges want to build?");

            Button buttonBuildCollege = new Button(this);
            buttonBuildCollege.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT));
            buttonBuildCollege.setText("Build college");
            buttonBuildCollege.setOnClickListener(view -> {
                if (TextUtils.isEmpty(wantToBuildCollegesEdit.getText())) {
                    Toast.makeText(MinistryActivity.this, "You need to enter something", Toast.LENGTH_SHORT).show();
                } else {
                    int count = Integer.parseInt(String.valueOf(wantToBuildCollegesEdit.getText()));
                    if (country.getMinistryOfTransportation().getFreeBuilders() >= MinistryOfTransportation.getCollegeBuildersNeed() * count) {
                        Toast.makeText(MinistryActivity.this, "We'll build " + count + " colleges", Toast.LENGTH_SHORT).show();
                        country.getMinistryOfTransportation().buildCollege(count);
                    } else {
                        Toast.makeText(MinistryActivity.this, "That is too much building", Toast.LENGTH_SHORT).show();
                    }
                }

            });


            buttonLayout.addView(teachersLimitEdit);
            buttonLayout.addView(buttonConfirmTeachersLimitEdit);
            buttonLayout.addView(teachersSalaryEdit);
            buttonLayout.addView(buttonConfirmTeachersSalaryEdit);

            buttonLayout.addView(wantToBuildSchoolsEdit);
            buttonLayout.addView(buttonBuildSchool);

            buttonLayout.addView(wantToBuildCollegesEdit);
            buttonLayout.addView(buttonBuildCollege);

        } else if (MainGameMenuActivity.selectedMinistry == 2) {
            ministry = country.getMinistryOfEconomy();
        } else if (MainGameMenuActivity.selectedMinistry == 3) {
            ministry = country.getMinistryOfDefense();

            ministryFunding();

            MinistryOfDefense defense = (MinistryOfDefense) ministry;

            EditText generalsLimitEdit = new EditText(this);
            generalsLimitEdit.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT));
            generalsLimitEdit.setInputType(InputType.TYPE_CLASS_NUMBER);
            generalsLimitEdit.setHint("Enter limit of generals");

            Button buttonConfirmGeneralsLimitEdit = new Button(this);
            buttonConfirmGeneralsLimitEdit.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT));
            buttonConfirmGeneralsLimitEdit.setText("Confirm limit");
            buttonConfirmGeneralsLimitEdit.setOnClickListener(view -> {
                if (TextUtils.isEmpty(generalsLimitEdit.getText())) {
                    Toast.makeText(MinistryActivity.this, "You need to enter something", Toast.LENGTH_SHORT).show();
                } else {
                    Integer limit = Integer.parseInt(String.valueOf(generalsLimitEdit.getText()));
                    if (limit > defense.getMaximumGeneralsLimit() || limit < 0) {
                        Toast.makeText(MinistryActivity.this, ("Have you lost your mind, " + (country.getRuler().getSex() == Sex.MALE ? "sir?" : "ma'am?")), Toast.LENGTH_SHORT).show();
                    } else {
                        defense.setGeneralsLimit(limit);
                        MinistryActivity.this.recreate();
                    }
                }
            });

            EditText generalsSalaryEdit = new EditText(this);
            generalsSalaryEdit.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT));
            generalsSalaryEdit.setInputType(InputType.TYPE_CLASS_NUMBER);
            generalsSalaryEdit.setHint("Enter salary of generals");

            Button buttonConfirmGeneralsSalaryEdit = new Button(this);
            buttonConfirmGeneralsSalaryEdit.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT));
            buttonConfirmGeneralsSalaryEdit.setText("Confirm salary");
            buttonConfirmGeneralsSalaryEdit.setOnClickListener(view -> {
                if (TextUtils.isEmpty(generalsSalaryEdit.getText())) {
                    Toast.makeText(MinistryActivity.this, "You need to enter something", Toast.LENGTH_SHORT).show();
                } else {
                    Float salary = Float.parseFloat(String.valueOf(generalsSalaryEdit.getText()));
                    if (salary > (defense.getGeneralsSalaryNeed() * 10) || salary < 0) {
                        Toast.makeText(MinistryActivity.this, ("Have you lost your mind, " + (country.getRuler().getSex() == Sex.MALE ? "sir?" : "ma'am?")), Toast.LENGTH_SHORT).show();
                    } else {
                        defense.setGeneralsSalary(salary);
                        MinistryActivity.this.recreate();
                    }
                }
            });

            EditText admiralsLimitEdit = new EditText(this);
            admiralsLimitEdit.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT));
            admiralsLimitEdit.setInputType(InputType.TYPE_CLASS_NUMBER);
            admiralsLimitEdit.setHint("Enter limit of admirals");

            Button buttonConfirmAdmiralsLimitEdit = new Button(this);
            buttonConfirmAdmiralsLimitEdit.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT));
            buttonConfirmAdmiralsLimitEdit.setText("Confirm limit");
            buttonConfirmAdmiralsLimitEdit.setOnClickListener(view -> {
                if (TextUtils.isEmpty(admiralsLimitEdit.getText())) {
                    Toast.makeText(MinistryActivity.this, "You need to enter something", Toast.LENGTH_SHORT).show();
                } else {
                    Integer limit = Integer.parseInt(String.valueOf(admiralsLimitEdit.getText()));
                    if (limit > defense.getMaximumAdmiralsLimit() || limit < 0) {
                        Toast.makeText(MinistryActivity.this, ("Have you lost your mind, " + (country.getRuler().getSex() == Sex.MALE ? "sir?" : "ma'am?")), Toast.LENGTH_SHORT).show();
                    } else {
                        defense.setAdmiralsLimit(limit);
                        MinistryActivity.this.recreate();
                    }
                }
            });

            EditText admiralsSalaryEdit = new EditText(this);
            admiralsSalaryEdit.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT));
            admiralsSalaryEdit.setInputType(InputType.TYPE_CLASS_NUMBER);
            admiralsSalaryEdit.setHint("Enter salary of admirals");

            Button buttonConfirmAdmiralsSalaryEdit = new Button(this);
            buttonConfirmAdmiralsSalaryEdit.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT));
            buttonConfirmAdmiralsSalaryEdit.setText("Confirm salary");
            buttonConfirmAdmiralsSalaryEdit.setOnClickListener(view -> {
                if (TextUtils.isEmpty(admiralsSalaryEdit.getText())) {
                    Toast.makeText(MinistryActivity.this, "You need to enter something", Toast.LENGTH_SHORT).show();
                } else {
                    Float salary = Float.parseFloat(String.valueOf(admiralsSalaryEdit.getText()));
                    if (salary > (defense.getAdmiralsSalaryNeed() * 10) || salary < 0) {
                        Toast.makeText(MinistryActivity.this, ("Have you lost your mind, " + (country.getRuler().getSex() == Sex.MALE ? "sir?" : "ma'am?")), Toast.LENGTH_SHORT).show();
                    } else {
                        defense.setAdmiralsSalary(salary);
                        MinistryActivity.this.recreate();
                    }
                }
            });

            EditText airForceOfficersLimitEdit = new EditText(this);
            airForceOfficersLimitEdit.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT));
            airForceOfficersLimitEdit.setInputType(InputType.TYPE_CLASS_NUMBER);
            airForceOfficersLimitEdit.setHint("Enter limit of Air Force officers");

            Button buttonConfirmAirForceOfficersLimitEdit = new Button(this);
            buttonConfirmAirForceOfficersLimitEdit.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT));
            buttonConfirmAirForceOfficersLimitEdit.setText("Confirm limit");
            buttonConfirmAirForceOfficersLimitEdit.setOnClickListener(view -> {
                if (TextUtils.isEmpty(airForceOfficersLimitEdit.getText())) {
                    Toast.makeText(MinistryActivity.this, "You need to enter something", Toast.LENGTH_SHORT).show();
                } else {
                    Integer limit = Integer.parseInt(String.valueOf(airForceOfficersLimitEdit.getText()));
                    if (limit > defense.getMaximumAirForceOfficersLimit() || limit < 0) {
                        Toast.makeText(MinistryActivity.this, ("Have you lost your mind, " + (country.getRuler().getSex() == Sex.MALE ? "sir?" : "ma'am?")), Toast.LENGTH_SHORT).show();
                    } else {
                        defense.setAirForceOfficersLimit(limit);
                        MinistryActivity.this.recreate();
                    }
                }
            });

            EditText airForceOfficersSalaryEdit = new EditText(this);
            airForceOfficersSalaryEdit.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT));
            airForceOfficersSalaryEdit.setInputType(InputType.TYPE_CLASS_NUMBER);
            airForceOfficersSalaryEdit.setHint("Enter salary of Air Force officers");

            Button buttonConfirmAirForceOfficersSalaryEdit = new Button(this);
            buttonConfirmAirForceOfficersSalaryEdit.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT));
            buttonConfirmAirForceOfficersSalaryEdit.setText("Confirm salary");
            buttonConfirmAirForceOfficersSalaryEdit.setOnClickListener(view -> {
                if (TextUtils.isEmpty(airForceOfficersSalaryEdit.getText())) {
                    Toast.makeText(MinistryActivity.this, "You need to enter something", Toast.LENGTH_SHORT).show();
                } else {
                    Float salary = Float.parseFloat(String.valueOf(airForceOfficersSalaryEdit.getText()));
                    if (salary > (defense.getAirForceOfficersSalaryNeed() * 10) || salary < 0) {
                        Toast.makeText(MinistryActivity.this, ("Have you lost your mind, " + (country.getRuler().getSex() == Sex.MALE ? "sir?" : "ma'am?")), Toast.LENGTH_SHORT).show();
                    } else {
                        defense.setAirForceOfficersSalary(salary);
                        MinistryActivity.this.recreate();
                    }
                }
            });

            CheckBox conscriptionPenalty = new CheckBox(this);
            conscriptionPenalty.setText("Conscription");
            conscriptionPenalty.setChecked(defense.isConscription());
            conscriptionPenalty.setOnCheckedChangeListener((compoundButton, b) -> {
                defense.setConscription(b);
                MinistryActivity.this.recreate();
            });

            // TODO: Special process to buy army, navy, air powers

            buttonLayout.addView(generalsLimitEdit);
            buttonLayout.addView(buttonConfirmGeneralsLimitEdit);
            buttonLayout.addView(generalsSalaryEdit);
            buttonLayout.addView(buttonConfirmGeneralsSalaryEdit);

            buttonLayout.addView(admiralsLimitEdit);
            buttonLayout.addView(buttonConfirmAdmiralsLimitEdit);
            buttonLayout.addView(admiralsSalaryEdit);
            buttonLayout.addView(buttonConfirmAdmiralsSalaryEdit);

            buttonLayout.addView(airForceOfficersLimitEdit);
            buttonLayout.addView(buttonConfirmAirForceOfficersLimitEdit);
            buttonLayout.addView(airForceOfficersSalaryEdit);
            buttonLayout.addView(buttonConfirmAirForceOfficersSalaryEdit);

            buttonLayout.addView(conscriptionPenalty);

        } else if (MainGameMenuActivity.selectedMinistry == 4) {
            ministry = country.getMinistryOfAgriculture();

            ministryFunding();

            MinistryOfAgriculture agriculture = (MinistryOfAgriculture) ministry;


            EditText farmersLimitEdit = new EditText(this);
            farmersLimitEdit.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT));
            farmersLimitEdit.setInputType(InputType.TYPE_CLASS_NUMBER);
            farmersLimitEdit.setHint("Enter limit of farmers");

            Button buttonConfirmFarmersLimitEdit = new Button(this);
            buttonConfirmFarmersLimitEdit.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT));
            buttonConfirmFarmersLimitEdit.setText("Confirm limit");
            buttonConfirmFarmersLimitEdit.setOnClickListener(view -> {
                if (TextUtils.isEmpty(farmersLimitEdit.getText())) {
                    Toast.makeText(MinistryActivity.this, "You need to enter something", Toast.LENGTH_SHORT).show();
                } else {
                    Integer limit = Integer.parseInt(String.valueOf(farmersLimitEdit.getText()));
                    if (limit > agriculture.getMaximumFarmersLimit() || limit < 0) {
                        Toast.makeText(MinistryActivity.this, ("Have you lost your mind, " + (country.getRuler().getSex() == Sex.MALE ? "sir?" : "ma'am?")), Toast.LENGTH_SHORT).show();
                    } else {
                        agriculture.setFarmersLimit(limit);
                        MinistryActivity.this.recreate();
                    }
                }
            });

            EditText farmersSalaryEdit = new EditText(this);
            farmersSalaryEdit.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT));
            farmersSalaryEdit.setInputType(InputType.TYPE_CLASS_NUMBER);
            farmersSalaryEdit.setHint("Enter salary of farmers");

            Button buttonConfirmFarmersSalaryEdit = new Button(this);
            buttonConfirmFarmersSalaryEdit.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT));
            buttonConfirmFarmersSalaryEdit.setText("Confirm salary");
            buttonConfirmFarmersSalaryEdit.setOnClickListener(view -> {
                if (TextUtils.isEmpty(farmersSalaryEdit.getText())) {
                    Toast.makeText(MinistryActivity.this, "You need to enter something", Toast.LENGTH_SHORT).show();
                } else {
                    Float salary = Float.parseFloat(String.valueOf(farmersSalaryEdit.getText()));
                    if (salary > (agriculture.getFarmersSalaryNeed() * 10) || salary < 0) {
                        Toast.makeText(MinistryActivity.this, ("Have you lost your mind, " + (country.getRuler().getSex() == Sex.MALE ? "sir?" : "ma'am?")), Toast.LENGTH_SHORT).show();
                    } else {
                        agriculture.setFarmersSalary(salary);
                        MinistryActivity.this.recreate();
                    }
                }
            });

            EditText minersLimitEdit = new EditText(this);
            minersLimitEdit.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT));
            minersLimitEdit.setInputType(InputType.TYPE_CLASS_NUMBER);
            minersLimitEdit.setHint("Enter limit of miners");

            Button buttonConfirmMinersLimitEdit = new Button(this);
            buttonConfirmMinersLimitEdit.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT));
            buttonConfirmMinersLimitEdit.setText("Confirm limit");
            buttonConfirmMinersLimitEdit.setOnClickListener(view -> {
                if (TextUtils.isEmpty(minersLimitEdit.getText())) {
                    Toast.makeText(MinistryActivity.this, "You need to enter something", Toast.LENGTH_SHORT).show();
                } else {
                    Integer limit = Integer.parseInt(String.valueOf(minersLimitEdit.getText()));
                    if (limit > agriculture.getMaximumMinersLimit() || limit < 0) {
                        Toast.makeText(MinistryActivity.this, ("Have you lost your mind, " + (country.getRuler().getSex() == Sex.MALE ? "sir?" : "ma'am?")), Toast.LENGTH_SHORT).show();
                    } else {
                        agriculture.setMinersLimit(limit);
                        MinistryActivity.this.recreate();
                    }
                }
            });

            EditText minersSalaryEdit = new EditText(this);
            minersSalaryEdit.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT));
            minersSalaryEdit.setInputType(InputType.TYPE_CLASS_NUMBER);
            minersSalaryEdit.setHint("Enter salary of miners");

            Button buttonConfirmMinersSalaryEdit = new Button(this);
            buttonConfirmMinersSalaryEdit.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT));
            buttonConfirmMinersSalaryEdit.setText("Confirm salary");
            buttonConfirmMinersSalaryEdit.setOnClickListener(view -> {
                if (TextUtils.isEmpty(minersSalaryEdit.getText())) {
                    Toast.makeText(MinistryActivity.this, "You need to enter something", Toast.LENGTH_SHORT).show();
                } else {
                    Float salary = Float.parseFloat(String.valueOf(minersSalaryEdit.getText()));
                    if (salary > (agriculture.getMinersSalaryNeed() * 10) || salary < 0) {
                        Toast.makeText(MinistryActivity.this, ("Have you lost your mind, " + (country.getRuler().getSex() == Sex.MALE ? "sir?" : "ma'am?")), Toast.LENGTH_SHORT).show();
                    } else {
                        agriculture.setMinersSalary(salary);
                        MinistryActivity.this.recreate();
                    }
                }
            });

            buttonLayout.addView(farmersLimitEdit);
            buttonLayout.addView(buttonConfirmFarmersLimitEdit);
            buttonLayout.addView(farmersSalaryEdit);
            buttonLayout.addView(buttonConfirmFarmersSalaryEdit);
            buttonLayout.addView(minersLimitEdit);
            buttonLayout.addView(buttonConfirmMinersLimitEdit);
            buttonLayout.addView(minersSalaryEdit);
            buttonLayout.addView(buttonConfirmMinersSalaryEdit);

            // TODO: Change some texts in MinistryActivity
        } else if (MainGameMenuActivity.selectedMinistry == 5) {
            ministry = country.getMinistryOfDevelopment();

            ministryFunding();

            MinistryOfDevelopment development = (MinistryOfDevelopment) ministry;

            EditText clerksLimitEdit = new EditText(this);
            clerksLimitEdit.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT));
            clerksLimitEdit.setInputType(InputType.TYPE_CLASS_NUMBER);
            clerksLimitEdit.setHint("Enter limit of clerks");

            Button buttonConfirmClerksLimitEdit = new Button(this);
            buttonConfirmClerksLimitEdit.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT));
            buttonConfirmClerksLimitEdit.setText("Confirm limit");
            buttonConfirmClerksLimitEdit.setOnClickListener(view -> {
                if (TextUtils.isEmpty(clerksLimitEdit.getText())) {
                    Toast.makeText(MinistryActivity.this, "You need to enter something", Toast.LENGTH_SHORT).show();
                } else {
                    Integer limit = Integer.parseInt(String.valueOf(clerksLimitEdit.getText()));
                    if (limit > development.getMaximumClerksLimit() || limit < 0) {
                        Toast.makeText(MinistryActivity.this, ("Have you lost your mind, " + (country.getRuler().getSex() == Sex.MALE ? "sir?" : "ma'am?")), Toast.LENGTH_SHORT).show();
                    } else {
                        development.setClerksLimit(limit);
                        MinistryActivity.this.recreate();
                    }
                }
            });

            EditText clerksSalaryEdit = new EditText(this);
            clerksSalaryEdit.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT));
            clerksSalaryEdit.setInputType(InputType.TYPE_CLASS_NUMBER);
            clerksSalaryEdit.setHint("Enter salary of clerks");

            Button buttonConfirmClerksSalaryEdit = new Button(this);
            buttonConfirmClerksSalaryEdit.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT));
            buttonConfirmClerksSalaryEdit.setText("Confirm salary");
            buttonConfirmClerksSalaryEdit.setOnClickListener(view -> {
                if (TextUtils.isEmpty(clerksSalaryEdit.getText())) {
                    Toast.makeText(MinistryActivity.this, "You need to enter something", Toast.LENGTH_SHORT).show();
                } else {
                    Float salary = Float.parseFloat(String.valueOf(clerksSalaryEdit.getText()));
                    if (salary > (development.getClerksSalaryNeed() * 10) || salary < 0) {
                        Toast.makeText(MinistryActivity.this, ("Have you lost your mind, " + (country.getRuler().getSex() == Sex.MALE ? "sir?" : "ma'am?")), Toast.LENGTH_SHORT).show();
                    } else {
                        development.setClerksSalary(salary);
                        MinistryActivity.this.recreate();
                    }
                }
            });

            buttonLayout.addView(clerksLimitEdit);
            buttonLayout.addView(buttonConfirmClerksLimitEdit);
            buttonLayout.addView(clerksSalaryEdit);
            buttonLayout.addView(buttonConfirmClerksSalaryEdit);

        } else if (MainGameMenuActivity.selectedMinistry == 6) {
            ministry = country.getMinistryOfIndustry();

            ministryFunding();

            MinistryOfIndustry industry = (MinistryOfIndustry) ministry;

            EditText lowWorkersLimitEdit = new EditText(this);
            lowWorkersLimitEdit.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT));
            lowWorkersLimitEdit.setInputType(InputType.TYPE_CLASS_NUMBER);
            lowWorkersLimitEdit.setHint("Enter limit of low workers");

            Button buttonConfirmLowWorkersLimitEdit = new Button(this);
            buttonConfirmLowWorkersLimitEdit.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT));
            buttonConfirmLowWorkersLimitEdit.setText("Confirm limit");
            buttonConfirmLowWorkersLimitEdit.setOnClickListener(view -> {
                if (TextUtils.isEmpty(lowWorkersLimitEdit.getText())) {
                    Toast.makeText(MinistryActivity.this, "You need to enter something", Toast.LENGTH_SHORT).show();
                } else {
                    Integer limit = Integer.parseInt(String.valueOf(lowWorkersLimitEdit.getText()));
                    if (limit > industry.getMaximumLowWorkersLimit() || limit < 0) {
                        Toast.makeText(MinistryActivity.this, ("Have you lost your mind, " + (country.getRuler().getSex() == Sex.MALE ? "sir?" : "ma'am?")), Toast.LENGTH_SHORT).show();
                    } else {
                        industry.setLowWorkersLimit(limit);
                        MinistryActivity.this.recreate();
                    }
                }
            });

            EditText lowWorkersSalaryEdit = new EditText(this);
            lowWorkersSalaryEdit.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT));
            lowWorkersSalaryEdit.setInputType(InputType.TYPE_CLASS_NUMBER);
            lowWorkersSalaryEdit.setHint("Enter salary of low workers");

            Button buttonConfirmLowWorkersSalaryEdit = new Button(this);
            buttonConfirmLowWorkersSalaryEdit.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT));
            buttonConfirmLowWorkersSalaryEdit.setText("Confirm salary");
            buttonConfirmLowWorkersSalaryEdit.setOnClickListener(view -> {
                if (TextUtils.isEmpty(lowWorkersSalaryEdit.getText())) {
                    Toast.makeText(MinistryActivity.this, "You need to enter something", Toast.LENGTH_SHORT).show();
                } else {
                    Float salary = Float.parseFloat(String.valueOf(lowWorkersSalaryEdit.getText()));
                    if (salary > (industry.getLowWorkersSalaryNeed() * 10) || salary < 0) {
                        Toast.makeText(MinistryActivity.this, ("Have you lost your mind, " + (country.getRuler().getSex() == Sex.MALE ? "sir?" : "ma'am?")), Toast.LENGTH_SHORT).show();
                    } else {
                        industry.setLowWorkersSalary(salary);
                        MinistryActivity.this.recreate();
                    }
                }
            });

            EditText middleWorkersLimitEdit = new EditText(this);
            middleWorkersLimitEdit.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT));
            middleWorkersLimitEdit.setInputType(InputType.TYPE_CLASS_NUMBER);
            middleWorkersLimitEdit.setHint("Enter limit of middle workers");

            Button buttonConfirmMiddleWorkersLimitEdit = new Button(this);
            buttonConfirmMiddleWorkersLimitEdit.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT));
            buttonConfirmMiddleWorkersLimitEdit.setText("Confirm limit");
            buttonConfirmMiddleWorkersLimitEdit.setOnClickListener(view -> {
                if (TextUtils.isEmpty(middleWorkersLimitEdit.getText())) {
                    Toast.makeText(MinistryActivity.this, "You need to enter something", Toast.LENGTH_SHORT).show();
                } else {
                    Integer limit = Integer.parseInt(String.valueOf(middleWorkersLimitEdit.getText()));
                    if (limit > industry.getMaximumMiddleWorkersLimit() || limit < 0) {
                        Toast.makeText(MinistryActivity.this, ("Have you lost your mind, " + (country.getRuler().getSex() == Sex.MALE ? "sir?" : "ma'am?")), Toast.LENGTH_SHORT).show();
                    } else {
                        industry.setMiddleWorkersLimit(limit);
                        MinistryActivity.this.recreate();
                    }
                }
            });

            EditText middleWorkersSalaryEdit = new EditText(this);
            middleWorkersSalaryEdit.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT));
            middleWorkersSalaryEdit.setInputType(InputType.TYPE_CLASS_NUMBER);
            middleWorkersSalaryEdit.setHint("Enter salary of middle workers");

            Button buttonConfirmMiddleWorkersSalaryEdit = new Button(this);
            buttonConfirmMiddleWorkersSalaryEdit.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT));
            buttonConfirmMiddleWorkersSalaryEdit.setText("Confirm salary");
            buttonConfirmMiddleWorkersSalaryEdit.setOnClickListener(view -> {
                if (TextUtils.isEmpty(middleWorkersSalaryEdit.getText())) {
                    Toast.makeText(MinistryActivity.this, "You need to enter something", Toast.LENGTH_SHORT).show();
                } else {
                    Float salary = Float.parseFloat(String.valueOf(middleWorkersSalaryEdit.getText()));
                    if (salary > (industry.getMiddleWorkersSalaryNeed() * 10) || salary < 0) {
                        Toast.makeText(MinistryActivity.this, ("Have you lost your mind, " + (country.getRuler().getSex() == Sex.MALE ? "sir?" : "ma'am?")), Toast.LENGTH_SHORT).show();
                    } else {
                        industry.setMiddleWorkersSalary(salary);
                        MinistryActivity.this.recreate();
                    }
                }
            });

            EditText highWorkersLimitEdit = new EditText(this);
            highWorkersLimitEdit.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT));
            highWorkersLimitEdit.setInputType(InputType.TYPE_CLASS_NUMBER);
            highWorkersLimitEdit.setHint("Enter limit of high workers");

            Button buttonConfirmHighWorkersLimitEdit = new Button(this);
            buttonConfirmHighWorkersLimitEdit.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT));
            buttonConfirmHighWorkersLimitEdit.setText("Confirm limit");
            buttonConfirmHighWorkersLimitEdit.setOnClickListener(view -> {
                if (TextUtils.isEmpty(highWorkersLimitEdit.getText())) {
                    Toast.makeText(MinistryActivity.this, "You need to enter something", Toast.LENGTH_SHORT).show();
                } else {
                    Integer limit = Integer.parseInt(String.valueOf(highWorkersLimitEdit.getText()));
                    if (limit > industry.getMaximumHighWorkersLimit() || limit < 0) {
                        Toast.makeText(MinistryActivity.this, ("Have you lost your mind, " + (country.getRuler().getSex() == Sex.MALE ? "sir?" : "ma'am?")), Toast.LENGTH_SHORT).show();
                    } else {
                        industry.setHighWorkersLimit(limit);
                        MinistryActivity.this.recreate();
                    }
                }
            });

            EditText highWorkersSalaryEdit = new EditText(this);
            highWorkersSalaryEdit.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT));
            highWorkersSalaryEdit.setInputType(InputType.TYPE_CLASS_NUMBER);
            highWorkersSalaryEdit.setHint("Enter salary of high workers");

            Button buttonConfirmHighWorkersSalaryEdit = new Button(this);
            buttonConfirmHighWorkersSalaryEdit.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT));
            buttonConfirmHighWorkersSalaryEdit.setText("Confirm salary");
            buttonConfirmHighWorkersSalaryEdit.setOnClickListener(view -> {
                if (TextUtils.isEmpty(highWorkersSalaryEdit.getText())) {
                    Toast.makeText(MinistryActivity.this, "You need to enter something", Toast.LENGTH_SHORT).show();
                } else {
                    Float salary = Float.parseFloat(String.valueOf(highWorkersSalaryEdit.getText()));
                    if (salary > (industry.getHighWorkersSalaryNeed() * 10) || salary < 0) {
                        Toast.makeText(MinistryActivity.this, ("Have you lost your mind, " + (country.getRuler().getSex() == Sex.MALE ? "sir?" : "ma'am?")), Toast.LENGTH_SHORT).show();
                    } else {
                        industry.setHighWorkersSalary(salary);
                        MinistryActivity.this.recreate();
                    }
                }
            });

            buttonLayout.addView(lowWorkersLimitEdit);
            buttonLayout.addView(buttonConfirmLowWorkersLimitEdit);
            buttonLayout.addView(lowWorkersSalaryEdit);
            buttonLayout.addView(buttonConfirmLowWorkersSalaryEdit);
            buttonLayout.addView(middleWorkersLimitEdit);
            buttonLayout.addView(buttonConfirmMiddleWorkersLimitEdit);
            buttonLayout.addView(middleWorkersSalaryEdit);
            buttonLayout.addView(buttonConfirmMiddleWorkersSalaryEdit);
            buttonLayout.addView(highWorkersLimitEdit);
            buttonLayout.addView(buttonConfirmHighWorkersLimitEdit);
            buttonLayout.addView(highWorkersSalaryEdit);
            buttonLayout.addView(buttonConfirmHighWorkersSalaryEdit);


        } else if (MainGameMenuActivity.selectedMinistry == 7) {
            ministry = country.getMinistryOfTransportation();

            ministryFunding();

            MinistryOfTransportation transportation = (MinistryOfTransportation) ministry;

            EditText buildersLimitEdit = new EditText(this);
            buildersLimitEdit.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT));
            buildersLimitEdit.setInputType(InputType.TYPE_CLASS_NUMBER);
            buildersLimitEdit.setHint("Enter limit of builders");

            Button buttonConfirmBuildersLimitEdit = new Button(this);
            buttonConfirmBuildersLimitEdit.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT));
            buttonConfirmBuildersLimitEdit.setText("Confirm limit");
            buttonConfirmBuildersLimitEdit.setOnClickListener(view -> {
                if (TextUtils.isEmpty(buildersLimitEdit.getText())) {
                    Toast.makeText(MinistryActivity.this, "You need to enter something", Toast.LENGTH_SHORT).show();
                } else {
                    Integer limit = Integer.parseInt(String.valueOf(buildersLimitEdit.getText()));
                    if (limit > transportation.getMaximumBuildersLimit() || limit < 0) {
                        Toast.makeText(MinistryActivity.this, ("Have you lost your mind, " + (country.getRuler().getSex() == Sex.MALE ? "sir?" : "ma'am?")), Toast.LENGTH_SHORT).show();
                    } else {
                        transportation.setBuildersLimit(limit);
                        MinistryActivity.this.recreate();
                    }
                }
            });

            EditText buildersSalaryEdit = new EditText(this);
            buildersSalaryEdit.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT));
            buildersSalaryEdit.setInputType(InputType.TYPE_CLASS_NUMBER);
            buildersSalaryEdit.setHint("Enter salary of builders");

            Button buttonConfirmBuildersSalaryEdit = new Button(this);
            buttonConfirmBuildersSalaryEdit.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT));
            buttonConfirmBuildersSalaryEdit.setText("Confirm salary");
            buttonConfirmBuildersSalaryEdit.setOnClickListener(view -> {
                if (TextUtils.isEmpty(buildersSalaryEdit.getText())) {
                    Toast.makeText(MinistryActivity.this, "You need to enter something", Toast.LENGTH_SHORT).show();
                } else {
                    Float salary = Float.parseFloat(String.valueOf(buildersSalaryEdit.getText()));
                    if (salary > (transportation.getBuildersSalaryNeed() * 10) || salary < 0) {
                        Toast.makeText(MinistryActivity.this, ("Have you lost your mind, " + (country.getRuler().getSex() == Sex.MALE ? "sir?" : "ma'am?")), Toast.LENGTH_SHORT).show();
                    } else {
                        transportation.setBuildersSalary(salary);
                        MinistryActivity.this.recreate();
                    }
                }
            });

            EditText wantToBuildAirportsEdit = new EditText(this);
            wantToBuildAirportsEdit.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT));
            wantToBuildAirportsEdit.setInputType(InputType.TYPE_CLASS_NUMBER);
            wantToBuildAirportsEdit.setHint("How much airports want to build?");

            Button buttonBuildAirport = new Button(this);
            buttonBuildAirport.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT));
            buttonBuildAirport.setText("Build airport");
            buttonBuildAirport.setOnClickListener(view -> {
                if (TextUtils.isEmpty(wantToBuildAirportsEdit.getText())) {
                    Toast.makeText(MinistryActivity.this, "You need to enter something", Toast.LENGTH_SHORT).show();
                } else {
                    int count = Integer.parseInt(String.valueOf(wantToBuildAirportsEdit.getText()));
                    if (country.getMinistryOfTransportation().getFreeBuilders() >= MinistryOfTransportation.getAirportsBuildersNeed() * count) {
                        Toast.makeText(MinistryActivity.this, "We'll build " + count + " airports", Toast.LENGTH_SHORT).show();
                        country.getMinistryOfTransportation().buildAirport(count);
                    } else {
                        Toast.makeText(MinistryActivity.this, "That is too much building", Toast.LENGTH_SHORT).show();
                    }
                }
                MinistryActivity.this.recreate();
            });

            EditText wantToBuildPortsEdit = new EditText(this);
            wantToBuildPortsEdit.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT));
            wantToBuildPortsEdit.setInputType(InputType.TYPE_CLASS_NUMBER);
            wantToBuildPortsEdit.setHint("How much ports want to build?");

            Button buttonBuildPort = new Button(this);
            buttonBuildPort.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT));
            buttonBuildPort.setText("Build port");
            buttonBuildPort.setOnClickListener(view -> {
                if (TextUtils.isEmpty(wantToBuildPortsEdit.getText())) {
                    Toast.makeText(MinistryActivity.this, "You need to enter something", Toast.LENGTH_SHORT).show();
                } else {
                    int count = Integer.parseInt(String.valueOf(wantToBuildPortsEdit.getText()));
                    if (country.getMinistryOfTransportation().getFreeBuilders() >= MinistryOfTransportation.getPortsBuildersNeed() * count) {
                        Toast.makeText(MinistryActivity.this, "We'll build " + count + " ports", Toast.LENGTH_SHORT).show();
                        country.getMinistryOfTransportation().buildPorts(count);
                    } else {
                        Toast.makeText(MinistryActivity.this, "That is too much building", Toast.LENGTH_SHORT).show();
                    }
                }
                MinistryActivity.this.recreate();
            });

            EditText wantToBuildRailroadEdit = new EditText(this);
            wantToBuildRailroadEdit.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT));
            wantToBuildRailroadEdit.setInputType(InputType.TYPE_CLASS_NUMBER);
            wantToBuildRailroadEdit.setHint("How much kms of railroad want to build?");

            Button buttonBuildRailroad = new Button(this);
            buttonBuildRailroad.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT));
            buttonBuildRailroad.setText("Build railroad");
            buttonBuildRailroad.setOnClickListener(view -> {
                if (TextUtils.isEmpty(wantToBuildRailroadEdit.getText())) {
                    Toast.makeText(MinistryActivity.this, "You need to enter something", Toast.LENGTH_SHORT).show();
                } else {
                    int count = Integer.parseInt(String.valueOf(wantToBuildRailroadEdit.getText()));
                    if (country.getMinistryOfTransportation().getFreeBuilders() >= MinistryOfTransportation.getRailroadBuildersNeed() * count) {
                        Toast.makeText(MinistryActivity.this, "We'll build " + count + " kms of railroad", Toast.LENGTH_SHORT).show();
                        country.getMinistryOfTransportation().buildRailroad(count);
                    } else {
                        Toast.makeText(MinistryActivity.this, "That is too much building", Toast.LENGTH_SHORT).show();
                    }
                }
                MinistryActivity.this.recreate();
            });

            EditText wantToBuildRoadEdit = new EditText(this);
            wantToBuildRoadEdit.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT));
            wantToBuildRoadEdit.setInputType(InputType.TYPE_CLASS_NUMBER);
            wantToBuildRoadEdit.setHint("How much kms of road want to build?");

            Button buttonBuildRoad = new Button(this);
            buttonBuildRoad.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT));
            buttonBuildRoad.setText("Build road");
            buttonBuildRoad.setOnClickListener(view -> {
                if (TextUtils.isEmpty(wantToBuildRailroadEdit.getText())) {
                    Toast.makeText(MinistryActivity.this, "You need to enter something", Toast.LENGTH_SHORT).show();
                } else {
                    int count = Integer.parseInt(String.valueOf(wantToBuildRailroadEdit.getText()));
                    if (country.getMinistryOfTransportation().getFreeBuilders() >= MinistryOfTransportation.getRoadBuildersNeed() * count) {
                        Toast.makeText(MinistryActivity.this, "We'll build " + count + " kms of road", Toast.LENGTH_SHORT).show();
                        country.getMinistryOfTransportation().buildRoad(count);
                    } else {
                        Toast.makeText(MinistryActivity.this, "That is too much building", Toast.LENGTH_SHORT).show();
                    }
                }
                MinistryActivity.this.recreate();
            });


            TextView buildingQueue = new TextView(this);
            buildingQueue.setText("Building queue: \n" + transportation.queueToString());

            buttonLayout.addView(buildersLimitEdit);
            buttonLayout.addView(buttonConfirmBuildersLimitEdit);
            buttonLayout.addView(buildersSalaryEdit);
            buttonLayout.addView(buttonConfirmBuildersSalaryEdit);

            buttonLayout.addView(wantToBuildAirportsEdit);
            buttonLayout.addView(buttonBuildAirport);
            buttonLayout.addView(wantToBuildPortsEdit);
            buttonLayout.addView(buttonBuildPort);
            buttonLayout.addView(wantToBuildRailroadEdit);
            buttonLayout.addView(buttonBuildRailroad);
            buttonLayout.addView(wantToBuildRoadEdit);
            buttonLayout.addView(buttonBuildRoad);

            buttonLayout.addView(buildingQueue);


        } else if (MainGameMenuActivity.selectedMinistry == 8) {
            ministry = country.getMinistryOfCulture();

            ministryFunding();

            MinistryOfCulture culture = (MinistryOfCulture) ministry;

            EditText culturalWorkersLimitEdit = new EditText(this);
            culturalWorkersLimitEdit.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT));
            culturalWorkersLimitEdit.setInputType(InputType.TYPE_CLASS_NUMBER);
            culturalWorkersLimitEdit.setHint("Enter limit of cultural workers");

            Button buttonConfirmCulturalWorkersLimitEdit = new Button(this);
            buttonConfirmCulturalWorkersLimitEdit.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT));
            buttonConfirmCulturalWorkersLimitEdit.setText("Confirm limit");
            buttonConfirmCulturalWorkersLimitEdit.setOnClickListener(view -> {
                if (TextUtils.isEmpty(culturalWorkersLimitEdit.getText())) {
                    Toast.makeText(MinistryActivity.this, "You need to enter something", Toast.LENGTH_SHORT).show();
                } else {
                    Integer limit = Integer.parseInt(String.valueOf(culturalWorkersLimitEdit.getText()));
                    if (limit > culture.getMaximumCulturalWorkersLimit() || limit < 0) {
                        Toast.makeText(MinistryActivity.this, ("Have you lost your mind, " + (country.getRuler().getSex() == Sex.MALE ? "sir?" : "ma'am?")), Toast.LENGTH_SHORT).show();
                    } else {
                        culture.setCulturalWorkersLimit(limit);
                        MinistryActivity.this.recreate();
                    }
                }
            });

            EditText culturalWorkersSalaryEdit = new EditText(this);
            culturalWorkersSalaryEdit.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT));
            culturalWorkersSalaryEdit.setInputType(InputType.TYPE_CLASS_NUMBER);
            culturalWorkersSalaryEdit.setHint("Enter salary of cultural workers");

            Button buttonConfirmCulturalWorkersSalaryEdit = new Button(this);
            buttonConfirmCulturalWorkersSalaryEdit.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT));
            buttonConfirmCulturalWorkersSalaryEdit.setText("Confirm salary");
            buttonConfirmCulturalWorkersSalaryEdit.setOnClickListener(view -> {
                if (TextUtils.isEmpty(culturalWorkersSalaryEdit.getText())) {
                    Toast.makeText(MinistryActivity.this, "You need to enter something", Toast.LENGTH_SHORT).show();
                } else {
                    Float salary = Float.parseFloat(String.valueOf(culturalWorkersSalaryEdit.getText()));
                    if (salary > (culture.getCulturalWorkersSalaryNeed() * 10) || salary < 0) {
                        Toast.makeText(MinistryActivity.this, ("Have you lost your mind, " + (country.getRuler().getSex() == Sex.MALE ? "sir?" : "ma'am?")), Toast.LENGTH_SHORT).show();
                    } else {
                        culture.setCulturalWorkersSalary(salary);
                        MinistryActivity.this.recreate();
                    }
                }
            });

            EditText wantToBuildMonumentsEdit = new EditText(this);
            wantToBuildMonumentsEdit.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT));
            wantToBuildMonumentsEdit.setInputType(InputType.TYPE_CLASS_NUMBER);
            wantToBuildMonumentsEdit.setHint("How much monuments want to build?");

            Button buttonBuildMonument = new Button(this);
            buttonBuildMonument.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT));
            buttonBuildMonument.setText("Build monument");
            buttonBuildMonument.setOnClickListener(view -> {
                if (TextUtils.isEmpty(wantToBuildMonumentsEdit.getText())) {
                    Toast.makeText(MinistryActivity.this, "You need to enter something", Toast.LENGTH_SHORT).show();
                } else {
                    int count = Integer.parseInt(String.valueOf(wantToBuildMonumentsEdit.getText()));
                    if (country.getMinistryOfTransportation().getFreeBuilders() >= MinistryOfTransportation.getMonumentBuildersNeed() * count) {
                        Toast.makeText(MinistryActivity.this, "We'll build " + count + " monuments", Toast.LENGTH_SHORT).show();
                        country.getMinistryOfTransportation().buildMonument(count);
                    } else {
                        Toast.makeText(MinistryActivity.this, "That is too much building", Toast.LENGTH_SHORT).show();
                    }
                }

            });

            buttonLayout.addView(culturalWorkersLimitEdit);
            buttonLayout.addView(buttonConfirmCulturalWorkersLimitEdit);
            buttonLayout.addView(culturalWorkersSalaryEdit);
            buttonLayout.addView(buttonConfirmCulturalWorkersSalaryEdit);
            buttonLayout.addView(wantToBuildMonumentsEdit);
            buttonLayout.addView(buttonBuildMonument);


        } else if (MainGameMenuActivity.selectedMinistry == 9) {
            ministry = country.getMinistryOfInternalAffairs();

            ministryFunding();

            MinistryOfInternalAffairs internalAffairs = (MinistryOfInternalAffairs) ministry;

            EditText policemenLimitEdit = new EditText(this);
            policemenLimitEdit.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT));
            policemenLimitEdit.setInputType(InputType.TYPE_CLASS_NUMBER);
            policemenLimitEdit.setHint("Enter limit of policemen");

            Button buttonConfirmPolicemenLimitEdit = new Button(this);
            buttonConfirmPolicemenLimitEdit.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT));
            buttonConfirmPolicemenLimitEdit.setText("Confirm limit");
            buttonConfirmPolicemenLimitEdit.setOnClickListener(view -> {
                if (TextUtils.isEmpty(policemenLimitEdit.getText())) {
                    Toast.makeText(MinistryActivity.this, "You need to enter something", Toast.LENGTH_SHORT).show();
                } else {
                    Integer limit = Integer.parseInt(String.valueOf(policemenLimitEdit.getText()));
                    if (limit > internalAffairs.getMaximumPolicemenLimit() || limit < 0) {
                        Toast.makeText(MinistryActivity.this, ("Have you lost your mind, " + (country.getRuler().getSex() == Sex.MALE ? "sir?" : "ma'am?")), Toast.LENGTH_SHORT).show();
                    } else {
                        internalAffairs.setPolicemenLimit(limit);
                        MinistryActivity.this.recreate();
                    }
                }
            });

            EditText policemenSalaryEdit = new EditText(this);
            policemenSalaryEdit.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT));
            policemenSalaryEdit.setInputType(InputType.TYPE_CLASS_NUMBER);
            policemenSalaryEdit.setHint("Enter salary of policemen");

            Button buttonConfirmPolicemenSalaryEdit = new Button(this);
            buttonConfirmPolicemenSalaryEdit.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT));
            buttonConfirmPolicemenSalaryEdit.setText("Confirm salary");
            buttonConfirmPolicemenSalaryEdit.setOnClickListener(view -> {
                if (TextUtils.isEmpty(policemenSalaryEdit.getText())) {
                    Toast.makeText(MinistryActivity.this, "You need to enter something", Toast.LENGTH_SHORT).show();
                } else {
                    Float salary = Float.parseFloat(String.valueOf(policemenSalaryEdit.getText()));
                    if (salary > (internalAffairs.getPolicemenSalaryNeed() * 10) || salary < 0) {
                        Toast.makeText(MinistryActivity.this, ("Have you lost your mind, " + (country.getRuler().getSex() == Sex.MALE ? "sir?" : "ma'am?")), Toast.LENGTH_SHORT).show();
                    } else {
                        internalAffairs.setPolicemenSalary(salary);
                        MinistryActivity.this.recreate();
                    }
                }
            });

            EditText wantToBuildDepartmentsEdit = new EditText(this);
            wantToBuildDepartmentsEdit.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT));
            wantToBuildDepartmentsEdit.setInputType(InputType.TYPE_CLASS_NUMBER);
            wantToBuildDepartmentsEdit.setHint("How much departments want to build?");

            Button buttonBuildDepartment = new Button(this);
            buttonBuildDepartment.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT));
            buttonBuildDepartment.setText("Build monument");
            buttonBuildDepartment.setOnClickListener(view -> {
                if (TextUtils.isEmpty(wantToBuildDepartmentsEdit.getText())) {
                    Toast.makeText(MinistryActivity.this, "You need to enter something", Toast.LENGTH_SHORT).show();
                } else {
                    int count = Integer.parseInt(String.valueOf(wantToBuildDepartmentsEdit.getText()));
                    if (country.getMinistryOfTransportation().getFreeBuilders() >= MinistryOfTransportation.getDepartmentBuildersNeed() * count) {
                        Toast.makeText(MinistryActivity.this, "We'll build " + count + " monuments", Toast.LENGTH_SHORT).show();
                        country.getMinistryOfTransportation().buildDepartment(count);
                    } else {
                        Toast.makeText(MinistryActivity.this, "That is too much building", Toast.LENGTH_SHORT).show();
                    }
                }
            });

            TextView securityText = new TextView(this);
            securityText.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT));
            securityText.setText("Level of security");


            Slider securitySlider = new Slider(this);
            securitySlider.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT));
            securitySlider.setValueFrom(1);
            securitySlider.setValue(1);
            securitySlider.setValueTo(6);
            securitySlider.setStepSize(1);

            Button securityButton = new Button(this);
            securitySlider.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT));
            securityButton.setText("Confirm security level");
            securityButton.setOnClickListener(view -> {
                internalAffairs.setLevelOfSecurity((int) securitySlider.getValue());
                MinistryActivity.this.recreate();
            });

            buttonLayout.addView(policemenLimitEdit);
            buttonLayout.addView(buttonConfirmPolicemenLimitEdit);
            buttonLayout.addView(policemenSalaryEdit);
            buttonLayout.addView(buttonConfirmPolicemenSalaryEdit);
            buttonLayout.addView(wantToBuildDepartmentsEdit);
            buttonLayout.addView(buttonBuildDepartment);

            buttonLayout.addView(securityText);
            buttonLayout.addView(securitySlider);
            buttonLayout.addView(securityButton);
        } else if (MainGameMenuActivity.selectedMinistry == 10) {
            ministry = country.getMinistryOfJustice();

            ministryFunding();

            MinistryOfJustice justice = (MinistryOfJustice) ministry;

            EditText judgesLimitEdit = new EditText(this);
            judgesLimitEdit.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT));
            judgesLimitEdit.setInputType(InputType.TYPE_CLASS_NUMBER);
            judgesLimitEdit.setHint("Enter limit of judges");

            Button buttonConfirmJudgesLimitEdit = new Button(this);
            buttonConfirmJudgesLimitEdit.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT));
            buttonConfirmJudgesLimitEdit.setText("Confirm limit");
            buttonConfirmJudgesLimitEdit.setOnClickListener(view -> {
                if (TextUtils.isEmpty(judgesLimitEdit.getText())) {
                    Toast.makeText(MinistryActivity.this, "You need to enter something", Toast.LENGTH_SHORT).show();
                } else {
                    Integer limit = Integer.parseInt(String.valueOf(judgesLimitEdit.getText()));
                    if (limit > justice.getMaximumJudgesLimit() || limit < 0) {
                        Toast.makeText(MinistryActivity.this, ("Have you lost your mind, " + (country.getRuler().getSex() == Sex.MALE ? "sir?" : "ma'am?")), Toast.LENGTH_SHORT).show();
                    } else {
                        justice.setJudgesLimit(limit);
                        MinistryActivity.this.recreate();
                    }
                }
            });

            EditText judgesSalaryEdit = new EditText(this);
            judgesSalaryEdit.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT));
            judgesSalaryEdit.setInputType(InputType.TYPE_CLASS_NUMBER);
            judgesSalaryEdit.setHint("Enter salary of judges");

            Button buttonConfirmJudgesSalaryEdit = new Button(this);
            buttonConfirmJudgesSalaryEdit.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT));
            buttonConfirmJudgesSalaryEdit.setText("Confirm salary");
            buttonConfirmJudgesSalaryEdit.setOnClickListener(view -> {
                if (TextUtils.isEmpty(judgesSalaryEdit.getText())) {
                    Toast.makeText(MinistryActivity.this, "You need to enter something", Toast.LENGTH_SHORT).show();
                } else {
                    Float salary = Float.parseFloat(String.valueOf(judgesSalaryEdit.getText()));
                    if (salary > (justice.getJudgesSalaryNeed() * 10) || salary < 0) {
                        Toast.makeText(MinistryActivity.this, ("Have you lost your mind, " + (country.getRuler().getSex() == Sex.MALE ? "sir?" : "ma'am?")), Toast.LENGTH_SHORT).show();
                    } else {
                        justice.setJudgesSalary(salary);
                        MinistryActivity.this.recreate();
                    }
                }
            });


            EditText wantToBuildPrionsEdit = new EditText(this);
            wantToBuildPrionsEdit.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT));
            wantToBuildPrionsEdit.setInputType(InputType.TYPE_CLASS_NUMBER);
            wantToBuildPrionsEdit.setHint("How much prisons want to build?");

            Button buttonBuildPrison = new Button(this);
            buttonBuildPrison.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT));
            buttonBuildPrison.setText("Build prison");
            buttonBuildPrison.setOnClickListener(view -> {
                if (TextUtils.isEmpty(wantToBuildPrionsEdit.getText())) {
                    Toast.makeText(MinistryActivity.this, "You need to enter something", Toast.LENGTH_SHORT).show();
                } else {
                    int count = Integer.parseInt(String.valueOf(wantToBuildPrionsEdit.getText()));
                    if (country.getMinistryOfTransportation().getFreeBuilders() >= MinistryOfTransportation.getPrisonsBuildersNeed() * count) {
                        Toast.makeText(MinistryActivity.this, "We'll build " + count + " prisons", Toast.LENGTH_SHORT).show();
                        country.getMinistryOfTransportation().buildPrison(count);
                    } else {
                        Toast.makeText(MinistryActivity.this, "That is too much building", Toast.LENGTH_SHORT).show();
                    }
                }
            });

            CheckBox deathPenalty = new CheckBox(this);
            deathPenalty.setText("Death Penalty");
            deathPenalty.setChecked(justice.isDeathPenalty());
            deathPenalty.setOnCheckedChangeListener((compoundButton, b) -> {
                justice.setDeathPenalty(b);
                MinistryActivity.this.recreate();
            });

            TextView libertyText = new TextView(this);
            libertyText.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT));
            libertyText.setText("Level of judges liberty");


            Slider libertySlider = new Slider(this);
            libertySlider.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT));
            libertySlider.setValueFrom(1);
            libertySlider.setValue(1);
            libertySlider.setValueTo(6);
            libertySlider.setStepSize(1);

            Button libertyButton = new Button(this);
            libertySlider.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT));
            libertyButton.setText("Confirm liberty level");
            libertyButton.setOnClickListener(view -> {
                justice.setLevelOfJudgesLiberty((int) libertySlider.getValue());
                MinistryActivity.this.recreate();
            });

            buttonLayout.addView(judgesLimitEdit);
            buttonLayout.addView(buttonConfirmJudgesLimitEdit);
            buttonLayout.addView(judgesSalaryEdit);
            buttonLayout.addView(buttonConfirmJudgesSalaryEdit);

            buttonLayout.addView(wantToBuildPrionsEdit);
            buttonLayout.addView(buttonBuildPrison);

            buttonLayout.addView(deathPenalty);

            buttonLayout.addView(libertyText);
            buttonLayout.addView(libertySlider);
            buttonLayout.addView(libertyButton);
        } else if (MainGameMenuActivity.selectedMinistry == 11) {
            ministry = country.getParliament();

            // TODO: Parliament
        } else if (MainGameMenuActivity.selectedMinistry == 12) {
            ministry = country.getNationalIntelligence();

            ministryFunding();

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

            ministryFunding();

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

    private void ministryFunding() {
        EditText ministryFundingEdit = new EditText(this);
        ministryFundingEdit.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT));
        ministryFundingEdit.setInputType(InputType.TYPE_CLASS_NUMBER);
        ministryFundingEdit.setHint("Enter funding of the ministry");

        Button buttonConfirmMinistryFundingEdit = new Button(this);
        buttonConfirmMinistryFundingEdit.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT));
        buttonConfirmMinistryFundingEdit.setText("Confirm funding");
        buttonConfirmMinistryFundingEdit.setOnClickListener(view -> {
            if (TextUtils.isEmpty(ministryFundingEdit.getText())) {
                Toast.makeText(MinistryActivity.this, "You need to enter something", Toast.LENGTH_SHORT).show();
            } else {
                Float funding = Float.parseFloat(String.valueOf(ministryFundingEdit.getText()));
                if (funding > (ministry.getGeneralBudgetNeed() * 2) || funding < 0) {
                    Toast.makeText(MinistryActivity.this, ("Have you lost your mind, " + (country.getRuler().getSex() == Sex.MALE ? "sir?" : "ma'am?")), Toast.LENGTH_SHORT).show();
                } else {
                    ministry.setMinistryFunding(funding);
                    MinistryActivity.this.recreate();
                }
            }
        });

        buttonLayout.addView(ministryFundingEdit);
        buttonLayout.addView(buttonConfirmMinistryFundingEdit);
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

