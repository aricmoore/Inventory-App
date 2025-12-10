package com.example.inventoryappariellemoore;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.*;
import androidx.appcompat.app.AppCompatActivity;
import java.util.ArrayList;

public class DashboardActivity extends AppCompatActivity {

    ListView inventoryGrid;
    ArrayList<String> items;
    InventoryAdapter adapter;
    TextView welcomeText;
    Button addItemButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard);

        welcomeText = findViewById(R.id.welcomeText);
        inventoryGrid = findViewById(R.id.inventoryGrid);
        addItemButton = findViewById(R.id.addItemButton);

        String username = getIntent().getStringExtra("username");
        welcomeText.setText(getString(R.string.welcome_message, username));

        // Dummy inventory data
        items = new ArrayList<>();
        items.add("Item A - 42");
        items.add("Item B - 38");
        items.add("Item C - 0");

        adapter = new InventoryAdapter();
        inventoryGrid.setAdapter(adapter);

        // Add new item button
        addItemButton.setOnClickListener(view -> {
            items.add("New Item - 0");
            adapter.notifyDataSetChanged();
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 1 && resultCode == RESULT_OK) {
            String updatedItem = data.getStringExtra("updatedItem");
            int index = data.getIntExtra("itemIndex", -1);

            if (index >= 0) {
                items.set(index, updatedItem);
                adapter.notifyDataSetChanged();
            }
        }
    }

    // Custom adapter for inventory rows
    private class InventoryAdapter extends BaseAdapter {

        @Override
        public int getCount() {
            return items.size();
        }

        @Override
        public Object getItem(int position) {
            return items.get(position);
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            View rowView = convertView;

            if (rowView == null) {
                LayoutInflater inflater = LayoutInflater.from(DashboardActivity.this);
                rowView = inflater.inflate(R.layout.inventory_row, parent, false);
            }

            TextView itemNameText = rowView.findViewById(R.id.itemNameText);
            TextView itemQuantityText = rowView.findViewById(R.id.itemQuantityText);
            Button deleteButton = rowView.findViewById(R.id.deleteButton);

            String item = items.get(position);
            String[] parts = item.split(" - ");
            String name = parts[0];
            int quantity = Integer.parseInt(parts[1]);

            itemNameText.setText(name);
            itemQuantityText.setText(String.valueOf(quantity));

            // Colour red if quantity is 0
            itemQuantityText.setTextColor(quantity == 0 ? Color.RED : Color.BLACK);

            // Delete button click
            deleteButton.setOnClickListener(v -> {
                items.remove(position);
                notifyDataSetChanged();
            });

            // Row click to edit
            rowView.setOnClickListener(v -> {
                Intent intent = new Intent(DashboardActivity.this, ItemManagementActivity.class);
                intent.putExtra("itemName", items.get(position));
                intent.putExtra("itemIndex", position);
                startActivityForResult(intent, 1);
            });

            return rowView;
        }
    }
}
