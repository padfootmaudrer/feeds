package com.example.sushobhithsharma.myapplication;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
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
import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;

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
        Button like = view.findViewById(R.id.btn_like);
        CardView card = view.findViewById(R.id.card);
        TextView timestamp = view.findViewById(R.id.timestamp);
        final ImageView cardImage = view.findViewById(R.id.card_image);



        final DataItem item = dataItems.get(i);
        Log.d("JSON","dataItem "+item);
        name.setText(item.getName());
        title.setText(item.getTitle());
        text.setText(item.getText());


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
        };
        t.start();


        card.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent descActivity = new Intent(activity,DescriptionActivity.class);
                descActivity.putExtra("DESCRIPTION",item.getDescription());
                activity.startActivity(descActivity);
            }
        });

        like.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // LIKE BUTTON CHANGE LOGIC
            }
        });


        // Converting timestamp into x ago format
//        CharSequence timeAgo = DateUtils.getRelativeTimeSpanString(
//                Long.parseLong(item.getTimeStamp()),
//                System.currentTimeMillis(), DateUtils.SECOND_IN_MILLIS);
//        timestamp.setText(timeAgo);
        timestamp.setText(item.getTimeStamp());

        // Chcek for empty status message
//        if (!TextUtils.isEmpty(item.getStatus())) {
//            statusMsg.setText(item.getStatus());
//            statusMsg.setVisibility(View.VISIBLE);
//        } else {
//            // status is empty, remove from view
//            statusMsg.setVisibility(View.GONE);
//        }

        // Checking for null feed url
//        if (item.getUrl() != null) {
//            url.setText(Html.fromHtml("<a href=\"" + item.getUrl() + "\">"
//                    + item.getUrl() + "</a> "));
//
//            // Making url clickable
//            url.setMovementMethod(LinkMovementMethod.getInstance());
//            url.setVisibility(View.VISIBLE);
//        } else {
//            // url is null, remove from the view
//            url.setVisibility(View.GONE);
//        }

        // user profile pic
//        profilePic.setImageUrl(item.getProfilePic(), imageLoader);
//
//        // Feed image
//        if (item.getImge() != null) {
//            feedImageView.setImageUrl(item.getImge(), imageLoader);
//            feedImageView.setVisibility(View.VISIBLE);
//            feedImageView
//                    .setResponseObserver(new FeedImageView.ResponseObserver() {
//                        @Override
//                        public void onError() {
//                        }
//
//                        @Override
//                        public void onSuccess() {
//                        }
//                    });
//        } else {
//            feedImageView.setVisibility(View.GONE);
//        }

        return view;
    }
}

