package controllers;

import models.Account;
import models.App;
import models.Result;
import models.enums.Gender;
import models.enums.SecurityQuestions;

import java.util.Queue;
import java.util.Random;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class LoginMenuController implements Controller{
    @Override
    public Result changeMenu() {
        //TODO
        return null;
    }

    public Result register(String username, String password, String confirmPassword, String name, String email, String gender) {

        if (!Account.isUsernameValid(username).isSuccessful()) {
            return Account.isUsernameValid(username);
        }

        if (!Account.isPasswordValid(password).isSuccessful()) {
            return Account.isPasswordValid(password);
        }

        if (!confirmPassword.equals(password)) {
            return new Result(false, "Passwords do not match");
        }

        if (!Account.isEmailValid(email).isSuccessful()) {
            return Account.isEmailValid(email);
        }

        Gender genderEnum = Gender.getGender(gender);
        if (genderEnum == null) {
            return new Result(false, "Invalid gender! type \"male\" or \"female\" for gender!");
        }
        

        Account account = new Account(genderEnum, email,name, password, username);

        StringBuilder message = new StringBuilder("Account registered successfully! now you can choose a security question:");
        message.append("\n").append(SecurityQuestions.getQuestionList());
        return new Result(true, message.toString());
    }

    public Result suggestUsername(String username) {
        if(!App.doesUsernameExist(username)){
            return new Result(true, "");
        }

        StringBuilder newUsername = new StringBuilder(username);
        Random rand = new Random();
        while (App.doesUsernameExist(newUsername.toString())) {
            int randomNumber = rand.nextInt() % 11;
            if (randomNumber == 10) {
                newUsername.append("-");
            } else {
                newUsername.append(randomNumber);
            }
        }

        return new Result(false, "you should choose a new username, do you want to continue with" +
                "this username? \"" + newUsername.toString() + "\"\ntype \"yes\" if you want to continue!");
    }

    public Result pickQuestion() {
        //TODO
        return null;
    }

    public Result login() {
        //TODO
        return null;
    }

    public Result forgetPassword() {
        //TODO
        return null;
    }



    private String randomPassword() {
        //TODO
        return null;
    }





}
