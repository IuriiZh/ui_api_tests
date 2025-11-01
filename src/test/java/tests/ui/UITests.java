package tests.ui;

import com.codeborne.selenide.SelenideElement;
import org.junit.jupiter.api.*;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;

import static com.codeborne.selenide.Selenide.$$x;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;

import io.qameta.allure.*;
import ui.pageObject.*;
import utils.UIBaseTest;

import java.io.IOException;

@Epic("UI Tests")
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@DisplayName("UI: Tests")
@Tag("ui")
public class UITests extends UIBaseTest {

    @Story("Login")
    @Severity(SeverityLevel.NORMAL)
    @Feature("UI:Access")
    @Link("https://www.saucedemo.com/")
    @Test
    @Order(1)
    @DisplayName("Landing")
    @Description("Test Landing page functionality")
    public void testLanding() throws IOException {
        LandingPage.accessLanding();
        LandingPage.verifyTitle();
        LoginPage.isCurrentPage();
    }

    @Story("Login")
    @Severity(SeverityLevel.CRITICAL)
    @Feature("UI:Login")
    @Link("https://www.saucedemo.com/")
    @Test
    @Order(2)
    @DisplayName("Unsuccessful login")
    @Description("Test Unsuccessful Login functionality")
    public void testUnsuccessfulLogin() {
        LoginPage.openApplication();
        LoginPage.loginWithWrongPassword("Wrong password here");
        LoginPage.verifyErrorMessage();
    }

    @Story("Login")
    @Severity(SeverityLevel.NORMAL)
    @Feature("UI:Login")
    @Link("https://www.saucedemo.com/")
    @ParameterizedTest(name="with user: {0}")
    @CsvFileSource(resources = "/login-data.csv", numLinesToSkip = 1)
    @Order(3)
    @DisplayName("Login")
    @Description("Test successfull login functionality")
    public void testLogin(String username, String password) {
        LoginPage.openApplication();
        LoginPage.loginAs(username, password);
    }

    @Story("Cart")
    @Severity(SeverityLevel.NORMAL)
    @Feature("UI:Cart")
    @Link("https://www.saucedemo.com/inventory.html")
    @Test
    @Order(4)
    @DisplayName("Add to cart")
    @Description("Test add to cart functionality")
    public void addToCart() {
        LoginPage.openApplication();
        LoginPage.loginWithValidCredentials();
        CartPage.removeAllFromCart();
        ProductsPage.addToCartBackpack();
        ProductsPage.verifyAddToCart();
    }

    @Story("Cart")
    @Severity(SeverityLevel.NORMAL)
    @Feature("UI:Cart")
    @Link("https://www.saucedemo.com/inventory.html")
    @Test
    @Order(5)
    @DisplayName("Cancellation of purchase")
    @Description("Test of Cancellation of purchase functionality")
    public void testCancellationOfPurchase() {
        LoginPage.openApplication();
        LoginPage.loginWithValidCredentials();
        ProductsPage.addToCartBackpack();
        CartPage.clickCartButton();
        CartPage.clickToCheckoutButton();
        CheckoutInformationPage.fillFields();
        CheckoutInformationPage.clickContinueButton();
        CheckoutOverviewPage.clickToCancelButton();
    }

    @Story("Cart")
    @Severity(SeverityLevel.NORMAL)
    @Feature("UI:Cart")
    @Link("https://www.saucedemo.com/inventory.html")
    @Test
    @Order(6)
    @DisplayName("Remove from cart from page")
    @Description("Test Remove from cart from page functionality")
    public void removeFromCartFromPage() {
        LoginPage.openApplication();
        LoginPage.loginWithValidCredentials();
        ProductsPage.addToCartBackpack();
        int oldValueInCart = CartPage.getCountInCart();
        ProductsPage.removeFromCartBackpack();
        ProductsPage.verifyRemovingFromCartBackpack(oldValueInCart);
    }

    @Story("Cart")
    @Severity(SeverityLevel.NORMAL)
    @Feature("UI:Cart")
    @Link("https://www.saucedemo.com/cart.html")
    @Test
    @Order(7)
    @DisplayName("Remove from cart")
    @Description("Test Remove from cart functionality")
    public void removeFromCart() {
        LoginPage.openApplication();
        LoginPage.loginWithValidCredentials();
        ProductsPage.addToCartBackpack();
        int oldValueInCart = CartPage.getCountInCart();
        CartPage.removeFromCartBackpack();
        CartPage.verifyRemovingFromCartBackpack(oldValueInCart);
    }

    @Story("Cart")
    @Severity(SeverityLevel.NORMAL)
    @Feature("UI:Products")
    @Link("https://www.saucedemo.com/checkout-step-two.html")
    @Test
    @Order(9)
    @DisplayName("Calculation of items")
    @Description("Test of Calculation of the items functionality")
    public void testCalculationOfTheTotalAmountOfItems(){
        LoginPage.openApplication();
        LoginPage.loginWithValidCredentials();
        CartPage.removeAllFromCart();
        int amountItems = $$x("//button[contains(@data-test, 'add-to-cart-')]").size();
        $$x("//button[contains(@data-test, 'add-to-cart-')]").asFixedIterable().forEach(SelenideElement::click);
        assertThat(amountItems, equalTo(CartPage.getCountInCart()));
    }
}
