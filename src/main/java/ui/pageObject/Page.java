package ui.pageObject;

import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;

import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$;
import static org.junit.jupiter.api.Assertions.assertEquals;

public abstract class Page {
    public static void elementVisible(SelenideElement element, Boolean visibility) {
        if(visibility!=false) { element.shouldBe(visible); }
        else{ element.shouldNotBe(visible); }
    }
}
