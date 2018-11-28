import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class EntryDatabase extends SQLiteOpenHelper {

    // Table columns
    private final String TABLE_NAME = "entryTable";
    private final String ID = "_id";
    private final String TITLE = "title";
    private final String CONTENT = "content";
    private final String MOOD = "mood";
    private final String TIMESTAMP = "timestamp";
    private final String TEXT = " TEXT ";
    private final String INT = " INTEGER ";

    public EntryDatabase(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        final String CREATE = "create table " + TABLE_NAME + " ( " + ID +
                " INTEGER PRIMARY KEY " + ", " + TITLE + TEXT + ", " + CONTENT + TEXT + ", "
                + MOOD + INT + ", " + TIMESTAMP + TEXT;
        db.execSQL(CREATE);

        titleList = {"Topdag!", "Vervelende dag", "Nieuwe periode begonnen", " "}
        final String = "INSERT INTO " + TABLE_NAME + " ( " +
                                        TITLE + ", " +
                                        CONTENT + ", " +
                                        MOOD + ", " +
                                        TIMESTAMP + ", " +
                                        TEXT + ", " +



        VALUES(value_1,value_2,...);"

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        final String DROP = "DROP TABLE [IF EXISTS] entryTable";
        db.execSQL(DROP);
        final String CREATE = "create table " + TABLE_NAME + " ( " + ID +
                " INTEGER PRIMARY KEY " + ", " + TITLE + TEXT + ", " + CONTENT + TEXT + ", "
                + MOOD + INT + ", " + TIMESTAMP + TEXT;
        db.execSQL(CREATE);
    }
}
