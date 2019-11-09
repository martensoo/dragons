package com.dragons.service;

import com.dragons.model.Game;
import com.dragons.model.ItemBuyResult;
import com.dragons.model.Message;
import com.dragons.model.SolvedMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpStatusCodeException;
import org.springframework.web.client.HttpClientErrorException;

import java.util.*;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Service
public class GamePlayService {
	@Autowired
	private ApiService apiService;
	private static final Logger LOG = LoggerFactory.getLogger(GamePlayService.class);

	public SolvedMessage playGame() {

		Map<String, Integer> probabilities = new HashMap<>();
		probabilities.put("Sure thing", 100);
		probabilities.put("Piece of cake", 80);
		probabilities.put("Walk in the park", 70);
		probabilities.put("Quite likely", 70);
		probabilities.put("Rather detrimental", 50);
		probabilities.put("Playing with fire", 40);
		probabilities.put("Gamble", 30);
		probabilities.put("Risky", 30);
		probabilities.put("Hmmm....", 30);
		probabilities.put("Suicide mission", 10);

		List<String> cheapRandomitems = new ArrayList(Arrays.asList("cs", "gas", "wax", "tricks", "wingpot"));
		List<String> expensiveRandomitems = new ArrayList(Arrays.asList("ch", "rf", "iron", "mtrix", "wingpotmax"));

		Game game = apiService.startGame();
		boolean gameEnded = false;
		SolvedMessage solvedMessage = new SolvedMessage();
		do {
			try {
				List<Message> messages = apiService.getMessages(game.getGameId());
				if (!messages.isEmpty()) {
					messages.sort(Comparator.comparing(m -> probabilities.getOrDefault(m.getProbability(), 50), Comparator.reverseOrder()));

					solvedMessage = apiService.solveMessage(game.getGameId(), messages.get(0).getAdId());
					LOG.info("Result: " + solvedMessage.getSuccess() + " Message: " + messages.get(0).getMessage() + " Gold: " + solvedMessage.getGold() + " Score: " + solvedMessage.getScore());

					if (solvedMessage.getGold() >= 50 && solvedMessage.getLives() <= 4) {
						ItemBuyResult itemBuyResult = apiService.shopItem(game.getGameId(), "hpot");
					} else if (solvedMessage.getGold() >= 300 && expensiveRandomitems.size() > 0) {
						Random rand = new Random();
						int itemId = rand.nextInt(expensiveRandomitems.size());
						ItemBuyResult itemBuyResult = apiService.shopItem(game.getGameId(), expensiveRandomitems.get(itemId));
						expensiveRandomitems.remove(itemId);
					} else if (solvedMessage.getGold() >= 100 && cheapRandomitems.size() > 0) {
						Random rand = new Random();
						int itemId = rand.nextInt(cheapRandomitems.size());
						ItemBuyResult itemBuyResult = apiService.shopItem(game.getGameId(), cheapRandomitems.get(itemId));
						cheapRandomitems.remove(itemId);
					}
				} else {
					gameEnded = true;
				}
			} catch (HttpStatusCodeException e) {
				if (e.getStatusCode().value() == 410) {
					gameEnded = true;
				} else {
					LOG.error(e.getMessage());
				}
			}
		} while (!gameEnded);
		return solvedMessage;
	}

}
