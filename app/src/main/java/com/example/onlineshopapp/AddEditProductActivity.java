package com.example.onlineshopapp;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class AddEditProductActivity extends AppCompatActivity {

    DatabaseHelper dbHelper;
    EditText etName, etPrice, etQuantity;
    Button btnSave;
    int productId = -1; // Default untuk produk baru

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_edit_product);

        dbHelper = new DatabaseHelper(this);

        etName = findViewById(R.id.etName);
        etPrice = findViewById(R.id.etPrice);
        etQuantity = findViewById(R.id.etQuantity);
        btnSave = findViewById(R.id.btnSave);

        // Cek apakah ada data produk yang dikirim untuk diubah
        Intent intent = getIntent();
        if (intent.hasExtra("productId")) {
            productId = intent.getIntExtra("productId", -1);
            etName.setText(intent.getStringExtra("productName"));
            etPrice.setText(String.valueOf(intent.getDoubleExtra("productPrice", 0.0)));
            etQuantity.setText(String.valueOf(intent.getIntExtra("productQuantity", 0)));
        }

        btnSave.setOnClickListener(v -> saveProduct());
    }

    private void saveProduct() {
        String name = etName.getText().toString().trim();
        String priceStr = etPrice.getText().toString().trim();
        String quantityStr = etQuantity.getText().toString().trim();

        if (name.isEmpty() || priceStr.isEmpty() || quantityStr.isEmpty()) {
            Toast.makeText(this, "Please fill all fields", Toast.LENGTH_SHORT).show();
            return;
        }

        double price = Double.parseDouble(priceStr);
        int quantity = Integer.parseInt(quantityStr);

        if (productId == -1) {
            // Tambah produk baru
            long result = dbHelper.addProduct(name, price, quantity);
            if (result != -1) {
                Toast.makeText(this, "Product added successfully", Toast.LENGTH_SHORT).show();
                finish();
            } else {
                Toast.makeText(this, "Failed to add product", Toast.LENGTH_SHORT).show();
            }
        } else {
            // Perbarui produk
            int result = dbHelper.updateProduct(productId, name, price, quantity);
            if (result > 0) {
                Toast.makeText(this, "Product updated successfully", Toast.LENGTH_SHORT).show();
                finish();
            } else {
                Toast.makeText(this, "Failed to update product", Toast.LENGTH_SHORT).show();
            }
        }
    }
}
