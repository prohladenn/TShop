package com.example.tshop.t_shop.Product;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.tshop.t_shop.Basket.BasketActivity;
import com.example.tshop.t_shop.R;

import java.util.ArrayList;
import java.util.List;

public class ProductsActivity extends AppCompatActivity {

    List<Product> products;
    ProductAdapter productAdapter;
    RecyclerView recyclerView;
    TextView amountTextView;
    ImageView buttonBack;
    FrameLayout buttonInBasket;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_products);

        buttonBack = findViewById(R.id.products_button_back);
        amountTextView = findViewById(R.id.products_text_amount);
        recyclerView = findViewById(R.id.products_recycler_view);
        products = generateStudentList();
        productAdapter = new ProductAdapter(products, this, amountTextView);
        recyclerView.setAdapter(productAdapter);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(linearLayoutManager);
        buttonInBasket = findViewById(R.id.products_button_basket);
        buttonInBasketListener();
    }

    void buttonInBasketListener() {
        buttonInBasket.setOnClickListener(v -> {
            Intent intent = new Intent(ProductsActivity.this, BasketActivity.class);
            intent.putExtra("data", ProductAdapter.getBasket());
            startActivity(intent);
        });
    }

    private List<Product> generateStudentList() {
        List<Product> products = new ArrayList<>();
        products.add(
                new Product("Name1", "Desc1",
                        "", "",
                        111, "р",
                        11, "", 1)
        );
        products.add(
                new Product("Name2", "Desc2",
                        "", "",
                        222, "р",
                        22, "", 2)
        );
        products.add(
                new Product("Name3", "Desc3",
                        "", "",
                        333, "р",
                        33, "", 3)
        );
        products.add(
                new Product("Name4", "Desc4",
                        "", "",
                        444, "р",
                        44, "", 4)
        );
        products.add(
                new Product("Name5", "Desc5",
                        "", "",
                        555, "р",
                        55, "", 5)
        );
        products.add(
                new Product("Name6", "Desc6",
                        "", "",
                        666, "р",
                        66, "", 6)
        );
        products.add(
                new Product("Name7", "Desc7",
                        "", "",
                        777, "р",
                        77, "", 7)
        );
        return products;
    }


}
