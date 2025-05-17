import controllers.ForgotPasswordFlow;
import models.Account;
import models.App;
import models.enums.Gender;
import models.enums.Menu;
import models.enums.SecurityQuestions;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.MockedStatic;
import records.Result;
import views.LoginMenu;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.io.PrintStream;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class ForgotPasswordTest {
    private ForgotPasswordFlow flow;
    private final Account mockAccount =  new Account(Gender.MALE, "test@gmail.com", "AliAlm", "TestPass#403","AliAlmasi");
    private static final String USERNAME = "testUser";
    private static final String PASSWORD = "ValidPassword123";
    private static final Map<SecurityQuestions, String> securityAnswers = new HashMap<>();
    private Scanner scanner;
    private LoginMenu menu;
    private final PrintStream originalOut = System.out;
    private final InputStream originalIn = System.in;
    private ByteArrayOutputStream out;

    @BeforeEach
    void setup() {
        App.setLoggedInAccount(null);
        App.addAccount(mockAccount);

        menu = new LoginMenu();
        out = new ByteArrayOutputStream();
        mockAccount.getSecurityAnswers().put(SecurityQuestions.COLOR, "meow1");
        mockAccount.getSecurityAnswers().put(SecurityQuestions.PET, "meow2");

        out = new ByteArrayOutputStream();
        App.setCurrentMenu(Menu.LOGIN_MENU);
        App.setLoggedInAccount(null);
        menu = new LoginMenu();

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
    private void checkStartExpected(String startExpected) {
        scanner = new Scanner(System.in);
        App.getCurrentMenu().checker(scanner);
        String output = out.toString().trim();
        assertTrue(output.startsWith(startExpected), "your code output:\n" + output + "\nexpected:\n" + startExpected + "\n");
    }
    private void checkExpectedContains(String startExpected) {
        scanner = new Scanner(System.in);
        App.getCurrentMenu().checker(scanner);
        String output = out.toString().trim();
        assertTrue(output.contains(startExpected), "your code output:\n" + output + "\nexpected:\n" + startExpected + "\n");
    }

    private void checkExpected(String expected) {
        scanner = new Scanner(System.in);
        App.getCurrentMenu().checker(scanner);
        String output = out.toString().trim();
        assertEquals(expected, output, "your code output:\n" + output + "\nexpected:\n" + expected + "\n");
    }



    @Test
    void testForgotPassword(){
        checkExpectedContains("invalid");
        checkExpectedContains("invalid");
        checkExpectedContains("exist");
        checkExpectedContains(SecurityQuestions.COLOR.getQuestion());
        checkExpectedContains("wrong");
        checkExpectedContains("correct");
        checkExpectedContains(SecurityQuestions.PET.getQuestion());
        checkExpectedContains("wrong");
        checkExpectedContains("correct");
        checkExpectedContains("enter");
    }
}
