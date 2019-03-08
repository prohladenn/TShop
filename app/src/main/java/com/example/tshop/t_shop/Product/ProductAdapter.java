package com.example.tshop.t_shop.Product;

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

import com.example.tshop.t_shop.R;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

public class ProductAdapter extends RecyclerView.Adapter<ProductAdapter.ProductViewHolder> {

    private final List<Product> products;
    private static TextView amountTextView;
    private static TextView amountCurTextView;
    private static Activity parent;
    private static Map<Product, Integer> basket;
    private final Listener onProductClickListener;

    public ProductAdapter(List<Product> products, Activity parent, Listener onProductClickListener,
                          TextView amountTextView, TextView amountCurTextView) {
        this.products = products;
        this.onProductClickListener = onProductClickListener;
        ProductAdapter.parent = parent;
        ProductAdapter.amountTextView = amountTextView;
        ProductAdapter.amountTextView.setVisibility(View.INVISIBLE);
        ProductAdapter.amountCurTextView = amountCurTextView;
        ProductAdapter.amountCurTextView.setVisibility(View.INVISIBLE);
        basket = new TreeMap<>();
    }

    public static ArrayList<Product> getBasket() {
        ArrayList<Product> answer = new ArrayList<>();
        for (Product key :
                basket.keySet()) {
            key.setSelected(basket.get(key));
            answer.add(key);
        }
        return answer;
    }

    public static String getAmountString() {
        return amountTextView.getText().toString();
    }

    @NonNull
    @Override
    public ProductViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        final View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.product_item, viewGroup, false);
        view.setOnClickListener(v -> onProductClickListener.onProductClick(view, (Integer) v.getTag()));
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
        private TextView priceTextView;
        private TextView priceCurTextView;
        private ImageView avatarImageView;
        private FrameLayout buyButton;
        private FrameLayout addButton;
        private FrameLayout deleteButton;

        ProductViewHolder(@NonNull View itemView) {
            super(itemView);
            nameTextView = itemView.findViewById(R.id.product_item_text_desc);
            countTextView = itemView.findViewById(R.id.product_item_text_count);
            priceTextView = itemView.findViewById(R.id.product_item_text_price);
            priceCurTextView = itemView.findViewById(R.id.product_item_text_price_cur);
            avatarImageView = itemView.findViewById(R.id.product_item_image_photo);
            buyButton = itemView.findViewById(R.id.product_item_button_buy);
            addButton = itemView.findViewById(R.id.product_item_button_add);
            deleteButton = itemView.findViewById(R.id.product_item_button_delete);
        }

        private void bind(@NonNull Product product) {

            notInBasket();
            nameTextView.setText(product.getName());
            priceTextView.setText(String.valueOf(product.getPriceAmount()));
            priceCurTextView.setText(product.getPriceCurrency());
            //TODO почему-то не работает загрузка из пикассо
            //Picasso.get().load("http://i.imgur.com/DvpvklR.png").resize(80, 80).into(avatarImageView);
            avatarImageView.setBackgroundResource(R.color.colorPrimary);
            Picasso.get().load(product.getPictureSource()).resize(80, 80).into(avatarImageView);

            buyButton.setOnClickListener(v -> {
                if (amountTextView.getText().toString().isEmpty() || amountTextView.getText().toString().equals("0")) {
                    amountTextView.setVisibility(View.VISIBLE);
                    amountTextView.setText(String.valueOf(product.getPriceAmount()));
                    amountCurTextView.setVisibility(View.VISIBLE);
                    amountCurTextView.setText(product.getPriceCurrency());
                } else {
                    amountTextView.setText(String.valueOf(product.getPriceAmount() +
                            Integer.valueOf(amountTextView.getText().toString())));
                }
                inBasket();
                basket.put(product, 1);
                addButton.setOnClickListener(v1 -> {
                    int selected = Integer.valueOf(countTextView.getText().toString());
                    if (selected == product.getCount()) {
                        Toast.makeText(parent, "Больше нет", Toast.LENGTH_SHORT).show();
                    } else {
                        countTextView.setText(String.format(Integer.toString(selected + 1), ""));
                        amountTextView.setText(String.valueOf(
                                product.getPriceAmount() + Integer.valueOf(amountTextView.getText().toString())));
                        basket.put(product, basket.get(product) + 1);
                    }
                });
                deleteButton.setOnClickListener(v1 -> {
                    int selected = Integer.valueOf(countTextView.getText().toString());
                    amountTextView.setText(String.valueOf(
                            Integer.valueOf(amountTextView.getText().toString()) - product.getPriceAmount()));
                    if (amountTextView.getText().toString().equals("0")) {
                        amountTextView.setVisibility(View.INVISIBLE);
                        amountCurTextView.setVisibility(View.INVISIBLE);
                    }
                    if (selected == 1) {
                        notInBasket();
                        basket.remove(product);
                    } else {
                        countTextView.setText(String.format(Integer.toString(selected - 1), ""));
                        basket.put(product, basket.get(product) - 1);
                    }
                });
            });
        }

        void inBasket() {
            buyButton.setVisibility(View.INVISIBLE);
            addButton.setVisibility(View.VISIBLE);
            deleteButton.setVisibility(View.VISIBLE);
            countTextView.setVisibility(View.VISIBLE);
        }

        void notInBasket() {
            countTextView.setText("1");
            buyButton.setVisibility(View.VISIBLE);
            addButton.setVisibility(View.INVISIBLE);
            deleteButton.setVisibility(View.INVISIBLE);
            countTextView.setVisibility(View.INVISIBLE);
        }

    }

    interface Listener {

        void onProductClick(View view, int i);

    }

}
