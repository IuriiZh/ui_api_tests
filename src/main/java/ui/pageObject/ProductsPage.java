package ui.pageObject;

import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;
import static com.codeborne.selenide.Selenide.$;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static ui.pageObject.CartPage.clickToCheckoutButton;
import static ui.pageObject.CheckoutInformationPage.clickContinueButton;

public class ProductsPage extends Page {

    //Title
    public static final SelenideElement pageTitleElement = $(".header_secondary_container .title").as("Product Title");
    public static final String pageTitle = "Products";

    public static void isCurrentPage() {
        elementVisible(pageTitleElement, true);
        assertEquals(pageTitle, pageTitleElement.getText());
    }

    //Buttons
    public static SelenideElement addToCartBackpack = $("#add-to-cart-sauce-labs-backpack").as("Add To Cart Backpack");
    public static SelenideElement removeFromCartBackpack = $("#remove-sauce-labs-backpack").as("Remove From Cart Backpack");

    @Step("Add to cart")
    public static void addToCartBackpack() {
        addToCartBackpack.click();
    }

    @Step("Verify add to cart")
    public static void verifyAddToCart() {
        Page.elementVisible(removeFromCartBackpack, true);
        assertEquals(1, CartPage.getCountInCart());

    }

    @Step("Remove from cart from page")
    public static void removeFromCartFromPage() {
        removeFromCartBackpack.click();
    }

    @Step("Verifying removing from cart from page")
    public static void verifyRemovingFromCartFromPage(int oldValueInCart) {
        Page.elementVisible(removeFromCartBackpack, false);
        assertEquals(oldValueInCart-1, CartPage.getCountInCart());
    }

}
