package com.example.playermarket.controllers;

import com.example.playermarket.models.Player;
import com.example.playermarket.models.Team;
import com.example.playermarket.services.PlayerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Currency;
import java.util.List;
import java.util.Optional;

@RestController
public class PlayerController {

    PlayerService playerService;

    @Autowired
    public PlayerController(PlayerService playerService) {
        this.playerService = playerService;
    }

    @GetMapping("/player/all")
    public ResponseEntity<List<Player>> getAllPlayers() {
        return new ResponseEntity<>(playerService.getAll(), HttpStatus.OK);
    }

    @GetMapping("/player/{id}")
    public ResponseEntity<Player> getById(@PathVariable Long id) {
        Player byId = playerService.getById(id).orElse(null);
        return new ResponseEntity<>(byId, HttpStatus.OK);
    }

    @PostMapping("/player")
    public ResponseEntity<Player> insertPlayer(@RequestBody Player player) {
        Player responsePlayer = playerService.add(player);
        boolean transactionStatus = responsePlayer != null;
        HttpStatus status = transactionStatus ? HttpStatus.CREATED : HttpStatus.BAD_REQUEST;
        return new ResponseEntity<>(responsePlayer, status);
    }

    @PutMapping("/player")
    public ResponseEntity<Player> updatePlayer(@RequestBody Player player) {
        Player responsePlayer = playerService.add(player);
        boolean transactionStatus = responsePlayer != null;
        HttpStatus status = transactionStatus ? HttpStatus.ACCEPTED : HttpStatus.BAD_REQUEST;
        return new ResponseEntity<>(responsePlayer, status);
    }

    @DeleteMapping("/player/{id}")
    public ResponseEntity<Void> deletePlayer(@PathVariable Long id) {
        playerService.delete(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @PutMapping("/player/{id}/contractFee")
    public ResponseEntity<Player> chargeContractFee(@RequestBody Player player, Team team) {
        try {
            double transferFee = getTransferFee(player.getAge(), player.getMonthsOfExperience());
            double commission = (transferFee * 10) / 100.0;
            Currency currency = Currency.getInstance(team.getLocaleFromString());
            Player clonePlayer = player.clone();
            clonePlayer.setContractFee((transferFee + commission) + " " + currency.getCurrencyCode());
            clonePlayer.setTeam(team.getName());
            Player responsePlayer = playerService.add(clonePlayer);
            boolean transactionStatus = responsePlayer != null;
            HttpStatus status = transactionStatus ? HttpStatus.ACCEPTED : HttpStatus.BAD_REQUEST;
            return new ResponseEntity<>(responsePlayer, status);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.UNPROCESSABLE_ENTITY);
        }
    }

    public double getTransferFee(Integer age, Integer monthsOfExperience)
            throws NullPointerException {
        return monthsOfExperience * 100000.0 / age;
    }


}
