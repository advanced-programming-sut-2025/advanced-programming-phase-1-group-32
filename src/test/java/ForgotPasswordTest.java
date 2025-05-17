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

public class ForgotPasswordTest implements Serializable {
    private ForgotPasswordFlow flow;
    private Account account = new Account(Gender.MALE, "test@gmail.com", "AliAlm", "TestPass#403","AliAlmasi");

    @BeforeEach
    void setup() {
        flow = new ForgotPasswordFlow();


        Map<SecurityQuestions, String> securityAnswers = new HashMap<>();
        account.getSecurityAnswers().put(SecurityQuestions.COLOR, "blue");
        account.getSecurityAnswers().put(SecurityQuestions.PET, "cat");


    }

    @Test
    void testForgotPassword(){
        try (MockedStatic<App> mockedApp = mockStatic(App.class)) {
            mockedApp.when(() -> App.getUserByUsername("AliAlmasi")).thenReturn(account);

            Result result = flow.handle("Alilmasi");
            assertEquals("username doesn't exist!", result.message());

            result = flow.handle("AliAlmasi");
            assertTrue(result.message().contains("answer the questions"));
            assertTrue(result.message().contains(flow.getCurrentQuestion().getQuestion()));

            result = flow.handle("asd");
            assertTrue(result.message().contains("wrong"));


            result = flow.handle(account.getSecurityAnswers().get(flow.getCurrentQuestion()));
            assertTrue(result.message().contains("correct"));
            assertTrue(result.message().contains(flow.getCurrentQuestion().getQuestion()));

            result = flow.handle("asd");
            assertTrue(result.message().contains("wrong"));

            result = flow.handle(account.getSecurityAnswers().get(flow.getCurrentQuestion()));
            assertTrue(result.message().contains("correct"));
            assertTrue(result.message().contains("enter a new password"));

            result = flow.handle("random");
            assertTrue(result.message().contains("confirm your new password"));

            result = flow.handle("n");
            result = flow.handle("asd");
            assertTrue(result.message().contains(Account.isPasswordValid("asd").message()));

            result = flow.handle("asdasdasd");
            assertTrue(result.message().contains(Account.isPasswordValid("asdasdasd").message()));

            result = flow.handle("asdasdasd123");
            assertTrue(result.message().contains(Account.isPasswordValid("asdasdasd123").message()));

            result = flow.handle("asdasd@asd123");
            assertTrue(result.message().contains(Account.isPasswordValid("asdasd@asd123").message()));

            result = flow.handle("Parsa@1145");
            assertTrue(result.message().contains("re-enter your new password:"));

            result = flow.handle("Parsa@");
            assertTrue(result.message().contains("passwords don't match"));

            result = flow.handle("Parsa@13");
            assertTrue(result.message().contains("passwords don't match"));

            result = flow.handle("back");
            assertTrue(result.message().contains("enter a password"));

            result = flow.handle("Parsa@1145");
            assertTrue(result.message().contains("re-enter your new password:"));

            result = flow.handle("Parsa@1145");
            assertTrue(result.message().contains("password was set"));

            assertTrue(account.isPasswordCorrect("Parsa@1145"));
        }
    }
}
