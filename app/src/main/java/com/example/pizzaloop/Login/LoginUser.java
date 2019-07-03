package com.example.pizzaloop.Login;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.pizzaloop.R;
import com.example.pizzaloop.WelcomePage;


public class LoginUser extends AppCompatActivity {
  EditText Name ;
    EditText Password ;
    Button Login ;
    String name;
    String password;



    private static String UserId;
    DatabaseHelper helper = new DatabaseHelper(this);
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_user);



}

    public void onLOgInClick(View v) {
        if (v.getId() == R.id.BLogin) {
            EditText a = (EditText) findViewById(R.id.TFusername);
            String str = a.getText().toString();
            EditText b = (EditText) findViewById(R.id.TFpassword);
            String pass = b.getText().toString();
            setUserId(a.getText().toString());
            String password = helper.searchPass(str);

            if (pass.equals(password) && checkNetworkConnectionStatus()) {

                Intent i = new Intent(LoginUser.this, WelcomePage.class);
                i.putExtra("Username", str);
                startActivity(i);
                finish();

            }else if(checkNetworkConnectionStatus()==false){
                Toast temp= Toast.makeText(LoginUser.this,"CHECK YOUR CONNECTION",Toast.LENGTH_SHORT);
                temp.show();
            }
            else {
                Toast temp = Toast.makeText(LoginUser.this,"Wrong !!" , Toast.LENGTH_SHORT);
                temp.show();
            }


        }
    }

    private boolean checkNetworkConnectionStatus() {
        Boolean status = null;
        boolean wifiConnected;
        boolean mobileConnected;
        ConnectivityManager connMgr = (ConnectivityManager)getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeInfo = connMgr.getActiveNetworkInfo();
        if(activeInfo != null && activeInfo.isConnected()){
            wifiConnected = activeInfo.getType() == ConnectivityManager.TYPE_WIFI;
            mobileConnected=activeInfo.getType() == ConnectivityManager.TYPE_MOBILE;
            if (wifiConnected){
                Toast.makeText(this, "WIFI_CONNECTED", Toast.LENGTH_SHORT).show();
                status= true;
            }
            else if(mobileConnected){
                Toast.makeText(this, "Mobile Data Has No Connection with the Host", Toast.LENGTH_SHORT).show();
                status= false;
            }
        }
        else {
            Toast.makeText(this, "NO INTERNET", Toast.LENGTH_SHORT).show();
            status= false;
        }
        return status;
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

    public void NoAcc(View v) {
        Intent intent1 = new Intent(LoginUser.this, SignUp.class);
        startActivity(intent1);
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
    public static String getUserId() {
        return UserId;
    }

    public void setUserId(String userId) {
        UserId = userId;
    }
}