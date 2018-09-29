package com.technocracy.app.aavartan.Contacts.Model.Data;

import java.util.List;

public class ContactData {
    private boolean success;
    private List<Contact> contacts;
    public ContactData(boolean success, List<Contact> contacts, String message) {
        this.success = success;
        this.contacts = contacts;
    }

    public boolean isSuccess() {
        return success;
    }

    public List<Contact> getContacts() {
        return contacts;
    }
}
