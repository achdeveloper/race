package org.example.race.service;

import org.example.race.data.dto.RaceDto;
import org.example.race.exception.InsufficientRunnersException;

@FunctionalInterface
public interface RaceService {

    /**
     * create race with minimum 3 runners
     *
     * @param raceRequest raceDTO
     * @return raceDto
     * @throws InsufficientRunnersException when have under 3 runners for race
     */
    RaceDto createRace(RaceDto raceRequest) throws InsufficientRunnersException;

}