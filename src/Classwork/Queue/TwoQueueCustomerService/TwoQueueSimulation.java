package Classwork.Queue.TwoQueueCustomerService;

import java.util.*;

class Customer {
    int id;
    int arrival;
    int service;
    int start;
    int finish;
    int wait;
    String queue;

    Customer(int id, int arrival, int service) {
        this.id = id;
        this.arrival = arrival;
        this.service = service;
    }
}

public class TwoQueueSimulation {
    public static void main(String[] args) {
        Random rand = new Random();
        int numCustomers = 6;
        int minArrival = 1, maxArrival = 20;
        int minService = 2, maxService = 8;

        List<Customer> customers = new ArrayList<>();

        // Generate random arrival and service times
        for (int i = 1; i <= numCustomers; i++) {
            int arrival = rand.nextInt(maxArrival - minArrival + 1) + minArrival;
            int service = rand.nextInt(maxService - minService + 1) + minService;
            customers.add(new Customer(i, arrival, service));
        }

        // Sort by arrival time
        customers.sort(Comparator.comparingInt(c -> c.arrival));

        // Track queue finish times
        int finishA = 0;
        int finishB = 0;

        System.out.printf("%-6s%-10s%-10s%-8s%-10s%-10s%n",
                "Cust", "Arrival", "Service", "Queue", "Finish", "Wait");
        System.out.println("-----------------------------------------------------");

        for (Customer c : customers) {
            int arrival = c.arrival;
            int service = c.service;
            String chosenQueue;
            int start;

            // Determine which queue to join
            if (finishA <= arrival && finishB <= arrival) {
                // both idle
                chosenQueue = "A";
                start = arrival;
            } else if (finishA <= arrival) {
                chosenQueue = "A";
                start = arrival;
            } else if (finishB <= arrival) {
                chosenQueue = "B";
                start = arrival;
            } else {
                // both busy: choose the one that finishes first
                if (finishA < finishB) {
                    chosenQueue = "A";
                    start = finishA;
                } else if (finishB < finishA) {
                    chosenQueue = "B";
                    start = finishB;
                } else {
                    chosenQueue = "A"; // equal, choose A
                    start = finishA;
                }
            }

            int finish = start + service;
            int wait = start - arrival;

            // Update queue finish times
            if (chosenQueue.equals("A")) finishA = finish;
            else finishB = finish;

            c.queue = chosenQueue;
            c.start = start;
            c.finish = finish;
            c.wait = wait;

            System.out.printf("%-6d%-10d%-10d%-8s%-10d%-10d%n",
                    c.id, arrival, service, chosenQueue, finish, wait);
        }
    }
}