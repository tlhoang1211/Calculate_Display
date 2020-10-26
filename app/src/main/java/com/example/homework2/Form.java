package com.example.homework2;

import android.annotation.SuppressLint;
import android.app.DatePickerDialog;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.Calendar;

public class Form extends AppCompatActivity {
    CheckBox terms;
    RadioGroup radioBtnGroup;
    RadioButton male;
    RadioButton female;
    Button registerBtn;
    EditText firstName;
    EditText lastName;
    EditText address;
    EditText email;
    TextView value;
    String respondStr;
    Calendar calendar;
    TextView birthday;
    int year, month, day;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.form_layout);

        terms = (CheckBox) findViewById(R.id.terms);
        registerBtn = (Button) findViewById(R.id.registerBtn);
        radioBtnGroup = (RadioGroup) findViewById(R.id.radioBtnGroup);
        male = (RadioButton) findViewById(R.id.male);
        female = (RadioButton) findViewById(R.id.female);
        firstName = findViewById(R.id.firstName);
        lastName =  findViewById(R.id.lastName);
        address = findViewById(R.id.address);
        email = findViewById(R.id.email);

        birthday = (TextView) findViewById(R.id.birthday);
        calendar = Calendar.getInstance();
        year = calendar.get(Calendar.YEAR);
        month = calendar.get(Calendar.MONTH);
        day = calendar.get(Calendar.DAY_OF_MONTH);
        showDate(year, month + 1, day);

        value = findViewById(R.id.value);

        registerBtn.setOnClickListener(new View.OnClickListener() {
            @SuppressLint("SetTextI18n")
            @Override
            public void onClick(View v) {
                if (!terms.isChecked()) {
                    respondStr = "";
                    respondStr = "Please accept the term to continue! ";
                    value.setText(respondStr);
                } else {
                    respondStr = "";
                    if (firstName.getText().length() == 0) {
                        respondStr += "First name; ";
                    }
                    if (lastName.getText().length() == 0) {
                        respondStr += "Last name; ";
                    }
                    if (address.getText().length() == 0) {
                        respondStr += "Address; ";
                    }
                    if (email.getText().length() == 0) {
                        respondStr += "Email; ";
                    }
                    if (birthday.getText().length() == 0) {
                        respondStr += "Birthday; ";
                    }
                    int radioId = radioBtnGroup.getCheckedRadioButtonId();
                    if (male.getId() != radioId && female.getId() != radioId){
                        respondStr += "Gender; ";
                    }
                    value.setText("Your " + removeLastCharacter(respondStr) + " is missing, please fill all the form!");
                    if (firstName.getText().length() != 0 && lastName.getText().length() != 0 && address.getText().length() != 0 && email.getText().length() != 0 && birthday.getText().length() != 0) {
                        value.setText("Your information has been successfully saved!");
                        value.setTextColor(Color.parseColor("#2ecc71"));
                    }
                }
            }
        });
    }

    @SuppressWarnings("deprecation")
    public void setDate(View view) {
        showDialog(999);
        Toast.makeText(getApplicationContext(), "ca",
                Toast.LENGTH_SHORT)
                .show();
    }

    @Override
    protected DatePickerDialog onCreateDialog(int id) {
        // TODO Auto-generated method stub
        if (id == 999) {
            return new DatePickerDialog(this,
                    myDateListener, year, month, day);
        }
        return null;
    }

    private DatePickerDialog.OnDateSetListener myDateListener = new DatePickerDialog.OnDateSetListener() {
        @Override
        public void onDateSet(DatePicker view,
                              int year, int month, int day) {
            showDate(year, month+1, day);
        }
    };

    private void showDate(int year, int month, int day) {
        birthday.setText(new StringBuilder().append(day).append("/")
                .append(month).append("/").append(year));
    }

    public static String removeLastCharacter(String str) {
        String result = null;
        if ((str != null) && (str.length() > 0)) {
            result = str.substring(0, str.length() - 2);
        }
        return result;
    }
}
