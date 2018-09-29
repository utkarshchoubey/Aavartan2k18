package com.technocracy.app.aavartan.helper;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import com.technocracy.app.aavartan.Attraction.Model.Data.Attraction;
import com.technocracy.app.aavartan.Contacts.Model.Data.Contact;
import com.technocracy.app.aavartan.Event.Model.Data.Event;
import com.technocracy.app.aavartan.api.MyEvent;
import com.technocracy.app.aavartan.api.Notifications;

import java.util.ArrayList;
import java.util.List;

public class DatabaseHandler extends SQLiteOpenHelper {

    // All Static variables
    // Database Version
    private static final int DATABASE_VERSION = 2;

    // Database Name
    private static final String DATABASE_NAME = "LocalDatabase";

    // Notifications table name
    private static final String TABLE_NOTIFICATIONS = "notifications";
    // Notifications Table Columns names
    private static final String NOTIFICATION_ID = "id";
    private static final String NOTIFICATION_TITLE = "title";
    private static final String NOTIFICATION_MESSAGE = "message";
    private static final String NOTIFICATION_IMAGE_URL = "image_url";
    private static final String NOTIFICATION_CREATED_AT = "created_at";

    // table name
    private static final String TABLE_ATTRACTIONS = "attractions";
    // table columns names
    private static final String ATTRACTION_NAME = "name";
    private static final String ATTRACTION_DESCRIPTION = "description";
    private static final String ATTRACTION_IMAGE_URL = "image";
    private static final String ATTRACTION_DATE = "date";
    private static final String ATTRACTION_VENUE = "venue";


    // table names TODO: Add the particular events_set_Id for the tables
    private static final String TABLE_FUNEVENTS = "fun";
    private static final String TABLE_MANAGERIALEVENTS = "manager";
    private static final String TABLE_TECHNICALVENTS = "tech";
    private static final String TABLE_ROBOTICS = "robo";

    // table columns names
    private static final String EVENT_NAME = "eventname";
    private static final String EVENT_TYPE = "eventtype";
    private static final String EVENT_DESCRIPTION = "eventdescription";
    private static final String EVENT_ID = "eventid";
    private static final String EVENT_IMAGE_URL = "eventimageurl";

    // table name
    private static final String TABLE_GALLERY = "gallery";
    // table columns names
    private static final String GALLERY_ID = "id";
    private static final String GALLERY_TITLE = "title";
    private static final String GALLERY_RATIO = "ratio";
    private static final String GALLERY_IMAGE_URL = "image";

    // table name
    private static final String TABLE_CONTACTS = "contacts";
    // table columns names
    private static final String CONTACTS_ID = "id";
    private static final String CONTACTS_NAME = "name";
    private static final String CONTACTS_DESIGNATION = "designation";
    private static final String CONTACTS_IMAGE_URL = "image";
    private static final String CONTACTS_FACEBOOK_URL = "facebook";

    // table name
    private static final String TABLE_SCHEDULE_DAY1 = "schedule_day1";
    // table name
    private static final String TABLE_MY_EVENTS = "my_events";
    // table columns names
    private static final String MY_EVENTS_ID = "id";
    private static final String MY_EVENTS_EVENT_NAME = "event_name";
    private static final String MY_EVENTS_EVENT_DATE = "event_date";
    private static final String EVENT_VENUE = "event_venue";
    private static final String EVENT_TIME = "event_time";
    private static final String EVENT_DATE = "event_date";
    // table name
    private static final String TABLE_SCHEDULE_DAY2 = "schedule_day2";
    Context context;
    private List<Contact> appTeamContacts;
    private String TABLE_APP_TEAM = "appTeam";

    public DatabaseHandler(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
        this.context = context;
    }

