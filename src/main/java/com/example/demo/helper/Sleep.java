package com.example.demo.helper;

public class Sleep {
    private final Object lock = new Object();
    
    public void doSleep() {
        synchronized (lock) {
            try {
                Thread.sleep(10L * 1000);
            } catch (InterruptedException ex) {
                ex.printStackTrace();
            }
        }
    }
}
