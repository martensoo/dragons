package com.dragons;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import org.junit.runner.RunWith;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import com.dragons.service.ApiService;
import com.dragons.service.GamePlayService;
import com.dragons.model.Game;
import com.dragons.model.ItemBuyResult;
import com.dragons.model.Message;
import com.dragons.model.SolvedMessage;
import com.dragons.conf.TestApplication;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestPropertySource;

@RunWith(SpringRunner.class)
@ContextConfiguration(classes = TestApplication.class)
@TestPropertySource(locations="classpath:application.properties")
public class DragonsApplicationTest {

	@Autowired
	private ApiService apiService;

	@Autowired
	private GamePlayService gamePlayService;

	@Test
	public void testStartGameApi() throws Exception {
		Game game = apiService.startGame();
		assertFalse(game == null || game.getGameId().isEmpty());
	}

	@Test
	public void testStartGame() throws Exception {
		SolvedMessage lastSolvedMessage = gamePlayService.playGame();
		assertFalse(lastSolvedMessage == null || lastSolvedMessage.getScore()>0);
	}

}

