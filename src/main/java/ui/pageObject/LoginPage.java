package ui.pageObject;

import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;
import static org.junit.jupiter.api.Assertions.assertEquals;

import io.qameta.allure.Step;
import utils.PropertyReader;

public class LoginPage extends Page {

    //Title
    public static final SelenideElement pageTitleElement = null;
    public static final String pageTitle = "";

    public static void isCurrentPage() {
        elementVisible(pageTitleElement, true);
        assertEquals(pageTitle, pageTitleElement.getText());
    }

    //LOGIN PAGE
    public static final SelenideElement loginLogo = $(".login_logo").as("Landing logo");

    //LOGIN FORM FIELDS
    public static final SelenideElement usernameField = $("#user-name").as("Username Field");
    public static final SelenideElement passwordField = $("#password").as("Password Field");

    //BUTTON
    public static final SelenideElement loginButton = $("#login-button").as("Login Button");
    public static final SelenideElement logoutButton = $("#login-buttonlogout_sidebar_link").as("Logout Button");

    //DATA
    public static final CharSequence USERNAME = (PropertyReader.getInstance().getUIUsername());
    public static final CharSequence PASSWORD = (PropertyReader.getInstance().getUIPassword());

    //MESSAGE
    public static SelenideElement errorLoginMessage = $(".error-message-container h3").as("Error login message");

    @Step("Open login page")
    public static void openLoginPage() {
        open(PropertyReader.getInstance().getUIUrl());
        checkCurrentPageIsItLoginPage();
    }

    @Step("Check that current page is login page")
    public static void checkCurrentPageIsItLoginPage(){
        Page.elementVisible(loginLogo, true);
        Page.elementVisible(usernameField, true);
        Page.elementVisible(passwordField, true);
        Page.elementVisible(loginButton, true);
    }

    @Step("Login")
    public static void login(String username, String password) {
        usernameField.sendKeys(username);
        passwordField.sendKeys(password);
        loginButton.click();
    }

    @Step("Logout")
    public static void logout() {
        logoutButton.click();
    }

    public static void verifyErrorMessage() {
        Page.elementVisible(errorLoginMessage, true);
    }
}
