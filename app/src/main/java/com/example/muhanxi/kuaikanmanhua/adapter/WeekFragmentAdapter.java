package com.example.muhanxi.kuaikanmanhua.adapter;

import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.graphics.drawable.GradientDrawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.muhanxi.kuaikanmanhua.IApplication;
import com.example.muhanxi.kuaikanmanhua.R;
import com.example.muhanxi.kuaikanmanhua.bean.WeekBean;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.assist.FailReason;
import com.nostra13.universalimageloader.core.listener.ImageLoadingListener;
import com.nostra13.universalimageloader.core.listener.ImageLoadingProgressListener;

import java.util.List;

/**
 * Created by muhanxi on 17/4/25.
 */

public class WeekFragmentAdapter extends BaseAdapter {


    private List<WeekBean.DataBean.ComicsBean> list ;
    private Context context;

    private LayoutInflater inflater ;

    public WeekFragmentAdapter(Context context, List<WeekBean.DataBean.ComicsBean> list){

        this.context =  context;
        this.list = list;
        inflater = LayoutInflater.from(context);
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Object getItem(int position) {
        return list.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View view, ViewGroup parent) {


        ViewHolder viewHolder = null ;

        if( view == null ){
            

             view = inflater.inflate(R.layout.week_item,null);


            viewHolder = new ViewHolder();

            viewHolder.week_item_type = (TextView) view.findViewById(R.id.week_item_type);
            viewHolder.week_item_title = (TextView) view.findViewById(R.id.week_item_title);

            viewHolder.week_item_imageview = (ImageView) view.findViewById(R.id.week_item_imageview);


            viewHolder.week_item_sub_title = (TextView) view.findViewById(R.id.week_item_sub_title);

            viewHolder.week_item_image_zan = (ImageView) view.findViewById(R.id.week_item_image_zan);
            viewHolder.week_item_zannum = (TextView) view.findViewById(R.id.week_item_zannum);

            viewHolder.week_item_image_comment = (ImageView) view.findViewById(R.id.week_item_image_comment);
            viewHolder.week_item_comment = (TextView) view.findViewById(R.id.week_item_comment);

            view.setTag(viewHolder);

        } else {

            viewHolder = (ViewHolder) view.getTag() ;

        }




//        String labelColor =  list.get(position).getLabel_color() ;

        // 设置 文字 背景色 ， 动态改变， 和服务器返回的颜色一致
        GradientDrawable myGrad = (GradientDrawable) viewHolder.week_item_type.getBackground();
        myGrad.setColor(Color.parseColor(list.get(position).getLabel_color()));

        viewHolder.week_item_type.setText(list.get(position).getLabel_text());
        viewHolder.week_item_title.setText(list.get(position).getTopic().getTitle());


//        ImageLoader.getInstance().displayImage(list.get(position).getCover_image_url(),viewHolder.week_item_imageview, IApplication.getOption());


        ImageLoader.getInstance().displayImage(list.get(position).getCover_image_url(), viewHolder.week_item_imageview, IApplication.getOption(), new ImageLoadingListener() {
            @Override
            public void onLoadingStarted(String s, View view) {


                //onLoadingStarted
            }

            @Override
            public void onLoadingFailed(String s, View view, FailReason failReason) {

            }

            @Override
            public void onLoadingComplete(String s, View view, Bitmap bitmap) {

            }

            @Override
            public void onLoadingCancelled(String s, View view) {

            }
        }, new ImageLoadingProgressListener() {
            @Override
            public void onProgressUpdate(String s, View view, int i, int i1) {

            }
        });

        //
        viewHolder.week_item_sub_title.setText(list.get(position).getTitle());


        viewHolder.week_item_zannum.setText(list.get(position).getLikes_count()+"");

        viewHolder.week_item_comment.setText(list.get(position).getComments_count()+"");



        viewHolder.week_item_image_zan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                // 点赞动画

                AnimatorSet animatorSet = new AnimatorSet();
                animatorSet.playTogether(
                        ObjectAnimator.ofFloat(v, "scaleY", 1, 1.4f, 1),
                        ObjectAnimator.ofFloat(v, "scaleX", 1, 1.4f, 1));
                animatorSet.setDuration(400);
                animatorSet.start();
            }
        });


        return view;
    }





    static class ViewHolder {

        TextView week_item_type;
        TextView week_item_title;


        //
        ImageView week_item_imageview;

        //
        TextView week_item_sub_title;

        ImageView week_item_image_zan;
        TextView week_item_zannum;

        ImageView week_item_image_comment;
        TextView week_item_comment;







    }



}