    // Creating Tables
    @Override
    public void onCreate(SQLiteDatabase db) {
        String CREATE_NOTIFICATIONS_TABLE = "CREATE TABLE " + TABLE_NOTIFICATIONS + "("
                + NOTIFICATION_ID + " INTEGER PRIMARY KEY," + NOTIFICATION_TITLE + " TEXT,"
                + NOTIFICATION_MESSAGE + " TEXT," + NOTIFICATION_IMAGE_URL + " TEXT,"
                + NOTIFICATION_CREATED_AT + " TEXT" + ")";
        db.execSQL(CREATE_NOTIFICATIONS_TABLE);

        String CREATE_ATTRACTIONS_TABLE = "CREATE TABLE " + TABLE_ATTRACTIONS + "("
                + ATTRACTION_NAME + " TEXT," + ATTRACTION_DATE + " TEXT," +
                ATTRACTION_VENUE + " TEXT," +
                ATTRACTION_DESCRIPTION + " TEXT," + ATTRACTION_IMAGE_URL + " TEXT" + ")";
        db.execSQL(CREATE_ATTRACTIONS_TABLE);

        String CREATE_GALLERY_TABLE = "CREATE TABLE " + TABLE_GALLERY + "("
                + GALLERY_ID + " INTEGER PRIMARY KEY," + GALLERY_TITLE + " TEXT,"
                + GALLERY_RATIO + " TEXT," + GALLERY_IMAGE_URL + " TEXT" + ")";
        db.execSQL(CREATE_GALLERY_TABLE);

        String CREATE_CONTACTS_TABLE = "CREATE TABLE " + TABLE_CONTACTS + "("
                + CONTACTS_ID + " INTEGER," + CONTACTS_NAME + " TEXT,"
                + CONTACTS_DESIGNATION + " TEXT," + CONTACTS_IMAGE_URL + " TEXT,"
                + CONTACTS_FACEBOOK_URL + " TEXT" + ")";
        db.execSQL(CREATE_CONTACTS_TABLE);

        String CREATE_APP_TEAM_TABLE = "CREATE TABLE " + TABLE_APP_TEAM + "("
                + CONTACTS_ID + " INTEGER," + CONTACTS_NAME + " TEXT,"
                + CONTACTS_DESIGNATION + " TEXT," + CONTACTS_IMAGE_URL + " TEXT,"
                + CONTACTS_FACEBOOK_URL + " TEXT" + ")";
        db.execSQL(CREATE_APP_TEAM_TABLE);

        String CREATE_SCHEDULE_DAY1_TABLE = "CREATE TABLE " + TABLE_SCHEDULE_DAY1 + "("
                + EVENT_ID + " TEXT PRIMARY KEY," + EVENT_NAME + " TEXT,"
                + EVENT_DESCRIPTION + " TEXT," + EVENT_TYPE + " TEXT," + EVENT_DATE + " TEXT,"
                + EVENT_TIME + " TEXT," + EVENT_VENUE + " TEXT," + EVENT_IMAGE_URL + " TEXT" + ")";
        db.execSQL(CREATE_SCHEDULE_DAY1_TABLE);

        String CREATE_SCHEDULE_DAY2_TABLE = "CREATE TABLE " + TABLE_SCHEDULE_DAY2 + "("
                + EVENT_ID + " TEXT PRIMARY KEY," + EVENT_NAME + " TEXT,"
                + EVENT_DESCRIPTION + " TEXT," + EVENT_TYPE + " TEXT," + EVENT_DATE + " TEXT,"
                + EVENT_TIME + " TEXT," + EVENT_VENUE + " TEXT," + EVENT_IMAGE_URL + " TEXT" + ")";
        db.execSQL(CREATE_SCHEDULE_DAY2_TABLE);

        String CREATE_FUNEVENTS_TABLE = "CREATE TABLE " + TABLE_FUNEVENTS + "("
                + EVENT_ID + " INTEGER," + EVENT_NAME + " TEXT,"
                + EVENT_DESCRIPTION + " TEXT," + EVENT_TYPE + " TEXT," + EVENT_DATE + " TEXT,"
                + EVENT_TIME + " TEXT," + EVENT_VENUE + " TEXT," + EVENT_IMAGE_URL + " TEXT" + ")";
        db.execSQL(CREATE_FUNEVENTS_TABLE);

        String CREATE_MANAGERIALEVENTS_TABLE = "CREATE TABLE " + TABLE_MANAGERIALEVENTS + "("
                + EVENT_ID + "  INTEGER," + EVENT_NAME + " TEXT,"
                + EVENT_DESCRIPTION + " TEXT," + EVENT_TYPE + " TEXT," + EVENT_DATE + " TEXT,"
                + EVENT_TIME + " TEXT," + EVENT_VENUE + " TEXT," + EVENT_IMAGE_URL + " TEXT" + ")";
        db.execSQL(CREATE_MANAGERIALEVENTS_TABLE);

        String CREATE_TECHNICALEVENTS_TABLE = "CREATE TABLE " + TABLE_TECHNICALVENTS + "("
                + EVENT_ID + "  INTEGER," + EVENT_NAME + " TEXT,"
                + EVENT_DESCRIPTION + " TEXT," + EVENT_TYPE + " TEXT," + EVENT_DATE + " TEXT,"
                + EVENT_TIME + " TEXT," + EVENT_VENUE + " TEXT," + EVENT_IMAGE_URL + " TEXT" + ")";
        db.execSQL(CREATE_TECHNICALEVENTS_TABLE);

        String CREATE_ROBOTICS_TABLE = "CREATE TABLE " + TABLE_ROBOTICS + "("
                + EVENT_ID + " INTEGER," + EVENT_NAME + " TEXT,"
                + EVENT_DESCRIPTION + " TEXT," + EVENT_TYPE + " TEXT," + EVENT_DATE + " TEXT,"
                + EVENT_TIME + " TEXT," + EVENT_VENUE + " TEXT," + EVENT_IMAGE_URL + " TEXT" + ")";
        db.execSQL(CREATE_ROBOTICS_TABLE);

        String CREATE_MY_EVENTS_TABLE = "CREATE TABLE " + TABLE_MY_EVENTS + "("
                + MY_EVENTS_ID + " INTEGER PRIMARY KEY," + MY_EVENTS_EVENT_NAME + " TEXT,"
                + MY_EVENTS_EVENT_DATE + " TEXT" + ")";
        db.execSQL(CREATE_MY_EVENTS_TABLE);
    }

