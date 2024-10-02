package src;

import java.util.ArrayList;

public class EventManager {
    private ArrayList<Event> events;

    public EventManager() {
        events = new ArrayList<>();
    }

    public void addEvent(Event event) {
        events.add(event);
    }

    public void displayEvents() {
        if (events.isEmpty()) {
            System.out.println("No events scheduled.");
        } else {
            for (Event event : events) {
                System.out.println(event);
            }
        }
    }

    public void removeEvent(String eventName) {
        events.removeIf(event -> event.getEventName().equalsIgnoreCase(eventName));
    }
}
