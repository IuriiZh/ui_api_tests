package ui.pageObject;

import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Selenide.$;
import static org.junit.jupiter.api.Assertions.assertEquals;

import io.qameta.allure.Step;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.HttpClientBuilder;
import utils.PropertyReader;

import java.io.IOException;

public class LandingPage extends Page {

    //Title
    public static final SelenideElement pageTitleElement = null;
    public static final String pageTitle = "";

    public static void isCurrentPage() {
        elementVisible(pageTitleElement, true);
        assertEquals(pageTitle, pageTitleElement.getText());
    }

    public static final SelenideElement loginLogo = $(".login_logo").as("Landing logo");
    public static final SelenideElement usernameField = $("#user-name").as("Username Field");
    public static final SelenideElement passwordField = $("#password").as("Password Field");
    public static final SelenideElement loginButton = $("#login-button").as("Login Button");

    @Step("Connect")
    public static void accessLanding() throws IOException {
        HttpClient client = HttpClientBuilder.create().build();
        HttpGet request = new HttpGet(PropertyReader.getInstance().getUIUrl());
        HttpResponse response = client.execute(request);
        assertEquals(200, response.getStatusLine().getStatusCode());
    }

    @Step("Title")
    public static void verifyTitle() {
        assertEquals("Swag Labs", Selenide.title());
    }

    @Step("Verify base elements visibility")
    public static void landingBaseElementsVisibility() {
        Page.elementVisible(loginLogo, true);
        Page.elementVisible(usernameField, true);
        Page.elementVisible(passwordField, true);
        Page.elementVisible(loginButton, true);
    }

}
