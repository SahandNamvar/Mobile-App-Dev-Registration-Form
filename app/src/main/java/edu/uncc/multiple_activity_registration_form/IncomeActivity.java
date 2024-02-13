package edu.uncc.multiple_activity_registration_form;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.SeekBar;
import android.widget.TextView;

import org.w3c.dom.Text;

public class IncomeActivity extends AppCompatActivity {

    TextView textViewSeekBarValue;
    SeekBar seekBar;

    public static final String INCOME_KEY = "INCOME_KEY";

    String incomeLevel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_income);

        textViewSeekBarValue = findViewById(R.id.textViewSeekBarValue);
        seekBar = findViewById(R.id.seekBar);

        incomeLevel = "<$25K";
        textViewSeekBarValue.setText(incomeLevel);

        seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {

            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                if (progress == 0){
                    incomeLevel = "<$25K";
                    textViewSeekBarValue.setText(incomeLevel);
                } else if (progress == 1) {
                    incomeLevel = "$25K to <$50K";
                    textViewSeekBarValue.setText(incomeLevel);
                } else if (progress == 2) {
                    incomeLevel = "$50K to <$100K";
                    textViewSeekBarValue.setText(incomeLevel);
                } else if (progress == 3) {
                    incomeLevel = "$100K to <$200K";
                    textViewSeekBarValue.setText(incomeLevel);
                } else if (progress == 4) {
                    incomeLevel = ">$200K";
                    textViewSeekBarValue.setText(incomeLevel);
                }
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });

        findViewById(R.id.btnSubmit_Income).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.putExtra(INCOME_KEY, incomeLevel);
                setResult(RESULT_OK, intent);
                finish();
            }
        });

        findViewById(R.id.btnCancel_Income).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

    }
}