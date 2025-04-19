package controllers;

import models.Account;
import models.App;
import models.Result;
import models.enums.Menu;

import javax.accessibility.AccessibleContext;

public class LoginMenuController implements Controller{
    @Override
    public Result changeMenu() {
        //TODO
        return null;
    }

    public Result register() {
        //TODO
        return null;
    }

    public Result pickQuestion() {
        //TODO
        return null;
    }

    public Result login(String username, String password, boolean stayLogged) {
        if(username.isEmpty()){
            return new Result(false, "username should not be empty");
        }
        if(password.isEmpty()){
            return new Result(false, "password should not be empty");
        }
        Account account = App.getUserByUsername(username);

        if(account == null){
            return new Result(false, "username doesnt exist");
        }

        if(!account.getPassword().equals(password)){
            return new Result(false, "incorrect password");
        }

        App.setStayLoggedIn(stayLogged);
        App.setCurrentAccount(account);
        App.setCurrentMenu(Menu.LOGIN_MENU);

        return new Result(true, "logged in successfully");
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
