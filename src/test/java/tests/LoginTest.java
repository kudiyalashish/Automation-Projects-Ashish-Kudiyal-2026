package tests;
import org.testng.annotations.DataProvider;
import utils.ExcelReader;
import base.BaseTest;
import org.testng.Assert;
import org.testng.annotations.Test;
import pages.InventoryPage;
import pages.LoginPage;
import utils.ConfigReader;

public class LoginTest extends BaseTest {

    @Test
    public void testValidLogin() {
        LoginPage loginPage = new LoginPage(driver);
        loginPage.login(
                ConfigReader.get("username"),
                ConfigReader.get("password")
        );
        InventoryPage inventoryPage = new InventoryPage(driver);
        Assert.assertEquals(inventoryPage.getPageTitle(), "Products");
    }

    @Test
    public void testInvalidLogin() {
        LoginPage loginPage = new LoginPage(driver);
        loginPage.login("wrong_user", "wrong_pass");
        Assert.assertTrue(loginPage.getErrorMessage().contains("Username and password do not match"));
    }

    @Test
    public void testEmptyCredentials() {
        LoginPage loginPage = new LoginPage(driver);
        loginPage.clickLogin();
        Assert.assertTrue(loginPage.getErrorMessage().contains("Username is required"));
    }
    @Test(dataProvider = "loginData")
    public void testLoginWithMultipleUsers(String username, String password) {
        LoginPage loginPage = new LoginPage(driver);
        loginPage.login(username, password);
        InventoryPage inventoryPage = new InventoryPage(driver);
        Assert.assertEquals(inventoryPage.getPageTitle(), "Products");
    }

    @DataProvider(name = "loginData")
    public Object[][] getLoginData() {
        return ExcelReader.getTestData(
                "testdata/LoginData.xlsx",
                "Sheet1"
        );
    }
}
