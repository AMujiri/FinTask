import DataObject.CustomerData;
import DataObject.SearchCustomerData;
import PageObject.AccountPage;
import PageObject.CustomerLoginPage;
import PageObject.HomePage;
import PageObject.ManagerPage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.concurrent.TimeUnit;

public class ManagerTest {
    private ManagerPage managerPage;
    private  HomePage homePage;
    private AccountPage accountPage;
    private CustomerLoginPage customerLoginPage;
    WebDriver driver;

    @BeforeMethod(description ="Chrome ბრაუზერის გახსნა, თაიმ აუთის დაყენება, მისამართზე შესვლა, კლასების ინიციალიზაცია")
    public void setUp() {
        driver = new ChromeDriver();
        driver.manage().timeouts().pageLoadTimeout(20, TimeUnit.SECONDS);
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);

        driver.get("https://www.globalsqa.com/angularJs-protractor/BankingProject/#/login");

        managerPage = new ManagerPage(driver);
        homePage = new HomePage(driver);
        accountPage = new AccountPage(driver);
        customerLoginPage = new CustomerLoginPage(driver);
    }

    @Test(description = "ახალი მომხმარებლის დამატება")
    public void addCustomerTest() throws InterruptedException{
        managerPage.loginAsManager();
        managerPage.clickAddCustomer();
        managerPage.inputFirstName(CustomerData.FirstName);
        managerPage.inputLastName(CustomerData.LastName);
        managerPage.inputPostCode(CustomerData.PostCode);
        managerPage.clickSaveNewCustomer();
        managerPage.goToCustomers();
       boolean newCustomerWasAdded = managerPage.customerExistsInList(CustomerData.FirstName, CustomerData.LastName, CustomerData.PostCode);
       Assert.assertTrue(newCustomerWasAdded);
    }

    @Test (description = "მენეჯერის მიერ გვარით მომხმარებლის მოძებნა ")
    public void searchCustomerTest() throws InterruptedException{
        managerPage.loginAsManager();
        managerPage.goToCustomers();
        managerPage.searchCustomerByLastName(SearchCustomerData.LastName);
        boolean exists = managerPage.customerExistsInList(null,SearchCustomerData.LastName,null);
        Assert.assertTrue(exists);
    }

    @Test(description = "მომხმარებლისათვის ახალი დოლარის ანგარიშის გახსნა")
    public void openNewDollarAccount()throws InterruptedException{
        managerPage.loginAsManager();
        managerPage.clickAddCustomer();
        managerPage.inputFirstName("Anna");
        managerPage.inputLastName("Mujiri");
        managerPage.inputPostCode("0186");
        managerPage.clickSaveNewCustomer();
        managerPage.goToOpenAccount();
        managerPage.openAccountChooseCustomer("Anna Mujiri");
        managerPage.openAccountChooseCurrency("Dollar");
        managerPage.clickProcessButton();
        managerPage.goToHome();
        homePage.loginAsCustomer();
        customerLoginPage.selectUserName("Anna Mujiri");
        customerLoginPage.clickLoginButton();
        double balance = accountPage.getTotalBalance();
        accountPage.goBack();
        String currency = accountPage.getCurrency();
        Assert.assertEquals(balance,0);
        Assert.assertEquals(currency,"Dollar");
    }

    @AfterMethod(description = "Chrome ბრაუზერის დახურვა")
    public void tearDown(){
        driver.quit();
    }

}