package com.guiviana.jogoDaVelha.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.guiviana.jogoDaVelha.model.Partida;

@Repository
public interface PartidaRepository extends JpaRepository<Partida, Long> {

}
