package controllers;

import models.Account;
import models.App;
import models.Result;
import models.enums.Menu;
import models.enums.Gender;
import models.enums.SecurityQuestions;
import java.security.SecureRandom;
import java.util.*;
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

        //TODO: add account to json file and Account list

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

    public Result login(String username, String password, boolean stayLogged) {
        if (username.isEmpty()) {
            return new Result(false, "username should not be empty");
        }
        if (password.isEmpty()) {
            return new Result(false, "password should not be empty");
        }
        Account account = App.getUserByUsername(username);

        if (account == null) {
            return new Result(false, "username doesnt exist");
        }

        if (!account.getPassword().equals(password)) {
            return new Result(false, "incorrect password");
        }

        App.setStayLoggedIn(stayLogged);
        App.setCurrentAccount(account);
        App.setCurrentMenu(Menu.LOGIN_MENU);

        return new Result(true, "logged in successfully");
    }
    public Result pickQuestion(int number, String answer,String answerConfirm) {
        SecurityQuestions question = SecurityQuestions.getQuestion(number);
        if (question == null) {
            return new Result(false, "Invalid question!");
        }

        if (App.getLoggedInAccount().securityAnswers.containsKey(question)) {
            return new Result(false, "You are already answered this question!");
        }

        if (!answer.equals(answerConfirm)) {
            return new Result(false, "answers do not match!");
        }

        App.getLoggedInAccount().securityAnswers.put(question, answer);
        return new Result(true, "You are answered question number " + number + "successfully!");
    }

    public Result forgetPassword() {
        //TODO
        return null;
    }

    public static String generatePassword() {
        String upper = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
        String lower = "abcdefghijklmnopqrstuvwxyz";
        String digits = "0123456789";
        String special = "?>,<\"';:/\\|][}{+=)(*&^%$#!";

        SecureRandom random = new SecureRandom();
        List<Character> passwordChars = new ArrayList<>();
        int length = random.nextInt(8) + 8;

        passwordChars.add(upper.charAt(random.nextInt(upper.length())));
        passwordChars.add(lower.charAt(random.nextInt(lower.length())));
        passwordChars.add(digits.charAt(random.nextInt(digits.length())));
        passwordChars.add(special.charAt(random.nextInt(special.length())));

        String allChars = upper + lower + digits + special;
        for (int i = 4; i < length; i++) {
            passwordChars.add(allChars.charAt(random.nextInt(allChars.length())));
        }

        Collections.shuffle(passwordChars, random);

        StringBuilder password = new StringBuilder();
        for (char c : passwordChars) {
            password.append(c);
        }

        return password.toString();
    }


}
