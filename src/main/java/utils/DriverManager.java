package utils;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.RemoteWebDriver;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;

import static utils.ConfigHelper.*;

public class DriverManager {

    private static final ThreadLocal<WebDriver> driverThreadLocal = new ThreadLocal<>();

    // Singleton pattern
    public static WebDriver getInstance() {
        if(driverThreadLocal.get() == null) {
            if(getConfig().getBoolean("student.app.run.locally")) {
            ChromeOptions options = new ChromeOptions();
            options.addArguments("--remote-allow-origins=*");
            driverThreadLocal.set(new ChromeDriver(options));
            } else {
                ChromeOptions browserOptions = new ChromeOptions();
                browserOptions.setPlatformName("Windows 8");
                browserOptions.setBrowserVersion("108");
                Map<String, Object> sauceOptions = new HashMap<>();
                sauceOptions.put("build", "selenium-build-HJAUT");
                sauceOptions.put("name", "<your test name>");
                browserOptions.setCapability("sauce:options", sauceOptions);

                URL url = null;
                try {
                    url = new URL("https://oauth-nikita-839ac:91b032f7-1489-40cf-b55c-3bc4a3376d1c@ondemand.us-west-1.saucelabs.com:443/wd/hub");
                } catch (MalformedURLException e) {
                    throw new RuntimeException(e);
                }
                driverThreadLocal.set(new RemoteWebDriver(url, browserOptions));
            }
        }
        return driverThreadLocal.get();
    }
}