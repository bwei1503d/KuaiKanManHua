package com.example.muhanxi.kuaikanmanhua.adapter;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.example.muhanxi.kuaikanmanhua.fragment.second.FocusFragment;
import com.example.muhanxi.kuaikanmanhua.fragment.second.HotFragment;

/**
 * Created by muhanxi on 17/4/24.
 */

public class HomeFragmentAdapter extends FragmentPagerAdapter {

    public HomeFragmentAdapter(FragmentManager fragmentManager){
        super(fragmentManager);
    }

    @Override
    public Fragment getItem(int position) {

        if(position == 0){

            // 关注页面
            FocusFragment focusFragment = new FocusFragment();

            Bundle bundle = new Bundle();

            bundle.putInt("pos",0);

            focusFragment.setArguments(bundle);

            return focusFragment;
        } else {
            // 热门 页面
            HotFragment hotFragment = new HotFragment();

            Bundle bundle = new Bundle();

            bundle.putInt("pos",0);

            hotFragment.setArguments(bundle);


            return hotFragment;
        }

    }

    @Override
    public int getCount() {
        return 2;
    }
}
