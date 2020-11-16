package com.josh.trackcovid19v2;

import android.Manifest;
import android.annotation.SuppressLint;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import java.io.File;

public class HomeActivity extends AppCompatActivity {
    private int SPLASH_SCREEN_TIME_OUT = 30000;
    private Button button;
    private Handler handler;
    private final int STORAGE_PERMISSION_CODE = 1;
    private final int INTERNET_PERMISSION_CODE = 1;
    private Runnable myRunnable;
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        if(ContextCompat.checkSelfPermission(HomeActivity.this,
                Manifest.permission.WRITE_EXTERNAL_STORAGE) == PackageManager.PERMISSION_GRANTED) {
        }
        else{
            requestStoragePermission();
        }
        if(ContextCompat.checkSelfPermission(HomeActivity.this,
                Manifest.permission.INTERNET) == PackageManager.PERMISSION_GRANTED) {
        }
        else{
            requestInternetPermission();
        }
    }

    @RequiresApi(api = Build.VERSION_CODES.M)
    @Override
    protected void onStart() {
        super.onStart();
        button = findViewById(R.id.magicbtn);
        button.setOnClickListener(new View.OnClickListener() {
            @SuppressLint("LongLogTag")
            @Override
            public void onClick(View v) {
                Log.d("foasdfaosdfoasdfoasdfoasdfoasdofaosdfoasdfoasdf", "button thingy");
                skipSplashScreen();
            }
        });
        handler =  new Handler();
        Runnable myRunnable = new Runnable() {
            public void run() {
                Intent i= new Intent(HomeActivity.this,
                        MainActivity.class);
                //Intent is used to switch from one activity to another.

                startActivity(i);
                //invoke the SecondActivity.

                finish();
            }
        };
        handler.postDelayed(myRunnable, SPLASH_SCREEN_TIME_OUT);
    }
    public void startNextActivity(){
        startActivity(new Intent(getApplicationContext(), MainActivity.class));
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        skipSplashScreen();
    }

    public void skipSplashScreen(){
        if (handler != null)
            handler.removeCallbacksAndMessages(null);

        startNextActivity();
    }

    @Override
    protected void onStop() {
        super.onStop();
        // clear handler on stop
        if (handler != null)
            handler.removeCallbacksAndMessages(null);
    }
    private void requestInternetPermission() {
        if (ActivityCompat.shouldShowRequestPermissionRationale(this, Manifest.permission.INTERNET)){
            new AlertDialog.Builder(this)
                    .setTitle("Permission needed")
                    .setMessage("This permission is needed to store COVID data on your phone for offline usage")
                    .setPositiveButton("ok", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            ActivityCompat.requestPermissions(HomeActivity.this, new String [] {Manifest.permission.INTERNET}, INTERNET_PERMISSION_CODE);
                        }
                    })
                    .setNegativeButton("cancel", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            dialog.dismiss();
                        }
                    })
                    .create().show();
        } else{
            ActivityCompat.requestPermissions(this, new String [] {Manifest.permission.INTERNET}, INTERNET_PERMISSION_CODE);
        }
    }

    private void requestStoragePermission() {
        if (ActivityCompat.shouldShowRequestPermissionRationale(this, Manifest.permission.WRITE_EXTERNAL_STORAGE)){
            new AlertDialog.Builder(this)
                    .setTitle("Permission needed")
                    .setMessage("This permission is needed to access data")
                    .setPositiveButton("ok", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            ActivityCompat.requestPermissions(HomeActivity.this, new String [] {Manifest.permission.WRITE_EXTERNAL_STORAGE}, STORAGE_PERMISSION_CODE);
                        }
                    })
                    .setNegativeButton("cancel", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            dialog.dismiss();
                        }
                    })
                    .create().show();
        } else{
            ActivityCompat.requestPermissions(this, new String [] {Manifest.permission.WRITE_EXTERNAL_STORAGE}, STORAGE_PERMISSION_CODE);
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        if (requestCode == STORAGE_PERMISSION_CODE) {
            if(grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED){
            }
            else{
                Toast.makeText(this, "Permission denied", Toast.LENGTH_SHORT).show();
            }
        }
        if (requestCode == INTERNET_PERMISSION_CODE) {
            if(grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED){
            }
            else{
                Toast.makeText(this, "Permission denied", Toast.LENGTH_SHORT).show();
            }
        }
    }
    protected void onDestroy() {
        // TODO Auto-generated method stub
        super.onDestroy();
    }

    public void clearApplicationData() {
        File cache = getCacheDir();
        File appDir = new File(cache.getParent());
        if (appDir.exists()) {
            String[] children = appDir.list();
            for (String s : children) {
                if (!s.equals("lib")) {
                    deleteDir(new File(appDir, s));
                    Log.i("EEEEEERRRRRROOOOOOORRRR", "**************** File /data/data/APP_PACKAGE/" + s + " DELETED *******************");
                }
            }
        }
    }

    public static boolean deleteDir(File dir) {
        if (dir != null && dir.isDirectory()) {
            String[] children = dir.list();
            int i = 0;
            while (i < children.length) {
                boolean success = deleteDir(new File(dir, children[i]));
                if (!success) {
                    return false;
                }
                i++;
            }
        }

        assert dir != null;
        return dir.delete();
    }
}
