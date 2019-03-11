package com.example.tshop.t_shop;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;

import java.util.ArrayList;

import static android.support.constraint.Constraints.TAG;

public class ProductsActivity extends AppCompatActivity {

    ArrayList<Product> products = new ArrayList<>();

    ProductAdapter productAdapter;
    RecyclerView recyclerView;
    TextView amountTextView;
    TextView amountCurTextView;
    ImageView buttonBack;
    FrameLayout buttonInBasket;
    DocumentReference ref;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_products);

        buttonBack = findViewById(R.id.products_button_back);
        amountTextView = findViewById(R.id.products_text_amount);
        amountCurTextView = findViewById(R.id.products_text_amount_cur);
        recyclerView = findViewById(R.id.products_recycler_view);
        ref = FirebaseFirestore.getInstance().document(getIntent().getStringExtra("refPath"));

        generateStudentList();
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(linearLayoutManager);

        buttonInBasket = findViewById(R.id.products_button_basket);
        buttonInBasketListener();

        backListener();
    }

    void buttonInBasketListener() {
        buttonInBasket.setOnClickListener(v -> {
            if (!ProductAdapter.getAmountString().equals("0")) {
                Intent intent = new Intent(ProductsActivity.this, BasketActivity.class);
                intent.putExtra("data", productAdapter.getBasket());
                intent.putExtra("sum", ProductAdapter.getAmountString());
                startActivityForResult(intent, 1234);
            } else {
                Toast.makeText(this, "Корзина пуста", Toast.LENGTH_SHORT).show();
            }
        });
    }

    void backListener() {
        buttonBack.setOnClickListener(v -> onBackPressed());
    }

    private void onProductClick(int i) {
        Intent dialog = new Intent(ProductsActivity.this, PreviewDialogActivity.class);
        dialog.putExtra("product", products.get(i));
        startActivityForResult(dialog, 1111);
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 1234 && resultCode == RESULT_OK && data != null) {
            ArrayList<Product> help = (ArrayList<Product>) data.getSerializableExtra("data");
            for (Product p1 : products) {
                for (Product p : help) {
                    if (p1.getName().equals(p.getName()))
                        p1.setSelected(p.getSelected());
                }
            }
            productAdapter = new ProductAdapter(products, this, this::onProductClick, amountTextView,
                    amountCurTextView);
            recyclerView.setAdapter(productAdapter);
            LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
            recyclerView.setLayoutManager(linearLayoutManager);
        }
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

    private void generateStudentList() {

        ref.collection("items").addSnapshotListener((queryDocumentSnapshots, e) -> {
            if (e != null) {
                Log.w(TAG, "Listen failed.", e);
                return;
            }
            for (QueryDocumentSnapshot docItem : queryDocumentSnapshots) {
                Long count = docItem.getLong("count");
                Long reserved = docItem.getLong("reserved");
                Task<DocumentSnapshot> task = docItem.getDocumentReference("productRef").get();
                while (!task.isComplete()) {
                    Log.d("LOGI", "WaitOwen");
                }
                DocumentSnapshot doc = task.getResult();
                products.add(
                        new Product(doc.getString("name"), doc.getString("description"),
                                doc.getString("picture.source"),
                                doc.getString("picture.thumbnail"),
                                doc.getLong("price.amount").intValue(),
                                doc.getString("price.currency.shortName"),
                                doc.getLong("value.amount").toString(),
                                doc.getString("value.unit"), count - reserved, 0)
                );

            }
            productAdapter = new ProductAdapter(products, ProductsActivity.this,
                    this::onProductClick, amountTextView, amountCurTextView);
            recyclerView.setAdapter(productAdapter);
        });
    }

}