package com.example.sushobhithsharma.myapplication;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.os.Parcelable;
import android.support.v7.widget.CardView;
import android.text.Html;
import android.text.TextUtils;
import android.text.format.DateUtils;
import android.text.method.LinkMovementMethod;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import java.io.IOException;
import java.io.Serializable;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;
import android.R.color;
/**
 * Created by sushobhith.sharma on 28/09/17.
 */

public class DataAdapter extends BaseAdapter {
    private Activity activity;
    private List<DataItem> dataItems;
    private LayoutInflater inflater;

    public DataAdapter(Activity activity, List<DataItem> dataItems) {
        this.activity = activity;
        this.dataItems = dataItems;
    }

    @Override
    public int getCount() {
        return dataItems.size();
    }

    @Override
    public Object getItem(int i) {
        return dataItems.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView (int i, View view, ViewGroup viewGroup) {


        if (inflater == null)
            inflater = (LayoutInflater) activity
                    .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        if (view == null)
            view = inflater.inflate(R.layout.data_item, null);

        TextView name = view.findViewById(R.id.name);
        TextView title = view.findViewById(R.id.card_title);
        TextView text = view.findViewById(R.id.card_text);
        final Button like = view.findViewById(R.id.btn_like);
        CardView card = view.findViewById(R.id.card);
        final ImageView cardImage = view.findViewById(R.id.card_image);



        final DataItem item = dataItems.get(i);

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
                activity.runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        cardImage.setImageBitmap(finalBmp);
                    }
                });
            }

            }
        };
        t.start();


        card.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent descActivity = new Intent(activity,DescriptionActivity.class);
                descActivity.putExtra("ITEM",item);
                descActivity.putExtra("BUTTON",like.getText().toString());
                activity.startActivity(descActivity);
            }
        });

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

        return view;
    }
}

