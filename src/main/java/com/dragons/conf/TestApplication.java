package com.dragons.conf;

import com.dragons.service.ApiService;
import com.dragons.service.GamePlayService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class TestApplication {
	@Bean
	public ApiService apiService() {
		return new ApiService();
	}

	@Bean
	public GamePlayService gamePlayService() {
		return new GamePlayService();
	}

}
