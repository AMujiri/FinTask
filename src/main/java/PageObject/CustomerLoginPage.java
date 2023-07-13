package PageObject;
import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.Select;


public class CustomerLoginPage {
    WebDriver driver;
    public CustomerLoginPage (WebDriver driver) {
        this.driver = driver;
    }

    By userNameDropdown = By.id("userSelect");
    By loginButton =By.className("btn-default");
    @Step("მომხმარებლის არჩევა")
    public void selectUserName (String name){
        Select userSelect = new Select(driver.findElement(userNameDropdown));
        userSelect.selectByVisibleText(name); // Harry Potter
    }
    @Step ("log in ღილაკზე დაწკაპება")
    public void clickLoginButton (){
        driver.findElement(loginButton).click();

    }
}
