package com.example.powerslave;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.TextView;

import java.util.Random;

public class ChooseRulerActivity extends AppCompatActivity implements View.OnClickListener {
    private ImageButton backArrowChRul;
    private ImageButton forwardArrowChRul;
    private ImageButton imageButtonRandomName;
    private EditText editTextName;
    private EditText editTextSurname;
    private RadioGroup radioGroupSexChRul;
    private RadioButton radioButtonSexMaleChRul;
    private RadioButton radioButtonSexFemaleChRul;
    private Spinner spinnerIdeology;
    private TextView textViewIdeoDescChRul;
    private MainActivity.Country country = MainActivity.countries.get(StartActivity.countrySpinnerStart.getSelectedItemPosition());
    public Person ruler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_choose_ruler);

        backArrowChRul = findViewById(R.id.backArrowChRul);
        forwardArrowChRul = findViewById(R.id.forwardArrowChRul);
        imageButtonRandomName = findViewById(R.id.imageButtonRandomName);
        editTextName = findViewById(R.id.editTextName);
        editTextSurname = findViewById(R.id.editTextSurname);
        radioGroupSexChRul = findViewById(R.id.radioGroupSexChRul);
        radioButtonSexMaleChRul = findViewById(R.id.radioButtonSexMaleChRul);
        radioButtonSexFemaleChRul = findViewById(R.id.radioButtonSexFemaleChRul);
        spinnerIdeology = findViewById(R.id.spinnerIdeology);
        textViewIdeoDescChRul = findViewById(R.id.textViewIdeoDescChRul);


        backArrowChRul.setOnClickListener(this);
        forwardArrowChRul.setOnClickListener(this);
        imageButtonRandomName.setOnClickListener(this);

        ArrayAdapter ideologyAdapter = new ArrayAdapter(this, android.R.layout.simple_spinner_dropdown_item, getResources().getStringArray(R.array.ideology));

        spinnerIdeology.setAdapter(ideologyAdapter);
        spinnerIdeology.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                textViewIdeoDescChRul.setText(getResources().getStringArray(R.array.ideology_desc)[spinnerIdeology.getSelectedItemPosition()]);
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
    }

    @Override
    public void onClick(View view) {
        if (view.getId() == backArrowChRul.getId()) {
            ChooseRulerActivity.this.finish();
        } else if (view.getId() == forwardArrowChRul.getId()) {
            Sex sex;
            if (radioGroupSexChRul.getCheckedRadioButtonId() == radioButtonSexMaleChRul.getId()) {
                sex = Sex.MALE;
            } else {
                sex = Sex.FEMALE;
            }
            ruler = new Person(editTextName.getText().toString(), editTextSurname.getText().toString(), sex, country.getContinent(), Ideology.valueOf(getResources().getStringArray(R.array.ideology)[spinnerIdeology.getSelectedItemPosition()].toString()));
            System.out.println(ruler);
        } else if (view.getId() == imageButtonRandomName.getId()) {
            Random random = new Random();
            switch (country.getContinent()) {
                case EY:
                    if (radioGroupSexChRul.getCheckedRadioButtonId() == radioButtonSexMaleChRul.getId()) {
                        editTextName.setText(getResources().getStringArray(R.array.eyut_names_m)[random.nextInt(getResources().getStringArray(R.array.eyut_names_m).length)]);
                    } else {
                        editTextName.setText(getResources().getStringArray(R.array.eyut_names_f)[random.nextInt(getResources().getStringArray(R.array.eyut_names_f).length)]);
                    }
                    editTextSurname.setText(getResources().getStringArray(R.array.eyut_surnames)[random.nextInt(getResources().getStringArray(R.array.eyut_surnames).length)]);
                    break;
                case NY:
                    if (radioGroupSexChRul.getCheckedRadioButtonId() == radioButtonSexMaleChRul.getId()) {
                        editTextName.setText(getResources().getStringArray(R.array.nyut_names_m)[random.nextInt(getResources().getStringArray(R.array.nyut_names_m).length)]);
                    } else {
                        editTextName.setText(getResources().getStringArray(R.array.nyut_names_f)[random.nextInt(getResources().getStringArray(R.array.nyut_names_f).length)]);
                    }
                    editTextSurname.setText(getResources().getStringArray(R.array.nyut_surnames)[random.nextInt(getResources().getStringArray(R.array.nyut_surnames).length)]);
                    break;
                case SY:
                    if (radioGroupSexChRul.getCheckedRadioButtonId() == radioButtonSexMaleChRul.getId()) {
                        editTextName.setText(getResources().getStringArray(R.array.syut_names_m)[random.nextInt(getResources().getStringArray(R.array.syut_names_m).length)]);
                    } else {
                        editTextName.setText(getResources().getStringArray(R.array.syut_names_f)[random.nextInt(getResources().getStringArray(R.array.syut_names_f).length)]);
                    }
                    editTextSurname.setText(getResources().getStringArray(R.array.syut_surnames)[random.nextInt(getResources().getStringArray(R.array.syut_surnames).length)]);
                    break;
                case WY:
                    if (radioGroupSexChRul.getCheckedRadioButtonId() == radioButtonSexMaleChRul.getId()) {
                        editTextName.setText(getResources().getStringArray(R.array.wyut_names_m)[random.nextInt(getResources().getStringArray(R.array.wyut_names_m).length)]);
                    } else {
                        editTextName.setText(getResources().getStringArray(R.array.wyut_names_f)[random.nextInt(getResources().getStringArray(R.array.wyut_names_f).length)]);
                    }
                    editTextSurname.setText(getResources().getStringArray(R.array.wyut_surnames)[random.nextInt(getResources().getStringArray(R.array.wyut_surnames).length)]);
                    break;
                case IB:
                    if (radioGroupSexChRul.getCheckedRadioButtonId() == radioButtonSexMaleChRul.getId()) {
                        editTextName.setText(getResources().getStringArray(R.array.ibru_names_m)[random.nextInt(getResources().getStringArray(R.array.ibru_names_m).length)]);
                    } else {
                        editTextName.setText(getResources().getStringArray(R.array.ibru_names_f)[random.nextInt(getResources().getStringArray(R.array.ibru_names_f).length)]);
                    }
                    editTextSurname.setText(getResources().getStringArray(R.array.ibru_surnames)[random.nextInt(getResources().getStringArray(R.array.ibru_surnames).length)]);
                    break;
                case OB:
                    if (radioGroupSexChRul.getCheckedRadioButtonId() == radioButtonSexMaleChRul.getId()) {
                        editTextName.setText(getResources().getStringArray(R.array.obru_names_m)[random.nextInt(getResources().getStringArray(R.array.obru_names_m).length)]);
                    } else {
                        editTextName.setText(getResources().getStringArray(R.array.obru_names_f)[random.nextInt(getResources().getStringArray(R.array.obru_names_f).length)]);
                    }
                    editTextSurname.setText(getResources().getStringArray(R.array.obru_surnames)[random.nextInt(getResources().getStringArray(R.array.obru_surnames).length)]);
                    break;
                case CA:
                    if (radioGroupSexChRul.getCheckedRadioButtonId() == radioButtonSexMaleChRul.getId()) {
                        editTextName.setText(getResources().getStringArray(R.array.cadu_names_m)[random.nextInt(getResources().getStringArray(R.array.cadu_names_m).length)]);
                    } else {
                        editTextName.setText(getResources().getStringArray(R.array.cadu_names_f)[random.nextInt(getResources().getStringArray(R.array.cadu_names_f).length)]);
                    }
                    editTextSurname.setText(getResources().getStringArray(R.array.cadu_surnames)[random.nextInt(getResources().getStringArray(R.array.cadu_surnames).length)]);
                    break;
                case FA:
                    if (radioGroupSexChRul.getCheckedRadioButtonId() == radioButtonSexMaleChRul.getId()) {
                        editTextName.setText(getResources().getStringArray(R.array.fadu_names_m)[random.nextInt(getResources().getStringArray(R.array.fadu_names_m).length)]);
                    } else {
                        editTextName.setText(getResources().getStringArray(R.array.fadu_names_f)[random.nextInt(getResources().getStringArray(R.array.fadu_names_f).length)]);
                    }
                    editTextSurname.setText(getResources().getStringArray(R.array.fadu_surnames)[random.nextInt(getResources().getStringArray(R.array.fadu_surnames).length)]);
                    break;
                case ME:
                    if (radioGroupSexChRul.getCheckedRadioButtonId() == radioButtonSexMaleChRul.getId()) {
                        editTextName.setText(getResources().getStringArray(R.array.mehe_names_m)[random.nextInt(getResources().getStringArray(R.array.mehe_names_m).length)]);
                    } else {
                        editTextName.setText(getResources().getStringArray(R.array.mehe_names_f)[random.nextInt(getResources().getStringArray(R.array.mehe_names_f).length)]);
                    }
                    editTextSurname.setText(getResources().getStringArray(R.array.mehe_surnames)[random.nextInt(getResources().getStringArray(R.array.mehe_surnames).length)]);
                    break;
                case GE:
                    if (radioGroupSexChRul.getCheckedRadioButtonId() == radioButtonSexMaleChRul.getId()) {
                        editTextName.setText(getResources().getStringArray(R.array.gewi_names_m)[random.nextInt(getResources().getStringArray(R.array.gewi_names_m).length)]);
                    } else {
                        editTextName.setText(getResources().getStringArray(R.array.gewi_names_f)[random.nextInt(getResources().getStringArray(R.array.gewi_names_f).length)]);
                    }
                    editTextSurname.setText(getResources().getStringArray(R.array.gewi_surnames)[random.nextInt(getResources().getStringArray(R.array.gewi_surnames).length)]);
                    break;
            }
        }
    }
}