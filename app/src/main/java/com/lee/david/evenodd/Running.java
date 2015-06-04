package com.lee.david.evenodd;

/**
 * Created by David on 6/4/2015.
 */
public class Running implements Runnable{

    private volatile boolean running = true;

    // Terminates the threads
    public void terminate() {
        running = false;
    }

    @Override
    public void run() {

        while (running) {
            try {
                Thread.sleep(1000);
            }catch (InterruptedException e) {

                running = false;
            }
        }
    }
}
