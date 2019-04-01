package group3.eyemirror;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.text.method.ScrollingMovementMethod;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CalendarView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Comparator;

public class TabFragment2 extends Fragment {
    TextView eventText;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.tab_fragment_2, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        CalendarView cv = (CalendarView) getView().findViewById(R.id.calendarView);
        TextView date = (TextView) getView().findViewById(R.id.monthlyDate);
        eventText = (TextView) getView().findViewById(R.id.monthlyEvents);
        eventText.setMovementMethod(new ScrollingMovementMethod());
        Calendar cal = Calendar.getInstance();
        ArrayList <Event> events = ((MainActivity) getActivity()).getEventsList();
        int curDay = cal.get(Calendar.DAY_OF_MONTH);
        int curMonth = cal.get(Calendar.MONTH);
        int curYear = cal.get(Calendar.YEAR);
        date.setText(curMonth+"/"+curDay+"/"+curYear);
        updateSchedule(curDay, curMonth, curYear, eventText);
        cv.setOnDateChangeListener(new CalendarView.OnDateChangeListener() {
            @Override
            public void onSelectedDayChange(CalendarView view, int year, int month,
                                            int dayOfMonth) {
                TextView date = (TextView) getView().findViewById(R.id.monthlyDate);
                date.setText(month+"/"+dayOfMonth+"/"+year);
                updateSchedule(dayOfMonth, month, year, eventText);
            }
        });

    }

    public ArrayList<Event> updateSchedule(int day, int month, int year, TextView eventText){
        eventText.setText("");
        ArrayList<Event>schedule = new ArrayList<Event>();
        ArrayList <Event> events = ((MainActivity) getActivity()).getEventsList();
        for (int i = 0; i < events.size(); i++){
            if (day == events.get(i).getDay() && month == events.get(i).getMonth() && year == events.get(i).getYear()){
                schedule.add(events.get(i));
            }
        }
        Collections.sort(schedule, new Comparator<Event>(){
            public int compare(Event e1, Event e2){
                return e1.getHour() - e2.getHour();
            }
        });
        for(Event e : schedule){
            eventText.append(e.getHour()+":"+e.getMinute()+"- "+e.getEventText()+"\n"+e.getDescText()+"\n\n");
        }
        if (schedule.size() == 0){
            eventText.setText("No events scheduled");
        }
        return schedule;
    }
}
