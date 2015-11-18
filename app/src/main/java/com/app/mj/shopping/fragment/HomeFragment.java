package com.app.mj.shopping.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.app.mj.shopping.Contants;
import com.app.mj.shopping.R;
import com.app.mj.shopping.adapter.DividerItemDecortion;
import com.app.mj.shopping.adapter.HomeCatgoryAdapter;
import com.app.mj.shopping.bean.Banner;
import com.app.mj.shopping.bean.Campaign;
import com.app.mj.shopping.bean.HomeCampaign;
import com.app.mj.shopping.http.BaseCallback;
import com.app.mj.shopping.http.OkHttpHelper;
import com.app.mj.shopping.http.SpotsCallBack;
import com.daimajia.slider.library.SliderLayout;
import com.daimajia.slider.library.SliderTypes.BaseSliderView;
import com.daimajia.slider.library.SliderTypes.TextSliderView;
import com.daimajia.slider.library.Tricks.ViewPagerEx;
import com.google.gson.Gson;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.Response;

import java.util.List;


public class HomeFragment extends Fragment {

    private static  final  String TAG="HomeFragment";

    private SliderLayout mSliderLayout;   //声明开源控件(图片流)

    private RecyclerView mRecyclerView;    //声明RecyclerView

    private HomeCatgoryAdapter mAdatper;    //声明Adatper

    private Gson mGson = new Gson();

    private List<Banner> mBanner; //轮播广告每张图片的实体

    private OkHttpHelper httpHelper = OkHttpHelper.getInstance(); //Okhttp的封装实例

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_home,container,false);

        mSliderLayout = (SliderLayout) view.findViewById(R.id.slider);          //得到view

        requestImages();     //初始化SliderLayout,动态图片展示

        requestRecyclerView(view);  //初始化RecyclerView，页面显示

        return  view;
    }

    //    从网络获取并初始化页面数据，完成RecyclerView的初始化
    private void requestRecyclerView(View view) {
        mRecyclerView = (RecyclerView) view.findViewById(R.id.recyclerview);

        httpHelper.get(Contants.API.CAMPAIGN_HOME,new BaseCallback<List<HomeCampaign>>() {
            @Override
            public void onBeforeRequest(Request request) {
            }

            @Override
            public void onFailure(Request request, Exception e) {
            }

            @Override
            public void onResponse(Response response) {
            }

            @Override
            public void onSuccess(Response response, List<HomeCampaign> homeCampaigns) {
                initRecyclerView(homeCampaigns);  //请求数据中成功后，完成RecyclerView的初始化
            }

            @Override
            public void onError(Response response, int code, Exception e) {
            }
        });
    }

    //使用Okhttp得到数据后，初始化RecyclerView
    private void initRecyclerView(List<HomeCampaign> homeCampaigns) {

        mAdatper = new HomeCatgoryAdapter(homeCampaigns,getActivity());

        Log.e("initRecyclerView","初始化已经完成");
        mAdatper.setOnCampaignClickListener(new HomeCatgoryAdapter.OnCampaignClickListener() {
            @Override
            public void onClick(View view, Campaign campaign) {
                Toast.makeText(getContext(), "title=" + campaign.getTitle(), Toast.LENGTH_LONG).show();
            }
        });

        mRecyclerView.setAdapter(mAdatper);

        mRecyclerView.addItemDecoration(new DividerItemDecortion());

        mRecyclerView.setLayoutManager(new LinearLayoutManager(this.getActivity()));
    }

//添加一行

//    //初始化RecyclerView,这个是写死的，读取本地
//    private void initRecyclerView(View view) {
////        1、数据
////        2、Adapter
////        3、LayoutManager
//
//        mRecyclerView = (RecyclerView) view.findViewById(R.id.recyclerview);
//
//        List<HomeCategory> datas = new ArrayList<>(5); //放数据
//
//        HomeCategory category = new HomeCategory("热门活动",R.drawable.img_big_1,R.drawable.img_1_small1,R.drawable.img_1_small2);
//        datas.add(category);
//        category = new HomeCategory("有利可图",R.drawable.img_big_4,R.drawable.img_4_small1,R.drawable.img_4_small2);
//        datas.add(category);
//        category = new HomeCategory("品牌街",R.drawable.img_big_2,R.drawable.img_2_small1,R.drawable.img_2_small2);
//        datas.add(category);
//        category = new HomeCategory("金融街 包赚翻",R.drawable.img_big_1,R.drawable.img_3_small1,R.drawable.imag_3_small2);
//        datas.add(category);
//        category = new HomeCategory("超值购",R.drawable.img_big_0,R.drawable.img_0_small1,R.drawable.img_0_small2);
//        datas.add(category);
//
//        mAdatper = new HomeCatgoryAdapter(datas);
//
//        mRecyclerView.setAdapter(mAdatper);  //适配器
//
//        mRecyclerView.addItemDecoration(new DividerItemDecortion());  //添加横线
//
//        mRecyclerView.setLayoutManager(new LinearLayoutManager(this.getActivity())); //LayoutManager
//    }


