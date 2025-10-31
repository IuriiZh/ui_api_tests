package ui.pageObject;

import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;

import java.util.List;
import java.util.stream.Collectors;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class CheckoutOverviewPage extends Page {

    public static final SelenideElement pageTitleElement = $(".header_secondary_container .title").as("Checkout: Overview Title");
    public static final String pageTitle = "Checkout: Overview";

    public static void isCurrentPage() {
        elementVisible(pageTitleElement, true);
        assertEquals(pageTitle, pageTitleElement.getText());
    }

    //BUTTONS
    private static final SelenideElement finishButton = $("#finish").as("Finish Button");
    private static final SelenideElement cancelButton = $("#cancel").as("Cancel Button");

    //PRICE
    private static final ElementsCollection itemPrice = $$(".inventory_item_price").as("Item Price");
    public static SelenideElement itemTotalPrice = $(".summary_subtotal_label").as("Item Total Price");

    @Step("Click to cancel button")
    public static void clickToCancelButton() {
        cancelButton.click();
    }

}

