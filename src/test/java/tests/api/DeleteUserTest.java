package tests.api;

import io.qameta.allure.*;
import org.junit.jupiter.api.*;
import static api.setup.RequestSpecs.*;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class DeleteUserTest {

    @Severity(SeverityLevel.BLOCKER)
    @Test
    @Order(1)
    @DisplayName("delete User DELETE")
    @Description("Tests for delete user DELETE")
    public void deleteUserTest() {
        request(204)
                .when()
                .delete("api/users/2")
                .then()
                .log().status();
    }
}
