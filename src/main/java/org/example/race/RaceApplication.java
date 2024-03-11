package org.example.race;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.kafka.annotation.EnableKafka;

@EnableKafka
@SpringBootApplication
public class RaceApplication {

    public static void main(String[] args) {
        SpringApplication.run(RaceApplication.class, args);
    }

}
