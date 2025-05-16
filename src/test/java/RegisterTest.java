import models.Account;
import models.App;
import models.enums.Gender;
import models.enums.Menu;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import views.LoginMenu;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.io.PrintStream;
import java.util.Scanner;

import static org.junit.jupiter.api.Assertions.*;

public class RegisterTest {

    private LoginMenu menu;
    private final PrintStream originalOut = System.out;
    private final InputStream originalIn = System.in;
    private ByteArrayOutputStream out;
    private Scanner scanner;

    @BeforeEach
    void setup() {
        App.setRegisteredAccount(null);
        App.setLoggedInAccount(null);
        App.setCurrentMenu(Menu.LOGIN_MENU);
        menu = new LoginMenu();
        out = new ByteArrayOutputStream();
        System.setOut(new PrintStream(out));
    }

    @AfterEach
    void restoreStreams() {
        System.setOut(originalOut);
        System.setIn(originalIn);
    }

    private void setIn(String input) {
        System.setIn(new ByteArrayInputStream(input.getBytes()));
    }

    private String makeRegisterCommand(String username, String pass, String passConfirm,
                                       String nickname, String email, String gender) {
        return String.format("register -u %s -p %s %s -n %s -e %s -g %s",
                username, pass, passConfirm, nickname, email, gender);
    }



    @ParameterizedTest
    @CsvSource({
            "register -u Ali-Almasi -p CorrectP@ss1 CorrectP@ss1 -n AliAlm -e Ali@gmail.com -g male",
            "register -u AliAlmasi2 -p CorP@ss1 CorP@ss1 -n AliAlm -e Ali@gmail.com -g male",
            "register -u AliAlmasi3 -p CorrectP@ss1 CorrectP@ss1 -n AliAlm -e Ali@gm.ail.com -g male",
            "register -u AliAlmasi4 -p CorrectP@ss1 CorrectP@ss1 -n AliAlm -e Al.i@gmail.com -g male",
            "register -u AliAlmasi5 -p CorrectP@ss1 CorrectP@ss1 -n AliAlm -e Ali@gmail.com -g female"

    })
    void validInput(String input) {
        String startExpected = "Account registered successfully!";
        setIn(input);
        scanner = new Scanner(System.in);
        menu.checker(scanner);
        String output = out.toString().trim();
        assertTrue(output.startsWith(startExpected), "your code output:\n" + output + "\nexpected:\n" + startExpected + "\n");

    }

    @ParameterizedTest
    @CsvSource({
            "register -u Ali Almasi -p CorrectP@ss1 CorrectP@ss1 -n AliAlm -e Ali@gmail.com -g male",
            "register -u Ali_Almasi -p CorrectP@ss1 CorrectP@ss1 -n AliAlm -e Ali@gmail.com -g male",
    })
    void usernameValidation(String input) {
        setIn(input);
        scanner = new Scanner(System.in);
        menu.checker(scanner);
        String output = out.toString().trim();
        String startExpected = "Username contains invalid characters";//TODO
        assertTrue(output.startsWith(startExpected), "your code output:\n" + output + "\nexpected:\n" + startExpected + "\n");
    }

    @Test
    void sameUser() {
        App.addAccount(new Account(Gender.MALE, "test@gmail.com", "testnickname", "Pass123@", "AliAlm"));
        setIn(makeRegisterCommand("AliAlm", "CorrectP@ss1", "CorrectP@ss1",
                "AliAlm", "Ali@gmail.com", "male"));
        scanner = new Scanner(System.in);
        menu.checker(scanner);
        String output = out.toString().trim();
        String startExpected = "you should choose a new username, do you want to continue withthis username?";
        assertTrue(output.startsWith(startExpected), "your code output:\n" + output + "\nexpected:\n" + startExpected + "\n");
    }


    @ParameterizedTest
    @CsvSource({
            "register -u AliAlm1 -p CorrectP@ss1 CorrectP@ss1 -n AliAlm -e Ali@gmail.co.m -g male, \"m\" is too short",
            "register -u AliAlm2 -p CorrectP@ss1 CorrectP@ss1 -n AliAlm -e Ali@gm..ail.com -g male, email should not contain \"..\"",
            "register -u AliAlm3 -p CorrectP@ss1 CorrectP@ss1 -n AliAlm -e Ali@gma@il.com -g male, \"Ali@gma\" contains invalid characters",
            "register -u AliAlm4 -p CorrectP@ss1 CorrectP@ss1 -n AliAlm -e Ali@gmAil.com -g male, Account registered successfully!",
            "register -u AliAlm5 -p CorrectP@ss1 CorrectP@ss1 -n AliAlm -e Ali@gail.om -g male, Account registered successfully!",
            "register -u AliAlm6 -p CorrectP@ss1 CorrectP@ss1 -n AliAlm -e Ali@gl.com -g male, Account registered successfully!"
    })
    void emailValidation(String input, String startExpected) {
        setIn(input);
        scanner = new Scanner(System.in);
        menu.checker(scanner);
        String output = out.toString().trim();
        assertTrue(output.startsWith(startExpected), "your code output:\n" + output + "\nexpected:\n" + startExpected + "\n");
    }
}
