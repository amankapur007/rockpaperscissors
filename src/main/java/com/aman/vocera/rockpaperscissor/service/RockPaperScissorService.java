package com.aman.vocera.rockpaperscissor.service;

import java.util.Optional;
import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.aman.vocera.rockpaperscissor.model.Game;
import com.aman.vocera.rockpaperscissor.model.Moves;
import com.aman.vocera.rockpaperscissor.model.Token;
import com.aman.vocera.rockpaperscissor.repository.GameRepository;
import com.aman.vocera.rockpaperscissor.repository.TokenRepository;

@Service
public class RockPaperScissorService {

	@Autowired
	TokenRepository tokenRepository;
	
	@Autowired
	GameRepository gameRepository;
	
	public Token start() {
		Token token = new Token();
		tokenRepository.save(token);
		return token;
	}

	public boolean isValidToken(String token) {
		if(token!=null) {
			Optional<Token> t= tokenRepository.findByToken(token);
			if(!t.isEmpty()) {
				return true;
			}
		}
		return false;
	}
	
	public Token tokenDetails(String token) {
		if(token!=null) {
			Optional<Token> t= tokenRepository.findByToken(token);
			if(!t.isEmpty()) {
				return t.get();
			}
		}
		return null;
	}

	public Game serverMove(String token, Moves playerMove) {
		//get prev score
		Optional<Game> game = gameRepository.findFirstByTokenOrderByIdDesc(token);
		Game g = null;
		if(game.isEmpty()) {
			g = new Game();
			g.setServerScore(0);
			g.setPlayerScore(0);
			g.setToken(token);
		}else {
			g =  game.get();
		}
		g.setPlayerMove(playerMove);
		Moves serverMove = generateServerMove(playerMove);
		g.setServerMove(serverMove);
	    g = checkWinner(g);
	    if(g.getPlayerScore() == 3) {
	    	g.setWinner("PLAYER");
		    Token tok = tokenRepository.findByToken(token).get();
		    tok.setMessage("ENDED");
	    }else if(g.getServerScore() == 3){
			g.setWinner("SERVER");
		    Token tok = tokenRepository.findByToken(token).get();
		    tok.setMessage("ENDED");
		}
	    gameRepository.save(g);
	    return g;
	}

	private Game checkWinner(Game g) {
		switch (g.getServerMove()) {
		case ROCK:
			if(g.getPlayerMove().equals(Moves.SCISSOR)) {
				g.setServerScore(g.getServerScore()+1);
			}else if(!g.getPlayerMove().equals(Moves.ROCK)) {
				g.setPlayerScore(g.getPlayerScore()+1);				
			}
			break;
		case PAPER:
			if(g.getPlayerMove().equals(Moves.ROCK)) {
				g.setServerScore(g.getServerScore()+1);
			}else if(!g.getPlayerMove().equals(Moves.PAPER)) {
				g.setPlayerScore(g.getPlayerScore()+1);
			}
			break;
		case SCISSOR:
			if(g.getPlayerMove().equals(Moves.PAPER)) {
				g.setServerScore(g.getServerScore()+1);
			}else if(!g.getPlayerMove().equals(Moves.SCISSOR)) {
				g.setPlayerScore(g.getPlayerScore()+1);
			}
			break;
		default:
			break;
		}
		return g;
		
	}

	private Moves generateServerMove(Moves plMoves) {
		return Moves.values()[new Random().nextInt(Moves.values().length)];
	}

	public Game getGameDetails(String token) {
		// TODO Auto-generated method stub
		Game g = gameRepository.findFirstByTokenOrderByIdDesc(token).get();
		return g;
	}
}
