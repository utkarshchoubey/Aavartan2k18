package com.technocracy.app.aavartan.Attraction.Presenter;


import android.content.Context;

import com.technocracy.app.aavartan.Attraction.AttractionCallback;
import com.technocracy.app.aavartan.Attraction.Model.AttractionProvider;
import com.technocracy.app.aavartan.Attraction.Model.Data.AttractionData;
import com.technocracy.app.aavartan.Attraction.View.AttractionView;
import com.technocracy.app.aavartan.R;

public class AttractionPresenterImpl implements AttractionPresenter {

    private AttractionProvider provider;
    private AttractionView view;
    private Context context;

    public AttractionPresenterImpl(AttractionProvider provider, AttractionView view, Context context) {
        this.provider = provider;
        this.view = view;
        this.context = context;
    }

    @Override
    public void getAttractions() {
        view.showProgressBar(true);
        provider.getAttractions(new AttractionCallback() {
            @Override
            public void onFailure() {
                view.showProgressBar(false);
                view.showAttractionsFromDatabase();
                view.showMessage(context.getResources().getString(R.string.Connection_Error));
            }

            @Override
            public void onSuccess(AttractionData body) {
                view.showProgressBar(false);
                if (body.isSuccess()) {
                    view.showAttractions(body.getAttractionList());
                } else {
                    view.showAttractionsFromDatabase();
                }
            }
        });
    }
}