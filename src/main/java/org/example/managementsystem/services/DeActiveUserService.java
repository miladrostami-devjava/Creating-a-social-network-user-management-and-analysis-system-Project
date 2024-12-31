package org.example.managementsystem.services;

import org.example.managementsystem.entities.User;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicBoolean;

public class DeActiveUserService {


    public static void main(String[] args) {

        //region Using simple loops (for, while, do-while)

        //step 1;

// Register user information with a for loop

        List<User> users = new ArrayList<>();






        for (int i = 1; i <= 10 ; i++) {
            users.add(new User(i,"User" + i , i % 2 != 0));
        }



        //step 2;
// Display user information with a while loop

        int index = 0;

        while (index <users.size()){
            User user = users.get(index);
            System.out.println("User ID :"  + user.getId() + " , Name :" + user.getName());
            index ++;
        }



        //step 3;
// Check active users with do - while loop

        int i = 0;
        do {
            User user = users.get(i);
            if (user.isActive()){
                System.out.println("DeActive user : " + user.getName());
            }
            i ++;
        }while (i < users.size());

        //endregion


        //region Using advanced loops (for-each, streams)

        //step 1:
        // Calculating the average age of users using stream

        double averageAge = users.stream()
                .mapToInt(User::getAge)
                .average()
                .orElse(0);

        System.out.println("Average age :" + averageAge);


        //step 2:
        //List active users using advanced for-each loop

        for (User user : users){
            if (user.isActive()){
                System.out.println("Active user : " + user.getName());
            }
        }



        //endregion


        //region Synchronous and Timed Loops (Using Java's Synchronous and Asynchronous Features)

        //step 1:
        //Run reports using ExecutorService to process users concurrently

        ExecutorService executorService = Executors.newFixedThreadPool(2);

        for (User user :users){
            executorService.submit(
                    ()-> {
                        System.out.println("Processing User : " + user.getName());

                        try {
                            Thread.sleep(750);
                        }catch (InterruptedException e){
                            Thread.currentThread().interrupt();
                        }
                    }
            );
        }
        executorService.shutdown();


        //step 2:
        //Scheduling reports using ScheduledExecutorService

        ScheduledExecutorService scheduler = Executors.newScheduledThreadPool(1);
        scheduler.scheduleAtFixedRate(()-> {
            long activeUserCount = users.stream().filter(User::isActive).count();
            System.out.println("DeActive Users Count :");

        },5,0, TimeUnit.SECONDS);


        //endregion



        //region Combining loops with advanced tools like iterators


        //step 1:

        //     Navigating users with iterator and removing inactive users

        Iterator<User> iterator = users.iterator();
        while (iterator.hasNext()){
            User user = iterator.next();
            if (user.isActive()){
                iterator.remove();
            }
        }



        //step 2:

        //  Displaying users simultaneously with parallelStream

        users.parallelStream().forEach(user ->{
            System.out.println("Parallel Processing User : " + user.getName());
        });


        //endregion



        //region Infinite Loop Simulation with Controlled Conditions



        //step 1:
        //Infinite Loop for Monitoring User Activity

        AtomicBoolean running = new AtomicBoolean(true);

        new Thread(()-> {
            while (running.get()){

                System.out.println("Monitoring Users...");
                try {
                    Thread.sleep(3500);

                }catch (InterruptedException e){
                    Thread.currentThread().interrupt();
                }

            }




        }).start();


        //stop next 10 second
        try {
            Thread.sleep(12000);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
        running.set(false);



        //endregion


        System.out.println("the end process");

















    }



}
