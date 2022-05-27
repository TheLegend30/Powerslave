package com.example.powerslave;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import com.example.powerslave.person.Ruler;

public class MainGameMenuActivity extends AppCompatActivity {
    public static MainActivity.Country country = MainActivity.countries.get(StartActivity.countrySpinnerStart.getSelectedItemPosition());
    public static Ruler ruler = ChooseRulerActivity.ruler;
    public static boolean showAlertMessage = true;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_game_menu);

        ActionBar actionBar = getSupportActionBar();

        actionBar.setIcon(R.drawable.ic_settings_foreground);

        actionBar.setDisplayUseLogoEnabled(true);
        actionBar.setDisplayShowHomeEnabled(true);

        showAlertMessage();
    }

    private void showAlertMessage() {
        DialogInterface.OnClickListener dialogClickListener = (dialogInterface, i) -> {
            switch (i) {
                case Dialog.BUTTON_POSITIVE:
                    showAlertMessage = false;
                    break;
            }

        };
        AlertDialog.Builder builder = new AlertDialog.Builder(MainGameMenuActivity.this);
        if (showAlertMessage) {
            builder.setMessage("Welcome to the Power Slave! Now you're playing as ruler of country of " + country.getName() + ". Good luck in your decisions!")
                    .setPositiveButton("Understood", dialogClickListener)
                    .show();
        }
    }

    @Override
    public boolean onCreateOptionsMenu( Menu menu ) {

        getMenuInflater().inflate(R.menu.main, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected( @NonNull MenuItem item ) {

        switch (item.getItemId()){
            case R.id.exit:
                Intent intent = new Intent(MainGameMenuActivity.this, MainActivity.class);
                startActivity(intent);
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onBackPressed() {

    }

}