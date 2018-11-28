package e.roel.journal;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

public class InputActivity extends AppCompatActivity {

    // for debugging purposes
    String tag = "InputActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_input);
    }
    // Change activitys and save data to databas on click of a mood button
    public void moodClicked(View view) {
        Log.d(tag, "moodclicked");
        Intent intent = new Intent(InputActivity.this, MainActivity.class);
        startActivity(intent);
    }
}
