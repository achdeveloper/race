package org.example.race.service;

import org.example.race.data.dto.RaceDto;
import org.example.race.data.dto.RunnerDto;
import org.example.race.data.entity.Race;
import org.example.race.data.entity.Runner;
import org.example.race.data.model.RaceEvent;
import org.example.race.exception.InsufficientRunnersException;
import org.example.race.repository.RaceRepository;
import org.example.race.service.impl.RaceServiceImpl;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.kafka.core.KafkaTemplate;

import java.time.LocalDate;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertThrows;

@ExtendWith(MockitoExtension.class)
class RaceServiceImplTest {

    @Mock
    private RaceRepository courseRepository;

    @Mock
    private KafkaTemplate<String, RaceEvent> kafkaTemplate;
    @InjectMocks
    private RaceServiceImpl raceService;


    @Test
    public void testCreateRace() throws InsufficientRunnersException {
        // Arrange
        RaceDto raceDto = new RaceDto("race1",LocalDate.now(), List.of(new RunnerDto(), new RunnerDto(), new RunnerDto()));
        Race race = new Race(1L, "race1",LocalDate.now(), List.of(new Runner(), new Runner(), new Runner()));
        Mockito.when(courseRepository.save(Mockito.any(Race.class))).thenReturn(race);
        raceService.createRace(raceDto);
        Mockito.verify(courseRepository, Mockito.times(1)).save(Mockito.any(Race.class));
        Mockito.verify(kafkaTemplate, Mockito.times(1)).send(Mockito.eq("race-event"), Mockito.any(RaceEvent.class));
    }
    @Test
    public void testCreateRace_InsufficientRunners() {
        // Arrange
        RaceDto raceDto = new RaceDto();
        raceDto.setRunners(List.of(new RunnerDto(), new RunnerDto()));
        raceDto.setRaceDate(LocalDate.now());
        raceDto.setName("The gold race");

        assertThrows(InsufficientRunnersException.class, () -> raceService.createRace(raceDto));
    }
}