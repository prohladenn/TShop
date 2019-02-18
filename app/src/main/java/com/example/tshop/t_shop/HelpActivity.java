package com.example.tshop.t_shop;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.ImageView;

public class HelpActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_help);
    }

    ImageView buttonBack = findViewById(R.id.button_help_back);
    EditText textName = findViewById(R.id.edit_text_help_name);
    EditText textNumber = findViewById(R.id.edit_text_help_number);
    EditText textTheme = findViewById(R.id.edit_text_help_theme);
    FrameLayout frameProblem = findViewById(R.id.button_help_description);
    FrameLayout frameSend = findViewById(R.id.button_help_send);

}
