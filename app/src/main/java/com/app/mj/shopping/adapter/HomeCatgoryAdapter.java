package com.app.mj.shopping.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.app.mj.shopping.R;
import com.app.mj.shopping.bean.Campaign;
import com.app.mj.shopping.bean.HomeCampaign;
import com.squareup.picasso.Picasso;

import java.util.List;



public class HomeCatgoryAdapter extends RecyclerView.Adapter<HomeCatgoryAdapter.ViewHolder> {





    private  static int VIEW_TYPE_L=0;//判断使用哪个布局xml
    private  static int VIEW_TYPE_R=1;//判断使用哪个布局xml

    private LayoutInflater mInflater;//从xml获得view对象

    private List<HomeCampaign> mDatas;

    private  Context mContext;



    private  OnCampaignClickListener mListener;






    //内部类 ViewHolder <参数>
    class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        TextView textTitle;
        ImageView imageViewBig;
        ImageView imageViewSmallTop;
        ImageView imageViewSmallBottom;

        public ViewHolder(View itemView) {
            super(itemView);
            textTitle = (TextView) itemView.findViewById(R.id.text_title);
            imageViewBig = (ImageView) itemView.findViewById(R.id.imgview_big);
            imageViewSmallTop = (ImageView) itemView.findViewById(R.id.imgview_small_top);
            imageViewSmallBottom = (ImageView) itemView.findViewById(R.id.imgview_small_bottom);

            imageViewBig.setOnClickListener(this);
            imageViewSmallTop.setOnClickListener(this);
            imageViewSmallBottom.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            HomeCampaign homeCampaign = mDatas.get(getLayoutPosition());

            if (mListener != null) {
                switch (v.getId()) {
                    case R.id.imgview_big:
                        mListener.onClick(v, homeCampaign.getCpOne());
                        break;
                    case R.id.imgview_small_top:
                        mListener.onClick(v, homeCampaign.getCpTwo());
                        break;
                    case R.id.imgview_small_bottom:
                        mListener.onClick(v, homeCampaign.getCpThree());
                        break;
                }
            }
        }
    }





    //构造方法，初始化数据
    public HomeCatgoryAdapter(List<HomeCampaign> datas,Context context){
        mDatas = datas;
        this.mContext = context;
    }




    //ViewHolder布局，得到view
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int type) {

        mInflater = LayoutInflater.from(viewGroup.getContext());
        if(type == VIEW_TYPE_R){
            return  new ViewHolder(mInflater.inflate(R.layout.template_home_cardview2,viewGroup,false));
        }else{
            return  new ViewHolder(mInflater.inflate(R.layout.template_home_cardview,viewGroup,false));
        }
    }

    //绑定数据
    @Override
    public void onBindViewHolder(ViewHolder viewHolder, int i) {

        HomeCampaign homeCampaign = mDatas.get(i);
        viewHolder.textTitle.setText(homeCampaign.getTitle());

        Picasso.with(mContext).load(homeCampaign.getCpOne().getImgUrl()).into(viewHolder.imageViewBig);
        Picasso.with(mContext).load(homeCampaign.getCpTwo().getImgUrl()).into(viewHolder.imageViewSmallTop);
        Picasso.with(mContext).load(homeCampaign.getCpThree().getImgUrl()).into(viewHolder.imageViewSmallBottom);
    }

    @Override
    public int getItemCount() {
        return mDatas.size();
    }

    @Override
    public int getItemViewType(int position) {
        if(position % 2==0){
            return  VIEW_TYPE_R;
        }
        else {return VIEW_TYPE_L;}
    }




    //关于点击事件，我擦咧，自己写

//来个接口
    public  interface OnCampaignClickListener{
        void onClick(View view,Campaign campaign);
    }

//监听方法
    public void setOnCampaignClickListener(OnCampaignClickListener listener){
        this.mListener = listener;
    }

//在ViewHolder类继承借口，并中添加onClick()方法

}
