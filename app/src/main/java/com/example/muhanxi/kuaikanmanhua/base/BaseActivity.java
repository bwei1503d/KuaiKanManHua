package com.example.muhanxi.kuaikanmanhua.base;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;

import com.example.muhanxi.kuaikanmanhua.R;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public class BaseActivity extends FragmentActivity {

    private static final int KEEP_ALIVE_SECONDS = 30;
    private static final BlockingQueue<Runnable> sPoolWorkQueue =
            new LinkedBlockingQueue<Runnable>(128);
    public static ThreadPoolExecutor threadPoolExecutor = new ThreadPoolExecutor(
            3, 10, KEEP_ALIVE_SECONDS, TimeUnit.SECONDS,
            sPoolWorkQueue);


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_base);

    }
}
