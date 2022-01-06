package com.aman.vocera.rockpaperscissor.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.aman.vocera.rockpaperscissor.exception.InvalidTokenException;
import com.aman.vocera.rockpaperscissor.model.Game;
import com.aman.vocera.rockpaperscissor.model.Moves;
import com.aman.vocera.rockpaperscissor.model.Token;
import com.aman.vocera.rockpaperscissor.service.RockPaperScissorService;

@RestController
public class RockPaperScissorController {

	@Autowired
	RockPaperScissorService rockPaperScissorService;
	@GetMapping("/start")
	public ResponseEntity<Token> start(){
		Token token = rockPaperScissorService.start();
		return new ResponseEntity<Token>(token, HttpStatus.OK);
	}
	
	@GetMapping("v1/{token}/{playerMove}")
	public ResponseEntity<Game> start(@PathVariable("token") String token,@PathVariable("playerMove") Moves playerMove){
		//check valid token
		Token tokenDetails =  rockPaperScissorService.tokenDetails(token);
		if(tokenDetails==null) {
			throw new InvalidTokenException();
		}
		//check game is not over
		Game g = null;
		if(tokenDetails.getMessage()=="ENDED") {
			g = rockPaperScissorService.getGameDetails(token);
			return new ResponseEntity<Game>(g, HttpStatus.OK);
		}
		g = rockPaperScissorService.serverMove(token, playerMove);
		return new ResponseEntity<Game>(g, HttpStatus.OK);
	}
}
