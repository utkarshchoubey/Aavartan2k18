package com.technocracy.app.aavartan.Contacts;

import com.technocracy.app.aavartan.Contacts.Model.Data.ContactData;

/**
 * Created by Abhi on 23-Sep-17.
 */

public interface ContactCallback {
    void onFailure();

    void onSuccess(ContactData contactData);
}
