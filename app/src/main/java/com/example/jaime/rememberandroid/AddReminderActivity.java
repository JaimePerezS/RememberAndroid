package com.example.jaime.rememberandroid;

import android.arch.lifecycle.ViewModelProviders;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.jaime.rememberandroid.data.Reminder;
import com.example.jaime.rememberandroid.viewmodels.ReminderViewModel;

public class AddReminderActivity extends AppCompatActivity {

    private ReminderViewModel mReminderViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_reminder);

        final EditText editTextName = findViewById(R.id.edTextName);
        final EditText editTextDate = findViewById(R.id.edTxtDate);
        final EditText editTextDescription = findViewById(R.id.edTxtDescription);

        Button btnSaveReminder = findViewById(R.id.btnSaveReminder);

        mReminderViewModel = ViewModelProviders.of(this).get(ReminderViewModel.class);

        btnSaveReminder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(TextUtils.isEmpty(editTextName.getText()) || TextUtils.isEmpty(editTextDate.getText()) || TextUtils.isEmpty(editTextDescription.getText())) {
                    setResult(RESULT_CANCELED);
                } else {
                    Reminder reminder = new Reminder(editTextName.getText().toString(), editTextDate.getText().toString(), editTextDescription.getText().toString());
                    mReminderViewModel.insert(reminder);
                    setResult(RESULT_OK);
                }
                finish();
            }
        });
    }
}
