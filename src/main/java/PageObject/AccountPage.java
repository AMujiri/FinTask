package PageObject;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

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

    public void  goBack() throws InterruptedException{
        driver.findElement(backButton).click();
        Thread.sleep(1000);
    }
    public void deposit(double amount) throws InterruptedException{
        Thread.sleep(1000);
        driver.findElement(depositButton).click();
        Thread.sleep(1000);
        driver.findElement(amountInput).sendKeys(Double.toString(amount));
        Thread.sleep(1000);
        driver.findElement(submitButton).click();
        Thread.sleep(1000);

    }
    public void withdrawl (double amount) throws InterruptedException{
        Thread.sleep(1000);
        driver.findElement(withdrawlButton).click();
        Thread.sleep(1000);
        driver.findElement(amountInput).sendKeys(Double.toString(amount));
        Thread.sleep(1000);
        driver.findElement(submitButton).click();
        Thread.sleep(1000);

    }
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



}
