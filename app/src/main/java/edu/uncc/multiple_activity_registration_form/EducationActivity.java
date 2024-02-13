package edu.uncc.multiple_activity_registration_form;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.RadioGroup;

public class EducationActivity extends AppCompatActivity {

    // fields
    RadioGroup radioGroupEducation;

    public static final String EDUCATION_KEY = "EDUCATION_KEY";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_education);

        radioGroupEducation = findViewById(R.id.radioGroupEducation);

        // onClick - submit button
        findViewById(R.id.btnSubmitEducation).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int educationID = radioGroupEducation.getCheckedRadioButtonId();

                // placeholder for education level
                String educationLevel = getString(R.string.high_school);

                if (educationID == R.id.radioButtonBelowHS){
                    educationLevel = getString(R.string.below_high_school);
                } else if (educationID == R.id.radioButtonHS){
                    educationLevel = getString(R.string.high_school);
                } else if (educationID == R.id.radioButtonBS){
                    educationLevel = getString(R.string.bachelor_s_degree);
                } else if (educationID == R.id.radioButtonMS){
                    educationLevel = getString(R.string.master_s_degree);
                } else if (educationID == R.id.radioButtonPhD){
                    educationLevel = getString(R.string.ph_d_or_higher);
                } else if (educationID == R.id.radioButtonTradeSchool){
                    educationLevel = getString(R.string.trade_school);
                } else if (educationID == R.id.radioButtonPreferNot){
                    educationLevel = getString(R.string.prefer_not_to_say);
                }

                // New empty intent - No need to specify parameters because of "startEducationForResult(intent)"
                // defined in DemographicInfoActivity, it only has one direction to send the data back to whoever invoked it.
                // The data being send will be received inside of "ActivityResultLauncher<Intent> startEducationForResult".
                Intent intent = new Intent();
                intent.putExtra(EDUCATION_KEY, educationLevel);
                setResult(RESULT_OK, intent);
                finish();
            }
        });

        // onClick - cancel button
        findViewById(R.id.btnCancelEducation).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }
}