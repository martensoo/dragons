package com.dragons.service;

import com.dragons.model.Game;
import com.dragons.model.ItemBuyResult;
import com.dragons.model.Message;
import com.dragons.model.SolvedMessage;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.retry.annotation.EnableRetry;
import org.springframework.retry.annotation.Retryable;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

@Service
@EnableRetry
@Component
public class ApiService {

    @Value("${api-url}")
    private String API_URL;

    @Retryable(maxAttempts=3,value=Exception.class)
    public Game startGame(){
        RestTemplate restTemplate = new RestTemplate();
        HttpEntity<String> request = new HttpEntity<>(new String());
        return restTemplate.postForObject(API_URL + "game/start", request, Game.class);
    }

    @Retryable(maxAttempts=3,value=Exception.class)
    public List<Message> getMessages(String gameId){
        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<Message[]> entity = restTemplate.getForEntity(API_URL + gameId + "/messages", Message[].class);
        return entity.getBody() != null? Arrays.asList(entity.getBody()) :
                Collections.emptyList();
    }
    @Retryable(maxAttempts=3,value=Exception.class)
    public SolvedMessage solveMessage(String gameId, String adId){
        RestTemplate restTemplate = new RestTemplate();
        HttpEntity<String> request = new HttpEntity<>(new String());
        return restTemplate.postForObject(API_URL + gameId + "/solve/" + adId, request, SolvedMessage.class);
    }

    @Retryable(maxAttempts=3,value=Exception.class)
    public ItemBuyResult shopItem(String gameId, String itemId){
        RestTemplate restTemplate = new RestTemplate();
        HttpEntity<String> request = new HttpEntity<>(new String());
        return restTemplate.postForObject(API_URL + gameId + "/shop/buy/" + itemId, request, ItemBuyResult.class);
    }

}
