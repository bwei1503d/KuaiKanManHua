package com.example.muhanxi.kuaikanmanhua.task;

/**
 * Created by muhanxi on 17/4/24.
 */


// 网络请求回调
public interface ResponseListener {


    //成功回调
    public void onSuccess(String string);

    // 失败回调
    public void onFail();

}
