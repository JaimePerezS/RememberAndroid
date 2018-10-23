package com.example.jaime.rememberandroid.data;

import android.app.Application;
import android.arch.lifecycle.LiveData;
import android.os.AsyncTask;

import java.util.List;

public class ReminderRepository {
    private ReminderDao mReminderDao;
    private LiveData<List<Reminder>> mAllReminders;

    public ReminderRepository(Application application) {
        ReminderDatabase db = ReminderDatabase.getDatabase(application);
        this.mReminderDao = db.reminderDao();
        this.mAllReminders = mReminderDao.getReminders();
    }

    public LiveData<List<Reminder>> getReminders() {
        return mAllReminders;
    }

    public void insertReminder(Reminder reminder) {
        new insertAsyncTask(mReminderDao).execute(reminder);
    }

    private static class insertAsyncTask extends AsyncTask<Reminder, Void, Void> {
        private ReminderDao mAsyncTaskDao;

        insertAsyncTask(ReminderDao dao) {
            this.mAsyncTaskDao = dao;
        }

        @Override
        protected Void doInBackground(final Reminder... params){
            mAsyncTaskDao.saveReminder(params[0]);
            return null;
        }
    }

    public void editReminder (Reminder reminder) {
        new editAsyncTask(mReminderDao).execute(reminder);
    }

    private static class editAsyncTask extends AsyncTask<Reminder, Void, Void> {
        private ReminderDao mAsyncTaskDao;

        editAsyncTask(ReminderDao dao) {this.mAsyncTaskDao = dao;}


        @Override
        protected Void doInBackground(final Reminder... params) {
            mAsyncTaskDao.updateReminder(params[0]);
            return null;
        }
    }

}
