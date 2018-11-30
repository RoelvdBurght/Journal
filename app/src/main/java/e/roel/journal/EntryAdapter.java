package e.roel.journal;

import android.content.Context;
import android.database.Cursor;
import android.text.Layout;
import android.util.Log;
import android.view.View;
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
        Log.d(tag, "bindview");
        Log.d(tag, cursor.toString());
        int titleIndex = cursor.getColumnIndex("title");
        int contentIndex = cursor.getColumnIndex("content");
        int moodIndex = cursor.getColumnIndex("mood");
        int timestampIndex = cursor.getColumnIndex("timestamp");

        String title = cursor.getString(titleIndex);
        TextView titleView = view.findViewById(R.id.title);
        titleView.setText(title);
    }
}