//    //初始化SliderLayout
//    private void initSlider() {
//        TextSliderView textSliderView = new TextSliderView(this.getActivity());
//        textSliderView.image("http://d05.res.meilishuo.net/pic/_o/1e/e9/14e8265a5e314d85b369549cbfec_1597_724.jpg");
//        textSliderView.description("新品推荐");
//        //设置监听事件（简单点击时，弹出一个Toast）
//        textSliderView.setOnSliderClickListener(new BaseSliderView.OnSliderClickListener() {
//            @Override
//            public void onSliderClick(BaseSliderView baseSliderView) {
//                Toast.makeText(HomeFragment.this.getActivity(), "新品推荐", Toast.LENGTH_LONG).show();
//            }
//        });
//
//        TextSliderView textSliderView2 = new TextSliderView(this.getActivity());
//        textSliderView2.image("http://bs.baidu.com/lego-mat/ai_c8a81663-511a-40fe-accf-ed02ed298811.jpg");
//        textSliderView2.description("时尚男装");
//        textSliderView2.setOnSliderClickListener(new BaseSliderView.OnSliderClickListener() {
//            @Override
//            public void onSliderClick(BaseSliderView baseSliderView) {
//                Toast.makeText(HomeFragment.this.getActivity(),"时尚男装",Toast.LENGTH_LONG).show();
//            }
//        });
//
//
//        TextSliderView textSliderView3 = new TextSliderView(this.getActivity());
//        textSliderView3.image("http://img.ea3w.com/221/220042.jpg");
//        textSliderView3.description("家电秒杀");
//        textSliderView3.setOnSliderClickListener(new BaseSliderView.OnSliderClickListener() {
//            @Override
//            public void onSliderClick(BaseSliderView baseSliderView) {
//                Toast.makeText(HomeFragment.this.getActivity(),"家电秒杀",Toast.LENGTH_LONG).show();
//            }
//        });
//

//        mSliderLayout.addSlider(textSliderView);
//        mSliderLayout.addSlider(textSliderView2);
//        mSliderLayout.addSlider(textSliderView3);
//
//    }



//    从网络获取并初始化轮播广告，完成SliderLayout的初始化
    private  void requestImages(){

        String url ="http://112.124.22.238:8081/course_api/banner/query?type=1";

        httpHelper.get(url, new SpotsCallBack<List<Banner>>(getContext()){

            @Override
//           从网络获取数据，并直接转化为List对象
            public void onSuccess(Response response, List<Banner> banners) {
                mBanner = banners;
                initSlider(); //请求数据中成功后，完成动态图片的初始化
            }

            @Override
            public void onError(Response response, int code, Exception e) {
            }
        });
    }

    //使用Okhttp得到数据后，初始化SliderLayout
    private void initSlider() {

        if(mBanner !=null){
            for (Banner banner : mBanner){
                TextSliderView textSliderView = new TextSliderView(this.getActivity());
                textSliderView.image(banner.getImgUrl());
                textSliderView.description(banner.getName());
                textSliderView.setScaleType(BaseSliderView.ScaleType.Fit);
                mSliderLayout.addSlider(textSliderView);    //数据都添加到轮播广告控件
            }
        }



        //添加监听事件
        mSliderLayout.addOnPageChangeListener(new ViewPagerEx.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int i, float v, int i2) {
//                Log.e("onPageScrolled","页面滑动");
            }

            @Override
            public void onPageSelected(int i) {
//                Toast.makeText(getContext(), "onPageSelected"+"第几张图片"+i, Toast.LENGTH_LONG).show();
                Log.e("onPageSelected","选中"+i);
            }

            @Override
            public void onPageScrollStateChanged(int i) {
//                Log.e("onPageScrollStateChanged","状态改变");
            }
        });
    }


    @Override
    public void onStop() {

        super.onStop();
        Log.e(TAG,"onStop这个阶段");
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        mSliderLayout.stopAutoCycle();
        Log.e(TAG,"onDestroyView这个阶段");
    }

//    @Override
//    public void onDestroy() {
//        super.onDestroy();
//        Log.e(TAG,"onDestroy这个阶段");
//    }

    public void onPause(){
        super.onPause();
        Log.e(TAG,"onPause这个阶段");
    }
}
