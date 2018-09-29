package com.technocracy.app.aavartan.Contacts.Model;


import com.technocracy.app.aavartan.Contacts.ContactCallback;

public interface ContactProvider {
    void getContact(ContactCallback callback);
    void getAppTeam(ContactCallback callback);
}
