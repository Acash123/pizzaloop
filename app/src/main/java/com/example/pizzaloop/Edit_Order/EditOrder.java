package com.example.pizzaloop.Edit_Order;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.NumberPicker;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.example.pizzaloop.Order.DetailActivity;
import com.example.pizzaloop.Order.Payment;
import com.example.pizzaloop.R;
import com.squareup.picasso.Picasso;

import java.text.DecimalFormat;

import static com.example.pizzaloop.Order.MainActivity.EXTRA_URL;
import static com.example.pizzaloop.Order.MainActivity.EXTRA_details;
import static com.example.pizzaloop.Order.MainActivity.EXTRA_name;
import static com.example.pizzaloop.Order.MainActivity.EXTRA_pizzaId;
import static com.example.pizzaloop.Order.MainActivity.EXTRA_price;

import static com.example.pizzaloop.Edit_Order.OrderCRUD.EXTRA_OrderId;
import static com.example.pizzaloop.Edit_Order.OrderCRUD.EXTRA_Address;
import static com.example.pizzaloop.Edit_Order.OrderCRUD.EXTRA_OrderStatus;
import static com.example.pizzaloop.Edit_Order.OrderCRUD.EXTRA_paymentMethod;
import static com.example.pizzaloop.Edit_Order.OrderCRUD.EXTRA_phoneNumber;
import static com.example.pizzaloop.Edit_Order.OrderCRUD.EXTRA_pizzaName;
import static com.example.pizzaloop.Edit_Order.OrderCRUD.EXTRA_qty;
import static com.example.pizzaloop.Edit_Order.OrderCRUD.EXTRA_totalPrice;
import static com.example.pizzaloop.Edit_Order.OrderCRUD.EXTRA_userId;


public class EditOrder extends AppCompatActivity {
    public NumberPicker numberPicker;
    ImageButton imageButton;
    RadioGroup rg;
    RadioButton rb;
    Button cancel;


    public static final String EXTRA_QTY = "QTY";
    public static final String EXTRA_LPRICE = "LastPrice";


    public static String Pizzaname;
    public static String PizId;

    public static String Pass_EXTRA_OrderId;
    public static String Pass_EXTRA_Address;
    public static String Pass_EXTRA_OrderStatus;
    public static String Pass_EXTRA_paymentMethod;
    public static String Pass_EXTRA_phoneNumber;
    public static String Pass_EXTRA_pizzaName;
    public static int Pass_EXTRA_qty;
    public Double Pass_EXTRA_totalPrice;
    public static String Pass_EXTRA_userId;
    public static String Pass_EXTRA_PizzaId;




    //------------------------------------------
    public double dprice;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_detail);


        rg = findViewById(R.id.Psize);
        cancel = findViewById(R.id.dcancel);

        numberPicker = findViewById(R.id.pqty);
        imageButton = findViewById(R.id.pback);
        imageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(EditOrder.this, ReviewMain.class);
                startActivity(intent);
                finish(); // new
            }
        });
        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(EditOrder.this, ReviewMain.class);
                startActivity(intent);
                finish(); //new
            }
        });



        Intent intent = getIntent();
        String imageUrl = intent.getStringExtra(EXTRA_URL);
        PizId = intent.getStringExtra(EXTRA_pizzaId);
        dprice = intent.getDoubleExtra(EXTRA_price, 0.00);
        Pizzaname = intent.getStringExtra(EXTRA_name);
        String details = intent.getStringExtra(EXTRA_details);

        ImageView imageView = findViewById(R.id.pimage);


        TextView textViewname = findViewById(R.id.pname);


        Picasso.get().load(imageUrl).fit().centerInside().into(imageView);
        textViewname.setText(Pizzaname);
