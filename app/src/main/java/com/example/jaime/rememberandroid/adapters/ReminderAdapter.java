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

        public void bind(final Reminder currentReminder, final OnItemClickListener listener) {
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    listener.onItemClick(currentReminder);
                }
            });
        }

        public void bindLongClickLisener(final Reminder currentReminder, final OnItemLongClickListener longClickListener) {
            itemView.setOnLongClickListener(new View.OnLongClickListener() {
                @Override
                public boolean onLongClick(View v) {
                    longClickListener.onItemLongClicked(currentReminder);
                    return true;
                }
            });
        }
    }

    private final LayoutInflater mInflater;
    private List<Reminder> mRemindersList;
    private final OnItemClickListener listener;
    private final OnItemLongClickListener longListener;

    public ReminderAdapter(Context context, OnItemClickListener listener, OnItemLongClickListener longListener) {
        mInflater = LayoutInflater.from(context);
        this.listener = listener;
        this.longListener = longListener;
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
            holder.bind(currentReminder, listener);
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

    public interface OnItemClickListener {
        void onItemClick(Reminder reminder);
    }

    public interface OnItemLongClickListener {
        void onItemLongClicked(Reminder reminder);
    }
}
