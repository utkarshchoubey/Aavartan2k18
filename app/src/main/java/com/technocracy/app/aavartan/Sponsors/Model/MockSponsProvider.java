package com.technocracy.app.aavartan.Sponsors.Model;

import android.os.Handler;

import com.technocracy.app.aavartan.Sponsors.Model.Data.SponsData;
import com.technocracy.app.aavartan.Sponsors.Model.Data.Sponsor;
import com.technocracy.app.aavartan.Sponsors.SponsCallback;

import java.util.ArrayList;
import java.util.List;

public class MockSponsProvider implements SponsProvider {
    private SponsData mockSpons;

    @Override
    public void getSpons(final SponsCallback callback) {
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                callback.onSuccess(getMockSpons());
            }
        }, 500);
    }

    public SponsData getMockSpons() {
        List<Sponsor> list = new ArrayList<>();
        list.add(new Sponsor("1", "BabuRao", "http://www.technocracynitrr.org/assets/img/2.jpg"));
        list.add(new Sponsor("2", "BabuRao", "http://www.technocracynitrr.org/assets/img/2.jpg"));
        list.add(new Sponsor("3", "BabuRao", "http://www.technocracynitrr.org/assets/img/2.jpg"));
        list.add(new Sponsor("4", "BabuRao", "http://www.technocracynitrr.org/assets/img/2.jpg"));
        list.add(new Sponsor("1", "BabuRao", "http://www.technocracynitrr.org/assets/img/2.jpg"));
        list.add(new Sponsor("3", "BabuRao", "http://www.technocracynitrr.org/assets/img/2.jpg"));
        list.add(new Sponsor("4", "BabuRao", "http://www.technocracynitrr.org/assets/img/2.jpg"));
        list.add(new Sponsor("1", "BabuRao", "http://www.technocracynitrr.org/assets/img/2.jpg"));
        list.add(new Sponsor("2", "BabuRao", "http://www.technocracynitrr.org/assets/img/2.jpg"));
        list.add(new Sponsor("1", "BabuRao", "http://www.technocracynitrr.org/assets/img/2.jpg"));
        list.add(new Sponsor("1", "BabuRao", "http://www.technocracynitrr.org/assets/img/2.jpg"));
        list.add(new Sponsor("4", "BabuRao", "http://www.technocracynitrr.org/assets/img/2.jpg"));
        list.add(new Sponsor("1", "BabuRao", "http://www.technocracynitrr.org/assets/img/2.jpg"));
        list.add(new Sponsor("1", "BabuRao", "http://www.technocracynitrr.org/assets/img/2.jpg"));
        list.add(new Sponsor("4", "BabuRao", "http://www.technocracynitrr.org/assets/img/2.jpg"));
        list.add(new Sponsor("3", "BabuRao", "http://www.technocracynitrr.org/assets/img/2.jpg"));
        list.add(new Sponsor("3", "BabuRao", "http://www.technocracynitrr.org/assets/img/2.jpg"));
        mockSpons = new SponsData(true,"Success",list);
        return mockSpons;
    }
}
