package group3.eyemirror;

import java.util.ArrayList;

public class EventList {
    private ArrayList events = new ArrayList();

    public EventList() {

    }

    public void addEvent(Event e){
        events.add(e);
    }

    public ArrayList getList(){
        return events;
    }

}
