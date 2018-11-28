package e.roel.journal;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.Arrays;

public class MainActivity extends AppCompatActivity {

    // for debugging purposes
    String tag = "MainActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Find the listView and start listening for clicks
        ListView listview = findViewById(R.id.entryList);
        listview.setOnItemClickListener(new ListItemClickListener());

        // Make and fill arraylist with journalentry objects
        ArrayList<JournalEntry> journalEntryList = new ArrayList<>();

        JournalEntry entry1 = new JournalEntry("een title",
                "al die content  blblalbal",
                "28-11-2018",
                2);
        JournalEntry entry2 = new JournalEntry("een title",
                "al die content  blblalbal",
                "28-11-2018",
                2);
        JournalEntry entry3 = new JournalEntry("een title",
                "al die content  blblalbal",
                "28-11-2018",
                2);
        JournalEntry entry4 = new JournalEntry("een title",
                "al die content  blblalbal",
                "28-11-2018",
                2);
        JournalEntry entry5 = new JournalEntry("een title",
                "al die content  blblalbal",
                "28-11-2018",
                2);
        JournalEntry entry6 = new JournalEntry("een title",
                "al die content  blblalbal",
                "28-11-2018",
                2);
        JournalEntry entry7 = new JournalEntry("een title",
                "al die content  blblalbal",
                "28-11-2018",
                2);
        JournalEntry entry8 = new JournalEntry("een title",
                "al die content  blblalbal",
                "28-11-2018",
                2);

        journalEntryList.add(entry1);
        journalEntryList.add(entry2);
        journalEntryList.add(entry3);
        journalEntryList.add(entry4);
        journalEntryList.add(entry5);
        journalEntryList.add(entry6);
        journalEntryList.add(entry7);
        journalEntryList.add(entry8);

        // Make and set an adapter for the listview
        JournalListAdapter adapter = new JournalListAdapter(this, R.layout.entry_row, journalEntryList);
        listview.setAdapter(adapter);

    }

    // Clicklistener for the listView
    private class ListItemClickListener implements AdapterView.OnItemClickListener {

        @Override
        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
            JournalEntry clickedEntry = (JournalEntry) parent.getItemAtPosition(position);
            Intent intent = new Intent(MainActivity.this, DetailActivity.class);
            intent.putExtra("clickedEntry", clickedEntry);
            startActivity(intent);
        }
    }

    public void floatingButtonClick(View view) {
        Intent intent = new Intent(MainActivity.this, InputActivity.class);
        startActivity(intent);
    }
}
