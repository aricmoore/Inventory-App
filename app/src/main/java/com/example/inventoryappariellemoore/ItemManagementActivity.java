package com.example.inventoryappariellemoore;

import android.content.Intent;
import android.os.Bundle;
import android.text.InputFilter;
import android.text.InputType;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

public class ItemManagementActivity extends AppCompatActivity {

    EditText itemNameText;
    EditText itemQuantityEdit;
    Button saveButton;
    int itemIndex;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_item_management);

        itemNameText = findViewById(R.id.itemNameText);
        itemQuantityEdit = findViewById(R.id.itemQuantityEdit);
        saveButton = findViewById(R.id.saveButton);

        // Ensure it only accepts numeric input
        itemQuantityEdit.setInputType(InputType.TYPE_CLASS_NUMBER);
        itemQuantityEdit.setFilters(new InputFilter[]{ new InputFilter.LengthFilter(6) });

        // Get passed item data
        String itemName = getIntent().getStringExtra("itemName");
        itemIndex = getIntent().getIntExtra("itemIndex", -1);

        if (itemName != null && itemName.contains(" - ")) {
            String[] parts = itemName.split(" - ");
            itemNameText.setText(parts[0]);
            itemQuantityEdit.setText(parts[1]);
        } else {
            itemNameText.setText(itemName != null ? itemName : "");
            itemQuantityEdit.setText("0");
        }

        saveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String name = itemNameText.getText().toString().trim();
                String quantityStr = itemQuantityEdit.getText().toString().trim();

                if (name.isEmpty()) {
                    Toast.makeText(ItemManagementActivity.this, "Item name cannot be empty", Toast.LENGTH_SHORT).show();
                    return;
                }

                if (quantityStr.isEmpty()) {
                    Toast.makeText(ItemManagementActivity.this, "Quantity cannot be empty", Toast.LENGTH_SHORT).show();
                    return;
                }

                int quantity;
                try {
                    quantity = Integer.parseInt(quantityStr);
                    if (quantity < 0) quantity = 0;  // prevents negative quantities
                } catch (NumberFormatException e) {
                    quantity = 0;
                }

                String updatedItem = name + " - " + quantity;

                Intent resultIntent = new Intent();
                resultIntent.putExtra("updatedItem", updatedItem);
                resultIntent.putExtra("itemIndex", itemIndex);
                setResult(RESULT_OK, resultIntent);
                finish();
            }
        });
    }
}
