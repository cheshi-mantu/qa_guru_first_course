package tests;

import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.logevents.SelenideLogger;
import drivers.CustomWebDriver;
import io.qameta.allure.selenide.AllureSelenide;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;

import static com.codeborne.selenide.Selenide.closeWebDriver;
import static helpers.AttachmentsHelper.*;


class TestBase {
    @BeforeAll
    public static void beforeAll(){
        SelenideLogger.addListener("AllureSelenide", new AllureSelenide().screenshots(true).savePageSource(true));
        Configuration.timeout = 10000;
        Configuration.fastSetValue = true;
    }

    @BeforeEach
    void beforeEach() {
        Configuration.startMaximized=true;
        Configuration.browser = CustomWebDriver.class.getName();
    }

    @AfterEach
    void afterEach() {
        attachScreenshot("Last screenshot");
        attachPageSource();
        attachBrowserConsoleLogs();
        attachVideo();
        closeWebDriver();
    }

}