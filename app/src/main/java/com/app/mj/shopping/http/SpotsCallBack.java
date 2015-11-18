package com.app.mj.shopping.http;

import android.content.Context;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.Response;
import dmax.dialog.SpotsDialog;

//这是一个控件，参见：https://github.com/d-max/spots-dialog#readme

public abstract class SpotsCallBack<T> extends BaseCallback<T> {

    private  Context mContext;

    private  SpotsDialog mDialog;

//单例模式
    private  void initSpotsDialog(){
        mDialog = new SpotsDialog(mContext,"拼命加载中...");
    }

    public SpotsCallBack(Context context){
        mContext = context;
        initSpotsDialog();
    }



    public  void showDialog(){
        mDialog.show();
    }

    public  void dismissDialog(){
        mDialog.dismiss();
    }


    public void setLoadMessage(int resId){
        mDialog.setMessage(mContext.getString(resId));
    }


    @Override
    public void onFailure(Request request, Exception e) {
        dismissDialog();
    }

    //加载数据前显示
    @Override
    public void onBeforeRequest(Request request) {
        showDialog();
    }

    //加载数据成功后关闭
    @Override
    public void onResponse(Response response) {
        dismissDialog();
    }
}
