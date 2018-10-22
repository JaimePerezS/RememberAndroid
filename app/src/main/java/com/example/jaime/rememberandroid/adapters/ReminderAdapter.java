package com.example.jaime.rememberandroid.adapters;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.jaime.rememberandroid.R;
import com.example.jaime.rememberandroid.data.Reminder;

import java.util.List;

public class ReminderAdapter extends RecyclerView.Adapter<ReminderAdapter.ReminderViewHolder> {

    class ReminderViewHolder extends RecyclerView.ViewHolder {
        private final TextView txtViewName;
        private final TextView txtViewDate;

        private ReminderViewHolder(View v) {
            super(v);
            txtViewName = v.findViewById(R.id.txtViewName);
            txtViewDate = v.findViewById(R.id.txtViewDate);
        }
    }

    private final LayoutInflater mInflater;
    private List<Reminder> mRemindersList;

    public ReminderAdapter(Context context) {
        mInflater = LayoutInflater.from(context);
    }

    @NonNull
    @Override
    public ReminderViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = mInflater.inflate(R.layout.item_reminder_layout, parent, false);
        return new ReminderViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull ReminderViewHolder holder, int position) {
        if(mRemindersList != null) {
            Reminder currentReminder = mRemindersList.get(position);
            holder.txtViewName.setText(currentReminder.getName());
            holder.txtViewDate.setText(currentReminder.getDate());
        } else {
            holder.txtViewName.setText("Empty");
            holder.txtViewDate.setText("");
        }
    }

    public void setReminders(List<Reminder> reminders) {
        mRemindersList = reminders;
        notifyDataSetChanged();
    }

    @Override
    public int getItemCount() {
        if(mRemindersList != null) {
            return mRemindersList.size();
        } else {
            return 0;
        }
    }
}
