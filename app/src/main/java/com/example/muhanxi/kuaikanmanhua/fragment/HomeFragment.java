package com.example.muhanxi.kuaikanmanhua.fragment;

import android.animation.Animator;
import android.animation.ObjectAnimator;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RadioGroup;
import android.widget.RelativeLayout;

import com.example.muhanxi.kuaikanmanhua.R;
import com.example.muhanxi.kuaikanmanhua.adapter.HomeFragmentAdapter;
import com.example.muhanxi.kuaikanmanhua.base.BaseActivity;
import com.example.muhanxi.kuaikanmanhua.event.ScrollEvent;
import com.example.muhanxi.kuaikanmanhua.task.IAsyncTask;
import com.example.muhanxi.kuaikanmanhua.task.ResponseListener;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

/**
 * Created by muhanxi on 17/4/24.
 */

public class HomeFragment extends Fragment implements ResponseListener{


    private ViewPager viewPager;
    private RadioGroup radioGroup;
    private RelativeLayout relativeLayoutTitle;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view =  inflater.inflate(R.layout.home_fragment,container,false);



        initView(view);




        return view ;
    }

    private void initView(View view) {

        relativeLayoutTitle = (RelativeLayout) view.findViewById(R.id.pub_title_id);

        viewPager = (ViewPager) view.findViewById(R.id.home_fragment_viewpager);


        System.out.println("view = parent " + getActivity().getSupportFragmentManager());
        HomeFragmentAdapter adapter = new HomeFragmentAdapter(getActivity().getSupportFragmentManager());

        viewPager.setAdapter(adapter);


        radioGroup = (RadioGroup) view.findViewById(R.id.pub_radiogroup);
        // 设置 显示 热门 fragment
        viewPager.setCurrentItem(1);

        //这是 热门 这个radiobutton 选中状态
        radioGroup.check(R.id.pub_title_right);


        radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {

                switch (checkedId) {
                    case R.id.pub_title_left:
                        viewPager.setCurrentItem(0);

                        break;
                    case R.id.pub_title_right:
                        viewPager.setCurrentItem(1);

                        break;

                }

            }
        });

        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {

                if(position == 0){
                    radioGroup.check(R.id.pub_title_left);
                } else {
                    radioGroup.check(R.id.pub_title_right);
                }

            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });


        EventBus.getDefault().register(this);



    }


    // onEvent

    //改方法在主线程执行
    @Subscribe(threadMode = ThreadMode.MAIN , sticky = false)
    public void onEvent(ScrollEvent event){
        if(event.isUp()){


            if(relativeLayoutTitle.getVisibility() == View.GONE){
                return;
            }

            ObjectAnimator anim = ObjectAnimator.ofFloat(relativeLayoutTitle, "y", relativeLayoutTitle.getY(),
                    relativeLayoutTitle.getY() - relativeLayoutTitle.getHeight());
            anim.setDuration(500);
            anim.addListener(new Animator.AnimatorListener() {
                @Override
                public void onAnimationStart(Animator animation) {

                }

                @Override
                public void onAnimationEnd(Animator animation) {
                    relativeLayoutTitle.setVisibility(View.GONE);

                }

                @Override
                public void onAnimationCancel(Animator animation) {

                }

                @Override
                public void onAnimationRepeat(Animator animation) {

                }
            });
            anim.start();
        } else {

            if(relativeLayoutTitle.getVisibility() == View.VISIBLE){
                return;
            }

            relativeLayoutTitle.setVisibility(View.VISIBLE);

            ObjectAnimator anim = ObjectAnimator.ofFloat(relativeLayoutTitle, "y", relativeLayoutTitle.getY(),
                    relativeLayoutTitle.getY() + relativeLayoutTitle.getHeight());
            anim.setDuration(500);
            anim.addListener(new Animator.AnimatorListener() {
                @Override
                public void onAnimationStart(Animator animation) {

                }

                @Override
                public void onAnimationEnd(Animator animation) {
                    relativeLayoutTitle.setVisibility(View.VISIBLE);

                }

                @Override
                public void onAnimationCancel(Animator animation) {

                }

                @Override
                public void onAnimationRepeat(Animator animation) {

                }
            });
            anim.start();

        }






    }





    @Override
    public void onSuccess(String string) {





    }

    @Override
    public void onFail() {

    }


    @Override
    public void onStop() {
        super.onStop();

        EventBus.getDefault().unregister(this);
    }
}
