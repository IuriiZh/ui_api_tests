package tests.ui;

import org.junit.jupiter.api.*;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;

import static com.codeborne.selenide.Selenide.$$x;
import com.codeborne.selenide.ElementsCollection;
import static org.junit.jupiter.api.Assertions.assertEquals;

import io.qameta.allure.*;
import ui.pageObject.*;
import utils.PropertyReader;
import utils.UIBaseTest;
import java.util.Random;

import java.io.IOException;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)

public class UITests extends UIBaseTest {

    //DATA
    public static final String UI_USERNAME = (PropertyReader.getInstance().getUIUsername());
    public static final String UI_PASSWORD = (PropertyReader.getInstance().getUIPassword());


    @Severity(SeverityLevel.NORMAL)
    @Feature("Blocker")
    @Link("https://www.saucedemo.com/")
    @Test
    @Order(1)
    @DisplayName("Landing")
    @Description("Test Landing page functionality")
    public void testLanding() throws IOException {
        LandingPage.accessLanding();
        LandingPage.verifyTitle();
        LandingPage.landingBaseElementsVisibility();
    }

    @Severity(SeverityLevel.CRITICAL)
    @Feature("Blocker")
    @Link("https://www.saucedemo.com/")
    @Test
    @Order(2)
    @DisplayName("Unsuccessful login")
    @Description("Test Unsuccessful Login functionality")
    public void testUnsuccessfulLogin() {
        LoginPage.openLoginPage();
        LoginPage.login(UI_USERNAME, "Wrong password here");
        LoginPage.verifyErrorMessage();
    }

    @Severity(SeverityLevel.NORMAL)
    @Feature("Blocker")
    @Link("https://www.saucedemo.com/")
    @ParameterizedTest
    @CsvFileSource(resources = "/login-data.csv", numLinesToSkip = 1)
    @Order(3)
    @DisplayName("Login")
    @Description("Test Login functionality")
    public void testLogin(String username, String password) {
        LoginPage.openLoginPage();
        LoginPage.login(username, password);
        ProductsPage.isCurrentPage();
    }

    @Severity(SeverityLevel.NORMAL)
    @Feature("Blocker")
    @Link("https://www.saucedemo.com/inventory.html")
    @Test
    @Order(4)
    @DisplayName("Add to cart")
    @Description("Test add to cart functionality")
    public void addToCart() {
        LoginPage.openLoginPage();
        LoginPage.login(UI_USERNAME, UI_PASSWORD);
        CartPage.removeAllFromCart();
        ProductsPage.addToCartBackpack();
        ProductsPage.verifyAddToCart();
    }

    @Severity(SeverityLevel.NORMAL)
    @Link("https://www.saucedemo.com/inventory.html")
    @Test
    @Order(5)
    @DisplayName("Cancellation of purchase")
    @Description("Test of Cancellation of purchase functionality")
    public void testCancellationOfPurchase() {
        LoginPage.openLoginPage();
        LoginPage.login(UI_USERNAME, UI_PASSWORD);
        CartPage.removeAllFromCart();
        ProductsPage.addToCartBackpack();
        CartPage.clickCartButton();
        CartPage.clickToCheckoutButton();
        CheckoutInformationPage.fillFields();
        CheckoutInformationPage.clickContinueButton();
        CheckoutOverviewPage.isCurrentPage();
        CheckoutOverviewPage.clickToCancelButton();
        ProductsPage.isCurrentPage();
    }

    @Severity(SeverityLevel.NORMAL)
    @Feature("Blocker")
    @Link("https://www.saucedemo.com/inventory.html")
    @Test
    @Order(6)
    @DisplayName("Remove from cart from page")
    @Description("Test Remove from cart from page functionality")
    public void removeFromCartFromPage() {
        LoginPage.login(UI_USERNAME, UI_PASSWORD);
        CartPage.removeAllFromCart();
        ProductsPage.addToCartBackpack();
        int oldValueInCart = CartPage.getCountInCart();
        ProductsPage.removeFromCartFromPage();
        ProductsPage.verifyRemovingFromCartFromPage(oldValueInCart);
    }

    @Severity(SeverityLevel.NORMAL)
    @Feature("Blocker")
    @Link("https://www.saucedemo.com/cart.html")
    @Test
    @Order(7)
    @DisplayName("Remove from cart")
    @Description("Test Remove from cart functionality")
    public void removeFromCart() {
        LoginPage.login(UI_USERNAME, UI_PASSWORD);
        CartPage.removeAllFromCart();
        ProductsPage.addToCartBackpack();
        int oldValueInCart = CartPage.getCountInCart();
        CartPage.removeFromCartBackpack();
        CartPage.verifyRemovingFromCart(oldValueInCart);
    }

    @Severity(SeverityLevel.NORMAL)
    @Link("https://www.saucedemo.com/checkout-step-two.html")
    @Test
    @Order(9)
    @DisplayName("Calculation of items")
    @Description("Test of Calculation of the items functionality")
    public void testCalculationOfTheTotalAmountOfItems(){
        LoginPage.login(UI_USERNAME, UI_PASSWORD);
        CartPage.removeAllFromCart();
        ElementsCollection addButtons = $$x("//button[contains(@data-test, 'add-to-cart-')]");
        int amountItems = (new Random()).nextInt(addButtons.size()) + 1;;
        for (int i = 0; i < amountItems; i++) {
            addButtons.get(i).click();
        }
        assertEquals(amountItems, CartPage.getCountInCart());
    }
}
