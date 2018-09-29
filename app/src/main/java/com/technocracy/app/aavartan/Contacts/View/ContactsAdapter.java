package com.technocracy.app.aavartan.Contacts.View;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.net.Uri;
import android.support.v4.content.ContextCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.squareup.picasso.Callback;
import com.squareup.picasso.Picasso;
import com.technocracy.app.aavartan.Contacts.Model.Data.Contact;
import com.technocracy.app.aavartan.R;
import com.technocracy.app.aavartan.helper.App;

import java.util.List;

public class ContactsAdapter extends BaseAdapter {
    private List<Contact> contactList;
    private LayoutInflater inflater = null;
    private Context context;

    public ContactsAdapter(List<Contact> contactList, Context context) {
        this.contactList = contactList;
        this.context = context;
        inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public int getCount() {
        return contactList.size();
    }

    @Override
    public Object getItem(int position) {
        return position;
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        final ContactsAdapter.Holder holder = new ContactsAdapter.Holder();
        View rowView;

        rowView = inflater.inflate(R.layout.item_contacts, null);
        holder.contactImageView = (ImageView) rowView.findViewById(R.id.contact_image);
        holder.contactName = (TextView) rowView.findViewById(R.id.contact_name);
        holder.contactDesignation = (TextView) rowView.findViewById(R.id.contact_designation);
        holder.progressBar = (ProgressBar) rowView.findViewById(R.id.progressBar);
        holder.contactName.setText(contactList.get(position).getName());
        holder.contactDesignation.setText(contactList.get(position).getDesignation());

        int width = Math.round(App.getScreenWidth(context) / 2 - 10);
        int height = Math.round(width * 1);
        height = height / 2;
        Bitmap bitmap = Bitmap.createBitmap(width, height, Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(bitmap);
        Paint paint = new Paint();
        paint.setColor(ContextCompat.getColor(context, R.color.colorPrimaryDark));
        paint.setStyle(Paint.Style.FILL);
        canvas.drawPaint(paint);
        holder.contactImageView.setImageBitmap(bitmap);
        App.showProgressBar(holder.progressBar);
        Picasso.with(context).load(App.Base_Url + contactList.get(position).getImageUrl()).placeholder(R.drawable.avartan_logo100).into(holder.contactImageView, new Callback() {
            @Override
            public void onSuccess() {
                App.hideProgressBar(holder.progressBar);
            }

            @Override
            public void onError() {
                App.hideProgressBar(holder.progressBar);
            }
        });

        rowView.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Intent i = new Intent(Intent.ACTION_VIEW);
                i.setData(Uri.parse(contactList.get(position).getFacebookUrl()));
                context.startActivity(i);
            }
        });
        return rowView;
    }

    public class Holder {
        ImageView contactImageView;
        TextView contactName;
        TextView contactDesignation;
        ProgressBar progressBar;
    }
}