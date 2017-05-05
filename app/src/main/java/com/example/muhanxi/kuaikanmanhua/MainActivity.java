package com.example.muhanxi.kuaikanmanhua;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import com.example.muhanxi.kuaikanmanhua.base.BaseActivity;
import com.example.muhanxi.kuaikanmanhua.fragment.FeedFragment;
import com.example.muhanxi.kuaikanmanhua.fragment.FindFragment;
import com.example.muhanxi.kuaikanmanhua.fragment.HomeFragment;
import com.example.muhanxi.kuaikanmanhua.fragment.MeFragment;
import com.example.muhanxi.kuaikanmanhua.listener.ViewListener;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends BaseActivity implements ViewListener{

    private RadioButton radioButtonHome;
    private RadioGroup radioGroup;
    private RadioButton radioButtonFind;
    private RadioButton radioButtonFeed;
    private RadioButton radioButtonMe;


    private List<Fragment> list = new ArrayList<Fragment>();


    private int selectIndex ;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        init();

//        if(savedInstanceState != null){
//
//           HomeFragment homeFragment = (HomeFragment) getSupportFragmentManager().findFragmentByTag("HomeFragment");
//            FindFragment findFragment = (FindFragment) getSupportFragmentManager().findFragmentByTag("FindFragment");
//            FeedFragment feedFragment = (FeedFragment) getSupportFragmentManager().findFragmentByTag("FeedFragment");
//            MeFragment meFragment = (MeFragment) getSupportFragmentManager().findFragmentByTag("MeFragment");
//            System.out.println("outState  onCreate  =  临时保存数据 "+homeFragment);
//            System.out.println("outState  onCreate  =  临时保存数据 "+findFragment);
//            System.out.println("outState  onCreate  =  临时保存数据 "+feedFragment);
//            System.out.println("outState  onCreate  =  临时保存数据 "+meFragment);
//
//
//
//
//        }





        createFragmet(savedInstanceState);

        showFragment(0);





    }


    // 初始化ui
    @Override
    public void init() {

        radioGroup = (RadioGroup) findViewById(R.id.tab_radiogroup);
        radioButtonHome = (RadioButton) findViewById(R.id.tab_radiobutton_home);
        radioButtonFind = (RadioButton) findViewById(R.id.tab_radiobutton_find);
        radioButtonFeed = (RadioButton) findViewById(R.id.tab_radiobutton_feed);
        radioButtonMe = (RadioButton) findViewById(R.id.tab_radiobutton_me);


        radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {

                switch (checkedId) {

                    case R.id.tab_radiobutton_home:
                        selectIndex = 0 ;
                        break;
                    case R.id.tab_radiobutton_find:
                        selectIndex = 1 ;

                        break;
                    case R.id.tab_radiobutton_feed:
                        selectIndex = 2 ;

                        break;
                    case R.id.tab_radiobutton_me:
                        selectIndex = 3 ;

                        break;
                }

                showFragment(selectIndex);

            }
        });


    }



    // 创建fragment
    private void createFragmet(Bundle savedInstanceState){
        HomeFragment homeFragment = null;
        FindFragment findFragment = null;

        FeedFragment feedFragment = null;

        MeFragment meFragment = null;

        if(savedInstanceState == null){
             homeFragment = new HomeFragment();
             findFragment = new FindFragment();
             feedFragment = new FeedFragment();
             meFragment = new MeFragment();
        } else {
            homeFragment = (HomeFragment)getSupportFragmentManager().findFragmentByTag("HomeFragment");
            findFragment = (FindFragment)getSupportFragmentManager().findFragmentByTag("FindFragment");
            feedFragment = (FeedFragment)getSupportFragmentManager().findFragmentByTag("FeedFragment");
            meFragment = (MeFragment)getSupportFragmentManager().findFragmentByTag("MeFragment");

            if(homeFragment == null){
                homeFragment = new HomeFragment();
            }
            if(findFragment == null){
                findFragment = new FindFragment();
            }
            if(feedFragment == null){
                feedFragment = new FeedFragment();
            }
            if(meFragment == null){
                meFragment = new MeFragment();
            }
        }

        list.add(homeFragment);
        list.add(findFragment);
        list.add(feedFragment);
        list.add(meFragment);
    }


    // 显示 fragment
    private void showFragment(int pos) {

       FragmentManager fragmentManager =  getSupportFragmentManager() ;

        FragmentTransaction transaction =  fragmentManager.beginTransaction() ;

        if(!list.get(pos).isAdded()){
//            transaction.add(R.id.container,list.get(pos));

            transaction.add(R.id.container,list.get(pos),list.get(pos).getClass().getSimpleName());
        }

        for(int i=0;i<list.size();i++){
            if(pos == i){
                transaction.show(list.get(i));
            } else {
                transaction.hide(list.get(i));
            }
        }
        transaction.commit();


    }


    // 内存不足 临时保存数据
    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putInt("selectIndex",selectIndex);
        System.out.println("outState =  临时保存数据 " + outState);

    }


    // 内存不足 取保存的数据
    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);

        System.out.println("outState onRestoreInstanceState =  临时保存数据 ");

        if(savedInstanceState != null){
            System.out.println("outState onRestoreInstanceState =  临时保存数据 "+savedInstanceState.getInt("selectIndex"));

        }

    }
}
