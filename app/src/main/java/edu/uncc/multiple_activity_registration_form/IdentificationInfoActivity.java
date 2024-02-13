package edu.uncc.multiple_activity_registration_form;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.Toast;

public class IdentificationInfoActivity extends AppCompatActivity {

    // fields
    EditText editTextName, editTextEmail;
    RadioGroup radioGroup;

    // For the purposes of sending data to another activity via Intent using K, V pairs
    public static final String RESPONSE_KEY = "RESPONSE";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_identification_info);

        editTextName = findViewById(R.id.editTextName);
        editTextEmail = findViewById(R.id.editTextEmail);
        radioGroup = findViewById(R.id.radioGroup);


        // onClick - btn Next
        findViewById(R.id.btnNext1).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Get values
                String name = editTextName.getText().toString();
                String email = editTextEmail.getText().toString();
                int roleID = radioGroup.getCheckedRadioButtonId();

                // placeholder for value of selected radio button
                String selectedRole = getString(R.string.student);

                // Check for empty fields
                if (name.isEmpty()){
                    Toast.makeText(IdentificationInfoActivity.this, "Enter Name!", Toast.LENGTH_SHORT).show();
                } else if (email.isEmpty()){
                    Toast.makeText(IdentificationInfoActivity.this, "Enter Email!", Toast.LENGTH_SHORT).show();
                } else { // TODO: go to next activity (Demographics) and store the retrieved values in Response class.
                    // If no field is empty, check which radio button is selected
                    if (roleID == R.id.radioButtonStudent){
                        selectedRole = getString(R.string.student);
                    } else if (roleID == R.id.radioButtonEmployee){
                        selectedRole = getString(R.string.employee);
                    } else if (roleID == R.id.radioButtonOther){
                        selectedRole = getString(R.string.other);
                    }

                    // Send the data to next activity
                    Response response = new Response(name, email, selectedRole); // Create new response object
                    Intent intent = new Intent(IdentificationInfoActivity.this, DemographicInfoActivity.class);
                    intent.putExtra(RESPONSE_KEY, response); // K, V pair --> {RESPONSE : user_object}
                    startActivity(intent);
                }

            }
        });
    }
}