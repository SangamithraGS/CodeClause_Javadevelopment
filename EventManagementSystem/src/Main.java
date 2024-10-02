package src;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        EventManager eventManager = new EventManager();
        Scanner scanner = new Scanner(System.in);
        int choice;

        do {
            System.out.println("Event Management System");
            System.out.println("1. Add Event");
            System.out.println("2. Display Events");
            System.out.println("3. Remove Event");
            System.out.println("4. Exit");
            System.out.print("Enter your choice: ");
            choice = scanner.nextInt();
            scanner.nextLine(); // Consume newline

            switch (choice) {
                case 1:
                    System.out.print("Enter event name: ");
                    String name = scanner.nextLine();
                    System.out.print("Enter event date: ");
                    String date = scanner.nextLine();
                    System.out.print("Enter event location: ");
                    String location = scanner.nextLine();

                    Event event = new Event(name, date, location);
                    eventManager.addEvent(event);
                    break;

                case 2:
                    eventManager.displayEvents();
                    break;

                case 3:
                    System.out.print("Enter event name to remove: ");
                    String eventName = scanner.nextLine();
                    eventManager.removeEvent(eventName);
                    break;

                case 4:
                    System.out.println("Exiting...");
                    break;

                default:
                    System.out.println("Invalid choice. Please try again.");
            }

        } while (choice != 4);

        scanner.close();
    }
}
