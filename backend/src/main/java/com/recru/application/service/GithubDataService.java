package com.recru.application.service;


import com.recru.application.businesslogic.Calculations;
import com.recru.application.dto.GithubLoginData;
import com.recru.application.entity.LoginStats;
import com.recru.application.model.GithubDataResponse;
import com.recru.application.repository.LoginDataRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.time.LocalDate;

@Service
public class GithubDataService {

    private final LoginDataRepository loginDataRepository;

    public GithubDataService(LoginDataRepository loginDataRepository) {
        this.loginDataRepository = loginDataRepository;
    }

    public GithubLoginData getLogin(String login) {
        GithubLoginData githubLoginData = new GithubLoginData();
        GithubDataResponse responseFromGithub = callGithubServiceAPI(login);
        LoginStats loginStats = new LoginStats();

        saveUserQuery(loginStats
                .login(responseFromGithub.getLogin()));

        return githubLoginData.login(responseFromGithub.getLogin())
                .avatarUrl(responseFromGithub.getAvatar_url())
                .id(responseFromGithub.getId())
                .name(responseFromGithub.getName())
                .type(responseFromGithub.getType())
                .calculations(Calculations
                        .doCalculations(responseFromGithub.getFollowers(),
                                responseFromGithub.getPublic_repos()))
                .createdAt(LocalDate.now());
    }

    private void saveUserQuery(LoginStats loginStats) {
        if(loginDataRepository.existsByLogin(loginStats.getLogin())) {
            var loginStats1 = loginDataRepository.findByLogin(loginStats.getLogin());
            loginStats1.requestCount(loginStats1.getRequestCount() + 1);
            loginDataRepository.save(loginStats1);
        } else {
            loginDataRepository.save(loginStats);
        }
    }

    private GithubDataResponse callGithubServiceAPI(String login) {
        RestTemplate restTemplate = new RestTemplate();
        String fooResourceUrl
                = "https://api.github.com/users/";
        ResponseEntity<GithubDataResponse> response
                = restTemplate.getForEntity(fooResourceUrl + login, GithubDataResponse.class);
        return response.getBody();
    }
}
