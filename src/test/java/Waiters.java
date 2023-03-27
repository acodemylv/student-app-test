public class Waiters {
    public static void main(String[] args) {



        // Implicit wait: By implicitly waiting, WebDriver polls the DOM for a certain duration when trying to find any element.
        // This can be useful when certain elements on the webpage are not available immediately and need some time to load.

        // Warning: Do not mix implicit and explicit waits. Doing so can cause unpredictable wait times.
        // For example, setting an implicit wait of 10 seconds and an explicit wait of 15 seconds could cause a timeout to occur after 20 seconds.

        // An implicit wait is to tell WebDriver to poll the DOM for a certain amount of time when trying to find an element or elements if they are not immediately available.
        // The default setting is 0, meaning disabled. Once set, the implicit wait is set for the life of the session.

        // Example: driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));


        // Explicit wait
        // They allow your code to halt program execution, or freeze the thread, until the condition you pass it resolves.
        // The condition is called with a certain frequency until the timeout of the wait is elapsed.
        // WebElement firstResult = new WebDriverWait(driver, Duration.ofSeconds(10))
        //        .until(ExpectedConditions.elementToBeClickable(By.xpath("//a/h3")));
    }

//    public void selectGender(String gender) {
//        driver.findElement(By.xpath("//div[@class='ant-select-selector']")).click();
//        driver.findElement(By.xpath("//div[@title='" + gender + "']")).click();
//    }
}
