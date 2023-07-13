package PageObject;
import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class HomePage {
    WebDriver driver;

    public HomePage (WebDriver driver) {
        this.driver = driver;
    }
    By customerLoginButton = By.xpath("//button[@class='btn btn-primary btn-lg']");

    @Step("მომხმარებლის log in ღილაკზე დაწკაპება")
    public void loginAsCustomer()
    {
       WebElement button = driver.findElement(customerLoginButton);
       String name = button.getText();
       button.click();
    }
}
