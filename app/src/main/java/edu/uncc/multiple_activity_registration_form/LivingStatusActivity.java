package edu.uncc.multiple_activity_registration_form;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RadioGroup;

public class LivingStatusActivity extends AppCompatActivity {

    RadioGroup radioGroupLivingStatus;
    Button btnSubmitLivingStatus, btnCancelLivingStatus;

    public static final String LIVINGSTATUS_KEY = "LIVINGSTATUS_KEY";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_living_status);

        radioGroupLivingStatus = findViewById(R.id.radioGroupLivingStatus);

        findViewById(R.id.btnSubmitLivingStatus).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int livingStatusID = radioGroupLivingStatus.getCheckedRadioButtonId();

                String livingStatus = getString(R.string.homeowner);

                if (livingStatusID == R.id.radioButtonHomeOwner){
                    livingStatus = getString(R.string.homeowner);
                } else if (livingStatusID == R.id.radioButtonRenter){
                    livingStatus = getString(R.string.renter);
                } else if (livingStatusID == R.id.radioButtonLessee){
                    livingStatus = getString(R.string.lessee);
                } else if (livingStatusID == R.id.radioButtonOther){
                    livingStatus = getString(R.string.other);
                } else if (livingStatusID == R.id.radioButtonPreferNot){
                    livingStatus = getString(R.string.prefer_not_to_say);
                }

                Intent intent = new Intent();
                intent.putExtra(LIVINGSTATUS_KEY, livingStatus);
                setResult(RESULT_OK, intent);
                finish();
            }
        });

        findViewById(R.id.btnCancelLivingStatus).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }
}