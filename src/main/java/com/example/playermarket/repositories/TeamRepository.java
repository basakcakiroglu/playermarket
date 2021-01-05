package com.example.playermarket.repositories;

import com.example.playermarket.models.Team;
import org.springframework.data.repository.CrudRepository;

public interface TeamRepository extends CrudRepository<Team, Long> {

    Team findByName(String name);
}
