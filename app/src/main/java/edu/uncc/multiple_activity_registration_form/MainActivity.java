package edu.uncc.multiple_activity_registration_form;

/*
    Assignment #3
    File Name: Multiple_Activity_Registration_Form
    Student Name: Sahand Namvar
 */

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Onclick Listener
        findViewById(R.id.btnStart).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, IdentificationInfoActivity.class);
                startActivity(intent);
            }
        });
    }
}