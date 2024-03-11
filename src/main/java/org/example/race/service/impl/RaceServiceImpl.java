package org.example.race.service.impl;

import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.example.race.data.dto.RaceDto;
import org.example.race.data.entity.Race;
import org.example.race.data.model.RaceEvent;
import org.example.race.exception.InsufficientRunnersException;
import org.example.race.repository.RaceRepository;
import org.example.race.service.RaceService;
import org.example.race.utils.Constants;
import org.example.race.utils.Mapper;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;


@Service
@AllArgsConstructor
@Log4j2
public class RaceServiceImpl implements RaceService {

    private final KafkaTemplate<String, RaceEvent> kafkaTemplate;

    private final RaceRepository raceRepository;


    /**
     * create race with runners
     *
     * @param raceRequest request
     * @return raceDto
     */
    @Transactional
    @Override
    public RaceDto createRace(RaceDto raceRequest) throws InsufficientRunnersException {
        log.info("Creating race: {}", raceRequest.getName());
        controlNumberOfRunner(raceRequest.getRunners().size());
        Race race = Mapper.raceMapper(raceRequest);
        Race savedCourse = raceRepository.save(race);
        RaceEvent raceEvent = new RaceEvent(Constants.CREATE_RACE_EVENT_TYPE, savedCourse);
        kafkaTemplate.send(Constants.RACE_EVENT_TOPIC, raceEvent);
        log.info("The race {} with id :{} is created", savedCourse.getName(), savedCourse.getId());
        return raceRequest;
    }

    /**
     * control Number Of Runner
     *
     * @param runnerNbr runner nbr
     */
    private void controlNumberOfRunner(Integer runnerNbr) throws InsufficientRunnersException {
        if (runnerNbr < Constants.NBR_MIN_RUNNERS) {
            log.error("Number of runner is under 3");
            throw new InsufficientRunnersException("Can't create race. The minimum number of runners is 3");
        }
    }
}
