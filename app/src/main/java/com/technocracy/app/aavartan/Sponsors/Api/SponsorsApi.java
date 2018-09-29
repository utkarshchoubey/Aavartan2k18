package com.technocracy.app.aavartan.Sponsors.Api;

import com.technocracy.app.aavartan.Sponsors.Model.Data.SponsData;
import com.technocracy.app.aavartan.helper.App;

import retrofit2.Call;
import retrofit2.http.GET;

public interface SponsorsApi {
    @GET(App.SPONSORS_URL)
    Call<SponsData> getSpons();
}
