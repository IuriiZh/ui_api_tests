package ui.pageObject;

import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$;


public class CheckoutOverviewPage {

    public static final SelenideElement pageTitleElement = $(".header_secondary_container .title").as("Checkout: Overview Title");
    public static final String pageTitle = "Checkout: Overview";

    public static void isCurrentPage() {
        pageTitleElement.shouldBe(visible).shouldHave(text(pageTitle));
    }

    //BUTTONS
    private static final SelenideElement cancelButton = $("#cancel").as("Cancel Button");

    //PRICE

    @Step("Click to cancel button")
    public static void clickToCancelButton() {
        cancelButton.click();
        ProductsPage.isCurrentPage();
    }

}