//



                 Pass_EXTRA_OrderId= intent.getStringExtra(EXTRA_OrderId);
                 Pass_EXTRA_Address=intent.getStringExtra(EXTRA_Address);
                 Pass_EXTRA_OrderStatus= intent.getStringExtra(EXTRA_OrderStatus);
                 Pass_EXTRA_paymentMethod=intent.getStringExtra(EXTRA_paymentMethod);
                 Pass_EXTRA_phoneNumber=intent.getStringExtra(EXTRA_phoneNumber);
                 Pass_EXTRA_pizzaName=intent.getStringExtra(EXTRA_pizzaName);
                 Pass_EXTRA_qty= intent.getIntExtra(String.valueOf(EXTRA_qty),0);
                 Pass_EXTRA_totalPrice=intent.getDoubleExtra(EXTRA_totalPrice,0.00);
                 Pass_EXTRA_userId=intent.getStringExtra(EXTRA_userId);



        TextView EditPrice = findViewById(R.id.pprice);
        EditPrice.setText(Pass_EXTRA_totalPrice.toString());
        TextView EditPizzaName=findViewById(R.id.pname);
        EditPizzaName.setText(Pass_EXTRA_pizzaName);
        TextView EditPizzaDetails= findViewById(R.id.pdetails);
        EditPizzaDetails.setText("");



    }

    public void dorder(View v) {

        Intent Dintent = new Intent(EditOrder.this, Payment.class);
        startActivity(Dintent);
        DetailActivity detailActivity = new DetailActivity();
        Dintent.putExtra(EXTRA_QTY, z);
        Dintent.putExtra(EXTRA_LPRICE, lastPrice);
        finish();//new


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

    public static String lastPrice;

    public static int z;

    public void rbClick(View v) {
        int x;
        x = rg.getCheckedRadioButtonId();
        rb = (RadioButton) findViewById(x);

        String y;
        TextView textViewprice2 = findViewById(R.id.pprice);
        numberPicker = findViewById(R.id.pqty);
        Toast.makeText(this, rb.getText(), Toast.LENGTH_SHORT).show();
        y = (rb.getText().toString());

        DecimalFormat temp = new DecimalFormat("####.##");
        if (y.equals("   Small")) {
            z = numberPicker.getValue();
            textViewprice2.setText("Rs. " + temp.format(z * 0.5 * (dprice)));
            lastPrice = (temp.format(z * 0.5 * (dprice)));
        } else if (y.equals("   Medium")) {
            z = numberPicker.getValue();
            textViewprice2.setText("Rs. " + temp.format(z * 0.75 * (dprice)));
            lastPrice = (temp.format(z * 0.75 * (dprice)));
        } else if (y.equals("   Large")) {
            z = numberPicker.getValue();
            textViewprice2.setText("Rs. " + temp.format(z * 1.0 * (dprice)));
            lastPrice = (temp.format(z * 1.0 * (dprice)));
        }
//


        numberPicker.setOnValueChangedListener(new NumberPicker.OnValueChangeListener() {
            @Override
            public void onValueChange(NumberPicker picker, int oldVal, int newVal) {
                int x;
                x = rg.getCheckedRadioButtonId();
                rb = (RadioButton) findViewById(x);

                String y;
                TextView textViewprice2 = findViewById(R.id.pprice);
                numberPicker = findViewById(R.id.pqty);

                y = (rb.getText().toString());

                DecimalFormat temp = new DecimalFormat("####.##");
                if (y.equals("   Small")) {
                    z = numberPicker.getValue();
                    textViewprice2.setText("Rs. " + temp.format(z * 0.5 * (dprice)));
                    lastPrice = (temp.format(z * 0.5 * (dprice)));
                } else if (y.equals("   Medium")) {
                    z = numberPicker.getValue();
                    textViewprice2.setText("Rs. " + temp.format(z * 0.75 * (dprice)));
                    lastPrice = (temp.format(z * 0.75 * (dprice)));
                } else if (y.equals("   Large")) {
                    z = numberPicker.getValue();
                    textViewprice2.setText("Rs. " + temp.format(z * 1.0 * (dprice)));
                    lastPrice = (temp.format(z * 1.0 * (dprice)));
                }
            }
        });
    }
}