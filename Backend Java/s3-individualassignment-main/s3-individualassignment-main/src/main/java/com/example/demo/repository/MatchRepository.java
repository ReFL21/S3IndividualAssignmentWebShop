package com.example.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MatchRepository extends JpaRepository<FootballMatchEntity,Long> {


    FootballMatchEntity save(FootballMatchEntity footballMatchEntity);
    List<FootballMatchEntity> findAll();

}
