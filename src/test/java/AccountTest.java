import PageObject.AccountPage;
import PageObject.CustomerLoginPage;
import PageObject.HomePage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.concurrent.TimeUnit;

import static DataObject.AmountData.depositAmount;
import static DataObject.AmountData.withdrawAmount;
public class AccountTest {
    private HomePage homePage;
    private CustomerLoginPage customerLoginPage;
    private AccountPage accountPage;
    WebDriver driver;

    @BeforeMethod
    public void setUp() {
        driver = new ChromeDriver();
        driver.manage().timeouts().pageLoadTimeout(20, TimeUnit.SECONDS);
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);

        driver.get("https://www.globalsqa.com/angularJs-protractor/BankingProject/#/login");
        customerLoginPage = new CustomerLoginPage(driver);
        homePage = new HomePage(driver);
        accountPage = new AccountPage(driver);
    }
private void loginHarryPotter() throws InterruptedException{
        Thread.sleep(1000);
    homePage.loginAsCustomer();
    Thread.sleep(1000);
    customerLoginPage.selectUserName("Harry Potter");
    Thread.sleep(1000);

    customerLoginPage.clickLoginButton();
    Thread.sleep(1000);

}
    @Test
    public void loginTest() throws InterruptedException {
        loginHarryPotter();
    }
   @Test
    public void depositTest() throws InterruptedException{
        loginHarryPotter();

        double expectedAmount = accountPage.getTotalBalance() + depositAmount;
        accountPage.goBack();
        accountPage.deposit(depositAmount);
        double currentTotalAmount = accountPage.getTotalBalance();
        Assert.assertEquals(currentTotalAmount,expectedAmount);
    }
    @Test
    public void withDrawlTest() throws InterruptedException{
        loginHarryPotter();
        double totalBalance = accountPage.getTotalBalance();
        accountPage.goBack();
        if(totalBalance == 0)
        {

            accountPage.deposit(depositAmount);
        }
        double expectedAmount = accountPage.getTotalBalance() - withdrawAmount;
        accountPage.goBack();
        accountPage.withdrawl(withdrawAmount);
        double currentTotalAmount = accountPage.getTotalBalance();
        Assert.assertEquals(currentTotalAmount,expectedAmount);
    }
    @AfterMethod
    public void tearDown(){
        driver.quit();
    }
}
