package com.example.playermarket.services;

import com.example.playermarket.models.Player;
import com.example.playermarket.repositories.PlayerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Service
public class PlayerService {

    PlayerRepository playerRepository;

    @Autowired
    public PlayerService(PlayerRepository playerRepository) {
        this.playerRepository = playerRepository;
    }

    @Transactional
    public List<Player> getAll(){
        return StreamSupport.stream(playerRepository.findAll().spliterator(), false)
                .collect(Collectors.toList());
    }

    @Transactional
    public Optional<Player> getById(Long id){
        return playerRepository.findById(id);
    }

    @Transactional
    public Player add(Player player){
        return playerRepository.save(player);
    }

    @Transactional
    public void delete(Long id){
        playerRepository.deleteById(id);
    }

}
