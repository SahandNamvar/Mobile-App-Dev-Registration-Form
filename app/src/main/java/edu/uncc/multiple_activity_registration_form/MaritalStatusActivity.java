package edu.uncc.multiple_activity_registration_form;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RadioGroup;

public class MaritalStatusActivity extends AppCompatActivity {

    // fields
    RadioGroup radioGroupMaritalStatus;
    Button btnCancelMaritalStatus, btnSubmitMaritalStatus;

    public static final String MARITALSTATUS_KEY = "MARITALSTATUS_KEY";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_marital_status);

        radioGroupMaritalStatus = findViewById(R.id.radioGroupMaritalStatus);

        findViewById(R.id.btnSubmitMaritalStatus).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int radioMaritalStatusID = radioGroupMaritalStatus.getCheckedRadioButtonId();

                // placeholder for marital status radio button value
                String maritalStatus = getString(R.string.not_married);

                if (radioMaritalStatusID == R.id.radioButtonNotMarried){
                    maritalStatus = getString(R.string.not_married);
                } else if (radioMaritalStatusID == R.id.radioButtonMarried) {
                    maritalStatus = getString(R.string.married);
                } else if (radioMaritalStatusID == R.id.radioButtonPreferNot) {
                    maritalStatus = getString(R.string.prefer_not_to_say);
                }

                // Create a new intent, load in the data in the form of K, V pair {MARITALSTATUS_KEY : "maritalStatus"}
                Intent intent = new Intent();
                intent.putExtra(MARITALSTATUS_KEY, maritalStatus);
                setResult(RESULT_OK, intent);
                finish();
            }
        });

        findViewById(R.id.btnCancelMaritalStatus).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

    }
}