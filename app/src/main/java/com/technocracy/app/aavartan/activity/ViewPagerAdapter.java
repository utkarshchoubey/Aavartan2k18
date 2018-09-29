package com.technocracy.app.aavartan.activity;

import android.content.Context;
import android.content.Intent;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.technocracy.app.aavartan.R;
import com.technocracy.app.aavartan.activity.intentactivity.ViewPDF;
import com.technocracy.app.aavartan.helper.App;

/**
 * Created by ALFURQUAN on 08-09-2017.
 */
public class ViewPagerAdapter extends PagerAdapter {

    private Context context;
    private LayoutInflater layoutInflater;
    private Integer[] images = {R.drawable.ar, R.drawable.biomedical, R.drawable.bio, R.drawable.chemical, R.drawable.civil1, R.drawable.cse1, R.drawable.electrical, R.drawable.elex, R.drawable.it_1, R.drawable.me, R.drawable.meta, R.drawable.mining1, R.drawable.mca, R.drawable.img1, R.drawable.go_green1};
    private String[] names = {"Architecture", "Bio Med", "Bio Tech", "Chemical", "Civil", "CSE", "Electrical", "Elex", "IT", "Mechanical", "Metallurgy", "Mining", "MCA", "E-Cell", "Go Green"};

    public ViewPagerAdapter(Context context) {
        this.context = context;
    }

    @Override
    public int getCount() {
        return images.length;
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view == object;
    }

    @Override
    public Object instantiateItem(ViewGroup container, final int position) {


        layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view = layoutInflater.inflate(R.layout.custom_layout, null);
        ImageView imageView = (ImageView) view.findViewById(R.id.thumbnail);
        imageView.setImageResource(images[position]);
        TextView textView = (TextView) view.findViewById(R.id.title);
        textView.setText(names[position]);

        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                switch (position) {
                    case 0:
                        App.LinkPDF = App.ArchiPDF;
                        Intent i1 = new Intent(context, ViewPDF.class);
                        context.startActivity(i1);
                        break;
                    case 1:
                        App.LinkPDF = App.BioMedPDF;
                        Intent i2 = new Intent(context, ViewPDF.class);
                        context.startActivity(i2);
                        break;
                    case 2:
                        App.LinkPDF = App.BioTechPDF;
                        Intent i3 = new Intent(context, ViewPDF.class);
                        context.startActivity(i3);
                        break;
                    case 3:
                        App.LinkPDF = App.ChemPDF;
                        Intent i4 = new Intent(context, ViewPDF.class);
                        context.startActivity(i4);
                        break;
                    case 4:
                        App.LinkPDF = App.CivilPDF;
                        Intent i5 = new Intent(context, ViewPDF.class);
                        context.startActivity(i5);
                        break;
                    case 5:
                        App.LinkPDF = App.CSEPDF;
                        Intent i6 = new Intent(context, ViewPDF.class);
                        context.startActivity(i6);
                        break;
                    case 6:
                        App.LinkPDF = App.ElecPDF;
                        Intent i7 = new Intent(context, ViewPDF.class);
                        context.startActivity(i7);
                        break;
                    case 7:
                        App.LinkPDF = App.ElexPDF;
                        Intent i8 = new Intent(context, ViewPDF.class);
                        context.startActivity(i8);
                        break;
                    case 8:
                        App.LinkPDF = App.ITPDF;
                        Intent i9 = new Intent(context, ViewPDF.class);
                        context.startActivity(i9);
                        break;
                    case 9:
                        App.LinkPDF = App.MechPDF;
                        Intent i10 = new Intent(context, ViewPDF.class);
                        context.startActivity(i10);
                        break;
                    case 10:
                        App.LinkPDF = App.MetaPDF;
                        Intent i11 = new Intent(context, ViewPDF.class);
                        context.startActivity(i11);
                        break;
                    case 11:
                        App.LinkPDF = App.MiningPDF;
                        Intent i12 = new Intent(context, ViewPDF.class);
                        context.startActivity(i12);
                        break;
                    case 12:
                        App.LinkPDF = App.MCAPDF;
                        Intent i13 = new Intent(context, ViewPDF.class);
                        context.startActivity(i13);
                        break;
                    case 13:
                        App.LinkPDF = App.EcellPDF;
                        Intent i14 = new Intent(context, ViewPDF.class);
                        context.startActivity(i14);
                        break;
                    case 14:
                        App.LinkPDF = App.GoGreenPDF;
                        Intent i15 = new Intent(context, ViewPDF.class);
                        context.startActivity(i15);
                        break;
                    default:
                        break;
                }

            }
        });

        ViewPager vp = (ViewPager) container;
        vp.addView(view, 0);
        return view;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        ViewPager vp = (ViewPager) container;
        View view = (View) object;
        vp.removeView(view);
    }
}