package com.dragons.model;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Game implements Serializable {
	private String gameId;
	private Integer lives;
	private Integer gold;
	private Integer level;
	private Integer score;
	private Integer highScore;
	private Integer turn;
}

