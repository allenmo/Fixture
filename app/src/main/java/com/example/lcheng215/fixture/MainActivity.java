package com.example.lcheng215.fixture;

import android.app.ActionBar;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.os.Message;
import android.os.StrictMode;
import android.support.v7.app.AppCompatActivity;
import android.text.Layout;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import java.io.IOException;
import java.io.InputStream;
import java.util.logging.Handler;
import java.util.logging.LogRecord;

public class MainActivity extends AppCompatActivity {
    private Button button;
    public ImageView imageview;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        button = (Button)findViewById(R.id.button);
        imageview = (ImageView)findViewById(R.id.imageview);
        System.out.println("Activity --->" + Thread.currentThread().getId());

        int SDK_INT = android.os.Build.VERSION.SDK_INT;
        Log.v("kkk","SDK_INT:" + SDK_INT);
        if (SDK_INT > 8)
        {
            StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder()
                    .permitAll().build();
            StrictMode.setThreadPolicy(policy);
            //your codes here

        }

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try{
                    InputStream inputStream = HttpUtils.getImageViewInputStream();
                    Bitmap bitmap = BitmapFactory.decodeStream(inputStream);
                    imageview.setImageBitmap(bitmap);
                }catch (IOException e){
                    e.printStackTrace();
                }
                    /*Thread thread = new Thread(r);
                    thread.start();*/

            }
        });
    }

    /*Runnable r= new Runnable() {
        @Override
        public void run() {
                System.out.println("Activity --->" + Thread.currentThread().getId());
                byte[] data = HttpUtils.getImageViewArray();
                Bitmap bitmap = BitmapFactory.decodeByteArray(data,0,data.length);
                imageview.setImageBitmap(bitmap);
                Log.v("kkk", "in btn kick");
        }
    };*/



    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
