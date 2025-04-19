package models;

import models.enums.Gender;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Account {
    private static final Pattern validUsernameCharacters = Pattern.compile("^[a-zA-Z0-9-]*$");
    private static final Pattern emailGroupingPattern    = Pattern.compile("^(?<mail>\\S+)@(?<domain>\\S+)\\.(?<tld>\\S+)$");
    private static final Pattern validMailCharacters     = Pattern.compile("^[a-zA-Z0-9._-]+$");
    private static final Pattern validDomainCharacters   = Pattern.compile("^[a-zA-Z0-9.-]+$");
    private static final Pattern validPasswordCharacters = Pattern.compile("^[0-9A-Za-z?<>,\"';:/\\\\|\\[\\]{}+=()*&^%$#!]+$");
    private static final Pattern hasDigitPattern = Pattern.compile(".*[0-9].*");
    private static final Pattern hasLowerCasePattern = Pattern.compile(".*[a-z].*");
    private static final Pattern hasUpperCasePattern = Pattern.compile(".*[A-Z].*");
    private static final Pattern hasSpecialCharactersPattern = Pattern.compile(".*[?<>,\"';:/\\\\|\\[\\]{}+=()*&^%$#!]+.*");


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

    public static Result isUsernameValid(String username) {
        if(username.isEmpty()){
            return new Result(false, "Username should not be empty");
        }
        if(!Account.validUsernameCharacters.matcher(username).matches()){
            return new Result(false, "Username contains invalid characters");
        }
        return new Result(true, "");
    }
    public static Result isPasswordValid(String password) { //check all username conditions
        if(password.isEmpty()){
            return new Result(false, "password should not be empty");
        }
        if(password.length()<8){
            return new Result(false, "password should have at least 8 characters");
        }
        if(!validPasswordCharacters.matcher(password).matches()){
            return new Result(false, "password contains invalid characters");
        }
        if(!hasLowerCasePattern.matcher(password).matches()){
            return new Result(false, "password should contain at least one lowercase");
        }
        if(!hasUpperCasePattern.matcher(password).matches()){
            return new Result(false, "password should contain at least one uppercase");
        }
        if(!hasDigitPattern.matcher(password).matches()){
            return new Result(false, "password should contain at least one digit");
        }
        if(!hasSpecialCharactersPattern.matcher(password).matches()){
            return new Result(false, "password should contain at least one special character (?<>,\"';:/\\|[]{}+=()*&^%$#!)");
        }
        return new Result(true, "");
    }
    public static Result isEmailValid(String email) {
        if(email.isEmpty()){
            return new Result(false, "email should not be empty");
        }

        Matcher emailGroups = Account.emailGroupingPattern.matcher(email);
        if(!emailGroups.matches()){
            return new Result(false, "email format is invalid : sample@domain.com");
        }

        String tld    = emailGroups.group("tld");
        String mail   = emailGroups.group("mail");
        String domain = emailGroups.group("domain");

        if(!validMailCharacters.matcher(mail).matches()){
            return new Result(false, "\"" + mail + "\" contains invalid characters");
        }
        if(!(Character.isLetterOrDigit(mail.charAt(0)) && Character.isLetterOrDigit(mail.charAt(mail.length()-1)))){
            return new Result(false, "\"" + mail + "\" should start and end with a letter or digit");
        }
        if(!(Character.isLetterOrDigit(domain.charAt(0)) && Character.isLetterOrDigit(tld.charAt(tld.length()-1)))){
            return new Result(false, "\"" + domain + "." + tld + "\" should start and end with a letter or digit");
        }
        if(!validDomainCharacters.matcher(domain+tld).matches()){
            return new Result(false, "\"" + domain + "." + tld + "\" contains invalid characters");
        }
        if(tld.length() < 2){
            return new Result(false, "\"" + tld + "\" is too short");
        }
        if(email.contains("..")){
            return new Result(false, "email should not contain \"..\"");
        }

        return new Result(true, "");
    }
    public static Result isNicknameValid(String nickname) { //check all username conditions
        return new Result(true, "");
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
