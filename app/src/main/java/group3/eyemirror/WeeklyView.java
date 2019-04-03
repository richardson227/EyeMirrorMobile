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

public class WeeklyView extends Fragment {
    ArrayList<String> days = new ArrayList<String>();
    ArrayList<String> schedule = new ArrayList <String>();
    ArrayList<Event> events = new ArrayList<Event>();

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.tab_fragment_3, container, false);
    }
    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        RecyclerView list = (RecyclerView) getView().findViewById(R.id.recycler);
        LinearLayoutManager layout = new LinearLayoutManager(getActivity());
        TextView tv = (TextView)getView().findViewById(R.id.heading);
        Calendar cal = Calendar.getInstance();
        int curMonth = cal.get(Calendar.MONTH);
        int firstDayOfWk = cal.getFirstDayOfWeek();
        cal.set(Calendar.DATE, firstDayOfWk);
        cal.add(Calendar.DATE, 6);
        int lastDayOfWk = cal.get(Calendar.DATE);
        tv.setText("Week of" + "=" + firstDayOfWk + " - " + lastDayOfWk);
        list.setLayoutManager(layout);
        events = ((MainActivity) getActivity()).getEventsList();
        days = initWeeklyDays(days);
        schedule = initWeeklySchedule(schedule);
        schedule = updateSchedule(schedule);
        ListAdapter l = new ListAdapter(getActivity(), days, schedule, events);
        list.setAdapter(l);
        super.onViewCreated(view, savedInstanceState);
    }

    public ArrayList<String> initWeeklySchedule(ArrayList<String> schedule){
        schedule.clear();
        for (int i = 0; i <= 7; i++){
            schedule.add("");
        }
        return schedule;
    }

    public ArrayList<String> initWeeklyDays(ArrayList<String> times) {
        times.clear();
        times.addAll(Arrays.asList("Monday", "Tuesday", "Wednesday", "Thursday", "Friday", "Saturday",  "Sunday"));
        return times;
    }

    public ArrayList<String> updateSchedule(ArrayList<String> schedule){
        System.out.println("here");
        Calendar cal = Calendar.getInstance();
        int firstDayOfWk = cal.getFirstDayOfWeek();
        cal.set(Calendar.DATE, firstDayOfWk);
        int month = cal.get(Calendar.MONTH);
        int year = cal.get(Calendar.YEAR);
        int lastDayofMonth = cal.getActualMaximum(Calendar.DATE);
        cal.add(Calendar.DATE, 6);
        int lastDayOfWk = cal.get(Calendar.DATE);
        String hour;
        String minute;
        int iter = firstDayOfWk;
        int curDayOfWk = 0;
        while (iter != lastDayOfWk){
        for (int i = 0; i < events.size(); i++) {
                System.out.println(events.get(i).getMonth() + "/" + events.get(i).getDay() + "/" + events.get(i).getYear() +
                "compared with " + month + "/" + iter + "/" + year);
                if (iter == events.get(i).getDay() && month == events.get(i).getMonth() && year == events.get(i).getYear()) {
                    String temp = schedule.get(curDayOfWk);

                    if (events.get(i).getHour() > 12) {
                        hour = "" + (events.get(i).getHour() - 12);
                    } else if (events.get(i).getHour() == 0) {
                        hour = "12";
                    } else {
                        hour = "" + events.get(i).getHour();
                    }

                    if (events.get(i).getMinute() < 10) {
                        minute = "0" + events.get(i).getMinute();
                    } else {
                        minute = "" + events.get(i).getMinute();
                    }


                    temp = temp + (hour + ":" + minute + " - " + events.get(i).getEventText() + "\n" + events.get(i).getDescText() + "\n");
                    schedule.set(curDayOfWk, temp);
                }
        }
            curDayOfWk++;
            iter++;
            if (iter > lastDayofMonth){
                iter = 1;
                month++;
                if (month > 12){
                    month = 1;
                    year++;
                }
            }

        }
        return schedule;
    }

}
