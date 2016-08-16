package com.example.tankorbox.myplayer.Activities;

import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.example.tankorbox.myplayer.R;

public class PreStartApp extends AppCompatActivity {
    ProgressBar progressBar;
    int mProgress = 0;
    Handler handler;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pre_start_app);

        TextView textView1 = (TextView) findViewById(R.id.tvPreStart);
        Typeface typeface1 = Typeface.createFromAsset(getAssets(), "gotham.otf");
        textView1.setTypeface(typeface1);

        progressBar = (ProgressBar) findViewById(R.id.progressBar);
        final Handler handler = new Handler() {
            @Override
            public void handleMessage(Message msg) {
                progressBar.setProgress(msg.arg1);
            }
        };

        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                while (mProgress < 100) {
                    Message msg = new Message();
                    msg.arg1 = mProgress;
                    handler.sendMessage(msg);
                    try {
                        Thread.sleep(20);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    mProgress++;
                }
                Intent launchNextActivity;
                launchNextActivity = new Intent(PreStartApp.this, MainActivity.class);
                launchNextActivity.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                launchNextActivity.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
                launchNextActivity.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
                startActivity(launchNextActivity);
//              Now your activity A is top on stack with no other activities of your application on the backstack.


            }
        });
        thread.start();

    }
}
