package com.app.mj.shopping.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.app.mj.shopping.Contants;
import com.app.mj.shopping.R;
import com.app.mj.shopping.adapter.HWAdatper;
import com.app.mj.shopping.bean.Page;
import com.app.mj.shopping.bean.Wares;
import com.app.mj.shopping.http.OkHttpHelper;
import com.app.mj.shopping.http.SpotsCallBack;
import com.cjj.MaterialRefreshLayout;
import com.cjj.MaterialRefreshListener;
import com.squareup.okhttp.Response;

import java.util.List;



public class HotFragment extends Fragment{

    private RecyclerView mRecyclerView;

    private MaterialRefreshLayout mRefreshLaout;

    private OkHttpHelper httpHelper = OkHttpHelper.getInstance();

    private List<Wares> datas;

//    private HotWaresAdapter mAdatper;
    private HWAdatper mAdatper;   //使用封装后的Adapter


    private int currPage=1;
    private int totalPage=1;
    private int pageSize=10;

    //设置不同加载方式的标记位
    private  static final int STATE_NORMAL=0;
    private  static final int STATE_REFREH=1;
    private  static final int STATE_MORE=2;
    //默认正常加载
    private int state=STATE_NORMAL;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View view= inflater.inflate(R.layout.fragment_hot,container,false);

        mRecyclerView= (RecyclerView) view.findViewById(R.id.recyclerview);
        mRefreshLaout= (MaterialRefreshLayout) view.findViewById(R.id.refresh_view);

        initRefreshLayout(); //定义下拉刷新，上拉加载两种实现方法

        getData(); //getData()方法内，实现showData()

        return view ;

    }


    //控件的初始化
    private void initRefreshLayout() {

        mRefreshLaout.setLoadMore(true);

        mRefreshLaout.setMaterialRefreshListener(new MaterialRefreshListener() {
            //下拉刷新
            @Override
            public void onRefresh(MaterialRefreshLayout materialRefreshLayout) {
                refreshData();//具体实现方法
            }
            //上拉加载
            @Override
            public void onRefreshLoadMore(MaterialRefreshLayout materialRefreshLayout) {

                if(currPage <=totalPage)
                    loadMoreData();//具体实现方法
                else{
                    Toast.makeText(getContext(), "没有内容了", Toast.LENGTH_LONG).show();
                    mRefreshLaout.finishRefreshLoadMore();
                }
            }
        });

    }

    private void loadMoreData() {
        currPage = ++currPage;
        state = STATE_MORE;
        getData();
    }

    private void refreshData() {
        currPage =1;
        state=STATE_REFREH;
        getData();

    }


    private void showData() {

        switch (state){
            case  STATE_NORMAL:
//                mAdatper =  new HotWaresAdapter(datas);
                mAdatper = new HWAdatper(getContext(),datas);

                mRecyclerView.setAdapter(mAdatper);
                mRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
//                mRecyclerView.setItemAnimator(new DefaultItemAnimator());
//                mRecyclerView.addItemDecoration(new DividerItemDecoration(getContext(),DividerItemDecoration.VERTICAL_LIST));
                break;

            case STATE_REFREH:
                mAdatper.clear();
                mAdatper.addData(datas);

                mRecyclerView.scrollToPosition(0);//默认位置
                mRefreshLaout.finishRefresh();
                break;

            case STATE_MORE:
                mAdatper.addData(mAdatper.getDatas().size(),datas);
                mRecyclerView.scrollToPosition(mAdatper.getDatas().size());
                mRefreshLaout.finishRefreshLoadMore();
                break;
        }
    }


    //getData()方法内，实现showData()
    private void getData(){
        String url = Contants.API.WARES_HOT+"?curPage="+currPage+"&pageSize="+pageSize;
        httpHelper.get(url, new SpotsCallBack<Page<Wares>>(getContext()) {


            @Override
            public void onSuccess(Response response, Page<Wares> waresPage) {
                datas = waresPage.getList();
                currPage = waresPage.getCurrentPage();
                totalPage =waresPage.getTotalPage();

                showData();//
            }

            @Override
            public void onError(Response response, int code, Exception e) {

            }
        });

    }
}
