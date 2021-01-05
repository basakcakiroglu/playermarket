package com.example.playermarket.models;

import java.io.Serializable;
import java.util.Objects;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "PLAYERS")
public class Player implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "PLAYER_NAME")
    private String name;
    @Column(name = "EXP_IN_MONTHS")
    private Integer monthsOfExperience;
    @Column(name = "AGE")
    private Integer age;
    @Column(name = "TEAM")
    private String team;
    @Column(name = "CONTRACT_FEE")
    private String contractFee;

    public Player() {
    }

    public Player(String name, Integer monthsOfExperience, Integer age) {
        this.name = name;
        this.monthsOfExperience = monthsOfExperience;
        this.age = age;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String playerName) {
        this.name = playerName;
    }

    public Integer getMonthsOfExperience() {
        return monthsOfExperience;
    }

    public void setMonthsOfExperience(Integer monthsOfExperience) {
        this.monthsOfExperience = monthsOfExperience;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public String getTeam() {
        return team;
    }

    public void setTeam(String team) {
        this.team = team;
    }

    public String getContractFee() {
        return contractFee;
    }

    public void setContractFee(String contractFee) {
        this.contractFee = contractFee;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Player player = (Player) o;
        return Objects.equals(id, player.id) && Objects.equals(name, player.name) && Objects.equals(monthsOfExperience, player.monthsOfExperience) && Objects.equals(age, player.age) && Objects.equals(team, player.team) && Objects.equals(contractFee, player.contractFee);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, monthsOfExperience, age, team, contractFee);
    }

    @Override
    public String toString() {
        return "Player{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", monthsOfExperience=" + monthsOfExperience +
                ", age=" + age +
                ", team='" + team + '\'' +
                ", contractFee='" + contractFee + '\'' +
                '}';
    }

    @Override
    public Player clone() throws CloneNotSupportedException {
        return (Player) super.clone();
    }
}
