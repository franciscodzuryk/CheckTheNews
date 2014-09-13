package capturaweb.com.android.ui;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import com.facebook.UiLifecycleHelper;

import capturaweb.com.android.capturaweb.R;



public class ViewImage extends Activity {

    private UiLifecycleHelpercleHelper uiHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.view_image_activity);
        // Habilitar el UpButton
        getActionBar().setDisplayHomeAsUpEnabled(true);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.view_image_activity_actionbar, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_capure:
                openUiCompartirFacebook();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    private void openUiCompartirFacebook() {

    }
}
