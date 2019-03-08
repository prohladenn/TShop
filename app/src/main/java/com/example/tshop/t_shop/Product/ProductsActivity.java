package com.example.tshop.t_shop.Product;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.CardView;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.tshop.t_shop.Basket.BasketActivity;
import com.example.tshop.t_shop.R;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class ProductsActivity extends AppCompatActivity {

    ArrayList<Product> products;

    ProductAdapter productAdapter;
    RecyclerView recyclerView;
    TextView amountTextView;
    TextView amountCurTextView;
    ImageView buttonBack;
    FrameLayout buttonInBasket;

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
        setContentView(R.layout.activity_products);

        blockView = findViewById(R.id.products_block);
        descCardView = findViewById(R.id.product_desk);
        avatarDescImage = findViewById(R.id.product_desk_avatar);
        nameDescText = findViewById(R.id.product_desk_name);
        amountDescText = findViewById(R.id.product_desk_value_amount);
        unitDescText = findViewById(R.id.product_desk_value_unit);
        descDescTest = findViewById(R.id.product_desk_value_desc);
        descCardView.setVisibility(View.INVISIBLE);
        blockView.setVisibility(View.INVISIBLE);

        buttonBack = findViewById(R.id.products_button_back);
        amountTextView = findViewById(R.id.products_text_amount);
        amountCurTextView = findViewById(R.id.products_text_amount_cur);
        recyclerView = findViewById(R.id.products_recycler_view);
        products = generateStudentList();
        productAdapter = new ProductAdapter(products, this, this::onProductClick, amountTextView, amountCurTextView);
        recyclerView.setAdapter(productAdapter);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(linearLayoutManager);
        buttonInBasket = findViewById(R.id.products_button_basket);
        buttonInBasketListener();
        backListener();
    }

    void buttonInBasketListener() {
        buttonInBasket.setOnClickListener(v -> {
            Intent intent = new Intent(ProductsActivity.this, BasketActivity.class);
            intent.putExtra("data", productAdapter.getBasket());
            intent.putExtra("sum", ProductAdapter.getAmountString());
            startActivity(intent);
        });
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

    /*
        TODO ДОСТАТЬ ЭТИХ ПАРНЕЙ ИЗ БАЗЫ
         */
    private ArrayList<Product> generateStudentList() {
        ArrayList<Product> products = new ArrayList<>();
        products.add(
                new Product("Name1", "Desc1",
                        "https://avatarfiles.alphacoders.com/148/14824.png",
                        "https://avatarfiles.alphacoders.com/148/14824.png",
                        111, "р",
                        "11", "г", 1, 0)
        );
        products.add(
                new Product("Name2", "Desc2",
                        "https://avatarfiles.alphacoders.com/223/22359.png",
                        "https://avatarfiles.alphacoders.com/223/22359.png",
                        222, "р",
                        "22", "г", 2, 0)
        );
        products.add(
                new Product("Name3", "Desc3",
                        "https://avatarfiles.alphacoders.com/223/22359.png",
                        "https://avatarfiles.alphacoders.com/223/22359.png",
                        333, "р",
                        "33", "г", 3, 0)
        );
        products.add(
                new Product("Name4", "Desc4",
                        "https://avatarfiles.alphacoders.com/148/14824.png",
                        "https://avatarfiles.alphacoders.com/148/14824.png",
                        444, "р",
                        "44", "г", 4, 0)
        );
        products.add(
                new Product("Name5", "Desc5",
                        "https://avatarfiles.alphacoders.com/223/22359.png",
                        "https://avatarfiles.alphacoders.com/223/22359.png",
                        555, "р",
                        "55", "г", 5, 0)
        );
        products.add(
                new Product("Name6", "Desc6",
                        "https://avatarfiles.alphacoders.com/148/14824.png",
                        "https://avatarfiles.alphacoders.com/148/14824.png",
                        666, "р",
                        "66", "г", 6, 0)
        );
        products.add(
                new Product("Name7", "Desc7",
                        "https://avatarfiles.alphacoders.com/223/22359.png",
                        "https://avatarfiles.alphacoders.com/223/22359.png",
                        777, "р",
                        "77", "г", 7, 0)
        );
        return products;
    }

}
