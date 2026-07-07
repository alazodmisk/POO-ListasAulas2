package com.example.jogoDaVelha.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.jogoDaVelha.model.Partida;

@Repository
public interface PartidaRepository extends JpaRepository<Partida, Long> {
}
