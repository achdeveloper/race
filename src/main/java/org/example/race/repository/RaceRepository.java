package org.example.race.repository;

import org.example.race.data.entity.Race;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface RaceRepository extends JpaRepository<Race, Long> {
}
