package com.dragons.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SolvedMessage implements Serializable {
	private Boolean success;
	private Integer lives;
	private Integer gold;
	private Integer score;
	private Integer highScore;
	private Integer turn;
	private String message;
}

