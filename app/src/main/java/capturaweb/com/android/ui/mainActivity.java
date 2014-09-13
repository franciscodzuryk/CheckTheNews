package capturaweb.com.android.ui;

import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.os.Bundle;

import android.os.Environment;
import android.view.Menu;
import android.view.MenuItem;

import android.view.View;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.content.Intent;
import capturaweb.com.android.capturaweb.R;


import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;

public class mainActivity extends Activity {
    public int imgID;

    //views
    public TextView mTextView;
    public EditText mEditText;
    public WebView mWebView;
    public ImageView mImageView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //mTextView = (TextView) findViewById(R.id.mTextView);
        mEditText = (EditText) findViewById(R.id.mEditText);
        mWebView = (WebView) findViewById(R.id.mWebView);
        mWebView.setWebViewClient(new WebViewClient());
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main_activity_actionbar, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        switch (item.getItemId()) {
            case R.id.action_capure:
                openViewImage();
                return true;
            case R.id.action_favorite:
                openFavorite();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    /* Checks if external storage is available for read and write */
    public boolean isExternalStorageWritable() {
        String state = Environment.getExternalStorageState();
        if (Environment.MEDIA_MOUNTED.equals(state)) {
            return true;
        }
        return false;
    }
    /* Checks if external storage is available to at least read |   NO ESTA EN USO*/
    public boolean isExternalStorageReadable() {
        String state = Environment.getExternalStorageState();
        if (Environment.MEDIA_MOUNTED.equals(state) ||
                Environment.MEDIA_MOUNTED_READ_ONLY.equals(state)) {
            return true;
        }
        return false;
    }

    public void getBitmap() {
        mWebView.layout(0, 0, 500, 500);
        Bitmap bm = Bitmap.createBitmap(500, 500, Bitmap.Config.ARGB_8888);
        Canvas c = new Canvas(bm);
        mWebView.draw(c);
        c.drawBitmap(bm, 0, 0, null);
        mImageView = (ImageView) findViewById(R.id.mImageView);
        mImageView.setImageBitmap(bm);/*
        if (isExternalStorageWritable()) {
            OutputStream stream = null;
            try {
                stream = new FileOutputStream(Environment.getExternalStorageDirectory()+"/Pictures"+imgID+".png");
                bm.compress(Bitmap.CompressFormat.PNG, 80, stream);
                if (stream != null) {
                    stream.close();
                    imgID++;
                }
            } catch (IOException e) {
            } finally {
                bm.recycle();
            }
        }*/
    }

    public void cargarURL(View view) {
        String url = mEditText.getText().toString();
        url = "http://" + url;
        mWebView.loadUrl(url);
        mWebView.setWebViewClient(new WebViewClient() {

            public void onPageFinished(WebView view, String url) {
                getBitmap();
            }
        });
    }

    public void closeImg(View view) {
        mImageView.setImageBitmap(null);
    }

    private void openFavorite() {
        /*Intent intent = new Intent(this, FavoriteListActivity.class);
        this.startActivity(intent);*/
    }

    private void openViewImage() {
        Intent intent = new Intent(this, view_image_activity.class);
        this.startActivity(intent);
    }
}