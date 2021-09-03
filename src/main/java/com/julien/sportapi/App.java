package com.julien.sportapi;

import com.julien.sportapi.service.CoachService;
import com.julien.sportapi.service.GymService;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

@SpringBootApplication
public class App {
    public static void main(String[] args) {

        ApplicationContext applicationContext = SpringApplication.run(App.class, args);
        String toto = "toto";

    }

}
