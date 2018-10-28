package com.example.jaime.rememberandroid;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.FrameLayout;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ReminderListFragment reminderListFragment = new ReminderListFragment();
        getSupportFragmentManager().beginTransaction().replace(R.id.fragment, reminderListFragment).addToBackStack(null).commit();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if(requestCode == 1) {
            if(resultCode == RESULT_OK) {
                ReminderListFragment reminderListFragment = new ReminderListFragment();
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment, reminderListFragment).addToBackStack(null).commit();
            }
        }
    }
}
