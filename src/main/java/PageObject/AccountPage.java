package PageObject;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

import java.util.List;

public class AccountPage {
    WebDriver driver;
    public AccountPage (WebDriver driver) {
        this.driver = driver;
    }

    By backButton = By.xpath("//button[contains(.,'Back')]");
    By depositButton = By.xpath("//button[contains(.,'Deposit')]");
    By withdrawlButton = By.xpath("//button[contains(.,'Withdrawl')]");
    By transactionsButton = By.xpath("//button[contains(.,'Transactions')]");
    By amountInput =By.xpath("//input[@type='number']");
    By submitButton = By.cssSelector("button[type=submit");
    By accountCurrency = By.xpath("//div[@ng-hide='noAccount']");
    By logOutButton = By.xpath("//button[@ng-click='byebye()']");
    By welcomePageFullNameLabel =By.className("fontBig");
    By accountNumberSelect = By.xpath("//select[@ng-hide='noAccount']");
    By accountNumberLabel = By.xpath("//div[@ng-hide='noAccount']");

    @Step("უკან დაბრუნების ღილაკზე დაწკაპება")
    public void  goBack() throws InterruptedException{
        driver.findElement(backButton).click();
        Thread.sleep(1000);
    }
    @Step("მიმდინარე ანგარიშზე თანხის შეტანა")
    public void deposit(double amount) throws InterruptedException{
        Thread.sleep(1000);
        driver.findElement(depositButton).click();
        Thread.sleep(1000);
        driver.findElement(amountInput).sendKeys(Double.toString(amount));
        Thread.sleep(1000);
        driver.findElement(submitButton).click();
        Thread.sleep(1000);

    }
    @Step("მიმდინარე ანგარიშიდან თანხის გამოტანა")
    public void withdrawl (double amount) throws InterruptedException{
        Thread.sleep(1000);
        driver.findElement(withdrawlButton).click();
        Thread.sleep(1000);
        driver.findElement(amountInput).sendKeys(Double.toString(amount));
        Thread.sleep(1000);
        driver.findElement(submitButton).click();
        Thread.sleep(1000);

    }
    @Step("მიმდინარე ანგარიშის ჯამური ბალანსის ამოკითხვა")
    public double getTotalBalance() throws InterruptedException{
        Thread.sleep(1000);
        driver.findElement(transactionsButton).click();
        Thread.sleep(1000);
        WebElement tableElement = driver.findElement(By.tagName("table"));

        List<WebElement> trCollection = tableElement.findElements(By.tagName("tr"));

        List<WebElement> tdCollection;
        double totalAmount = 0;
        for(int i =1; i<trCollection.size(); i++)
        {
            tdCollection = trCollection.get(i).findElements(By.tagName("td"));
            String amount = tdCollection.get(1).getText();
            String type = tdCollection.get(2).getText();
            if(type.contains("Credit")){
                totalAmount +=Double.parseDouble(amount);
            }else{
                totalAmount -=Double.parseDouble(amount);
            }

        }

        return totalAmount;
    }

    @Step("მიმდინარე ანგარიშის ვალუტის ამოკითხვა")
    public String getCurrency() throws InterruptedException{
        WebElement currencyLabel = driver.findElement(accountCurrency)
                .findElements(By.tagName("strong")).get(2);
        return  currencyLabel.getText();
    }

    @Step("მომხმარებლის გამოსვლა ანგარიშიდან")
    public void logOut() throws InterruptedException{
        WebElement logOut =driver.findElement(logOutButton);
        logOut.click();
        Thread.sleep(1000);

    }
    @Step("მომხმარებლის სახელი ანგარიშზე შესვლისას")
    public String welcomePageFullName() {
        WebElement welcomePageFullName =driver.findElement(welcomePageFullNameLabel);
        return welcomePageFullName.getText();

    }

    public void changeAccountNumber(String accountNumber) {

        Select accountSelect = new Select(driver.findElement(accountNumberSelect));
        accountSelect.selectByVisibleText(accountNumber); // 1004


    }

    public String getAccountNumber() throws InterruptedException{
            WebElement accountNumber = driver.findElement(accountNumberLabel)
                    .findElements(By.tagName("strong")).get(0);
            return  accountNumber.getText();
    }
}
