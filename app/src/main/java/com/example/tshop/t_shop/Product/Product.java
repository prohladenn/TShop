package com.example.tshop.t_shop.Product;

import java.io.Serializable;

public class Product implements Serializable {

    private String name;
    private String description;
    private String pictureSource;
    private String pictureSubnail;
    private int priceAmount;
    private String priceCurrency;
    private int valueAmount;
    private String valueUnits;
    private int count;
    private int selected;

    public Product(String name, String description,
                   String pictureSource, String pictureSubnail,
                   int priceAmount, String priceCurrency,
                   int valueAmount, String valueUnits, int count) {
        this.name = name;
        this.description = description;
        this.pictureSource = pictureSource;
        this.pictureSubnail = pictureSubnail;
        this.priceAmount = priceAmount;
        this.priceCurrency = priceCurrency;
        this.valueAmount = valueAmount;
        this.valueUnits = valueUnits;
        this.count = count;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getPictureSource() {
        return pictureSource;
    }

    public void setPictureSource(String pictureSource) {
        this.pictureSource = pictureSource;
    }

    public String getPictureSubnail() {
        return pictureSubnail;
    }

    public void setPictureSubnail(String pictureSubnail) {
        this.pictureSubnail = pictureSubnail;
    }

    public int getPriceAmount() {
        return priceAmount;
    }

    public void setPriceAmount(int priceAmount) {
        this.priceAmount = priceAmount;
    }

    public String getPriceCurrency() {
        return priceCurrency;
    }

    public void setPriceCurrency(String priceCurrency) {
        this.priceCurrency = priceCurrency;
    }

    public int getValueAmount() {
        return valueAmount;
    }

    public void setValueAmount(int valueAmount) {
        this.valueAmount = valueAmount;
    }

    public String getValueUnits() {
        return valueUnits;
    }

    public void setValueUnits(String valueUnits) {
        this.valueUnits = valueUnits;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public int getSelected() {
        return selected;
    }

    public void setSelected(int selected) {
        this.selected = selected;
    }
}
