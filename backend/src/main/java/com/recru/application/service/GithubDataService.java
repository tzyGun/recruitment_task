package com.recru.application.service;


import com.recru.application.businesslogic.Calculations;
import com.recru.application.dto.GithubLoginData;
import com.recru.application.entity.LoginStats;
import com.recru.application.model.GithubDataResponse;
import com.recru.application.repository.LoginDataRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import javax.transaction.Transactional;
import java.time.LocalDate;

@Service
public class GithubDataService {

    private final LoginDataRepository loginDataRepository;
    private final String GITHUB_USERS_URL_API = "https://api.github.com/users/";


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

    @Transactional
    public void saveUserQuery(LoginStats loginStats) {
        if (loginDataRepository.existsByLogin(loginStats.getLogin())) {
            var loginStats1 = loginDataRepository.findByLogin(loginStats.getLogin());
            loginStats1.requestCount(loginStats1.getRequestCount() + 1);
            loginDataRepository.save(loginStats1);
        } else {
            loginStats.requestCount(1);
            loginDataRepository.save(loginStats);
        }
    }

    private GithubDataResponse callGithubServiceAPI(String login) {
        RestTemplate restTemplate = new RestTemplate();

        ResponseEntity<GithubDataResponse> response
                = restTemplate.getForEntity(GITHUB_USERS_URL_API.concat(login), GithubDataResponse.class);
        return response.getBody();
    }
}
