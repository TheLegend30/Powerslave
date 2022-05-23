package com.example.powerslave;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import java.util.Random;

public class ChooseRulerActivity extends AppCompatActivity implements View.OnClickListener {
    private ImageButton backArrowChRul;
    private ImageButton imageButtonRandomName;
    private EditText editTextName;
    private EditText editTextSurname;
    private RadioGroup radioGroupSexChRul;
    private RadioButton radioButtonSexMaleChRul;
    private RadioButton radioButtonSexFemaleChRul;
    private MainActivity.Country country = MainActivity.countries.get(StartActivity.countrySpinnerStart.getSelectedItemPosition());

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_choose_ruler);

        backArrowChRul = findViewById(R.id.backArrowChRul);
        imageButtonRandomName = findViewById(R.id.imageButtonRandomName);
        editTextName = findViewById(R.id.editTextName);
        editTextSurname = findViewById(R.id.editTextSurname);
        radioGroupSexChRul = findViewById(R.id.radioGroupSexChRul);
        radioButtonSexMaleChRul = findViewById(R.id.radioButtonSexMaleChRul);
        radioButtonSexFemaleChRul = findViewById(R.id.radioButtonSexFemaleChRul);


        backArrowChRul.setOnClickListener(this);
        imageButtonRandomName.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        if (view.getId() == backArrowChRul.getId()) {
            ChooseRulerActivity.this.finish();
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