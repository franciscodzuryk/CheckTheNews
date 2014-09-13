package capturaweb.com.android.persistence;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.sql.SQLException;

public class SQLiteCapture extends SQLiteOpenHelper{

    private static String DATABASE_PATH = "/data/data/capturaweb.com.android/databases/";
    private static final String DATABASE_NAME = "capture.db";
    private static final Integer DATABASE_VERSION = 1;


    public SQLiteCapture(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        String scriptSQL =
                "CREATE TABLE image (" +
                        "_id INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT," +
                        "ruta TEXT," +
                        "detalle TEXT )";
        sqLiteDatabase.execSQL(scriptSQL);
        scriptSQL =
                "CREATE TABLE favorites (" +
                        "_id INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT," +
                        "url TEXT)";
        sqLiteDatabase.execSQL(scriptSQL);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i2) {
        // NO EXISTE NUEVA VERSION
        System.out.print("La base de datos ya existe");
    }
}