    // Upgrading database
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // Drop older table if existed
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NOTIFICATIONS);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_ATTRACTIONS);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_GALLERY);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_FUNEVENTS);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_MANAGERIALEVENTS);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_TECHNICALVENTS);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_ROBOTICS);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_CONTACTS);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_SCHEDULE_DAY1);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_SCHEDULE_DAY2);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_MY_EVENTS);
        // Create tables again
        onCreate(db);
    }

    public void addAttraction(Attraction attraction) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(ATTRACTION_NAME, attraction.getName());
        values.put(ATTRACTION_DATE, attraction.getDate());
        values.put(ATTRACTION_VENUE, attraction.getVenue());
        values.put(ATTRACTION_DESCRIPTION, attraction.getDescription());
        values.put(ATTRACTION_IMAGE_URL, attraction.getImgUrl());
        // Inserting Row
        db.insert(TABLE_ATTRACTIONS, null, values);
        db.close(); // Closing database connection
        Log.e("Attraction:", "stored in db.");
    }

    public ArrayList<Attraction> getAllAttractions() {
        ArrayList<Attraction> attractionList = new ArrayList<Attraction>();
        String selectQuery = "SELECT  * FROM " + TABLE_ATTRACTIONS;

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

        if (cursor.moveToFirst()) {
            do {
                Attraction attraction = new Attraction(cursor.getString(0), cursor.getString(1),
                        cursor.getString(2), cursor.getString(3), cursor.getString(4));
                attractionList.add(attraction);
            } while (cursor.moveToNext());
        }
        return attractionList;
    }

    public void deleteAllAttractions() {
        SQLiteDatabase db = this.getWritableDatabase();
        db.execSQL("DELETE FROM " + TABLE_ATTRACTIONS);
    }


    public void addEvents(Event event, String key) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(EVENT_ID, event.getEventId());
        values.put(EVENT_NAME, event.getName());
        values.put(EVENT_DESCRIPTION, event.getDescription());
        values.put(EVENT_TYPE, event.getType());
        values.put(EVENT_DATE, event.getDate());
        values.put(EVENT_TIME, event.getTime());
        values.put(EVENT_VENUE, event.getVenue());
        values.put(EVENT_IMAGE_URL, event.getImage_url());
        // Inserting Row
        long loc = db.insert(key, null, values);
        Log.d("abhi", "Adding Event" + loc + " " + key);
        db.close(); // Closing database connection
    }

    public List<Event> getAllEvents(String key) {
        List<Event> eventList = new ArrayList<Event>();
        String selectQuery = "SELECT  * FROM " + key + " ORDER BY " + EVENT_ID;
        Log.d("abhi", "trying to get events" + key);
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);
        if (cursor.moveToFirst()) {
            do {
                Event event = new Event(cursor.getInt(0), cursor.getString(1),
                        cursor.getString(2), cursor.getString(3), cursor.getString(4), cursor.getString(5), cursor.getString(6),
                        cursor.getString(7), "", "");
                eventList.add(event);
                Log.d("abhi", "trying to get events" + 11);

            } while (cursor.moveToNext());
        }
        return eventList;
    }

    public void deleteAllEvent(String key) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.execSQL("DELETE FROM " + key);
    }

    public void addContact(Contact contact) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(CONTACTS_ID, contact.getId());
        values.put(CONTACTS_NAME, contact.getName());
        values.put(CONTACTS_DESIGNATION, contact.getDesignation());
        values.put(CONTACTS_IMAGE_URL, contact.getImageUrl());
        values.put(CONTACTS_FACEBOOK_URL, contact.getFacebookUrl());
        // Inserting Row
        db.insert(TABLE_CONTACTS, null, values);
        db.close(); // Closing database connection
        Log.e("Contact :", "stored in db.");
    }

    public ArrayList<Contact> getAllContacts() {
        ArrayList<Contact> contactList = new ArrayList<Contact>();
        String selectQuery = "SELECT * FROM " + TABLE_CONTACTS;

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);
        if (cursor.moveToFirst()) {
            do {
                Contact contact = new Contact(cursor.getInt(0), cursor.getString(1),
                        cursor.getString(2), cursor.getString(3), cursor.getString(4));
                contactList.add(contact);
            } while (cursor.moveToNext());
        }
        return contactList;
    }

    public void deleteAllContacts() {
        SQLiteDatabase db = this.getWritableDatabase();
        db.execSQL("DELETE FROM " + TABLE_CONTACTS);
    }

    public void addAppTeamContact(Contact contact) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(CONTACTS_ID, contact.getId());
        values.put(CONTACTS_NAME, contact.getName());
        values.put(CONTACTS_DESIGNATION, contact.getDesignation());
        values.put(CONTACTS_IMAGE_URL, contact.getImageUrl());
        values.put(CONTACTS_FACEBOOK_URL, contact.getFacebookUrl());
        // Inserting Row
        db.insert(TABLE_APP_TEAM, null, values);
        db.close(); // Closing database connection
        Log.e("Contact :", "stored in db.");
    }

    public List<Contact> getAppTeamContacts() {
        List<Contact> contactList = new ArrayList<Contact>();
        String selectQuery = "SELECT * FROM " + TABLE_APP_TEAM;

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);
        if (cursor.moveToFirst()) {
            do {
                Contact contact = new Contact(cursor.getInt(0), cursor.getString(1),
                        cursor.getString(2), cursor.getString(3), cursor.getString(4));
                contactList.add(contact);
            } while (cursor.moveToNext());
        }
        return contactList;
    }

    public void deleteAppTeamContacts() {
        SQLiteDatabase db = this.getWritableDatabase();
        db.execSQL("DELETE FROM " + TABLE_APP_TEAM);
    }


    public void addScheduleDay1Item(Event event) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(EVENT_ID, event.getEventId());
        values.put(EVENT_NAME, event.getName());
        values.put(EVENT_DESCRIPTION, event.getDescription());
        values.put(EVENT_TYPE, event.getType());
        values.put(EVENT_DATE, event.getDate());
        values.put(EVENT_TIME, event.getTime());
        values.put(EVENT_VENUE, event.getVenue());
        values.put(EVENT_IMAGE_URL, event.getImage_url());
        // Inserting Row
        db.insert(TABLE_SCHEDULE_DAY1, null, values);
        db.close();
    }

    public List<Event> getScheduleDay1Items() {

        List<Event> eventList = new ArrayList<Event>();
        String selectQuery = "SELECT  * FROM " + TABLE_SCHEDULE_DAY1;

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

        if (cursor.moveToFirst()) {
            do {
                Event event = new Event(cursor.getInt(0), cursor.getString(1),
                        cursor.getString(2), cursor.getString(3), cursor.getString(4), cursor.getString(5), cursor.getString(6),
                        cursor.getString(7), "", "");
                eventList.add(event);
            } while (cursor.moveToNext());
        }
        return eventList;
    }

    public void deleteScheduleDay1() {
        SQLiteDatabase db = this.getWritableDatabase();
        db.execSQL("DELETE FROM " + TABLE_SCHEDULE_DAY1);
    }


    public void addScheduleDay2Item(Event event) {

        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(EVENT_ID, event.getEventId());
        values.put(EVENT_NAME, event.getName());
        values.put(EVENT_DESCRIPTION, event.getDescription());
        values.put(EVENT_TYPE, event.getType());
        values.put(EVENT_DATE, event.getDate());
        values.put(EVENT_TIME, event.getTime());
        values.put(EVENT_VENUE, event.getVenue());
        values.put(EVENT_IMAGE_URL, event.getImage_url());
        // Inserting Row
        db.insert(TABLE_SCHEDULE_DAY2, null, values);
        db.close();
    }

    public List<Event> getScheduleDay2Items() {
        List<Event> eventList = new ArrayList<Event>();
        String selectQuery = "SELECT  * FROM " + TABLE_SCHEDULE_DAY2;

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

        if (cursor.moveToFirst()) {
            do {
                Event event = new Event(cursor.getInt(0), cursor.getString(1),
                        cursor.getString(2), cursor.getString(3), cursor.getString(4), cursor.getString(5), cursor.getString(6),
                        cursor.getString(7), "", "");
                eventList.add(event);
            } while (cursor.moveToNext());
        }
        return eventList;
    }

    public void deleteScheduleDay2() {
        SQLiteDatabase db = this.getWritableDatabase();
        db.execSQL("DELETE FROM " + TABLE_SCHEDULE_DAY2);
    }


    public void addMyEvent(MyEvent myEvent) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(MY_EVENTS_ID, myEvent.getId());
        values.put(MY_EVENTS_EVENT_NAME, myEvent.getEventName());
        values.put(MY_EVENTS_EVENT_DATE, myEvent.getEventDate());
        // Inserting Row
        db.insert(TABLE_MY_EVENTS, null, values);
        db.close(); // Closing database connection
        Log.e("my event:", "stored in db.");
    }

    public List<MyEvent> getAllMyEvents() {
        List<MyEvent> myEventsList = new ArrayList<MyEvent>();
        String selectQuery = "SELECT  * FROM " + TABLE_MY_EVENTS + " ORDER BY " + MY_EVENTS_ID;
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);
        if (cursor.moveToFirst()) {
            do {
                MyEvent myEvent = new MyEvent(cursor.getInt(0), cursor.getString(1), cursor.getString(2));
                myEventsList.add(myEvent);
            } while (cursor.moveToNext());
        }
        return myEventsList;
    }

    public void deleteAllMyEvents() {
        SQLiteDatabase db = this.getWritableDatabase();
        db.execSQL("DELETE FROM " + TABLE_MY_EVENTS);
    }


    public void addNotification(Notifications notification) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(NOTIFICATION_ID, notification.getId());
        values.put(NOTIFICATION_TITLE, notification.getTitle());
        values.put(NOTIFICATION_MESSAGE, notification.getMessage());
        values.put(NOTIFICATION_IMAGE_URL, notification.getImageUrl());
        values.put(NOTIFICATION_CREATED_AT, notification.getCreatedAt());
        // Inserting Row
        db.insert(TABLE_NOTIFICATIONS, null, values);
        db.close(); // Closing database connection
    }

    public Notifications getNotification(int id) {
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = db.query(TABLE_NOTIFICATIONS, new String[]{NOTIFICATION_ID,
                        NOTIFICATION_TITLE, NOTIFICATION_MESSAGE,
                        NOTIFICATION_IMAGE_URL, NOTIFICATION_CREATED_AT,}, NOTIFICATION_ID + "=?",
                new String[]{String.valueOf(id)}, null, null, null, null);
        if (cursor != null)
            cursor.moveToFirst();

        Notifications notifications = new Notifications(Integer.parseInt(cursor.getString(0)),
                cursor.getString(1), cursor.getString(2), cursor.getString(3),
                cursor.getString(4));

        return notifications;
    }

    public ArrayList<Notifications> getAllNotifications() {
        ArrayList<Notifications> notificationsList = new ArrayList<Notifications>();
        // Select All Query
        String selectQuery = "SELECT  * FROM " + TABLE_NOTIFICATIONS + " ORDER BY " + NOTIFICATION_CREATED_AT + " DESC";

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

        // looping through all rows and adding to list
        if (cursor.moveToFirst()) {
            do {
                Notifications notification = new Notifications(Integer.parseInt(cursor.getString(0)),
                        cursor.getString(1), cursor.getString(2), cursor.getString(3),
                        cursor.getString(4));

                // Adding notification to list
                notificationsList.add(notification);
            } while (cursor.moveToNext());
        }
        // return notification list
        return notificationsList;
    }

    public int getNotificationsCount() {
        String countQuery = "SELECT  * FROM " + TABLE_NOTIFICATIONS;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(countQuery, null);
        int count = cursor.getCount();
        cursor.close();

        // return count
        return count;
    }

    public void refreshNotifications(ArrayList<Notifications> notificationsArrayList) {
        deleteAllNotifications();
        for (Notifications notification : notificationsArrayList)
            addNotification(notification);
    }

    public int updateNotification(Notifications notification) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(NOTIFICATION_ID, notification.getId());
        values.put(NOTIFICATION_TITLE, notification.getTitle());
        values.put(NOTIFICATION_MESSAGE, notification.getMessage());
        values.put(NOTIFICATION_IMAGE_URL, notification.getImageUrl());
        values.put(NOTIFICATION_CREATED_AT, notification.getCreatedAt());

        // updating row
        return db.update(TABLE_NOTIFICATIONS, values, NOTIFICATION_ID + " = ?",
                new String[]{String.valueOf(notification.getId())});
    }

    public void deleteAllNotifications() {
        SQLiteDatabase db = this.getWritableDatabase();
        db.execSQL("DELETE FROM " + TABLE_NOTIFICATIONS);
    }

    public void deleteNotification(Notifications notification) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(TABLE_NOTIFICATIONS, NOTIFICATION_ID + " = ?",
                new String[]{String.valueOf(notification.getId())});
        db.close();
    }

    public boolean notificationAlreadyPresent(Notifications notification) {
        SQLiteDatabase db = this.getReadableDatabase();
        String Query = "SELECT * FROM " + TABLE_NOTIFICATIONS + " WHERE "
                + NOTIFICATION_ID + " = " + notification.getId();
        Cursor cursor = db.rawQuery(Query, null);
        if (cursor.getCount() <= 0) {
            cursor.close();
            return false;
        }
        cursor.close();
        return true;
    }


    public void dropDB() {
        SQLiteDatabase db = this.getWritableDatabase();
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NOTIFICATIONS);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_ATTRACTIONS);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_GALLERY);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_FUNEVENTS);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_MANAGERIALEVENTS);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_TECHNICALVENTS);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_ROBOTICS);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_CONTACTS);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_SCHEDULE_DAY1);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_SCHEDULE_DAY2);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_MY_EVENTS);
        onCreate(db);
    }
}