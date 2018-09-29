package com.technocracy.app.aavartan.Contacts.Model;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.technocracy.app.aavartan.Contacts.Api.ContactApi;
import com.technocracy.app.aavartan.Contacts.ContactCallback;
import com.technocracy.app.aavartan.Contacts.Model.Data.ContactData;
import com.technocracy.app.aavartan.helper.App;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Call;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by Abhi on 23-Sep-17.
 */

public class RetrofitContactProvider implements ContactProvider {

    private final Retrofit retrofit;
    private ContactApi api;

    public RetrofitContactProvider() {
        HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
        interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
        OkHttpClient client = new OkHttpClient.Builder().addInterceptor(interceptor).
                readTimeout(60, TimeUnit.SECONDS).
                connectTimeout(60, TimeUnit.SECONDS).
                build();
        Gson gson = new GsonBuilder().setLenient().create();
        retrofit = new Retrofit.Builder().baseUrl(App.Base_Url).
                addConverterFactory(GsonConverterFactory.create(gson)).client(client).build();
    }

    @Override
    public void getContact(final ContactCallback callback) {
        api = retrofit.create(ContactApi.class);
        retrofit2.Call<ContactData> call = api.getContacts();
        call.enqueue(new retrofit2.Callback<ContactData>() {
            @Override
            public void onResponse(Call<ContactData> call, Response<ContactData> response) {
                callback.onSuccess(response.body());
            }

            @Override
            public void onFailure(Call<ContactData> call, Throwable t) {
                callback.onFailure();
                t.printStackTrace();
            }
        });
    }

    @Override
    public void getAppTeam(final ContactCallback callback) {
        api = retrofit.create(ContactApi.class);
        retrofit2.Call<ContactData> call = api.getAppTeam();
        call.enqueue(new retrofit2.Callback<ContactData>() {
            @Override
            public void onResponse(Call<ContactData> call, Response<ContactData> response) {
                callback.onSuccess(response.body());
            }

            @Override
            public void onFailure(Call<ContactData> call, Throwable t) {
                callback.onFailure();
                t.printStackTrace();
            }
        });
    }
}