package org.example.race.controller;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.kafka.test.context.EmbeddedKafka;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;


@SpringBootTest
@AutoConfigureMockMvc
@TestPropertySource(properties = {"spring.kafka.producer.bootstrap-servers=${spring.embedded.kafka.brokers}"})
@EmbeddedKafka(partitions = 1, topics = "race-events")
class RaceControllerTest {

        @Autowired
        private MockMvc mockMvc;

        @Test
        public void createRaceTest() throws Exception {
            String requestBody = "{\"name\":\"Gold Race\",\"raceDate\":\"2024-03-22\",\"runners\":[{\"name\":\"Horse A\"},{\"name\":\"Horse B\"},{\"name\":\"Horse C\"}]}";

            mockMvc.perform(MockMvcRequestBuilders.post("/api/races/create")
                            .contentType(MediaType.APPLICATION_JSON)
                            .content(requestBody))
                    .andExpect(MockMvcResultMatchers.status().isCreated());
        }

    @Test
    public void errorCreatingRaceTest() throws Exception {
        String requestBody = "{\"name\":\"Gold Race\",\"raceDate\":\"2024-03-22\",\"runners\":[{\"name\":\"Horse A\"},{\"name\":\"Horse B\"}]}";

        mockMvc.perform(MockMvcRequestBuilders.post("/api/races/create")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(requestBody))
                .andExpect(MockMvcResultMatchers.status().isBadRequest());
    }
}