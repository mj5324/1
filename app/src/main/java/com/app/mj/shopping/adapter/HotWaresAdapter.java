package com.app.mj.shopping.adapter;

import android.net.Uri;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.app.mj.shopping.R;
import com.app.mj.shopping.bean.Wares;
import com.facebook.drawee.view.SimpleDraweeView;

import java.util.List;

/**
 * Created by mj on 2015/11/19.
 */
public class HotWaresAdapter extends RecyclerView.Adapter<HotWaresAdapter.ViewHolder> {

    private LayoutInflater mInflater;


    private List<Wares> mDatas;

    public HotWaresAdapter(List<Wares> mDatas) {
        this.mDatas = mDatas;
    }

    public HotWaresAdapter(LayoutInflater mInflater) {
        this.mInflater = mInflater;
    }

    //每个Item布局的对象
    @Override
    public HotWaresAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        mInflater = LayoutInflater.from(parent.getContext());
        View view = mInflater.inflate(R.layout.template_hot_wares,null);
        return new ViewHolder(view);
    }

    //绑定数据。获得数据+与viewHolder绑定
    @Override
    public void onBindViewHolder(HotWaresAdapter.ViewHolder holder, int position) {

        //拿到第i个Item对象，然后进行绑定
        Wares wares = getData(position);

        holder.draweeView.setImageURI(Uri.parse(wares.getImgUrl()));
        holder.textTitle.setText(wares.getName());
        holder.textPrice.setText("￥"+wares.getPrice());
    }

    public Wares getData(int position) {
        return mDatas.get(position);
    }


    @Override
    public int getItemCount() {
        if(mDatas!=null && mDatas.size()>0){
            return mDatas.size();
        }else{
            return 0;
        }
    }




    //内部类
    class ViewHolder extends RecyclerView.ViewHolder {

        SimpleDraweeView draweeView;
        TextView textTitle;
        TextView textPrice;

        public ViewHolder(View itemView) {
            super(itemView);
            draweeView = (SimpleDraweeView) itemView.findViewById(R.id.drawee_view);
            textTitle= (TextView) itemView.findViewById(R.id.text_title);
            textPrice= (TextView) itemView.findViewById(R.id.text_price);
        }
    }


//recyclerView刷新时清空数据
    public void clear() {
        mDatas.clear();
        notifyItemRangeRemoved(0,mDatas.size());
    }



    public void addData(int position,List<Wares> datas){
        if(datas !=null && datas.size()>0) {
            mDatas.addAll(datas);
            notifyItemRangeChanged(position, mDatas.size());
        }
    }

    public void addData(List<Wares> datas){
        addData(0,datas);
    }



    public List<Wares> getDatas(){
        return  mDatas;
    }

}
