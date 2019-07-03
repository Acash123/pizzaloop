package com.example.pizzaloop.Login;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.example.pizzaloop.R;

import java.util.Timer;
import java.util.TimerTask;

public class SignUp extends AppCompatActivity {
    DatabaseHelper helper = new DatabaseHelper(this);
   
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);
    }

    public  void onSignUpClick(View v){
        if(v.getId()==R.id.Bsignupbutton)
        {
           
            EditText phoneNo = (EditText)findViewById(R.id.TFmnum);
            EditText uname = (EditText)findViewById(R.id.TFuname);
            EditText pass1 = (EditText)findViewById(R.id.TFpass1);
            EditText pass2 = (EditText)findViewById(R.id.TFpass2);

            
            String pnostr= phoneNo.getText().toString();
            String unamestr= uname.getText().toString();
            String pass1str= pass1.getText().toString();
            String pass2str= pass2.getText().toString();

            if(!pass1str.equals(pass2str)){
                Toast pass = Toast.makeText(SignUp.this , "Password Dont Match " , Toast.LENGTH_SHORT);
                pass.show();;
            }
            else
            {
                //Insert to DB
                Contact c = new Contact();
                c.setPhoneNum(pnostr);
                c.setUname(unamestr);
                c.setPass(pass1str);
                helper.insertContact(c);
                Toast success = Toast.makeText(SignUp.this,"REGISTRATION SUCCESS !",Toast.LENGTH_SHORT);
                success.show();
                Timer timer = new Timer();
                timer.schedule(new TimerTask() {
                    @Override
                    public void run() {
                        Intent intent = new Intent(SignUp.this,LoginUser.class);
                        startActivity(intent);
                        finish();
                    }
                },1500);
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
    public void HaveAcc(View view){
        Intent intent=new Intent(this,LoginUser.class) ;
        startActivity(intent);
        finish();
    }
}
