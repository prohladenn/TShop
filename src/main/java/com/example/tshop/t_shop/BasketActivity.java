package com.example.tshop.t_shop;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.ListIterator;

public class BasketActivity extends AppCompatActivity {

    ArrayList<Product> products;

    ProductAdapter productAdapter;
    RecyclerView recyclerView;
    TextView amountTextView;
    TextView amountCurTextView;
    ImageView buttonBack;
    FrameLayout buttonBuy;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_basket);

        buttonBack = findViewById(R.id.basket_button_back);
        amountTextView = findViewById(R.id.basket_text_amount);
        amountCurTextView = findViewById(R.id.basket_text_amount_cur);
        recyclerView = findViewById(R.id.basket_recycler_view);
        products = generateStudentList();
        productAdapter = new ProductAdapter(products, this, this::onProductClick,
                amountTextView, amountCurTextView);
        recyclerView.setAdapter(productAdapter);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(linearLayoutManager);
        buttonBuy = findViewById(R.id.basket_button_buy);
        buyListener();
        backListener();
    }

    void buyListener() {
        buttonBuy.setOnClickListener(v -> Toast.makeText(this, "ПРОДАНО",
                Toast.LENGTH_SHORT).show());
    }

    void backListener() {
        buttonBack.setOnClickListener(v -> onBackPressed());
    }

    private void onProductClick(int i) {
        Intent dialog = new Intent(BasketActivity.this, PreviewDialogActivity.class);
        dialog.putExtra("product", products.get(i));
        startActivityForResult(dialog, 1111);
    }

    @Override
    public void onBackPressed() {
        products = productAdapter.getBasket();
        Intent intent = new Intent();
        intent.putExtra("data", products);
        intent.putExtra("sum", ProductAdapter.getAmountString());
        setResult(RESULT_OK, intent);
        finish();

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 1111 && resultCode == RESULT_OK && data != null) {
            Product product = (Product) data.getSerializableExtra("product");
            for (Product p :
                    products) {
                if (p.equals(product)) {
                    p.setSelected(product.getSelected());
                }
            }
            productAdapter = new ProductAdapter(products, this, this::onProductClick, amountTextView,
                    amountCurTextView);
            recyclerView.setAdapter(productAdapter);
            LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
            recyclerView.setLayoutManager(linearLayoutManager);
        }
    }

    private ArrayList<Product> generateStudentList() {
        ArrayList<Product> data = (ArrayList<Product>) getIntent().getSerializableExtra("data");
        ListIterator<Product> i = data.listIterator();
        while (i.hasNext()) {
            Product p = i.next();
            if (p.getSelected() == 0)
                i.remove();
        }
        return data;
    }

}