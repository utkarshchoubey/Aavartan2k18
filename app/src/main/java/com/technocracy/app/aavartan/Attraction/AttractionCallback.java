package com.technocracy.app.aavartan.Attraction;


import com.technocracy.app.aavartan.Attraction.Model.Data.AttractionData;

public interface AttractionCallback {
    void onFailure();

    void onSuccess(AttractionData body);
}
