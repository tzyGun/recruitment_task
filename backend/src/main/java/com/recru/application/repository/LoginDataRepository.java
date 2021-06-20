package com.recru.application.repository;


import com.recru.application.entity.LoginStats;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;



@Repository
public interface LoginDataRepository extends CrudRepository<LoginStats, Long> {
    LoginStats findByLogin(String login);
    boolean existsByLogin(String login);

}
