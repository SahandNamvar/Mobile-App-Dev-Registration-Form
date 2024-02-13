package edu.uncc.multiple_activity_registration_form;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

// Implement View.OnClickListener because of multiple buttons - avoid setting onClickListener for each one.
public class DemographicInfoActivity extends AppCompatActivity implements View.OnClickListener{

    // fields
    TextView textViewEducation, textViewMaritalStatus, textViewLivingStatus, textViewIncome;
    Button btnSelectEducation, btnSelectMaritalStatus, btnSelectLivingStatus, btnSelectIncome, btnNext2;

    // Declare a Response object to 1) retrieve the values from Identification Activity 2) update values as received by other activities
    // The Response object sent from previous activity will be copied to the Response object declared here.
    Response response;

    public static final String COMPLETE_RESPONSE_KEY = "COMPLETE_RESPONSE_KEY";

    // ---------- Data retrieval from Activities opened via this instance and should return back data to this activity --------
    // Set up Activity Launchers for each activity that is going to get launched from DemographicInfoActivity in order to GET results back from ANOTHER activity
    private ActivityResultLauncher<Intent> startEducationForResult = registerForActivityResult(
            new ActivityResultContracts.StartActivityForResult(), new ActivityResultCallback<ActivityResult>() {
                // Override onActivityResult method implemented from "ActivityResultCallback<ActivityResult>()" interface - This is where we get back the result from EducationActivity
                @Override
                public void onActivityResult(ActivityResult educationResult) {
                    if (educationResult.getResultCode() == RESULT_OK && educationResult.getData() != null
                            && educationResult.getData().getSerializableExtra(EducationActivity.EDUCATION_KEY) != null){
                        // receive the data sent from Education Activity
                        Intent data = educationResult.getData();
                        String educationLevel = data.getStringExtra(EducationActivity.EDUCATION_KEY);
                        response.setEducationLevel(educationLevel); // Update Response Education
                        textViewEducation.setText(educationLevel);
                    } else {
                        textViewEducation.setText(R.string.n_a);
                    }
                }
            }
    );

    private ActivityResultLauncher<Intent> startMaritalStatusForResult = registerForActivityResult(
            new ActivityResultContracts.StartActivityForResult(), new ActivityResultCallback<ActivityResult>() {
                @Override
                public void onActivityResult(ActivityResult maritalResult) {
                    if (maritalResult.getResultCode() == RESULT_OK && maritalResult.getData() != null
                        && maritalResult.getData().getSerializableExtra(MaritalStatusActivity.MARITALSTATUS_KEY) != null){
                        Intent data = maritalResult.getData();
                        String maritalStatus = data.getStringExtra(MaritalStatusActivity.MARITALSTATUS_KEY);
                        response.setMaritalStatus(maritalStatus);
                        textViewMaritalStatus.setText(maritalStatus);
                    } else {
                        textViewMaritalStatus.setText(R.string.n_a);
                    }
                }
            }
    );

    private ActivityResultLauncher<Intent> startLivingStatusForResult = registerForActivityResult(
            new ActivityResultContracts.StartActivityForResult(), new ActivityResultCallback<ActivityResult>() {
                @Override
                public void onActivityResult(ActivityResult livingResult) {
                    if (livingResult.getResultCode() == RESULT_OK && livingResult.getData() != null
                            && livingResult.getData().getSerializableExtra(LivingStatusActivity.LIVINGSTATUS_KEY) != null){
                        Intent data = livingResult.getData();
                        String livingStatus = data.getStringExtra(LivingStatusActivity.LIVINGSTATUS_KEY);
                        response.setLivingStatus(livingStatus);
                        textViewLivingStatus.setText(livingStatus);
                    } else {
                        textViewLivingStatus.setText(R.string.n_a);
                    }
                }
            }
    );

