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
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;

public class DailyView extends Fragment {
    ArrayList<String> times = new ArrayList<String>();
    ArrayList<String> schedule = new ArrayList <String>();
    ArrayList<Event> events = new ArrayList<Event>();

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.tab_fragment_1, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        RecyclerView list = (RecyclerView) getView().findViewById(R.id.recycler);
        LinearLayoutManager layout = new LinearLayoutManager(getActivity());
        TextView tv = (TextView)getView().findViewById(R.id.heading);
        Calendar cal = Calendar.getInstance();
        int curDay = cal.get(Calendar.DAY_OF_MONTH);
        int curMonth = cal.get(Calendar.MONTH);
        int curYear = cal.get(Calendar.YEAR);
        tv.setText(curMonth+"/"+curDay+"/"+curYear);
        list.setLayoutManager(layout);
        times = initDailyTimes(times);
        schedule = initDailySchedule(schedule);
        events = ((MainActivity) getActivity()).getEventsList();
        schedule = updateSchedule(schedule);
        ListAdapter l = new ListAdapter(getActivity(), times, schedule, events);
        list.setAdapter(l);
        super.onViewCreated(view, savedInstanceState);
    }

    public ArrayList<String> initDailySchedule(ArrayList<String> schedule){
        schedule.clear();
        for (int i = 0; i <= 24; i++){
            schedule.add("");
        }
        return schedule;
    }

    public ArrayList<String> initDailyTimes(ArrayList<String> times) {
        times.clear();
        times.addAll(Arrays.asList("12:00 am", "1:00 am", "2:00 am", "3:00 am", "4:00 am", "5:00 am", "6:00 am", "7:00 am", "8:00 am", "9:00 am", "10:00 am", "11:00 am", "12:00 pm", "1:00 pm", "2:00 pm", "3:00 pm", "4:00 pm", "5:00 pm", "6:00 pm", "7:00 pm", "8:00 pm", "9:00 pm", "10:00 pm", "11:00 pm"));
        return times;
    }

    public ArrayList<String> updateSchedule(ArrayList<String> schedule){
        Calendar cal = Calendar.getInstance();
        int day = cal.get(Calendar.DAY_OF_MONTH);
        int month = cal.get(Calendar.MONTH);
        int year = cal.get(Calendar.YEAR);
        String hour;
        String minute;
        for (int i = 0; i < events.size(); i++){
            if (day == events.get(i).getDay() && month == events.get(i).getMonth() && year == events.get(i).getYear()){
                String temp = schedule.get(events.get(i).getHour());

                if (events.get(i).getHour() > 12 ){
                    hour = "" + (events.get(i).getHour() - 12);
                } else if (events.get(i).getHour() == 0){
                    hour = "12";
                } else {
                    hour = "" + events.get(i).getHour();
                }

                if (events.get(i).getMinute() < 10){
                    minute = "0" + events.get(i).getMinute();
                } else {
                    minute = "" + events.get(i).getMinute();
                }


                temp = temp + (hour + ":" + minute + " - " + events.get(i).getEventText()+"\n");
                schedule.set(events.get(i).getHour(), temp);
            }
        }
        return schedule;
    }
}
