package com.technocracy.app.aavartan.Sponsors.Presenter;

import android.content.Context;

import com.technocracy.app.aavartan.R;
import com.technocracy.app.aavartan.Sponsors.Model.Data.SponsData;
import com.technocracy.app.aavartan.Sponsors.Model.SponsProvider;
import com.technocracy.app.aavartan.Sponsors.SponsCallback;
import com.technocracy.app.aavartan.Sponsors.View.SponsView;

public class SponsPresenterImpl implements SponsPresenter {

    private SponsProvider provider;
    private SponsView view;
    private Context context;
    public SponsPresenterImpl(SponsProvider provider, SponsView view, Context context) {
        this.provider = provider;
        this.view = view;
        this.context = context;
    }

    @Override
    public void getSpons() {
        view.showProgressBar(true);
        provider.getSpons(new SponsCallback() {
            @Override
            public void onFailure() {
                view.showProgressBar(false);
                //  view.showSponsFromDatabase();
                view.showMessage(context.getResources().getString(R.string.Connection_Error));

            }

            @Override
            public void onSuccess(SponsData body) {
                view.showProgressBar(false);
                if (body.isSuccess()) {
                    view.showSpons(body.getSponsorList());
                } else {
                    view.showMessage(body.getMessage());
                    //  view.showSponsFromDatabase();
                }
            }
        });
    }
}