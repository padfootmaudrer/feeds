package com.example.sushobhithsharma.myapplication;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;


public class DescriptionActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_description);
        final DataItem item = (DataItem) getIntent().getSerializableExtra("ITEM");

        String buttonText = getIntent().getStringExtra("BUTTON");

        setTitle(item.getTitle());

        TextView name = (TextView) findViewById(R.id.details_name);
        TextView text = (TextView) findViewById(R.id.details_text);
        TextView desc = (TextView) findViewById(R.id.description);
        final Button like = (Button) findViewById(R.id.btn_details_like);
        final ImageView cardImage = (ImageView) findViewById(R.id.details_image);


        if(item.getName() != null){
            name.setText("from "+item.getName());
        }

        if(item.getText() != null) {
            text.setText(item.getText());
        }

        if(item.getDescription() != null) {
             desc.setText(item.getDescription());
        }

        if (buttonText.equals("LIKE")) {
            like.setText(buttonText);
            like.setBackgroundColor(Color.parseColor("#CDEBEB"));
            like.setTextColor(Color.parseColor("#00ADB7"));
        } else {
            like.setText(buttonText);
            like.setBackgroundColor(Color.parseColor("#00ADB7"));
            like.setTextColor(Color.parseColor("#FFFFFF"));
        }

        Thread t = new Thread() {
            public void run() {
                super.run();
                URL url = null;
                try {
                    url = new URL(item.getImage());
                } catch (MalformedURLException e) {
                    e.printStackTrace();
                }
                Bitmap bmp = null;
                if(url == null){
                    cardImage.setVisibility(View.GONE);
                } else {
                    try {
                        bmp = BitmapFactory.decodeStream(url.openConnection().getInputStream());
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    final Bitmap finalBmp = bmp;
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            cardImage.setImageBitmap(finalBmp);
                        }
                    });
                }

            }
        };
        t.start();

        like.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String btnText = like.getText().toString();
                if (btnText.equals("LIKE")) {
                    like.setText("UNLIKE");
                    like.setBackgroundColor(Color.parseColor("#00ADB7"));
                    like.setTextColor(Color.parseColor("#FFFFFF"));

                } else {
                    like.setText("LIKE");
                    like.setBackgroundColor(Color.parseColor("#CDEBEB"));
                    like.setTextColor(Color.parseColor("#00ADB7"));
                }

            }
        });

    }
}
