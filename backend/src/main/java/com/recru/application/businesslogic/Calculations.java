package com.recru.application.businesslogic;

public class Calculations {

    private Calculations() {
    }

    public static double doCalculations(int numberOfFollowers, int numberOfPublicRepos) {
        if (numberOfFollowers == 0) {
            return 0;
        } else {
            return (double) 6 / numberOfFollowers * (2 + numberOfPublicRepos);
        }
    }
}
