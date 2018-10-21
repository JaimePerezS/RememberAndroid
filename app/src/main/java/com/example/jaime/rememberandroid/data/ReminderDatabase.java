package com.example.jaime.rememberandroid.data;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;
import android.content.Context;

@Database(entities = {Reminder.class}, version = 1, exportSchema = false)
public abstract class ReminderDatabase extends RoomDatabase {
    public abstract ReminderDao reminderDao();

    private static volatile ReminderDatabase INSTANCE;

    static ReminderDatabase getDatabase(final Context context) {
        if(INSTANCE == null) {
            synchronized (ReminderDatabase.class) {
                if(INSTANCE == null) {
                    INSTANCE = Room.databaseBuilder(context.getApplicationContext(), ReminderDatabase.class, "reminder_database").build();
                }
            }
        }
        return INSTANCE;
    }
}
