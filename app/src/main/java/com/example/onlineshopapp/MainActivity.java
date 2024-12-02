package com.example.onlineshopapp;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    DatabaseHelper dbHelper;
    ProductAdapter productAdapter;
    ArrayList<Product> productList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        dbHelper = new DatabaseHelper(this);
        productList = new ArrayList<>();

        Button btnAddProduct = findViewById(R.id.btnAddProduct);
        ListView lvProducts = findViewById(R.id.lvProducts);

        btnAddProduct.setOnClickListener(v -> {
            Intent intent = new Intent(MainActivity.this, AddEditProductActivity.class);
            startActivity(intent);
        });

        loadProducts();

        productAdapter = new ProductAdapter(this, productList);
        lvProducts.setAdapter(productAdapter);
    }

    private void loadProducts() {
        productList.clear();
        Cursor cursor = dbHelper.getAllProducts();
        if (cursor.moveToFirst()) {
            do {
                int id = cursor.getInt(0);
                String name = cursor.getString(1);
                double price = cursor.getDouble(2);
                int quantity = cursor.getInt(3);

                productList.add(new Product(id, name, price, quantity));
            } while (cursor.moveToNext());
        }
        cursor.close();
    }
}
