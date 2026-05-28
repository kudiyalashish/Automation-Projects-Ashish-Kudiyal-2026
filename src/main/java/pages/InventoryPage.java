package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import java.util.List;

public class InventoryPage {

    WebDriver driver;

    @FindBy(className = "inventory_item")
    List<WebElement> inventoryItems;

    @FindBy(css = ".inventory_item button")
    List<WebElement> addToCartButtons;

    @FindBy(className = "shopping_cart_link")
    WebElement cartIcon;

    @FindBy(className = "shopping_cart_badge")
    WebElement cartBadge;

    @FindBy(css = ".title")
    WebElement pageTitle;

    public InventoryPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public String getPageTitle() {
        return pageTitle.getText();
    }

    public int getInventoryCount() {
        return inventoryItems.size();
    }

    public void addFirstItemToCart() {
        addToCartButtons.get(0).click();
    }

    public void addItemToCart(int index) {
        addToCartButtons.get(index).click();
    }

    public String getCartBadgeCount() {
        return cartBadge.getText();
    }

    public void goToCart() {
        cartIcon.click();
    }
}