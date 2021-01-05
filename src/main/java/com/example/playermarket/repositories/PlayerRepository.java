package com.example.playermarket.repositories;

import com.example.playermarket.models.Player;
import org.springframework.data.repository.CrudRepository;

public interface PlayerRepository extends CrudRepository<Player, Long> {
}
