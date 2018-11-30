package e.roel.journal;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

public class DetailActivity extends AppCompatActivity {

    String tag = "DetailActivity";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Log.d(tag, "onCreate");
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        // Retrieve the JournalEntry object containing the data to be displayed on screen
        Intent intent = getIntent();
        JournalEntry retrievedJournalEntry = (JournalEntry) intent.getSerializableExtra("clickedEntry");

        // Find the textviews and fill the with the appropriate content
        TextView title = findViewById(R.id.title);
        TextView content = findViewById(R.id.content);
        title.setText(retrievedJournalEntry.getTitle());
        content.setText(retrievedJournalEntry.getContent());

    }
}
