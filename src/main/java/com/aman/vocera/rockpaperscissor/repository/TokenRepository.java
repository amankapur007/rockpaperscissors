package com.aman.vocera.rockpaperscissor.repository;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;

import com.aman.vocera.rockpaperscissor.model.Token;

public interface TokenRepository extends CrudRepository<Token, String>{
	
	Optional<Token> findByToken(String token);
}
