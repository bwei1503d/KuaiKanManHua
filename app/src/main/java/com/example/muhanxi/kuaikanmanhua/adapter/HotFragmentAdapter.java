package com.example.muhanxi.kuaikanmanhua.adapter;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.PagerAdapter;

import com.example.muhanxi.kuaikanmanhua.fragment.third.WeekFragment;

/**
 * Created by muhanxi on 17/4/24.
 */

public class HotFragmentAdapter extends FragmentPagerAdapter {


    String [] TITLE = {"周一","周二","周三","周四","周五","周六","周日"} ;


    public HotFragmentAdapter(FragmentManager fragmentManager){
        super(fragmentManager);
    }




    @Override
    public Fragment getItem(int position) {

        WeekFragment weekFragment = new WeekFragment();
        Bundle bundle = new Bundle();
        bundle.putString("title",TITLE[position]);
        weekFragment.setArguments(bundle);

        return weekFragment;
    }

    @Override
    public int getCount() {
        return TITLE.length;
    }


    @Override
    public CharSequence getPageTitle(int position) {
       return TITLE[position];
    }
}
