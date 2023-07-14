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


    @BeforeMethod (description = "Chrome ბრაუზერის გახსნა, თაიმ აუთების გაწერა, მისამართზე შესვლა, კლასების ინიციალიზაცია")
    public void setUp() {
        driver = new ChromeDriver();
        driver.manage().timeouts().pageLoadTimeout(20, TimeUnit.SECONDS);
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);

        driver.get("https://www.globalsqa.com/angularJs-protractor/BankingProject/#/login");
        customerLoginPage = new CustomerLoginPage(driver);
        homePage = new HomePage(driver);
        accountPage = new AccountPage(driver);
    }
private void loginHarryPotter() throws InterruptedException {
    Thread.sleep(1000);
    homePage.loginAsCustomer();
    Thread.sleep(1000);
    customerLoginPage.selectUserName("Harry Potter");
    Thread.sleep(1000);

    customerLoginPage.clickLoginButton();
    Thread.sleep(1000);

}
    @Test (description = "ჰარი პოტერის დალოგინება")
    public void loginTest() throws InterruptedException {
        loginHarryPotter();
        boolean contains = driver.getCurrentUrl().endsWith("account");
        Assert.assertTrue(contains);
    }
   @Test (description = "ანგარიშზე თანხის შეტანა")
    public void depositTest() throws InterruptedException{
        loginHarryPotter();

        double expectedAmount = accountPage.getTotalBalance() + depositAmount;
        accountPage.goBack();
        accountPage.deposit(depositAmount);
        double currentTotalAmount = accountPage.getTotalBalance();
        Assert.assertEquals(currentTotalAmount,expectedAmount);
    }
    @Test (description = "ანგარიშიდან თანხის გამოტანა")
    public void withdrawlTest() throws InterruptedException{
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

   @Test (description = "ანგარიშზე უარყოფითი თანხის დამატება")
    public void addNegativeAmountTest() throws InterruptedException{
        loginHarryPotter();
        double totalBalance = accountPage.getTotalBalance();
        accountPage.goBack();
        accountPage.deposit(-depositAmount);
        double newBalance = accountPage.getTotalBalance();
        Assert.assertEquals(newBalance,totalBalance);
    }

    @Test (description = "ნულოვანი ანგარიშიდან თანხის გამოტანა")
    public void withdrawZeroBalanceTest() throws InterruptedException{
        loginHarryPotter();
        double totalBalance = accountPage.getTotalBalance();
        if(totalBalance !=0)
        {
            accountPage.withdrawl(totalBalance);
        }
        accountPage.goBack();
        accountPage.withdrawl(withdrawAmount);
        double newBalance = accountPage.getTotalBalance();
        Assert.assertEquals(newBalance, 0);
        Assert.assertEquals(newBalance,totalBalance);
    }
    @Test (description = "ანგარიშიდან უარყოფითი თანხის გამოტანა")
    public void withdrawNegativeAmountTest() throws InterruptedException{
        loginHarryPotter();
        double totalBalance = accountPage.getTotalBalance();
        accountPage.goBack();
        accountPage.withdrawl(-withdrawAmount);
        double newBalance = accountPage.getTotalBalance();
        Assert.assertEquals(newBalance,totalBalance);
    }
@Test (description = "მომხმარებლის ანგარიშიდან გამოსვლა")
public void logOutTest() throws InterruptedException {
        loginHarryPotter();
        accountPage.logOut();
    String currentUrl = driver.getCurrentUrl();
    Assert.assertTrue(currentUrl.endsWith("customer"));
}
@Test(description = "არჩეული მომხმარებლის სახელის გამოტანა")
public void welcomePageFullNameTest() throws InterruptedException{
        loginHarryPotter();
        accountPage.welcomePageFullName();
        String welcomePageFullName =accountPage.welcomePageFullName();
        Assert.assertEquals(welcomePageFullName, "Harry Potter");

}
@Test(description = "მომხმარებლის ანგარიშის ნომრის ასახვა")
public void accountNumberChangeTest () throws InterruptedException{
        loginHarryPotter();
        accountPage.changeAccountNumber("1004");
       String accountNumber= accountPage.getAccountNumber();
       Assert.assertEquals(accountNumber,"1004");

}

    @AfterMethod (description = "Chrome ბრაუზერის დახურვა")
    public void tearDown(){
        driver.quit();
    }
}
