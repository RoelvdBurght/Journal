package e.roel.journal;

import android.content.Intent;
import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.ContextMenu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.Arrays;

public class MainActivity extends AppCompatActivity {

    // for debugging purposes
    String tag = "MainActivity";

    private EntryDatabase db;
    private EntryAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Instanciate the correct database and adapter for the JournalEntrys
        db = EntryDatabase.getInstance(getApplicationContext());
        adapter = new EntryAdapter(this, db.selectAll());

        // Set itemclicklistener and contextmenu for longclicks, also connect the adapter
        ListView entryList = findViewById(R.id.entryList);
        entryList.setOnItemClickListener(new ListItemClickListener());
        entryList.setAdapter(adapter);
        registerForContextMenu(entryList);

    }

    // Clicklistener for the listView
    private class ListItemClickListener implements AdapterView.OnItemClickListener {

        // Find the title, content, mood, and date of the clicked entry in the database
        // and create a new journalEntry object with these arguments to pass to the adapter
        @Override
        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
            Cursor cursor = (Cursor) parent.getItemAtPosition(position);

            int titleIndex = cursor.getColumnIndex("title");
            String title = cursor.getString(titleIndex);

            int contentIndex = cursor.getColumnIndex("content");
            String content = cursor.getString(contentIndex);

            int moodIndex = cursor.getColumnIndex("mood");
            int mood = cursor.getInt(moodIndex);

            JournalEntry clickedEntry = new JournalEntry(title, content, "10-10-10", mood);

            Intent intent = new Intent(MainActivity.this, DetailActivity.class);
            intent.putExtra("clickedEntry", clickedEntry);
            startActivity(intent);
        }
    }

    // Method to create contextmenu and inflate the correct xml file to it
    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);
        if (v.getId() == R.id.entryList) {
            getMenuInflater().inflate(R.menu.longclickmenu, menu);
        }
    }

    @Override
    public boolean onContextItemSelected(MenuItem item) {

        // Find index of selected journalentry
        AdapterView.AdapterContextMenuInfo info = (AdapterView.AdapterContextMenuInfo) item.getMenuInfo();
        int index = info.position;

        // Create cursor object
        ListView entryList = findViewById(R.id.entryList);
        Cursor cursor = (Cursor) entryList.getItemAtPosition(index);

        // Switch on the options from the context menu
        switch (item.getItemId()) {

            // If read is chosen, proceed with the same procedure as a normal shortclick
            case R.id.read :

                // Find correct data in database and save it in JournalEntryObejct
                int titleIndex = cursor.getColumnIndex("title");
                String title = cursor.getString(titleIndex);
                int contentIndex = cursor.getColumnIndex("content");
                String content = cursor.getString(contentIndex);
                int moodIndex = cursor.getColumnIndex("mood");
                int mood = cursor.getInt(moodIndex);
                JournalEntry clickedEntry = new JournalEntry(title, content, "10-10-10", mood);

                // Make intent and add the journalEntryObject to it, then start new activity
                Intent intent = new Intent(MainActivity.this, DetailActivity.class);
                intent.putExtra("clickedEntry", clickedEntry);
                startActivity(intent);
                break;

            // If delete is chosen, remove the selected journalentry from the database
            case R.id.delete :

                // Find the database instance and remove the row with the index of the clicked item
                EntryDatabase db = EntryDatabase.getInstance(getApplicationContext());
                int columnIndex = cursor.getColumnIndex("_id");
                long _id = cursor.getLong(columnIndex);
                db.delete(_id);
                updateData();
                break;
        }
        return super.onContextItemSelected(item);
    }

    // Listener for the floating button, intent directs to new entry activity
    public void floatingButtonClick(View view) {
        Intent intent = new Intent(MainActivity.this, InputActivity.class);
        startActivity(intent);
    }

    // Updates the screen, should be called when an entry is deleted
    private void updateData() {
        adapter.swapCursor(db.selectAll());
    }
}
