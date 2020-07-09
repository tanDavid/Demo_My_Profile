package sg.edu.rp.c346.id19002765.demomyprofile;

import androidx.appcompat.app.AppCompatActivity;
import androidx.preference.PreferenceManager;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.Toast;


public class MainActivity extends AppCompatActivity {

    EditText etName, etGPA;
    RadioGroup rgGender;
    Button saveButt;

    @Override
    protected void onResume() {
        super.onResume();


        //Step 2a: Obtain an instance of sharedpreference
        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(this);

        //Step 2b: Retrieve the saved data with the key greeting from the sharedpreference object
        String msg = prefs.getString("name", "John");
        Float GPA = prefs.getFloat("gpa", 0.0f);
        int gend = prefs.getInt("gender", R.id.radioButtonGenderMale);

        //Step 2c: Update the UI element with the value
        etName.setText(msg);
        etGPA.setText(GPA + "");
        rgGender.check(gend);
    }

    @Override
    protected void onPause() {
        super.onPause();

        //Step 1a: Get the user input from the EditText and Store it in a variable
        String strName = etName.getText().toString();
        float gpa = Float.parseFloat(etGPA.getText().toString());
        int intGenderId = rgGender.getCheckedRadioButtonId();

        //Step 1b: Obtain an instance of the sharepreferences
        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(this);

        //Step 1c: Obtain an instance of the sharepreference editor for update later
        SharedPreferences.Editor prefEdit = prefs.edit();

        //Step 1d: Add the key-value pair
        prefEdit.putString("name", strName);
        prefEdit.putFloat("gpa", gpa);
        prefEdit.putInt("genderId", intGenderId);


        //Step 1e: call commit() to save the changes into SharedPreferences
        prefEdit.commit();



    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        etName = findViewById(R.id.editTextName);
        etGPA = findViewById(R.id.editTextGPA);
        rgGender = findViewById(R.id.radioGroupGender);
        saveButt = findViewById(R.id.buttonSave);


        saveButt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(MainActivity.this);
                SharedPreferences.Editor prefEdit = prefs.edit();
                String strName = etName.getText().toString();
                float gpa = Float.parseFloat(etGPA.getText().toString());
                int intGenderId = rgGender.getCheckedRadioButtonId();
                prefEdit.putString("name", strName);
                prefEdit.putFloat("gpa", gpa);
                prefEdit.putInt("genderId", intGenderId);
                Toast.makeText(MainActivity.this, "Saved!", Toast.LENGTH_LONG).show();

            }
        });

    }


}
