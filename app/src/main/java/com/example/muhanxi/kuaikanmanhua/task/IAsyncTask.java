package com.example.muhanxi.kuaikanmanhua.task;

import android.os.AsyncTask;
import android.text.TextUtils;

import com.example.muhanxi.kuaikanmanhua.utils.StringUtils;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

/**
 * Created by muhanxi on 17/4/24.
 */

public class IAsyncTask extends AsyncTask<String,Void,String> {


    private ResponseListener listener ;
    public  IAsyncTask(ResponseListener listener){

        this.listener= listener ;

    }

    private URL url;

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
    }

    @Override
    protected String doInBackground(String... params) {

        String result = "" ;

        String path = params[0] ;
        InputStream inputStream = null ;
        if(TextUtils.isEmpty(path)){
            return result;
        }


        try {
            Thread.sleep(10000);
            URL url =   new URL(path);

            HttpURLConnection connection = (HttpURLConnection) url.openConnection() ;

            connection.setRequestMethod("GET");
            connection.setConnectTimeout(20000);
            connection.setReadTimeout(20000);
            if(connection.getResponseCode() == 200){
                inputStream =  connection.getInputStream() ;
                result =  StringUtils.inputStreamToString(inputStream);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }finally {
            try {
                if(inputStream != null){
                    inputStream.close();
                    inputStream = null;
                }
            } catch (IOException e) {
                e.printStackTrace();
            }

            System.out.println("result = " + result);
            return  result ;
        }
    }


    @Override
    protected void onPostExecute(String result) {
        super.onPostExecute(result);

        if(listener != null){


            if(TextUtils.isEmpty(result)){
                //回调失败
                listener.onFail();
            }else{
                // 回调成功

                //
                listener.onSuccess(result);
            }
        } else {
            listener.onFail();
        }



    }

    @Override
    protected void onCancelled(String s) {
        super.onCancelled(s);
        if(listener != null){
            listener.onFail();
        }

    }
}
