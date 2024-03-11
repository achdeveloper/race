package org.example.race.utils;

import org.example.race.data.dto.RaceDto;
import org.example.race.data.entity.Race;
import org.example.race.data.entity.Runner;

import java.util.List;

public class Mapper {

    /**
     * mapper RaceDto to Race
     *
     * @param raceDto raceDtoRequest
     * @return race
     */
    public static Race raceMapper(RaceDto raceDto) {
        List<Runner> runnerList = raceDto.getRunners().stream()
                .map(runnerDto -> {
                    Runner r = new Runner();
                    r.setName(runnerDto.getName());
                    return r;
                })
                .toList();
        return Race.builder()
                .raceDate(raceDto.getRaceDate())
                .name(raceDto.getName())
                .runners(runnerList)
                .build();
    }
}