    private ActivityResultLauncher<Intent> startIncomeForResult = registerForActivityResult(
            new ActivityResultContracts.StartActivityForResult(), new ActivityResultCallback<ActivityResult>() {
                @Override
                public void onActivityResult(ActivityResult incomeResult) {
                    if (incomeResult.getResultCode() == RESULT_OK && incomeResult.getData() != null
                            && incomeResult.getData().getSerializableExtra(IncomeActivity.INCOME_KEY) != null){
                        Intent data = incomeResult.getData();
                        String incomeLevel = data.getStringExtra(IncomeActivity.INCOME_KEY);
                        response.setIncome(incomeLevel);
                        textViewIncome.setText(incomeLevel);
                    } else {
                        textViewIncome.setText(R.string.n_a);
                    }
                }
            }
    );

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_demographic_info);

        // Grab textViews by id
        textViewEducation = findViewById(R.id.textViewEducation);
        textViewMaritalStatus = findViewById(R.id.textViewMaritalStatus);
        textViewLivingStatus = findViewById(R.id.textViewLivingStatus);
        textViewIncome = findViewById(R.id.textViewIncome);

        // Grab buttons by id & set onClickListener
        btnSelectEducation = findViewById(R.id.btnSelectEducation);
        btnSelectEducation.setOnClickListener(this);
        btnSelectMaritalStatus = findViewById(R.id.btnSelectMaritalStatus);
        btnSelectMaritalStatus.setOnClickListener(this);
        btnSelectLivingStatus = findViewById(R.id.btnSelectLivingStatus);
        btnSelectLivingStatus.setOnClickListener(this);
        btnSelectIncome = findViewById(R.id.btnSelectIncome);
        btnSelectIncome.setOnClickListener(this);
        btnNext2 = findViewById(R.id.btnNext2);
        btnNext2.setOnClickListener(this);

        // ---------- Data retrieval from PREVIOUS activity --------
        // Check for the Intent (data) sent from IdentificationInfoActivity
        if (getIntent() !=  null && getIntent().getExtras() != null && getIntent().hasExtra(IdentificationInfoActivity.RESPONSE_KEY)){
            // If all requirements are met, convert the received Serializable extras to a Response object
            // the moment this activity is created, it will receive the data (a Response object) from its previous class
            response = (Response) getIntent().getSerializableExtra(IdentificationInfoActivity.RESPONSE_KEY);
        }

        // TODO: implement onClick listener for 5 buttons
        // All other buttons: Open corresponding activity
        // Next button: check for empty fields, if non, move to Profile Activity

    }

    /*
    * Create intent: Depending on which button is clicked, create a new intent for destination activity.
    * Send data: To send data to another activity: puExtra(key, value_data).
    * Launch new Activity: There are two ways to launch destination activity:
    *      - Sending data and not expecting any data back: StartActivity(intent) --> instantly starts new activity, transfers data (if any specified) and is ready to be retrieved on the new activity.
    *           -- How to retrieve it? --> // ---------- Data retrieval from PREVIOUS activity --------
    *      - Launching a new activity expecting to receive data back from that activity: Must set up an activity for result listener -- This is where the data from the new activity is sent back to.
    *           -- How to retrieve it? --> private ActivityResultLauncher<Intent> startEducationForResult = registerForActivityResult(new ActivityResultContracts.StartActivityForResult(), new ActivityResultCallback<ActivityResult>()
    *           -- : ---------- Data retrieval from Activities opened via this instance and should return back data to this activity --------
    *           -- : Call the ".launcher(intent)" method on your intent.
    * */
    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.btnSelectEducation){
            Intent intent = new Intent(DemographicInfoActivity.this, EducationActivity.class);
            // Note: Not saying startActivity(intent).
            // In order to get data back from another activity, must implement private ActivityResultLauncher<Intent> startEducationForResult.
            startEducationForResult.launch(intent);
        } else if (v.getId() == R.id.btnSelectMaritalStatus){
            Intent intent = new Intent(DemographicInfoActivity.this, MaritalStatusActivity.class);
            startMaritalStatusForResult.launch(intent);
        }  else if (v.getId() == R.id.btnSelectLivingStatus){
            Intent intent = new Intent(DemographicInfoActivity.this, LivingStatusActivity.class);
            startLivingStatusForResult.launch(intent);
        }  else if (v.getId() == R.id.btnSelectIncome){
            Intent intent = new Intent(DemographicInfoActivity.this, IncomeActivity.class);
            startIncomeForResult.launch(intent);
        }  else if (v.getId() == R.id.btnNext2){

            String n_a = getString(R.string.n_a);

            if (textViewEducation.getText().toString() == null || textViewEducation.getText().toString().equals(n_a) ){
                Toast.makeText(DemographicInfoActivity.this, "Select Education!", Toast.LENGTH_SHORT).show();
            } else if (textViewMaritalStatus.getText().toString() == null || textViewMaritalStatus.getText().toString().equals(n_a)){
                Toast.makeText(DemographicInfoActivity.this, "Select Marital Status!", Toast.LENGTH_SHORT).show();
            } else if (textViewLivingStatus.getText().toString() == null || textViewLivingStatus.getText().toString().equals(n_a)){
                Toast.makeText(DemographicInfoActivity.this, "Select Living Status!", Toast.LENGTH_SHORT).show();
            } else if (textViewIncome.getText().toString() == null || textViewIncome.getText().toString().equals(n_a)){
                Toast.makeText(DemographicInfoActivity.this, "Select Income!", Toast.LENGTH_SHORT).show();
            } else {
                Intent intent = new Intent(DemographicInfoActivity.this, ProfileActivity.class);
                intent.putExtra(COMPLETE_RESPONSE_KEY, response);
                startActivity(intent);
            }
        }
    }
}