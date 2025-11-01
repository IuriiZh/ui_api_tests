package tests.api;

import io.qameta.allure.*;
import org.junit.jupiter.api.*;
import static api.setup.RequestSpecs.login;
import static utils.PropertyReader.*;

@Epic("API Tests")
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@DisplayName("API:Login tests")
@Tag("api")
public class LoginTests {
    @AllureId("1")
    @Feature("API:Login")
    @Severity(SeverityLevel.BLOCKER)
    @Test
    @Order(1)
    @DisplayName("Successful Login POST")
    @Description("Successful Login functionality POST")
    public void successfulLogin() {
        login(email, password, 200,"");
    }

    @Feature("API:Login")
    @Severity(SeverityLevel.MINOR)
    @Test
    @Order(2)
    @DisplayName("unSuccessfulLogin: Missing email POST")
    @Description("unSuccessful Login Missing email functionality POST")
    public void unSuccessfulLoginMissingEmail() {
        login("","", 400, "Missing email or username");
    }

    @Feature("API:Login")
    @Severity(SeverityLevel.MINOR)
    @Test
    @Order(3)
    @DisplayName("unSuccessfulLogin: Missing password POST")
    @Description("unSuccessful Login Missing password functionality POST")
    public void unSuccessfulLoginMissingPassword() {
        login(email,"", 400, "Missing password");
    }

    @Feature("API:Login")
    @Severity(SeverityLevel.MINOR)
    @Test
    @Order(4)
    @DisplayName("unSuccessfulLogin: Wrong password POST")
    @Description("unSuccessful Login Wrong password functionality POST")
    public void unSuccessfulLoginWrongPassword() {
        login(email,password+password, 400, "Wrong password");
    }

    @Feature("API:Login")
    @Severity(SeverityLevel.MINOR)
    @Test
    @Order(5)
    @DisplayName("unSuccessfulLogin: User not found POST")
    @Description("unSuccessful Login User not found functionality POST")
    public void unSuccessfulLoginUserNotFound() {
        login("_"+email, password, 400, "user not found");
    }
}
