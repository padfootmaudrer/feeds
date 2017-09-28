package com.example.sushobhithsharma.myapplication;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class DescriptionActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_description);

        String description = getIntent().getStringExtra("DESCRIPTION");
        // TextView description = (TextView) view.findViewById(R.id.description);
//        description.setText(description);


    }
}
