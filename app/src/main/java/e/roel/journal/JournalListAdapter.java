package e.roel.journal;

import android.content.Context;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

public class JournalListAdapter extends ArrayAdapter<JournalEntry> {
    String tag = "JournalListAdapter";

    private ArrayList<JournalEntry> entryList;

    public JournalListAdapter(Context context, int resource, ArrayList<JournalEntry> entryList) {
        super(context, resource, entryList);
        this.entryList = entryList;


    }

    @Override
    public View getView(int position, @Nullable View convertView, ViewGroup parent) {
        Log.d(tag, "in getView()");
        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.entry_row, parent, false);
        }

        // Get the journal entry at this position
        JournalEntry thisEntry = entryList.get(position);

        // Set mood and title etc of this entry to the values stored in the e.roel.journal.JournalEntry object
        ImageView mood = convertView.findViewById(R.id.moodImage);
        TextView title = convertView.findViewById(R.id.title);
        TextView dateTime = convertView.findViewById(R.id.dateTime);
        //mood.setImageResource(thisEntry.getMood());
        title.setText(thisEntry.getTitle());
        dateTime.setText(thisEntry.getDate());

        return convertView;
    }

}
