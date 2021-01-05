package com.example.playermarket.services;

import com.example.playermarket.models.Team;
import com.example.playermarket.repositories.TeamRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Service
public class TeamService {

    TeamRepository teamRepository;

    @Autowired
    public TeamService(TeamRepository teamRepository) {
        this.teamRepository = teamRepository;
    }

    @Transactional
    public List<Team> getAll() {
        return StreamSupport.stream(teamRepository.findAll().spliterator(), false)
                .collect(Collectors.toList());
    }

    @Transactional
    public Team getByName(String name) {
        return teamRepository.findByName(name);
    }

    @Transactional
    public Team save(Team team) {
        return teamRepository.save(team);
    }

    @Transactional
    public Team update(Team team) {
        return teamRepository.save(team);
    }

    @Transactional
    public void delete(Long id) {
        teamRepository.deleteById(id);
    }


}
