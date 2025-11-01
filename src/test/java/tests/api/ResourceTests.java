package tests.api;

import api.reqres.in.ListResourceResponse;
import io.qameta.allure.*;
import org.junit.jupiter.api.*;
import java.util.List;
import java.util.stream.Collectors;
import static io.restassured.module.jsv.JsonSchemaValidator.matchesJsonSchemaInClasspath;
import static api.setup.RequestSpecs.*;
import static org.junit.jupiter.api.Assertions.*;

@Epic("API Tests")
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@DisplayName("API:Resource schema tests")
@Tag("api")
public class ResourceTests {

    @Feature("API:Resources")
    @Severity(SeverityLevel.BLOCKER)
    @Test
    @Order(1)
    @DisplayName("Resource List Json Scheme Validation GET")
    @Description("Validation list Resource Json Scheme GET")
    public void listResourceJsonSchemeValidation() {
        request(200)
                .get("api/unknown")
                .then()
                .log().status()
                .log().body()
                .body(matchesJsonSchemaInClasspath("reqres/json_scheme/list_resource.json"));
    }

    @Feature("API:Resources")
    @Severity(SeverityLevel.BLOCKER)
    @Test
    @Order(2)
    @DisplayName("Resource List GET")
    @Description("Tests for Resource List GET")
    public void listResourceTests() {
        List<ListResourceResponse> resourceList = request(200)
                .when()
                .get("api/unknown")
                .then()
                .log().status()
                .log().body()
                .extract().body().jsonPath().getList("data", ListResourceResponse.class);

        resourceList.forEach(x -> assertTrue(x.getColor().matches("#\\w{6}")));
        resourceList.forEach(x -> assertTrue(x.getPantone_value().matches("\\d{2}-\\d{4}")));
        List<Integer> years = resourceList.stream().map(ListResourceResponse::getYear).collect(Collectors.toList());
        List<Integer> sortedYears = years.stream().sorted().collect(Collectors.toList());
        assertEquals(years, sortedYears);
    }
}
