package com.technocracy.app.aavartan.activity;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Rect;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuItem;

import com.nightonke.boommenu.BoomButtons.OnBMClickListener;
import com.nightonke.boommenu.BoomButtons.TextOutsideCircleButton;
import com.nightonke.boommenu.BoomMenuButton;
import com.technocracy.app.aavartan.Attraction.View.AttractionActivity;
import com.technocracy.app.aavartan.Contacts.View.ContactsActivity;
import com.technocracy.app.aavartan.Event.View.EventListActivity;
import com.technocracy.app.aavartan.R;
import com.technocracy.app.aavartan.Schedule.View.ScheduleActivity;
import com.technocracy.app.aavartan.Sponsors.View.SponsActivity;
import com.technocracy.app.aavartan.api.User;
import com.technocracy.app.aavartan.gallery.View.GalleryActivity;
import com.technocracy.app.aavartan.helper.BottomNavigationViewHelper;
import com.technocracy.app.aavartan.helper.SQLiteHandler;
import com.technocracy.app.aavartan.helper.SessionManager;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Timer;
import java.util.TimerTask;

public class MainActivity extends AppCompatActivity {
    private static final int ACTIVITY_NUM = 0;
    private SessionManager sessionManager;
    private String currentDateString;
    private SimpleDateFormat dateFormat;
    private SQLiteHandler sqLiteHandler;
    private Intent intent;
    private String intent_name[] = {"Gallery", "Sponsors", "Contacts", "App Team", "About Us", "Vigyaan"};
    private int icons[] = {R.drawable.ic_photo_library_black_24dp, R.drawable.spons, R.drawable.ic_account_box_24dp,
            R.drawable.ic_group_black_24dp, R.drawable.ic_accessibility_black_24dp, R.drawable.ic_highlight_black_24dp};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        final Typewriter tv = (Typewriter) findViewById(R.id.typewriter);
        final Typewriter tv1 = (Typewriter) findViewById(R.id.typewriter1);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        Timer timer = new Timer();
        timer.scheduleAtFixedRate(new MyTimerTask(), 1200, 5000);
        dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        currentDateString = dateFormat.format(Calendar.getInstance().getTime());
        sessionManager = new SessionManager(getApplicationContext());
        if (sessionManager.isLoggedIn()) {
            sqLiteHandler = new SQLiteHandler(getApplicationContext());
        }
        SessionManager sessionManager = new SessionManager(getApplicationContext());
        if (sessionManager.isLoggedIn()) {
            SQLiteHandler sqLiteHandler = new SQLiteHandler(getApplicationContext());
            User user = sqLiteHandler.getUser();
        }
        final BottomNavigationView bottomNavigationView = (BottomNavigationView) findViewById(R.id.bottom_navigation);
        BottomNavigationViewHelper.removeShiftMode(bottomNavigationView);

        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.btn1:
                        break;
                    case R.id.btn2:
                        intent = new Intent(MainActivity.this, EventListActivity.class);
                        startActivity(intent);
                        overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);
                        break;
                    case R.id.btn3:
                        intent = new Intent(MainActivity.this, AttractionActivity.class);
                        startActivity(intent);
                        overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);
                        break;
                    case R.id.btn4:
                        intent = new Intent(MainActivity.this, AccountActivity.class);
                        startActivity(intent);
                        overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);
                        break;
                    case R.id.btn5:
                        intent = new Intent(MainActivity.this, ScheduleActivity.class);
                        startActivity(intent);
                        overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);
                        break;
                }
                return true;
            }
        });
        BoomMenuButton bmb = (BoomMenuButton) findViewById(R.id.bmb);
        for (int i = 0; i < bmb.getPiecePlaceEnum().pieceNumber(); i++) {
            TextOutsideCircleButton.Builder builder = new TextOutsideCircleButton.Builder()
                    .listener(new OnBMClickListener() {
                        @Override
                        public void onBoomButtonClick(int index) {
                            Intent intent2;
                            if (index == 0) {
                                intent2 = new Intent(MainActivity.this, GalleryActivity.class);
                                startActivity(intent2);
                                overridePendingTransition(R.anim.slide_up, R.anim.slide_down);
                              //  Snackbar.make(findViewById(R.id.relativeLayout_main), "This feature is currently unavailable!App will be updated soon", Snackbar.LENGTH_LONG)
                               //         .setAction("Action", null).show();
                            } else if (index == 1) {
                                intent2 = new Intent(MainActivity.this, SponsActivity.class);
                                startActivity(intent2);
                                overridePendingTransition(R.anim.slide_up, R.anim.slide_down);

                            } else if (index == 2) {
                                intent2 = new Intent(MainActivity.this, ContactsActivity.class);
                                intent2.putExtra("contact_type", "1");
                                startActivity(intent2);
                                overridePendingTransition(R.anim.slide_up, R.anim.slide_down);

                            } else if (index == 3) {
                                intent2 = new Intent(MainActivity.this, ContactsActivity.class);
                                intent2.putExtra("contact_type", "2");
                                startActivity(intent2);
                                overridePendingTransition(R.anim.slide_up, R.anim.slide_down);
                            } else if (index == 4) {
                                intent2 = new Intent(MainActivity.this, AboutUsActivity.class);
                                startActivity(intent2);
                                overridePendingTransition(R.anim.slide_up, R.anim.slide_down);
                            } else if (index == 5) {
                                intent2 = new Intent(MainActivity.this, VigyaanActivity.class);
                                startActivity(intent2);
                                overridePendingTransition(R.anim.slide_up, R.anim.slide_down);
                            }
                        }
                    })
                    .normalImageRes(icons[i])
                    .normalText(intent_name[i])
                    .rotateImage(true)
                    .shadowEffect(true)
                    .imagePadding(new Rect(2, 2, 2, 2))
                    .textGravity(Gravity.CENTER)
                    .rippleEffect(true).normalColor(R.color.white).textGravity(Gravity.CENTER).textSize(15).maxLines(2);
            bmb.addBuilder(builder);
        }

        Menu menu = bottomNavigationView.getMenu();
        MenuItem menuItem = menu.getItem(ACTIVITY_NUM);
        menuItem.setChecked(true);
    }


    @Override
    public void onBackPressed() {

        new AlertDialog.Builder(this).setIcon(R.drawable.ic_dialog_alert).setTitle("Exit")
                .setMessage("Are you sure ?")
                .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Intent intent = new Intent(Intent.ACTION_MAIN);
                        intent.addCategory(Intent.CATEGORY_HOME);
                        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
                        startActivity(intent);
                        finish();
                    }
                }).setNegativeButton("No", null).show();
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.actionbarbutton, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        //noinspection SimplifiableIfStatement
        if (id == R.id.action_notification) {
            Intent intent = new Intent(MainActivity.this, NotificationsActivity.class);
            MainActivity.this.startActivity(intent);
        }
        if (id == R.id.map) {
            Intent intent = new Intent(MainActivity.this, MapsActivity.class);
            MainActivity.this.startActivity(intent);
        }
        return false;
    }

    public class MyTimerTask extends TimerTask {

        @Override
        public void run() {
            MainActivity.this.runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    final Typewriter tv = (Typewriter) findViewById(R.id.typewriter);
                    final Typewriter tv1 = (Typewriter) findViewById(R.id.typewriter1);
                    tv.setText(" ");
                    tv.setCharacterDelay(150);
                    tv.animateText("IMAGINE IMPROVE IMPLEMENT");
                    tv1.setText(" ");
                    tv1.setCharacterDelay(150);
                    tv1.animateText("ERA OF DIGITALIZATION");
                }
            });
        }
    }
}