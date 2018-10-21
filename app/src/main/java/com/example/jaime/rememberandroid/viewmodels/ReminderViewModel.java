package com.example.jaime.rememberandroid.viewmodels;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.LiveData;
import android.support.annotation.NonNull;

import com.example.jaime.rememberandroid.data.Reminder;
import com.example.jaime.rememberandroid.data.ReminderRepository;

import java.util.List;

public class ReminderViewModel extends AndroidViewModel {
    private ReminderRepository mReminderRepository;
    private LiveData<List<Reminder>> mAllReminders;

    public ReminderViewModel(@NonNull Application application) {
        super(application);
        mReminderRepository = new ReminderRepository(application);
        mAllReminders = mReminderRepository.getReminders();
    }

    LiveData<List<Reminder>> getmAllReminders() {return mAllReminders;}

    public void insert(Reminder reminder) {mReminderRepository.insertReminder(reminder);}
}
