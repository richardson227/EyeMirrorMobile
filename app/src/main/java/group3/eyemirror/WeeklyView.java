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
        // Get calendar set to current date and time
        Calendar c = Calendar.getInstance();

    // Set the calendar to monday of the current week
        c.set(Calendar.DAY_OF_WEEK, Calendar.MONDAY);
        int startOfWk = c.get(Calendar.DAY_OF_MONTH);
        int startMonth = c.get(Calendar.MONTH) +1;
        c.add(Calendar.DATE, 7);
        int endOfWk = c.get(Calendar.DAY_OF_MONTH);
        int endMonth = c.get(Calendar.MONTH) +1;
        tv.setText("Week of " + startMonth + "/" + startOfWk + " to " + endMonth  + "/" + endOfWk);
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
        Calendar cal = Calendar.getInstance();
        cal.setFirstDayOfWeek(Calendar.MONDAY);
        String hour;
        String minute;
        Calendar c = Calendar.getInstance();
        c.set(Calendar.DAY_OF_WEEK, Calendar.MONDAY);
        for (int count = 0; count < 7; count++) {
        for (int i = 0; i < events.size(); i++) {
                if (c.get(Calendar.DAY_OF_MONTH) == events.get(i).getDay() && (c.get(Calendar.MONTH) + 1) == events.get(i).getMonth() && c.get(Calendar.YEAR) == events.get(i).getYear()) {
                    String temp = schedule.get(count);

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
                    schedule.set(count, temp);
                }
        }
            c.add(Calendar.DATE, 1);
        }
        return schedule;
    }

}
