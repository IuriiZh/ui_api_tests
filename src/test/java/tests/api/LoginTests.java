package tests.api;

import io.qameta.allure.*;
import org.junit.jupiter.api.*;
import static api.setup.RequestSpecs.login;
import static utils.PropertyReader.*;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class LoginTests {
    @AllureId("1")
    @Feature("Login")
    @Tag("smoke")
    @Severity(SeverityLevel.BLOCKER)
    @Test
    @Order(1)
    @DisplayName("Successful Login")
    @Description("Successful Login functionality")
    public void successfulLogin() {
        login(email, password, 200,"");
    }

    @Severity(SeverityLevel.MINOR)
    @Test
    @Order(2)
    @DisplayName("unSuccessfulLogin: Missing email")
    @Description("unSuccessful Login Missing email functionality")
    public void unSuccessfulLoginMissingEmail() {
        login("","", 400, "Missing email or username");
    }

    @Severity(SeverityLevel.MINOR)
    @Test
    @Order(3)
    @DisplayName("unSuccessfulLogin: Missing password")
    @Description("unSuccessful Login Missing password functionality")
    public void unSuccessfulLoginMissingPassword() {
        login(email,"", 400, "Missing password");
    }

    @Severity(SeverityLevel.MINOR)
    @Test
    @Order(4)
    @DisplayName("unSuccessfulLogin: Wrong password")
    @Description("unSuccessful Login Wrong password functionality")
    public void unSuccessfulLoginWrongPassword() {
        login(email,password+password, 400, "Wrong password");
    }

    @Severity(SeverityLevel.MINOR)
    @Test
    @Order(5)
    @DisplayName("unSuccessfulLogin: User not found")
    @Description("unSuccessful Login User not found functionality")
    public void unSuccessfulLoginUserNotFound() {
        login("_"+email, password, 400, "user not found");
    }
}
