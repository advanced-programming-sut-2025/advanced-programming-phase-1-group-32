package models;

import models.enums.Gender;

import models.Result;
import java.util.ResourceBundle;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Account {
    private static final Pattern validUsernamePattern = Pattern.compile("^[a-zA-Z0-9-]*$");
    private static final Pattern emailGroupingPattern = Pattern.compile("^(?<mail>\\S+)@(?<domain>\\S+)\\.(?<tld>\\S+)$");
    private static final Pattern validMailPattern     = Pattern.compile("^[a-zA-Z0-9._-]+$");
    private static final Pattern validDomainPattern   = Pattern.compile("^[a-zA-Z0-9.-]+$");
    private static final Pattern validPasswordPattern = Pattern.compile("^$");

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
        if(!Account.validUsernamePattern.matcher(username).matches()){
            return new Result(false, "Username can only contain letters, numbers, and dashes.");
        }
        return new Result(true, "");
    }
    public static Result isPasswordValid(String password) { //check all username conditions
        return new Result(true, null);
        // TODO
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

        if(!validMailPattern.matcher(mail).matches()){
            return new Result(false, "\"" + mail + "\" contains invalid characters");
        }
        if(!(Character.isLetterOrDigit(mail.charAt(0)) && Character.isLetterOrDigit(mail.charAt(mail.length()-1)))){
            return new Result(false, "\"" + mail + "\" should start and end with a letter or digit");
        }
        if(!(Character.isLetterOrDigit(domain.charAt(0)) && Character.isLetterOrDigit(tld.charAt(tld.length()-1)))){
            return new Result(false, "\"" + domain + "." + tld + "\" should start and end with a letter or digit");
        }
        if(!validDomainPattern.matcher(domain+tld).matches()){
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
