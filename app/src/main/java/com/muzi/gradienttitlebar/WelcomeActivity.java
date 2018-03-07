package com.muzi.gradienttitlebar;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

public class WelcomeActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome);
        initIconFont();
    }

    private void initIconFont() {
        new TypeFaceTask(this.getApplicationContext()).setComplete(new TypeFaceTask.onComplete() {
            @Override
            public void onComplete() {
                startActivity(new Intent(WelcomeActivity.this, MainActivity.class));
                finish();
            }
        }).execute();
    }
}
