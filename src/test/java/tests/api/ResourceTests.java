package tests.api;

import api.reqres_in.ListResourceResponse;
import io.qameta.allure.*;
import org.junit.Assert;
import org.junit.jupiter.api.*;
import java.util.List;
import java.util.stream.Collectors;
import static io.restassured.module.jsv.JsonSchemaValidator.matchesJsonSchemaInClasspath;
import static api.setup.RequestSpecs.*;
import static org.junit.jupiter.api.Assertions.*;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class ResourceTests {

    @Severity(SeverityLevel.BLOCKER)
    @Test
    @Order(1)
    @DisplayName("listResourceJsonSchemeValidation")
    @Description("Validation list Resource Json Scheme ")
    public void listResourceJsonSchemeValidation() {
        request(200)
                .get("api/unknown")
                .then()
                .log().status()
                .log().body()
                .body(matchesJsonSchemaInClasspath("reqres/json_scheme/list_resource.json"));
    }

    @Severity(SeverityLevel.BLOCKER)
    @Test
    @Order(2)
    @DisplayName("list Resource")
    @Description("Tests for list Resource")
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
