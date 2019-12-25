package com.spammachine.task;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.spammachine.task.utils.TestConfig;
import net.thucydides.core.annotations.Managed;
import org.junit.Before;
import org.openqa.selenium.WebDriver;

import java.util.concurrent.TimeUnit;

public abstract class AbstractTest {

    static String user;
    static String psw;
    static String postUrl;

    ObjectMapper objectMapper = new ObjectMapper();

    @Managed(driver = "firefox")
    static WebDriver driver;

    @Before
    public void setup() {
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        user = System.getProperty("login");
        psw = System.getProperty("password");
        postUrl = TestConfig.getINSTANCE().getConfig("postService");
    }
}
