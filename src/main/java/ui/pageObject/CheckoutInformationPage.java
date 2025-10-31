package ui.pageObject;

import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;

import static com.codeborne.selenide.Selenide.$;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class CheckoutInformationPage extends Page {

    public static final SelenideElement pageTitleElement = $(".header_secondary_container .title").as("Checkout: Your Information Title");
    public static final String pageTitle = "Checkout: Your Information";

    public static  void isCurrentPage() {
        elementVisible(pageTitleElement, true);
        assertEquals(pageTitle, pageTitleElement.getText());
    }

    //CHECKOUT FORM FIELDS
    private static final SelenideElement firstNameField = $("#first-name").as("First Name Field");
    private static final SelenideElement lastNameField = $("#last-name").as("Last Name Field");
    private static final SelenideElement zipPostalCodeField = $("#postal-code").as("Zip Postal Code Field");

    //BUTTON
    private static final SelenideElement continueButton = $("#continue").as("Continue Button");

    //DATA
    private static final CharSequence FIRST_NAME = ("Iurii");
    private static final CharSequence LAST_NAME = ("No given anme");
    private static final CharSequence ZIP_POSTAL_CODE = ("90210");

    @Step("Fill input fields")
    public static void fillFields() {
        firstNameField.sendKeys(FIRST_NAME);
        lastNameField.sendKeys(LAST_NAME);
        zipPostalCodeField.sendKeys(ZIP_POSTAL_CODE);
    }

    @Step("Click continue button")
    public static void clickContinueButton() {
        continueButton.click();
        CheckoutOverviewPage.isCurrentPage();
    }
}