package com.example.playermarket.controllers;

import com.example.playermarket.models.Team;
import com.example.playermarket.services.TeamService;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.util.LinkedList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@SpringBootTest
@ContextConfiguration
@AutoConfigureMockMvc
class TeamControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Spy
    @InjectMocks
    private TeamController teamController;

    @Mock
    private TeamService teamService;

    @Test
    public void when_findAll_should_returnAll4MockTeams() throws Exception {
        List<Team> teams = new LinkedList<>();
        teams.add(mock(Team.class));
        teams.add(mock(Team.class));
        teams.add(mock(Team.class));
        teams.add(mock(Team.class));
        when(teamService.getAll()).thenReturn(teams);
        ResponseEntity<List<Team>> result = teamController.getAllTeams();
        verify(teamService, times(1)).getAll();
        assertEquals("OK", result.getStatusCode().name());
        assertEquals(4, result.getBody().size());
        mockMvc.perform(MockMvcRequestBuilders.get("/team/all"));
    }

    @Test
    public void when_findAll_should_returnEmptyList(){
        List<Team> teams = new LinkedList<>();
        when(teamService.getAll()).thenReturn(teams);
        ResponseEntity<List<Team>> result = teamController.getAllTeams();
        verify(teamService, times(1)).getAll();
        assertEquals("OK", result.getStatusCode().name());
        assertEquals(0, result.getBody().size());
    }

    @Test
    public void when_getTeamByName_should_returnTeamNamedAAA() throws Exception {
        Team mockTeam = mock(Team.class);
        when(mockTeam.getName()).thenReturn("AAA");

        when(teamService.getByName("AAA")).thenReturn(mockTeam);
        ResponseEntity<Team> result = teamController.getTeamByName("AAA");
        verify(teamService, times(1)).getByName("AAA");
        assertEquals("OK", result.getStatusCode().name());
        assertEquals("AAA", result.getBody().getName());
        mockMvc.perform(MockMvcRequestBuilders.get("/teamByName/AAA}"));
    }

    @Test
    public void when_getTeamByName_should_returnNoTeam(){
        ResponseEntity<Team> result = teamController.getTeamByName("AAA");
        verify(teamService, times(1)).getByName("AAA");
        assertEquals("OK", result.getStatusCode().name());
        assertEquals(null, result.getBody());
    }

    @Test
    public void when_insertTeam_should_returnCreated() throws Exception {
        Team mockTeam = mock(Team.class);
        when(mockTeam.getName()).thenReturn("AAA");

        Team mockResponseTeam = mock(Team.class);
        when(mockResponseTeam.getName()).thenReturn("AAA");

        when(teamService.save(mockTeam)).thenReturn(mockResponseTeam);
        ResponseEntity<Team> result = teamController.insertTeam(mockTeam);
        verify(teamService, times(1)).save(mockTeam);
        assertEquals("CREATED", result.getStatusCode().name());
        assertEquals(mockResponseTeam, result.getBody());
        mockMvc.perform(MockMvcRequestBuilders.get("/team"));
    }

    @Test
    public void when_insertTeam_should_returnBadRequest(){
        Team mockTeam = mock(Team.class);
        when(mockTeam.getName()).thenReturn("AAA");

        ResponseEntity<Team> result = teamController.insertTeam(mockTeam);
        verify(teamService, times(1)).save(mockTeam);
        assertEquals("BAD_REQUEST", result.getStatusCode().name());
        assertEquals(null, result.getBody());
    }

    @Test
    public void when_updateTeam_should_returnAccepted() throws Exception {
        Team mockTeam = mock(Team.class);
        when(mockTeam.getName()).thenReturn("AAA");

        Team mockResponseTeam = mock(Team.class);
        when(mockResponseTeam.getName()).thenReturn("AAA");

        when(teamService.save(mockTeam)).thenReturn(mockResponseTeam);
        ResponseEntity<Team> result = teamController.updateTeam(mockTeam);
        verify(teamService, times(1)).save(mockTeam);
        assertEquals("ACCEPTED", result.getStatusCode().name());
        assertEquals(mockResponseTeam, result.getBody());
        mockMvc.perform(MockMvcRequestBuilders.get("/team"));
    }

    @Test
    public void when_updateTeam_should_returnBadRequest(){
        Team mockTeam = mock(Team.class);
        when(mockTeam.getName()).thenReturn("AAA");

        ResponseEntity<Team> result = teamController.updateTeam(mockTeam);
        verify(teamService, times(1)).save(mockTeam);
        assertEquals("BAD_REQUEST", result.getStatusCode().name());
        assertEquals(null, result.getBody());
    }

    @Test
    public void when_delete_should_returnNoContent() throws Exception {
        ResponseEntity<Void> result = teamController.deleteTeam(1L);
        verify(teamService, times(1)).delete(1L);
        assertEquals("NO_CONTENT", result.getStatusCode().name());
        assertEquals(null, result.getBody());
        mockMvc.perform(MockMvcRequestBuilders.get("/team/1"));
    }


}