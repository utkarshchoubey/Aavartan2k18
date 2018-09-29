package com.technocracy.app.aavartan.Contacts.View;

import com.technocracy.app.aavartan.Contacts.Model.Data.Contact;

import java.util.List;

/**
 * Created by Abhi on 23-Sep-17.
 */

public interface ContactView {
    void showProgressBar(boolean b);

    void showMessage(String message);

    void showContacts(List<Contact> contacts);

    void showContactsFromDB();

    void showAppTeamFromDB();

    void showAppTeam(List<Contact> contacts);
}
