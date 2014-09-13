package capturaweb.com.android.persistence;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;
import java.util.Vector;
import capturaweb.com.android.model.FavoriteBean;


public class FavoriteDAO {

    private SQLiteCapture capture;


    public FavoriteDAO(SQLiteCapture db) {
        capture = db;
        SQLiteDatabase database = capture.getWritableDatabase();
        database.close();
    }

    public void insert (String url){
        String insertSQL = " INSERT INTO favorites VALUES(null, '" + url + "')";
        // Toma la db en modo de escritura
        SQLiteDatabase database = capture.getWritableDatabase();
        database.execSQL(insertSQL);
        database.close();
    }

    public List<String> list(){
        String selectSQL = "SELECT * FROM favorites";
        List<String> result = new ArrayList<String>();
        // Toma la db en modo de lectura
        SQLiteDatabase database = capture.getReadableDatabase();
        // rawQuery nos permite hacer una consulta. (String sql, String[] paramentros )
        Cursor cursor = database.rawQuery(selectSQL, null);
        while(cursor.moveToNext()){
            //FavoriteBean favorite = new FavoriteBean();
            //favorite.getId(cursor.getInt(0));
            //favorite.getUrl(cursor.getString(1));
            //result.add(favorite);
            result.add(cursor.getString(1));
        }
        cursor.close();
        database.close();
        return result;
    }

}
