package com.aman.vocera.rockpaperscissor.model;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name="game")
public class Game {
	@Id
	@SequenceGenerator(name = "game_sequence", sequenceName = "game_sequence")
	@GeneratedValue(strategy = GenerationType.SEQUENCE)
	Integer id;
	@Enumerated(EnumType.STRING)
	Moves playerMove;
	@Enumerated(EnumType.STRING)
	Moves serverMove;
	String token;
	Integer serverScore;
	Integer playerScore;
	String winner;
	public Moves getPlayerMove() {
		return playerMove;
	}
	public void setPlayerMove(Moves playerMove) {
		this.playerMove = playerMove;
	}
	public Moves getServerMove() {
		return serverMove;
	}
	public void setServerMove(Moves serverMove) {
		this.serverMove = serverMove;
	}
	public String getToken() {
		return token;
	}
	public void setToken(String token) {
		this.token = token;
	}
	public Integer getServerScore() {
		return serverScore;
	}
	public void setServerScore(Integer serverScore) {
		this.serverScore = serverScore;
	}
	public Integer getPlayerScore() {
		return playerScore;
	}
	public void setPlayerScore(Integer playerScore) {
		this.playerScore = playerScore;
	}
	public String getWinner() {
		return winner;
	}
	public void setWinner(String winner) {
		this.winner = winner;
	}
	public Game(Moves playerMove, Moves serverMove, String token, Integer serverScore, Integer playerScore,
			String winner) {
		super();
		this.playerMove = playerMove;
		this.serverMove = serverMove;
		this.token = token;
		this.serverScore = serverScore;
		this.playerScore = playerScore;
		this.winner = winner;
	}
	public Game() {
		super();
	}
	
	
	
	
}
