package group3.eyemirror;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import java.util.ArrayList;


public class TabFragment1 extends Fragment {
    ArrayList<String> times = new ArrayList<String>();
    ArrayList<String> schedule = new ArrayList <String>();
    ArrayList<Event> events = new ArrayList<Event>();
    public MainActivity activity;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.tab_fragment_1, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        RecyclerView list = (RecyclerView) getView().findViewById(R.id.recycler);
        LinearLayoutManager layout = new LinearLayoutManager(getActivity());
        list.setLayoutManager(layout);
        times = ((MainActivity) getActivity()).initDailyTimes(times);
        schedule = ((MainActivity) getActivity()).initDailySchedule(schedule);
        schedule = ((MainActivity) getActivity()).updateSchedule(schedule);
        ListAdapter l = new ListAdapter(getActivity(), times, schedule, events);
        list.setAdapter(l);
        super.onViewCreated(view, savedInstanceState);
    }

}
