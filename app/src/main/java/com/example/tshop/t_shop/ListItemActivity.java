package com.example.tshop.t_shop;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class ListItemActivity extends AppCompatActivity {

    List<Product> products;
    ProductAdapter productAdapter;
    RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_item);

        recyclerView = findViewById(R.id.list_item_recycler_view);
        products = generateStudentList();
        productAdapter = new ProductAdapter(products, this);
        recyclerView.setAdapter(productAdapter);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(linearLayoutManager);
    }

    private List<Product> generateStudentList() {
        List<Product> students = new ArrayList<>();
        students.add(
                new Product("Name1", "Desc1",
                        "", "",
                        111, "",
                        11, "", 1)
        );
        students.add(
                new Product("Name2", "Desc2",
                        "", "",
                        222, "",
                        22, "", 2)
        );
        students.add(
                new Product("Name3", "Desc3",
                        "", "",
                        333, "",
                        33, "", 3)
        );
        students.add(
                new Product("Name4", "Desc4",
                        "", "",
                        444, "",
                        44, "", 4)
        );
        students.add(
                new Product("Name5", "Desc5",
                        "", "",
                        555, "",
                        55, "", 5)
        );
        students.add(
                new Product("Name6", "Desc6",
                        "", "",
                        666, "",
                        66, "", 6)
        );
        students.add(
                new Product("Name7", "Desc7",
                        "", "",
                        777, "",
                        77, "", 7)
        );
        return students;
    }


}
