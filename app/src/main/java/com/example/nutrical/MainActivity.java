package com.example.nutrical;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.Toast;

import cn.pedant.SweetAlert.SweetAlertDialog;

public class MainActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener {

    private EditText nw_name, nw_age, nw_weight, nw_height;
    private Spinner spinner, spinnerweight, spinnerheight;
    private RadioGroup genderGroup;
    private RadioButton genderButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button btn_Create = findViewById(R.id.btn_create);
        nw_age = findViewById(R.id.nw_age);
        nw_name = findViewById(R.id.nw_name);
        nw_weight = findViewById(R.id.nw_weight);
        nw_height = findViewById(R.id.nw_height);
        genderGroup = findViewById(R.id.rgroup);
        spinner = findViewById(R.id.nw_AF);
        spinnerweight = findViewById(R.id.nw_weightmeasure);
        spinnerheight = findViewById(R.id.nw_heightmeasure);

        //when btn_Create is clicked
        btn_Create.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //IF VALIDATE FUNCTION RETURNS FALSE
                if (!validateName() || !validateAge() || !validateWeight() || !validateHeight()) {
                    Toast.makeText(MainActivity.this, "Cannot create user", Toast.LENGTH_SHORT).show();
                } else {
                    int selected_gender = genderGroup.getCheckedRadioButtonId();
                    genderButton = findViewById(selected_gender);
                    //WRITE DATA
                    newuserModel NewuserModel;

                    NewuserModel = new newuserModel(-1, nw_name.getText().toString(), genderButton.getText().toString(),
                            spinner.getSelectedItem().toString(), Integer.parseInt(nw_age.getText().toString()),
                            weightconv(), heightconv(),
                            bmrCalc());

                    DataBaseHelper dataBaseHelper = new DataBaseHelper(MainActivity.this);
                    boolean success = dataBaseHelper.addOne(NewuserModel);

                    //OPEN HOME PAGE
                    Intent intent_one = new Intent(MainActivity.this, Home.class);
                    startActivity(intent_one);
                    dataBaseHelper.close();

                    finish();
                }
            }
        });

        //put the items to the spinner
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                R.array.AFarray, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);
        spinner.setOnItemSelectedListener(this);

        //put items to the weight spinner
        ArrayAdapter<CharSequence> adapterWeight = ArrayAdapter.createFromResource(this,
                R.array.WArray, android.R.layout.simple_spinner_item);
        adapterWeight.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerweight.setAdapter(adapterWeight);
        spinnerweight.setOnItemSelectedListener(this);

        //put items to the height spinner
        ArrayAdapter<CharSequence> adapterHeight = ArrayAdapter.createFromResource(this,
                R.array.HArray, android.R.layout.simple_spinner_item);
        adapterHeight.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerheight.setAdapter(adapterHeight);
        spinnerheight.setOnItemSelectedListener(this);
    }

    //Toast- when an item in the spinner is selected, the word will hover
    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        /*String text = parent.getItemAtPosition(position).toString();
         Toast.makeText(parent.getContext(), text, Toast.LENGTH_SHORT).show();*/
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }

    //VALIDATE NAME
    private boolean validateName() {
        String valName = nw_name.getText().toString().trim();
        if (valName.isEmpty()) {
            nw_name.setError("Field can not be empty");
            return false;
        } else if (valName.length() > 20) {
            nw_name.setError("Username is too large!");
            return false;
        } else {
            nw_name.setError(null);
            return true;
        }
    }

    //VALIDATE AGE
    private boolean validateAge() {
        int valAge = Integer.parseInt(nw_age.getText().toString());
        String SvalAge = nw_age.getText().toString();

        if (valAge < 10 || valAge > 150) {
            nw_age.setError("Invalid Age");
            return false;
        }
        if (SvalAge.contains(" ") && (SvalAge.startsWith(" ") && SvalAge.endsWith(" "))) {
            nw_age.setError("No White spaces are allowed!");
            return false;
        }
        if (SvalAge.isEmpty()) {
            nw_age.setError("Field can not be empty");
            return false;
        } else
            return true;
    }

    //VALIDATE WEIGHT
    private boolean validateWeight() {
        float valWeight = Float.parseFloat(nw_weight.getText().toString());
        String SvalWeight = nw_weight.getText().toString();

        if (valWeight < 20 || valWeight > 442) {
            nw_weight.setError("Invalid Weight");
            return false;
        }
        if (SvalWeight.contains(" ") && (SvalWeight.startsWith(" ") && SvalWeight.endsWith(" "))) {
            nw_weight.setError("No White spaces are allowed!");
            return false;
        }
        if (SvalWeight.isEmpty()) {
            nw_weight.setError("Field can not be empty");
            return false;
        } else
            return true;
    }

    //VALIDATE HEIGHT
    private boolean validateHeight() {
        float valHeight = Float.parseFloat(nw_height.getText().toString());
        String SvalHeight = nw_height.getText().toString();

        if (valHeight < 50 || valHeight > 250) {
            nw_height.setError("Invalid Height");
            return false;
        }
        if (SvalHeight.contains(" ") && (SvalHeight.startsWith(" ") && SvalHeight.endsWith(" "))) {
            nw_height.setError("No White spaces are allowed!");
            return false;
        }
        if (SvalHeight.isEmpty()) {
            nw_height.setError("Field can not be empty");
            return false;
        } else
            return true;
    }

    //BMR CALCULATIONS
    private double bmrCalc() {
        int valAge = Integer.parseInt(nw_age.getText().toString());
        float valHeight = Float.parseFloat(nw_height.getText().toString());
        float valWeight = Float.parseFloat(nw_weight.getText().toString());
        String valGender = genderButton.getText().toString();

        double calcBmr = 0, totBmr = 0;

        if (valGender.equals("Female")) {
            calcBmr = (((10 * valWeight) + (6.25 * valHeight)) - (5 * valAge)) - 161;
            totBmr = calCalc(calcBmr);
        } else {
            calcBmr = (10 * valWeight) + (6.25 * valHeight) - (5 * valAge) + 5;
            totBmr = calCalc(calcBmr);
        }
        return totBmr;
    }

    //CALORIE REQUIREMENTS CALCULATIONS
    private double calCalc(double bmr) {
        String valAF = spinner.getSelectedItem().toString();
        switch (valAF) {
            case "Sedentary":
                bmr = bmr * 1.2;
                break;
            case "Lightly Active":
                bmr = bmr * 1.375;
                break;
            case "Moderately Active":
                bmr = bmr * 1.55;
                break;
            case "Very Active":
                bmr = bmr * 1.725;
                break;
            default:
                bmr = bmr * 1.9;
                break;
        }
        return bmr;
    }

    private double weightconv() {
        //to kilograms
        float valWeight = Float.parseFloat(nw_weight.getText().toString());
        String sw = spinnerweight.getSelectedItem().toString();
        double finweight = 0;
        if ("lb".equals(sw)) {
            finweight = valWeight * 0.453592;
        } else {
            finweight = valWeight;
        }
        return finweight;
    }

    private double heightconv(){
        float valHeight = Float.parseFloat(nw_height.getText().toString());
        String sh = spinnerheight.getSelectedItem().toString();
        double finheight = 0;
        switch (sh) {
            case "m":
                finheight = valHeight * 100;
                break;
            case "ft":
                finheight = valHeight * 30.48;
                break;
            case "in":
                finheight = valHeight * 2.54;
                break;
            default:
                finheight = valHeight;
                break;
        }
        return finheight;
    }
}