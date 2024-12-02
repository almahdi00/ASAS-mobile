package com.example.onlineshopapp;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ListAdapter;
import android.widget.TextView;

import java.util.ArrayList;

public class ProductAdapter extends BaseAdapter implements ListAdapter {

    private Context context;
    private ArrayList<Product> products;

    public ProductAdapter(Context context, ArrayList<Product> products) {
        this.context = context;
        this.products = products;
    }

    @Override
    public int getCount() {
        return products.size();
    }

    @Override
    public Object getItem(int position) {
        return products.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            convertView = LayoutInflater.from(context).inflate(R.layout.item_product, parent, false);
        }

        Product product = products.get(position);

        TextView tvProductName = convertView.findViewById(R.id.tvName);
        TextView tvProductPrice = convertView.findViewById(R.id.tvPrice);
        TextView tvProductQuantity = convertView.findViewById(R.id.tvQuantity);

        tvProductName.setText(product.getName());
        tvProductPrice.setText(String.valueOf(product.getPrice()));
        tvProductQuantity.setText(String.valueOf(product.getQuantity()));

        return convertView;
    }
}