package e.roel.journal;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.*;
import java.text.*;

public class InputActivity extends AppCompatActivity {

    // for debugging purposes
    String tag = "InputActivity";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_input);

        // Set the date in the date textview to today
        Date dNow = new Date();
        SimpleDateFormat ft = new SimpleDateFormat("dd-MM-yyyy",  Locale.getDefault());
        TextView dateView = findViewById(R.id.date);
        dateView.setText(ft.format(dNow));
        TextView timeView = findViewById(R.id.time);
        ft = new SimpleDateFormat("H:m", Locale.getDefault());
        timeView.setText(ft.format(dNow));


    }
    // Change activitys and save data to database on click of a mood button
    public void addEntry(View view) {

        // Find relevant views
        TextView titleView = findViewById(R.id.title);
        TextView dateView = findViewById(R.id.date);
        TextView timeView = findViewById(R.id.time);
        TextView contentView = findViewById(R.id.content);
        String buttonName = getResources().getResourceEntryName(view.getId());

        // Find values in views
        String title = titleView.getText().toString();
        CharSequence date = dateView.getText();
        CharSequence time = timeView.getText();
        String dateTime = date.toString() + " @ " + time.toString();


        String content = contentView.getText().toString();

        // The button pressed determines the mood, so here we switch on the possible names of
        // the buttons to find the int corresponding to the selected mood
        int moodId = 0;
        switch (buttonName) {
            case "imageButton" :
                moodId = 1;
                break;
            case "imageButton2" :
                moodId = 2;
                break;
            case "imageButton3" :
                moodId = 3;
                break;
            case "imageButton4" :
                moodId = 4;
                break;
        }
        JournalEntry je = new JournalEntry(title, content, dateTime, moodId);

        EntryDatabase db = EntryDatabase.getInstance(getApplicationContext());
        db.insert(je);
        Intent intent = new Intent(InputActivity.this, MainActivity.class);
        startActivity(intent);
    }
}
