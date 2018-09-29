package com.technocracy.app.aavartan.Sponsors.View;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ProgressBar;

import com.squareup.picasso.Callback;
import com.squareup.picasso.Picasso;
import com.technocracy.app.aavartan.R;
import com.technocracy.app.aavartan.Sponsors.Model.Data.Sponsor;
import com.technocracy.app.aavartan.helper.App;

import java.util.ArrayList;
import java.util.List;

public class SimpleAdapter extends RecyclerView.Adapter<SimpleAdapter.SimpleViewHolder> {


    private static int COUNT;
    private final Context mContext;
    private final List<Integer> mItems;
    private final int size1, size2, size3;
    private int mCurrentItemId = 0;
    private List<Sponsor> sponsorList[];

    public SimpleAdapter(Context context, List<Sponsor>[] sponsorList) {
        mContext = context;
        this.sponsorList = sponsorList;
        size1 = sponsorList[0].size();
        size2 = sponsorList[1].size();
        size3 = sponsorList[2].size();
        COUNT = size1 + size2 + size3;
        Log.d("Aavartan2k17", "" + size1 + "  " + size2 + " " + size3);
        mItems = new ArrayList<>(COUNT);
        for (int i = 0; i < COUNT; i++) {
            addItem(i);
        }
    }

    public SimpleViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        final View view = LayoutInflater.from(mContext).inflate(R.layout.item, parent, false);
        return new SimpleViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final SimpleViewHolder holder, int position) {
        int category = 0;
        if (position < size1)
            category = 0;
        if (position >= size1 && position < size1 + size2) {
            category = 1;
            position = position - size1;
        } else if (position >= (size1 + size2 )&& position < (size1 + size2 + size3)) {
            category = 2;
            position = position - (size1 + size2);
        }
        App.showProgressBar(holder.pBAr);
        int width = (int) App.getScreenWidth(mContext);
        holder.imageView.getLayoutParams().height = width / 2;
        holder.imageView.getLayoutParams().width = width;
        holder.imageView.requestLayout();

        Picasso.with(mContext).load(App.Base_Url + sponsorList[category].get(position).getImage_url()).placeholder(R.drawable.avartan_logo100).
                into(holder.imageView, new Callback() {
                    @Override
                    public void onSuccess() {
                        App.hideProgressBar(holder.pBAr);
                    }

                    @Override
                    public void onError() {
                        App.hideProgressBar(holder.pBAr);
                    }
                });
    }

    public void addItem(int position) {
        final int id = mCurrentItemId++;
        mItems.add(position, id);
        notifyItemInserted(position);
    }

    public void removeItem(int position) {
        mItems.remove(position);
        notifyItemRemoved(position);
    }

    @Override
    public int getItemCount() {
        return mItems.size();
    }

    public static class SimpleViewHolder extends RecyclerView.ViewHolder {
        public ImageView imageView;
        public ProgressBar pBAr;

        public SimpleViewHolder(View view) {
            super(view);
            imageView = (ImageView) view.findViewById(R.id.spons);
            pBAr = (ProgressBar) view.findViewById(R.id.progressBar);
        }
    }
}