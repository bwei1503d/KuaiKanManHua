package com.example.muhanxi.kuaikanmanhua;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;

public class SplashActivity extends Activity {


    Handler handler = new Handler() {

        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);

            switch (msg.what) {

                case 1:

                    Intent intent = new Intent(SplashActivity.this,MainActivity.class);
                    startActivity(intent);

                    break;

            }
        }
    } ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);



        handler.sendEmptyMessageDelayed(1,2000);


    }
}
