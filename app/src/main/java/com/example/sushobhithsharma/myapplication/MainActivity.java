package com.example.sushobhithsharma.myapplication;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
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
        JSONObject payload2 = new JSONObject();
        JSONObject payload3 = new JSONObject();
        JSONObject payload4 = new JSONObject();


        try {
            payload.put("name","John Zeratsky");
            payload.put("imageUrl","http://thewirecutter.com/wp-content/uploads/2016/09/sierra-blog-lede-630.jpg");
            payload.put("title","People");
            payload.put("text","Lorem ipsum dolor sit amet, consectetur adipiscing elit.");
            payload.put("time","1501577379000");
            payload.put("description","Lorem ipsum dolor sit amet, consectetur adipiscing elit. Ut dictum, lectus non rhoncus tincidunt,\n" +
                    "    quam lectus lacinia nunc, vitae egestas felis ligula a eros. Nulla ac ex finibus, sodales nisl ac, convallis urna. Donec\n" +
                    "    ornare aliquam dapibus. Ut aliquam massa ex, a maximus lectus condimentum vitae. Donec rutrum aliquet\n" +
                    "    ullamcorper. Suspendisse sit amet pulvinar tellus");


            payload2.put("name","Sierra");
            payload2.put("imageUrl","http://thewirecutter.com/wp-content/uploads/2016/09/sierra-blog-lede-630.jpg");
            payload2.put("title","People");
            payload2.put("time","1501577379000");
            payload2.put("description","Lorem ipsum dolor sit amet, consectetur adipiscing elit. Ut dictum, lectus non rhoncus tincidunt,\n" +
                    "    quam lectus lacinia nunc, vitae egestas felis ligula a eros. Nulla ac ex finibus, sodales nisl ac, convallis urna. Donec\n" +
                    "    ornare aliquam dapibus. Ut aliquam massa ex, a maximus lectus condimentum vitae. Donec rutrum aliquet\n" +
                    "    ullamcorper. Suspendisse sit amet pulvinar tellus");

            payload3.put("name","Happen");
            payload3.put("title","Quote");
            payload3.put("text","You have to make it happen");
            payload3.put("time","1501404579000");
            payload3.put("description","Lorem ipsum dolor sit amet, consectetur adipiscing elit. Ut dictum, lectus non rhoncus tincidunt,\n" +
                    "    quam lectus lacinia nunc, vitae egestas felis ligula a eros. Nulla ac ex finibus, sodales nisl ac, convallis urna. Donec\n" +
                    "    ornare aliquam dapibus. Ut aliquam massa ex, a maximus lectus condimentum vitae. Donec rutrum aliquet\n" +
                    "    ullamcorper. Suspendisse sit amet pulvinar tellus.");



            payload4.put("name","John Zeratsky");
            payload4.put("imageUrl","http://thewirecutter.com/wp-content/uploads/2016/09/sierra-blog-lede-630.jpg");
            payload4.put("title","People");
            payload4.put("text","Lorem ipsum dolor sit amet, consectetur adipiscing elit.");
            payload4.put("time","1501577379000");
            payload4.put("description","Lorem ipsum dolor sit amet, consectetur adipiscing elit. Ut dictum, lectus non rhoncus tincidunt,\n" +
                    "    quam lectus lacinia nunc, vitae egestas felis ligula a eros. Nulla ac ex finibus, sodales nisl ac, convallis urna. Donec\n" +
                    "    ornare aliquam dapibus. Ut aliquam massa ex, a maximus lectus condimentum vitae. Donec rutrum aliquet\n" +
                    "    ullamcorper. Suspendisse sit amet pulvinar tellus");

            data.put(payload);
            data.put(payload2);
            data.put(payload3);
            data.put(payload4);

            parseJsonFeed(data);
        } catch (JSONException e) {
            e.printStackTrace();
        }

    }

    private void parseJsonFeed(JSONArray response) {
        try {
            for (int i = 0; i < response.length(); i++) {
                JSONObject feedObj = (JSONObject) response.get(i);

                DataItem item = new DataItem();

                item.setName(feedObj.getString("name"));

                String image = feedObj.isNull("imageUrl") ? null : feedObj
                        .getString("imageUrl");

                item.setImage(image);

                String title = feedObj.isNull("title") ? null : feedObj
                        .getString("title");
                item.setTitle(title);

                String text = feedObj.isNull("text") ? null : feedObj
                        .getString("text");
                item.setText(text);

                String time = feedObj.isNull("time") ? null : feedObj
                        .getString("time");
                item.setTimeStamp(time);

                String description = feedObj.isNull("description") ? null : feedObj
                        .getString("description");
                item.setDescription(description);

                dataItems.add(item);
            }

            listAdapter.notifyDataSetChanged();
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }


}
