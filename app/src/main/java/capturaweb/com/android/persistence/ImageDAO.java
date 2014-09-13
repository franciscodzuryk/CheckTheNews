package capturaweb.com.android.persistence;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import java.util.Vector;
import capturaweb.com.android.model.FavoriteBean;


public class ImageDAO {

    private SQLiteDatabase database;
    private SQLiteCapture capture;


    public ImageDAO (Context context) {
        // Se crea una instancia de SQLiteCapture
        capture = new SQLiteCapture(context);
    }

    public void insert (String url){
        String insertSQL = " INSERT INTO favorites VALUES(null, '" + url + "')";
        // Toma la db en modo de escritura
        database = capture.getWritableDatabase();
        database.execSQL(insertSQL);
    }

    public Vector<FavoriteBean> list(){
        String selectSQL = "SELECT * FROM favorites";
        Vector<FavoriteBean> result = new Vector<FavoriteBean>();
        // Toma la db en modo de lectura
        database = capture.getReadableDatabase();
        // rawQuery nos permite hacer una consulta. (String sql, String[] paramentros )
        Cursor cursor = database.rawQuery(selectSQL, null);
        while(cursor.moveToNext()){
            FavoriteBean favorite = new FavoriteBean();
            favorite.getId(cursor.getInt(0));
            favorite.getUrl(cursor.getString(1));
            result.add(favorite);
        }
        cursor.close();
        return result;
    }
}