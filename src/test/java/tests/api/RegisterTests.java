package tests.api;

import io.qameta.allure.*;
import org.junit.jupiter.api.*;
import static api.setup.RequestSpecs.*;
import static utils.PropertyReader.*;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class RegisterTests {
    @Severity(SeverityLevel.BLOCKER)
    @Test
    @Order(1)
    @DisplayName("successfulRegister")
    @Description("Successful register functionality")
    public void successfulRegister() {
        register("", email, password, 200, "");
    }

    @Severity(SeverityLevel.MINOR)
    @Test
    @Order(2)
    @DisplayName("unSuccessfulRegister: Missing username")
    @Description("unSuccessful register missing username functionality")
    public void unSuccessfulRegisterNoUsername() {
        register("", email, password, 2400, "");
    }

    @Severity(SeverityLevel.MINOR)
    @Test
    @Order(3)
    @DisplayName("unSuccessfulRegister: Missing email")
    @Description("unSuccessful register missing email functionality")
    public void unSuccessfulRegisterNoEmail() {
        register(username, "", password, 400, "Note: Only defined users succeed registration");
    }

    @Severity(SeverityLevel.MINOR)
    @Test
    @Order(4)
    @DisplayName("unSuccessfulRegister: Missing username and email")
    @Description("unSuccessful register missing username and email functionality")
    public void unSuccessfulRegisterNoUsernameAndEmail() {
        register("", "", password, 400, "Missing email or username");
    }

    @Severity(SeverityLevel.MINOR)
    @Test
    @Order(5)
    @DisplayName("unSuccessfulRegister: Missing password")
    @Description("unSuccessful register missing password functionality")
    public void unSuccessfulRegisterNoPassword() {
        register(username, email, "", 400, "Missing password");
    }
}
