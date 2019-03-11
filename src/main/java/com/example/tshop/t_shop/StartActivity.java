package com.example.tshop.t_shop;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

public class StartActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {
    private FirebaseAuth mAuth;
    private FirebaseFirestore mFirestore;
    private TextView nameTextView;
    private TextView phoneTextView;
    private TextView greetingTextView;
    private FrameLayout nextButton;
    private EditText codeEditText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start);
        Toolbar toolbar = findViewById(R.id.toolbar);
        toolbar.setTitle("");
        setSupportActionBar(toolbar);
        mAuth = FirebaseAuth.getInstance();


        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();
        NavigationView navigationView = findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
        View headerView = navigationView.getHeaderView(0);
        nameTextView = headerView.findViewById(R.id.name);
        phoneTextView = headerView.findViewById(R.id.number);
        greetingTextView = findViewById(R.id.greeting);
        codeEditText = findViewById(R.id.box_number);
        nextButton = findViewById(R.id.button_next);
        nextButton.setOnClickListener(v -> {
            String code = codeEditText.getText().toString().trim();
            if (code.isEmpty()) {
                codeEditText.setError("Введите код");
                codeEditText.requestFocus();
                return;
            }
            mFirestore.collection("shops").get().addOnCompleteListener(task -> {
                if (task.getResult() != null) {
                    Boolean isShopExist = false;
                    DocumentReference inventoryRef = null;
                    for (DocumentSnapshot doc : task.getResult()) {
                        if (doc.getString("code").equals(codeEditText.getText().toString().trim())) {
                            isShopExist = true;
                            inventoryRef = doc.getDocumentReference("inventory.reference");
                        }
                        if (isShopExist) {
                            if (inventoryRef != null) {
                                Intent intent = new Intent(StartActivity.this, ProductsActivity.class);
                                intent.putExtra("refPath", inventoryRef.getPath());
                                startActivity(intent);
                            } else {
                                Toast.makeText(StartActivity.this,
                                        "Ошибка! Попробуйте ввести код заново", Toast.LENGTH_SHORT).show();
                                return;
                            }
                        } else {
                            Toast.makeText(StartActivity.this,
                                    "Нет магазина с таким кодом! Попробуйте ввести код заново", Toast.LENGTH_SHORT).show();
                            return;
                        }
                    }
                } else Toast.makeText(StartActivity.this,
                        "Нет доступных магазинов", Toast.LENGTH_SHORT).show();

            });
        });
        mFirestore = FirebaseFirestore.getInstance();
        mFirestore.collection("users").document(mAuth.getCurrentUser().getUid())
                .get().addOnCompleteListener(task -> {
            DocumentSnapshot doc = task.getResult();
            String name = doc.getString("name");
            String number = doc.getString("phoneNumber");
            if (name != null && !name.isEmpty()) {
                nameTextView.setText(name);
                greetingTextView.setText("Добрый день, " + name + "!");
            } else {
                nameTextView.setText("Имя отсутствует");
                greetingTextView.setText("Добрый день!");
            }
            if (number != null && !number.isEmpty())
                phoneTextView.setText(number);
            else phoneTextView.setText("Номер отсутствует");
        });
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }


    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();
        switch (id) {
            case R.id.nav_orders: {
            }
            break;
            case R.id.nav_about_app: {
            }
            break;
            case R.id.nav_exit: {
                mAuth.signOut();
                Intent intent = new Intent(StartActivity.this, PhoneAuthActivity.class);
                startActivity(intent);
                finish();
            }
            break;
            case R.id.nav_help: {
                Intent intent = new Intent(StartActivity.this, HelpActivity.class);
                startActivity(intent);
            }
            break;

        }
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

}
