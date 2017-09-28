package com.example.sushobhithsharma.myapplication;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.os.Parcelable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;

import static com.example.sushobhithsharma.myapplication.R.id.card;

public class DescriptionActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_description);
        final DataItem item = (DataItem) getIntent().getSerializableExtra("ITEM");

        String buttonText = getIntent().getStringExtra("BUTTON");


        TextView name = (TextView) findViewById(R.id.details_name);
        TextView title = (TextView) findViewById(R.id.details_title);
        TextView text = (TextView) findViewById(R.id.details_text);
        TextView desc = (TextView) findViewById(R.id.description);
        final Button like = (Button) findViewById(R.id.btn_details_like);
        final ImageView cardImage = (ImageView) findViewById(R.id.details_image);


        if(item.getName() == null){
            name.setVisibility(View.GONE);
        } else {
            name.setText(item.getName());
        }

        if(item.getTitle() == null) {
            title.setVisibility(View.GONE);
        } else {
            title.setText(item.getTitle());
        }

        if(item.getText() == null) {
            text.setVisibility(View.GONE);
        } else {
            text.setText(item.getText());
        }

        if(item.getDescription() == null) {
            desc.setVisibility(View.GONE);
        } else {
            desc.setText(item.getText());
        }

        like.setText(buttonText);

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
                    like.setBackgroundColor(Color.parseColor("#42A5F5"));
                } else {
                    like.setText("LIKE");
                    like.setBackgroundColor(Color.parseColor("#E3F2FD"));
                }

            }
        });


        Log.d("JSON","description "+item);


    }
}
