package com.example.pizzaloop;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

import static com.example.pizzaloop.OrderCRUD.EXTRA_PIZZAID;
import static com.example.pizzaloop.OrderCRUD.EXTRA_OrderId;
public class ReviewOrder extends AppCompatActivity implements Adapter.OnItemClickListener {
    //New Code variables
    //New Code variables
    public static final String EXTRA_URL = "imageUrl";
    public static final String EXTRA_price = "price";
    public static final String EXTRA_details = "details";
    public static final String EXTRA_name = "name";
    public static final String EXTRA_pizzaId="pizzaId";

    //

    //Member Variables
    private RecyclerView mRecyclerView;
    private Adapter mExampleAdapter;
    private ArrayList<item_details> mExampleList;
    private RequestQueue mRequestQueue;
    //Done


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        mRecyclerView = findViewById(R.id.rv_list);
        mRecyclerView.setHasFixedSize(true);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));

        mExampleList = new ArrayList<>();

        mRequestQueue = Volley.newRequestQueue(this);
        parseJSON();


    }

    private void parseJSON() {
        ipAddress ipAddress=new ipAddress();
        String ip=ipAddress.getIpAddress();
        String url = "http://"+ip+":8080/demo/all";


        JsonArrayRequest request = new JsonArrayRequest(Request.Method.GET, url,
                null, new ReviewOrder.HTTPResponseListner(), new ReviewOrder.HTTPErrorListner());

        mRequestQueue.add(request);

    }

    @Override
    public void onItemClick(int position) {
        Intent detailIntent = new Intent(this, ReviewDetails.class);
        item_details clickedItem = mExampleList.get(position);
        Intent intent = getIntent();

        detailIntent.putExtra(EXTRA_URL, clickedItem.getBackgroundURL());
        detailIntent.putExtra(EXTRA_name, clickedItem.getName());
        detailIntent.putExtra(EXTRA_price, clickedItem.getPrice());
        detailIntent.putExtra(EXTRA_details, clickedItem.getDetails());
        detailIntent.putExtra(EXTRA_pizzaId,clickedItem.getId());

        startActivity(detailIntent);


    }


    class HTTPResponseListner implements Response.Listener<JSONArray> {

        @Override
        public void onResponse(JSONArray jsonArray) {

            for (int i = 0; i < jsonArray.length(); i++) {
                try {
                    JSONObject hit = jsonArray.getJSONObject(i);
                    item_details item_details;
                    String id=hit.getString("pizzaId");
                    String name = hit.getString("name");
                    String url = hit.getString("imageUrl");
                    Double price = hit.getDouble("price");
                    String details = hit.getString("description");

                    mExampleList.add(new item_details(name, price, url, details,id));

                    mExampleAdapter = new Adapter(ReviewOrder.this, mExampleList);
                    mRecyclerView.setAdapter(mExampleAdapter);
                    mExampleAdapter.setonItemClickListner(ReviewOrder.this);

                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    class HTTPErrorListner implements Response.ErrorListener {
        @Override
        public void onErrorResponse(VolleyError error) {

        }

    }


    //PopUp msg for Exit
    @Override
    public void onBackPressed() {
        new AlertDialog.Builder(this)
                .setIcon(android.R.drawable.ic_dialog_alert)
                .setTitle("Closing Activity")
                .setMessage("Are you sure you want to close this activity?")
                .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        finish();
                    }

                })
                .setNegativeButton("No", null)
                .show();
    }

    @Override
    public void onWindowFocusChanged(boolean hasFocus) {
        super.onWindowFocusChanged(hasFocus);
        if (hasFocus) {
            hideSystemUI();
        }
    }

    private void hideSystemUI() {
        // Enables regular immersive mode.
        // For "lean back" mode, remove SYSTEM_UI_FLAG_IMMERSIVE.
        // Or for "sticky immersive," replace it with SYSTEM_UI_FLAG_IMMERSIVE_STICKY
        View decorView = getWindow().getDecorView();
        decorView.setSystemUiVisibility(
                View.SYSTEM_UI_FLAG_IMMERSIVE
                        // Set the content to appear under the system bars so that the
                        // content doesn't resize when the system bars hide and show.
                        | View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                        | View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                        | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                        // Hide the nav bar and status bar
                        | View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
                        | View.SYSTEM_UI_FLAG_FULLSCREEN);
    }


}