package com.recru.application.service;


import com.recru.application.model.GithubDataResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class GithubAPIService {

    private final String GITHUB_USERS_URL_API = "https://api.github.com/users/";

    public GithubAPIService() {
    }

    public GithubDataResponse callGithubServiceAPI(String login) {
        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<GithubDataResponse> response
                = restTemplate.getForEntity(GITHUB_USERS_URL_API.concat(login), GithubDataResponse.class);
        return response.getBody();
    }
}
