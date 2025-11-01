package ui.pageObject;

import com.codeborne.selenide.Selenide;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;

import io.qameta.allure.Step;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.HttpClientBuilder;
import utils.PropertyReader;

import java.io.IOException;

public class LandingPage {

    @Step("Check connection")
    public static void accessLanding() throws IOException {
        HttpClient client = HttpClientBuilder.create().build();
        HttpGet request = new HttpGet(PropertyReader.getInstance().getUIUrl());
        HttpResponse response = client.execute(request);
        assertThat(response.getStatusLine().getStatusCode(), equalTo(200));
    }

    @Step("Verify title")
    public static void verifyTitle() {
        assertThat(Selenide.title(), equalTo("Swag Labs"));
    }
}
