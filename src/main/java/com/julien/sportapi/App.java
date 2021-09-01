package com.julien.sportapi;

import com.julien.sportapi.cli.CliController;
import com.julien.sportapi.service.CoachService;
import com.julien.sportapi.service.GymService;
import com.julien.sportapi.service.SubscriptionService;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

@SpringBootApplication
public class App {
    public static void main(String[] args) {

        ApplicationContext applicationContext =  SpringApplication.run(App.class, args);
        CoachService coachService = applicationContext.getBean(CoachService.class);
        GymService gymService = applicationContext.getBean(GymService.class);
        SubscriptionService subscriptionService = applicationContext.getBean(SubscriptionService.class);

        initCoachData(coachService);
        initGymData(gymService);
        initSubscriptionData(subscriptionService);

        CliController cliController = applicationContext.getBean(CliController.class);
        cliController.start();

    }

    private static void initGymData(GymService gymService) {
        gymService.add("Paf Gym !", "Vannes");
        gymService.add("Paf Paf Gym !", "Surzur");
    }

    private static void initCoachData(CoachService coachService) {
        coachService.add("Suuuper coach");
        coachService.add("coach trop fort !");
    }

    private static void initSubscriptionData(SubscriptionService subscriptionService) {
        subscriptionService.add();
        subscriptionService.add();
    }

}
