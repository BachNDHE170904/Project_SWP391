/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package event;

import jakarta.servlet.ServletContextEvent;
import jakarta.servlet.ServletContextListener;
import jakarta.servlet.annotation.WebListener;
import java.util.Timer;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class AddMoneyEventListener implements ServletContextListener {

    private Timer timer;
    private ScheduledExecutorService scheduler;

    @Override
    public void contextInitialized(ServletContextEvent sce) {
        // Create a Timer instance
        scheduler = Executors.newScheduledThreadPool(1);
        // Create a TimerTask instance
        scheduler.scheduleAtFixedRate(new AddMoneyTask(), 0, 1, TimeUnit.DAYS);
    }

    @Override
    public void contextDestroyed(ServletContextEvent servletContextEvent) {
        // Cleanup tasks, if needed
        if (scheduler != null) {
            scheduler.shutdown();
        }
    }
}
