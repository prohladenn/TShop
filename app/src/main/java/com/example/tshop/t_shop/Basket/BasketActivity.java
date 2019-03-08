package com.example.tshop.t_shop.Basket;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.CardView;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.tshop.t_shop.Product.Product;
import com.example.tshop.t_shop.R;
import com.squareup.picasso.Picasso;

import java.util.List;

public class BasketActivity extends AppCompatActivity {

    List<Product> products;

    BasketAdapter basketAdapter;
    RecyclerView recyclerView;
    TextView amountTextView;
    TextView amountCurTextView;
    ImageView buttonBack;
    FrameLayout buttonBuy;

    View blockView;
    CardView descCardView;
    ImageView avatarDescImage;
    TextView nameDescText;
    TextView amountDescText;
    TextView unitDescText;
    TextView descDescTest;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_basket);

        blockView = findViewById(R.id.basket_block);
        descCardView = findViewById(R.id.basket_desk);
        avatarDescImage = findViewById(R.id.basket_desk_avatar);
        nameDescText = findViewById(R.id.basket_desk_name);
        amountDescText = findViewById(R.id.basket_desk_value_amount);
        unitDescText = findViewById(R.id.basket_desk_value_unit);
        descDescTest = findViewById(R.id.basket_desk_value_desc);
        descCardView.setVisibility(View.INVISIBLE);
        blockView.setVisibility(View.INVISIBLE);

        buttonBack = findViewById(R.id.basket_button_back);
        amountTextView = findViewById(R.id.basket_text_amount);
        amountCurTextView = findViewById(R.id.basket_text_amount_cur);
        recyclerView = findViewById(R.id.basket_recycler_view);
        products = generateStudentList();
        basketAdapter = new BasketAdapter(products, this, this::onProductClick, amountTextView, amountCurTextView, getIntent().getStringExtra("sum"));
        recyclerView.setAdapter(basketAdapter);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(linearLayoutManager);
        buttonBuy = findViewById(R.id.basket_button_buy);
        buyListener();
        backListener();
    }

    void buyListener() {
        buttonBuy.setOnClickListener(v -> Toast.makeText(this, "ПРОДАНО", Toast.LENGTH_SHORT).show());
    }

    void backListener() {
        buttonBack.setOnClickListener(v -> onBackPressed());
    }

    private void onProductClick(View view, int i) {
        blockView.setVisibility(View.VISIBLE);
        descCardView.setVisibility(View.VISIBLE);
        Product product = products.get(i);
        //todo опять не робит Пикассо поэтому ставлю просто заливку
        avatarDescImage.setBackgroundResource(R.color.colorPrimary);
        Picasso.get().load(product.getPictureSource()).into(avatarDescImage);
        nameDescText.setText(product.getName());
        amountDescText.setText(product.getValueAmount());
        unitDescText.setText(product.getValueUnits());
        descDescTest.setText(product.getDescription());
        blockView.setOnClickListener(v -> onBackPressed());
    }

    @Override
    public void onBackPressed() {
        if (descCardView.getVisibility() == View.VISIBLE) {
            descCardView.setVisibility(View.INVISIBLE);
            blockView.setVisibility(View.INVISIBLE);
        } else
            super.onBackPressed();
    }

    private List<Product> generateStudentList() {
        return (List<Product>) getIntent().getSerializableExtra("data");
    }

}
