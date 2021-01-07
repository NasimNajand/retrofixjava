package com.najand.rextorift;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.najand.rextorift.model.Items;
import com.squareup.picasso.Picasso;

public class ItemActivity extends AppCompatActivity {
    private ImageView imageView;
    private TextView titleTextView;
    private TextView contentTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_item);
        findViews();
        Intent i = getIntent();
        Items item = (Items)i.getSerializableExtra("item");
        Picasso.get()
                .load(item.getHdUrl())
                .placeholder(R.drawable.nebs)
                .into(imageView);
        titleTextView.setText(item.getTitle());
        contentTextView.setText(item.getExplanation());
    }

    private void findViews() {
        imageView = findViewById(R.id.imageView);
        titleTextView = findViewById(R.id.title_textView);
        contentTextView = findViewById(R.id.content_textView);
    }
}