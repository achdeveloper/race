package org.example.race.data.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.example.race.data.entity.Race;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RaceEvent {

    private String eventType;

    private Race race;
}
