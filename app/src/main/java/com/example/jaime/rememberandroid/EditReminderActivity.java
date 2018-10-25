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

public class EditReminderActivity extends AppCompatActivity {

    private ReminderViewModel mReminderViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_reminder);

        final EditText edTxtName = findViewById(R.id.edTxtEditName);
        final EditText edTxtDate = findViewById(R.id.edTxtDate);
        final EditText edTxtDescription = findViewById(R.id.edTxtDescription);

        Button btnSaveChanges = findViewById(R.id.btnSaveChanges);

        mReminderViewModel = ViewModelProviders.of(this).get(ReminderViewModel.class);

        btnSaveChanges.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(TextUtils.isEmpty(edTxtName.getText()) || TextUtils.isEmpty(edTxtName.getText()) || TextUtils.isEmpty(edTxtName.getText())) {
                    setResult(RESULT_CANCELED);
                } else {
                    Reminder reminder = new Reminder(edTxtName.getText().toString(), edTxtDate.getText().toString(), edTxtDescription.getText().toString());
                    mReminderViewModel.edit(reminder);
                    setResult(RESULT_OK);
                }
                finish();
            }
        });

    }
}
