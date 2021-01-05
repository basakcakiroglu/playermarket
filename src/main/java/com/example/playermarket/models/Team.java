package com.example.playermarket.models;

import javax.persistence.*;
import java.util.Arrays;
import java.util.Locale;
import java.util.Objects;


@Entity
@Table(name = "TEAMS")
public class Team {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "NAME")
    private String name;
    @Column(name = "LOCALE")
    private String locale;

    public Team() {
    }

    public Team(String teamName, String locale) {
        this.name = teamName;
        this.locale = locale;
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

    public void setName(String teamName) {
        this.name = teamName;
    }

    public String getLocale() {
        return locale;
    }

    public void setLocale(String locale) {
        this.locale = locale;
    }

    public Locale getLocaleFromString(){
       return Arrays.stream(Locale.getAvailableLocales()).filter(loc -> loc.getCountry().equals(this.getLocale()))
               .findFirst().get();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Team team = (Team) o;
        return Objects.equals(id, team.id) && Objects.equals(name, team.name) && Objects.equals(locale, team.locale);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, locale);
    }

    @Override
    public String toString() {
        return "Team{" +
                "id=" + id +
                ", teamName='" + name + '\'' +
                ", locale=" + locale +
                '}';
    }
}
