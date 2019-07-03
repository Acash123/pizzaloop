package com.example.pizzaloop.Edit_Order;

import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.craftman.cardform.Card;
import com.craftman.cardform.CardForm;
import com.craftman.cardform.OnPayBtnClickListner;
import com.example.pizzaloop.Order.Order;
import com.example.pizzaloop.Order.OrderAdapter;
import com.example.pizzaloop.R;
import com.example.pizzaloop.WelcomePage;
import com.example.pizzaloop.ipAddress;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;


public class OrderCRUD extends AppCompatActivity implements OrderAdapter.OnItemClickListener {
    public static  String  EXTRA_OrderId;
    public static final String EXTRA_userId = "userId";
    public static final String EXTRA_pizzaName = "pizzaName";
    public static final int EXTRA_qty = 0;
    public static final String EXTRA_totalPrice = "totalPrice";
    public static final String EXTRA_paymentMethod = "paymentMethod";
    public static final String EXTRA_phoneNumber = "phoneNumber";
    public static final String EXTRA_Address = "Address";
    public static final String EXTRA_OrderStatus = "OrderStatus";
    public static final String EXTRA_PIZZAID="pizzaId";
    public  static String OrderID22;

    //
    OrderAdapter orderAdapter;
    Dialog myDialog;
Timer timer;
    //Member Variables
    private RecyclerView mRecyclerView;
    private OrderAdapter mExampleAdapter;
    private ArrayList<Order> mExampleList;
    private RequestQueue mRequestQueue;
    EditText UserID;
    Button check;
    public String OrderId;
    public String OrderStatusCheck;
    //Done


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        //CREDIT CARD CODE
       // myDialog = new Dialog(this);




        ///////////////////
        super.onCreate(savedInstanceState);
        setContentView(R.layout.my_orders);

        check = findViewById(R.id.Check_Oder);
        UserID=findViewById(R.id.order_user_id);
        mRecyclerView = findViewById(R.id.rv_list);

        mRecyclerView.setHasFixedSize(true);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));

        mExampleList = new ArrayList<>();

        mRequestQueue = Volley.newRequestQueue(this);

