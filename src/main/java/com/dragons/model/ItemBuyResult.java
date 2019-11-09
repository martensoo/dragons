package com.dragons.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ItemBuyResult implements Serializable {
	private Boolean shoppingSuccess;
	private Integer lives;
	private Integer gold;
	private Integer turn;
	private Integer level;
}

