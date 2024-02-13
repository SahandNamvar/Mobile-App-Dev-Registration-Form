package edu.uncc.multiple_activity_registration_form;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

import java.lang.annotation.Repeatable;

public class ProfileActivity extends AppCompatActivity {

    TextView textViewProfileName, textViewProfileEmail, textViewProfileRole, textViewProfileEducation, textViewProfileMaritalStatus
            , textViewProfileLivingStatus, textViewProfileIncome;

    Response response;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        textViewProfileName = findViewById(R.id.textViewProfileName);
        textViewProfileEmail = findViewById(R.id.textViewProfileEmail);
        textViewProfileRole = findViewById(R.id.textViewProfileRole);
        textViewProfileEducation = findViewById(R.id.textViewProfileEducation);
        textViewProfileMaritalStatus = findViewById(R.id.textViewProfileMaritalStatus);
        textViewProfileLivingStatus = findViewById(R.id.textViewProfileLivingStatus);
        textViewProfileIncome = findViewById(R.id.textViewProfileIncome);

        if (getIntent() !=  null && getIntent().getExtras() != null && getIntent().hasExtra(DemographicInfoActivity.COMPLETE_RESPONSE_KEY)){
            response = (Response) getIntent().getSerializableExtra(DemographicInfoActivity.COMPLETE_RESPONSE_KEY);
            Toast.makeText(ProfileActivity.this, "Form is complete!", Toast.LENGTH_LONG).show();
        }

        textViewProfileName.setText(getString(R.string.name)  + " " + response.getName());
        textViewProfileEmail.setText(getString(R.string.email)  + " " + response.getEmail());
        textViewProfileRole.setText(getString(R.string.role)  + " " + response.getRole_id());
        textViewProfileEducation.setText(getString(R.string.education)  + " " + response.getEducationLevel());
        textViewProfileMaritalStatus.setText(getString(R.string.marital_status)  + " " + response.getMaritalStatus());
        textViewProfileLivingStatus.setText(getString(R.string.living_status)  + " " + response.getLivingStatus());
        textViewProfileIncome.setText(getString(R.string.income)  + " " + response.getIncome());
    }
}