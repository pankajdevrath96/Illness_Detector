package com.example.macbook.illnessdectector;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;

public class SplashScreen extends AppCompatActivity {

  //  private static final int SPLASH_TIMEOUT = 2000;// milliseconds ;

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);


    }



    private boolean handler=new Handler().postDelayed(new Runnable() {
        @Override
        public void run() {
            Intent intent= new Intent(SplashScreen.this, MainActivity.class);
            startActivity(intent);
            finish();
        }
    },2000);


}




