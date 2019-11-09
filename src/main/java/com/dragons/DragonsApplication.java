package com.dragons;

import com.dragons.model.SolvedMessage;
import com.dragons.service.GamePlayService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.Banner;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import static java.lang.System.exit;

@SpringBootApplication
public class DragonsApplication implements CommandLineRunner {

	@Autowired
	private GamePlayService gamePlayService;

	public static void main(String[] args) throws Exception {
		SpringApplication app = new SpringApplication(DragonsApplication.class);
		app.setBannerMode(Banner.Mode.OFF);
		app.run(args);
	}

	@Override
	public void run(String... args) throws Exception {
		SolvedMessage lastSolvedMessage = gamePlayService.playGame();
		System.out.format("Score: %d", lastSolvedMessage.getScore());
		exit(0);
	}



}

