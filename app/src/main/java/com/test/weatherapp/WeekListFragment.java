package com.test.weatherapp;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.test.weatherapp.adapters.DayWeekAdapter;
import com.test.weatherapp.pojo.Period;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class WeekListFragment extends Fragment {

    public interface OnDayChanged {
        void onSelect(Period period);
    }

    private DayChangeListener mCallback;

    public <T extends Activity> void setDayChangeListener(DayChangeListener<T> listener) {
        this.mCallback = listener;
    }

    @BindView(R.id.week_rw)
    RecyclerView recycler;
    private DayWeekAdapter adapter;
    private RecyclerView.LayoutManager manager;
    private List<Period> periods;

    public void setList(List<Period> periods) {

        adapter = new DayWeekAdapter(getActivity(), periods);
        recycler.setAdapter(adapter);

        this.periods = periods;
        adapter.notifyDataSetChanged();

        adapter.setListener(period -> mCallback.onDayChanged(getActivity(), period));
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.week_list_fragment, null);
        ButterKnife.bind(this, view);

        if (adapter != null) {
            adapter = new DayWeekAdapter(getActivity(), periods);
            recycler.setAdapter(adapter);
        }

        manager = new LinearLayoutManager(getActivity());
        recycler.setLayoutManager(manager);
        recycler.setHasFixedSize(true);

        return view;
    }
}
