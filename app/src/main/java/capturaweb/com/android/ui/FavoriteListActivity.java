package capturaweb.com.android.ui;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ListView;
import java.util.ArrayList;
import java.util.List;
import capturaweb.com.android.capturaweb.R;
import capturaweb.com.android.persistence.FavoriteDAO;
import capturaweb.com.android.persistence.SQLiteCapture;

public class FavoriteListActivity extends Activity {

    private ListView listView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.setContentView(R.layout.activity_favorite);
        // Habilitar el UpButton
        this.getActionBar().setDisplayHomeAsUpEnabled(true);

        SQLiteCapture db = new SQLiteCapture(this);
        FavoriteDAO favoriteDAO = new FavoriteDAO(db);
        favoriteDAO.insert("www.google.com");
        favoriteDAO.insert("www.yahoo.com");

        listView = (ListView) findViewById(R.id.listview_favorite);
        List<String> lista= new ArrayList<String>();
        lista.add("www.google.com");
        lista.add("www.yahoo.com");
        lista.add("www.youtube.com");

        this.listView.setAdapter(new AdapterListFavorite(this, lista));
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.favorite, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
