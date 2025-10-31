package tests.api;

import api.reqres_in.*;
import io.qameta.allure.*;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.*;
import java.time.Clock;
import static api.setup.RequestSpecs.*;
import static org.junit.jupiter.api.Assertions.*;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class UserTests {

    @Severity(SeverityLevel.BLOCKER)
    @Test
    @Order(1)
    @DisplayName("Create User")
    @Description("Tests for create user functionality")
    public void createdUserTests() {
        CreatedUserResponse createdUser = request(201)
                .body(new CreateUserRequest("user1", "worker"))
                .when()
                .post("api/users")
                .then()
                .log().status()
                .log().body()
                .extract().body().as(CreatedUserResponse.class);
        String currentTime = Clock.systemUTC().instant().toString().replaceAll("\\..+", "");
        assertEquals(createdUser.getCreatedAt().replaceAll("\\..+", ""), currentTime);
    }

    @Severity(SeverityLevel.BLOCKER)
    @Test
    @Order(2)
    @DisplayName("Update user")
    @Description("Tests for update user functionality")
    public void updatedUserTests() {
        request(200)
            .body(new CreateUserRequest("user1", "manager"))
            .when()
            .put("api/users/2")
            .then()
            .log().status()
            .log().body()
            .assertThat()
            .body("name", Matchers.equalTo("user1"))
            .body("job", Matchers.equalTo("manager"));
    }

    @Severity(SeverityLevel.BLOCKER)
    @Test
    @Order(2)
    @DisplayName("Update user chained")
    @Description("Tests for update user in chain functionality")
    public void updatedUserChainedTests() {

        CreatedUserResponse createdUser =
        request(201)
                .body(new CreateUserRequest("user1", "worker"))
                .when()
                .post("api/users")
                .then()
                .log().status()
                .log().body()
                .extract().body().as(CreatedUserResponse.class);

        request(200)
                .when()
                .get("api/users/"+createdUser.getId())
                .then()
                .log().status()
                .log().body()
                .assertThat()
                .body("name", Matchers.equalTo("user1"))
                .body("job", Matchers.equalTo("manager"));

        request(200)
                .body(new CreateUserRequest("user1", "manager"))
                .when()
                .put("api/users/"+createdUser.getId())
                .then()
                .log().status()
                .log().body()
                .assertThat()
                .body("name", Matchers.equalTo("user1"))
                .body("job", Matchers.equalTo("manager"));
    }
}
