package com.app.mj.shopping.adapter;

import android.content.Context;
import android.net.Uri;

import com.app.mj.shopping.R;
import com.app.mj.shopping.bean.Wares;
import com.facebook.drawee.view.SimpleDraweeView;

import java.util.List;


public class HWAdatper extends SimpleAdapter<Wares> {


    public HWAdatper(Context context, List<Wares> datas) {
        super(context, R.layout.template_hot_wares, datas);
    }

    @Override
    protected void convert(BaseViewHolder viewHolder, Wares wares) {
        SimpleDraweeView draweeView = (SimpleDraweeView) viewHolder.getView(R.id.drawee_view);
        draweeView.setImageURI(Uri.parse(wares.getImgUrl()));

        viewHolder.getTextView(R.id.text_title).setText(wares.getName());
//                        TextView textView = (TextView) viewHolder.getView(R.id.text_title);
    }
}
