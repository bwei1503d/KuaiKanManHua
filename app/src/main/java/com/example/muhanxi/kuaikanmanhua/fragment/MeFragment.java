package com.example.muhanxi.kuaikanmanhua.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.muhanxi.kuaikanmanhua.R;
import com.example.muhanxi.kuaikanmanhua.event.ScrollEvent;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

/**
 * Created by muhanxi on 17/4/24.
 */

public class MeFragment extends Fragment {


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view =  inflater.inflate(R.layout.me_fragment,container,false);


        EventBus.getDefault().register(this);

        return view ;
    }

    //改方法在主线程执行
    @Subscribe(threadMode = ThreadMode.MAIN , sticky = false)
    public void onEvent(ScrollEvent event){
        System.out.println("event = me " + event.isUp());



    }

}
