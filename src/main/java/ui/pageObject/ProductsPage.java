package ui.pageObject;

import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;

import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selenide.$;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;

public class ProductsPage {

    //Title
    public static final SelenideElement pageTitleElement = $(".header_secondary_container .title").as("Product Title");
    public static final String pageTitle = "Products";

    //Buttons
    public static SelenideElement addToCartBackpack = $("#add-to-cart-sauce-labs-backpack").as("Add To Cart Backpack");
    public static SelenideElement removeFromCartBackpack = $("#remove-sauce-labs-backpack").as("Remove From Cart Backpack");

    @Step("Check that current page is Products page")
    public static void isCurrentPage() {
        pageTitleElement.shouldBe(visible).shouldHave(text(pageTitle));
    }


    @Step("Add to cart Backpack")
    public static void addToCartBackpack() {
        if(removeFromCartBackpack.exists()) { removeFromCartBackpack.click(); }
        addToCartBackpack.click();
    }

    @Step("Verify adding to cart Backpack")
    public static void verifyAddToCart() {
        removeFromCartBackpack.shouldBe(visible);
        CartPage.cartBadge.shouldBe(visible).shouldHave(text("1"));
    }

    @Step("Remove from cart Backpack")
    public static void removeFromCartBackpack() {
        removeFromCartBackpack.click();
    }

    @Step("Verifying removing from cart Backpack")
    public static void verifyRemovingFromCartBackpack(int oldValueInCart) {
        removeFromCartBackpack.shouldBe(hidden);
        assertThat(oldValueInCart-1, equalTo(CartPage.getCountInCart()));
    }
}
