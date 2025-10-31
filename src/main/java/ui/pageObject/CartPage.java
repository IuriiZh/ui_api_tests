package ui.pageObject;

import com.codeborne.selenide.SelenideElement;
import static com.codeborne.selenide.Condition.*;
import io.qameta.allure.Step;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$x;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class CartPage extends Page {

    //Title
    public static final SelenideElement pageTitleElement = $(".header_secondary_container .title").as("Cart Title");
    public static final String pageTitle = "Your Cart";

    public static void isCurrentPage() {
        elementVisible(pageTitleElement, true);
        assertEquals(pageTitle, pageTitleElement.getText());
    }

    //BUTTONS
    public static SelenideElement removeFromCartBackpack = $("#remove-sauce-labs-backpack").as("Remove From Cart Backpack");
    private static final SelenideElement checkoutButton = $("#checkout").as("Checkout button");
    public static final SelenideElement cartButton = $(".shopping_cart_link").as("Shopping cart button");

    //Badge
    public static final SelenideElement cartBadge = $(".shopping_cart_badge").as("Shopping cart badge");

    public static int getCountInCart() {
        return cartBadge.is(visible) ? Integer.parseInt(cartBadge.getText()) : 0;
    }

    @Step("Click to cart button")
    public static void clickCartButton() {
        cartButton.click();
        CartPage.isCurrentPage();
    }

    @Step("Click to checkout button")
    public static void clickToCheckoutButton() {
        checkoutButton.click();
        CheckoutInformationPage.isCurrentPage();
    }

    @Step("Click to remove button")
    public static void removeFromCartBackpack() {
        removeFromCartBackpack.click();
    }

    @Step("Verifying removing from cart")
    public static void verifyRemovingFromCart(int oldValueInCart) {
        Page.elementVisible(removeFromCartBackpack, false);
        assertEquals(oldValueInCart-1, CartPage.getCountInCart());
    }

    public static void removeAllFromCart() {
        $$x("//button[contains(@data-test, 'remove')]").asDynamicIterable().forEach(SelenideElement::click);
    }
}

