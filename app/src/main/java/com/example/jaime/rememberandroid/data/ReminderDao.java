package com.example.jaime.rememberandroid.data;

import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;

import java.util.List;

import static android.arch.persistence.room.OnConflictStrategy.REPLACE;

@Dao
public interface ReminderDao {
    @Query("SELECT * FROM reminders")
    LiveData<List<Reminder>> getReminders();

    @Insert(onConflict = REPLACE)
    void saveReminder(Reminder... reminder);

    @Update
    void updateReminder(Reminder... reminder);

    @Delete
    void deleteReminder(Reminder... reminder);
}
