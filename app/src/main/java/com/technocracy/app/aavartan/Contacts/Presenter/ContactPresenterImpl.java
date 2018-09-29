package com.technocracy.app.aavartan.Contacts.Presenter;

import android.content.Context;

import com.technocracy.app.aavartan.Contacts.ContactCallback;
import com.technocracy.app.aavartan.Contacts.Model.ContactProvider;
import com.technocracy.app.aavartan.Contacts.Model.Data.ContactData;
import com.technocracy.app.aavartan.Contacts.View.ContactView;
import com.technocracy.app.aavartan.R;

public class ContactPresenterImpl implements ContactPresenter {
    private ContactView view;
    private ContactProvider provider;
    private Context context;

    public ContactPresenterImpl(ContactView view, ContactProvider provider, Context context) {
        this.view = view;
        this.provider = provider;
        this.context = context;
    }


    @Override
    public void getContact(String type) {
        view.showProgressBar(true);
        if (type.equals("1")) {
            provider.getContact(new ContactCallback() {
                @Override
                public void onFailure() {
                    view.showProgressBar(false);
                    view.showContactsFromDB();
                    view.showMessage(context.getResources().getString(R.string.Connection_Error));
                }

                @Override
                public void onSuccess(ContactData contactData) {
                    view.showProgressBar(false);
                    if (contactData.isSuccess()) {
                        view.showContacts(contactData.getContacts());
                    } else {
                        view.showContactsFromDB();
                    }
                }
            });
        } else {
            provider.getAppTeam(new ContactCallback() {
                @Override
                public void onFailure() {
                    view.showProgressBar(false);
                    view.showAppTeamFromDB();
                    view.showMessage(context.getResources().getString(R.string.Connection_Error));
                }

                @Override
                public void onSuccess(ContactData contactData) {
                    view.showProgressBar(false);
                    if (contactData.isSuccess()) {
                        view.showAppTeam(contactData.getContacts());
                    } else {
                        view.showAppTeamFromDB();
                        view.showMessage("Some Error!Try Again");
                    }
                }
            });
        }
    }
}