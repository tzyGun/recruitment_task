package com.recru.application.entity;

import javax.persistence.*;

@Entity
@Table(indexes = @Index(columnList = "LOGIN"))
public class LoginStats {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name="LOGIN")
    private String login;

    @Column(name="REQUEST_COUNT")
    private int requestCount;

    public Long getId() {
        return id;
    }

    public LoginStats id(Long id) {
        this.id = id;
        return this;
    }

    public String getLogin() {
        return login;
    }

    public LoginStats login(String login) {
        this.login = login;
        return this;
    }

    public int getRequestCount() {
        return requestCount;
    }

    public LoginStats requestCount(int requestCount) {
        this.requestCount = requestCount;
        return this;
    }
}
