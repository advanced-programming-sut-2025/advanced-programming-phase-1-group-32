package models;

import models.Account;

import java.util.ArrayList;

public class App {
    private final static ArrayList<Account> accountList = new ArrayList<>();


    /***
     * Returns null if the username doesn't exist.
     */
    public static Account getUserByUsername(String username){
        for(Account a : accountList){
            if(a.getUsername().equals(username)){
                return a;
            }
        }
        return null;
    }

    public static boolean doesUsernameExist(String username){
        return getUserByUsername(username) != null;
    }
}