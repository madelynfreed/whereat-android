package org.tlc.whereat.db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class Dao extends SQLiteOpenHelper {

    // FIELDS

    public static final String TABLE_LOCATIONS = "locations";
    public static final String COLUMN_ID = "_id";
    public static final String COLUMN_LAT = "lat";
    public static final String COLUMN_LON = "lon";
    public static final String COLUMN_TIME = "time";

    protected static final String DB_NAME = "whereat.db";
    protected static final int DB_VERSION = 1;
    protected static final String DB_CREATE =
        "create table " + TABLE_LOCATIONS + " (" +
            COLUMN_ID + " integer primary key autoincrement, " +
            COLUMN_LAT + " real not null, " +
            COLUMN_LON + " real not null, " +
            COLUMN_TIME + " integer not null);";

    private static Dao sInstance;

    // CONSTRUCTOR

    public static synchronized Dao getInstance(Context ctx){
        if (sInstance == null) sInstance = new Dao(ctx);
        return sInstance;
    }

    private Dao(Context ctx){
        super(ctx, DB_NAME, null, DB_VERSION);
    }

    protected Dao(Context ctx, String dbName){
        super(ctx, dbName, null, DB_VERSION);
    }

    // INTERFACE IMPLEMENTATION

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(DB_CREATE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        String msg = "Upgrading DB from v. " + oldVersion + " to " + newVersion + "; will destroy all old data";
        Log.w(Dao.class.getName(), msg);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_LOCATIONS);
        onCreate(db);
    }
}
