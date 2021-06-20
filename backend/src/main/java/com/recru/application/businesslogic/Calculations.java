package com.recru.application.businesslogic;

public class Calculations {

    private Calculations(){}

    public static double doCalculations(double numberOfFollowers, double numberOfPublicRepos) {
        return 6 / numberOfFollowers * (2 + numberOfPublicRepos);
    }
}
