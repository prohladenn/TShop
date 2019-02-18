package com.example.tshop.t_shop;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.ImageView;

public class HelpDescriptionActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_help_description);
    }

    ImageView buttonBack = findViewById(R.id.button_help_desk_back);
    ImageView buttonCheck = findViewById(R.id.button_help_desk_check);
    EditText textProblem = findViewById(R.id.edit_text_help_problem);

}