check.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View v) {
        Intent intent=getIntent();
        //intent.getStringExtra(EXTRA_OrderStatus);
        OrderStatusCheck=intent.getStringExtra(OrderCRUD.EXTRA_OrderStatus);
        OrderId=UserID.getText().toString();
        OrderID22=UserID.getText().toString();
        parseJSON();


    }
});
        myDialog = new Dialog(this);



    }
    public void Confirm_Order(View v){
        OrderStatusCheck=orderAdapter.ORDER_STATUS();
        if(OrderStatusCheck.equals("Order_Confirmed")){
            Button button=findViewById(R.id.button4);
            button.setText("ORDER CONFIRMED"); }
          else {




            myDialog.setContentView(R.layout.creditcardpopup);
            myDialog.show();
            CardForm cardForm = (CardForm) myDialog.findViewById(R.id.cardform);
            TextView txtDes=(TextView)myDialog.findViewById(R.id.payment_amount);
            Button btnPay=(Button)myDialog.findViewById(R.id.btn_pay);


            txtDes.setText("RS:"+orderAdapter.TOTAL_PRICE().toString());
            btnPay.setText(String.format("Payer %s",txtDes.getText()));
            cardForm.setPayBtnClickListner(new OnPayBtnClickListner() {
                @Override
                public void onClick(Card card) {
                    Toast.makeText(OrderCRUD.this,"YOUR ORDER IS CONFIRMED :)",Toast.LENGTH_SHORT).show();
                    JSONORDER_CONFIRMED();
                      Intent intent=new Intent(OrderCRUD.this,WelcomePage.class);
                      finish();

                }
            });



          /////////////////////////////////////////////////////////////////////

          }


    }

    //MEW CODE FOR CREDIT CARD FROM HERE


    private void JSONORDER_CONFIRMED() {
        ipAddress ipAddress=new ipAddress();
        String ip=ipAddress.getIpAddress();
        String UserId=orderAdapter.USER_ID();
        String OrderedPizzaName=orderAdapter.PIZZA_NAME();
        String OrderedQty= String.valueOf(orderAdapter.QTY());
        String LastPrice= String.valueOf(orderAdapter.TOTAL_PRICE());
        String DilAdress=orderAdapter.ADDRESS();
        String PayMethod=orderAdapter.PAYMENTMETHOD();
        String TelNo=orderAdapter.PHONENUMBER();

         String temp="Order_Confirmed";
        String pizzaId=orderAdapter.PIZZAID();


        String URLO = "http://"+ip+":8080/demo/updateOrder?orderId=" + OrderID22 + "&userId=" + UserId + "&pizzaName=" + OrderedPizzaName + "&qty=" + OrderedQty + "&totalPrice=" + LastPrice + "&paymentMethod=" + PayMethod + "&phoneNumber=" + TelNo + "&Address=" + DilAdress + "&OrderStatus=" + temp + "&pizzaId=" + pizzaId;
        RequestQueue queue = (RequestQueue) Volley.newRequestQueue(OrderCRUD.this);
        StringRequest stringRequest = new StringRequest(Request.Method.GET, URLO,
                new OrderCRUD.HTTPResponseListner3(), new OrderCRUD.HTTPErrorListner3());
        queue.add(stringRequest);
        Toast.makeText(this, "ORDER "+OrderID22+"Updated ", Toast.LENGTH_SHORT).show();



////

    }





    //END OF CODE FOR CREDIT CARD
    private void parseJSON() {
        ipAddress ipAddress=new ipAddress();
        String ip=ipAddress.getIpAddress();
        String url = "http://"+ip+":8080/demo/findByOrderId?id="+OrderId;


        JsonArrayRequest request = new JsonArrayRequest(Request.Method.GET, url,
                null, new HTTPResponseListner(), new HTTPErrorListner());

        mRequestQueue.add(request);

    }

    public void cancel_order(View v){

        OrderStatusCheck=orderAdapter.ORDER_STATUS();
        if(OrderStatusCheck.equals("Order_Confirmed")){
            Toast.makeText(OrderCRUD.this,"YOUR ORDER IS ALREADY CONFIRMED :)",Toast.LENGTH_SHORT).show();}

        else{

        AlertDialog.Builder builder=new AlertDialog.Builder(OrderCRUD.this);
       builder.setMessage("Do You want to Remove The Order")
              .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                  @Override
                  public void onClick(DialogInterface dialog, int which) {
                      parseJSON2();
                      Toast.makeText(OrderCRUD.this, "Order Removed :(", Toast.LENGTH_SHORT).show();
                      Button button_cancel_O = findViewById(R.id.button_cancel);
                      button_cancel_O.setText("Order Canceled");
                      timer = new Timer();
                      timer.schedule(new TimerTask() {
                          @Override
                          public void run() {
                              Intent intent = new Intent(OrderCRUD.this, WelcomePage.class);
                              startActivity(intent);
                              finish();
                          }
                      },2500);
                  }
              }).setNegativeButton("NO", new DialogInterface.OnClickListener() {
           @Override
           public void onClick(DialogInterface dialog, int which) {
               Toast.makeText(OrderCRUD.this, "Order Is Alive :)", Toast.LENGTH_SHORT).show();
           }
       });
        AlertDialog alertDialog=builder.create();
        alertDialog.show();

    }}
    private void parseJSON2() {
        ipAddress ipAddress=new ipAddress();
        String ip=ipAddress.getIpAddress();
        String url = "http://"+ip+":8080/demo/deleteByOrderId?id="+OrderId;


        JsonArrayRequest request = new JsonArrayRequest(Request.Method.GET, url,
                null, new HTTPResponseListner2(), new HTTPErrorListner());

        mRequestQueue.add(request);


    }

    @Override
    public void onItemClick(int position) {

        OrderStatusCheck=orderAdapter.ORDER_STATUS();
        if(OrderStatusCheck.equals("Order_Confirmed")){
            Toast.makeText(OrderCRUD.this,"YOUR ORDER IS ALREADY CONFIRMED :)",Toast.LENGTH_SHORT).show();

        }else {

            Intent detailIntent = new Intent(this, OrderCRUD.class);//ReviewMain

            Intent detailIntent2 = new Intent(this, ReviewMain.class);
            Order clickedItem = mExampleList.get(position);


            detailIntent.putExtra(String.valueOf(EXTRA_OrderId), clickedItem.getOrderId());
            detailIntent.putExtra(String.valueOf(EXTRA_userId), clickedItem.getUserId());
            detailIntent.putExtra(EXTRA_pizzaName, clickedItem.getPizzaName());
            detailIntent.putExtra(String.valueOf(EXTRA_qty), clickedItem.getQty());
            detailIntent.putExtra(EXTRA_paymentMethod, clickedItem.getPaymentMethod());
            detailIntent.putExtra(EXTRA_phoneNumber, clickedItem.getPhoneNumber());
            detailIntent.putExtra(EXTRA_Address, clickedItem.getAddress());
            detailIntent.putExtra(EXTRA_OrderStatus, clickedItem.getOrderStatus());
            detailIntent.putExtra(EXTRA_totalPrice, clickedItem.getTotalPrice());
            detailIntent.putExtra(EXTRA_PIZZAID, clickedItem.getPizzaId());

            startActivity(detailIntent2);
            finish();//new
        }
    }


    class HTTPResponseListner implements Response.Listener<JSONArray> {

        @Override
        public void onResponse(JSONArray jsonArray) {

            for (int i = 0; i < jsonArray.length(); i++) {
                try {
                    JSONObject hit = jsonArray.getJSONObject(i);

                    String OrderId1 = hit.getString("orderId");
                    String userId = hit.getString("userId");
                     String pizzaName =  hit.getString("pizzaName");
                     int qty =  hit.getInt("qty");
                     Double TotalPrice =  hit.getDouble("totalPrice");
                     String paymentMethod =  hit.getString("paymentMethod");
                    String phoneNumber =  hit.getString("phoneNumber");
                     String Address = hit.getString( "address");
                      String OrderStatus =  hit.getString("orderStatus");
                      String PizzaId=hit.getString("pizzaId");

                    mExampleList.add(new Order(OrderId1,userId , pizzaName, qty,TotalPrice,paymentMethod,phoneNumber,Address,OrderStatus,PizzaId));

                  mExampleAdapter =  new OrderAdapter(OrderCRUD.this, mExampleList);
                    mRecyclerView.setAdapter(mExampleAdapter);
                   mExampleAdapter.setonItemClickListner(OrderCRUD.this);

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


    class HTTPResponseListner2 implements Response.Listener<JSONArray> {

        @Override
        public void onResponse(JSONArray jsonArray) {

            for (int i = 0; i < jsonArray.length(); i++) {
                try {
                    JSONObject hit = jsonArray.getJSONObject(i);

                    String OrderId = hit.getString("orderId");
                    String userId = hit.getString("userId");
                    String pizzaName =  hit.getString("pizzaName");
                    int qty =  hit.getInt("qty");
                    Double TotalPrice =  hit.getDouble("totalPrice");
                    String paymentMethod =  hit.getString("paymentMethod");
                    String phoneNumber =  hit.getString("phoneNumber");
                    String Address = hit.getString( "address");
                    String OrderStatus =  hit.getString("orderStatus");
                    String PizzaId=hit.getString("pizzaId");

                  mExampleList.add(new Order(OrderId,userId , pizzaName, qty,TotalPrice,paymentMethod,phoneNumber,Address,OrderStatus,PizzaId));

                    mExampleAdapter =  new OrderAdapter(OrderCRUD.this, mExampleList);
                   mRecyclerView.setAdapter(mExampleAdapter);
                   mExampleAdapter.setonItemClickListner(OrderCRUD.this);

                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }
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




    class HTTPResponseListner3 implements Response.Listener<String> {
        @Override
        public void onResponse(String response) {
           // Toast.makeText(OrderCRUD.this, response, Toast.LENGTH_SHORT).show();
        }
    }

    class HTTPErrorListner3 implements Response.ErrorListener {
        @Override
        public void onErrorResponse(VolleyError error) {
            Toast.makeText(OrderCRUD.this, (CharSequence) error, Toast.LENGTH_SHORT).show();
        }

    }

    }
