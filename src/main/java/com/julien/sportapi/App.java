package com.julien.sportapi;

import com.julien.sportapi.service.CoachService;
import com.julien.sportapi.service.GymService;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

@SpringBootApplication
public class App {
    public static void main(String[] args) {

        ApplicationContext applicationContext =  SpringApplication.run(App.class, args);
        CoachService coachService = applicationContext.getBean(CoachService.class);
        GymService gymService = applicationContext.getBean(GymService.class);

        initCoachData(coachService);
        initGymData(gymService);

    }

    private static void initGymData(GymService gymService) {
        gymService.add("Paf Gym !", "Vannes");
        gymService.add("Paf Paf Gym !", "Surzur");
    }

    private static void initCoachData(CoachService coachService) {
        coachService.add("Suuuper coach");
        coachService.add("coach trop fort !");
    }


}
