package controllers;

import models.Account;
import models.App;
import models.enums.SecurityQuestions;
import java.util.ArrayList;
import records.Result;


public class ForgotPasswordFlow {
    private record QuestionAnswerPair(SecurityQuestions question, String answer){};

    Account account;
    ArrayList<QuestionAnswerPair> questionAnswerPairs;
    String newPassword;
    int state = 0;
    int currentQuestion = 0;

    public Result handle(String input){
        switch (state){
            case 0:
                account = App.getUserByUsername(input);

                if(account == null){
                    return new Result(false, "username doesn't exist!");
                }

                state++;
                return new Result(true, "answer the questions one by one (give an empty answer to cancel the process)");
            case 1:
                if(currentQuestion == questionAnswerPairs.size()){
                    state = 3;
                    return new Result(true, "the answers were correct! now "+
                                            "enter a new password (enter \"random\" for a random password):");
                }
                state++;
                return new Result(true, questionAnswerPairs.get(currentQuestion).question.toString());
            case 2:
                if(input.isEmpty()){
                    return new Result(false, "the process was canceled");
                }

                if(!input.equals(questionAnswerPairs.get(currentQuestion).answer)){
                    return new Result(false, "wrong answer! try again");
                }

                currentQuestion++;
                state--;
                return new Result(true, "correct!");
            case 3:
                if(input.equals("random")){
                    newPassword = LoginMenuController.generatePassword();
                    state++;
                    return new Result(true, "confirm your new password: " + newPassword +" (y/n)");
                }
                newPassword = input;
                state = 5;
                return new Result(true, "re-enter your new password:");
            case 4:
                if(input.equals("y")){
                    App.getLoggedInAccount().setPassword(newPassword);
                    return new Result(false, "password was set!");
                }else{
                    state--;
                    return new Result(true, "enter a password:");
                }
            case 5:
                if(input.equals(newPassword)){
                    App.getLoggedInAccount().setPassword(newPassword);
                    return new Result(false, "password was set!");
                }
                if(input.equals("back")){
                    state = 3;
                    return new Result(true, "enter a password:");
                }
                return new Result(true, "passwords don't match. re-enter your new password: (enter back to go back)");
            default:
                return null;
        }
    }
}

