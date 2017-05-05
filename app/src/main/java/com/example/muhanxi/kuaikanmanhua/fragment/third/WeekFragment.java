package com.example.muhanxi.kuaikanmanhua.fragment.third;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.SwipeRefreshLayout;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.ListView;
import android.widget.TextView;

import com.example.muhanxi.kuaikanmanhua.R;
import com.example.muhanxi.kuaikanmanhua.adapter.WeekFragmentAdapter;
import com.example.muhanxi.kuaikanmanhua.bean.WeekBean;
import com.example.muhanxi.kuaikanmanhua.event.ScrollEvent;
import com.example.muhanxi.kuaikanmanhua.task.IAsyncTask;
import com.example.muhanxi.kuaikanmanhua.task.ResponseListener;
import com.google.gson.Gson;

import org.greenrobot.eventbus.EventBus;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;

/**
 * Created by muhanxi on 17/4/24.
 */

public class WeekFragment extends Fragment implements SwipeRefreshLayout.OnRefreshListener , ResponseListener{

    private TextView textView;

    String title ;
    private ListView listView;
    private SwipeRefreshLayout swipeRefreshLayout;
    private List<WeekBean.DataBean.ComicsBean> list = new ArrayList<WeekBean.DataBean.ComicsBean>();

    private WeekFragmentAdapter adapter ;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {


        View view = inflater.inflate(R.layout.weekfragment,container,false);


        if(getArguments() != null) {
            title = getArguments().getString("title");
        }

        init(view);

        return view;
    }

    int index = 0 ;

    private void init(View view) {


        listView = (ListView) view.findViewById(R.id.week_listview);

        swipeRefreshLayout = (SwipeRefreshLayout) view.findViewById(R.id.week_swiperefershlayout);

        swipeRefreshLayout.setOnRefreshListener(this);



        adapter = new WeekFragmentAdapter(this.getActivity(),list);
        listView.setAdapter(adapter);

        getData();


        listView.setOnScrollListener(new AbsListView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(AbsListView view, int scrollState) {

                if(scrollState == AbsListView.OnScrollListener.SCROLL_STATE_TOUCH_SCROLL){

                    index =  listView.getLastVisiblePosition() ;

                }

                if(scrollState == AbsListView.OnScrollListener.SCROLL_STATE_IDLE){


                    if(listView.getLastVisiblePosition() - index > 0 ){
                        // 上滑


                        EventBus.getDefault().post(new ScrollEvent(true));

                    } else {
                        EventBus.getDefault().post(new ScrollEvent(false));

                    }

                }

            }

            @Override
            public void onScroll(AbsListView view, int firstVisibleItem, int visibleItemCount, int totalItemCount) {

            }
        });




    }


    // 下啦
    @Override
    public void onRefresh() {



        getData();

    }


    // 发送网络请求
    private void getData(){
        String path = "http://api.kuaikanmanhua.com/v1/daily/comic_lists/1489334400?since=0&gender=0&sa_event=eyJwcm9qZWN0Ijoia3VhaWthbl9hcHAiLCJ0aW1lIjoxNDg5NjQzNTYxODM4LCJwcm9wZXJ0aWVzIjp7IkhvbWVwYWdlVGFiTmFtZSI6IueDremXqCIsIlZDb21tdW5pdHlUYWJOYW1lIjoi54Ot6ZeoIiwiJG9zX3ZlcnNpb24iOiI0LjQuMiIsIkdlbmRlclR5cGUiOiLlpbPniYgiLCJGcm9tSG9tZXBhZ2VUYWJOYW1lIjoi54Ot6ZeoIiwiJGxpYl92ZXJzaW9uIjoiMS42LjM0IiwiJG5ldHdvcmtfdHlwZSI6IldJRkkiLCIkd2lmaSI6dHJ1ZSwiJG1hbnVmYWN0dXJlciI6IkxFTk9WTyIsIkZyb21Ib21lcGFnZVVwZGF0ZURhdGUiOjMsIiRzY3JlZW5faGVpZ2h0IjoxNDQwLCJIb21lcGFnZVVwZGF0ZURhdGUiOjMsIlByb3BlcnR5RXZlbnQiOiJSZWFkSG9tZVBhZ2UiLCJGaW5kVGFiTmFtZSI6IuaOqOiNkCIsImFidGVzdF9ncm91cCI6MTAsIiRzY3JlZW5fd2lkdGgiOjkwMCwiJG9zIjoiQW5kcm9pZCIsIlRyaWdnZXJQYWdlIjoiSG9tZVBhZ2UiLCIkY2FycmllciI6IkNISU5BIE1PQklMRSIsIiRtb2RlbCI6Ikxlbm92byBQNzAtdCIsIiRhcHBfdmVyc2lvbiI6IjMuOS4yIn0sInR5cGUiOiJ0cmFjayIsImRpc3RpbmN0X2lkIjoiQTo1MDdCOUQyN0Q2NUEwMDAwIiwib3JpZ2luYWxfaWQiOiJBOjUwN0I5RDI3RDY1QTAwMDAiLCJldmVudCI6IlJlYWRIb21lUGFnZSJ9" ;

        new IAsyncTask(this).execute(path);

       
    }





    // 网络请求回调
    @Override
    public void onSuccess(String string) {


        swipeRefreshLayout.setRefreshing(false);

        Gson gson = new Gson();
        WeekBean bean =  gson.fromJson(string, WeekBean.class);


        list.addAll(bean.getData().getComics());

        adapter.notifyDataSetChanged();



    }

    @Override
    public void onFail() {
        swipeRefreshLayout.setRefreshing(false);

    }
}
