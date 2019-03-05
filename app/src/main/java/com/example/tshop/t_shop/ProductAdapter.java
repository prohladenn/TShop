package com.example.tshop.t_shop;

import android.app.Activity;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

public class ProductAdapter extends RecyclerView.Adapter<ProductAdapter.ProductViewHolder> {

    private final List<Product> products;
    private static Activity parent;

    public ProductAdapter(List<Product> products, Activity parent) {
        this.products = products;
        ProductAdapter.parent = parent;
    }

    @NonNull
    @Override
    public ProductViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        final View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.product_item, viewGroup, false);
        return new ProductViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ProductViewHolder productViewHolder, int i) {
        Product product = products.get(i);
        productViewHolder.bind(product);
        productViewHolder.itemView.setTag(i);
    }

    @Override
    public int getItemCount() {
        return products.size();
    }

    static final class ProductViewHolder extends RecyclerView.ViewHolder {

        private TextView nameTextView;
        private TextView countTextView;
        private ImageView avatarImageView;
        private FrameLayout buyButton;
        private FrameLayout addButton;
        private FrameLayout deleteButton;

        ProductViewHolder(@NonNull View itemView) {
            super(itemView);
            nameTextView = itemView.findViewById(R.id.product_item_text_desc);
            countTextView = itemView.findViewById(R.id.product_item_text_count);
            avatarImageView = itemView.findViewById(R.id.product_item_image_photo);
            buyButton = itemView.findViewById(R.id.product_item_button_buy);
            addButton = itemView.findViewById(R.id.product_item_button_add);
            deleteButton = itemView.findViewById(R.id.product_item_button_delete);
        }

        private void bind(@NonNull Product product) {
            nameTextView.setText(product.getName());
            countTextView.setText("1");
            countTextView.setVisibility(View.INVISIBLE);
            avatarImageView.setImageResource(R.drawable.ic_square_black_48dp);
            buyButton.setOnClickListener(v -> {
                buyButton.setVisibility(View.INVISIBLE);
                countTextView.setVisibility(View.VISIBLE);
                addButton.setVisibility(View.VISIBLE);
                deleteButton.setVisibility(View.VISIBLE);
                addButton.setOnClickListener(v1 -> {
                    int count = Integer.valueOf(countTextView.getText().toString());
                    if (count == product.getCount()) {
                        Toast.makeText(parent, "Нету столько", Toast.LENGTH_SHORT).show();
                    } else {
                        countTextView.setText(String.format(Integer.toString(count + 1), ""));
                    }
                });
                deleteButton.setOnClickListener(v1 -> {
                    int count = Integer.valueOf(countTextView.getText().toString());
                    if (count == 0) {
                        bind(product);
                    } else {
                        countTextView.setText(String.format(Integer.toString(count - 1), ""));
                    }
                });
            });
            addButton.setVisibility(View.INVISIBLE);
            deleteButton.setVisibility(View.INVISIBLE);
        }

    }

}
