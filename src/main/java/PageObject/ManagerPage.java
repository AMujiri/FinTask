package PageObject;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

import java.util.List;

public class ManagerPage {
    WebDriver driver;
    By customerLoginButton = By.xpath("//button[@ng-click='manager()']");
    By addCustomerButton = By.xpath("//button[@ng-click='addCust()']");
    By firstNameInput = By.xpath("//input[@ng-model='fName']");
    By lastNameInput = By.xpath("//input[@ng-model='lName']");
    By postCdNameInput = By.xpath("//input[@ng-model='postCd']");
    By customersListButton = By.xpath("//button[@ng-click='showCust()']");
    By saveCustomerButton = By.xpath("//button[@type='submit']");
    By searchCustomerInput = By.xpath("//input[@type='text']");
    By openAccountButton = By.xpath("//button[@ng-click='openAccount()']");
    By openAccountCustomerSelect = By.id("userSelect");
    By openAccountCurrencySelect = By.id("currency");
    By clickProcessButton = By.xpath("//button[@type='submit']");
    By homeButton = By.xpath("//button[@ng-click='home()']");
    public ManagerPage(WebDriver driver) {
        this.driver = driver;
    }

    @Step("მენეჯერის როლით დალოგინება")
    public void  loginAsManager()throws InterruptedException{
        WebElement button = driver.findElement(customerLoginButton);
        button.click();
        Thread.sleep(1000);
    }

    @Step("მომხმარებლის დამატების ღილაკზე დაწკაპება")
    public void clickAddCustomer() throws InterruptedException{
        WebElement button = driver.findElement(addCustomerButton);
        button.click();
        Thread.sleep(1000);
    }
    @Step("მომხმარებლის საფოსტო ინდექსის შევსება")
    public void inputPostCode(String postCode) throws InterruptedException{
        WebElement input = driver.findElement(postCdNameInput);
        input.sendKeys(postCode);
        Thread.sleep(1000);
    }

    @Step("ახალი მომხმარებლის მონაცემების შენახვა")
    public void clickSaveNewCustomer() throws InterruptedException{
        WebElement button = driver.findElement(saveCustomerButton);
        String text = button.getText();
        button.click();
        driver.switchTo().alert().dismiss();
        Thread.sleep(1000);

    }

    @Step("სიაში მომხმარებლის შემოწმება")
    public boolean customerExistsInList(String firstName, String lastName, String postCode) throws InterruptedException{

        WebElement tableElement = driver.findElement(By.tagName("table"));

        List<WebElement> trCollection = tableElement.findElements(By.tagName("tr"));

        List<WebElement> tdCollection;
        for(int i =1; i<trCollection.size(); i++)
        {
            tdCollection = trCollection.get(i).findElements(By.tagName("td"));
            String t_firstName = tdCollection.get(0).getText();
            String t_lastName = tdCollection.get(1).getText();
            String t_postCode = tdCollection.get(2).getText();
            if((firstName == null || firstName.contains(t_firstName)) &&
                    (lastName == null || lastName.contains(t_lastName)) &&
                    (postCode == null || postCode.contains(t_postCode))){
                return true;
            }
        }
        return false;
    }

    @Step("მომხმარებლის გვარის შეყვანა")
    public void inputLastName(String lastName) throws InterruptedException{
        WebElement input = driver.findElement(lastNameInput);
        input.sendKeys(lastName);
        Thread.sleep(1000);
    }
    @Step("მომხმარებლის სახელის შეყვანა")
    public void inputFirstName (String firstName) throws InterruptedException {
        WebElement input = driver.findElement(firstNameInput);
        input.sendKeys(firstName);
        Thread.sleep(1000);
    }

    @Step("მომხმარებელთა სიის გვერდზე გადასვლა")
    public void goToCustomers() throws  InterruptedException {
        WebElement button = driver.findElement(customersListButton);
        button.click();
        Thread.sleep(1000);
    }

    @Step("მომხმარებლის ძებნა გვარის მიხედვით")
    public void searchCustomerByLastName(String lastName) throws InterruptedException{
        WebElement input = driver.findElement(searchCustomerInput);
        input.sendKeys(lastName);
        Thread.sleep(1000);
    }

    @Step("მომხმარებლის ანგარიშის გახსნის გვერდზე გადასვლა")
    public void goToOpenAccount() throws InterruptedException{
        WebElement button =driver.findElement(openAccountButton);
        button.click();
        Thread.sleep(1000);
    }

    @Step("ანგარიშის გასახსნელად მომხმარებლის არჩევა")
    public void openAccountChooseCustomer(String fullName) {
        Select userSelect = new Select(driver.findElement(openAccountCustomerSelect));
        userSelect.selectByVisibleText(fullName);
    }
    @Step("ანგარიშის გასახსნელად ვალუტის არჩევა")
    public void openAccountChooseCurrency(String currencyName) {
        Select currencySelect = new Select(driver.findElement(openAccountCurrencySelect));
        currencySelect.selectByVisibleText(currencyName);
    }
    @Step("ანგარიშის გასახსნელად Process ღილაკზე დაწკაპება")
    public void clickProcessButton() throws InterruptedException{
        WebElement button =driver.findElement(clickProcessButton);
        button.click();
        driver.switchTo().alert().dismiss();
        Thread.sleep(1000);

    }
@Step("საწყის გვერდზე დაბრუნება")
    public void goToHome() throws InterruptedException {
        WebElement button = driver.findElement(homeButton);
        button.click();
        Thread.sleep(1000);

    }
}