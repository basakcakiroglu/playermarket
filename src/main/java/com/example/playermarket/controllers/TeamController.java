package com.example.playermarket.controllers;

import com.example.playermarket.models.Team;
import com.example.playermarket.services.TeamService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class TeamController {

    TeamService teamService;

    @Autowired
    public TeamController(TeamService teamService) {
        this.teamService = teamService;
    }

    @GetMapping("/team/all")
    public ResponseEntity<List<Team>> getAllTeams() {
        List<Team> all = teamService.getAll();
        return new ResponseEntity<>(all, HttpStatus.OK);
    }

    @GetMapping("/teamByName/{name}")
    public ResponseEntity<Team> getTeamByName(@PathVariable String name) {
        Team responseTeam = teamService.getByName(name);
        return new ResponseEntity<>(responseTeam, HttpStatus.OK);
    }

    @PostMapping("/team")
    public ResponseEntity<Team> insertTeam(@RequestBody Team team) {
        Team responseTeam = teamService.save(team);
        boolean transactionStatus = responseTeam != null;
        HttpStatus status = transactionStatus ? HttpStatus.CREATED : HttpStatus.BAD_REQUEST;
        return new ResponseEntity<>(responseTeam, status);
    }

    @PutMapping("/team")
    public ResponseEntity<Team> updateTeam(@RequestBody Team team) {
        Team responseTeam = teamService.save(team);
        boolean transactionStatus = responseTeam != null;
        HttpStatus status = transactionStatus ? HttpStatus.ACCEPTED : HttpStatus.BAD_REQUEST;
        return new ResponseEntity<>(responseTeam, status);
    }

    @DeleteMapping("/team/{id}")
    public ResponseEntity<Void> deleteTeam(@PathVariable Long id) {
        teamService.delete(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

}
