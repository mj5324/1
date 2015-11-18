package com.app.mj.shopping;

import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TabHost;
import android.widget.TextView;

import com.app.mj.shopping.Widget.FragmentTabHost;
import com.app.mj.shopping.bean.Tab;
import com.app.mj.shopping.fragment.CartFragment;
import com.app.mj.shopping.fragment.CategoryFragment;
import com.app.mj.shopping.fragment.HomeFragment;
import com.app.mj.shopping.fragment.HotFragment;
import com.app.mj.shopping.fragment.MineFragment;

import java.util.ArrayList;
import java.util.List;



public class MainActivity extends FragmentActivity {

    private FragmentTabHost mTabHost;
    private LayoutInflater mInflater;
    private List<Tab> mTabs = new ArrayList<>(5);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initTab(); //初始化，创建activity时就添加
    }


    //1.初始化函数
    private void initTab() {

        mTabHost = (FragmentTabHost) this.findViewById(android.R.id.tabhost); //获得View实体
        mTabHost.setup(this,getSupportFragmentManager(),R.id.realtabcontent);

        Tab tab_home=new Tab( HomeFragment.class,R.string.home,R.drawable.selector_icon_home);
        Tab tab_hot = new Tab(HotFragment.class,R.string.hot,R.drawable.selector_icon_hot);
        Tab tab_category = new Tab(CategoryFragment.class,R.string.category,R.drawable.selector_icon_category);
        Tab tab_cart = new Tab(CartFragment.class,R.string.cart,R.drawable.selector_icon_cart);
        Tab tab_mine = new Tab(MineFragment.class,R.string.mine,R.drawable.selector_icon_mine);

        mTabs.add(tab_home);
        mTabs.add(tab_hot);
        mTabs.add(tab_category);
        mTabs.add(tab_cart);
        mTabs.add(tab_mine);


        for (Tab tab : mTabs){
            //TabSpec和Indicator
            TabHost.TabSpec tabSpec = mTabHost.newTabSpec(getString(tab.getTitle()));
            tabSpec.setIndicator(buildIndicator(tab));
            //添加到FragmentTabHost中
            mTabHost.addTab(tabSpec,tab.getFragment(),null);
        }

        mTabHost.getTabWidget().setShowDividers(LinearLayout.SHOW_DIVIDER_NONE); //去掉间隔线
        mTabHost.setCurrentTab(0);  //设置初始位置
    }

    //1.得到View的函数
    private  View buildIndicator(Tab tab){

        mInflater = LayoutInflater.from(this);      //private只是声明，这里是获得这个View实体进行操作

        View view =mInflater.inflate(R.layout.tab_indicator,null);
        ImageView img = (ImageView) view.findViewById(R.id.icon_tab);
        TextView text = (TextView) view.findViewById(R.id.txt_indicator);

        img.setBackgroundResource(tab.getIcon());
        text.setText(tab.getTitle());

        return  view;
    }


}
