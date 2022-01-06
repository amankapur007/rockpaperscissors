package com.aman.vocera.rockpaperscissor.repository;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.aman.vocera.rockpaperscissor.model.Game;

@Repository
public interface GameRepository extends CrudRepository<Game, Integer>{

	Optional<Game> findFirstByTokenOrderByIdDesc(String token);
}
