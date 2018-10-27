package com.example.jaime.rememberandroid;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;

import com.example.jaime.rememberandroid.data.Reminder;


public class ReminderDetailFragment extends Fragment {

    private static final String REMINDER = "reminder";

    private Reminder mReminder;

    public ReminderDetailFragment() {
        // Required empty public constructor
    }

    public static ReminderDetailFragment newInstance(Reminder reminder) {
        ReminderDetailFragment fragment = new ReminderDetailFragment();
        Bundle args = new Bundle();
        args.putParcelable(REMINDER, reminder);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mReminder = getArguments().getParcelable(REMINDER);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_reminder_detail, container, false);

        final TextView txtViewReminderNameDetail = v.findViewById(R.id.txtViewReminderNameDetail);
        final TextView txtViewReminderDateDetail = v.findViewById(R.id.txtViewReminderDateDetail);
        final TextView txtViewReminderDescriptionDetail = v.findViewById(R.id.txtViewReminderDescriptionDetail);


        final FloatingActionButton btnEditReminder = v.findViewById(R.id.fButtonEditReminder);

        txtViewReminderNameDetail.setText(mReminder.getName());
        txtViewReminderDateDetail.setText(mReminder.getDate());
        txtViewReminderDescriptionDetail.setText(mReminder.getDescription());

        btnEditReminder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getContext(), EditReminderActivity.class);
                intent.putExtra("REMINDER_INFO", mReminder);
                startActivity(intent);
            }
        });

        return v;
    }
}
