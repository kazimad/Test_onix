package com.example.kazimad.test_onix.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.kazimad.test_onix.R;
import com.example.kazimad.test_onix.models.Item;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

/**
 * Kazimad on 07.05.2015.
 */
public class ItemAdapter extends BaseAdapter {
    private ArrayList<Item> mArrayList;
    private Context mContext;

    public ItemAdapter(Context context, ArrayList<Item> arrayList) {

        mArrayList = arrayList;
        mContext = context;
    }
    @Override
    public int getCount() {
        return mArrayList.size();
    }

    @Override
    public Object getItem(int i) {
        return mArrayList.get(i);
    }

    @Override
    public long getItemId(int i) {
        return mArrayList.get(i).getId();
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        Holder holder;
        Item item = mArrayList.get(i);
        if (view == null) {
            holder = new Holder();
            view = LayoutInflater.from(mContext).inflate(R.layout.item_card, viewGroup, false);
            holder.text = (TextView) view.findViewById(R.id.news_header);
            holder.imageView = (ImageView) view.findViewById(R.id.imageView);
            view.setTag(holder);
        } else {
            holder = (Holder) view.getTag();
        }

        holder.text.setText(item.getText());
        Picasso.with(mContext).load(item.getLink()).centerCrop().fit().into(holder.imageView);
        return view;
    }

    public class Holder {
        ImageView imageView;
        TextView text;
    }
}
