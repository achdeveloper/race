package org.example.race.controller;

import lombok.AllArgsConstructor;
import org.example.race.data.dto.RaceDto;
import org.example.race.service.impl.RaceServiceImpl;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/races")
@AllArgsConstructor
public class RaceController {

    private final RaceServiceImpl raceServiceImpl;

    @PostMapping("/create")
    public ResponseEntity<RaceDto> createRace(@RequestBody RaceDto race) {
        RaceDto createdRace = raceServiceImpl.createRace(race);
        return new ResponseEntity<>(createdRace, HttpStatus.CREATED);
    }

}
