package com.dragons.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Message implements Serializable {
	private String adId;
	private String message;
	private Integer reward;
	private Integer expiresIn;
	private String encrypted;
	private String probability;
}

