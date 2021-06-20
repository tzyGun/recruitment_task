package com.recru.application.service;

import com.recru.application.repository.LoginDataRepository;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;


public class GithubDataServiceTest {

    @Mock
    private LoginDataRepository loginDataRepository;

    private GithubDataService githubDataService;

    @BeforeEach
    void init() {
        githubDataService = new GithubDataService(loginDataRepository);
    }

    @Test
    void shouldReturnTrue() {

//         when(githubDataService.getLogin(anyString())).thenReturn("ELO");
//        String message = githubDataService.getLogin("marian");
//
//        assertThat(message).isEqualTo("ELO");

    }

    @Test
    void shouldFetchData() {
        RestTemplate restTemplate = new RestTemplate();
        String fooResourceUrl
                = "https://api.github.com/users";
        ResponseEntity<String> response
                = restTemplate.getForEntity(fooResourceUrl + "/mat3e", String.class);
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
    }
}
