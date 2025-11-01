package tests.api;

import api.reqres.in.CreateUserRequest;
import api.reqres.in.CreatedUserResponse;
import io.qameta.allure.*;
import org.junit.jupiter.api.*;
import static api.setup.RequestSpecs.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static utils.PropertyReader.*;
import static utils.PropertyReader.email;

@Epic("API Tests")
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@DisplayName("API:Users tests")
@Tag("api")
public class UserTests {

    @Feature("API:Login")
    @Severity(SeverityLevel.BLOCKER)
    @Test
    @Order(1)
    @DisplayName("Register successful POST")
    @Description("Register successful functionality POST")
    public void successfulRegister() {
        register("", email, password, 200, "");    }

    @Feature("API:Login")
    @Severity(SeverityLevel.MINOR)
    @Test
    @Order(2)
    @DisplayName("Register unsuccessful: Missing username POST")
    @Description("Register unSuccessful: Missing username functionality POST")
    public void unSuccessfulRegisterNoUsername() {
        register("", email, password, 2400, "");
    }

    @Feature("API:Login")
    @Severity(SeverityLevel.MINOR)
    @Test
    @Order(3)
    @DisplayName("Register unsuccessful: Missing email POST")
    @Description("Register unSuccessful: Missing email functionality POST")
    public void unSuccessfulRegisterNoEmail() {
        register(username, "", password, 400, "Note: Only defined users succeed registration");
    }

    @Feature("API:Login")
    @Severity(SeverityLevel.MINOR)
    @Test
    @Order(4)
    @DisplayName("Register unsuccessful: Missing username and email POST")
    @Description("Register unSuccessful: Missing username and email functionality POST")
    public void unSuccessfulRegisterNoUsernameAndEmail() {
        register("", "", password, 400, "Missing email or username");
    }

    @Feature("API:Login")
    @Severity(SeverityLevel.MINOR)
    @Test
    @Order(5)
    @DisplayName("Register unsuccessful: Missing password POST")
    @Description("Register unsuccessful: Missing password functionality POST")
    public void unSuccessfulRegisterNoPassword() {
        register(username, email, "", 400, "Missing password");
    }

    @Feature("API:Users")
    @Severity(SeverityLevel.BLOCKER)
    @Test
    @Order(6)
    @DisplayName("Create User POST")
    @Description("Tests for create user functionality POST")
    public void createUserTests() {
        String name= "user1", job = "worker";
        request(201)
                .body(new CreateUserRequest(name, job))
                .when()
                .post("api/users")
                .then()
                .log().status()
                .log().body()
                .assertThat()
                .body("name", equalTo(name))
                .body("job", equalTo(job));

//                .extract().body().as(CreatedUserResponse.class);
//        assertThat(name, equalTo(createdUser.getName()));
//        assertThat(job, equalTo(createdUser.getJob()));
    }

    @Feature("API:Users")
    @Severity(SeverityLevel.BLOCKER)
    @Test
    @Order(7)
    @DisplayName("Update user PUT")
    @Description("Tests for update user functionality PUT")
    public void updatedUserTests() {
        String name= "user1", newJob = "worker";
        request(200)
            .body(new CreateUserRequest(name, newJob))
            .when()
            .put("api/users/2")
            .then()
            .log().status()
            .log().body()
            .assertThat()
            .body("name", equalTo(name))
            .body("job", equalTo(newJob));
    }

    @Feature("API:Users")
    @Severity(SeverityLevel.BLOCKER)
    @Test
    @Order(8)
    @DisplayName("Update user chained")
    @Description("Tests for update user in chain functionality")
    public void updatedUserChainedTests() {
        String name= "user1", job = "worker";
        CreatedUserResponse createdUser =
        request(201)
                .body(new CreateUserRequest(name, job))
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
                .body("name", equalTo(name))
                .body("job", equalTo(job));
        String newJob = "manager";
        request(200)
                .body(new CreateUserRequest(name, newJob))
                .when()
                .put("api/users/"+createdUser.getId())
                .then()
                .log().status()
                .log().body()
                .assertThat()
                .body("name", equalTo(name))
                .body("job", equalTo(newJob));
    }

    @Feature("API:Users")
    @Severity(SeverityLevel.BLOCKER)
    @Test
    @Order(9)
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
