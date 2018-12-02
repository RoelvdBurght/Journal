package e.roel.journal;

import android.content.Context;
import android.database.Cursor;
import android.text.Layout;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.ResourceCursorAdapter;
import android.widget.TextView;

import e.roel.journal.R;

public class EntryAdapter extends ResourceCursorAdapter {

    String tag = "EntryAdapter";
    public EntryAdapter(Context context, Cursor cursor) {
        super(context, R.layout.entry_row, cursor);
    }

    @Override
    public void bindView(View view, Context context, Cursor cursor) {

        // Get content to display from database
        int titleIndex = cursor.getColumnIndex("title");
        int contentIndex = cursor.getColumnIndex("content");
        int moodIndex = cursor.getColumnIndex("mood");
        int timestampIndex = cursor.getColumnIndex("time_stamp");
        String title = cursor.getString(titleIndex);
        String timestamp = cursor.getString(timestampIndex);
        int mood = cursor.getInt(moodIndex);

        // Set content in correct views
        TextView titleView = view.findViewById(R.id.title);
        titleView.setText(title);
        TextView dateTimeView = view.findViewById(R.id.dateTime);

        dateTimeView.setText(timestamp);

        // Switch on possible mood integers to find correct image to display
        ImageView moodView = view.findViewById(R.id.moodImage);
        switch (mood) {
            case 1:
                moodView.setImageResource(R.raw.smile);
                break;
            case 2:
                moodView.setImageResource(R.raw.laugh);
                break;
            case 3:
                moodView.setImageResource(R.raw.sad);
                break;
            case 4:
                moodView.setImageResource(R.raw.angry);
                break;
        }
    }
}
