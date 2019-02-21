package com.example.tshop.t_shop;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.InputType;
import android.view.View;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.Toast;

public class RegistrationActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration);
        initialize();
        buttonNumberListener();
    }

    EditText editTextRegistration;
    FrameLayout buttonRegistration;
    FrameLayout buttonResend;

    String number = "";
    String code = "";
    String name = "";

    void initialize() {
        editTextRegistration = findViewById(R.id.edit_text_register_number_code_name);
        buttonRegistration = findViewById(R.id.button_register_next_send_name);
        buttonResend = findViewById(R.id.button_register_resend);
        buttonResend.setVisibility(View.INVISIBLE);
    }

    private void buttonNumberListener() {
        buttonRegistration.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                number = editTextRegistration.getText().toString();
                if (number.isEmpty()) {
                    Toast.makeText(RegistrationActivity.this, "Введите номер!",
                            Toast.LENGTH_LONG).show();
                } else {
                    editTextRegistration.setText("");
                    editTextRegistration.setHint(R.string.register_code);
                    buttonResend.setVisibility(View.VISIBLE);
                    buttonCodeListener();
                }
            }
        });
    }

    private void buttonCodeListener() {
        buttonRegistration.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                code = editTextRegistration.getText().toString();
                if (code.isEmpty()) {
                    Toast.makeText(RegistrationActivity.this, "Введите код!",
                            Toast.LENGTH_LONG).show();
                } else {
                    editTextRegistration.setText("");
                    editTextRegistration.setText(R.string.register_name);
                    editTextRegistration.setInputType(InputType.TYPE_CLASS_TEXT);
                    buttonResend.setVisibility(View.INVISIBLE);
                    buttonNameListener();
                }
            }
        });
    }

    private void buttonNameListener() {
        buttonRegistration.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                name = editTextRegistration.getText().toString();
                if (name.isEmpty()) {
                    Toast.makeText(RegistrationActivity.this, "Введите имя!",
                            Toast.LENGTH_LONG).show();
                } else {
                    Toast.makeText(RegistrationActivity.this, "Добро пожаловать " + name,
                            Toast.LENGTH_LONG).show();
                }
            }
        });
    }

}
