package com.example.tshop.t_shop.Basket;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.tshop.t_shop.Product.Product;
import com.example.tshop.t_shop.R;

import java.util.List;

public class BasketActivity extends AppCompatActivity {

    List<Product> products;
    BasketAdapter basketAdapter;
    RecyclerView recyclerView;
    TextView amountTextView;
    ImageView buttonBack;
    FrameLayout buttonBuy;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_basket);

        buttonBack = findViewById(R.id.basket_button_back);
        recyclerView = findViewById(R.id.basket_recycler_view);
        products = generateStudentList();
        basketAdapter = new BasketAdapter(products, this, amountTextView);
        recyclerView.setAdapter(basketAdapter);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(linearLayoutManager);
        buttonBuy = findViewById(R.id.basket_button_buy);
    }

    private List<Product> generateStudentList() {
        return (List<Product>) getIntent().getSerializableExtra("data");
    }


}
