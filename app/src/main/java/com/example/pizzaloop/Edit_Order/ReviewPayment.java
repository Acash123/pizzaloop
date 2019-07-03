package com.example.pizzaloop.Edit_Order;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.pizzaloop.Order.MainActivity;
import com.example.pizzaloop.Order.OrderAdapter;
import com.example.pizzaloop.R;
import com.example.pizzaloop.WelcomePage;
import com.example.pizzaloop.ipAddress;

import java.util.Timer;
import java.util.TimerTask;


import static com.example.pizzaloop.Edit_Order.OrderCRUD.OrderID22;
public class ReviewPayment extends AppCompatActivity {

    public  String OrderId = OrderID22;

    public int UserId = 333; // For Now
    public String OrderedPizzaName;
    public int OrderedQty;
    public String LastPrice;
    public String PayMethod;
    public String DilMethod;
    public String TelNo;
    public String DilAdress;
    public String orderStatus;
    public  String pizzaId;

    //
    Timer timer;
    RadioGroup PaymentMethod;
    RadioButton Payment;
    RadioGroup DiliverMethod;
    RadioButton Diliver;
    EditText Address;
    EditText PhNumber;
    TextView Result;
    OrderAdapter orderAdapter;
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_pop_up_test);
        PaymentMethod = findViewById(R.id.paymentMethod);
        DiliverMethod = findViewById(R.id.addressMethod);
        Address = findViewById(R.id.order_address);
        PhNumber = findViewById(R.id.phoneNumber);
        Result = findViewById(R.id.textView2);

        String phoneNo = orderAdapter.PHONENUMBER();
        String address = orderAdapter.ADDRESS();


        Address.setText(address);
        PhNumber.setText(phoneNo);


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


    public void ExitPayment(View view) {
        Intent intent = new Intent(ReviewPayment.this, OrderCRUD.class);
        startActivity(intent);
        finish();


    }

    public void addressCheck(View view) {

        int x;
        x = DiliverMethod.getCheckedRadioButtonId();
        Diliver = (RadioButton) findViewById(x);


        Toast.makeText(this, Diliver.getText(), Toast.LENGTH_SHORT).show();
        DilMethod = (Diliver.getText().toString());


    }

    public void paymentCheck(View view) {
        int y;
        y = PaymentMethod.getCheckedRadioButtonId();
        Payment = (RadioButton) findViewById(y);
        // Toast.makeText(this, Payment.getText(), Toast.LENGTH_SHORT).show();
        PayMethod = (Payment.getText().toString());
        PayMethod = PayMethod.replace(" ", "");
        if (PayMethod.equals("CreditCard")) {
            orderStatus = "Pending";
            Toast.makeText(this, orderStatus, Toast.LENGTH_SHORT).show();
        } else if(PayMethod.equals("CashOnDilivery")){
            orderStatus = "Order_Confirmed";
            Toast.makeText(this, orderStatus, Toast.LENGTH_SHORT).show();
        }

    }

    public void Place_order(View view) {





        ReviewDetails detailActivity = new ReviewDetails();
        Intent intent = getIntent();
        OrderedPizzaName = detailActivity.Pizzaname;
        OrderedPizzaName = OrderedPizzaName.replace(" ", "");
        OrderedQty = detailActivity.z;
        LastPrice = detailActivity.lastPrice;
        pizzaId=detailActivity.PizId;

        // PaymentMethodDone
        //DiliverMethodDone
        String userId=orderAdapter.USER_ID();
        DilAdress = (Address.getText().toString());
        TelNo = PhNumber.getText().toString();

        ipAddress ipAddress=new ipAddress();
        String ip=ipAddress.getIpAddress();


        String URLO = "http://"+ip+":8080/demo/updateOrder?orderId=" + OrderID22 + "&userId=" + userId + "&pizzaName=" + OrderedPizzaName + "&qty=" + OrderedQty + "&totalPrice=" + LastPrice + "&paymentMethod=" + PayMethod + "&phoneNumber=" + TelNo + "&Address=" + DilAdress + "&OrderStatus=" + orderStatus + "&pizzaId=" + pizzaId;
        RequestQueue queue = (RequestQueue) Volley.newRequestQueue(ReviewPayment.this);
        StringRequest stringRequest = new StringRequest(Request.Method.GET, URLO,
                new HTTPResponseListner(), new HTTPErrorListner());
        queue.add(stringRequest);
        Toast.makeText(this, "ORDER "+OrderID22+"Updated ", Toast.LENGTH_SHORT).show();


        timer = new Timer();
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                Intent intent = new Intent(ReviewPayment.this, WelcomePage.class);
                startActivity(intent);
                finish();
            }
        },1500);


    }

    class HTTPResponseListner implements Response.Listener<String> {
        @Override
        public void onResponse(String response) {
            Result.setText("Response:" + response);
        }
    }

    class HTTPErrorListner implements Response.ErrorListener {
        @Override
        public void onErrorResponse(VolleyError error) {
            Result.setText(error.getMessage());
        }

    }
}
