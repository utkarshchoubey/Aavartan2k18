package com.technocracy.app.aavartan.gallery.Presenter;

import android.content.Context;

import com.technocracy.app.aavartan.R;
import com.technocracy.app.aavartan.gallery.GalleryCallback;
import com.technocracy.app.aavartan.gallery.Model.Data.GalleryData;
import com.technocracy.app.aavartan.gallery.Model.GalleryProvider;
import com.technocracy.app.aavartan.gallery.View.GalleryView;


public class GalleryPresenterImpl implements GalleryPresenter {
    private GalleryView view;
    private GalleryProvider provider;
    private Context context;

    public GalleryPresenterImpl(Context context, GalleryProvider provider, GalleryView view) {
        this.view = view;
        this.provider = provider;
        this.context = context;
    }

    @Override
    public void getImages() {
        view.showProgressBar(true);
        provider.getImages(new GalleryCallback() {
            @Override
            public void onSuccess(GalleryData body) {
                if (body.isSuccess()) {
                    view.showProgressBar(false);
                    view.loadGallery(body.getImageList());
                } else {
                    view.showProgressBar(false);
                    view.showMessage(body.getMessage());
                }
            }

            @Override
            public void onFailure() {
                view.showProgressBar(false);
                view.showMessage(context.getString(R.string.Connection_Error));
            }
        });
    }
}
