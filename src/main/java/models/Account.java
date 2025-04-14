package src.main.java.models;

import src.main.java.models.enums.Gender;

import src.main.java.models.Result;
import java.util.ResourceBundle;

public class Account {
    private String username;
    private String password;
    private String nickname;
    private String email;
    private final Gender gender;

    public Account(Gender gender, String email, String nickname, String password, String username) {
        this.gender = gender;
        this.email = email;
        this.nickname = nickname;
        this.password = password;
        this.username = username;
    }

    public static Result isUsernameValid(String username) { //check all username conditions
        return new Result(true, null);
        // TODO
    }
    public static Result isPasswordValid(String password) { //check all username conditions
        return new Result(true, null);
        // TODO
    }
    public static Result isEmailValid(String email) { //check all username conditions
        return new Result(true, null);
        // TODO
    }
    public static Result isNicknameValid(String nickname) { //check all username conditions
        return new Result(true, null);
        // TODO
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Gender getGender() {
        return gender;
    }


}
