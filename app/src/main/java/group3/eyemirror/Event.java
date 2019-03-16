package group3.eyemirror;

import java.io.Serializable;

public class Event implements Serializable {
    private String eventText;
    private String descText;
    private int day;
    private int month;
    private int year;
    private int hour;
    private int minute;

    public Event(String eventText, String descText, int month, int day, int year, int hour, int minute){
        this.eventText = eventText;
        this.descText = descText;
        this.day = day;
        this.month = month;
        this.year = year;
        this.hour = hour;
        this.minute = minute;
    }

    public int getDay(){
        return this.day;
    }
    public int getMonth(){
        return this.month;
    }
    public int getYear(){
        return this.year;
    }
    public int getHour(){
        return this.hour;
    }
    public int getMinute(){
        return this.minute;
    }

    public String getEventText(){
        return this.eventText;
    }

    public String getDescText() {
        return descText;
    }

}
