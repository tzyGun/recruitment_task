package com.recru.application.dto;

import java.time.LocalDate;

public class GithubLoginData {
    private Long id;
    private String login;
    private String name;
    private String type;
    private String avatarUrl;
    private LocalDate createdAt;
    private Double calculations;

    public Long getId() {
        return id;
    }

    public GithubLoginData id(Long id) {
        this.id = id;
        return this;
    }

    public String getLogin() {
        return login;
    }

    public GithubLoginData login(String login) {
        this.login = login;
        return this;
    }

    public String getName() {
        return name;
    }

    public GithubLoginData name(String name) {
        this.name = name;
        return this;
    }

    public String getType() {
        return type;
    }

    public GithubLoginData type(String type) {
        this.type = type;
        return this;
    }

    public String getAvatarUrl() {
        return avatarUrl;
    }

    public GithubLoginData avatarUrl(String avatarUrl) {
        this.avatarUrl = avatarUrl;
        return this;
    }

    public LocalDate getCreatedAt() {
        return createdAt;
    }

    public GithubLoginData createdAt(LocalDate createdAt) {
        this.createdAt = createdAt;
        return this;
    }

    public Double getCalculations() {
        return calculations;
    }

    public GithubLoginData calculations(Double calculations) {
        this.calculations = calculations;
        return this;
    }
}
