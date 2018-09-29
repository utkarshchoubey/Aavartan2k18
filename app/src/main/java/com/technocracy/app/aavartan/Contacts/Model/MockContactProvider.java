package com.technocracy.app.aavartan.Contacts.Model;

import android.os.Handler;

import com.technocracy.app.aavartan.Contacts.ContactCallback;
import com.technocracy.app.aavartan.Contacts.Model.Data.Contact;
import com.technocracy.app.aavartan.Contacts.Model.Data.ContactData;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Abhi on 23-Sep-17.
 */

public class MockContactProvider implements ContactProvider {
    private ContactData mockContactData;

    @Override
    public void getContact(final ContactCallback callback) {
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                callback.onSuccess(getMockContactData());
            }
        }, 500);
    }

    @Override
    public void getAppTeam(ContactCallback callback) {

    }

    public ContactData getMockContactData() {
        List<Contact> contacts = new ArrayList<>();
        contacts.add(new Contact(5, "ANSHUL", "CORE MEMBER",
                "https://scontent.fpat1-1.fna.fbcdn.net/v/t1.0-1/c1.0.160.160/p160x160/16603052_1837856553145740_4241745748474488928_n.jpg?oh=77f0afdb651764e60cf3b5909b2ad88e&oe=5A3D604D",
                "https://www.facebook.com/profile.php?id=100007642396765"));
        contacts.add(new Contact(3, "ALFURQUAN", "CORE MEMBER",
                "https://scontent.fpat1-1.fna.fbcdn.net/v/t1.0-1/p160x160/13645192_1100720950004875_5455753059674200183_n.jpg?oh=c79914447bc851bbea17529084386036&oe=5A40FD65",
                "https://www.facebook.com/alfurquan.zahedi"));
        contacts.add(new Contact(4, "PRAKASH", "CORE MEMBER",
                "https://scontent.fpat1-1.fna.fbcdn.net/v/t1.0-1/p160x160/19114000_1217243251732407_1859731626407717269_n.jpg?oh=fb142ca9d967d5e35f2ff80477e611ce&oe=5A886FF4",
                "https://www.facebook.com/prakashchaudhary13"));
        contacts.add(new Contact(2, "AYUSH", "CORE MEMBER",
                "https://scontent.fpat1-1.fna.fbcdn.net/v/t1.0-1/p160x160/12670907_967853046637814_6418299831819514854_n.jpg?oh=6fca6c0550a572a7d6aefe50ab4f6723&oe=5A85FACE",
                "https://www.facebook.com/profile.php?id=100002393839561"));
        contacts.add(new Contact(1, "ABHISHEK", "CORE MEMBER",
                "https://scontent.fagr1-1.fna.fbcdn.net/v/t1.0-9/16142786_1279738395449227_8859314931119645664_n.jpg?oh=2aa49ea9af23f8bcfe369ded0c127640&oe=5A3A6F1A",
                "https://www.facebook.com/abhishek.tripathi.923171"));
        mockContactData = new ContactData(true, contacts, "Success");
        return mockContactData;
    }
}