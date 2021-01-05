package com.example.playermarket.controllers;

import static org.junit.jupiter.api.Assertions.*;

import com.example.playermarket.models.Team;
import com.example.playermarket.models.Player;
import com.example.playermarket.services.PlayerService;
import com.example.playermarket.services.TeamService;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.util.LinkedList;
import java.util.List;
import java.util.Locale;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@SpringBootTest
@ContextConfiguration
@AutoConfigureMockMvc
class PlayerControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Spy
    @InjectMocks
    private PlayerController playerController;

    @Mock
    private PlayerService playerService;

    @Test
    public void when_findAll_should_returnAll2Players() throws Exception {
        List<Player> players = new LinkedList<>();
        players.add(mock(Player.class));
        players.add(mock(Player.class));
        when(playerService.getAll()).thenReturn(players);
        ResponseEntity<List<Player>> result = playerController.getAllPlayers();
        verify(playerService, times(1)).getAll();
        assertEquals("OK", result.getStatusCode().name());
        assertEquals(2, result.getBody().size());
        mockMvc.perform(MockMvcRequestBuilders.get("/player/all"));
    }

    @Test
    public void when_findAll_should_returnEmptyList(){
        List<Player> players = new LinkedList<>();
        when(playerService.getAll()).thenReturn(players);
        ResponseEntity<List<Player>> result = playerController.getAllPlayers();
        verify(playerService, times(1)).getAll();
        assertEquals("OK", result.getStatusCode().name());
        assertEquals(0, result.getBody().size());
    }

    @Test
    public void when_getById_should_returnPlayer1() throws Exception {
        Player player = mock(Player.class);
        when(player.getId()).thenReturn(1L);
        Optional<Player> optional = Optional.of(player);
        when(playerService.getById(1L)).thenReturn(optional);
        ResponseEntity<Player> result = playerController.getById(1L);
        verify(playerService, times(1)).getById(1L);
        assertEquals("OK", result.getStatusCode().name());
        assertEquals(1L, result.getBody().getId());
        mockMvc.perform(MockMvcRequestBuilders.get("/player/1"));
    }

    @Test
    public void when_getById_should_returnNull() throws Exception {
        Optional<Player> optional = Optional.empty();
        when(playerService.getById(1L)).thenReturn(optional);
        ResponseEntity<Player> result = playerController.getById(1L);
        verify(playerService, times(1)).getById(1L);
        assertEquals("OK", result.getStatusCode().name());
        assertEquals(null, result.getBody());
        mockMvc.perform(MockMvcRequestBuilders.get("/player/1"));
    }

    @Test
    public void when_insertPlayer_should_returnCreated() throws Exception {
        Player player = mock(Player.class);
        Player responsePlayer = mock(Player.class);
        when(playerService.add(player)).thenReturn(responsePlayer);
        ResponseEntity<Player> result = playerController.insertPlayer(player);
        verify(playerService, times(1)).add(player);
        assertEquals("CREATED", result.getStatusCode().name());
        assertEquals(responsePlayer, result.getBody());
        mockMvc.perform(MockMvcRequestBuilders.get("/player"));
    }

    @Test
    public void when_insertPlayer_should_returnBadRequest() throws Exception {
        Player player = mock(Player.class);
        when(playerService.add(player)).thenReturn(null);
        ResponseEntity<Player> result = playerController.insertPlayer(player);
        verify(playerService, times(1)).add(player);
        assertEquals("BAD_REQUEST", result.getStatusCode().name());
        assertEquals(null, result.getBody());
        mockMvc.perform(MockMvcRequestBuilders.get("/player"));
    }

    @Test
    public void when_insertPlayer_should_returnAccepted() throws Exception {
        Player player = mock(Player.class);
        Player responsePlayer = mock(Player.class);
        when(playerService.add(player)).thenReturn(responsePlayer);
        ResponseEntity<Player> result = playerController.updatePlayer(player);
        verify(playerService, times(1)).add(player);
        assertEquals("ACCEPTED", result.getStatusCode().name());
        assertEquals(responsePlayer, result.getBody());
        mockMvc.perform(MockMvcRequestBuilders.get("/player"));
    }

    @Test
    public void when_delete_should_returnNoContent() throws Exception {
        ResponseEntity<Void> result = playerController.deletePlayer(1L);
        verify(playerService, times(1)).delete(1L);
        assertEquals("NO_CONTENT", result.getStatusCode().name());
        assertEquals(null, result.getBody());
        mockMvc.perform(MockMvcRequestBuilders.get("/player/1"));
    }

    @Test
    public void when_chargeContractFee_should_returnNewContractFee() throws Exception {
        Player mockPlayer = mock(Player.class);
        when(mockPlayer.getId()).thenReturn(1L);
        when(mockPlayer.getContractFee()).thenReturn("100.0 TRY");
        when(mockPlayer.getAge()).thenReturn(10);
        when(mockPlayer.getTeam()).thenReturn("ABC");
        when(mockPlayer.getName()).thenReturn("X");
        when(mockPlayer.getMonthsOfExperience()).thenReturn(50);

        Player clone = new Player("X", 50, 10);
        clone.setContractFee("100 TRY");
        clone.setTeam("ABC");

        when(mockPlayer.clone()).thenReturn(clone);

        Player responsePlayer = mock(Player.class);
        when(playerService.add(clone)).thenReturn(responsePlayer);

        Team mockTeam = mock(Team.class);
        when(mockTeam.getName()).thenReturn("DEF");
        when(mockTeam.getId()).thenReturn(2L);
        when(mockTeam.getLocale()).thenReturn("US");
        when(mockTeam.getLocaleFromString()).thenReturn(new Locale("en", "US"));

        ResponseEntity<Player> result = playerController.chargeContractFee(mockPlayer, mockTeam);
        verify(playerService, times(1)).add(clone);
        assertEquals("ACCEPTED", result.getStatusCode().name());
        assertEquals( "550000.0 USD", clone.getContractFee());
        assertEquals("DEF", clone.getTeam());
        mockMvc.perform(MockMvcRequestBuilders.get("/player/1/contractFee"));
    }

    @Test
    public void when_chargeContractFee_should_returnUnprocessableEntity() throws Exception {
        Player mockPlayer = mock(Player.class);
        when(mockPlayer.getId()).thenReturn(1L);
        when(mockPlayer.getContractFee()).thenReturn("100.0 TRY");
        when(mockPlayer.getAge()).thenReturn(null);
        when(mockPlayer.getTeam()).thenReturn("ABC");
        when(mockPlayer.getName()).thenReturn("X");
        when(mockPlayer.getMonthsOfExperience()).thenReturn(50);

        Player clone = new Player("X", 50, null);
        clone.setContractFee("100 TRY");
        clone.setTeam("ABC");

        when(mockPlayer.clone()).thenReturn(clone);

        Player responsePlayer = mock(Player.class);
        when(playerService.add(clone)).thenReturn(responsePlayer);

        Team mockTeam = mock(Team.class);
        when(mockTeam.getName()).thenReturn("DEF");
        when(mockTeam.getId()).thenReturn(2L);
        when(mockTeam.getLocale()).thenReturn("US");
        when(mockTeam.getLocaleFromString()).thenReturn(new Locale("en", "US"));

        ResponseEntity<Player> result = playerController.chargeContractFee(mockPlayer, mockTeam);
        assertThrows(NullPointerException.class, () -> playerController.getTransferFee(anyInt(), any()));
        assertEquals("UNPROCESSABLE_ENTITY", result.getStatusCode().name());
        assertEquals(null, result.getBody());
        mockMvc.perform(MockMvcRequestBuilders.get("/player/1/contractFee"));
    }



}