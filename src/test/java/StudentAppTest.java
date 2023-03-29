import com.github.javafaker.Faker;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import page_objects.AddStudentPage;
import page_objects.AllStudentsPage;
import page_objects.Notifications;
import utils.DriverManager;

import java.lang.reflect.Method;
import java.time.Duration;

import static constants.AllConstants.GenderConstants.MALE;
import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;
import static utils.ConfigHelper.getConfig;
import static utils.DriverManager.testName;

public class StudentAppTest {

    private WebDriver driver;
    private WebDriverWait driverWait;
    private final Faker dataFaker = new Faker();
    private AllStudentsPage allStudentsPage;
    private AddStudentPage addStudentPage;
    private Notifications notifications;


    @BeforeMethod
    public void initialize(Method method) {
        testName = (method.getName());
        driver = DriverManager.getInstance();

        driverWait = new WebDriverWait(driver, Duration.ofSeconds(10));
        driver.get(getConfig().getString("student.app.hostname"));
        allStudentsPage = new AllStudentsPage();
        addStudentPage = new AddStudentPage();
        notifications = new Notifications();
    }

    @AfterMethod(alwaysRun = true)
    public void tearDown(ITestResult result) {
        String status = result.isSuccess() ? "passed" : "failed";
        ((JavascriptExecutor) driver).executeScript("sauce:job-result=" + status);
        driver.close();
        driver.quit();
    }

    @Test(description = "Add student and check successful message")
    public void openStudentApp() {
        allStudentsPage.waitAndClickOnAddStudentButton();

        String name = dataFaker.name().firstName();
        addStudentPage.waitAndSetValueForNameField(name);
        addStudentPage.waitAndSetValueForEmailField();
        addStudentPage.waitAndSetGender(MALE);
        addStudentPage.clickOnSubmitButton();

        assertEquals(notifications.getMessageFromNotification(), "Student successfully added");
        assertEquals(notifications.getDescriptionFromNotification(), name + " was added to the system");

        notifications.getPopUpCloseButton().click();
        assertTrue(driverWait.until(ExpectedConditions.invisibilityOf(notifications.getPopUpCloseButton())));
    }
}
