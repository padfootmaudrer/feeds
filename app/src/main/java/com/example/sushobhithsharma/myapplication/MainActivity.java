package com.example.sushobhithsharma.myapplication;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.ListView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private ListView listView;
    private DataAdapter listAdapter;
    private List<DataItem> dataItems;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        listView = (ListView) findViewById(R.id.list);

        dataItems = new ArrayList<DataItem>();

        listAdapter = new DataAdapter(this, dataItems);
        listView.setAdapter(listAdapter);

        JSONArray data = new JSONArray();
        JSONObject payload = new JSONObject();


        try {
            payload.put("name","John");
            payload.put("imageUrl","https://www.gv.com/img/team-large/john-zeratsky.jpg");
            payload.put("title","People");
            payload.put("text","someshit");
            payload.put("time","1234");
            payload.put("description","something");
            data.put(payload);
            parseJsonFeed(data);
        } catch (JSONException e) {
            e.printStackTrace();
        }

    }

    private void parseJsonFeed(JSONArray response) {
        try {
            for (int i = 0; i < response.length(); i++) {
                JSONObject feedObj = (JSONObject) response.get(i);

                Log.d("JSON","object "+feedObj);

                DataItem item = new DataItem();
                item.setName(feedObj.getString("name"));

                String image = feedObj.isNull("imageUrl") ? null : feedObj
                        .getString("imageUrl");

                item.setImage(image);
                item.setTitle(feedObj.getString("title"));
                item.setText(feedObj.getString("text"));
                item.setTimeStamp(feedObj.getString("time"));
                item.setDescription(feedObj.getString("description"));

                dataItems.add(item);
            }

            // notify data changes to list adapater
            listAdapter.notifyDataSetChanged();
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }


}
