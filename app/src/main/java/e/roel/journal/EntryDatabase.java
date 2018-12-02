package e.roel.journal;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.lang.reflect.Array;

public class EntryDatabase extends SQLiteOpenHelper {

    String tag = "EntryDatabase";
    // Table columns
    private static final String TABLE_NAME = "entries";
    private final String ID = "_id";
    private final String TITLE = "title";
    private final String CONTENT = "content";
    private final String MOOD = "mood";
    private final String TIMESTAMP = "time_stamp";
    private final String TEXT = " TEXT ";
    private final String INT = " INTEGER ";

    // Temporary filler for the table
    private final String TITLE_PLACEHOLDER = "dit is een titel";
    private final String CONTENT_PLACEHOLDER = "Bacon";
    private final int MOOD_PLACEHOLDER = 1;
    private final String TIMESTAMP_PLACEHOLDER = "tijd";
    private static EntryDatabase instance;

    // Constructor
    private EntryDatabase(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    // Returns the database instance
    public static EntryDatabase getInstance(Context context) {
        if (instance == null) {
            instance = new EntryDatabase(context, TABLE_NAME, null, 1);
        }
        return instance;
    }

    //  Creates a new database and fills it with placeholder columns
    @Override
    public void onCreate(SQLiteDatabase db) {
        Log.d(tag, "hoi");

        // Create table
        final String CREATE = "CREATE TABLE " + TABLE_NAME + " ( " + ID +
                " INTEGER PRIMARY KEY AUTOINCREMENT" + ", " + TITLE + TEXT + ", " + CONTENT + TEXT + ", "
                + MOOD + INT + ", " + TIMESTAMP + TEXT + ");";

        db.execSQL(CREATE);
        // Loop to add rows to the table
        int numberOfRows = 12;
        for (int i = 0; i < numberOfRows; i++) {
            Log.d(tag, " " + i);
            // Fill the table
            ContentValues cv = new ContentValues();
            cv.put(TITLE, TITLE_PLACEHOLDER);
            cv.put(CONTENT, CONTENT_PLACEHOLDER);
            cv.put(MOOD, MOOD_PLACEHOLDER);
            cv.put(TIMESTAMP, TIMESTAMP_PLACEHOLDER);

            db.insert(TABLE_NAME, null, cv);
        }
    }

    // Drop the existing table and create a new empty one
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        final String DROP = "DROP TABLE IF EXISTS entryTable";
        onCreate(db);
    }

    public Cursor selectAll() {
        SQLiteDatabase database = getWritableDatabase();
        Cursor cursor = database.rawQuery("SELECT * FROM " + TABLE_NAME, null);
        return cursor;
    }

    public void insert(JournalEntry je) {
        SQLiteDatabase db = getWritableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM " + TABLE_NAME, null); // kan weg?
        ContentValues cv = new ContentValues();
        cv.put(TITLE, je.getTitle());
        cv.put(CONTENT, je.getContent());
        cv.put(MOOD, je.getMood());
        cv.put(TIMESTAMP, je.getDate());

        db.insert(TABLE_NAME, null, cv);
    }

    public void delete(long id) {
        SQLiteDatabase db = getWritableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM " + TABLE_NAME, null); // kan weg?
        db.delete(TABLE_NAME, ID + " = " + id, null);
    }
}
