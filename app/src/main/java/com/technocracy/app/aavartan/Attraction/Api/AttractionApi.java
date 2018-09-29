package com.technocracy.app.aavartan.Attraction.Api;

import com.technocracy.app.aavartan.Attraction.Model.Data.AttractionData;
import com.technocracy.app.aavartan.helper.App;

import retrofit2.Call;
import retrofit2.http.GET;

public interface AttractionApi {
    @GET(App.ATTRACTIONS_URL)
    Call<AttractionData> getAttraction();
}
