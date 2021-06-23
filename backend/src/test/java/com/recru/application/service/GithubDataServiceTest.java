package com.recru.application.service;

import com.recru.application.dto.GithubLoginData;
import com.recru.application.entity.LoginStats;
import com.recru.application.model.GithubDataResponse;
import com.recru.application.repository.LoginDataRepository;
import org.apache.juli.logging.Log;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class GithubDataServiceTest {

    @Mock
    private LoginDataRepository loginDataRepository;

    @Mock
    private GithubAPIService githubAPIService;

    private GithubDataService githubDataService;

    @BeforeEach
    void init() {
        githubDataService = new GithubDataService(loginDataRepository, githubAPIService);
    }

    @Test
    void shouldReturnGithubLoginData() {
        //GIVEN
        GithubDataResponse githubDataResponse = new GithubDataResponse();
        githubDataResponse.setLogin("test_login");
        githubDataResponse.setId(1L);

        //WHEN
        when(githubAPIService.callGithubServiceAPI(anyString())).thenReturn(githubDataResponse);
        GithubLoginData githubLoginData = githubDataService.getLogin("test_login");

        //THEN
        assertThat(githubLoginData).isNotNull();
        assertThat(githubLoginData.getLogin()).isEqualTo("test_login");
        assertThat(githubLoginData.getId()).isEqualTo(1L);

    }

    @Test
    void shouldModifyExistingLoginStats() {
        //GIVEN
        LoginStats loginStats = new LoginStats();
        loginStats.login("test_login");
        when(loginDataRepository.existsByLogin(anyString())).thenReturn(true);

        LoginStats loginStatsFromRepo = new LoginStats();
        loginStatsFromRepo.requestCount(1);
        when(loginDataRepository.findByLogin(anyString())).thenReturn(loginStatsFromRepo);

        //WHEN
        githubDataService.saveUserQuery(loginStats);

        //THEN
        verify(loginDataRepository, times(1)).findByLogin(anyString());
        verify(loginDataRepository, times(1)).save(any());

    }

    @Test
    void shouldCreateLoginStatsIfNotExists() {
        //GIVEN
        LoginStats loginStats = new LoginStats();
        loginStats.login("test_login");
        when(loginDataRepository.existsByLogin(anyString())).thenReturn(false);

        //WHEN
        githubDataService.saveUserQuery(loginStats);

        //THEN
        verify(loginDataRepository, times(0)).findByLogin(anyString());
        verify(loginDataRepository, times(1)).save(any());

    }
}
