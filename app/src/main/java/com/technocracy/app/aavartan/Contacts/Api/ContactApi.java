package com.technocracy.app.aavartan.Contacts.Api;

import com.technocracy.app.aavartan.Contacts.Model.Data.ContactData;
import com.technocracy.app.aavartan.helper.App;

import retrofit2.Call;
import retrofit2.http.GET;

public interface ContactApi {
    //TODO: type 1 for all contacts and type 2 for android team
    @GET(App.CONTACT)
    Call<ContactData> getContacts();

    @GET(App.CONTACT_APP)
    Call<ContactData> getAppTeam();
}
