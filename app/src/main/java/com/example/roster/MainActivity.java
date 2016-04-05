package com.example.roster;

import android.app.Activity;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.InputType;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.SeekBar;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Calendar;

public class MainActivity extends Activity {
    private EditText birthday;
    private EditText personName;
    private CheckBox steadyCondition;
    private Spinner eyeColor;
    private RadioGroup shirtSize;
    private SeekBar pantsizeSeekBar;
    private TextView pantsizeTextView;
    private SeekBar shirtsizeSeekBar;
    private TextView shirtsizeTextView;
    private SeekBar shoesizeSeekBar;
    private TextView shoesizeTextView;
    private SharedPreferences prefs;

    @Override
    protected void onCreate(Bundle savedInstanceState) {


        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        prefs = getSharedPreferences("rosterPreferences", MODE_PRIVATE);

        Initialization();

        pantsizeSeekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            int progress = 0;

            @Override
            public void onProgressChanged(SeekBar seekBar, int progresValue, boolean fromUser) {
                progress = progresValue;
                //Toast.makeText(getApplicationContext(), "Changing seekbar's progress", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {
                //     Toast.makeText(getApplicationContext(), "Started tracking seekbar", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
                pantsizeTextView.setText(" " + pantsizeSeekBar.getProgress() + "/" + pantsizeSeekBar.getMax());
                //  Toast.makeText(getApplicationContext(), "Stopped tracking seekbar", Toast.LENGTH_SHORT).show();
            }
        });

        shirtsizeSeekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            int progress = 0;

            @Override
            public void onProgressChanged(SeekBar seekBar, int progresValue, boolean fromUser) {
                progress = progresValue;
                //Toast.makeText(getApplicationContext(), "Changing seekbar's progress", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {
                //     Toast.makeText(getApplicationContext(), "Started tracking seekbar", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
                shirtsizeTextView.setText(" " + shirtsizeSeekBar.getProgress() + "/" + shirtsizeSeekBar.getMax());
                //  Toast.makeText(getApplicationContext(), "Stopped tracking seekbar", Toast.LENGTH_SHORT).show();
            }
        });

        shoesizeSeekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            int progress = 0;

            @Override
            public void onProgressChanged(SeekBar seekBar, int progresValue, boolean fromUser) {
                progress = progresValue;
                //Toast.makeText(getApplicationContext(), "Changing seekbar's progress", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {
                //     Toast.makeText(getApplicationContext(), "Started tracking seekbar", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
                shoesizeTextView.setText(" " + ( shoesizeSeekBar.getProgress() + 4) + "/" + ( shoesizeSeekBar.getMax() + 4 ));
                //  Toast.makeText(getApplicationContext(), "Stopped tracking seekbar", Toast.LENGTH_SHORT).show();
            }
        });

        birthday.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                birthdayoDialog().show();
            }
        });

        birthday.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (hasFocus) {
                    birthdayoDialog().show();
                }
            }
        });

    }

    protected Dialog birthdayoDialog(){
        Calendar calendar = Calendar.getInstance();
        Dialog dialog = null;
        DatePickerDialog.OnDateSetListener dateListener =
                new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker datePicker,
                                          int year, int month, int dayOfMonth) {
                        EditText editText =
                                (EditText) findViewById(R.id.birthdayEditText);
                        editText.setText(  year + "-" +
                                (month+1) + "-" + dayOfMonth );
                    }
                };

        dialog = new DatePickerDialog(this,
                dateListener,
                calendar.get(Calendar.YEAR),
                calendar.get(Calendar.MONTH),
                calendar.get(Calendar.DAY_OF_MONTH));
        return dialog;
    }

    public void  Initialization()
    {
        personName = (EditText)findViewById(R.id.nameEditText);
        personName.setText(prefs.getString("personName", ""));

        steadyCondition = (CheckBox)findViewById(R.id.steadyCheckBox);
        steadyCondition.setChecked(prefs.getBoolean("steadyCondition", false));

        eyeColor =(Spinner) findViewById(R.id.eyecolorSpinner);
        for (int i = 0; i < eyeColor.getCount(); i++) {
            if (eyeColor.getItemAtPosition(i).equals(prefs.getString("eyeColor","Brown"))) {
                eyeColor.setSelection(i);
                break;
            }
        }

        shirtSize = (RadioGroup)findViewById(R.id.shirRadioGroup);
        ((RadioButton)shirtSize.getChildAt(prefs.getInt("shirtSize", 0))).setChecked(true);

        birthday=(EditText)findViewById(R.id.birthdayEditText);
        birthday.setInputType(InputType.TYPE_NULL);
        birthday.setText(prefs.getString("birthDay", ""));

        pantsizeSeekBar = (SeekBar) findViewById(R.id.pantSeekBar);
        pantsizeTextView = (TextView) findViewById(R.id.psizetTextView);

        shirtsizeSeekBar = (SeekBar) findViewById(R.id.shirtSeekBar);
        shirtsizeTextView = (TextView) findViewById(R.id.shisizeTextview);

        shoesizeSeekBar = (SeekBar) findViewById(R.id.shoeseekBar);
        shoesizeTextView = (TextView) findViewById(R.id.shosizeTextView);

        int pantsize = prefs.getInt("pantsize", 0);
        int shirsize = prefs.getInt("shirtsize", 0);
        int shoesize = prefs.getInt("shoesize", 0);

        pantsizeTextView.setText(" " + pantsize + "/" + pantsizeSeekBar.getMax());
        shirtsizeTextView.setText(" " + shirsize + "/" + shirtsizeSeekBar.getMax());
        shoesizeTextView.setText(" " + (shoesize + 4) + "/" + (shoesizeSeekBar.getMax() + 4));

        pantsizeSeekBar.setProgress(pantsize);
        shirtsizeSeekBar.setProgress(shirsize);
        shoesizeSeekBar.setProgress(shoesize);
    }

    public void saveRoster(View v)
    {
        String name = personName.getText().toString();
        Boolean isChecked = steadyCondition.isChecked();
        String color = eyeColor.getSelectedItem().toString();
        String birthDay = birthday.getText().toString();
        int radioButtonID = shirtSize.indexOfChild(findViewById(shirtSize.getCheckedRadioButtonId()));
        int pantsize = pantsizeSeekBar.getProgress();
        int shirtsize = shirtsizeSeekBar.getProgress();
        int shoesize = shoesizeSeekBar.getProgress();

        // Save data to Preferences Store
        SharedPreferences.Editor editor = getSharedPreferences("rosterPreferences", MODE_PRIVATE).edit();
        editor.putString("personName",name);
        editor.putBoolean("steadyCondition", isChecked);
        editor.putString("eyeColor", color);
        editor.putString("birthDay", birthDay);
        editor.putInt("shirtSize", radioButtonID);
        editor.putInt("pantsize",pantsize);
        editor.putInt("shirtsize",shirtsize);
        editor.putInt("shoesize",shoesize);

        editor.commit();
    }
}





