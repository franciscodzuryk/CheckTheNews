package capturaweb.com.android.ui;

import android.app.Activity;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.Signature;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;

import android.util.Base64;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import com.sromku.simple.fb.Permission;
import com.sromku.simple.fb.SimpleFacebook;
import com.sromku.simple.fb.SimpleFacebookConfiguration;
import com.sromku.simple.fb.entities.Feed;
import com.sromku.simple.fb.entities.Photo;
import com.sromku.simple.fb.entities.Score;
import com.sromku.simple.fb.listeners.OnLoginListener;
import com.sromku.simple.fb.listeners.OnNewPermissionsListener;
import com.sromku.simple.fb.listeners.OnPhotosListener;
import com.sromku.simple.fb.listeners.OnPublishListener;
import com.sromku.simple.fb.utils.Logger;

import org.apache.http.ReasonPhraseCatalog;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.List;

import capturaweb.com.android.capturaweb.R;

public class LoginFB extends FragmentActivity {

    protected SimpleFacebook mSimpleFacebook;
    public TextView mTextView;
    private static final String app_id = "1482662632002146";
    private static final String app_namespace = "checkthenews_nsp";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_fb);
        mSimpleFacebook = SimpleFacebook.getInstance(this);
        /** Log de Key Hash **/
        try {
            PackageInfo info = getPackageManager()
                .getPackageInfo(
                        "capturaweb.com.android.capturaweb",
                        PackageManager.GET_SIGNATURES
                );
            for (Signature signature : info.signatures) {
                MessageDigest md;
                md = MessageDigest.getInstance("SHA");
                md.update(signature.toByteArray());
                String something = new String(Base64.encode(md.digest(), 0));
                //String something = new String(Base64.encodeBytes(md.digest()));
                Log.e("hash key", something);
            }
        } catch (PackageManager.NameNotFoundException e1) {
            Log.e("name not found", e1.toString());
        } catch (NoSuchAlgorithmException e) {
            Log.e("no such an algorithm", e.toString());
        } catch (Exception e) {
            Log.e("exception", e.toString());
        }
        /** Log de Key Hash **/

        Permission[] permissions = new Permission[] {
                Permission.USER_PHOTOS,
                Permission.EMAIL,
                Permission.PUBLISH_ACTION
        };

        SimpleFacebookConfiguration configuration = new SimpleFacebookConfiguration.Builder()
                .setAppId(app_id)
                .setNamespace(app_namespace)
                .setPermissions(permissions)
                .build();

        mSimpleFacebook.setConfiguration(configuration);
    }

    @Override
    public void onResume() {
        super.onResume();
        mSimpleFacebook = SimpleFacebook.getInstance(this);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.login_fb, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        mSimpleFacebook.onActivityResult(this, requestCode, resultCode, data);
        super.onActivityResult(requestCode, resultCode, data);
    }

    /** Login Listener **/
    OnLoginListener onLoginListener = new OnLoginListener() {
        @Override
        public void onLogin() {
            // change the state of the button or do whatever you want
            Log.i("asd", "Logged in");
        }

        @Override
        public void onNotAcceptingPermissions(Permission.Type type) {
            // user didn't accept READ or WRITE permission
            Log.i("asd", "You didn't accept permissions");
        }

        @Override
        public void onThinking() {
        }
        @Override
        public void onException(Throwable throwable) {
        }
        @Override
        public void onFail(String reason) {
        }
    };

    /** Publish Listener **/
    OnPublishListener onPublishListener = new OnPublishListener() {
        @Override
        public void onComplete(String postId) {
            Log.i("asd", "Published successfully. The new post id = " + postId);
        }
        @Override
        public void onFail(String reason) {
            Log.i("asd", "Not published because of " + reason);
        }
        @Override
        public void onException(Throwable throwable) {
            Log.i("asd", "excepcion "+throwable.getMessage());
        }
    };

    /** Permission Listener **/
    OnNewPermissionsListener onNewPermissionsListener = new OnNewPermissionsListener() {
        @Override
        public void onSuccess(String accessToken) {
            // updated access token
        }

        @Override
        public void onNotAcceptingPermissions(Permission.Type type) {
            // user didn't accept READ or WRITE permission
            Log.w("asd", String.format("You didn't accept %s permissions", type.name()));
        }
        @Override
        public void onThinking() {
        }
        @Override
        public void onFail(String reason) {
            Log.i("asd", "fail de permisos " + reason);
        }
        @Override
        public void onException(Throwable throwable) {
            Log.i("asd", "excepcion de permisos (?? "+throwable.getMessage());
        }
    };

    public void log(View view) {
        mSimpleFacebook.login(onLoginListener);
    }

    public void requestPermisos(View view) {
        Permission[] permissions = new Permission[] {
                Permission.PUBLISH_ACTION
        };
        boolean showPublish = true;
        mSimpleFacebook.requestNewPermissions(permissions, showPublish, onNewPermissionsListener);
    }

    public void pruebaPost(View view) {
        /*
        Feed feed = new Feed.Builder()
                .setMessage("Clone it out...")
                .setName("Simple Facebook SDK for Android")
                .setCaption("Code less, do the same.")
                .setDescription("Login, publish feeds and stories, invite friends and more...")
                .setPicture("https://raw.github.com/sromku/android-simple-facebook/master/Refs/android_facebook_sdk_logo.png")
                .setLink("https://github.com/sromku/android-simple-facebook")
                .build();*/
        //publicacion de imagenes
        Bitmap imagen = BitmapFactory.decodeResource(getResources(), R.drawable.ic_image_test);
        Photo photo = new Photo.Builder()
                .setImage(imagen)
                .setName("prueba imagen")
                .setPlace("110619208966868")
                .build();

        mSimpleFacebook.publish(photo, onPublishListener);
    }
}
