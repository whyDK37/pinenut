package jdk.thread.util;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

/**
 * simple CountDownLatchDemo demo
 * Created by whydk on 10/25/2016.
 */
public class CountDownLatchDemo {

    public static void main(String[] args) {

        // Creates a VideoConference with 10 participants.
        Videoconference conference = new Videoconference(10);
        // Creates a thread to run the VideoConference and start it.
        Thread threadConference = new Thread(conference);
        threadConference.start();

        // Creates ten participants, a thread for each one and starts them
        for (int i = 0; i < 10; i++) {
            Participant p = new Participant(conference, "Participant " + i);
            Thread t = new Thread(p);
            t.start();
        }

    }

    static class Videoconference implements Runnable {

        /**
         * This class uses a CountDownLatch to control the arrivel of all
         * the participants
         */
        private final CountDownLatch controller;

        /**
         * Constructor of the class. Initializes the CountDownLatch
         *
         * @param number The number of participants in the videoconference
         */
        public Videoconference(int number) {
            controller = new CountDownLatch(number);
        }

        /**
         * This method is called by every participant when he incorporates to the VideoConference
         *
         * @param name
         */
        public void arrive(String name) {
            System.out.printf("%s has arrived.\n", name);
            // This method uses the countDown method to decrement the internal counter of the
            // CountDownLatch
            controller.countDown();
            System.out.printf("VideoConference: Waiting for %d participants.\n", controller.getCount());
        }

        /**
         * This is the main method of the Controller of the VideoConference. It waits for all
         * the participants and the, starts the conference
         */
        @Override
        public void run() {
            System.out.printf("VideoConference: Initialization: %d participants.\n", controller.getCount());
            try {
                // Wait for all the participants
                controller.await();
                // Starts the conference
                System.out.printf("VideoConference: All the participants have come\n");
                System.out.printf("VideoConference: Let's start...\n");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }


    }


    /**
     * This class implements a participant in the VideoConference
     */
    static class Participant implements Runnable {

        /**
         * VideoConference in which this participant will take part off
         */
        private Videoconference conference;

        /**
         * Name of the participant. For log purposes only
         */
        private String name;

        /**
         * Constructor of the class. Initialize its attributes
         *
         * @param conference VideoConference in which is going to take part off
         * @param name       Name of the participant
         */
        public Participant(Videoconference conference, String name) {
            this.conference = conference;
            this.name = name;
        }

        /**
         * Core method of the participant. Waits a random time and joins the VideoConference
         */
        @Override
        public void run() {
            Long duration = (long) (Math.random() * 10);
            try {
                TimeUnit.SECONDS.sleep(duration);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            conference.arrive(name);

        }
    }

}
