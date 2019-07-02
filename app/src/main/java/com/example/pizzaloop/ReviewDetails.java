package com.example.pizzaloop;

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

import com.squareup.picasso.Picasso;

import java.text.DecimalFormat;

import static com.example.pizzaloop.ReviewMain.EXTRA_URL;
import static com.example.pizzaloop.ReviewMain.EXTRA_details;
import static com.example.pizzaloop.ReviewMain.EXTRA_name;
import static com.example.pizzaloop.ReviewMain.EXTRA_pizzaId;
import static com.example.pizzaloop.ReviewMain.EXTRA_price;

public class ReviewDetails extends AppCompatActivity {
    public NumberPicker numberPicker;
    ImageButton imageButton;
    RadioGroup rg;
    RadioButton rb;
    Button cancel;


    public static final String EXTRA_QTY = "QTY";
    public static final String EXTRA_LPRICE = "LastPrice";



    public static String Pizzaname;
    public  static  String PizId;
    //


    //------------------------------------------
    public double dprice;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_detail);
        //----
        // myDialog=new Dialog(this);
        //---

        // UserAdress=userAddress.getText().toString();

        rg = findViewById(R.id.Psize);
        cancel = findViewById(R.id.dcancel);

        numberPicker = findViewById(R.id.pqty);
        imageButton = findViewById(R.id.pback);
        imageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ReviewDetails.this, MainActivity.class);
                startActivity(intent);
            }
        });
        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ReviewDetails.this, MainActivity.class);
                startActivity(intent);
            }
        });


        numberPicker.setMinValue(1);
        numberPicker.setMaxValue(5);
        Intent intent = getIntent();
        String imageUrl = intent.getStringExtra(EXTRA_URL);
        PizId=intent.getStringExtra(EXTRA_pizzaId);
        dprice = intent.getDoubleExtra(EXTRA_price, 0.00);
        Pizzaname = intent.getStringExtra(EXTRA_name);
        String details = intent.getStringExtra(EXTRA_details);

        ImageView imageView = findViewById(R.id.pimage);


        TextView textViewname = findViewById(R.id.pname);


        Picasso.get().load(imageUrl).fit().centerInside().into(imageView);
        textViewname.setText(Pizzaname);


    }

    public void dorder(View v) {

        Intent Dintent = new Intent(ReviewDetails.this, ReviewPayment.class);
        startActivity(Dintent);
        ReviewDetails detailActivity = new ReviewDetails();
        Dintent.putExtra(EXTRA_QTY, z);
        Dintent.putExtra(EXTRA_LPRICE, lastPrice);


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