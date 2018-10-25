package com.example.jaime.rememberandroid;

import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.example.jaime.rememberandroid.adapters.ReminderAdapter;
import com.example.jaime.rememberandroid.data.Reminder;
import com.example.jaime.rememberandroid.viewmodels.ReminderViewModel;

import java.util.List;


public class ReminderListFragment extends Fragment {


    private ReminderViewModel mReminderViewModel;

    public ReminderListFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_reminder_list, container, false);

        RecyclerView recyclerView = v.findViewById(R.id.rcViewReminderList);
        final ReminderAdapter adapter = new ReminderAdapter(this.getContext(), new ReminderAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(Reminder reminder) {
                Fragment detailFragment = ReminderDetailFragment.newInstance(reminder);
                FragmentTransaction transaction = getActivity().getSupportFragmentManager().beginTransaction();
                transaction.replace(R.id.fragment, detailFragment);
                transaction.addToBackStack(null);
                transaction.commit();
            }
        });
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this.getContext()));

        mReminderViewModel = ViewModelProviders.of(this).get(ReminderViewModel.class);

        mReminderViewModel.getmAllReminders().observe(this, new Observer<List<Reminder>>() {
            @Override
            public void onChanged(@Nullable List<Reminder> reminders) {
                adapter.setReminders(reminders);
            }
        });

        DividerItemDecoration dividerItemDecoration = new DividerItemDecoration(recyclerView.getContext(),
                DividerItemDecoration.VERTICAL);
        recyclerView.addItemDecoration(dividerItemDecoration);

        FloatingActionButton btnAddReminder = v.findViewById(R.id.fButtonAddReminder);

        btnAddReminder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), AddReminderActivity.class);
                startActivity(intent);
            }
        });

        return v;
    }

}
