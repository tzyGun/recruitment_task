package com.recru.application.rest;


import com.recru.application.dto.GithubLoginData;
import com.recru.application.repository.LoginDataRepository;
import com.recru.application.service.GithubDataService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class GithubRESTController {

    private final GithubDataService githubDataService;

    @Autowired
    public GithubRESTController(GithubDataService githubDataService) {
        this.githubDataService = githubDataService;
    }

    @GetMapping("/users/{login}")
    GithubLoginData getUserByLogin(@PathVariable String login) {
        return githubDataService.getLogin(login);
    }
}
