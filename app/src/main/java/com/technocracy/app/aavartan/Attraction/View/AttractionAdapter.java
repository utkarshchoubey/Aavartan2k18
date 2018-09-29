
package com.technocracy.app.aavartan.Attraction.View;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.squareup.picasso.Picasso;
import com.technocracy.app.aavartan.Attraction.Model.Data.Attraction;
import com.technocracy.app.aavartan.R;
import com.technocracy.app.aavartan.helper.App;

import java.util.List;

public class AttractionAdapter extends RecyclerView.Adapter<AttractionAdapter.MyViewHolder> {
    public Context context;
    List<Attraction> item;
    private LayoutInflater inflater;

    public AttractionAdapter(Context context, List<Attraction> item) {
        inflater = LayoutInflater.from(context);
        this.item = item;
        this.context = context;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.row_attraction, parent, false);
        return new MyViewHolder(view);
    }
    @Override
    public void onBindViewHolder(final MyViewHolder holder, int position) {
        Attraction i = item.get(position);
        App.showProgressBar(holder.progressBar);
        Picasso.with(context).load(i.getImgUrl()).into(holder.img, new com.squareup.picasso.Callback() {

            @Override
            public void onSuccess() {
                App.hideProgressBar(holder.progressBar);
            }

            @Override
            public void onError() {
                App.hideProgressBar(holder.progressBar);
             }
        });
        holder.name.setText(i.getName());
        Log.d("abhi", "name" + i.getName());
        holder.des.setText(i.getDescription() + "\n On  : " + i.getDate() + "\n At : " + i.getVenue());
    }

    @Override
    public int getItemCount() {
        return item.size();
    }

    class MyViewHolder extends RecyclerView.ViewHolder {
        public final View mView;
        public final TextView name, des;
        public final ImageView img;
        public final ProgressBar progressBar;

        public MyViewHolder(View itemView) {
            super(itemView);
            mView = itemView;
            name = (TextView) itemView.findViewById(R.id.event_name);
            img = (ImageView) itemView.findViewById(R.id.attractn_img);
            des = (TextView) itemView.findViewById(R.id.descrptn);
            progressBar = (ProgressBar) itemView.findViewById(R.id.progressBar);
        }
    }
}